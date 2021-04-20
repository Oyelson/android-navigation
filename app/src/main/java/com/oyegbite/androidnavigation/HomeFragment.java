package com.oyegbite.androidnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO STEP 5 - Set an OnClickListener, using Navigation.createNavigateOnClickListener()
//        view.findViewById(R.id.navigate_destination_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view).navigate(R.id.flow_step_one_dest, null);
//            }
//        });
        /**
         * OR you can use Navigation.createNavigateOnClickListener(R.id.flow_step_one_dest)
         *    in the setOnClickListener instead of the default View.OnClickListener(){}
         * */
        //TODO END STEP 5

        //TODO STEP 6 - Set NavOptions to Add a Custom Transition (animation)
        // Comment STEP 5 if you want animation included
        NavOptions.Builder navOptionsBuilder = new NavOptions.Builder();
        navOptionsBuilder.setEnterAnim(R.anim.slide_in_right);
        navOptionsBuilder.setExitAnim(R.anim.slide_out_left);
        navOptionsBuilder.setPopEnterAnim(R.anim.slide_in_left);
        navOptionsBuilder.setPopEnterAnim(R.anim.slide_out_right);
        NavOptions options = navOptionsBuilder.build();

        view.findViewById(R.id.navigate_destination_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.flow_step_one_dest, null, options);
            }
        });
        //TODO END STEP 6

        //TODO STEP 7.2 - Update the OnClickListener to navigate using an action
        view.findViewById(R.id.navigate_action_button).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action, null)
        );
        //TODO END STEP 7.2
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }
}