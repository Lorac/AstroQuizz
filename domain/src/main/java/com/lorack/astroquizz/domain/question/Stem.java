package com.lorack.astroquizz.domain.question;

public class Stem {

    private final String stem;

    public Stem(String stem) {
        this.stem = stem;
    }

    public String getStem() {
        return stem;
    }

    public boolean isSame(Stem stem) {
        return this.stem.equals(stem.stem);
    }
}
