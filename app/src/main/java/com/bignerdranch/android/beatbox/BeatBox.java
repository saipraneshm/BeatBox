package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sai pranesh on 30-Aug-16.
 */
public class BeatBox {

    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final Integer MAX_SOUNDS = 5;
    private AssetManager mAssetManager;
    List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context){
        mAssetManager = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }

    public void loadSounds(){
        String[] soundNames;

        try{
            soundNames = mAssetManager.list(SOUNDS_FOLDER);
            Log.i(TAG,"Found " + soundNames.length + " sound files");
        } catch (IOException e) {
            Log.e(TAG,"Couldn't load sound files ", e);
            e.printStackTrace();
            return;
        }


        try{
            for(String filename : soundNames){
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            }
        } catch (IOException e) {
            Log.e(TAG,"Couldn't load the sound ", e);
            e.printStackTrace();
        }

    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssetManager.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }

    public void release(){
        mSoundPool.release();
    }
}
