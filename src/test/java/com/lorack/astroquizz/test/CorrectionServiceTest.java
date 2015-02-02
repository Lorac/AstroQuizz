package com.lorack.astroquizz.test;

import com.lorack.astroquizz.model.Question;
import com.lorack.astroquizz.service.CorrectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Maxime.Roussin-Belan on 2/2/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class CorrectionServiceTest {

    @Test
    public void whenAnswerTheQuestionWithTheGoodAnswerReturnTrue() {
        Question aQuestion = mock(Question.class);
        CorrectionService correctionService = new CorrectionService();
        boolean result = correctionService.verifyAnswer(aQuestion, "A");
        assertTrue(result);
    }
}
