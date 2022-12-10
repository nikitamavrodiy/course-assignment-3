package com.skypro.exam.service;

import com.skypro.exam.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MathQuestionService implements QuestionService{
    @Override
    public Question add(String question, String answer) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public Question add(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public Question remove(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public Collection<Question> getAll() {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }

    public Question getRandomQuestion() {
        MathOperation mathOperation = MathOperation.values()
                [ThreadLocalRandom.current().nextInt(0, MathOperation.values().length)];
        int num1 = ThreadLocalRandom.current().nextInt(1, 1000);
        int num2 = ThreadLocalRandom.current().nextInt(1, 1000);

        return new Question(mathOperation.getQuestion(num1, num2),
                String.valueOf(mathOperation.getAnswer(num1, num2)));
    }

}
