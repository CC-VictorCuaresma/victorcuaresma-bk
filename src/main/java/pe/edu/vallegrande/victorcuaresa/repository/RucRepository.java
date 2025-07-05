package pe.edu.vallegrande.victorcuaresa.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.victorcuaresa.model.Ruc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RucRepository extends ReactiveMongoRepository<Ruc, String> {
    
    Mono<Ruc> findByRucAndIsDeleteFalse(String ruc);
    
    Mono<Ruc> findByRuc(String ruc);
    
    Flux<Ruc> findAllByIsDeleteFalse();
    
    Flux<Ruc> findAllByIsDelete(Boolean isDelete);
}
