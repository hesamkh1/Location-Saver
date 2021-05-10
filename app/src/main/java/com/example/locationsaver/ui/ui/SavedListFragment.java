package com.example.locationsaver.ui.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.locationsaver.R;
import com.example.locationsaver.adapter.Adapter_SavedList;
import com.example.locationsaver.databinding.FragmentSavePageBinding;
import com.example.locationsaver.databinding.FragmentSavedListBinding;
import com.example.locationsaver.model.Location;
import com.example.locationsaver.viewmodels.SaveListViewModel;
import com.example.locationsaver.viewmodels.SavePageViewModel;

import java.util.List;


public class SavedListFragment extends Fragment {


    FragmentSavedListBinding binding;
    private SaveListViewModel saveListViewModel;
    RecyclerView recyclerView;
    Adapter_SavedList adapter_savedList;
    private RecyclerView.LayoutManager layoutManager;


    public SavedListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_saved_list,container,false);
        View view=binding.getRoot();
        saveListViewModel = ViewModelProviders.of(this).get(SaveListViewModel.class);
        binding.setViewModel(saveListViewModel);
        binding.setLifecycleOwner(this);
      //  saveListViewModel.setViewListener(this);

        recyclerView=binding.recyclerSavelist;
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        saveListViewModel.GetLocations().observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(List<Location> locations) {
                Log.e("SaveListFragment",locations.toString());
                adapter_savedList = new Adapter_SavedList(locations,getFragmentManager(),getContext());
                recyclerView.setAdapter(adapter_savedList);
                adapter_savedList.setLocations(locations);

            }
        });


        return view;
    }
}