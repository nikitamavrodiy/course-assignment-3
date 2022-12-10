package com.skypro.exam.service;

import com.skypro.exam.exceptions.UnSufficientQuestionsException;
import com.skypro.exam.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;

    public ExamServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
        if (questionService.getAll().size() < amount) {
            throw new UnSufficientQuestionsException();
        }
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
