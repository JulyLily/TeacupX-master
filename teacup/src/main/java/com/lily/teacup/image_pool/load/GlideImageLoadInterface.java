package com.lily.teacup.image_pool.load;

import android.content.Context;
import android.widget.ImageView;


public interface GlideImageLoadInterface {

    void ImageLoad(Context mContext, ImageView mImageView, String url);

    void ImageLoad(Context mContext, ImageView mImageView, String url, int mErrorOrPlacePath, ImgUtilsType loadType);

    void ImageLoad(Context mContext, ImageView mImageView, String url, int mErrorPath, int placeHolderPath);
}
