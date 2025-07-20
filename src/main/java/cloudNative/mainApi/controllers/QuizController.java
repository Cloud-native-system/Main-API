package cloudNative.mainApi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import cloudNative.mainApi.DTO.QuizDTO;
import cloudNative.mainApi.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/generate")
    public ResponseEntity<List<Map<String, Object>>> generate(@RequestBody @Valid QuizDTO quizDTO) {

        List<Map<String, Object>> response = quizService.create(quizDTO);
        return ResponseEntity.ok(response);
    }
}
