package ca.lorack.astroquizz.persistence;

import com.lorack.astroquizz.domain.module.Module;
import com.lorack.astroquizz.domain.module.ModuleName;
import repository.ModuleRespository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ModuleRepositoryInMemory implements ModuleRespository {

    private List<Module> modules;

    public ModuleRepositoryInMemory() {
        modules = new ArrayList<>();
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public int count() {
        return modules.size();
    }

    public boolean isEmpty() {
        return modules.isEmpty();
    }

    public Module findModuleByName(ModuleName moduleName) {
        return modules.stream().filter(m -> m.hasName(moduleName)).findFirst()
            .orElseThrow(() -> new NoSuchElementException("Cannot find the module"));
    }
}
