package lesson9;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {

    private List<Book> books;
    private List<User> users;

    public LibraryService(List<Book> books, List<User> users) {
        this.books = books;
        this.users = users;
    }

    public void sortBooks() {
        System.out.println("Sorted Books:");
        books.sort(
                Comparator.comparing(Book::getRating).reversed()
                        .thenComparing(Book::getYear)
                        .thenComparing(Book::getTitle)
        );
        books.forEach(System.out::println);
    }


    public void analyzeLibrary() {
        System.out.println("Library Analysis:");

        // 1. Average rating
        double avgRating = books.stream()
                .mapToDouble(Book::getRating)
                .average()
                .orElse(0);

        System.out.println("Average Rating: " + avgRating);

        List<Book> availableAfter2000 =
                books.stream()
                        .filter(b -> b.getYear() > 2000 && b.isAvailable())
                        .collect(Collectors.toList());

        System.out.println("Available after 2000: " + availableAfter2000);

        Map.Entry<String, Long> mostBorrowed =
                users.stream()
                        .flatMap(u -> u.getBorrowHistory().stream())
                        .collect(Collectors.groupingBy(
                                r -> r.getBook().getTitle(),
                                Collectors.counting()
                        ))
                        .entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        if (mostBorrowed != null)
            System.out.println("Most borrowed book: " + mostBorrowed.getKey()
                    + " (" + mostBorrowed.getValue() + " times)");

        System.out.println("Currently reading:");
        users.stream().forEach(u -> {
            List<Book> reading = u.getBorrowHistory().stream()
                    .filter(r -> r.getReturnedDate() == null)
                    .map(BorrowRecord::getBook)
                    .collect(Collectors.toList());
            System.out.println(u.getName() + " -> " + reading);
        });

        System.out.println("Books grouped by author (after 1950):");
        Map<String, List<Book>> byAuthor =
                books.stream().collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.filtering(b -> b.getYear() > 1950,
                                Collectors.toList())
                ));

        byAuthor.forEach((a, b) -> System.out.println(a + " -> " + b));
    }

    public void uniqueAuthorsRead() {
        System.out.println("Authors read by users:");

        Set<String> authors =
                users.stream()
                        .flatMap(u -> u.getBorrowHistory().stream())
                        .map(r -> r.getBook().getAuthor())
                        .collect(Collectors.toSet());

        System.out.println(authors);
    }

    public Optional<Book> findRecommendedBookForUser(User user) {

        if (user.getBorrowHistory().isEmpty())
            return Optional.empty();

        String favAuthor = user.getBorrowHistory().stream()
                .collect(Collectors.groupingBy(
                        r -> r.getBook().getAuthor(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (favAuthor == null)
            return Optional.empty();

        return books.stream()
                .filter(b -> b.getAuthor().equals(favAuthor))
                .max(Comparator.comparingDouble(Book::getRating));
    }

    public Optional<User> findTopReaderOfMonth(int month, int year) {

        return users.stream()
                .max(Comparator.comparingLong(u ->
                        u.getBorrowHistory().stream()
                                .filter(r -> r.getBorrowedDate().getYear() == year &&
                                        r.getBorrowedDate().getMonthValue() == month)
                                .count()
                ));
    }
}
