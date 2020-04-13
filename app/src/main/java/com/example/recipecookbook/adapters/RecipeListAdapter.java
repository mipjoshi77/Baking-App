package com.example.recipecookbook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipecookbook.R;
import com.example.recipecookbook.model.Recipe;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListAdapterViewHolder> {

    private List<Recipe> recipeList;
    private Context context;
    private RecipeItemOnClickHandler recipeItemOnClickHandler;

    public RecipeListAdapter(List<Recipe> recipeList, Context context, RecipeItemOnClickHandler recipeItemOnClickHandler) {
        this.recipeList = recipeList;
        this.context = context;
        this.recipeItemOnClickHandler = recipeItemOnClickHandler;
    }

    @NonNull
    @Override
    public RecipeListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_rv_list_items, parent, false);
        return new RecipeListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapterViewHolder holder, int position) {
        String recipeImage = recipeList.get(position).getImage();
        String recipeName = recipeList.get(position).getName();

        holder.recipeName.setText(recipeName);

        if (!recipeImage.isEmpty()) {
            Glide.with(context)
                    .load(recipeImage)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.recipeImage);
        }
        else {
            switch (position) {
                case 0:
                    holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.nutella_pie));
                    break;
                case 1:
                    holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.brownie));
                    break;
                case 2:
                    holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.yello_cake));
                    break;
                case 3:
                    holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.cheesecake));
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public interface RecipeItemOnClickHandler {
        void onClick(int position);
    }

    public class RecipeListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MaterialCardView recipeCardView;
        private ImageView recipeImage;
        private TextView recipeName;

        public RecipeListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeCardView = itemView.findViewById(R.id.recipe_card);
            recipeImage = itemView.findViewById(R.id.recipe_image);
            recipeName = itemView.findViewById(R.id.recipe_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            recipeItemOnClickHandler.onClick(position);
        }
    }
}
