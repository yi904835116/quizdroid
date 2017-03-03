package edu.washington.yiz24.quizdroid;

/**
 * Created by yizhaoyang on 3/2/17.
 */


import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import android.content.IntentFilter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.washington.yiz24.quizdroid.QuizApp;


public class AlarmReceiver extends BroadcastReceiver {

    private static boolean inProgress = false;
    private long enqueue;
    private DownloadManager dm;

    public AlarmReceiver() {
        Log.i("ALARM", "constructor entered");



        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(
                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(enqueue);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int columnIndex = c
                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                            Log.i("SUCCESS", "woogoo");

                            String uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));

                            String FILENAME = "quizdata.json";
                            String string = uriString;
                            StringBuffer stringBuff = new StringBuffer("");
                            int ch;

                            try {
                                ParcelFileDescriptor file = dm.openDownloadedFile(enqueue);
                                FileInputStream fileInputStream
                                        = new ParcelFileDescriptor.AutoCloseInputStream(file);

                                while( (ch = fileInputStream.read()) != -1) {
                                    stringBuff.append((char) ch);
                                }
                                string = stringBuff.toString();
                                FileOutputStream fos = QuizApp.getAppContext().openFileOutput(FILENAME, QuizApp.getAppContext().MODE_PRIVATE);
                                fos.write(string.getBytes());
                                fos.close();
                                InputStream input = QuizApp.getAppContext().openFileInput("quizdata.json");
                                QuizTopics.getInstance().createTopics(input);
                                Log.i("HI", "new topics");

                            } catch (IOException e) {

                            }
                            inProgress = false;

                        } else if (DownloadManager.STATUS_FAILED == c.getInt(columnIndex)) {
                            Log.i("download", "FAILLLLL");
                            //display message
                            AlertDialog.Builder failedDownload  = new AlertDialog.Builder(QuizApp.getAppContext());
                            failedDownload.setMessage("Download failed :( \n" +
                                    "Would you like to retry or quit and try again later?");
                            failedDownload.setTitle("Download failed");
                            failedDownload.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //retry download
                                    inProgress = true;
                                    String oldURL = PreferenceManager.getDefaultSharedPreferences(QuizApp.getAppContext())
                                            .getString("prefURL", "http://tednewardsandbox.site44.com/questions.json");
                                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(oldURL));
                                    enqueue = dm.enqueue(request);
                                }
                            });
                            failedDownload.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    System.exit(0);
                                }
                            });
                            failedDownload.setCancelable(false);
                            AlertDialog dialog = failedDownload.create();
                            try {
                                dialog.show();
                            } catch (Exception e) {

                            }
                        }
                    }
                }
                inProgress = false;
                dm.remove(enqueue);
            }
        };


        QuizApp.getAppContext().registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public void onReceive(Context ctxt, Intent intent) {
        inProgress = true;
        dm = (DownloadManager) ctxt.getSystemService(ctxt.DOWNLOAD_SERVICE);
        String url = PreferenceManager.getDefaultSharedPreferences(ctxt).getString("prefURL",
                "http://tednewardsandbox.site44.com/questions.json");

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        Log.i("HI", "Made request");
        enqueue = dm.enqueue(request);


    }

    public static boolean isInProgress() {
        return inProgress;
    }
}