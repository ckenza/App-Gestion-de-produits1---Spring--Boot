package com.project.glam_back.controllers;

import com.project.glam_back.daos.InvoiceItemDao;
import com.project.glam_back.entities.InvoiceItem;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice-item")
public class InvoiceItemController {


    private final InvoiceItemDao invoiceItemDao;

    public InvoiceItemController(InvoiceItemDao invoiceItemDao) {
        this.invoiceItemDao = invoiceItemDao;
    }



    @GetMapping("/all")
    public ResponseEntity<List<InvoiceItem>> getAllInvoiceItems() {
        return ResponseEntity.ok(invoiceItemDao.findAll());
    }



    @GetMapping("/{idInvoice}/{idProduct}")
    public ResponseEntity<InvoiceItem> getInvoiceItemById(@PathVariable int idInvoice, @PathVariable int idProduct) {
        return ResponseEntity.ok(invoiceItemDao.findById(idInvoice, idProduct));
    }



    @PostMapping
    public ResponseEntity<InvoiceItem> createInvoiceItem(@Valid @RequestBody InvoiceItem invoiceItem) {
        InvoiceItem createdInvoiceItem = invoiceItemDao.save(invoiceItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoiceItem);
    }



    @PutMapping("/{idInvoice}/{idProduct}")
    public ResponseEntity<InvoiceItem> updateInvoiceItem(@PathVariable int idInvoice, @PathVariable int idProduct, @RequestBody InvoiceItem invoiceItem) {
        InvoiceItem updatedInvoiceItem = invoiceItemDao.update(idInvoice, idProduct, invoiceItem);
        return ResponseEntity.ok(updatedInvoiceItem);
    }



    @DeleteMapping("/{idInvoice}/{idProduct}")
    public ResponseEntity<Void> deleteInvoiceItem(@PathVariable int idInvoice, @PathVariable int idProduct) {
        if (invoiceItemDao.delete(idInvoice, idProduct)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
