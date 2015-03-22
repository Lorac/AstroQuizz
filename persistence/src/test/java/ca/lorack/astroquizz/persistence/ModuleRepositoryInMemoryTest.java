package ca.lorack.astroquizz.persistence;

import com.lorack.astroquizz.domain.module.Module;
import com.lorack.astroquizz.domain.module.ModuleName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ModuleRepositoryInMemoryTest {

    private static final int ONE_MODULE = 1;
    private static final String MODULE_NAME = "ANAME";

    private ModuleRepositoryInMemory moduleRepositoryInMemory;
    private ModuleName moduleName;

    @Mock
    private Module aQuestion;

    @Before
    public void setUp() {
        moduleRepositoryInMemory = new ModuleRepositoryInMemory();
        moduleName = new ModuleName(MODULE_NAME);
        willReturn(true).given(aQuestion).hasName(moduleName);
    }

    @Test
    public void whenCreatingANewModuleItShouldBeEmpty() {
        ModuleRepositoryInMemory moduleRepositoryInMemory = new ModuleRepositoryInMemory();
        moduleRepositoryInMemory.isEmpty();
    }

    @Test
    public void whenAddingAQuestionToAModuleItShouldHaveOneQuestion() {
        moduleRepositoryInMemory.addModule(aQuestion);
        assertEquals(moduleRepositoryInMemory.count(), ONE_MODULE);
    }

    @Test
    public void whenAddingMoreQuestionsShouldBeAbleToChooseOne() {
        giveSomeQuestions();

        Module moduleResult = moduleRepositoryInMemory.findModuleByName(moduleName);

        assertEquals(aQuestion, moduleResult);
    }

    private void giveSomeQuestions() {
        Module question = mock(Module.class);
        Module question2 = mock(Module.class);
        Module question3 = mock(Module.class);

        moduleRepositoryInMemory.addModule(question2);
        moduleRepositoryInMemory.addModule(question3);
        moduleRepositoryInMemory.addModule(question);
        moduleRepositoryInMemory.addModule(aQuestion);
    }


}
