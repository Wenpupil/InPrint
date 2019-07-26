package com.example.inprint.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.inprint.R;
import com.example.inprint.base.Doc;

import java.util.List;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> {

    private List<Doc> mDocList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView docImage;
        TextView docTitle;
        TextView docWhere;
        ViewHolder(View view){
            super(view);
            docImage=view.findViewById(R.id.iv_doc_item_img);
            docTitle=view.findViewById(R.id.tv_doc_item_title);
            docWhere=view.findViewById(R.id.tv_doc_item_where);
        }
    }

    public DocAdapter(List<Doc> docList){
        mDocList=docList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doc_fragment_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Doc doc=mDocList.get(position);

        holder.docTitle.setText(doc.getDocTitle());
        holder.docWhere.setText(doc.getWhere());

        String rate=doc.getDocRate();
        if(rate!=null&&rate.equals("pdf")){
            Glide.with(holder.itemView).
                    load(R.mipmap.pdf).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).
                    into(holder.docImage);
        }else{
            Glide.with(holder.itemView).
                    load(R.mipmap.word).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).
                    into(holder.docImage);
        }
    }
    @Override
    public int getItemCount(){
        return mDocList.size();
    }
}
