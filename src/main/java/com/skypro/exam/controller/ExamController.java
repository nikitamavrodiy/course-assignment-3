package com.skypro.exam.controller;

import com.skypro.exam.exceptions.UnSufficientQuestionsException;
import com.skypro.exam.model.Question;
import com.skypro.exam.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/get")
public class ExamController {
    private final ExamService examService;

    @ExceptionHandler(UnSufficientQuestionsException.class)
    public ResponseEntity<String> handleException() {
        return ResponseEntity
                .badRequest()
                .body("Недостаточно вопросов");
    }

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return examService.getQuestions(amount);
    }
}
