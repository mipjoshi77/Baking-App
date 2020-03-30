package com.example.recipecookbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipecookbook.model.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    private Recipe recipe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            recipe = intentThatStartedThisActivity.getParcelableExtra(Constants.INTENT_RECIPE);
            Log.d("MAVERICK", "test detail-activity-data recipe name: " +recipe.getName());
        }

    }
}
