package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.GroceryListItem;
import com.wasteless.sd.Service.GroceryItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroceryItemController {
    private GroceryItemService groceryItemService;

    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping("/grocery-lists/{listId}")
    public String getAllItemsByListId(@PathVariable Integer listId, Model model) {
        model.addAttribute("items", groceryItemService.findByListId(listId));
        model.addAttribute("listId", listId);
        model.addAttribute("addItem", new GroceryListItem());
        return "groc-list-items";
    }

    @PostMapping(path = "/create-item/{listId}")
    public String createGroceryList(GroceryListItem groceryItem, @PathVariable(value = "listId") Integer listId) {
        groceryItemService.save(groceryItem, listId);
        return "redirect:/grocery-lists/" + listId;
    }

    @RequestMapping("/grocery-item/delete/{id}")
    public String deleteGroceryList(@PathVariable("id") Integer id) {
        Integer listId = groceryItemService.findById(id).getGroceryList().getId();
        groceryItemService.deleteGroceryItem(id);
        return "redirect:/grocery-lists/" + listId;
    }
}
