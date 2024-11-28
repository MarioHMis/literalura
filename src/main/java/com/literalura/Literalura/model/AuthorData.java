package com.literalura.Literalura.model;

public record AuthorData(
        String name,
        Integer birthYear,
        Integer deathYear
) {
    public String getName() {
        return name;
    }

    public Integer getYearOfBirth() {
        return birthYear;
    }

    public Integer getYearOfDeath() {
        return deathYear;
    }
}
