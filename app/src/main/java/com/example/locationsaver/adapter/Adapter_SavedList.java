package com.example.locationsaver.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.locationsaver.R;
import com.example.locationsaver.model.Location;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class Adapter_SavedList extends RecyclerView.Adapter<Adapter_SavedList.MyViewHolder> {

    private List<Location> locations;
    private Context context;
    FragmentManager fragmentManager;

    public Adapter_SavedList(List<Location> locations,FragmentManager fragmentManagerm,Context context) {
        this.context=context;
        this.locations = locations;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public  Adapter_SavedList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_for_savedlist, parent, false);
        return new  Adapter_SavedList.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter_SavedList.MyViewHolder holder, final int position) {
        holder.name.setText(locations.get(position).getName());
        holder.address.setText(locations.get(position).getAddress());
        holder.group.setText(locations.get(position).getGroup());
        holder.note.setText(locations.get(position).getNote());
        holder.number.setText(locations.get(position).getNumber());
             Glide.with(context)
                    .load(locations.get(position).getImage())
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .fallback(R.mipmap.ic_launcher)
                    .into(holder.img);
        holder.direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Lat lang",locations.get(position).getLatitude()+ locations.get(position).getLongitude());
//                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(locations.get(position).getLatitude()), Float.parseFloat(locations.get(position).getLongitude()));
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                context.startActivity(intent);

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + locations.get(position).getLatitude() + ","+locations.get(position).getLongitude()));
                context.startActivity(intent);
            }
        });




//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // Toast.makeText(context,"click on"+ cafe.get(position).getName().toString(),Toast.LENGTH_SHORT).show();
//
//                Bundle bundle=new Bundle();
//                    bundle.putString("info",cafe.get(position).getInfo());
//                bundle.putString("category",cafe.get(position).getCategory());
//
//                CafePageInfoFragment fragment=new CafePageInfoFragment();
//                fragment.setArguments(bundle);
//                fragmentManager.beginTransaction().replace(R.id.fragment_container_home,fragment).addToBackStack("HomePage").commit();
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
    public void setLocations(List<Location> locations) {
        this.locations = locations;
        notifyDataSetChanged();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,address,note,group,number;
        ImageView img;
        Button direction;
        LinearLayout parentLayout;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.name_savedlist);
            img=itemView.findViewById(R.id.imageview_savelist);
            number=itemView.findViewById(R.id.number_savedlist);
            note=itemView.findViewById(R.id.note_savedlist);
            address=itemView.findViewById(R.id.address_savedlist);
            group=itemView.findViewById(R.id.group_savedlist);
            direction=itemView.findViewById(R.id.direction_savelist);

        }

    }
}
