package repository;

import com.lorack.astroquizz.domain.module.Module;
import com.lorack.astroquizz.domain.module.ModuleName;

public interface ModuleRespository {

    void addModule(Module module);

    Module findModuleByName(ModuleName moduleName);
}
