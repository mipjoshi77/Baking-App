package com.example.recipecookbook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipecookbook.R;
import com.example.recipecookbook.model.Ingredient;

import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientListAdapterViewHolder> {

    private List<Ingredient> ingredientList;
    private Context context;

    public IngredientListAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredients_rv_list_items, parent, false);
        return new IngredientListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListAdapterViewHolder holder, int position) {
        holder.quantity.setText(ingredientList.get(position).getQuantity().toString());
        holder.measure.setText(ingredientList.get(position).getMeasure());
        holder.ingredient.setText(ingredientList.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class IngredientListAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView quantity;
        private TextView measure;
        private TextView ingredient;


        public IngredientListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity =  itemView.findViewById(R.id.quantity_value);
            measure = itemView.findViewById(R.id.measure_value);
            ingredient = itemView.findViewById(R.id.ingredient_value);
        }
    }
}
