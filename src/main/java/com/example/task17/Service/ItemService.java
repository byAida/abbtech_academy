package com.example.task17.Service;

import com.example.task17.Model.Item;
import com.example.task17.Repository.ItemRepository;

import java.util.List;

public class ItemService {

    private ItemRepository itemRepository;

    public ItemService() {
        itemRepository = new ItemRepository();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public boolean deleteItemById(Long id) {
        return itemRepository.deleteById(id);
    }
}
