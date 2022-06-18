package com.example.mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    public final static Api api =  new Api();
    private TextureView mTextureView;
    private static final int REQUEST_CAMERA_PERMISSION = 1234;
    private CameraDevice mCamera;
    private Size mPreviewSize;
    private CameraCaptureSession mCameraSession;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private ProgressBar progressBar;
    ImageButton takePhoto, btn_setting;
    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageView imageView;

    /**
     * Camera state: Showing camera preview.
     */
    private static final int STATE_PREVIEW = 0;

    /**
     * Camera state: Waiting for the focus to be locked.
     */
    private static final int STATE_WAITING_LOCK = 1;

    /**
     * Camera state: Waiting for the exposure to be precapture state.
     */
    private static final int STATE_WAITING_PRECAPTURE = 2;

    /**
     * Camera state: Waiting for the exposure state to be something other than precapture.
     */
    private static final int STATE_WAITING_NON_PRECAPTURE = 3;

    /**
     * Camera state: Picture was taken.
     */
    private static final int STATE_PICTURE_TAKEN = 4;

    private int mState = STATE_PREVIEW;

    @Override
    public void onBackPressed() {
        finishAffinity(); // 해당 어플리케이션의 루트 액티비티를 종료시키는 것 입니다.
        System.runFinalization(); // 현재 구동중인 쓰레드가 다 종료되면 종료시키는 것 입니다.
        System.exit(0); // 현재의 액티비티를 종료시키는 것 입니다.
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            return;
        }
        progressBar = (ProgressBar) findViewById(R.id.progress);
        takePhoto = (ImageButton) findViewById(R.id.btn_camera);
        btn_setting = (ImageButton) findViewById(R.id.btn_setting);
        imageView = (ImageView) findViewById(R.id.imageView);
        initTextureView();

        //사진 찍기 버튼 눌르면 takePicture 실행
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        //세팅 버튼 누르면 세팅창으로
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

    }

    //촬영 화면을 출력할 텍스처 뷰설정
    private void initTextureView() {
        mTextureView = (TextureView) findViewById(R.id.textureView);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                openCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    //텍스처 뷰에 카메라를 연결하기 위해 카메라 정보 접근
    private void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraIdArray = manager.getCameraIdList();


            String oneCameraID = cameraIdArray[0];

            CameraCharacteristics cameraCharacter = manager.getCameraCharacteristics(oneCameraID);

            StreamConfigurationMap map = cameraCharacter.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            Size[] sizesForStream = map.getOutputSizes(SurfaceTexture.class);

            mPreviewSize = sizesForStream[0];

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            manager.openCamera(oneCameraID, new CameraDevice.StateCallback() {
                @Override
                public void onOpened(@NonNull CameraDevice camera) {
                    mCamera = camera;
                    showCameraPreview();
                }

                @Override
                public void onDisconnected(@NonNull CameraDevice camera) {
                    mCamera.close();
                }

                @Override
                public void onError(@NonNull CameraDevice camera, int error) {
                    mCamera.close();
                    mCamera = null;
                }
            }, null);

        } catch (CameraAccessException e) {
            Log.d("ddd", e.toString());
        }

    }

    //접근한 카메라정보를 가지고 화면출력
    private void showCameraPreview() {
        try {
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            Surface textureViewSurface = new Surface(texture);

            mCaptureRequestBuilder = mCamera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mCaptureRequestBuilder.addTarget(textureViewSurface);
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);

            mCamera.createCaptureSession(Arrays.asList(textureViewSurface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    mCameraSession = cameraCaptureSession;
                    updatePreview();
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            }, null);
        } catch (CameraAccessException e) {
            Log.d("ddd", e.toString(), e);
        }
    }

    private void updatePreview() {
        try {
            mCameraSession.setRepeatingRequest(mCaptureRequestBuilder.build(), null, null);
        } catch (Exception e) {
            Log.d("hell", e.toString());
        }
    }
    
    //화면갱신
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            openCamera();
        }
    }

    //사진 촬영
    private void takePicture() {
        lockFocus();

    }

    //촬영 버튼 클릭시 화면 멈춤
    private void lockFocus() {
        mTextureView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        mTextureView.getBitmap().compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] b = byteArrayOutputStream.toByteArray();
        Bitmap bit = BitmapFactory.decodeByteArray(b, 0, b.length);
        imageView.setImageBitmap(bit);
        imageView.setVisibility(View.VISIBLE);

        //네트워크 사용시 쓰레드 처리 않해주면 오류 발생 따라서 api에 네트워크 부분 오류 발생으로 쓰레드 처리해줌
            new Thread(() -> {
                try {
                    api.Api_loader();
                } catch (Exception e) {
                    Log.d("오류",e.toString());
                }
            }).start();
            
            //헨들러를 이용해서일정 시간 후에 화면 이동
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), InfoLayout.class);
                intent.putExtra("ai", api.code);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }


}
