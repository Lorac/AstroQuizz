package com.lorack.astroquizz.domain.question;

public class QuestionNumber {

    private final String number;

    public QuestionNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean isSame(QuestionNumber questionNumber) {
        return number.equals(questionNumber.number);
    }
}
