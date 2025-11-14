package lesson9;
import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int year;
    private double rating;
    private boolean isAvailable;

    public Book(String title, String author, int year, double rating, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public double getRating() { return rating; }
    public boolean isAvailable() { return isAvailable; }

    @Override
    public String toString() {
        return title + " (" + author + ", " + year + ") " + rating;
    }
}

