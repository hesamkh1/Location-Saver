package com.example.locationsaver.ui.ui;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.locationsaver.R;
import com.example.locationsaver.databinding.FragmentSavePageBinding;
import com.example.locationsaver.viewmodels.SavePageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;


public class SavePageFragment extends Fragment implements SavePageNavigator{

    FragmentSavePageBinding binding;
    private SavePageViewModel savePageViewModel;
    private Spinner spinner;
    private Button save_btn;
    private String latitude,longitude;
    String[] Cgroup = { "Cafe","Resturant","Gym","Shop"};

    ImageView ivImage;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;

    public SavePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_save_page,container,false);
        View view=binding.getRoot();
        savePageViewModel = ViewModelProviders.of(this).get(SavePageViewModel.class);
        binding.setViewModel(savePageViewModel);
        binding.setLifecycleOwner(this);
        savePageViewModel.setViewListener(this);
        setHasOptionsMenu(true);//Make sure you have this line of code.
        Bundle bundle=this.getArguments();
        latitude = bundle.getString("latitude");
        longitude = bundle.getString("longitude");


        spinner=binding.groupSpinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                if (item.equals("Cafe")){
                    savePageViewModel.setgroup("Cafe");
                }
                else if (item.equals("Resturant")){
                    savePageViewModel.setgroup("Resturant");
                }
                else if (item.equals("Gym")){
                    savePageViewModel.setgroup("Gym");
                }
                else if (item.equals("Shop")){
                    savePageViewModel.setgroup("Shop");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });
        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.spinner_style,Cgroup);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(arrayAdapter);

        ivImage =binding.imageholder;
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });

        //save btn
        save_btn=binding.saveBtn;
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.nameEt.getText().toString().equals("") && !binding.nameEt.getText().toString().startsWith(" ")) {
                    savePageViewModel.setname(binding.nameEt.getText().toString());
                    savePageViewModel.setnumber(binding.numberEt.getText().toString());
                    savePageViewModel.setaddress(binding.addressEt.getText().toString());
                    savePageViewModel.setnote(binding.noteEt.getText().toString());
                    savePageViewModel.setlatitude(latitude);
                    savePageViewModel.setlongitude(longitude);
                    savePageViewModel.SaveCheker();
                }
                else {
                    Toast.makeText(getContext(),"Location must have Name !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void SelectImage(){
        final CharSequence[] items={"Camera","Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_DENIED){
                        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA);}

                      Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    
                      startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==REQUEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                savePageViewModel.storeImage(bmp);
                ivImage.setImageBitmap(bmp);

            }else if(requestCode==SELECT_FILE){
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    savePageViewModel.storeImage(bitmap);
                    ivImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                } } } }

    @Override
    public void onPerformMapPage() {
        Bundle bundle = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(bundle);
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container_main,fragment).commit();
    }
}