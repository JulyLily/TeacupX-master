package com.lily.teacup.image_pool.load;

public enum ImgUtilsType {
    IMAGE_LOAD_ERROR(0),
    IMAGE_LOAD_PLACEHOLDER(1);

    private final int mType;

    ImgUtilsType(int type){
        mType=type;
    }

    public int getValue(){
        return mType;
    }
}
