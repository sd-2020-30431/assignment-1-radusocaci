package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.GroceryListItem;
import com.wasteless.sd.Service.GroceryItemService;
import com.wasteless.sd.Service.GroceryListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroceryItemController {
    private GroceryItemService groceryItemService;
    private GroceryListService groceryListService;

    public GroceryItemController(GroceryItemService groceryItemService, GroceryListService groceryListService) {
        this.groceryItemService = groceryItemService;
        this.groceryListService = groceryListService;
    }

    @GetMapping("/grocery-lists/{listId}/items")
    @ResponseBody
    public List<GroceryListItem> getAllItemsByListId(@PathVariable(value = "listId") Integer listId) {
        return groceryItemService.findByListId(listId);
    }

    @PostMapping("/grocery-lists/{listId}/items")
    public ResponseEntity<HttpStatus> createGroceryItem(@PathVariable(value = "listId") Integer listId,
                                                        @Valid @RequestBody GroceryListItem groceryListItem) {
        groceryListService.findById(listId).map(list -> {
            groceryListItem.setGroceryList(list);
            return groceryItemService.save(groceryListItem);
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
