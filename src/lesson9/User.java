package lesson9;

import java.util.List;

public class User {
    private String name;
    private int age;
    private List<BorrowRecord> borrowHistory;

    public User(String name, int age, List<BorrowRecord> borrowHistory) {
        this.name = name;
        this.age = age;
        this.borrowHistory = borrowHistory;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public List<BorrowRecord> getBorrowHistory() { return borrowHistory; }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

