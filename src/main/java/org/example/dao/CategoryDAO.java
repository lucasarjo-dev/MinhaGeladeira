package org.example.dao;
import org.example.database.DatabaseConnection;
import org.example.model.Category;

import java.util.ArrayList;
import java.sql.*;


public class CategoryDAO {




public void save(Category category) throws SQLException {



        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO categories(name) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, category.getName());
        ps.executeUpdate();

    }
    public  ArrayList<Category> findAll() throws SQLException{
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT id, name FROM categories";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Category> categories = new ArrayList<>();

        while(rs.next()){
            Category category = new Category();
            String name = rs.getString("name");
            category.setName(name);

            int id = rs.getInt("id");
            category.setId(id);
            categories.add(category);



        }
        return categories;
    }



    }

