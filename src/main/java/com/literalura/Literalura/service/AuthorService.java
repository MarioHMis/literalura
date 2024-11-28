package com.literalura.Literalura.service;

import com.literalura.Literalura.model.AuthorData;
import com.literalura.Literalura.util.JsonFileManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class AuthorService {
    private final JsonFileManager jsonFileManager = new JsonFileManager();

    public void searchAuthorByName(Scanner scanner) {
        System.out.print("Enter the author name: ");
        String authorName = scanner.nextLine();

        var authors = jsonFileManager.loadFromJson("authors.json", AuthorData[].class);

        List<AuthorData> matchingAuthors = List.of(authors).stream()
                .filter(author -> author.getName().toLowerCase().contains(authorName.toLowerCase()))
                .toList();

        if (!matchingAuthors.isEmpty()) {
            System.out.println("\n=== Authors Found ===");
            matchingAuthors.forEach(author -> System.out.println(author.getName()));
            jsonFileManager.saveToJson("searchedAuthors.json", matchingAuthors);
        } else {
            System.out.println("No authors found.");
        }
    }

    public void listSearchedAuthors() {
        var authors = jsonFileManager.loadFromJson("searchedAuthors.json", AuthorData[].class);
        if (authors != null && authors.length > 0) {
            System.out.println("\n=== Searched Authors ===");
            for (var author : authors) {
                System.out.println(author.getName());
            }
        } else {
            System.out.println("No searched authors registered.");
        }
    }

    public void listLivingAuthorsByYears(Scanner scanner) {
        System.out.print("Enter the starting year: ");
        int startYear = scanner.nextInt();
        System.out.print("Enter the ending year: ");
        int endYear = scanner.nextInt();

        var authors = jsonFileManager.loadFromJson("authors.json", AuthorData[].class);

        List<AuthorData> livingAuthors = List.of(authors).stream()
                .filter(author -> author.getYearOfBirth() >= startYear &&
                        (author.getYearOfDeath() == null || author.getYearOfDeath() <= endYear))
                .toList();

        if (!livingAuthors.isEmpty()) {
            System.out.println("\n=== Living Authors ===");
            livingAuthors.forEach(author -> System.out.println(author.getName()));
        } else {
            System.out.println("No living authors found in the specified range.");
        }
    }
}
