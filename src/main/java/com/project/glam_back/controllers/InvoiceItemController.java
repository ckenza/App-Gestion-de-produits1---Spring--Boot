package com.project.glam_back.controllers;

import com.project.glam_back.daos.InvoiceItemDao;
import com.project.glam_back.entities.InvoiceItem;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice_item")
public class InvoiceItemController {

    private final InvoiceItemDao invoiceItemDao;

    public InvoiceItemController(InvoiceItemDao invoiceItemDao) {
        this.invoiceItemDao = invoiceItemDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<InvoiceItem>> getAllInvoiceItems() {
        return ResponseEntity.ok(invoiceItemDao.findAll());
    }


    @GetMapping("/{id_invoice}/{id_product}")
    public ResponseEntity<InvoiceItem> getInvoiceItemById(@PathVariable int idInvoice, @PathVariable int idProduct) {
        return ResponseEntity.ok(invoiceItemDao.findById(idInvoice, idProduct));
    }


   @PostMapping("/{idInvoice}")
    public ResponseEntity<String> createInvoiceItem(@PathVariable int idInvoice, @RequestBody List<InvoiceItem> items) {
        invoiceItemDao.save(idInvoice, items);
        return ResponseEntity.status(HttpStatus.CREATED).body("Détail de la facture enregistrés");
    }


    @PutMapping("/{id_invoice}/{id_product}")
    public ResponseEntity<InvoiceItem> updateInvoiceItem(@PathVariable int idInvoice, @PathVariable int idProduct, @RequestBody InvoiceItem invoiceItem) {
        InvoiceItem updatedInvoiceItem = invoiceItemDao.update(idInvoice, idProduct, invoiceItem);
        return ResponseEntity.ok(updatedInvoiceItem);
    }


    @DeleteMapping("/{id_invoice}/{id_product}")
    public ResponseEntity<Void> deleteInvoiceItem(@PathVariable int idInvoice, @PathVariable int idProduct) {
        if (invoiceItemDao.delete(idInvoice, idProduct)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
