package com.example.task17.Repository;
import com.example.task17.Model.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private List<Item> items;

    public ItemRepository() {
        items = new ArrayList<>();
        // Sample data
        items.add(new Item(1L, "Item 1", "Description 1"));
        items.add(new Item(2L, "Item 2", "Description 2"));
        items.add(new Item(3L, "Item 3", "Description 3"));
    }

    public List<Item> findAll() {
        return items;
    }

    public boolean deleteById(Long id) {
        return items.removeIf(item -> item.getId().equals(id));
    }
}
