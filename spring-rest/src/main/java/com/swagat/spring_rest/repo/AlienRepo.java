package com.swagat.spring_rest.repo;

import com.swagat.spring_rest.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlienRepo {
    private JdbcTemplate template;

    public  JdbcTemplate getTemplate(){
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public void save(Alien alien){
        String sql = "insert into alien (id,name,tech) values (?,?,?)";
        int rows = template.update(sql,alien.getId(),alien.getName(),alien.getTech());
        System.out.println("No of rows afftected : " + rows);
    }

    public List<Alien> findALl(){
        String sql = "select * from alien";
        RowMapper<Alien> mapper = new RowMapper<Alien>() {
            @Override
            public Alien mapRow(ResultSet rs, int rowNum) throws SQLException {
                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setTech(rs.getString(3));
                return a;
            }
        };
        List<Alien> aliens = template.query(sql,mapper);
        return  aliens;
    }

    public Optional<Alien> findById(int id){
        String sql = "select * from alien where id = ?";

        // 1. You already defined a RowMapper in your findALl method.
        //    We can reuse that logic here.
        RowMapper<Alien> mapper = (rs, rowNum) -> {
            Alien a = new Alien();
            a.setId(rs.getInt("id")); // Using column names is safer than indexes
            a.setName(rs.getString("name"));
            a.setTech(rs.getString("tech"));
            return a;
        };

        try {
            // 2. Use queryForObject to get just one object
            //    Pass the sql, the mapper, and the ID parameter
            return Optional.ofNullable(template.queryForObject(sql, mapper, id));
        }
        catch (Exception e){
            // 3. queryForObject throws an exception if no row is found.
            //    It's good practice to catch this and return null.
            return Optional.empty();
        }
    }
}
