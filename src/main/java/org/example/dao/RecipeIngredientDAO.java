package org.example.dao;
import org.example.database.DatabaseConnection;
import org.example.model.RecipeIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeIngredientDAO {
    public void save(RecipeIngredient ingredient) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        String query = "INSERT INTO recipe_ingredient(recipe_id, product_id, quantity) VALUES (?,?,?)";

                ;

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, ingredient.getRecipeId());
        ps.setInt(2, ingredient.getProductId());
        ps.setDouble(3, ingredient.getQuantity());

        ps.executeUpdate();
    }

    public ArrayList<RecipeIngredient> findAll() throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT id, recipe_id, product_id, quantity FROM recipe_ingredient";

        PreparedStatement ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        ArrayList<RecipeIngredient> ingredients = new ArrayList<>();

        while (rs.next()) {

            RecipeIngredient ingredient = new RecipeIngredient();

            ingredient.setId(rs.getInt("id"));
            ingredient.setRecipeId(rs.getInt("recipe_id"));
            ingredient.setProductId(rs.getInt("product_id"));
            ingredient.setQuantity(rs.getDouble("quantity"));

            ingredients.add(ingredient);
        }

        return ingredients;
    }

    public ArrayList<RecipeIngredient> findByRecipeId(int recipeId) throws SQLException {

        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT id, recipe_id, product_id, quantity FROM recipe_ingredient WHERE recipe_id = ?";


        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, recipeId);

        ResultSet rs = ps.executeQuery();

        ArrayList<RecipeIngredient> ingredients = new ArrayList<>();

        while (rs.next()) {

            RecipeIngredient ingredient = new RecipeIngredient();

            ingredient.setId(rs.getInt("id"));
            ingredient.setRecipeId(rs.getInt("recipe_id"));
            ingredient.setProductId(rs.getInt("product_id"));
            ingredient.setQuantity(rs.getDouble("quantity"));

            ingredients.add(ingredient);
        }

        return ingredients;
    }
    public void populateRecipeIngredients() throws SQLException {

        RecipeIngredient ingredient;

        // Milho
        ingredient = new RecipeIngredient();
        ingredient.setRecipeId(1);
        ingredient.setProductId(2);
        ingredient.setQuantity(1);
        save(ingredient);

        // Leite
        ingredient = new RecipeIngredient();
        ingredient.setRecipeId(1);
        ingredient.setProductId(1);
        ingredient.setQuantity(240);
        save(ingredient);

        // Açúcar
        ingredient = new RecipeIngredient();
        ingredient.setRecipeId(1);
        ingredient.setProductId(3);
        ingredient.setQuantity(240);
        save(ingredient);

        // Flocão de milho
        ingredient = new RecipeIngredient();
        ingredient.setRecipeId(1);
        ingredient.setProductId(4);
        ingredient.setQuantity(240);
        save(ingredient);

        // Óleo
        ingredient = new RecipeIngredient();
        ingredient.setRecipeId(1);
        ingredient.setProductId(5);
        ingredient.setQuantity(120);
        save(ingredient);

        // Ovos
        ingredient = new RecipeIngredient();
        ingredient.setRecipeId(1);
        ingredient.setProductId(6);
        ingredient.setQuantity(3);
        save(ingredient);

        // Fermento
        ingredient = new RecipeIngredient();
        ingredient.setRecipeId(1);
        ingredient.setProductId(7);
        ingredient.setQuantity(15);
        save(ingredient);
    }

}
