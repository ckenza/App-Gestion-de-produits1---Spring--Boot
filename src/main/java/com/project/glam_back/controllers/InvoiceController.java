package com.project.glam_back.controllers;

import com.project.glam_back.daos.InvoiceDao;
import com.project.glam_back.entities.Invoice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {


    private final InvoiceDao invoiceDao;

    public InvoiceController(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }



    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceDao.findAll());
    }



    @GetMapping("/{idInvoice}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable int idInvoice) {
        return ResponseEntity.ok(invoiceDao.findById(idInvoice));
    }



    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceDao.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice);
    }



    @PutMapping("/{idInvoice}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable int idInvoice, @RequestBody Invoice invoice) {
        Invoice updatedInvoice = invoiceDao.update(idInvoice, invoice);
        return ResponseEntity.ok(updatedInvoice);
    }



    @DeleteMapping("/{idInvoice}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable int idInvoice) {
        if (invoiceDao.delete(idInvoice)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
