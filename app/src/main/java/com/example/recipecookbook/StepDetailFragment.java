package com.example.recipecookbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recipecookbook.databinding.StepDetailFragmentBinding;

public class StepDetailFragment extends Fragment {

    private StepDetailFragmentBinding binding;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = StepDetailFragmentBinding.inflate(getLayoutInflater());
        rootView = binding.getRoot();
        return rootView;
    }
}
