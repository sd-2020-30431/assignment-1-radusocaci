package com.wasteless.sd.Service;

import com.wasteless.sd.Model.GroceryListItem;
import com.wasteless.sd.Repository.GroceryItemRepository;
import com.wasteless.sd.Repository.GroceryListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryItemService {
    private GroceryItemRepository groceryItemRepository;
    private GroceryListRepository groceryListRepository;

    public GroceryItemService(GroceryItemRepository groceryItemRepository,
                              GroceryListRepository groceryListRepository) {
        this.groceryItemRepository = groceryItemRepository;
        this.groceryListRepository = groceryListRepository;
    }

    public List<GroceryListItem> findByListId(Integer listId) {
        return groceryItemRepository.findByGroceryListId(listId);
    }

    public void save(GroceryListItem groceryListItem, Integer listId) {
        groceryListRepository.findById(listId).map(list -> {
            groceryListItem.setGroceryList(list);
            List<GroceryListItem> listItems = findByListId(listId);
            List<String> listItemNames = listItems
                    .stream()
                    .map(GroceryListItem::getName)
                    .collect(Collectors.toList());
            int i = listItemNames.indexOf(groceryListItem.getName());
            if (i != -1) {
                listItems.stream()
                        .filter(item -> item.getName().equals(listItemNames.get(i)))
                        .findFirst()
                        .ifPresent(item -> groceryListItem.setId(item.getId()));
            }
            return groceryItemRepository.save(groceryListItem);
        });
    }

    public void deleteGroceryItem(Integer id) {
        groceryItemRepository.deleteById(id);
    }

    public GroceryListItem findById(Integer id) {
        return groceryItemRepository.findById(id).get();
    }
}
