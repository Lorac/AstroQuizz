package com.lorack.astroquizz.services.correction;

import com.lorack.astroquizz.domain.question.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
