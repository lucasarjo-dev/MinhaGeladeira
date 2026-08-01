package org.example.manager;

import org.example.dao.RecipeIngredientDAO;
import org.example.dao.StockDAO;
import org.example.model.RecipeIngredient;
import org.example.model.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeManager {
    private RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAO();
    private StockDAO stockDAO = new StockDAO();

    public boolean canPrepareRecipe(int recipeId) throws SQLException {

        ArrayList<RecipeIngredient> ingredients =
                recipeIngredientDAO.findByRecipeId(recipeId);

        for (RecipeIngredient ingredient : ingredients) {

            Stock stock =
                    stockDAO.findByProductId(ingredient.getProductId());

            if (stock == null) {
                return false;
            }

            if (stock.getQuantity() < ingredient.getQuantity()) {
                return false;
            }

        }

        return true;
    }

    public void prepareRecipe(int recipeId) throws SQLException, SQLException {

        if (!canPrepareRecipe(recipeId)) {

            System.out.println("Estoque insuficiente.");

            return;
        }

        ArrayList<RecipeIngredient> ingredients =
                recipeIngredientDAO.findByRecipeId(recipeId);

        for (RecipeIngredient ingredient : ingredients) {

            Stock stock =
                    stockDAO.findByProductId(ingredient.getProductId());

            double newQuantity =
                    stock.getQuantity() - ingredient.getQuantity();

            stockDAO.updateQuantity(
                    ingredient.getProductId(),
                    newQuantity
            );
        }

        System.out.println("Receita preparada com sucesso!");
    }
}
