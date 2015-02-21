package com.lorack.astroquizz.services.correction;

import com.lorack.astroquizz.domain.Question;
import com.lorack.astroquizz.services.correction.CorrectionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Maxime.Roussin-Belan on 2/2/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class CorrectionServiceTest {

    @Test
    public void whenAnswerTheQuestionWithTheGoodAnswerReturnTrue() {
        Question aQuestion = Mockito.mock(Question.class);
        CorrectionService correctionService = new CorrectionService();
        boolean result = correctionService.verifyAnswer(aQuestion, "A");
        Assert.assertTrue(result);
    }
}
