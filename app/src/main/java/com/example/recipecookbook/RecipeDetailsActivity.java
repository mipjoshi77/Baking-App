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

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailFragment.PassStepClickedDataToActivity {

    private ArrayList<Recipe> recipeArrayList;
    private int recipeCurrentPosition;

    private Recipe recipe;
    private List<Ingredient> ingredientList;
    private List<Step> stepList;

    private Bundle recipeDataBundle;

    private RecipeDetailsActivityBinding binding;
    private View view;

    private boolean mTwoPane;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RecipeDetailsActivityBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);



//        Intent intentThatStartedThisActivity = getIntent();
        recipeDataBundle = getIntent().getExtras();

        if (recipeDataBundle != null) {
//            recipeArrayList = intentThatStartedThisActivity.getParcelableExtra(Constants.INTENT_RECIPES);
//            recipeCurrentPosition = intentThatStartedThisActivity.getIntExtra(Constants.RECIPE_POSITION, 0);
            recipeArrayList = recipeDataBundle.getParcelableArrayList(Constants.INTENT_RECIPES);
            recipeCurrentPosition = recipeDataBundle.getInt(Constants.RECIPE_POSITION);
            recipe = recipeArrayList.get(recipeCurrentPosition);
            Log.d("MAVERICK", "onCreate: current position: " +recipeCurrentPosition);
            Log.d("MAVERICK", "test detail-activity-data recipe name: " +recipe.getName());

            ingredientList = recipe.getIngredients();
            stepList = recipe.getSteps();
        }

//        if (binding.stepDetailFragmentLayout != null) {
//            mTwoPane = true;
//            StepDetailFragment stepDetailFragment = new StepDetailFragment(stepList.get(0));
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction()
//                    .add(R.id.step_detail_container, stepDetailFragment)
//                    .commit();
//        }
//        else {
//            mTwoPane = false;
//        }

        setupRecipeDetailFragment();
    }

    private void setupRecipeDetailFragment() {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setArguments(recipeDataBundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.recipe_detail_container, recipeDetailFragment)
                .commit();
    }

    @Override
    public void passStepClickedDataToActivity(int position) {
        StepDetailFragment stepDetailFragment = new StepDetailFragment(stepList.get(position));
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_container, stepDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}
