package com.example.recipecookbook;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipecookbook.adapters.IngredientListAdapter;
import com.example.recipecookbook.adapters.StepListAdapter;
import com.example.recipecookbook.databinding.RecipeDetailFragmentBinding;
import com.example.recipecookbook.model.Ingredient;
import com.example.recipecookbook.model.Step;

import java.util.List;

public class RecipeDetailFragment extends Fragment implements StepListAdapter.OnStepClickListener {

    private RecipeDetailFragmentBinding binding;
    private View rootView;

    private RecyclerView ingredientsRecyclerView;
    private IngredientListAdapter ingredientListAdapter;
    private StepListAdapter stepListAdapter;

    private RecyclerView stepsRecyclerView;

    private List<Ingredient> ingredientList;
    private List<Step> stepList;
    private PassStepClickedDataToActivity passStepClickedDataToActivity;

    public RecipeDetailFragment(List<Ingredient> ingredientList, List<Step> stepList) {
        this.ingredientList = ingredientList;
        this.stepList = stepList;
    }

    public interface PassStepClickedDataToActivity {
        void passStepClickedDataToActivity(int position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = RecipeDetailFragmentBinding.inflate(getLayoutInflater());
        rootView = binding.getRoot();
        ingredientsRecyclerView = binding.recipeIngredientsRv;
        stepsRecyclerView = binding.recipeStepsRv;
        setIngredientsView();
        setStepsView();
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            passStepClickedDataToActivity = (PassStepClickedDataToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement PassStepClickedDataToActivity");
        }
    }

    private void setIngredientsView() {
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ingredientListAdapter = new IngredientListAdapter(ingredientList, getContext());
        ingredientsRecyclerView.setAdapter(ingredientListAdapter);
    }

    private void setStepsView() {
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        stepListAdapter = new StepListAdapter(getContext(), stepList, this);
        stepsRecyclerView.setAdapter(stepListAdapter);
    }

    @Override
    public void onStepClicked(int position) {
        passStepClickedDataToActivity.passStepClickedDataToActivity(position);
    }
}
