package movies.sweng888.psu.edu.prelabassignment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextViewMsg;
    private Button mButtonCall;
    private Button mtakePictureButton;
    private ImageView mImageView;
    private ImageView mImageView1;
    private Uri mfile;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private String name;
    private String phone;
    private String data;
    private String TAG;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextViewMsg = (TextView) findViewById(R.id.second_text_view_msg);
        mtakePictureButton = (Button) findViewById(R.id.button_camera);
        mtakePictureButton.setOnClickListener(this);

        String msg = mTextViewMsg.getText().toString();
    }

    public void onClick(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_IMAGE_CAPTURE);
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, MY_CAMERA_PERMISSION_CODE);
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }

    }
}
