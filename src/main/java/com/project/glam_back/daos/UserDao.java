package com.project.glam_back.daos;

import com.project.glam_back.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, _) -> new User(
            rs.getInt("idUser"),
            rs.getString("email")
    );



    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, userRowMapper);
    }



    public User findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        return jdbcTemplate.query(sql, userRowMapper, email)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User avec l'email : " + email + " n'existe pas"));
    }


    public User save(User user) {
        String sql = "INSERT INTO user (email) VALUES (?)";
        jdbcTemplate.update(sql, user.getEmail());

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        Integer id = jdbcTemplate.queryForObject(sqlGetId, Integer.class);

        user.setIdUser(id);
        return user;
    }


    public User update(String email, User user) {
        if (!userExists(email)) {
            throw new RuntimeException("user avec l'email : " + email + " n'existe pas");
        }

        String sql = "UPDATE user SET email = ?, WHERE email = ?";
        int rowsAffected = jdbcTemplate.update(sql, user.getEmail(), email);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour du produit avec l'ID : " + email);
        }

        return this.findByEmail(email);
    }

    // méthode utilitaire à mettre en bas du fichier
    private boolean userExists(String email) {
        String checkSql = "SELECT COUNT(*) FROM user WHERE email = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, email);
        return count > 0;
    }


}
