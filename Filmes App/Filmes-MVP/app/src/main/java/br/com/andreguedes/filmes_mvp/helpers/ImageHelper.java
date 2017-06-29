package br.com.andreguedes.filmes_mvp.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by andreguedes on 25/06/17.
 */

public class ImageHelper {

    public static void loadImages(Context context, String file, ImageView img) {
        Glide.with(context)
                .load(file)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(img);
    }

}