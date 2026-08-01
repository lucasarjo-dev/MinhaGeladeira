package org.example.dao;

import org.example.database.DatabaseConnection;
import org.example.model.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDAO {
    public void save(Recipe recipes) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO recipe(name, description, preparation_time, difficulty) VALUES(?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, recipes.getName());
        ps.setString(2, recipes.getDescription());
        ps.setString(3, recipes.getPreparationTime());
        ps.setString(4, recipes.getDifficulty());
        ps.executeUpdate();


    }

    public ArrayList<Recipe> findAll() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT id, name, description, preparation_time, difficulty FROM recipe";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Recipe> recipesArrayList = new ArrayList<>();

        while (rs.next()) {
            Recipe recipe = new Recipe();
            String name = rs.getString("name");
            recipe.setName(name);

            String description = rs.getString("description");
            recipe.setDescription(description);

            String preparationTime = rs.getString("preparation_time");
            recipe.setPreparationTime(preparationTime);

            String difficulty = rs.getString("difficulty");
            recipe.setDifficulty(difficulty);

            int id = rs.getInt("id");
            recipe.setId(id);

            recipesArrayList.add(recipe);



        }
        return recipesArrayList;


    }
    public void populateRecipes() throws SQLException {

        Recipe recipe = new Recipe();
        RecipeDAO recipesDAO = new RecipeDAO();

        recipe.setName("Bolo de milho de lata");
        recipe.setDescription("Escorra o milho e use a própria lata para as medidas.\n" +
                "Unte e enfarinhe uma forma de bolo com furo.\n" +
                "Preaqueça o forno.\n" +
                "Coloque no liquidificador o milho (já escorrido), o leite, açúcar, flocão de milho, óleo, ovos e bata bem até que o milho fique bem moído.\n" +
                "Se quiser, pode acrescentar duas colheres de sopa de coco ralado.\n" +
                "Acrescente o fermento em pó e pulse o liquidificador 3 vezes.\n" +
                "Despeje essa massa na forma e leve ao forno médio.\n" +
                "Deixe assar por, aproximadamente, 40 minutos.");
        recipe.setPreparationTime("40 minutos");
        recipe.setDifficulty("Fácil");

        recipesDAO.save(recipe);

    }
}
