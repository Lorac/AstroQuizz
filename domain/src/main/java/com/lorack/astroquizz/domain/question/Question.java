package com.lorack.astroquizz.domain.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object other) {
        Question q = (Question) other;
        if (q == null)
            return false;
        if (q == this)
            return true;
        if (!(q instanceof Question))
            return false;
        if (!Objects.equals(stem, q.getStem()))
            return false;
        if (choices != q.getChoices())
            return false;

        List<String> copy = new ArrayList<>(choices);
        List<String> sourceList = new ArrayList<>(copy);
        List<String> destinationList = new ArrayList<>(q.getChoices());
        sourceList.removeAll(choices);
        copy.removeAll(destinationList);

        return sourceList.isEmpty() && copy.isEmpty();
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + stem.hashCode();
        hash = hash * 31 + choices.hashCode();
        return hash;
    }

}
