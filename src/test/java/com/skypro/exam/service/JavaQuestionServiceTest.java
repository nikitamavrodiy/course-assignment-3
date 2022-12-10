package com.skypro.exam.service;

import com.skypro.exam.exceptions.UnSufficientQuestionsException;
import com.skypro.exam.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;
    private List<Question> questions = List.of(
            new Question("q1", "a1"),
            new Question("q2", "a2"),
            new Question("q3", "a3"),
            new Question("q4", "a4"),
            new Question("q5", "a5")
    );

    @BeforeEach
    void setUp() {
        this.javaQuestionService = new JavaQuestionService();
        questions.forEach(javaQuestionService::add);
    }

    @Test
    void addQuestionTest() {
        Question question = new Question("q6", "a6");
        Question actual = this.javaQuestionService.add(question);
        assertThat(actual).isEqualTo(question);
        assertThat(this.javaQuestionService.getAll())
                .hasSize(6)
                .contains(question);
    }

    @Test
    void removeQuestionTest() {
        Question expected = this.questions.get(0);
        Question actual = this.javaQuestionService.remove(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(this.javaQuestionService.getAll())
                .hasSize(4)
                .doesNotContain(expected);
    }

    @Test
    void getRandomQuestion() {
        Question actual = this.javaQuestionService.getRandomQuestion();
        assertThat(actual).isIn(questions);
    }

    @Test
    void getAllTest() {
        assertThat(this.javaQuestionService.getAll())
                .hasSize(5)
                .containsAll(this.questions);
    }

    @Test
    void whenListIsEmptyThenGetRandomQuestionReturnException() {
        this.javaQuestionService = new JavaQuestionService();
        assertThatThrownBy(() -> javaQuestionService.getRandomQuestion())
                .isInstanceOf(UnSufficientQuestionsException.class);
    }
}
