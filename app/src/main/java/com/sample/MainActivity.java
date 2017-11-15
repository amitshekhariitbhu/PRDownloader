/*
 *    Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.sample.utils.Utils;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    Button buttonOne, buttonTwo, buttonThree, buttonFour,
            buttonFive, buttonSix, buttonSeven, buttonEight,
            buttonNine, buttonTen;
    TextView tvProgressOne, tvProgressTwo, tvProgressThree,
            tvProgressFour, tvProgressFive, tvProgressSix,
            tvProgressSeven, tvProgressEight, tvProgressNine,
            tvProgressTen;
    ProgressBar progressBarOne, progressBarTwo, progressBarThree,
            progressBarFour, progressBarFive, progressBarSix,
            progressBarSeven, progressBarEight, progressBarNine,
            progressBarTen;

    int downloadIdOne;

    boolean runningOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonTen = findViewById(R.id.buttonTen);

        tvProgressOne = findViewById(R.id.tvProgressOne);
        tvProgressTwo = findViewById(R.id.tvProgressTwo);
        tvProgressThree = findViewById(R.id.tvProgressThree);
        tvProgressFour = findViewById(R.id.tvProgressFour);
        tvProgressFive = findViewById(R.id.tvProgressFive);
        tvProgressSix = findViewById(R.id.tvProgressSix);
        tvProgressSeven = findViewById(R.id.tvProgressSeven);
        tvProgressEight = findViewById(R.id.tvProgressEight);
        tvProgressNine = findViewById(R.id.tvProgressNine);
        tvProgressTen = findViewById(R.id.tvProgressTen);


        progressBarOne = findViewById(R.id.progressBarOne);
        progressBarTwo = findViewById(R.id.progressBarTwo);
        progressBarThree = findViewById(R.id.progressBarThree);
        progressBarFour = findViewById(R.id.progressBarFour);
        progressBarFive = findViewById(R.id.progressBarFive);
        progressBarSix = findViewById(R.id.progressBarSix);
        progressBarSeven = findViewById(R.id.progressBarSeven);
        progressBarEight = findViewById(R.id.progressBarEight);
        progressBarNine = findViewById(R.id.progressBarNine);
        progressBarTen = findViewById(R.id.progressBarTen);

        final String URL1 = "http://www.appsapk.com/downloading/latest/Facebook-119.0.0.23.70.apk";
        final String URL2 = "http://www.appsapk.com/downloading/latest/WeChat-6.5.7.apk";
        final String URL3 = "http://www.appsapk.com/downloading/latest/Instagram.apk";
        final String URL4 = "http://www.appsapk.com/downloading/latest/Emoji%20Flashlight%20-%20Brightest%20Flashlight%202018-2.0.1.apk";
        final String URL5 = "http://www.appsapk.com/downloading/latest/Screen%20Recorder-7.7.apk";
        final String URL6 = "http://www.appsapk.com/downloading/latest/Call%20Recorder%20-%20Automatic%20Call%20Recorder-1.6.0.apk";
        final String URL7 = "http://www.appsapk.com/downloading/latest/Sound%20Profile%20(+%20volume%20scheduler)-5.25.apk";
        final String URL8 = "http://www.appsapk.com/downloading/latest/Evernote%20-%20stay%20organized.-7.9.7.apk";
        final String URL9 = "http://www.appsapk.com/downloading/latest/UC-Browser.apk";
        final String URL10 = "http://www.appsapk.com/downloading/latest/Barcode%20Scanner-1.2.apk";


        //60 mb apk
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (downloadIdOne != 0 && !runningOne) {
                    PRDownloader.resume(downloadIdOne);
                    runningOne = true;
                    buttonOne.setText("Pause");
                    return;
                }

                if (downloadIdOne != 0 && runningOne) {
                    PRDownloader.pause(downloadIdOne);
                    return;
                }

                buttonOne.setText("Pause");
                runningOne = true;
                downloadIdOne = PRDownloader.download(URL1, Utils.getRootDirPath(getApplicationContext()), "facebook.apk")
                        .build()
                        .setOnStartListener(new OnStartListener() {
                            @Override
                            public void onStart() {

                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                runningOne = false;
                                buttonOne.setText("Resume");
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                runningOne = false;
                                buttonOne.setText("Cancelled");
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressOne.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarOne.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonOne.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonOne.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressOne.setText("Progress");
                                progressBarOne.setProgress(0);
                                downloadIdOne = 0;
                                runningOne = false;
                            }
                        });
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonTwo.setText("Downloading...");
                PRDownloader.download(URL2, Utils.getRootDirPath(getApplicationContext()), "wechat.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressTwo.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarTwo.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonTwo.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonTwo.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressTwo.setText("Progress");
                                progressBarTwo.setProgress(0);
                            }
                        });
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonThree.setText("Downloading...");
                PRDownloader.download(URL3, Utils.getRootDirPath(getApplicationContext()), "instagram.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressThree.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarThree.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonThree.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonThree.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressThree.setText("Progress");
                                progressBarThree.setProgress(0);
                            }
                        });
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonFour.setText("Downloading...");
                PRDownloader.download(URL4, Utils.getRootDirPath(getApplicationContext()), "emojiflashlight.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressFour.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarFour.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFour.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFour.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressFour.setText("Progress");
                                progressBarFour.setProgress(0);
                            }
                        });
            }
        });

        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonFive.setText("Downloading...");
                PRDownloader.download(URL5, Utils.getRootDirPath(getApplicationContext()), "screenrecorder.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressFive.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarFive.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFive.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFive.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressFive.setText("Progress");
                                progressBarFive.setProgress(0);
                            }
                        });
            }
        });

        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSix.setText("Downloading...");
                PRDownloader.download(URL6, Utils.getRootDirPath(getApplicationContext()), "callrecorder.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressSix.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarSix.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonSix.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonSix.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressSix.setText("Progress");
                                progressBarSix.setProgress(0);
                            }
                        });
            }
        });

        //5 mb apk
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSeven.setText("Downloading...");
                PRDownloader.download(URL7, Utils.getRootDirPath(getApplicationContext()), "soundprofile.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressSeven.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarSeven.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonSeven.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonSeven.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressSeven.setText("Progress");
                                progressBarSeven.setProgress(0);
                            }
                        });
            }
        });

        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonEight.setText("Downloading...");
                PRDownloader.download(URL8, Utils.getRootDirPath(getApplicationContext()), "evernote.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressEight.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarEight.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonEight.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonEight.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressEight.setText("Progress");
                                progressBarEight.setProgress(0);
                            }
                        });
            }
        });

        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonNine.setText("Downloading...");
                PRDownloader.download(URL9, Utils.getRootDirPath(getApplicationContext()), "ucbrowser.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressNine.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarNine.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonNine.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonNine.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressNine.setText("Progress");
                                progressBarNine.setProgress(0);
                            }
                        });
            }
        });

        //8 mb apk
        buttonTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonTen.setText("Downloading...");
                PRDownloader.download(URL10, Utils.getRootDirPath(getApplicationContext()), "barcodescanner.apk")
                        .build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                Long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                tvProgressTen.setText("Progress:" + progressPercent.toString() + "%");
                                progressBarTen.setProgress(progressPercent.intValue());
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonTen.setText("Completed");
                            }

                            @Override
                            public void onError(Error error) {
                                buttonTen.setText("START");
                                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                                tvProgressTen.setText("Progress");
                                progressBarTen.setProgress(0);
                            }
                        });
            }
        });
    }
}
