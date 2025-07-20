package cloudNative.mainApi.services;

import cloudNative.mainApi.DTO.QuizDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    private final RestTemplate restTemplate;

    public QuizService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Map<String, Object>> create(QuizDTO quizDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String prompt;
        try {
            prompt = Files.readString(Paths.get("src/main/java/cloudNative/mainApi/prompts/generate-quiz.txt"));
            prompt = String.format(prompt, quizDTO.quantity(), quizDTO.subject(), quizDTO.difficulty());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo de prompt", e);
        }

        ResponseEntity<String> response = restTemplate.exchange(
                "http://agent:8080/agent",
                HttpMethod.POST,
                new HttpEntity<>(prompt, headers),
                String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("Request: \n" + prompt);
        System.out.println("Response from Agent: \n" + responseBody);

        try {
            return objectMapper.readValue(responseBody, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter a resposta de texto para dicionário", e);
        }
    }
}
