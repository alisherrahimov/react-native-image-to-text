package com.imagetotext;

import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.IOException;

@ReactModule(name = ImageToTextModule.NAME)
public class ImageToTextModule extends ReactContextBaseJavaModule {
  public static final String NAME = "ImageToText";
  TextRecognizer recognizer =
    TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

  public ImageToTextModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void imageToText(String path, Promise promise) {
    WritableMap map = new WritableNativeMap();
    try {
      recognizer.process(InputImage.fromFilePath(getReactApplicationContext(), Uri.parse("file://" + path)))
        .addOnSuccessListener(new OnSuccessListener<Text>() {
          @Override
          public void onSuccess(Text visionText) {
            StringBuilder news = new StringBuilder();

            for (Text.TextBlock block : visionText.getTextBlocks()) {
              Rect boundingBox = block.getBoundingBox();
              Point[] cornerPoints = block.getCornerPoints();
              String text = block.getText();
              news.append(text).append("\n");
            }
            map.putString("data", news.toString());
            promise.resolve(map);
          }
        })
        .addOnFailureListener(
          new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
              // Task failed with an exception
              // ...
              promise.reject("I do not read this image!");
            }
          });
    } catch (IOException e) {
      promise.reject(e);
      e.printStackTrace();
    }
  }
}
