package com.lorack.astroquizz.services.test.provider;

import com.lorack.astroquizz.domain.Answer;
import com.lorack.astroquizz.domain.Question;
import com.lorack.astroquizz.services.correction.CorrectionService;
import com.lorack.astroquizz.services.provider.AnswerProvider;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maxime on 2/4/2015.
 */
public class AnswerProviderTest {

    @Mock
    private Answer anAnswer;

    @Mock
    private Question aQuestion;

    @Mock
    private CorrectionService aCorrectionService;
    
    @Test
    public void whenAskedForTheAnswerOfAQuestionShouldReturnTheAnswer() {
        AnswerProvider answerProvider = new AnswerProvider();
        Answer answer = answerProvider.getAnswer(aQuestion);
        assertEquals(answer.getAnswer(), "A");

    }
}
