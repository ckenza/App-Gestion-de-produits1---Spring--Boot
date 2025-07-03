package com.project.glam_back.daos;

import com.project.glam_back.entities.Invoice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class InvoiceDao {

    private final JdbcTemplate jdbcTemplate;

    public InvoiceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Invoice> invoiceRowMapper = (rs, rowNum) -> new Invoice(
            rs.getInt("id"),
            rs.getInt("id_user"),
            rs.getDate("date"),
            rs.getBigDecimal("total")
    );


    public List<Invoice> findAll() {
        String sql = "SELECT * FROM invoice";
        return jdbcTemplate.query(sql, invoiceRowMapper);
    }


    public Invoice findById(int idInvoice) {
        String sql = "SELECT * FROM invoice WHERE id = ?";
        return jdbcTemplate.query(sql, invoiceRowMapper, idInvoice)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Facture avec l'ID : " + idInvoice + " n'existe pas"));
    }


    public int save(Invoice invoice) {
        String sql = "INSERT INTO invoice (id_user, date, total) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, invoice.getIdUser());
                    ps.setDate(2, invoice.getDate());
                    ps.setBigDecimal(3, invoice.getTotal());
                    return ps;
                }, keyHolder);

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }


    public Invoice update(int id, Invoice invoice) {
        if (!invoiceExists(id)) {
            throw new RuntimeException("Facture avec l'ID : " + id + " n'existe pas");
        }

        String sql = "UPDATE invoice SET total = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, invoice.getTotal(), id);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour de la facture avec l'ID : " + id);
        }

        return this.findById(id);
    }


    public boolean delete(int id) {
        String sql = "DELETE FROM invoice WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }


    private boolean invoiceExists(int id) {
        String checkSql = "SELECT COUNT(*) FROM invoice WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
}
