package com.andoop.ctrlf5.bangzhu.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.modle.FHTBean;
import com.andoop.ctrlf5.bangzhu.utils.DialogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FaBuHuaTiActivity extends AppCompatActivity {


    private static final int CHOOSE_PICTURE = 11;
    private static final int CAMERANF_REQUEST = 22;
    private static final int CROP_2_REQUEST = 33;

    private int pos=0;


    private FaBuHuaTiViewPresenter faBuHuaTiViewPresenter;
    ImageView icon1;
    ImageView icon2;
    ImageView icon3;
    EditText editText;

    boolean hasicon0;
    boolean hasicon1;
    boolean hasicon2;
    private Dialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_hua_ti);
        faBuHuaTiViewPresenter = new FaBuHuaTiViewPresenter(this);

        icon1= (ImageView) findViewById(R.id.iv_fbht_icon1);
        icon2= (ImageView) findViewById(R.id.iv_fbht_icon2);
        icon3= (ImageView) findViewById(R.id.iv_fbht_icon3);
        editText= (EditText) findViewById(R.id.et_fbht);

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choolsePhoto(0);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choolsePhoto(1);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choolsePhoto(2);
            }
        });

        findViewById(R.id.tv_fb_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabu();
            }
        });

        findViewById(R.id.tv_fabu_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void choolsePhoto(int i) {
        pos=i;
        showDialog();
    }

    private void fabu() {
        String text = editText.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
            Toast.makeText(this, "说点什么吧...", Toast.LENGTH_SHORT).show();
            return;
        }

        FHTBean fhtBean = new FHTBean();

        if(hasicon0){
            fhtBean.file0=file0;
        }
        if(hasicon1){
            fhtBean.file1=file1;
        }
        if(hasicon2){
            fhtBean.file2=file2;
        }

        fhtBean.content=text;
        fhtBean.time=System.currentTimeMillis();

        faBuHuaTiViewPresenter.fabu(fhtBean);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog1!=null)
            dialog1.dismiss();
    }

    public void showloading(){
        Toast.makeText(this, "话题发布中", Toast.LENGTH_SHORT).show();
        dialog1 = DialogUtils.showLoadingView(this);
    }

    public void showError(String err){
        dialog1.dismiss();
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    public void success(){
        dialog1.dismiss();
        Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    File file;
    File file0;
    File file1;
    File file2;
    Dialog dialog;
    private void showDialog() {
        file=new File("/mnt/sdcard/a.jpg");
        file1=new File("/mnt/sdcard/b.jpg");
        file2=new File("/mnt/sdcard/c.jpg");
        file0=new File("/mnt/sdcard/d.jpg");
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
        view.findViewById(R.id.photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FaBuHuaTiActivity.this, "从相册中取", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(FaBuHuaTiActivity.this, "调用相机", Toast.LENGTH_SHORT).show();
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
                dialog.dismiss();
                update(bitmap);
                break;
            default:
                break;
        }
    }

    private void update(Bitmap bitmap) {
        File mfile=null;
        switch (pos){
            case 0:
               mfile=file0;
                hasicon0=true;
                icon1.setImageBitmap(bitmap);
                break;
            case 1:
                mfile=file1;
                hasicon1=true;
                icon2.setImageBitmap(bitmap);
                break;
            case 2:
                mfile=file2;
                hasicon2=true;
                icon3.setImageBitmap(bitmap);
                break;
        }

        try {
            FileOutputStream out = new FileOutputStream(mfile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 95, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
