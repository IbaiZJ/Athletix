package com.athletix.util;

import java.io.Serializable;
import java.util.Objects;

public class AsignacionId implements Serializable {

    private Integer user;
    private Integer exercise;

    public AsignacionId() {}

    public AsignacionId(Integer user, Integer exercise) {
        this.user = user;
        this.exercise = exercise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AsignacionId)) return false;
        AsignacionId that = (AsignacionId) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(exercise, that.exercise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, exercise);
    }

    // Getters y setters si tu IDE o proyecto los necesita
}
