package com.lorack.astroquizz.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Maxime on 2/4/2015.
 */
public class Module {

    private List<Question> questions;

    public Module() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public int count() {
        return questions.size();
    }

    public boolean isEmpty() {
        return questions.isEmpty();
    }

    public Optional<Question> getQuestionByNumber(int questionNumber) {
        return questions.stream().filter(question -> question.hasNumber(questionNumber)).findFirst();
    }
}
