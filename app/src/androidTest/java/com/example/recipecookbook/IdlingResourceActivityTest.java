package com.example.recipecookbook;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.recipecookbook.idlingresource.IdlingResourceUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class IdlingResourceActivityTest {

    @Rule
    public final ActivityTestRule<RecipeActivity> recipeActivityActivityTestRule = new ActivityTestRule<>(RecipeActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void setUp() {
        //Initialize the mIdlingResource instance
        idlingResource = IdlingResourceUtils.getIdlingResource();

        //Register the idling resource
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void onRecipeClicked_openRecipeDetailActivity() {
        onView(withId(R.id.recipe_list_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click())); //Click position 0
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}
