package com.literalura.Literalura.main;

import com.literalura.Literalura.service.AuthorService;
import com.literalura.Literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Literalura Menu ===");
            System.out.println("1. Search book by title");
            System.out.println("2. List searched books");
            System.out.println("3. Search books by author");
            System.out.println("4. List searched authors");
            System.out.println("5. List living authors in a range of years");
            System.out.println("6. List books by language");
            System.out.println("7. Generate book statistics");
            System.out.println("8. Top 10 most downloaded books");
            System.out.println("9. Exit");
            System.out.print("Select an option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (option) {
                    case 1 -> bookService.searchBookByTitle(scanner);
                    case 2 -> bookService.listSearchedBooks();
                    case 3 -> authorService.searchAuthorByName(scanner);
                    case 4 -> authorService.listSearchedAuthors();
                    case 5 -> authorService.listLivingAuthorsByYears(scanner);
                    case 6 -> bookService.listBooksByLanguage(scanner);
                    case 7 -> bookService.generateStatistics();
                    case 8 -> bookService.top10MostDownloaded();
                    case 9 -> {
                        System.out.println("Exiting application...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }
}
