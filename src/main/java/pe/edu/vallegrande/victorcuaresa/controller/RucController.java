package pe.edu.vallegrande.victorcuaresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.victorcuaresa.model.Ruc;
import pe.edu.vallegrande.victorcuaresa.service.RucService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ruc")
@CrossOrigin(origins = "*")
public class RucController {
    
    @Autowired
    private RucService rucService;
    
    /**
     * Endpoint: GET /api/ruc/fetch/{ruc}
     * Registrar y guardar RUC desde API externa
     */
    @GetMapping("/fetch/{ruc}")
    public Mono<ResponseEntity<Ruc>> fetchAndSaveRuc(@PathVariable String ruc) {
        return rucService.fetchAndSaveRuc(ruc)
                .map(savedRuc -> ResponseEntity.ok(savedRuc))
                .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    
    /**
     * Endpoint: GET /api/ruc/list
     * Listar todos los RUC no eliminados
     */
    @GetMapping("/list")
    public Flux<Ruc> listAllRucs() {
        return rucService.listAllRucs();
    }
    
    /**
     * Endpoint: PATCH /api/ruc/delete/{ruc}
     * Borrado lógico (isDelete=true)
     */
    @PatchMapping("/delete/{ruc}")
    public Mono<ResponseEntity<Map<String, Object>>> logicalDeleteRuc(
            @PathVariable String ruc,
            @RequestBody Map<String, Boolean> request) {
        
        Boolean isDelete = request.get("isDelete");
        if (isDelete == null || !isDelete) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "isDelete field must be true");
            return Mono.just(ResponseEntity.badRequest().body(errorResponse));
        }
        
        return rucService.logicalDeleteRuc(ruc)
                .map(deletedRuc -> {
                    Map<String, Object> successResponse = new HashMap<>();
                    successResponse.put("message", "RUC deleted successfully");
                    successResponse.put("ruc", ruc);
                    successResponse.put("isDelete", deletedRuc.getIsDelete());
                    return ResponseEntity.ok(successResponse);
                })
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(createErrorMap("RUC not found or already deleted")));
    }
    
    /**
     * Endpoint: PATCH /api/ruc/restore/{ruc}
     * Restaurar lógico (isDelete=false)
     */
    @PatchMapping("/restore/{ruc}")
    public Mono<ResponseEntity<Map<String, Object>>> restoreRuc(
            @PathVariable String ruc,
            @RequestBody Map<String, Boolean> request) {
        
        Boolean isDelete = request.get("isDelete");
        if (isDelete == null || isDelete) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "isDelete field must be false");
            return Mono.just(ResponseEntity.badRequest().body(errorResponse));
        }
        
        return rucService.restoreRuc(ruc)
                .map(restoredRuc -> {
                    Map<String, Object> successResponse = new HashMap<>();
                    successResponse.put("message", "RUC restored successfully");
                    successResponse.put("ruc", ruc);
                    successResponse.put("isDelete", restoredRuc.getIsDelete());
                    return ResponseEntity.ok(successResponse);
                })
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(createErrorMap("RUC not found or not deleted")));
    }
    
    /**
     * Endpoint adicional: GET /api/ruc/{ruc}
     * Buscar un RUC específico
     */
    @GetMapping("/{ruc}")
    public Mono<ResponseEntity<Ruc>> findRucByNumber(@PathVariable String ruc) {
        return rucService.findRucByNumber(ruc)
                .map(foundRuc -> ResponseEntity.ok(foundRuc))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    // Método helper para crear mapas de error
    private Map<String, Object> createErrorMap(String errorMessage) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", errorMessage);
        return errorMap;
    }
}
