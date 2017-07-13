package com.yuyoubang.activity.push;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yuyoubang.R;
import com.yuyoubang.activity.ClipImaviewActivity;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.picker.ClipImageActivity;
import com.yuyoubang.bean.LoginUserBean;
import com.yuyoubang.bean.NewTrip;
import com.yuyoubang.bean.Trip;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.TripApi;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.utils.ChatUtil;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.SDUtil;
import com.yuyoubang.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/17.
 */
public class PushActActivity extends BaseActivity {
    //请求相机
    private static final int REQUEST_CAPTURE = 102;
    //请求相册
    private static final int REQUEST_PICK = 103;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 104;

    //基本情况
    private static final int SET_BASE_CASE = 105;
    private static final int SET_LOCATION = 106;
    private static final int SET_DATE = 107;
    private static final int SET_ACT_INF0 = 108;
    @Bind(R.id.tv_push)
    TextView tvPush;
    @Bind(R.id.bg_cover)
    ImageView bgCover;

    private File tempFile;
    @Bind(R.id.base_case)
    RelativeLayout baseCase;
    @Bind(R.id.location_set)
    RelativeLayout locationSet;
    @Bind(R.id.date_set)
    RelativeLayout dateSet;
    @Bind(R.id.act_info)
    RelativeLayout actInfo;
    @Bind(R.id.iv_cover)
    ImageView ivCover;

    private NewTrip newTrip;
    private static String[] allmebers = new String[0];
    private float angle;
    LoginUserBean.ResultBean.UserDataBean user;

