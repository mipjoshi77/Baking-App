package com.example.recipecookbook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipecookbook.R;
import com.example.recipecookbook.model.Step;

import java.util.List;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepListAdapterViewHolder> {

    private Context context;
    private List<Step> stepList;
    private OnStepClickListener onStepClickListener;

    public StepListAdapter(Context context, List<Step> stepList, OnStepClickListener onStepClickListener) {
        this.context = context;
        this.stepList = stepList;
        this.onStepClickListener = onStepClickListener;
    }

    @NonNull
    @Override
    public StepListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.steps_rv_list_items, parent, false);
        return new StepListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListAdapterViewHolder holder, int position) {
        holder.stepNumber.setText(stepList.get(position).getId().toString());
        holder.stepDescription.setText(stepList.get(position).getShortDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStepClickListener.onStepClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public interface OnStepClickListener {
        void onStepClicked(int position);
    }

    public class StepListAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView stepNumber;
        private TextView stepDescription;

        public StepListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNumber = itemView.findViewById(R.id.step_number);
            stepDescription = itemView.findViewById(R.id.short_description);
        }
    }
}
