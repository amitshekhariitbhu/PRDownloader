<p align="center">
<img alt="PRDownloader" src=https://raw.githubusercontent.com/amitshekhariitbhu/PRDownloader/master/assets/prdownloader.png />
</p>

# PRDownloader - A file downloader library for Android with pause and resume support

## Sample Download
<img src=https://raw.githubusercontent.com/amitshekhariitbhu/PRDownloader/master/assets/sample_download.png width=360 height=640 />

### Overview of PRDownloader library
* PRDownloader can be used to download any type of files like image, video, pdf, apk and etc.
* This file downloader library supports pause and resume while downloading a file.
* Supports large file download.
* This downloader library has a simple interface to make download request.
* We can check if the status of downloading with the given download Id.
* PRDownloader gives callbacks for everything like onProgress, onCancel, onStart, onError and etc while downloading a file.
* Supports proper request canceling.
* Many requests can be made in parallel.
* All types of customization are possible.

## About me

Hi, I am Amit Shekhar, Founder @ [Outcome School](https://outcomeschool.com) • IIT 2010-14 • I have taught and mentored many developers, and their efforts landed them high-paying tech jobs, helped many tech companies in solving their unique problems, and created many open-source libraries being used by top companies. I am passionate about sharing knowledge through open-source, blogs, and videos.

### Follow Amit Shekhar

- [X/Twitter](https://twitter.com/amitiitbhu)
- [LinkedIn](https://www.linkedin.com/in/amit-shekhar-iitbhu)
- [GitHub](https://github.com/amitshekhariitbhu)

### Follow Outcome School

- [YouTube](https://youtube.com/@OutcomeSchool)
- [X/Twitter](https://x.com/outcome_school)
- [LinkedIn](https://www.linkedin.com/company/outcomeschool)
- [GitHub](http://github.com/OutcomeSchool)

## I teach at Outcome School

- AI and Machine Learning
- Android

Join Outcome School and get a high-paying tech job: [Outcome School](https://outcomeschool.com)

## Using PRDownloader Library in your Android application

Add this in your `settings.gradle`:
```groovy
maven { url 'https://jitpack.io' }
```

If you are using `settings.gradle.kts`, add the following:
```kotlin
maven { setUrl("https://jitpack.io") }
```

Add this in your `build.gradle`
```groovy
implementation 'com.github.amitshekhariitbhu:PRDownloader:1.0.2'
```

If you are using `build.gradle.kts`, add the following:
```kotlin
implementation("com.github.amitshekhariitbhu:PRDownloader:1.0.2")
```

Do not forget to add internet permission in manifest if already not present
```xml
<uses-permission android:name="android.permission.INTERNET" />
```
Then initialize it in onCreate() Method of application class :
```java
PRDownloader.initialize(getApplicationContext());
```
Initializing it with some customization
```java
// Enabling database for resume support even after the application is killed:
PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
PRDownloader.initialize(getApplicationContext(), config);

// Setting timeout globally for the download network requests:
PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
PRDownloader.initialize(getApplicationContext(), config); 
```

### Make a download request
```java
int downloadId = PRDownloader.download(url, dirPath, fileName)
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                               
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                               
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                               
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                               
                            }

                            @Override
                            public void onError(Error error) {
                               
                            }
                        });            
```

### Pause a download request
```java
PRDownloader.pause(downloadId);
```

### Resume a download request
```java
PRDownloader.resume(downloadId);
```

### Cancel a download request
```java
// Cancel with the download id
PRDownloader.cancel(downloadId);
// The tag can be set to any request and then can be used to cancel the request
PRDownloader.cancel(TAG);
// Cancel all the requests
PRDownloader.cancelAll();
```

### Status of a download request
```java
Status status = PRDownloader.getStatus(downloadId);
```

### Clean up resumed files if database enabled
```java
// Method to clean up temporary resumed files which is older than the given day
PRDownloader.cleanUp(days);
```
### TODO
* Integration with other libraries like OkHttp, RxJava
* Test Cases
* And of course many many features and bug fixes

## [Outcome School Blog](https://outcomeschool.com/blog) - High-quality content to learn Android concepts.

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

### License
```
   Copyright (C) 2024 Amit Shekhar

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to PRDownloader
All pull requests are welcome, make sure to follow the [contribution guidelines](CONTRIBUTING.md)
when you submit pull request.
