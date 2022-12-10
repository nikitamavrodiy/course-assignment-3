package com.skypro.exam.service;

import com.skypro.exam.exceptions.UnSufficientQuestionsException;
import com.skypro.exam.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {
    @Mock
    QuestionService questionService;
    @InjectMocks
    ExamServiceImpl examService;

    @Test
    void whenAmountOfQuestionsIsSufficientThenGetQuestionReturnExactAmount() {
        List<Question> questionList = List.of(
                new Question("q1","a1"),
                new Question("q2","a2"),
                new Question("q3","a3")
        );
        when(questionService.getAll()).thenReturn(questionList);
        when(questionService.getRandomQuestion())
                .thenReturn(
                        questionList.get(0),
                        questionList.get(1));

        assertThat(examService.getQuestions(2))
                .hasSize(2)
                .containsOnly(questionList.get(0), questionList.get(1));
    }

    @Test
    void whenAmountOfQuestionsIsUnSufficientThenMethodThrowsException() {
        when(questionService.getAll()).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> examService.getQuestions(100))
                .isInstanceOf(UnSufficientQuestionsException.class);
    }
}
