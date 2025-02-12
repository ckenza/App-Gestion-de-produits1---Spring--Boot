package com.project.glam_back.daos;

import com.project.glam_back.entities.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
            rs.getInt("idProduct"),
            rs.getString("titleProduct"),
            rs.getString("imageUrl"),
            rs.getDouble("price"),
            rs.getInt("stock")
    );



    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }



    public Product findById(int idProduct) {
        String sql = "SELECT * FROM product WHERE idProduct = ?";
        return jdbcTemplate.query(sql, productRowMapper, idProduct)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produit avec l'ID : " + idProduct + " n'existe pas"));
    }



    public Product save(Product product) {
        String sql = "INSERT INTO product (titleProduct, imageUrl, price, stock) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getTitleProduct(), product.getImageUrl(), product.getPrice(), product.getStock());

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        int id = jdbcTemplate.queryForObject(sqlGetId, Integer.class);

        product.setIdProduct(id);
        return product;
    }



    public Product update(int id, Product product) {
        if (!productExists(id)) {
            throw new RuntimeException("Produit avec l'ID : " + id + " n'existe pas");
        }

        String sql = "UPDATE product SET titleProduct = ?, imageUrl = ?, price = ?, stock = ? WHERE idProduct = ?";
        int rowsAffected = jdbcTemplate.update(sql, product.getTitleProduct(), product.getImageUrl(), product.getPrice(), product.getStock(), id);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour du produit avec l'ID : " + id);
        }

        return this.findById(id);
    }



    public boolean delete(int id) {
        String sql = "DELETE FROM product WHERE idProduct = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }



    private boolean productExists(int id) {
        String checkSql = "SELECT COUNT(*) FROM product WHERE idProduct = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }
}
