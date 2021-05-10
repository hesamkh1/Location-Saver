package com.example.locationsaver.viewmodels;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.locationsaver.model.Location;
import com.example.locationsaver.repository.LocationRepository;
import com.example.locationsaver.session.AppDatabase;
import com.example.locationsaver.session.LocationDAO;
import com.example.locationsaver.ui.ui.LoginNavigator;
import com.example.locationsaver.ui.ui.SavePageNavigator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SavePageViewModel extends AndroidViewModel {

    LocationRepository locationRepository;
    Location location;
    private SavePageNavigator mListener;
    private MutableLiveData<String> mname = new MutableLiveData<>();
    private MutableLiveData<String> mnumber = new MutableLiveData<>();
    private MutableLiveData<String> mnote = new MutableLiveData<>();
    private MutableLiveData<String> maddress = new MutableLiveData<>();
    private MutableLiveData<String> mgroup = new MutableLiveData<>();
    private MutableLiveData<String> mimage_address = new MutableLiveData<>();
    private MutableLiveData<String> mlatitude = new MutableLiveData<>();
    private MutableLiveData<String> mlongitude = new MutableLiveData<>();


    public SavePageViewModel(@NonNull Application application) {
        super(application);
        locationRepository=new LocationRepository(application);
      //  locationRepository.getInstance(application);
    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() { return super.getApplication(); }

    public void setViewListener(SavePageNavigator listener) {
        this.mListener = listener;
    }

    public MutableLiveData<String> getname() {
        if(mname==null){
            mname=new MutableLiveData<>();
        }
        return mname;
    }
    public void setname(String name) {
       mname.setValue(name);
    }
    public MutableLiveData<String> getnumber() {
        if(mnumber==null){
            mnumber=new MutableLiveData<>();
        }
        return mnumber;
    }
    public void setnumber(String number) {
        mnumber.setValue(number);
    }
    public MutableLiveData<String> getnote() {
        if(mnote==null){
            mnote=new MutableLiveData<>();
        }
        return mnote;
    }
    public void setnote(String note) {
       mnote.setValue(note);
    }
    public MutableLiveData<String> getaddress() {
        if(maddress==null){
            maddress=new MutableLiveData<>();
        }
        return maddress;
    }
    public void setaddress(String address) {
        maddress.setValue(address);
    }
    public MutableLiveData<String> getgroup() {
        if(mgroup==null){
            mgroup=new MutableLiveData<>();
        }
        return mgroup;
    }

    public void setlatitude(String latitude) {
        mlatitude.setValue(latitude);
    }
    public MutableLiveData<String> getlatitude() {
        if(mlatitude==null){
            mlatitude=new MutableLiveData<>();
        }
        return mlatitude;
    }

    public void setlongitude(String longitude) {
        mlongitude.setValue(longitude);
    }
    public MutableLiveData<String> getlongitude() {
        if(mlongitude==null){
            mlongitude=new MutableLiveData<>();
        }
        return mlongitude;
    }




    public void setgroup(String group) {
        mgroup.setValue(group);
    }
    public MutableLiveData<String> getimage_address() {
        if(mgroup==null){
            mgroup=new MutableLiveData<>();
        }
        return mimage_address;
    }
    public void setimage_address(String  image_address) {
        mimage_address.setValue(image_address);
    }


    public void InsertLocation(Location location){

        locationRepository.InsertToDb(location);
    }

//    public List<Location> GetLocations(){
//        return locationRepository.GetAllDb();
//    }

    //save image in exernal storage
    public void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 98, fos);
            fos.close();

            Toast.makeText(getApplication(),"saved in gallery",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    /** Create a File for saving an image or video */
    public   File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplication().getPackageName()
                + "/Files/");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".png";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);

        //my do
        setimage_address(Environment.getExternalStorageDirectory()+
                "/Android/data/" +
                        getApplication().getPackageName()
                        + "/Files/"
                        +"MI_"+ timeStamp +".png");

        return mediaFile;
    }

    public void SaveCheker()
    {
        if(getname() != null && getaddress() != null && getgroup() != null && getnumber() != null && getnote() != null)
        {
            location=new Location(getname().getValue(),getnumber().getValue(),getnote().getValue()
                                  ,getaddress().getValue(),getgroup().getValue()
                                  ,getimage_address().getValue(),getlatitude().getValue(),getlongitude().getValue());
            InsertLocation(location);
            mListener.onPerformMapPage();

        }
    }



}
