package com.lorack.astroquizz.test.domain;

import com.lorack.astroquizz.domain.Module;
import com.lorack.astroquizz.domain.Question;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Maxime on 2/4/2015.
 */
public class ModuleTest {

    private static final int ONE_QUESTION = 1;

    @Test
    public void whenAddingAQuestionToAModuleItShouldHaveOneQuestion() {
        Module module = new Module();
        module.addQuestion(mock(Question.class));
        assertEquals(module.count(), ONE_QUESTION);
    }


}
