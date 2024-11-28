package com.literalura.Literalura.service;

import com.literalura.Literalura.model.Data;
import com.literalura.Literalura.model.DataBooks;
import com.literalura.Literalura.util.JsonFileManager;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

@Service
public class BookService {
    private static final String BASE_URL = "https://gutendex.com/books/";
    private final ApiClient apiClient = new ApiClient();
    private final JsonFileManager jsonFileManager = new JsonFileManager();

    public void searchBookByTitle(Scanner scanner) {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        String json = apiClient.getData(BASE_URL + "?search=" + title.replace(" ", "+"));

        Data data = jsonFileManager.loadFromJsonString(json, Data.class);


        if (data != null && !data.results().isEmpty()) {
            System.out.println("\n=== Books found ===");
            data.results().forEach(book -> System.out.println(book.title()));
            jsonFileManager.saveToJson("searchedBooks.json", data.results());
        } else {
            System.out.println("No books with that title were found.");
        }
    }

    public void listSearchedBooks() {
        var books = jsonFileManager.loadFromJson("searchedBooks.json", DataBooks[].class);
        if (books != null && books.length > 0) {
            System.out.println("\n=== Books searched ===");
            for (var book : books) {
                System.out.println(book.title());
            }
        } else {
            System.out.println("No searched books registered.");
        }
    }

    public void top10MostDownloaded() {
        var books = jsonFileManager.loadFromJson("searchedBooks.json", DataBooks[].class);
        if (books != null && books.length > 0) {
            System.out.println("\n=== Top 10 Most Downloaded Books ===");
            Arrays.stream(books)
                    .sorted(Comparator.comparing(DataBooks::download_count).reversed())
                    .limit(10)
                    .forEach(book -> System.out.println(book.title() + " - Downloads: " + book.download_count()));
        } else {
            System.out.println("No books found to display.");
        }
    }

    public void listBooksByLanguage(Scanner scanner) {
        System.out.print("Enter the language (e.g., 'en', 'fr'): ");
        String language = scanner.nextLine();

        var books = jsonFileManager.loadFromJson("searchedBooks.json", DataBooks[].class);

        var booksByLanguage = Arrays.stream(books)
                .filter(book -> book.languages().contains(language))
                .toList();

        if (!booksByLanguage.isEmpty()) {
            System.out.println("\n=== Books in " + language + " ===");
            booksByLanguage.forEach(book -> System.out.println(book.title()));
        } else {
            System.out.println("No books found in that language.");
        }
    }

    public void generateStatistics() {
        var books = jsonFileManager.loadFromJson("searchedBooks.json", DataBooks[].class);

        if (books != null && books.length > 0) {
            System.out.println("\n=== Statistics ===");
            long totalBooks = books.length;
            long totalDownloads = Arrays.stream(books).mapToLong(book -> book.download_count().longValue()).sum();

            System.out.println("Total books: " + totalBooks);
            System.out.println("Total downloads: " + totalDownloads);
            System.out.println("Average downloads per book: " + (double) totalDownloads / totalBooks);
        } else {
            System.out.println("No books found to generate statistics.");
        }
    }
}
