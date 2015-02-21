package com.lorack.astroquizz.services.provider;

import com.lorack.astroquizz.domain.Answer;
import com.lorack.astroquizz.domain.Question;

/**
 * Created by Maxime on 2/4/2015.
 */
public class AnswerProvider {

    /**
     * Provide the answer for the question
     *
     * @param question The question to find the answer
     * @return The Answer
     */
    public Answer getAnswer(Question question) {
        Answer answer = new Answer("A");
        return answer;
    }
}
