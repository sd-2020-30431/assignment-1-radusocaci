package com.wasteless.sd.Repository;

import com.wasteless.sd.Model.GroceryListItem;
import org.springframework.data.repository.CrudRepository;

public interface GroceryRepository extends CrudRepository<GroceryListItem, Integer> {

}
