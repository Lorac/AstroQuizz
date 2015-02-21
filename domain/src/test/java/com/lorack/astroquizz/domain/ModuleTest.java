package com.lorack.astroquizz.domain;

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

    @Before
    public void setUp() {
        module = new Module();
        willReturn(true).given(aQuestion).hasNumber(QUESTION_NUMBER);
    }

    @Test
    public void whenCreatingANewModuleItShouldBeEmpty() {
        Module module = new Module();
        module.isEmpty();
    }

    @Test
    public void whenAddingAQuestionToAModuleItShouldHaveOneQuestion() {
        module.addQuestion(aQuestion);
        assertEquals(module.count(), ONE_QUESTION);
    }

    @Test
    public void whenAddingMoreQuestionsShouldBeAbleToChooseOne() {
        giveSomeQuestions();

        Optional<Question> questionResult = module.getQuestionByNumber(QUESTION_NUMBER);
        
        assertEquals(aQuestion, questionResult.get());
    }

    private void giveSomeQuestions() {
        Question question = mock(Question.class);
        Question question2 = mock(Question.class);
        Question question3 = mock(Question.class);

        module.addQuestion(question2);
        module.addQuestion(question3);
        module.addQuestion(question);
        module.addQuestion(aQuestion);
    }


}
