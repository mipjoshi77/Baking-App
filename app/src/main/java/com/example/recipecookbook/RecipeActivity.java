package com.example.recipecookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.recipecookbook.adapters.RecipeListAdapter;
import com.example.recipecookbook.databinding.RecipeActivityBinding;
import com.example.recipecookbook.model.Recipe;
import com.example.recipecookbook.viewmodels.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;


public class RecipeActivity extends AppCompatActivity implements RecipeListAdapter.RecipeItemOnClickHandler {

    private RecipeViewModel recipeViewModel;

    private RecipeActivityBinding binding;
    private View view;

    private RecyclerView recipeRecyclerView;
    private RecipeListAdapter recipeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RecipeActivityBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        recipeRecyclerView = binding.recipeListRv;

        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        getRecipeList();
    }


    private void getRecipeList() {
        recipeViewModel.getRecipeList().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                Log.d("MAVERICK", "test activity-data recipe name: " +recipes.get(0).getName());
                recipeListAdapter = new RecipeListAdapter(recipes, getApplicationContext(), RecipeActivity.this);
                recipeRecyclerView.setAdapter(recipeListAdapter);
            }
        });
    }

    @Override
    public void onClick(int position, Recipe recipe) {
        Intent startRecipeDetailsActivity = new Intent(this, RecipeDetailsActivity.class);
        startRecipeDetailsActivity.putExtra(Constants.INTENT_RECIPE, recipe);
        startActivity(startRecipeDetailsActivity);
    }
}
