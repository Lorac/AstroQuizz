package com.lorack.astroquizz.test.answer;

import com.lorack.astroquizz.answer.AnswerProvider;
import com.lorack.astroquizz.domain.Answer;
import com.lorack.astroquizz.model.Question;
import com.lorack.astroquizz.service.CorrectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maxime on 2/4/2015.
 */
@RunWith(MockitoJUnitRunner.class)
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
