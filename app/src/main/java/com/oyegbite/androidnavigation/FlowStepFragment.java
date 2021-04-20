package com.oyegbite.androidnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FlowStepFragment extends Fragment {

    public FlowStepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

//        int flowStepNumber = getArguments().getInt("flowStepNumber");
        // TODO STEP 8 - Use type-safe arguments - remove previous line!
        FlowStepFragmentArgs flowStepNumberArgs = FlowStepFragmentArgs.fromBundle(getArguments());
        int flowStepNumber = flowStepNumberArgs.getFlowStepNumber();
        // TODO END STEP 8

        // Inflate the layout for this fragment
        if (flowStepNumber == 2) {
            return inflater.inflate(R.layout.fragment_flow_step_two, container, false);
        }
        return inflater.inflate(R.layout.fragment_flow_step_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.next_button).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action)
        );
    }
}