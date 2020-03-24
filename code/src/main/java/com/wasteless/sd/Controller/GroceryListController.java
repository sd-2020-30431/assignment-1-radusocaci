package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.GroceryList;
import com.wasteless.sd.Service.GroceryListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class GroceryListController {
    private GroceryListService groceryListService;

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    @GetMapping("/grocery-lists")
    public String getAllGroceryLists(Principal principal, Model model) {
        GroceryList groceryList = new GroceryList();
        groceryList.setUsername(principal.getName());
        model.addAttribute("addList", groceryList);
        model.addAttribute("groceryLists", groceryListService.findAllByUsername(principal.getName()));
        return "home";
    }

    @RequestMapping("/grocery-lists/delete/{id}")
    public String deleteGroceryList(@PathVariable("id") Integer id) {
        groceryListService.deleteGroceryList(id);
        return "redirect:/grocery-lists";
    }

    @PostMapping(path = "/create-list")
    public String createGroceryList(GroceryList groceryList, Principal principal) {
        groceryListService.save(groceryList);
        return "redirect:/grocery-lists";
    }
}
