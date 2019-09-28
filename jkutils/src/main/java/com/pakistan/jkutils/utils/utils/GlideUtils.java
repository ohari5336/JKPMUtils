package com.pakistan.jkutils.utils.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtils {

    public static void loadCircularImage(Context context, String image, ImageView holder){
        Glide
                .with(context)
                .load(image)
                .apply(RequestOptions.circleCropTransform())
                .into(holder);
    }

    public static void loadCircularImage(Context context, int image, ImageView holder){
        Glide
                .with(context)
                .load(image)
                .apply(RequestOptions.circleCropTransform())
                .into(holder);
    }

    public static void loadImage(Context context, String image, ImageView holder){
        Glide
                .with(context)
                .load(image)
                .into(holder);
    }

    public static void loadImage(Context context, int image, ImageView holder){
        Glide
                .with(context)
                .load(image)
                .into(holder);
    }

}
