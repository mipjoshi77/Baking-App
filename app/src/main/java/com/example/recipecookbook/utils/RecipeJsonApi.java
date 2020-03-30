package com.example.recipecookbook.utils;

import com.example.recipecookbook.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeJsonApi {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
