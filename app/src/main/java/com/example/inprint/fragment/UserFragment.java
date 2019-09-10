package com.example.inprint.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.inprint.R;
import com.example.inprint.myview.GlideRoundTransform;

public class UserFragment extends Fragment {
    private ImageView iv_userHead;
    private View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view=inflater.inflate(R.layout.user_fragment,container,false);
        iniView();
        return view;
    }
    private void iniView(){
        iv_userHead=view.findViewById(R.id.iv_user_fragment_img);

        roundTransformImg();
    }

    /**
     * 图片圆角化
     */
    private void roundTransformImg(){
        Glide.with(getContext())
                .load(R.mipmap.userimg)
                .transform(new CenterCrop(getContext()),new GlideRoundTransform(getContext()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.userimgback)
                .dontAnimate()
                .crossFade()
                .into(iv_userHead);
    }
}
