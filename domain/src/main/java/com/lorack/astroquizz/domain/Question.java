package com.lorack.astroquizz.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Maxime.Roussin-Belan on 2/2/2015.
 */
public class Question {

    private final String stem;
    private final List<String> choices;
    private final int number;

    public Question(String stem, List<String> choices, int questionNumber) {
        this.stem = stem;
        this.choices = choices;
        this.number = questionNumber;
    }

    public String getStem() {
        return stem;
    }

    public List<String> getChoices() {
        return choices;
    }

    public boolean hasNumber(int questionNumber) {
        return this.number == questionNumber;
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
