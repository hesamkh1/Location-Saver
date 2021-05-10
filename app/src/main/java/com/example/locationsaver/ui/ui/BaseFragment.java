package com.example.locationsaver.ui.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.locationsaver.R;

import dagger.android.DaggerFragment;


public  abstract class BaseFragment<T extends ViewModel> extends DaggerFragment {

    private T viewModel;

    /**
     *
     * @return view model instance
     */
    public abstract T getViewModel();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        viewModel = getViewModel();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
