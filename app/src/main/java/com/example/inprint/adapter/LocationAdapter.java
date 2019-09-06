package com.example.inprint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.R;
import com.example.inprint.bean.Location;

import java.util.List;

/*
 * @Author Wenpupil
 * @Time 2019.09.05
 * @Description 位置列表适配器：初始化位置信息列表
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Location> locationList;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener{
        void onClick(View v,String locationId);
    }
    public void setContext(Context context){
        this.context=context;
    }

    public void setItemListener(OnItemClickListener listener){
        this.listener=listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView locName;
        TextView locStatus;
        ViewHolder(View view){
            super(view);
            locName=view.findViewById(R.id.tv_location);
            locStatus=view.findViewById(R.id.tv_status);
        }
    }
    public LocationAdapter(List<Location> locationList){
        this.locationList=locationList;
    }
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Location location=locationList.get(position);
        holder.locName.setText(location.getLoc());
        holder.itemView.setTag(location.getLocationId());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener!=null){
                    listener.onClick(v,(String)v.getTag());
                }
            }
        });
        if(location.getStatus()==0){
            holder.locStatus.setText("空闲");
        }else if(location.getStatus()==1){
            holder.locStatus.setText("忙碌");
        }
    }
    @Override
    public int getItemCount(){
        return locationList.size();
    }
}
