package com.andoop.ctrlf5.bangzhu.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;

import java.io.File;
import java.io.IOException;

public class PersonMsgActivity extends AppCompatActivity {

    private static final int CHOOSE_PICTURE = 11;
    private static final int CAMERANF_REQUEST = 22;
    private static final int CROP_2_REQUEST = 33;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_msg);
        ((TextView)findViewById(R.id.tv_title)).setText("个人信息");
        icon= (ImageView) findViewById(R.id.iv_person_msg_icon);
        findViewById(R.id.ll_person_msg_zc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonMsgActivity.this, SkillChooseActivity.class);
                Bundle extra=new Bundle();
                extra.putInt("showtitle",1);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.iv_person_msg_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    File file;
    Dialog dialog;
    private void showDialog() {
        file=new File("/mnt/sdcard/a.jpg");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
          dialog = new Dialog(this,R.style.MyDialog);
        View view = View.inflate(this, R.layout.take_picture_dialog_content, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        //dialog.setContentView();

        view.findViewById(R.id.photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonMsgActivity.this, "从相册中取", Toast.LENGTH_SHORT).show();
                Intent it = new Intent();
                it.setType("image/*");

                it.setAction("android.intent.action.PICK");
                it.addCategory("android.intent.category.DEFAULT");

                startActivityForResult(it,CHOOSE_PICTURE);
            }
        });

        view.findViewById(R.id.take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonMsgActivity.this, "调用相机", Toast.LENGTH_SHORT).show();
                Intent it = new Intent();
                it.setAction("android.media.action.IMAGE_CAPTURE");
                it.addCategory("android.intent.category.DEFAULT");
                file = new File("/mnt/sdcard/a.jpg");
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(it, CAMERANF_REQUEST);
            }
        });
        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    // 切图
    public void cutimage(Uri imageUri) {

        Intent it = new Intent("com.android.camera.action.CROP");
        it.setDataAndType(imageUri, "image/*");
        it.putExtra("crop", "true");
        it.putExtra("aspectX", 1);
        it.putExtra("aspectY", 1);
        it.putExtra("outputX", 250);
        it.putExtra("outputY", 250);
        it.putExtra("outputFormat", "JPEG");
        it.putExtra("noFaceDetection", true);
        it.putExtra("return-data", true);
        startActivityForResult(it,CROP_2_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case CHOOSE_PICTURE:
                Uri uri = data.getData();
                cutimage(uri);
                Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
                break;
            case CAMERANF_REQUEST:
                Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                cutimage(Uri.fromFile(file));
                break;
            case CROP_2_REQUEST:
                Bitmap bitmap = data.getParcelableExtra("data");
                icon.setImageBitmap(bitmap);
                dialog.dismiss();
                update(bitmap);
                break;
            default:
                break;
        }
    }

    private void update(Bitmap bitmap) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "更新用户信息", Toast.LENGTH_SHORT).show();
    }
}
