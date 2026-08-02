package org.example;
import org.example.dao.*;
import org.example.manager.RecipeManager;
import org.example.model.Category;
import org.example.model.Product;
import org.example.model.Recipe;
import org.example.model.Stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {


        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\n===== MinhaGeladeira =====");
            System.out.println("1 - Cadastrar categoria");
            System.out.println("2 - Listar categorias");
            System.out.println("3 - Cadastrar produto");
            System.out.println("4 - Listar produtos");
            System.out.println("5 - Ver receitas disponíveis");
            System.out.println("6 - Preparar receita ");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1:
                    System.out.println("Cadastrar categoria");

                    Category category = new Category();
                    CategoryDAO categoryDAO = new CategoryDAO();

                    System.out.println("Digite a categoria que deseja cadastrar: ");
                    category.setName(sc.nextLine());
                    System.out.println(category.getName());
                    categoryDAO.save(category);
                    System.out.println(categoryDAO.findAll());
                    break;

                case 2:
                    System.out.println("===== CATEGORIAS CADASTRADAS =====");

                    CategoryDAO categoryDAO1 = new CategoryDAO();

                    ArrayList<Category> categories = categoryDAO1.findAll();

                    for (Category categoryList : categories) {

                        System.out.println("------------------------------");
                        System.out.println("ID: " + categoryList.getId());
                        System.out.println("Nome: " + categoryList.getName());

                    }

                    break;

                case 3:
                    System.out.println("Cadastrar produto");
                    CategoryDAO categoryDAOProduct  = new CategoryDAO();

                    ArrayList<Category> categories1 = categoryDAOProduct .findAll();

                    for(Category i : categories1){
                        System.out.println(
                                i.getId() + " - " + i.getName()
                        );
                    }

                    System.out.println("Digite o numero da categoria que deseja adicionar um produto:");
                    int choice = sc.nextInt();
                    sc.nextLine();

                    Category selectedCategory = null;

                    for(Category category1 : categories1){

                        if(category1.getId() == choice){
                            selectedCategory = category1;
                            break;
                        }

                    }

                    if(selectedCategory == null){
                        System.out.println("Categoria inválida.");
                        break;
                    }

                    ProductDAO productsDAO = new ProductDAO();
                    Product product = new Product();

                    System.out.println("Digite o nome do produto:");
                    String productName = sc.nextLine();
                    product.setName(productName);

                    System.out.println("Digite a quantidade de unidades: (Ex: Litros, Caixas, Gramas...");
                    String productUnit = sc.nextLine();
                    product.setUnit(productUnit);

                    product.setCategoryId(selectedCategory.getId());
                    productsDAO.save(product);
                    System.out.println("Produto cadastrado com sucesso!");


                    break;

                case 4:
                    System.out.println("Listar produtos");
                    System.out.println("===== PRODUTOS CADASTRADOS =====");

                    ProductDAO productsDAO1 = new ProductDAO();
                    StockDAO stockDAO = new StockDAO();

                    ArrayList<Product> products = productsDAO1.findAll();




                    for(Product product1 : products){

                        Stock stock = stockDAO.findByProductId(product1.getId());



                        System.out.println("------------------------------");
                        System.out.println("ID: " + product1.getId());
                        System.out.println("Nome: " + product1.getName());
                        System.out.println("Unidade: " + stock.getQuantity());
                        System.out.println("Categoria ID: " + product1.getCategoryId());

                    }

                case 5:

                    System.out.println("===== RECEITAS DISPONÍVEIS =====");

                    RecipeDAO recipesDAO = new RecipeDAO();

                    ArrayList<Recipe> recipesArrayList = recipesDAO.findAll();

                    for (Recipe recipe : recipesArrayList) {

                        System.out.println(recipe.getId() + " - " + recipe.getName());

                    }

                    System.out.println();
                    System.out.print("Digite o ID da receita que deseja visualizar: ");

                    int recipeChoice = sc.nextInt();
                    sc.nextLine();

                    boolean found = false;

                    for (Recipe recipe : recipesArrayList) {

                        if (recipe.getId() == recipeChoice) {

                            System.out.println("\n==============================");
                            System.out.println("Nome: " + recipe.getName());
                            System.out.println("Descrição: " + recipe.getDescription());
                            System.out.println("Tempo de preparo: " + recipe.getPreparationTime());
                            System.out.println("Dificuldade: " + recipe.getDifficulty());
                            System.out.println("==============================");

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Receita não encontrada.");
                    }

                    break;

                    case 6:

                    RecipeManager recipeManager = new RecipeManager();

                    RecipeDAO recipesDAO1 = new RecipeDAO();

                    ArrayList<Recipe> availableRecipes = recipesDAO1.findAll();

                    System.out.println("===== PREPARAR RECEITA =====");

                    for (Recipe recipe : availableRecipes) {

                        System.out.println(recipe.getId() + " - " + recipe.getName());

                    }

                    System.out.println();
                    System.out.print("Digite o ID da receita que deseja preparar: ");

                    int recipeId = sc.nextInt();
                    sc.nextLine();

                    recipeManager.prepareRecipe(recipeId);

                    break;
                case 9:
                    RecipeDAO addRecipe = new RecipeDAO();
                    addRecipe.populateRecipes();
                    break;

                case 10:
                    RecipeIngredientDAO addRecipeIngredients = new RecipeIngredientDAO();
                    addRecipeIngredients.populateRecipeIngredients();
                    break;
                case 11:
                    StockDAO addItemsToStock = new StockDAO();
                    addItemsToStock.populateStock();
                    break;
                case 0:
                    running = false;
                    System.out.println("Encerrando MyFridge...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }




}



