package pe.edu.vallegrande.victorcuaresa.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.edu.vallegrande.victorcuaresa.model.Ruc;
import reactor.core.publisher.Mono;

@Component
public class RucApiClient {
    
    private final WebClient webClient;
    
    @Value("${api.ruc.base-url:https://dniruc.apisperu.com/api/v1}")
    private String baseUrl;
    
    @Value("${api.ruc.token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1pY2hhZWxsLmliYXJyYUB2YWxsZWdyYW5kZS5lZHUucGUifQ.g06VWXFGCGt1fYra5VT6_WtOlmBCqY8esWCpYd705zc}")
    private String token;
    
    public RucApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    
    public Mono<Ruc> fetchRucData(String ruc) {
        return webClient.get()
                .uri(baseUrl + "/ruc/{ruc}?token={token}", ruc, token)
                .retrieve()
                .bodyToMono(Ruc.class)
                .onErrorResume(throwable -> {
                    // Log del error
                    System.err.println("Error fetching RUC data for: " + ruc + " - " + throwable.getMessage());
                    return Mono.empty();
                });
    }
}
