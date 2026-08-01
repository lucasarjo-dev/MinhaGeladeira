package org.example.dao;
import org.example.database.DatabaseConnection;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {

    public void save(Product products) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO products(name, unit, category_id) VALUES(?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, products.getName());

        ps.setInt(3, products.getCategoryId());
        ps.executeUpdate();


    }

    public ArrayList<Product> findAll() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT products.id, products.name, stock.quantity, products.category_id FROM products " +
                "INNER JOIN stock ON products.id = stock.product_id";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> products = new ArrayList<>();

        while (rs.next()) {
            Product product = new Product();
            String name = rs.getString("name");
            product.setName(name);

            int id = rs.getInt("id");
            product.setId(id);

            int categoryId = rs.getInt("category_id");
            product.setCategoryId(categoryId);

            double quantity = rs.getDouble("quantity");
            product.setQuantity(quantity);

            products.add(product);






        }
        return products;


    }

}
