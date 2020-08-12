package com.ahmed.customapp;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScannerView;


public class QRCodeActivity extends AppCompatActivity {


    private static final String TAG = "QRCodeActivity";

    private ImageView qr_ImageView;
    private Button btn_generate_QR;
    private Button btn_save_QR;
    private EditText editText_qr_url;
    private EditText editText_qr_content;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code);

        initViews();

        onViewsClicked();
        CodeScannerView scannerView = null;


       /* CodeScanner codeScanner = new CodeScanner(this, scannerView);
        codeScanner.setDecodeCallback(result -> {
            result.
        });*/

    }

    private void initViews() {
        qr_ImageView = findViewById(R.id.qr_Image);
        btn_generate_QR = findViewById(R.id.btn_generate_QR);
        btn_save_QR = findViewById(R.id.btn_save_QR);
        editText_qr_url = findViewById(R.id.editText_qr_url);
        editText_qr_content = findViewById(R.id.editText_qr_content);
    }


    private void onViewsClicked() {
        //https://stg.zawia.app/uploads/5f2beb714db8e.jpg
        editText_qr_url.setText("https://stg.zawia.app/uploads/5f2beb714db8e.jpg");
        btn_generate_QR.setOnClickListener(v -> {
            String url  = editText_qr_url.getText().toString();
            String content  = editText_qr_content.getText().toString();
            String userName  = "Android Test";
            String userID  = "123456";
            String discount  = "20%";
            String inputValue;
            inputValue = "UserID ==>" +  userID  + "\n" +
                         "UserName ==>" + userName +"\n" +
                         "Url ==> " + url + "\n" +
                         "Content ==> " + content + "\n" +
                         "DiscountValue ==> " + discount;
            buildQRCode(inputValue);
        });

        btn_save_QR.setOnClickListener(v -> {
           /* String inputValue = editText_qr.getText().toString();
            save_QR(inputValue);*/
        });

    }


    private void buildQRCode(String inputValue) {
        Bundle bundle = new Bundle();
        bundle.putString("MyKey","MyValue");

        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder(inputValue, bundle, QRGContents.Type.TEXT, 500);
       /* qrgEncoder.setColorBlack(Color.RED);
        qrgEncoder.setColorWhite(Color.BLUE);*/

        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            qr_ImageView.setImageBitmap(bitmap);


        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private void save_QR(String inputValue) {
        String savePath = "/storage/emulated/0/DCIM/Camera";
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            try {
                boolean save = new QRGSaver().save(savePath, inputValue, bitmap, QRGContents.ImageType.IMAGE_JPEG);
                String result = save ? "Image Saved" : "Image Not Saved";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                editText_qr_content.setText(null);
                editText_qr_url.setText(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

}
