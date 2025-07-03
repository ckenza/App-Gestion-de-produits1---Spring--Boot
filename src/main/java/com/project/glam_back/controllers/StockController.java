package com.project.glam_back.controllers;

import com.project.glam_back.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @PostMapping("/decrease")
    public ResponseEntity<String> decreaseStock(@RequestBody Map<String, Integer> body){
        Integer id = body.get("idProduct");
        Integer quantity = body.get("quantity");

        if(id == null || quantity == null){
            return ResponseEntity.badRequest().body("Paramètres manquants");
        }

        boolean result = stockService.decreaseStock(id, quantity);
        return result ? ResponseEntity.ok("Stock décrémenté")
                      : ResponseEntity.badRequest().body("Stock insuffisant");
    }

    @PostMapping("/increase/{id}")
    public ResponseEntity<String> increaseStock(@RequestParam int idProduct, @RequestParam int quantity){
        stockService.increaseStock(idProduct, quantity);
        return ResponseEntity.ok("Stock incrémenté");
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isStockAvailable(@RequestParam int idProduct, @RequestParam int quantity){
        boolean available = stockService.isStockAvailable(idProduct, quantity);
        return ResponseEntity.ok(available);
    }
}
