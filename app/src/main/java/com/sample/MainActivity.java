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

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.Status;
import com.sample.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static String dirPath;

    final String URL1 = "https://r2-static-assets.androidapksfree.com/sdata/f5963091fb0fd7e683c0cec9e2025b7f/com.facebook.katana_v445.0.0.34.118-448014984_Android-8.0.apk";
    final String URL2 = "https://r2-static-assets.androidapksfree.com/sdata/efe2bee67907827a1a42efdf317b8ae3/com.tencent.mm_v8.0.37-2380_Android-6.0.apk";
    final String URL3 = "https://r-static-assets.androidapks.com/rdata/629a15b917738fc4db672ddd772f453f/com.instagram.android_v314.0.0.20.114-371410133_Android-7.0.apk";
    final String URL4 = "https://r-static-assets.androidapks.com/rdata/32eaeba780349ec9a596ea4d68047d29/com.google.android.youtube_v19.01.34-1543755200_Android-8.0.apk";
    final String URL5 = "https://r-static-assets.androidapks.com/rdata/458f499e9087aad93ce5be09b59a9be0/com.nll.screenrecorder_v11.1-111_Android-5.0.apk";
    final String URL6 = "https://r-static-assets.androidapks.com/rdata/eee65b03b3cbfa6087117690f924130f/com.appstar.callrecorder_v6.40-245_Android-5.0.apk";
    final String URL7 = "https://r2-static-assets.androidapksfree.com/sdata/d3ab26eeec167494b66d4b5de6193460/org.videolan.vlc_v3.5.4-13050404_Android-4.2.apk";
    final String URL8 = "https://r2-static-assets.androidapksfree.com/sdata/cc88a0779892cd0954bf90465eb214d5/com.evernote_v10.51.1-1130687_Android-10.0.apk";
    final String URL9 = "https://r2-static-assets.androidapksfree.com/sdata/616af4dd55d7cc2c962ddf4b8bcf52dd/com.UCMobile.intl_v13.4.2.1307-50212_Android-8.0.apk";
    final String URL10 = "https://r-static-assets.androidapks.com/rdata/f8bcfee07f98ba05a91c4b55b41e08d5/com.snapchat.android_v12.67.0.24-106822_Android-5.0.apk";
    final String URL11 = "https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_640x360.m4v";
    final String URL12 = "https://docenti.unimc.it/antonella.pascali/teaching/2018/19055/files/ultima-lezione/harry-potter-and-the-philosophers-stone";
    final String URL13 = "https://media.giphy.com/media/Bk0CW5frw4qfS/giphy.gif";
    final String URL14 = "https://www.pexels.com/download/video/3195394/";
    final String URL15 = "https://www.pexels.com/download/video/3209828/";

    Button buttonOne, buttonTwo, buttonThree, buttonFour,
            buttonFive, buttonSix, buttonSeven, buttonEight,
            buttonNine, buttonTen, buttonEleven, buttonTwelve,
            buttonThirteen, buttonFourteen, buttonFifteen,
            buttonCancelOne, buttonCancelTwo, buttonCancelThree,
            buttonCancelFour, buttonCancelFive, buttonCancelSix,
            buttonCancelSeven, buttonCancelEight, buttonCancelNine,
            buttonCancelTen, buttonCancelEleven, buttonCancelTwelve,
            buttonCancelThirteen, buttonCancelFourteen, buttonCancelFifteen;

    TextView textViewProgressOne, textViewProgressTwo, textViewProgressThree,
            textViewProgressFour, textViewProgressFive, textViewProgressSix,
            textViewProgressSeven, textViewProgressEight, textViewProgressNine,
            textViewProgressTen, textViewProgressEleven, textViewProgressTwelve,
            textViewProgressThirteen, textViewProgressFourteen, textViewProgressFifteen;

    ProgressBar progressBarOne, progressBarTwo, progressBarThree,
            progressBarFour, progressBarFive, progressBarSix,
            progressBarSeven, progressBarEight, progressBarNine,
            progressBarTen, progressBarEleven, progressBarTwelve,
            progressBarThirteen, progressBarFourteen, progressBarFifteen;

    int downloadIdOne, downloadIdTwo, downloadIdThree, downloadIdFour,
            downloadIdFive, downloadIdSix, downloadIdSeven,
            downloadIdEight, downloadIdNine, downloadIdTen,
            downloadIdEleven, downloadIdTwelve, downloadIdThirteen,
            downloadIdFourteen, downloadIdFifteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dirPath = Utils.getRootDirPath(getApplicationContext());

        init();

        onClickListenerOne();
        onClickListenerTwo();
        onClickListenerThree();
        onClickListenerFour();
        onClickListenerFive();
        onClickListenerSix();
        onClickListenerSeven();
        onClickListenerEight();
        onClickListenerNine();
        onClickListenerTen();
        onClickListenerEleven();
        onClickListenerTwelve();
        onClickListenerThirteen();
        onClickListenerFourteen();
        onClickListenerFifteen();
    }

    private void init() {
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
        buttonEleven = findViewById(R.id.buttonEleven);
        buttonTwelve = findViewById(R.id.buttonTwelve);
        buttonThirteen = findViewById(R.id.buttonThirteen);
        buttonFourteen = findViewById(R.id.buttonFourteen);
        buttonFifteen = findViewById(R.id.buttonFifteen);

        buttonCancelOne = findViewById(R.id.buttonCancelOne);
        buttonCancelTwo = findViewById(R.id.buttonCancelTwo);
        buttonCancelThree = findViewById(R.id.buttonCancelThree);
        buttonCancelFour = findViewById(R.id.buttonCancelFour);
        buttonCancelFive = findViewById(R.id.buttonCancelFive);
        buttonCancelSix = findViewById(R.id.buttonCancelSix);
        buttonCancelSeven = findViewById(R.id.buttonCancelSeven);
        buttonCancelEight = findViewById(R.id.buttonCancelEight);
        buttonCancelNine = findViewById(R.id.buttonCancelNine);
        buttonCancelTen = findViewById(R.id.buttonCancelTen);
        buttonCancelEleven = findViewById(R.id.buttonCancelEleven);
        buttonCancelTwelve = findViewById(R.id.buttonCancelTwelve);
        buttonCancelThirteen = findViewById(R.id.buttonCancelThirteen);
        buttonCancelFourteen = findViewById(R.id.buttonCancelFourteen);
        buttonCancelFifteen = findViewById(R.id.buttonCancelFifteen);

        textViewProgressOne = findViewById(R.id.textViewProgressOne);
        textViewProgressTwo = findViewById(R.id.textViewProgressTwo);
        textViewProgressThree = findViewById(R.id.textViewProgressThree);
        textViewProgressFour = findViewById(R.id.textViewProgressFour);
        textViewProgressFive = findViewById(R.id.textViewProgressFive);
        textViewProgressSix = findViewById(R.id.textViewProgressSix);
        textViewProgressSeven = findViewById(R.id.textViewProgressSeven);
        textViewProgressEight = findViewById(R.id.textViewProgressEight);
        textViewProgressNine = findViewById(R.id.textViewProgressNine);
        textViewProgressTen = findViewById(R.id.textViewProgressTen);
        textViewProgressEleven = findViewById(R.id.textViewProgressEleven);
        textViewProgressTwelve = findViewById(R.id.textViewProgressTwelve);
        textViewProgressThirteen = findViewById(R.id.textViewProgressThirteen);
        textViewProgressFourteen = findViewById(R.id.textViewProgressFourteen);
        textViewProgressFifteen = findViewById(R.id.textViewProgressFifteen);

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
        progressBarEleven = findViewById(R.id.progressBarEleven);
        progressBarTwelve = findViewById(R.id.progressBarTwelve);
        progressBarThirteen = findViewById(R.id.progressBarThirteen);
        progressBarFourteen = findViewById(R.id.progressBarFourteen);
        progressBarFifteen = findViewById(R.id.progressBarFifteen);
    }

    public void onClickListenerOne() {
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
                    PRDownloader.pause(downloadIdOne);
                    return;
                }

                buttonOne.setEnabled(false);
                progressBarOne.setIndeterminate(true);
                progressBarOne.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
                    PRDownloader.resume(downloadIdOne);
                    return;
                }

                downloadIdOne = PRDownloader.download(URL1, dirPath, "facebook.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarOne.setIndeterminate(false);
                                buttonOne.setEnabled(true);
                                buttonOne.setText(R.string.pause);
                                buttonCancelOne.setEnabled(true);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonOne.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                buttonOne.setText(R.string.start);
                                buttonCancelOne.setEnabled(false);
                                progressBarOne.setProgress(0);
                                textViewProgressOne.setText("");
                                downloadIdOne = 0;
                                progressBarOne.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarOne.setProgress((int) progressPercent);
                                textViewProgressOne.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                                progressBarOne.setIndeterminate(false);
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonOne.setEnabled(false);
                                buttonCancelOne.setEnabled(false);
                                buttonOne.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonOne.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "1", Toast.LENGTH_SHORT).show();
                                textViewProgressOne.setText("");
                                progressBarOne.setProgress(0);
                                downloadIdOne = 0;
                                buttonCancelOne.setEnabled(false);
                                progressBarOne.setIndeterminate(false);
                                buttonOne.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdOne);
            }
        });
    }

    public void onClickListenerTwo() {
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdTwo)) {
                    PRDownloader.pause(downloadIdTwo);
                    return;
                }

                buttonTwo.setEnabled(false);
                progressBarTwo.setIndeterminate(true);
                progressBarTwo.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdTwo)) {
                    PRDownloader.resume(downloadIdTwo);
                    return;
                }
                downloadIdTwo = PRDownloader.download(URL2, dirPath, "wechat.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarTwo.setIndeterminate(false);
                                buttonTwo.setEnabled(true);
                                buttonTwo.setText(R.string.pause);
                                buttonCancelTwo.setEnabled(true);
                                buttonCancelTwo.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonTwo.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdTwo = 0;
                                buttonTwo.setText(R.string.start);
                                buttonCancelTwo.setEnabled(false);
                                progressBarTwo.setProgress(0);
                                textViewProgressTwo.setText("");
                                progressBarTwo.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarTwo.setProgress((int) progressPercent);
                                textViewProgressTwo.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonTwo.setEnabled(false);
                                buttonCancelTwo.setEnabled(false);
                                buttonTwo.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonTwo.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "2", Toast.LENGTH_SHORT).show();
                                textViewProgressTwo.setText("");
                                progressBarTwo.setProgress(0);
                                downloadIdTwo = 0;
                                buttonCancelTwo.setEnabled(false);
                                progressBarTwo.setIndeterminate(false);
                                buttonTwo.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdTwo);
            }
        });
    }

    public void onClickListenerThree() {
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdThree)) {
                    PRDownloader.pause(downloadIdThree);
                    return;
                }

                buttonThree.setEnabled(false);
                progressBarThree.setIndeterminate(true);
                progressBarThree.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdThree)) {
                    PRDownloader.resume(downloadIdThree);
                    return;
                }
                downloadIdThree = PRDownloader.download(URL3, dirPath, "instagram.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarThree.setIndeterminate(false);
                                buttonThree.setEnabled(true);
                                buttonThree.setText(R.string.pause);
                                buttonCancelThree.setEnabled(true);
                                buttonCancelThree.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonThree.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdThree = 0;
                                buttonThree.setText(R.string.start);
                                buttonCancelThree.setEnabled(false);
                                progressBarThree.setProgress(0);
                                textViewProgressThree.setText("");
                                progressBarThree.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarThree.setProgress((int) progressPercent);
                                textViewProgressThree.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonThree.setEnabled(false);
                                buttonCancelThree.setEnabled(false);
                                buttonThree.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonThree.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "3", Toast.LENGTH_SHORT).show();
                                textViewProgressThree.setText("");
                                progressBarThree.setProgress(0);
                                downloadIdThree = 0;
                                buttonCancelThree.setEnabled(false);
                                progressBarThree.setIndeterminate(false);
                                buttonThree.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdThree);
            }
        });
    }

    public void onClickListenerFour() {
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdFour)) {
                    PRDownloader.pause(downloadIdFour);
                    return;
                }

                buttonFour.setEnabled(false);
                progressBarFour.setIndeterminate(true);
                progressBarFour.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdFour)) {
                    PRDownloader.resume(downloadIdFour);
                    return;
                }
                downloadIdFour = PRDownloader.download(URL4, dirPath, "youtube.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarFour.setIndeterminate(false);
                                buttonFour.setEnabled(true);
                                buttonFour.setText(R.string.pause);
                                buttonCancelFour.setEnabled(true);
                                buttonCancelFour.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonFour.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdFour = 0;
                                buttonFour.setText(R.string.start);
                                buttonCancelFour.setEnabled(false);
                                progressBarFour.setProgress(0);
                                textViewProgressFour.setText("");
                                progressBarFour.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarFour.setProgress((int) progressPercent);
                                textViewProgressFour.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFour.setEnabled(false);
                                buttonCancelFour.setEnabled(false);
                                buttonFour.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFour.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "4", Toast.LENGTH_SHORT).show();
                                textViewProgressFour.setText("");
                                progressBarFour.setProgress(0);
                                downloadIdFour = 0;
                                buttonCancelFour.setEnabled(false);
                                progressBarFour.setIndeterminate(false);
                                buttonFour.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdFour);
            }
        });
    }

    public void onClickListenerFive() {
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdFive)) {
                    PRDownloader.pause(downloadIdFive);
                    return;
                }

                buttonFive.setEnabled(false);
                progressBarFive.setIndeterminate(true);
                progressBarFive.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdFive)) {
                    PRDownloader.resume(downloadIdFive);
                    return;
                }
                downloadIdFive = PRDownloader.download(URL5, dirPath, "screenrecorder.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarFive.setIndeterminate(false);
                                buttonFive.setEnabled(true);
                                buttonFive.setText(R.string.pause);
                                buttonCancelFive.setEnabled(true);
                                buttonCancelFive.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonFive.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdFive = 0;
                                buttonFive.setText(R.string.start);
                                buttonCancelFive.setEnabled(false);
                                progressBarFive.setProgress(0);
                                textViewProgressFive.setText("");
                                progressBarFive.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarFive.setProgress((int) progressPercent);
                                textViewProgressFive.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFive.setEnabled(false);
                                buttonCancelFive.setEnabled(false);
                                buttonFive.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFive.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "5", Toast.LENGTH_SHORT).show();
                                textViewProgressFive.setText("");
                                progressBarFive.setProgress(0);
                                downloadIdFive = 0;
                                buttonCancelFive.setEnabled(false);
                                progressBarFive.setIndeterminate(false);
                                buttonFive.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdFive);
            }
        });

    }

    public void onClickListenerSix() {
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdSix)) {
                    PRDownloader.pause(downloadIdSix);
                    return;
                }

                buttonSix.setEnabled(false);
                progressBarSix.setIndeterminate(true);
                progressBarSix.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdSix)) {
                    PRDownloader.resume(downloadIdSix);
                    return;
                }
                downloadIdSix = PRDownloader.download(URL6, dirPath, "callrecorder.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarSix.setIndeterminate(false);
                                buttonSix.setEnabled(true);
                                buttonSix.setText(R.string.pause);
                                buttonCancelSix.setEnabled(true);
                                buttonCancelSix.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonSix.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdSix = 0;
                                buttonSix.setText(R.string.start);
                                buttonCancelSix.setEnabled(false);
                                progressBarSix.setProgress(0);
                                textViewProgressSix.setText("");
                                progressBarSix.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarSix.setProgress((int) progressPercent);
                                textViewProgressSix.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonSix.setEnabled(false);
                                buttonCancelSix.setEnabled(false);
                                buttonSix.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonSix.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "6", Toast.LENGTH_SHORT).show();
                                textViewProgressSix.setText("");
                                progressBarSix.setProgress(0);
                                downloadIdSix = 0;
                                buttonCancelSix.setEnabled(false);
                                progressBarSix.setIndeterminate(false);
                                buttonSix.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdSix);
            }
        });

    }

    public void onClickListenerSeven() {
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdSeven)) {
                    PRDownloader.pause(downloadIdSeven);
                    return;
                }

                buttonSeven.setEnabled(false);
                progressBarSeven.setIndeterminate(true);
                progressBarSeven.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdSeven)) {
                    PRDownloader.resume(downloadIdSeven);
                    return;
                }
                downloadIdSeven = PRDownloader.download(URL7, dirPath, "vlc.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarSeven.setIndeterminate(false);
                                buttonSeven.setEnabled(true);
                                buttonSeven.setText(R.string.pause);
                                buttonCancelSeven.setEnabled(true);
                                buttonCancelSeven.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonSeven.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdSeven = 0;
                                buttonSeven.setText(R.string.start);
                                buttonCancelSeven.setEnabled(false);
                                progressBarSeven.setProgress(0);
                                textViewProgressSeven.setText("");
                                progressBarSeven.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarSeven.setProgress((int) progressPercent);
                                textViewProgressSeven.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonSeven.setEnabled(false);
                                buttonCancelSeven.setEnabled(false);
                                buttonSeven.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonSeven.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "7", Toast.LENGTH_SHORT).show();
                                textViewProgressSeven.setText("");
                                progressBarSeven.setProgress(0);
                                downloadIdSeven = 0;
                                buttonCancelSeven.setEnabled(false);
                                progressBarSeven.setIndeterminate(false);
                                buttonSeven.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdSeven);
            }
        });

    }

    public void onClickListenerEight() {
        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdEight)) {
                    PRDownloader.pause(downloadIdEight);
                    return;
                }

                buttonEight.setEnabled(false);
                progressBarEight.setIndeterminate(true);
                progressBarEight.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdEight)) {
                    PRDownloader.resume(downloadIdEight);
                    return;
                }
                downloadIdEight = PRDownloader.download(URL8, dirPath, "evernote.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarEight.setIndeterminate(false);
                                buttonEight.setEnabled(true);
                                buttonEight.setText(R.string.pause);
                                buttonCancelEight.setEnabled(true);
                                buttonCancelEight.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonEight.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdEight = 0;
                                buttonEight.setText(R.string.start);
                                buttonCancelEight.setEnabled(false);
                                progressBarEight.setProgress(0);
                                textViewProgressEight.setText("");
                                progressBarEight.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarEight.setProgress((int) progressPercent);
                                textViewProgressEight.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonEight.setEnabled(false);
                                buttonCancelEight.setEnabled(false);
                                buttonEight.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonEight.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "8", Toast.LENGTH_SHORT).show();
                                textViewProgressEight.setText("");
                                progressBarEight.setProgress(0);
                                downloadIdEight = 0;
                                buttonCancelEight.setEnabled(false);
                                progressBarEight.setIndeterminate(false);
                                buttonEight.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdEight);
            }
        });
    }

    public void onClickListenerNine() {
        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdNine)) {
                    PRDownloader.pause(downloadIdNine);
                    return;
                }

                buttonNine.setEnabled(false);
                progressBarNine.setIndeterminate(true);
                progressBarNine.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdNine)) {
                    PRDownloader.resume(downloadIdNine);
                    return;
                }
                downloadIdNine = PRDownloader.download(URL9, dirPath, "ucbrowser.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarNine.setIndeterminate(false);
                                buttonNine.setEnabled(true);
                                buttonNine.setText(R.string.pause);
                                buttonCancelNine.setEnabled(true);
                                buttonCancelNine.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonNine.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdNine = 0;
                                buttonNine.setText(R.string.start);
                                buttonCancelNine.setEnabled(false);
                                progressBarNine.setProgress(0);
                                textViewProgressNine.setText("");
                                progressBarNine.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarNine.setProgress((int) progressPercent);
                                textViewProgressNine.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonNine.setEnabled(false);
                                buttonCancelNine.setEnabled(false);
                                buttonNine.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonNine.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "9", Toast.LENGTH_SHORT).show();
                                textViewProgressNine.setText("");
                                progressBarNine.setProgress(0);
                                downloadIdNine = 0;
                                buttonCancelNine.setEnabled(false);
                                progressBarNine.setIndeterminate(false);
                                buttonNine.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdNine);
            }
        });
    }

    public void onClickListenerTen() {
        buttonTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdTen)) {
                    PRDownloader.pause(downloadIdTen);
                    return;
                }

                buttonTen.setEnabled(false);
                progressBarTen.setIndeterminate(true);
                progressBarTen.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdTen)) {
                    PRDownloader.resume(downloadIdTen);
                    return;
                }
                downloadIdTen = PRDownloader.download(URL10, dirPath, "snapchat.apk")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarTen.setIndeterminate(false);
                                buttonTen.setEnabled(true);
                                buttonTen.setText(R.string.pause);
                                buttonCancelTen.setEnabled(true);
                                buttonCancelTen.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonTen.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdTen = 0;
                                buttonTen.setText(R.string.start);
                                buttonCancelTen.setEnabled(false);
                                progressBarTen.setProgress(0);
                                textViewProgressTen.setText("");
                                progressBarTen.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarTen.setProgress((int) progressPercent);
                                textViewProgressTen.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonTen.setEnabled(false);
                                buttonCancelTen.setEnabled(false);
                                buttonTen.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonTen.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "10", Toast.LENGTH_SHORT).show();
                                textViewProgressTen.setText("");
                                progressBarTen.setProgress(0);
                                downloadIdTen = 0;
                                buttonCancelTen.setEnabled(false);
                                progressBarTen.setIndeterminate(false);
                                buttonTen.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdTen);
            }
        });
    }

    public void onClickListenerEleven() {
        buttonEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdEleven)) {
                    PRDownloader.pause(downloadIdEleven);
                    return;
                }

                buttonEleven.setEnabled(false);
                progressBarEleven.setIndeterminate(true);
                progressBarEleven.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdEleven)) {
                    PRDownloader.resume(downloadIdEleven);
                    return;
                }
                downloadIdEleven = PRDownloader.download(URL11, dirPath, "BigBuckBunny.m4v")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarEleven.setIndeterminate(false);
                                buttonEleven.setEnabled(true);
                                buttonEleven.setText(R.string.pause);
                                buttonCancelEleven.setEnabled(true);
                                buttonCancelEleven.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonEleven.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdEleven = 0;
                                buttonEleven.setText(R.string.start);
                                buttonCancelEleven.setEnabled(false);
                                progressBarEleven.setProgress(0);
                                textViewProgressEleven.setText("");
                                progressBarEleven.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarEleven.setProgress((int) progressPercent);
                                textViewProgressEleven.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonEleven.setEnabled(false);
                                buttonCancelEleven.setEnabled(false);
                                buttonEleven.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonEleven.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "11", Toast.LENGTH_SHORT).show();
                                textViewProgressEleven.setText("");
                                progressBarEleven.setProgress(0);
                                downloadIdEleven = 0;
                                buttonCancelEleven.setEnabled(false);
                                progressBarEleven.setIndeterminate(false);
                                buttonEleven.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdEleven);
            }
        });
    }

    public void onClickListenerTwelve() {
        buttonTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdTwelve)) {
                    PRDownloader.pause(downloadIdTwelve);
                    return;
                }

                buttonTwelve.setEnabled(false);
                progressBarTwelve.setIndeterminate(true);
                progressBarTwelve.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdTwelve)) {
                    PRDownloader.resume(downloadIdTwelve);
                    return;
                }
                downloadIdTwelve = PRDownloader.download(URL12, dirPath, "harry-porter.pdf")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarTwelve.setIndeterminate(false);
                                buttonTwelve.setEnabled(true);
                                buttonTwelve.setText(R.string.pause);
                                buttonCancelTwelve.setEnabled(true);
                                buttonCancelTwelve.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonTwelve.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdTwelve = 0;
                                buttonTwelve.setText(R.string.start);
                                buttonCancelTwelve.setEnabled(false);
                                progressBarTwelve.setProgress(0);
                                textViewProgressTwelve.setText("");
                                progressBarTwelve.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarTwelve.setProgress((int) progressPercent);
                                textViewProgressTwelve.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonTwelve.setEnabled(false);
                                buttonCancelTwelve.setEnabled(false);
                                buttonTwelve.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonTwelve.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "12", Toast.LENGTH_SHORT).show();
                                textViewProgressTwelve.setText("");
                                progressBarTwelve.setProgress(0);
                                downloadIdTwelve = 0;
                                buttonCancelTwelve.setEnabled(false);
                                progressBarTwelve.setIndeterminate(false);
                                buttonTwelve.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdTwelve);
            }
        });
    }

    public void onClickListenerThirteen() {
        buttonThirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdThirteen)) {
                    PRDownloader.pause(downloadIdThirteen);
                    return;
                }

                buttonThirteen.setEnabled(false);
                progressBarThirteen.setIndeterminate(true);
                progressBarThirteen.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdThirteen)) {
                    PRDownloader.resume(downloadIdThirteen);
                    return;
                }
                downloadIdThirteen = PRDownloader.download(URL13, dirPath, "giphy.gif")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarThirteen.setIndeterminate(false);
                                buttonThirteen.setEnabled(true);
                                buttonThirteen.setText(R.string.pause);
                                buttonCancelThirteen.setEnabled(true);
                                buttonCancelThirteen.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonThirteen.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdThirteen = 0;
                                buttonThirteen.setText(R.string.start);
                                buttonCancelThirteen.setEnabled(false);
                                progressBarThirteen.setProgress(0);
                                textViewProgressThirteen.setText("");
                                progressBarThirteen.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarThirteen.setProgress((int) progressPercent);
                                textViewProgressThirteen.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonThirteen.setEnabled(false);
                                buttonCancelThirteen.setEnabled(false);
                                buttonThirteen.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonThirteen.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "13", Toast.LENGTH_SHORT).show();
                                textViewProgressThirteen.setText("");
                                progressBarThirteen.setProgress(0);
                                downloadIdThirteen = 0;
                                buttonCancelThirteen.setEnabled(false);
                                progressBarThirteen.setIndeterminate(false);
                                buttonThirteen.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelThirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdThirteen);
            }
        });

    }

    public void onClickListenerFourteen() {
        buttonFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdFourteen)) {
                    PRDownloader.pause(downloadIdFourteen);
                    return;
                }

                buttonFourteen.setEnabled(false);
                progressBarFourteen.setIndeterminate(true);
                progressBarFourteen.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdFourteen)) {
                    PRDownloader.resume(downloadIdFourteen);
                    return;
                }
                downloadIdFourteen = PRDownloader.download(URL14, dirPath, "pexels-3195394.mp4")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarFourteen.setIndeterminate(false);
                                buttonFourteen.setEnabled(true);
                                buttonFourteen.setText(R.string.pause);
                                buttonCancelFourteen.setEnabled(true);
                                buttonCancelFourteen.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonFourteen.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdFourteen = 0;
                                buttonFourteen.setText(R.string.start);
                                buttonCancelFourteen.setEnabled(false);
                                progressBarFourteen.setProgress(0);
                                textViewProgressFourteen.setText("");
                                progressBarFourteen.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarFourteen.setProgress((int) progressPercent);
                                textViewProgressFourteen.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFourteen.setEnabled(false);
                                buttonCancelFourteen.setEnabled(false);
                                buttonFourteen.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFourteen.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "14", Toast.LENGTH_SHORT).show();
                                textViewProgressFourteen.setText("");
                                progressBarFourteen.setProgress(0);
                                downloadIdFourteen = 0;
                                buttonCancelFourteen.setEnabled(false);
                                progressBarFourteen.setIndeterminate(false);
                                buttonFourteen.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdFourteen);
            }
        });

    }

    public void onClickListenerFifteen() {
        buttonFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdFifteen)) {
                    PRDownloader.pause(downloadIdFifteen);
                    return;
                }

                buttonFifteen.setEnabled(false);
                progressBarFifteen.setIndeterminate(true);
                progressBarFifteen.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdFifteen)) {
                    PRDownloader.resume(downloadIdFifteen);
                    return;
                }
                downloadIdFifteen = PRDownloader.download(URL15, dirPath, "pexels-3209828.mp4")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarFifteen.setIndeterminate(false);
                                buttonFifteen.setEnabled(true);
                                buttonFifteen.setText(R.string.pause);
                                buttonCancelFifteen.setEnabled(true);
                                buttonCancelFifteen.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonFifteen.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdFifteen = 0;
                                buttonFifteen.setText(R.string.start);
                                buttonCancelFifteen.setEnabled(false);
                                progressBarFifteen.setProgress(0);
                                textViewProgressFifteen.setText("");
                                progressBarFifteen.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarFifteen.setProgress((int) progressPercent);
                                textViewProgressFifteen.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFifteen.setEnabled(false);
                                buttonCancelFifteen.setEnabled(false);
                                buttonFifteen.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFifteen.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "15", Toast.LENGTH_SHORT).show();
                                textViewProgressFifteen.setText("");
                                progressBarFifteen.setProgress(0);
                                downloadIdFifteen = 0;
                                buttonCancelFifteen.setEnabled(false);
                                progressBarFifteen.setIndeterminate(false);
                                buttonFifteen.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdFifteen);
            }
        });
    }

}

