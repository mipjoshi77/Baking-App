package com.example.recipecookbook;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;


public class RecipeWidgetService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public RecipeWidgetService() {
        super("RecipeWidgetService");
    }

    public static void startBakingService(Context context, String ingredientsListForWidget, String recipeName) {
        Intent intent = new Intent(context, RecipeWidgetService.class);
        intent.putExtra(Constants.INGREDIENTS_LIST_WIDGET, ingredientsListForWidget);
        intent.putExtra(Constants.RECIPE_NAME_WIDGET, recipeName);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String ingredientsListForWidget = intent.getStringExtra(Constants.INGREDIENTS_LIST_WIDGET);
            String recipeNameForWidget = intent.getStringExtra(Constants.RECIPE_NAME_WIDGET);
            handleActionUpdateRecipeWidgets(ingredientsListForWidget, recipeNameForWidget);
        }
    }

    private void handleActionUpdateRecipeWidgets(String ingredientsListForWidget, String recipeNameForWidget) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra(Constants.INGREDIENTS_LIST_WIDGET,ingredientsListForWidget);
        intent.putExtra(Constants.RECIPE_NAME_WIDGET, recipeNameForWidget);
        sendBroadcast(intent);
    }
}
