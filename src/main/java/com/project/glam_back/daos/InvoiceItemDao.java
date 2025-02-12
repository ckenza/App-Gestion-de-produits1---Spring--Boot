package com.project.glam_back.daos;

import com.project.glam_back.entities.InvoiceItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceItemDao {

    private final JdbcTemplate jdbcTemplate;

    public InvoiceItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<InvoiceItem> invoiceItemRowMapper = (rs, rowNum) -> new InvoiceItem(
            rs.getInt("idInvoice"),
            rs.getInt("idProduct"),
            rs.getInt("quantity")
    );



    public List<InvoiceItem> findAll() {
        String sql = "SELECT * FROM invoice_item";
        return jdbcTemplate.query(sql, invoiceItemRowMapper);
    }



    public InvoiceItem findById(int idInvoice, int idProduct) {
        String sql = "SELECT * FROM invoice_item WHERE idInvoice = ? AND idProduct = ?";
        return jdbcTemplate.query(sql, invoiceItemRowMapper, idInvoice, idProduct)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("L'élément de facture avec idInvoice: " + idInvoice + " et idProduct: " + idProduct + " n'existe pas"));
    }



    public InvoiceItem save(InvoiceItem invoiceItem) {
        String sql = "INSERT INTO invoice_item (idInvoice, idProduct, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, invoiceItem.getIdInvoice(), invoiceItem.getIdProduct(), invoiceItem.getQuantity());
        return invoiceItem;
    }



    public InvoiceItem update(int idInvoice, int idProduct, InvoiceItem invoiceItem) {
        if (!invoiceItemExists(idInvoice, idProduct)) {
            throw new RuntimeException("L'élément de facture avec idInvoice: " + idInvoice + " et idProduct: " + idProduct + " n'existe pas");
        }

        String sql = "UPDATE invoice_item SET quantity = ? WHERE idInvoice = ? AND idProduct = ?";
        int rowsAffected = jdbcTemplate.update(sql, invoiceItem.getQuantity(), idInvoice, idProduct);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour de l'élément de facture avec idInvoice: " + idInvoice + " et idProduct: " + idProduct);
        }

        return this.findById(idInvoice, idProduct);
    }




    public boolean delete(int idInvoice, int idProduct) {
        String sql = "DELETE FROM invoice_item WHERE idInvoice = ? AND idProduct = ?";
        int rowsAffected = jdbcTemplate.update(sql, idInvoice, idProduct);
        return rowsAffected > 0;
    }




    private boolean invoiceItemExists(int idInvoice, int idProduct) {
        String checkSql = "SELECT COUNT(*) FROM invoice_item WHERE idInvoice = ? AND idProduct = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, idInvoice, idProduct);
        return count > 0;
    }
}
