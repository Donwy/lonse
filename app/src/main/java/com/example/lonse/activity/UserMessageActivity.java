package com.example.lonse.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.lonse.R;
import com.example.lonse.util.AppSettingPopup;
import com.example.lonse.util.JumpPermissionUtils;
import com.example.lonse.util.MyPermissionUtils;
import com.example.lonse.util.StringCache;
import com.example.lonse.view.PicChangePopup;

import java.io.File;

import static com.example.lonse.util.MyPermissionUtils.PERMISSION_CAMERA;
import static com.example.lonse.util.MyPermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE;

/**
 * @author Donvy_y
 * @date 2019/9/3
 */
public class UserMessageActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "UserMessageActivity";
    private ImageView img;
    private EditText editTextUserName;
    private EditText editTextCredentialsNumber;
    private EditText editTextPhoneNumber;
    private PicChangePopup picChangePopup;
    private AppSettingPopup setPopup;
    private File tempFile = null;
    private String imgUri = "";


    public static final int WRITE_EXTERNAL = 1;
    public static final int CAMERA_PERMISSION = 2;
    public static final int IMAGE_PREW = 3;
    public static final int CAMERA_PREW = 4;
    public static final int GET_IMAGE_BY_CAMERA = 5001;
    public static final int GET_IMAGE_FROM_PHONE = 5002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);

        img = findViewById(R.id.img_submit);
        editTextUserName = findViewById(R.id.edit_username);
        editTextCredentialsNumber = findViewById(R.id.edit_credentials_number);
        editTextPhoneNumber = findViewById(R.id.edit_phone_number);


        img.setOnClickListener(this);
        editTextUserName.setOnClickListener(this);

        editTextUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                EditText et = (EditText) v;
                if (!hasFocus) {// 失去焦点
                    et.setHint(et.getTag().toString());
                } else {
                    String hint = et.getHint().toString();
                    et.setTag(hint);//保存预设字
                    et.setHint(null);
                }
            }
        });

        editTextCredentialsNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                EditText et = (EditText) v;
                if (!hasFocus) {// 失去焦点
                    et.setHint(et.getTag().toString());
                } else {
                    String hint = et.getHint().toString();
                    et.setTag(hint);//保存预设字
                    et.setHint(null);
                }
            }
        });

        editTextPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                EditText et = (EditText) v;
                if (!hasFocus) {// 失去焦点
                    et.setHint(et.getTag().toString());
                } else {
                    String hint = et.getHint().toString();
                    et.setTag(hint);//保存预设字
                    et.setHint(null);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_submit:
                showChangeIconPop();
                break;
            case R.id.album_rel://相册
                picChangePopup.dismiss();
                Toast.makeText(this,"点击了相册",Toast.LENGTH_SHORT).show();
                checkThePermisson(this, new String[]{PERMISSION_WRITE_EXTERNAL_STORAGE}, IMAGE_PREW, "读写权限");
                break;
            case R.id.photo_rel://拍照
                picChangePopup.dismiss();
                Toast.makeText(this,"点击了拍照",Toast.LENGTH_SHORT).show();
                checkThePermisson(this, new String[]{PERMISSION_CAMERA}, CAMERA_PREW, "获取相机权限");
                break;
            default:
                break;
        }
    }



    //显示图片更换popupwindows
    private void showChangeIconPop() {
        picChangePopup = new PicChangePopup(this).createPopup();
        picChangePopup.albumClick(this).photoClick(this);
        picChangePopup.setTitleText("上传照片");
        picChangePopup.showAtLocation(findViewById(R.id.activity_user_message), Gravity.BOTTOM, 0, 0);

    }


    public void checkThePermisson(final Context context, final String[] strings, final int code, final String string) {
        //请求Camera权限 与 文件读写 权限
        MyPermissionUtils.checkMorePermissions(context, strings, new MyPermissionUtils.PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {
                doAction(code);
            }

            @Override
            public void onUserHasAlreadyTurnedDown(String... permission) {
//                ToastUtils.ToastMessage(context, "需要权限");
                MyPermissionUtils.requestMorePermissions(context, strings, code);
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
//                ToastUtils.ToastMessage(context, "需要权限");
                showToAppSettingPop(context, string);
            }
        });

        if (strings != null){
            for (String per : strings){
                if (!TextUtils.isEmpty(per)){
                    StringCache.getInstance().addCache(per,per);
                }
            }
        }
    }
    //打开相册和相机
    public void doAction(int code) {
        switch (code) {
            case IMAGE_PREW:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_IMAGE_FROM_PHONE);
                break;
            case CAMERA_PREW:
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uri = FileProvider.getUriForFile(this, "com.example.lonse.fileProvider", tempFile);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent1, GET_IMAGE_BY_CAMERA);

                break;
        }
    }
    private void showToAppSettingPop(Context context, String string) {
        final Activity activity = (Activity) context;
        setPopup = new AppSettingPopup(context).createPopup();
        setPopup.sureTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpPermissionUtils.GoToSetting(activity);
                setPopup.dismiss();
            }
        });
        setPopup.cancelTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopup.dismiss();
            }
        });
        setPopup.setPermissText(string);
        new Handler().postDelayed(new Runnable() {

            public void run() {
                View view = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
                setPopup.showAtLocation(view, Gravity.CENTER, 0, 0);//findViewById(R.id.activity_qr).getRootView()

            }

        }, 100L);
    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (MyPermissionUtils.isPermissionRequestSuccess(grantResults)) {
                    doAction(CAMERA_PERMISSION);
                }
                break;
            case WRITE_EXTERNAL:
                if (MyPermissionUtils.isPermissionRequestSuccess(grantResults)) {
                    doAction(WRITE_EXTERNAL);
                }
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GET_IMAGE_FROM_PHONE:// 当选择从本地获取图片时
                    if (data != null) {
                        Uri uri = data.getData();
                        imgUri = getRealPathFromURI(this, uri);
                        Glide.with(this).load(imgUri).into(img);
//                        mAddImg.setVisibility(View.GONE);
                    }
                    break;
                case GET_IMAGE_BY_CAMERA:// 当选择从拍照获取图片时
//                    Uri uri = MediaUtils.getImgUri(this, tempFile);
//                    if (uri != null) {
//                        if (tempFile != null){
//                            imgUri = tempFile.getAbsolutePath();
//                        }
//                        mAddImg.setVisibility(View.GONE);
//                        Glide.with(this).load(uri).into(show_img);
//                    }
                    break;
                default:
                    break;
            }
        }
    }
    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            if (cursor == null){
                return contentUri.toString();
            }
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
