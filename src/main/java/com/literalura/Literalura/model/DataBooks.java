package com.literalura.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBooks(
        String title,
        List<AuthorData> authors,
        List<String> languages,
        Double download_count
) {}
