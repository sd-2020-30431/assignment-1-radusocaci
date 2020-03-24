package com.wasteless.sd.Service;

import com.wasteless.sd.Repository.GroceryRepository;

public class GroceryService {
    private GroceryRepository groceryRepository;

    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }


}
