package com.lorack.astroquizz.domain.question;

public class Stem {

    private final String stem;

    public Stem(String stem) {
        this.stem = stem;
    }

    public String getStem() {
        return stem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Stem stem1 = (Stem) o;

        if (stem != null ? !stem.equals(stem1.stem) : stem1.stem != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stem != null ? stem.hashCode() : 0;
    }
}
