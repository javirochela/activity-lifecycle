package com.example.android.lifecycle;

import android.os.AsyncTask;
import android.os.Trace;
import android.text.Html;
import android.util.Log;

/**
 * Created by javi_rochela on 11/2/16.
 */
public class MyAsyncTaskB extends AsyncTask<Integer, Integer, Void> {

    @Override
    protected void onPreExecute(){
        Log.d("Status_tracker","PreExecute2");
    }

    @Override
    protected Void doInBackground(Integer... wait) {
        Log.d("Status_tracker","DoingAsync2....");
        for (int i=0; i<5; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress((int)((i/(float)5 )*100));
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... i) {
        Log.d("Status_tracker","Progress2..."+i[0]+"%");

    }

    @Override
    protected void onPostExecute(Void v) {
        Log.d("Status_tracker","PostExecute2");
    }

}