package com.skypro.exam.service;

import com.skypro.exam.exceptions.UnSufficientQuestionsException;
import com.skypro.exam.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ExamServiceImpl implements ExamService {
    private final List<QuestionService> questionServices;

    public ExamServiceImpl(List<QuestionService> questionServices) {
        this.questionServices = questionServices;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionServices
                    .get(ThreadLocalRandom.current().nextInt(0, size()))
                    .getRandomQuestion());
        }
        return questions;
    }

    public int size() {
        return questionServices.size();
    }
}
