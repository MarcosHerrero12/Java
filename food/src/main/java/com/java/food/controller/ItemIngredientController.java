package com.java.food.controller;

import com.java.food.request.ItemIngredientRequest;
import com.java.food.response.ItemIngredientResponse;
import com.java.food.service.ItemIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-ingredients")
public class ItemIngredientController {
    private final ItemIngredientService service;

    public ItemIngredientController(ItemIngredientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ItemIngredientResponse>> getIngredientsByItem(@RequestParam Long itemId) {
        return ResponseEntity.ok(service.getIngredientsByItem(itemId));
    }

    @PostMapping
    public ResponseEntity<String> addItemIngredient(@RequestBody ItemIngredientRequest request) {
        service.saveItemIngredient(request);
        return ResponseEntity.ok("Ingrediente agregado al ítem exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemIngredient(@PathVariable Long id) {
        service.deleteItemIngredient(id);
        return ResponseEntity.ok("Ingrediente eliminado correctamente");
    }
}
