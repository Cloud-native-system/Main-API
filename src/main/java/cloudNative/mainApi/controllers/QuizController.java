package cloudNative.mainApi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @PostMapping("/generate")
    public String generate() {
        // Logic to generate a quiz
        return "Quiz generated successfully!";
    }
}
