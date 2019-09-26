package com.example.inprint.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.inprint.myview.GlideRoundTransform;
import com.makeramen.roundedimageview.RoundedImageView;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView){
        Glide.with(context).load(path).into(imageView);
    }
    @Override
    public ImageView createImageView(Context context) {
        RoundedImageView roundedImg = new RoundedImageView(context, null);
        roundedImg.setCornerRadius(20);
        return roundedImg;
    }
}
