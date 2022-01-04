package com.example.mynew;

public class word {

    private String mDefaultTranslation;

    private String mMarathiTranslation;
    private int mAudioResource;


    private  int mImageResourceId = No_Image;

    private static final int No_Image = -1;

    public word(String DefaultTranslation , String MarathiTranslation , int AudioResource)
    {
        mDefaultTranslation = DefaultTranslation;
        mMarathiTranslation = MarathiTranslation;
        mAudioResource = AudioResource;
    }

    public word(String DefaultTranslation , String MarathiTranslation , int ImageResourceId ,int AudioResource)
    {
        mDefaultTranslation = DefaultTranslation;
        mMarathiTranslation = MarathiTranslation;
        mImageResourceId = ImageResourceId;
        mAudioResource = AudioResource;
    }

    public String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }
    public int getImageResourceId()
    {
        return mImageResourceId;
    }

    public boolean hasImage()
    {
        return mImageResourceId != No_Image;

    }

    public int getAudioResource()
    {
        return mAudioResource;
    }


    public String getMarathiTranslation() {
        return mMarathiTranslation;
    }
}
