package com.lorack.astroquizz.services.provider;

import com.lorack.astroquizz.domain.Answer;
import com.lorack.astroquizz.domain.Question;

public class AnswerProvider {

    public Answer getAnswer(Question question) {
        Answer answer = new Answer("A");
        return answer;
    }
}
