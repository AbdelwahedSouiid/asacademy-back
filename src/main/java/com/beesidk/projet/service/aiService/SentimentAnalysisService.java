package com.beesidk.projet.service.aiService;


import com.beesidk.projet.config.FastApiProperties;
import com.beesidk.projet.entity.Avis;
import com.beesidk.projet.entity.Reclamation;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SentimentAnalysisService {


    private final WebClient.Builder webClientBuilder;
    private final FastApiProperties fastApiProperties;

    public SentimentAnalysisService(WebClient.Builder webClientBuilder, FastApiProperties fastApiProperties) {
        this.webClientBuilder = webClientBuilder;
        if (this.webClientBuilder == null) {
            throw new IllegalStateException("WebClient.Builder is not initialized");
        }
        this.fastApiProperties = fastApiProperties;
    }


    public Mono<String> predict_note(Avis avis) {
        // Construire l'URL finale en ajoutant le préfixe
        String url = fastApiProperties.getBaseUrl() + "/sentiment/predict-sentiment"; // Ajoutez le préfixe spécifique

        return webClientBuilder.build()
                .post()
                .uri(url)
                .bodyValue(avis)
                .retrieve()
                .bodyToMono(SentimentResponse.class)  // Utiliser un DTO pour la réponse
                .map(SentimentResponse::getNote);
    }

    // DTO pour mapper la réponse de l'API FastAPI
    private static class SentimentResponse {
        private String note;

        @JsonProperty("note")
        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
