package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.GroceryList;
import com.wasteless.sd.Service.GroceryListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class GroceryListController {
    private GroceryListService groceryListService;

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    @GetMapping("/grocery-lists")
    @ResponseBody
    public List<GroceryList> getAllPosts(Principal principal) {
        return groceryListService.findAllByUsername(principal.getName());
    }

    @PostMapping("/grocery-lists")
    public ResponseEntity<HttpStatus> createGroceryList(@Valid @RequestBody GroceryList groceryList) {
        groceryListService.save(groceryList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
