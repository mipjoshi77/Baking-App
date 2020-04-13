package com.example.recipecookbook;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    private static String ingredientList;
    private static String recipeName;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);

//        // Create an intent to launch RecipeActivity when clicked
//        Intent intent = new Intent(context, RecipeActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//
//        // Widgets allow click handlers to only launch pending intents
//        views.setOnClickPendingIntent(R.id.widget_container_layout, pendingIntent);

        views.setTextViewText(R.id.widget_recipe_name, recipeName);
        views.setTextViewText(R.id.widget_recipe_ingredients, ingredientList);

        if (ingredientList.isEmpty() && recipeName.isEmpty()) {
            views.setViewVisibility(R.id.widget_default_text, View.VISIBLE);
        }
        else {
            views.setViewVisibility(R.id.widget_default_text, View.GONE);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecipeWidgetProvider.class));

        final String action = intent.getAction();

        if (action.equals("android.appwidget.action.APPWIDGET_UPDATE")) {
            ingredientList = intent.getStringExtra(Constants.INGREDIENTS_LIST_WIDGET);
            recipeName = intent.getStringExtra(Constants.RECIPE_NAME_WIDGET);
            onUpdate(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);
        }
    }
}

