package com.example.recipecookbook.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipecookbook.model.Recipe;
import com.example.recipecookbook.repository.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> recipeList;


    public RecipeViewModel() {
        recipeRepository = new RecipeRepository();
        this.recipeList = recipeRepository.getRecipeList();
    }

    public LiveData<List<Recipe>> getRecipeList() {
        return recipeList;
    }
}
