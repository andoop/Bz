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
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.presenter.PersonMsgViewPersenter;
import com.andoop.ctrlf5.bangzhu.utils.DialogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonMsgActivity extends AppCompatActivity {

    private static final int CHOOSE_PICTURE = 11;
    private static final int CAMERANF_REQUEST = 22;
    private static final int CROP_2_REQUEST = 33;
    private ImageView icon;
    private PersonMsgViewPersenter personMsgViewPersenter;
    private Dialog dialog1;

    private TextView jn1;
    private TextView jn2;
    private TextView jn3;

    TextView tvtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_msg);
        ((TextView)findViewById(R.id.tv_title)).setText("个人信息");
        icon= (ImageView) findViewById(R.id.iv_person_msg_icon);
        jn1= (TextView) findViewById(R.id.jn1);
        jn2= (TextView) findViewById(R.id.jn2);
        jn3= (TextView) findViewById(R.id.jn3);

        tvtime= (TextView) findViewById(R.id.msg_time);

        ImageLoader.getInstance().displayImage(BzUser.getCurentuser().getUserinfo().getAvatar(),icon);

        TextView tv_personal_name= (TextView)findViewById(R.id.tv_person_msg_name);
        tv_personal_name.setText(BzUser.getCurentuser().getUserinfo().getUsername());
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

        findViewById(R.id.ll_person_msg_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(PersonMsgActivity.this, R.style.MyDialog);
                View inflate = View.inflate(PersonMsgActivity.this, R.layout.dsdsd, null);
                dialog.setContentView(inflate);
                dialog.show();
                final EditText dddd= (EditText) inflate.findViewById(R.id.ettt);
                inflate.findViewById(R.id.tv_fb_ok_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(dddd.getText().toString())){
                            Toast.makeText(PersonMsgActivity.this, "请输入时间", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Map params=new HashMap();
                        params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
                        params.put("free_time",dddd.getText().toString());
                        BzDataManager.getInstance().changeUserInfo(PersonMsgActivity.this, params, new DataListener<BzUser>() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onSuccess(BzUser data) {
                                dialog.dismiss();
                                tvtime.setText(data.getUserinfo().getFree_time());
                            }

                            @Override
                            public void onError(String err) {
                                Toast.makeText(PersonMsgActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                            @Override
                            public void onLoading(int persent) {

                            }
                        });
                    }
                });

                inflate.findViewById(R.id.cccccc).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        personMsgViewPersenter = new PersonMsgViewPersenter(this);
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
    private File tempfile;
    private void update(Bitmap bitmap) {
        tempfile=new File("/mnt/sdcard/avatar.jpg");
        if(!tempfile.exists()){
            try {
                tempfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,new FileOutputStream(tempfile));
            personMsgViewPersenter.uploadImg(bitmap,tempfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();



        List<String> skills = BzUser.getCurentuser().getUserinfo().getSkills();

        jn1.setVisibility(View.GONE);
        jn2.setVisibility(View.GONE);
        jn3.setVisibility(View.GONE);

        if(skills.size()>=3){
           jn1.setText(skills.get(0));
           jn2.setText(skills.get(1));
           jn3.setText(skills.get(2));

           jn1.setVisibility(View.VISIBLE);
           jn2.setVisibility(View.VISIBLE);
           jn3.setVisibility(View.VISIBLE);

        }else if(skills.size()>=2){
            jn1.setText(skills.get(0));
            jn2.setText(skills.get(1));
            jn1.setVisibility(View.VISIBLE);
            jn2.setVisibility(View.VISIBLE);
        }else if(skills.size()>=1){
            jn1.setVisibility(View.VISIBLE);
            jn1.setText(skills.get(0));
        }

        tvtime.setText(BzUser.getCurentuser().getUserinfo().getFree_time());


    }

    public void showLoading() {
        dialog1 = DialogUtils.showLoadingView(this);
    }

    public void success(BzUser bzUser) {
        dialog1.dismiss();
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
    }

    public void showError(String s) {
        dialog1.dismiss();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
