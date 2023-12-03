package com.imagetotext;

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
  TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

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
      recognizer.process(InputImage.fromFilePath(getReactApplicationContext(), Uri.parse(path))).addOnSuccessListener(new OnSuccessListener<Text>() {
        @Override
        public void onSuccess(Text visionText) {
          StringBuilder sBlockText = new StringBuilder();
          StringBuilder sLineText = new StringBuilder();
          StringBuilder sElementText = new StringBuilder();
          StringBuilder sSymboleText = new StringBuilder();
          for (Text.TextBlock block : visionText.getTextBlocks()) {
            String blockText = block.getText();
            sBlockText.append(blockText);
            for (Text.Line line : block.getLines()) {
              String lineText = line.getText();
              sLineText.append(lineText);
              for (Text.Element element : line.getElements()) {
                String elementText = element.getText();
                sElementText.append(elementText);
                for (Text.Symbol symbol : element.getSymbols()) {
                  String symbolText = symbol.getText();
                  sSymboleText.append(symbolText);
                }
              }
            }
          }

          map.putString("blockText", sBlockText.toString());
          map.putString("lineText", sLineText.toString());
          map.putString("elementText", sElementText.toString());
          map.putString("symboleText", sSymboleText.toString());
          promise.resolve(map);
        }
      }).addOnFailureListener(new OnFailureListener() {
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
