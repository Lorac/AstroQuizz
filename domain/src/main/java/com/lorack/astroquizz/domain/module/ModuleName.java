package com.lorack.astroquizz.domain.module;

import org.apache.commons.lang.builder.EqualsBuilder;

public class ModuleName {

    private final String name;

    public ModuleName(String name) {
        this.name = name;
    }

    public boolean isSame(ModuleName moduleName) {
        return EqualsBuilder.reflectionEquals(this, moduleName);
    }
}

