package com.bignerdranch.android.beatbox;

/**
 * Created by sai pranesh on 30-Aug-16.
 */
public class Sound {

    private String mAssetPath;
    private String mSoundName;
    private Integer mSoundId;

    public Sound(String assetPath){
        mAssetPath = assetPath;
        String[] components = mAssetPath.split("/");
        mSoundName = components[components.length-1];
        mSoundName = mSoundName.replace(".wav","");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getSoundName() {
        return mSoundName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
