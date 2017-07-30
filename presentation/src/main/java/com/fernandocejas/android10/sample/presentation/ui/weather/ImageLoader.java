package com.fernandocejas.android10.sample.presentation.ui.weather;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Created by Ruby on 7/30/2017.
 */

class ImageLoader {
  public static void fromUrl(Context context, ImageView imageView, String url) {
    Glide.with(context).load(url).into(imageView);
  }
}