    public static void start(Context context) {
        Intent intent = new Intent(context, PushActActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("发布活动路线");
        /*builder.setRightTvText("保存", new OnClickListener() {
            @Override
            protected void clickOperate() {

            }
        });*/

    }

    @Override
    protected int getContentResId() {
        return R.layout.act_push_act;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        try {
            user = PreferenceUtils.deSerialization(PreferenceUtils.getPrefString(PushActActivity.this, "user", ""));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        createHeadImageTempFile(savedInstanceState);
        try {
            if (!TextUtils.isEmpty(PreferenceUtils.getPrefString(PushActActivity.this, "trip", ""))) {
                newTrip = PreferenceUtils.deSerialization(PreferenceUtils.getPrefString(PushActActivity.this, "trip", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (newTrip != null) {
            ImageLoader.getInstance().displayImage(newTrip.getTrip_cover_pic(), bgCover, ImageOption.default_banner_options);
        }
        tvPush.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (newTrip == null) {
                    ToastUtils.showShort("信息不完整");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getTrip_cover_pic())) {
                    ToastUtils.showShort("请上传活动图片, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getTrip_name())) {
                    ToastUtils.showShort("请输入活动名字, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getTrip_route_type())) {
                    ToastUtils.showShort("请选择活动路线, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getParticipants_limit_count())) {
                    ToastUtils.showShort("请输入活动人数, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getTrip_price())) {
                    ToastUtils.showShort("请输入活动价格, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getMeeting_place())) {
                    ToastUtils.showShort("请输入活动具体地址, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getStart_time())) {
                    ToastUtils.showShort("请选择活动开始日期, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getEnd_time())) {
                    ToastUtils.showShort("请选择活动结束日期, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getParticipate_end_time())) {
                    ToastUtils.showShort("请选择活动报名日期, 再点发布");
                    return;
                }


                if (TextUtils.isEmpty(newTrip.getTrip_cost())) {
                    ToastUtils.showShort("请填写活动详情, 再点发布");
                    return;
                }
                if (TextUtils.isEmpty(newTrip.getTrip_intro())) {
                    ToastUtils.showShort("请填写活动详情, 再点发布");
                    return;
                }

                ChatUtil.CreateGroup(newTrip.getTrip_name(), newTrip.getTrip_cover_pic(), allmebers, null, new EMValueCallBack<EMGroup>() {
                    @Override
                    public void onSuccess(EMGroup emGroup) {
                        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                        EMMessage message = EMMessage.createTxtSendMessage(user.getUser_name() + "邀请您加入了群聊", emGroup.getGroupId());
//                        EMMessage message = EMMessage.createTxtSendMessage(newTrip.getTrip_name(), emGroup.getGroupId());
//                //如果是群聊，设置chattype，默认是单聊
                        message.setChatType(EMMessage.ChatType.GroupChat);
//                //发送消息
                        EMClient.getInstance().chatManager().sendMessage(message);
                        // TODO: 16/11/30
                        pushAct(emGroup.getGroupId());
                    }

                    @Override
                    public void onError(int i, String s) {
                        ToastUtils.showLong(s+i);
                    }
                });

            }
        });
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
//                    gotoClipActivity(Uri.fromFile(tempFile));

                    String mAlbumPicturePath = SDUtil.getPath(getApplicationContext(),
                            Uri.fromFile(tempFile));
                    Intent intent = new Intent(this, ClipImaviewActivity.class);
                    intent.putExtra("path", mAlbumPicturePath);
                    startActivityForResult(intent, REQUEST_CROP_PHOTO);
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String mAlbumPicturePath = SDUtil.getPath(getApplicationContext(),
                            uri);
                    Intent intent = new Intent(this, ClipImaviewActivity.class);
                    intent.putExtra("path", mAlbumPicturePath);
                    startActivityForResult(intent, REQUEST_CROP_PHOTO);
//                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                byte[] bis = data.getByteArrayExtra("bitmap");
                if (bis != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);

                    if (angle != 0) {
                        // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
                        Matrix m = new Matrix();
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        m.setRotate(angle); // 旋转angle度
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                                m, true);// 从新生成图片

                    }
                    if (bitmap != null) {
                        bgCover.setImageBitmap(bitmap);
                        File cacheDir = StorageUtils.getOwnCacheDirectory(this,
                                "yuyoubang/Cache");
                        String fileName = cacheDir.getPath() + "/"
                                + System.currentTimeMillis();
                        FileOutputStream mFileOutputStream = null;
                        try {
                            mFileOutputStream = new FileOutputStream(fileName);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                                    mFileOutputStream);// 把数据写入文件
                            File file = new File(fileName);
                            upLoad(file);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                mFileOutputStream.flush();
                                mFileOutputStream.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
                break;

            case SET_BASE_CASE:
                newTrip = (NewTrip) data.getExtras().getSerializable("trip");
                try {
                    PreferenceUtils.setPrefString(PushActActivity.this, "trip", PreferenceUtils.serialize(newTrip));
                } catch (IOException e) {

                    e.printStackTrace();
                }
                break;
            case SET_LOCATION:
                newTrip = (NewTrip) data.getExtras().getSerializable("trip");
                try {
                    PreferenceUtils.setPrefString(PushActActivity.this, "trip", PreferenceUtils.serialize(newTrip));
                } catch (IOException e) {

                    e.printStackTrace();
                }
                break;
            case SET_ACT_INF0:
                newTrip = (NewTrip) data.getExtras().getSerializable("trip");
                try {
                    PreferenceUtils.setPrefString(PushActActivity.this, "trip", PreferenceUtils.serialize(newTrip));
                } catch (IOException e) {

                    e.printStackTrace();
                }
                break;
            case SET_DATE:
                newTrip = (NewTrip) data.getExtras().getSerializable("trip");
                try {
                    PreferenceUtils.setPrefString(PushActActivity.this, "trip", PreferenceUtils.serialize(newTrip));
                } catch (IOException e) {

                    e.printStackTrace();
                }
                break;

        }
    }


    private void upLoad(File file) {

        onShowProgressDlg();
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("pic", file.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), file));
        UploadApi uploadApi = RetrofitManager.getTestRetrofit().create(UploadApi.class);
        Call<UploadBean> upload = uploadApi.upload(filePart);
        upload.enqueue(new BaseCallback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.e("PushActActivity", "response=" + response.body().getResult().getBmiddle_pic());
                if (response.body().getError_code() == 0) {
                    if (newTrip == null) {
                        newTrip = new NewTrip();
                    }
                    newTrip.setTrip_cover_pic(response.body().getResult().getBmiddle_pic());
                    try {
                        PreferenceUtils.setPrefString(PushActActivity.this, "trip", PreferenceUtils.serialize(newTrip));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
//                    ToastUtils.showLong(response.body().error_msg);
                }
                cancelProgressDlg();

            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong(R.string.net_error);

                Log.e("PushActActivity", "response=网络错误");
            }
        });
    }

    @OnClick({R.id.base_case, R.id.location_set, R.id.date_set, R.id.act_info, R.id.iv_cover})
    public void onClick(View view) {

        if (newTrip == null) {
            newTrip = new NewTrip();
        }
        switch (view.getId()) {
            case R.id.base_case:
                BaseCaseActivity.start(PushActActivity.this, newTrip, SET_BASE_CASE);
                break;
            case R.id.location_set:
                SetLocationActivity.start(PushActActivity.this, newTrip, SET_LOCATION);
                break;
            case R.id.date_set:
                SetDateActivity.start(PushActActivity.this, newTrip, SET_DATE);
                break;
            case R.id.act_info:
                SetActInfoActiviy.start(PushActActivity.this, newTrip, SET_ACT_INF0);
                break;
            case R.id.iv_cover:
                showPhotoDialog();
                break;

        }
    }


    private void pushAct(String groupId) {
        TripApi tripApi = RetrofitManager.getTestRetrofit().create(TripApi.class);
        FormBody body = new FormBody.Builder().add("uid", PreferenceUtils.getsessionId(PushActActivity.this))
                .add("biz_user_name", user.getUser_name())
                .add("end_time", newTrip.getEnd_time())
                .add("meeting_city", newTrip.getMeeting_city())
                .add("meeting_place", newTrip.getMeeting_place())
                .add("meeting_province", newTrip.getMeeting_province())
                .add("participants_limit_count", newTrip.getParticipants_limit_count())
                .add("participate_end_time", newTrip.getParticipate_end_time())
                .add("start_time", newTrip.getStart_time())
                .add("trip_cover_pic", newTrip.getTrip_cover_pic())
                .add("trip_name", newTrip.getTrip_name())
                .add("trip_price", newTrip.getTrip_price())
                .add("trip_route_type", newTrip.getTrip_route_type())
                .add("trip_tags", newTrip.getTrip_tags())
                .add("user_create_group_id", groupId)
                .add("other_desc", newTrip.getTrip_other())
                .add("trip_equipment_intro", newTrip.getTrip_cost())//装备介绍
                .add("trip_intro", newTrip.getTrip_intro())//活动介绍
                .add("meeting_province_code", newTrip.getMeeting_province_code())//集合省份编码
                .add("meeting_city_code", newTrip.getMeeting_city_code())//集合城市编码
                .build();
        Call<Trip> login = tripApi.trip_new(body);
        login.enqueue(new BaseCallback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                Log.e("PushActActivity", response.body() + "=" + response.message());
                if (response.body().getError_code() == 0) {
                    PreferenceUtils.setPrefString(PushActActivity.this, "trip", "");
                    ToastUtils.showLong("发布成功，通过审核后就能上线啦");
                    finish();
                } else {

                }
                cancelProgressDlg();

            }

            @Override
            public void onFailure(Call<Trip> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }

    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", 1);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
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
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    public void backDialog() {
        new android.support.v7.app.AlertDialog.Builder(this).setTitle("温馨提示").setMessage("返回数据会被情况哦").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
}
