package com.example.inprint.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.R;
import com.example.inprint.bean.Talk;

import java.util.List;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {

    private List<Talk> mTalkList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView limg,rimg;
        TextView ltalk,rtalk;
        LinearLayout llayout,rlayout;
        ViewHolder(View view){
            super(view);
            limg = view.findViewById(R.id.talk_item_img1);
            rimg = view.findViewById(R.id.talk_item_img2);

            ltalk = view.findViewById(R.id.talk_item_content1);
            rtalk = view.findViewById(R.id.talk_item_content2);

            llayout = view.findViewById(R.id.talk_layout_l);
            rlayout = view.findViewById(R.id.talk_layout_r);
        }
    }
    public TalkAdapter(List<Talk> mTalkList){
        this.mTalkList = mTalkList;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int ViewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Talk talk = mTalkList.get(position);

        if(talk.getWhere() == 0){
            holder.llayout.setVisibility(View.VISIBLE);
            holder.rlayout.setVisibility(View.GONE);

            holder.ltalk.setText(talk.getContent());
        }else{
            holder.llayout.setVisibility(View.GONE);
            holder.rlayout.setVisibility(View.VISIBLE);

            holder.rtalk.setText(talk.getContent());
        }
    }
    @Override
    public int getItemCount(){
        return mTalkList.size();
    }
}
