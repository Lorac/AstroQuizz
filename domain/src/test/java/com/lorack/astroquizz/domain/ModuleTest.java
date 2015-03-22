package com.lorack.astroquizz.domain;

import com.lorack.astroquizz.domain.module.Module;
import com.lorack.astroquizz.domain.module.ModuleName;
import com.lorack.astroquizz.domain.question.Question;
import com.lorack.astroquizz.domain.question.QuestionNumber;
import com.lorack.astroquizz.domain.question.Stem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ModuleTest {

    private static final int ONE_QUESTION = 1;
    private static final QuestionNumber QUESTION_NUMBER = new QuestionNumber("1");

    private Module module;
    private Question question;

    @Before
    public void setUp() {
        module = new Module(mock(ModuleName.class));
        question = new Question(new Stem("STEM"), new LinkedList<>(), QUESTION_NUMBER);
    }

    @Test
    public void whenInitializingAModuleItShouldBeEmpty() {
        assertTrue(module.isEmpty());
    }

    @Test
    public void whenAddingOneQuestionItShouldHaveOneQuestion() {
        module.addQuestion(mock(Question.class));

        assertEquals(ONE_QUESTION, module.size());
    }

    @Test
    public void whenAddingMultipleQuestionWeShouldBeAbleToRetriveOne() {
        module.addQuestion(question);
        module.addQuestion(mock(Question.class));
        module.addQuestion(mock(Question.class));

        Question questionResult = module.getQuestionByNumber(QUESTION_NUMBER);

        assertEquals(question, questionResult);
    }
}

