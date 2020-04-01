package com.example.recipecookbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.recipecookbook.databinding.RecipeDetailsActivityBinding;
import com.example.recipecookbook.model.Ingredient;
import com.example.recipecookbook.model.Recipe;
import com.example.recipecookbook.model.Step;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailFragment.PassStepClickedDataToActivity {

    private Recipe recipe;
    private List<Ingredient> ingredientList;
    private List<Step> stepList;

    private RecipeDetailsActivityBinding binding;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RecipeDetailsActivityBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            recipe = intentThatStartedThisActivity.getParcelableExtra(Constants.INTENT_RECIPE);
            ingredientList = recipe.getIngredients();
            stepList = recipe.getSteps();
            Log.d("MAVERICK", "test detail-activity-data recipe name: " +recipe.getName());
        }

        setupRecipeDetailFragment();
    }

    private void setupRecipeDetailFragment() {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment(ingredientList, stepList);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.recipe_detail_container, recipeDetailFragment)
                .commit();
    }

    @Override
    public void passStepClickedDataToActivity(int position) {
        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_container, stepDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}
