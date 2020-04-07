package com.example.recipecookbook;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.recipecookbook.databinding.RecipeDetailsActivityBinding;
import com.example.recipecookbook.model.Ingredient;
import com.example.recipecookbook.model.Recipe;
import com.example.recipecookbook.model.Step;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailFragment.PassStepClickedDataToActivity, StepDetailFragment.OnStepChangeListener {

    private ArrayList<Recipe> recipeArrayList;
    private int recipeCurrentPosition;

    private Recipe recipe;
    private List<Ingredient> ingredientList;
    private List<Step> stepList;

    private Bundle recipeDataBundle;

    private RecipeDetailsActivityBinding binding;
    private View view;
    private MaterialToolbar toolbar;

    private boolean mTwoPane;

    static String RECIPE_DETAIL="RECIPE_DETAIL";
    static String RECIPE_STEP_DETAIL="RECIPE_STEP_DETAIL";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RecipeDetailsActivityBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        toolbar = findViewById(R.id.overhead_toolbar);
        setSupportActionBar(toolbar);


        recipeDataBundle = getIntent().getExtras();

        recipeArrayList = new ArrayList<>();
        if (recipeDataBundle != null) {
            recipeArrayList = recipeDataBundle.getParcelableArrayList(Constants.INTENT_RECIPES);
            recipeCurrentPosition = recipeDataBundle.getInt(Constants.RECIPE_POSITION);
            recipe = recipeArrayList.get(recipeCurrentPosition);
            Log.d("MAVERICK", "onCreate: current position: " +recipeCurrentPosition);
            Log.d("MAVERICK", "test detail-activity-data recipe name: " +recipe.getName());

            ingredientList = recipe.getIngredients();
            stepList = recipe.getSteps();
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(recipe.getName());

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


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

        if (savedInstanceState == null) setupRecipeDetailFragment();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (binding.stepDetailFragmentLayout == null) {
                    if (fragmentManager.getBackStackEntryCount() > 1) {
                        fragmentManager.popBackStack(RECIPE_DETAIL, 0);
                    }
                    else if (fragmentManager.getBackStackEntryCount() > 0) {
                        finish();
                    }
                }
                else {
                    finish();
                }
            }
        });
    }

    private void setupRecipeDetailFragment() {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setArguments(recipeDataBundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_container, recipeDetailFragment)
                .addToBackStack(RECIPE_DETAIL)
                .commit();

        Log.d("EUREKA", "setupRecipeDetailFragment: fragment count: " +getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void passStepClickedDataToActivity(int position) {
        recipeDataBundle.putInt(Constants.STEP_POSITION, position);
        StepDetailFragment stepDetailFragment = new StepDetailFragment(stepList.get(position), position);
        stepDetailFragment.setArguments(recipeDataBundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_detail_container, stepDetailFragment)
                .addToBackStack(RECIPE_STEP_DETAIL)
                .commit();

        Log.d("EUREKA", "passStepClickedDataToActivity: fragment count: " +getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onStepChangeClicked(int position) {
        passStepClickedDataToActivity(position);
    }

}
