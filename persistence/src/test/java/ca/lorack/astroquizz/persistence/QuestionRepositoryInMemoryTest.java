package ca.lorack.astroquizz.persistence;

import com.lorack.astroquizz.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class QuestionRepositoryInMemoryTest {

    private static final int ONE_QUESTION = 1;

    private static final int QUESTION_NUMBER = 2;

    private QuestionRepositoryInMemory questionRepositoryInMemory;

    @Mock
    private Question aQuestion;

    @Before
    public void setUp() {
        questionRepositoryInMemory = new QuestionRepositoryInMemory();
        BDDMockito.willReturn(true).given(aQuestion).hasNumber(QUESTION_NUMBER);
    }

    @Test
    public void whenCreatingANewModuleItShouldBeEmpty() {
        QuestionRepositoryInMemory questionRepositoryInMemory = new QuestionRepositoryInMemory();
        questionRepositoryInMemory.isEmpty();
    }

    @Test
    public void whenAddingAQuestionToAModuleItShouldHaveOneQuestion() {
        questionRepositoryInMemory.addQuestion(aQuestion);
        assertEquals(questionRepositoryInMemory.count(), ONE_QUESTION);
    }

    @Test
    public void whenAddingMoreQuestionsShouldBeAbleToChooseOne() {
        giveSomeQuestions();

        Optional<Question> questionResult = questionRepositoryInMemory.findQuestionByNumber(QUESTION_NUMBER);

        assertEquals(aQuestion, questionResult.get());
    }

    private void giveSomeQuestions() {
        Question question = mock(Question.class);
        Question question2 = mock(Question.class);
        Question question3 = mock(Question.class);

        questionRepositoryInMemory.addQuestion(question2);
        questionRepositoryInMemory.addQuestion(question3);
        questionRepositoryInMemory.addQuestion(question);
        questionRepositoryInMemory.addQuestion(aQuestion);
    }


}
