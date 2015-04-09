package com.lorack.astroquizz.domain.question;

import java.util.List;

public class Question {

    private final Stem stem;
    private final List<String> choices;
    private final QuestionNumber number;

    public Question(Stem stem, List<String> choices, QuestionNumber questionNumber) {
        this.stem = stem;
        this.choices = choices;
        this.number = questionNumber;
    }

    public Stem getStem() {
        return stem;
    }

    public List<String> getChoices() {
        return choices;
    }

    public boolean hasNumber(QuestionNumber questionNumber) {
        return this.number == questionNumber;
    }

    public QuestionNumber getQuestionNumber() {
        return this.number;
    }

    public boolean isSame(Question question) {
        return stem.isSame(question.stem) && number.equals(question.number);
    }
}
