package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAO {
    public void save(Stock stock) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO stock(product_id, quantity) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, stock.getProductId());
        ps.setDouble(2, stock.getQuantity());
        ps.executeUpdate();


    }

    public ArrayList<Stock> findAll() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT id, product_id, quantity FROM stock";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Stock> stockArrayList = new ArrayList<>();

        while (rs.next()) {
            Stock stock = new Stock();
            int productId = rs.getInt("product_id");
            stock.setProductId(productId);

            double quantity = rs.getInt("quantity");
            stock.setQuantity(quantity);
            stockArrayList.add(stock);
        }
        return stockArrayList;

    }

    public Stock findByProductId(int productId) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT id, product_id, quantity FROM stock WHERE product_id = ?";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, productId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Stock stock = new Stock();

            stock.setId(rs.getInt("id"));
            stock.setProductId(rs.getInt("product_id"));
            stock.setQuantity(rs.getDouble("quantity"));

            return stock;
        }
    return null;
}
    public void updateQuantity(int productId, double newQuantity) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        String query = "UPDATE stock SET quantity = ? WHERE product_id = ?";


        PreparedStatement ps = conn.prepareStatement(query);

        ps.setDouble(1, newQuantity);
        ps.setInt(2, productId);

        ps.executeUpdate();
    }
    public void populateStock () throws SQLException{
        Stock item;

        item = new Stock();
        item.setProductId(2);
        item.setQuantity(200);
        save(item);

        item = new Stock();
        item.setProductId(1);
        item.setQuantity(200);
        save(item);

        item = new Stock();
        item.setProductId(3);
        item.setQuantity(100);
        save(item);

        item = new Stock();
        item.setProductId(4);
        item.setQuantity(500);
        save(item);

        item = new Stock();
        item.setProductId(5);
        item.setQuantity(500);
        save(item);

        item = new Stock();
        item.setProductId(6);
        item.setQuantity(10);
        save(item);

        item = new Stock();
        item.setProductId(7);
        item.setQuantity(100);
        save(item);




    }
}

