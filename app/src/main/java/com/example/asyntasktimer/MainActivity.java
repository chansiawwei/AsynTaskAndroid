package com.example.asyntasktimer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View v ){
        new CountDownTask().execute();
    }

    private class CountDownTask extends AsyncTask<Void, Integer, Void> {

        // A callback method executed on UI thread on starting the task
        @Override
        protected void onPreExecute() {
            // Getting reference to the TextView tv_counter of the layout activity_main
            TextView textview1 = (TextView) findViewById(R.id.textview1);
            textview1.setText("Countdown Starts ");
        }

        // A callback method executed on non UI thread, invoked after
        // onPreExecute method if exists
        @Override
        protected Void doInBackground(Void... params) {
            for(int i=3;i>=0;i--){
                try {
                    Thread.sleep(1000);
                    publishProgress(i); // Invokes onProgressUpdate()
                } catch (InterruptedException e) {
                }
            }
            return null;
        }

        // A callback method executed on UI thread, invoked by the publishProgress()
        // from doInBackground() method
        @Override
        protected void onProgressUpdate(Integer... values) {
            // Getting reference to the TextView tv_counter of the layout activity_main
            TextView textview1 = (TextView) findViewById(R.id.textview1);

            // Updating the TextView
            textview1.setText( Integer.toString(values[0].intValue()));
        }

        // A callback method executed on UI thread, invoked after the completion of the task
        @Override
        protected void onPostExecute(Void result) {
            // Getting reference to the TextView tv_counter of the layout activity_main
            TextView tvCounter = (TextView) findViewById(R.id.textview1);
            tvCounter.setText("Times Up...");
        }
    }
}
