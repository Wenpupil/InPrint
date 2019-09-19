package com.example.inprint.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.inprint.R;
import com.example.inprint.myview.GlideRoundTransform;
import com.example.inprint.presenter.UserFragmentPresenter;

public class UserFragment extends Fragment implements View.OnClickListener{
    private ImageView iv_userHead;
    private ConstraintLayout cl_balance;   //余额控件
    private ConstraintLayout cl_service;   //客服控件
    private ConstraintLayout cl_recharge;  //充值控件
    private ConstraintLayout cl_set;       //设置控件

    private UserFragmentPresenter userFragmentPresenter;   //中间对象件

    private View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view=inflater.inflate(R.layout.user_fragment,container,false);
        userFragmentPresenter=new UserFragmentPresenter();
        iniView();
        return view;
    }
    private void iniView(){

        iv_userHead=view.findViewById(R.id.iv_user_fragment_img);
        cl_balance=view.findViewById(R.id.cl_user_fragment_balance);
        cl_service=view.findViewById(R.id.cl_user_fragment_service);
        cl_recharge=view.findViewById(R.id.cl_user_fragment_recharge);
        cl_set=view.findViewById(R.id.cl_user_fragment_set);

        cl_balance.setOnClickListener(this);
        cl_service.setOnClickListener(this);
        cl_recharge.setOnClickListener(this);
        cl_set.setOnClickListener(this);
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

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.cl_user_fragment_balance:
                break;
            case R.id.cl_user_fragment_service:
                break;
            case R.id.cl_user_fragment_recharge:
                break;
            case R.id.cl_user_fragment_set:
                break;
        }
    }
}
