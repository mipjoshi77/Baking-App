package com.example.recipecookbook.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipecookbook.idlingresource.IdlingResourceUtils;
import com.example.recipecookbook.model.Recipe;
import com.example.recipecookbook.utils.RecipeJsonApi;
import com.example.recipecookbook.utils.RecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private RecipeJsonApi recipeJsonApi;

    final MutableLiveData<List<Recipe>> recipeList;

    public RecipeRepository() {
        recipeJsonApi = RecipeResponse.getRetrofitInstance().create(RecipeJsonApi.class);
        recipeList = new MutableLiveData<>();
    }


    public LiveData<List<Recipe>> getRecipeList() {

        IdlingResourceUtils.setIdlingResourceState(false);

        recipeJsonApi.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                recipeList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("MAVERICK", "Failed: " +t.toString());
            }
        });
        return recipeList;
    }
}
