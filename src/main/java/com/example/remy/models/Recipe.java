package com.example.remy.models;

import com.example.remy.dao.ObjectDao;
import com.example.remy.dao.ParamDao;
import com.example.remy.entities.Object;
import com.example.remy.entities.Param;
import org.jetbrains.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private Long recipeId;
    private String recipeName;
    private String[] steps;
    private String website;

    private static ObjectDao objectDao = new ObjectDao();
    private static ParamDao paramDao = new ParamDao();
    private static List<Object> objects = objectDao.getAll();
    private static List<Param> params = paramDao.getAll();

    public Recipe(Long id, String name, String steps, String web)
    {
        recipeId = id;
        recipeName = name;
        this.steps = steps.split(";");
        website = web;
    }


    public static List<Recipe> getAllPecipes()
    {
        List<Recipe> recipes = new ArrayList<>();
        for(Object obj : objects)
        {
            if(obj.getObject_type_id() == 2)
            {
                Recipe recipe = new Recipe(obj.getObject_id(), obj.getName(), "Пока не знаю, как готовить :(", null);
                for(Param param : params)
                {
                    if(param.getAttr_id()==2 && param.getObject_id().equals(recipe.recipeId))
                        recipe.setSteps(param.getText_value());

                    if(param.getAttr_id()==3 && param.getObject_id().equals(recipe.recipeId))
                        recipe.setWebsite(param.getText_value());
                }
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String[] getSteps() {
        return steps;
    }

    public String getWebsite() {
        return website;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setSteps(String steps) {
        this.steps = steps.split(";");
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return this.recipeName;
    }
}
