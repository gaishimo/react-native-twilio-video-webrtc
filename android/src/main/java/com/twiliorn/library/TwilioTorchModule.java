package com.twiliorn.library;

import android.hardware.Camera;
import android.os.Build;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.twilio.video.CameraCapturer;

@ReactModule(name = TwilioTorchModule.NAME)
public class TwilioTorchModule extends ReactContextBaseJavaModule {
    public static final String NAME = "TwilioTorch";

    public TwilioTorchModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    private Camera getCamera() {
        CameraCapturer capturer = CustomTwilioVideoView.getCameraCapturer();
        if (capturer == null) return null;
        try {
            java.lang.reflect.Field field = CameraCapturer.class.getDeclaredField("camera");
            field.setAccessible(true);
            return (Camera) field.get(capturer);
        } catch (Exception e) {
            return null;
        }
    }

    @ReactMethod
    public void enableTorch(Promise promise) {
        Camera camera = getCamera();
        if (camera == null) {
            promise.reject("UNAVAILABLE", "Camera not available");
            return;
        }
        try {
            Camera.Parameters params = camera.getParameters();
            if (params == null) {
                promise.reject("FAILED", "params null");
                return;
            }
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject("FAILED", e.getMessage(), e);
        }
    }

    @ReactMethod
    public void disableTorch(Promise promise) {
        Camera camera = getCamera();
        if (camera == null) {
            promise.reject("UNAVAILABLE", "Camera not available");
            return;
        }
        try {
            Camera.Parameters params = camera.getParameters();
            if (params == null) {
                promise.reject("FAILED", "params null");
                return;
            }
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject("FAILED", e.getMessage(), e);
        }
    }
}
