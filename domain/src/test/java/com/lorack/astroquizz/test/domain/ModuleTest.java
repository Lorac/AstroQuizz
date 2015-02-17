package com.lorack.astroquizz.test.domain;

import com.lorack.astroquizz.domain.Module;
import com.lorack.astroquizz.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

/**
 * Created by Maxime on 2/4/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ModuleTest {

    private static final int ONE_QUESTION = 1;

    private static final int QUESTION_NUMBER = 2;

    private Module module;

    @Mock
    private Question aQuestion;


    @Test
    public void whenCreatingANewModuleItShouldBeEmpty() {
        Module module = new Module();
        module.isEmpty();
    }


    @Before
    public void setUp() {
        module = new Module();
        willReturn(true).given(aQuestion).hasNumber(QUESTION_NUMBER);
    }

    @Test
    public void whenAddingAQuestionToAModuleItShouldHaveOneQuestion() {
        module.addQuestion(aQuestion);
        assertEquals(module.count(), ONE_QUESTION);
    }

    @Test
    public void whenAddingMoreQuestionsShouldBeAbleToChooseOne() {
        Question question2 = mock(Question.class);
        Question question3 = mock(Question.class);
        module.addQuestion(question2);
        module.addQuestion(question3);
        module.addQuestion(aQuestion);


        Optional<Question> question = module.getQuestionByNumber(QUESTION_NUMBER);
        assertEquals(aQuestion, question.get());
    }


}
