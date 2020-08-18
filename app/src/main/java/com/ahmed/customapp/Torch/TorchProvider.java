package com.ahmed.customapp.Torch;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;

public class TorchProvider {


    public Builder Builder(Context context) {
        return new Builder(context);
    }

    public static class Builder {
        CameraManager camManager = null;
        String cameraId = null;
        public Builder(Context context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                camManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
                cameraId = null;
                try {
                    cameraId = camManager.getCameraIdList()[0];
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
            this.context = context;
        }

        private Context context;
        private int intervalTime = 500;
        private int repeatTimes = 5;
        private int waitFor = -1;
        private boolean infinite = false;


        /**
         * This method defines the lag between every ON and OFF
         * */
        public Builder intervalTime(int timeInMilliseconds) {
            this.intervalTime = timeInMilliseconds;
            return this;
        }

        /**
         * This method defines the num of repeat time
         * */
        public Builder repeatTimes(int repeatTimes) {
            this.repeatTimes = repeatTimes;
            return this;
        }

        /**
         * This method turn the torch on for ever
         * */
        public Builder infinite(boolean infinite) {
            this.infinite = infinite;
            return this;
        }

        /**
         * This method wait for timeInMilliseconds then turn the torch off
         * */
        public Builder waitFor(int waitForInMilliseconds){
            this.waitFor = waitForInMilliseconds;
            return this;
        }


        public void build() {

            if (infinite) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        turnOnTorch();
                    }
                }).start();
                return;
            }

            if (waitFor != -1){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        turnOnTorch();
                        try {
                            Thread.sleep(waitFor);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        turnOFFTorch();

                    }
                }).start();
                return;
            }


            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < repeatTimes; i++) {
                        turnOnTorch();
                        try {
                            Thread.sleep(intervalTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        turnOFFTorch();
                    }
                }
            }).start();


        }


        private void turnOnTorch() {
            if (camManager != null && cameraId != null){
                try {
                    camManager.setTorchMode(cameraId, true);   //Turn ON
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        private void turnOFFTorch() {
            if (camManager != null && cameraId != null){
                try {
                    camManager.setTorchMode(cameraId, false);   //Turn ON
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
