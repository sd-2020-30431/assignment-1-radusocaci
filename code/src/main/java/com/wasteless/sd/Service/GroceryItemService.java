package com.wasteless.sd.Service;

import com.wasteless.sd.Model.GroceryListItem;
import com.wasteless.sd.Repository.GroceryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemService {
    private GroceryItemRepository groceryItemRepository;

    public GroceryItemService(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    public List<GroceryListItem> findByListId(Integer listId) {
        return groceryItemRepository.findByGroceryListId(listId);
    }

    public GroceryListItem save(GroceryListItem groceryListItem) {
        return groceryItemRepository.save(groceryListItem);
    }
}
