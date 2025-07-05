package pe.edu.vallegrande.victorcuaresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.victorcuaresa.client.RucApiClient;
import pe.edu.vallegrande.victorcuaresa.model.Ruc;
import pe.edu.vallegrande.victorcuaresa.repository.RucRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class RucService {
    
    @Autowired
    private RucRepository rucRepository;
    
    @Autowired
    private RucApiClient rucApiClient;
    
    /**
     * Busca un RUC en la API externa y lo guarda en la base de datos
     */
    public Mono<Ruc> fetchAndSaveRuc(String ruc) {
        // Primero verificar si ya existe en BD
        return rucRepository.findByRuc(ruc)
                .flatMap(existingRuc -> {
                    // Si existe pero está marcado como borrado, lo restauramos
                    if (existingRuc.getIsDelete()) {
                        existingRuc.setIsDelete(false);
                        existingRuc.setUpdatedAt(LocalDateTime.now());
                        return rucRepository.save(existingRuc);
                    }
                    // Si existe y no está borrado, retornamos el existente
                    return Mono.just(existingRuc);
                })
                .switchIfEmpty(
                    // Si no existe, buscamos en la API externa
                    rucApiClient.fetchRucData(ruc)
                            .flatMap(apiRuc -> {
                                // Aseguramos que tenga el RUC correcto
                                apiRuc.setRuc(ruc);
                                apiRuc.setCreatedAt(LocalDateTime.now());
                                apiRuc.setUpdatedAt(LocalDateTime.now());
                                apiRuc.setIsDelete(false);
                                return rucRepository.save(apiRuc);
                            })
                );
    }
    
    /**
     * Lista todos los RUCs no eliminados
     */
    public Flux<Ruc> listAllRucs() {
        return rucRepository.findAll();
    }
    
    /**
     * Borrado lógico de un RUC
     */
    public Mono<Ruc> logicalDeleteRuc(String ruc) {
        return rucRepository.findByRucAndIsDeleteFalse(ruc)
                .flatMap(existingRuc -> {
                    existingRuc.setIsDelete(true);
                    existingRuc.setUpdatedAt(LocalDateTime.now());
                    return rucRepository.save(existingRuc);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("RUC not found or already deleted: " + ruc)));
    }
    
    /**
     * Restaurar un RUC eliminado lógicamente
     */
    public Mono<Ruc> restoreRuc(String ruc) {
        return rucRepository.findByRuc(ruc)
                .flatMap(existingRuc -> {
                    if (!existingRuc.getIsDelete()) {
                        return Mono.error(new RuntimeException("RUC is not deleted: " + ruc));
                    }
                    existingRuc.setIsDelete(false);
                    existingRuc.setUpdatedAt(LocalDateTime.now());
                    return rucRepository.save(existingRuc);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("RUC not found: " + ruc)));
    }
    
    /**
     * Buscar un RUC específico (solo los no eliminados)
     */
    public Mono<Ruc> findRucByNumber(String ruc) {
        return rucRepository.findByRucAndIsDeleteFalse(ruc);
    }
}
