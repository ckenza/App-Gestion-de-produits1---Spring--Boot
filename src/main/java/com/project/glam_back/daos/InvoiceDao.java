package com.project.glam_back.daos;

import com.project.glam_back.entities.Invoice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceDao {

    private final JdbcTemplate jdbcTemplate;

    public InvoiceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Invoice> invoiceRowMapper = (rs, rowNum) -> new Invoice(
            rs.getInt("idInvoice"),
            rs.getDate("date"),
            rs.getDouble("total")
    );



    public List<Invoice> findAll() {
        String sql = "SELECT * FROM invoice";
        return jdbcTemplate.query(sql, invoiceRowMapper);
    }



    public Invoice findById(int idInvoice) {
        String sql = "SELECT * FROM invoice WHERE idInvoice = ?";
        return jdbcTemplate.query(sql, invoiceRowMapper, idInvoice)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Facture avec l'ID : " + idInvoice + " n'existe pas"));
    }



    public Invoice save(Invoice invoice) {
        String sql = "INSERT INTO invoice (date, total) VALUES (?, ?)";
        jdbcTemplate.update(sql, invoice.getDate(), invoice.getTotal());

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        int id = jdbcTemplate.queryForObject(sqlGetId, Integer.class);

        invoice.setIdInvoice(id);
        return invoice;
    }



    public Invoice update(int id, Invoice invoice) {
        if (!invoiceExists(id)) {
            throw new RuntimeException("Facture avec l'ID : " + id + " n'existe pas");
        }

        String sql = "UPDATE invoice SET total = ? WHERE idInvoice = ?";
        int rowsAffected = jdbcTemplate.update(sql, invoice.getTotal(), id);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour de la facture avec l'ID : " + id);
        }

        return this.findById(id);
    }



    public boolean delete(int id) {
        String sql = "DELETE FROM invoice WHERE idInvoice = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }



    private boolean invoiceExists(int id) {
        String checkSql = "SELECT COUNT(*) FROM invoice WHERE idInvoice = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
}
