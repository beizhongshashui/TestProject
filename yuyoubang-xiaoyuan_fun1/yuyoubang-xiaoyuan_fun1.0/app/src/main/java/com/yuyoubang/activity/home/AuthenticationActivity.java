package com.yuyoubang.activity.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.network.api.UserApi;
import com.yuyoubang.utils.FileUtil;
import com.yuyoubang.utils.ToastUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/24.
 */
public class AuthenticationActivity extends BaseActivity {
    private static final int ONE = 0;//正面
    private static final int TWO = 1;//反面
    //请求相机
    private static final int REQUEST_CAPTURE = 102;
    //请求相册
    private static final int REQUEST_PICK = 103;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 104;
    private File tempFile;
    @Bind(R.id.rel_name)
    EditText relName;
    @Bind(R.id.rel_card)
    EditText relCard;
    @Bind(R.id.card_in)
    ImageView cardIn;
    @Bind(R.id.card_out)
    ImageView cardOut;
    @Bind(R.id.tv_audit)
    TextView tvAudit;

    private String pic2;
    private String pic3;
    private int type;


    public static void start(Context context) {
        Intent intent = new Intent(context, AuthenticationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("身份认证");
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_authentication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        createHeadImageTempFile(savedInstanceState);
    }

    @OnClick({R.id.card_in, R.id.card_out, R.id.tv_audit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_in:
                type = ONE;
                showPhotoDialog();
                break;
            case R.id.card_out:
                type = TWO;
                showPhotoDialog();
                break;
            case R.id.tv_audit:
                String name = relName.getText().toString();
                String card = relCard.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showLong("请填写名字");
                    return;
                }
                if (TextUtils.isEmpty(card)) {
                    ToastUtils.showLong("请填写身份证号");
                    return;
                }
                if (TextUtils.isEmpty(pic2)) {
                    ToastUtils.showLong("请上传身份证");
                    return;
                }

                if (TextUtils.isEmpty(pic3)) {
                    ToastUtils.showLong("请上传身份证");
                    return;
                }
                //// TODO: 16/11/28  缺如下几个参数
                AuthenTication("phone",pic2,pic3,name,"uid","user_name",card);
                break;
        }
    }

    private void showPhotoDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.alert_dialog);
        TextView tv_paizhao = (TextView) window.findViewById(R.id.tv_content1);
        tv_paizhao.setText("拍照");
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getCamera(dlg);
            }
        });
        TextView tv_xiangce = (TextView) window.findViewById(R.id.tv_content2);
        tv_xiangce.setText("相册");
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getPhoto(dlg);

            }
        });

    }


    private void getPhoto(AlertDialog dlg) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
        dlg.cancel();
    }

    private void getCamera(AlertDialog dlg) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
        dlg.cancel();
    }

    private void createHeadImageTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {

            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    upLoad(tempFile, type);
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String path = FileUtil.getRealFilePathFromUri(getApplicationContext(), uri);
                    upLoad(new File(path), type);
                }
                break;
        }
    }

    private void AuthenTication(String phone,String pic2,String pic3,String real_name,String uid,String user_name,String vip_card_number) {
        UserApi userApi = RetrofitManager.getTestRetrofit().create(UserApi.class);
        FormBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("pic2", pic2)
                .add("pic3", pic3)
                .add("real_name", real_name)
                .add("uid", uid)
                .add("user_name", user_name)
                .add("vip_card_number", vip_card_number)
                .build();

        Call<Object> find = userApi.USER_PERSON_VERIFICATION_APPLY(body);
        find.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }


    private void upLoad(File file, final int type) {
        onShowProgressDlg();
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("pic", file.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), file));
        UploadApi uploadApi = RetrofitManager.getTestRetrofit().create(UploadApi.class);
        Call<UploadBean> upload = uploadApi.upload(filePart);
        upload.enqueue(new BaseCallback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.e("LoginInfoActivity", "response=" + response.body().getResult().getBmiddle_pic());
                if (response.body().getError_code() == 0) {
                    if (type == ONE) {
                        pic2 = response.body().getResult().getBmiddle_pic();
                        ImageLoader.getInstance().displayImage(pic2,cardIn, ImageOption.defaultOptions);
                    } else {
                        pic3 = response.body().getResult().getBmiddle_pic();
                        ImageLoader.getInstance().displayImage(pic3,cardOut, ImageOption.defaultOptions);
                    }
//
                } else {
//                    ToastUtils.showLong(response.body().error_msg);
                }
                cancelProgressDlg();

            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong("网络连接失败");
            }
        });
    }
}
