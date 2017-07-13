package com.yuyoubang.fragment.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yuyoubang.R;
import com.yuyoubang.activity.ClipImaviewActivity;
import com.yuyoubang.activity.mine.business.BusinessCenterActivity;
import com.yuyoubang.activity.mine.business.OpenBusinessActivity;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.bean.findfollowinfo.OtherHome;
import com.yuyoubang.bean.mine.UpdateUserInfo;
import com.yuyoubang.bean.mine.UserSureName;
import com.yuyoubang.fragment.base.BaseNetFragment;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.utils.BitmapUtils;
import com.yuyoubang.utils.FileUtil;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.utils.SDUtil;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.RoundImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import static android.app.Activity.RESULT_OK;

/**
 * Created by hongchen on 16/11/6.
 */

public class MineFragment extends BaseNetFragment<OtherHome> {


    //请求相机
    private static final int REQUEST_CAPTURE = 102;
    //请求相册
    private static final int REQUEST_PICK = 103;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 104;
    @Bind(R.id.mine_avatar)
    RoundImageView mineAvatar;
    @Bind(R.id.mine_name)
    TextView mineName;
    @Bind(R.id.trip_old)
    TextView tripOld;
    @Bind(R.id.trip_location)
    TextView tripLocation;
    @Bind(R.id.mine_sign)
    TextView mineSign;
    @Bind(R.id.mine_trends_count)
    TextView mineTrendsCount;
    @Bind(R.id.mine_follow_count)
    TextView mineFollowCount;
    @Bind(R.id.mine_fans_count)
    TextView mineFansCount;
    private File tempFile;
    private String profile_pic_url = "";

    @Bind(R.id.business_center)
    LinearLayout businessCenter;
    @Bind(R.id.setting)
    LinearLayout setting;
    private ImageView iv_bg;
    private TagFlowLayout allFlowLayout;
    private TagAdapter<String> tagAdapter;
    private List<String> tempList = new ArrayList<>();
    private TextView apply_state;
    private ImageView iv_company;
    private ImageView iv_sex;
    private TextView is_verification;
    private TextView login_time;
    private LinearLayout bg_color;
    private String location = "";

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goneLoading();
        createHeadImageTempFile(savedInstanceState);


    }


    @Override
    protected void initViews() {
        LinearLayout about_ours = getViewById(R.id.about_ours);
        LinearLayout setting = getViewById(R.id.setting);
        LinearLayout care_person = getViewById(R.id.care_person);
        LinearLayout dongtai = getViewById(R.id.dongtai);
        LinearLayout like = getViewById(R.id.like);
        LinearLayout user_true = getViewById(R.id.user_true);
        LinearLayout mine_order = getViewById(R.id.mine_order);
        LinearLayout commend_layout = getViewById(R.id.commend_layout);
        bg_color = getViewById(R.id.bg_color);
        TextView editor = getViewById(R.id.editor);
        TextView load_bg = getViewById(R.id.load_bg);
        login_time = getViewById(R.id.login_time);
        iv_sex = getViewById(R.id.iv_sex);
        apply_state = getViewById(R.id.apply_state);
        iv_bg = getViewById(R.id.iv_bg);
        allFlowLayout = getViewById(R.id.id_flowlayout_two);
        iv_company = getViewById(R.id.iv_company);
        is_verification = getViewById(R.id.is_verification);


        about_ours.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//关于
                LaunchOperate.openAboutActivity(getActivity());
            }
        });

        setting.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//设置
                LaunchOperate.openSettingActivity(getActivity());
            }
        });

        care_person.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//关注
                LaunchOperate.openCareUserList(getActivity(), PreferenceUtils.getsessionId(getActivity()), "1000");
            }
        });

        like.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//粉丝
                LaunchOperate.openLikeList(getActivity(), PreferenceUtils.getsessionId(getActivity()), "1000");
            }
        });

        editor.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//编辑
                LaunchOperate.openEditorActivity(getActivity());
            }
        });

        load_bg.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//上传封面
                showPhotoDialog();
            }
        });

        dongtai.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//我的动态
                LaunchOperate.openMineDoTaiActivity(getActivity());
            }
        });

        user_true.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//实名认证
//                AuthenticationActivity.start(getActivity());
                LaunchOperate.openUserInfoStatsActivity(getActivity());
            }
        });

        mine_order.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//我的订单
                LaunchOperate.openMineOrderActivity(getActivity());
            }
        });

        commend_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//我的评价
                LaunchOperate.openMineCommendActivity(getActivity());
            }
        });
    }

    private void showPhotoDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(getActivity()).create();
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

    private void getCamera(AlertDialog dlg) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
        dlg.cancel();
    }

    private void getPhoto(AlertDialog dlg) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {

            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
//                    gotoClipActivity(Uri.fromFile(tempFile));

                    String mAlbumPicturePath = SDUtil.getPath(getActivity().getApplicationContext(),
                            Uri.fromFile(tempFile));
                    Intent intent = new Intent(getActivity(), ClipImaviewActivity.class);
                    intent.putExtra("path", mAlbumPicturePath);
                    startActivityForResult(intent, REQUEST_CROP_PHOTO);
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String mAlbumPicturePath = SDUtil.getPath(getActivity().getApplicationContext(),
                            uri);
                    Intent intent = new Intent(getActivity(), ClipImaviewActivity.class);
                    intent.putExtra("path", mAlbumPicturePath);
                    startActivityForResult(intent, REQUEST_CROP_PHOTO);
//                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                byte[] bis = data.getByteArrayExtra("bitmap");
                if (bis != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);

//                    if (angle != 0) {
//                        // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
//                        Matrix m = new Matrix();
//                        int width = bitmap.getWidth();
//                        int height = bitmap.getHeight();
//                        m.setRotate(angle); // 旋转angle度
//                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
//                                m, true);// 从新生成图片
//
//                    }
                    if (bitmap != null) {
                        iv_bg.setImageBitmap(bitmap);
                        File cacheDir = StorageUtils.getOwnCacheDirectory(getActivity(),
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



        }
    }

    private void upLoad(File file) {
        Log.e("LoginInfoActivity", "file.name=" + file.getName());
        onShowProgressDlg();
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("pic", file.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), file));
        UploadApi uploadApi = RetrofitManager.getTestRetrofit().create(UploadApi.class);
        Call<UploadBean> upload = uploadApi.upload(filePart);
        upload.enqueue(new BaseCallback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.e("LoginInfoActivity", "response=" + response.body().getResult().getBmiddle_pic());
                if (response.body().getError_code() == 0) {
                    profile_pic_url = response.body().getResult().getBmiddle_pic();
                    ImageLoader.getInstance().displayImage(profile_pic_url, iv_bg, ImageOption.default_banner_options);
                    updateUserInfo();//更新用户信息接口
                } else {
//                    ToastUtils.showLong(response.body().error_msg);
                }
            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong("网络连接失败");
                Log.e("RigisterActivity", "测试失败");
            }
        });
    }

    private void updateUserInfo() {
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(getActivity()))
                .add("user_cover_pic_url", profile_pic_url)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<UpdateUserInfo> updateUserInfoCall = mineApi.update_user_info(formBody);
        updateUserInfoCall.enqueue(new Callback<UpdateUserInfo>() {
            @Override
            public void onResponse(Call<UpdateUserInfo> call, Response<UpdateUserInfo> response) {
                LogUtils.e("UpdateUserInfo" + response.body());
                ToastUtils.showShort("更新封面成功");
                cancelProgressDlg();
            }

            @Override
            public void onFailure(Call<UpdateUserInfo> call, Throwable t) {
                ToastUtils.showShort("更新资料失败");
                cancelProgressDlg();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void loadData() {
        QLog.d("mine", PreferenceUtils.getsessionId(getActivity()));
        FormBody formBody = new FormBody.Builder()
                .add("to_uid", PreferenceUtils.getsessionId(getActivity()))
                .add("uid", PreferenceUtils.getsessionId(getActivity()))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<OtherHome> otherHomeCall = findApi.user_info_show(formBody);
        otherHomeCall.enqueue(this);
    }

    @Override
    protected void processData(OtherHome o) {
        if (o.getError_code() == 10001) {
            ToastUtils.showShort("系统错误");
            return;
        }
        if (o.getResult().getUser() != null && o.getResult().getUser().size() > 0) {
            if (o.getResult().getUser().get(0).getUser_data().getUser_cover_pic_url() != null) {
                profile_pic_url = o.getResult().getUser().get(0).getUser_data().getUser_cover_pic_url();
                ImageLoader.getInstance().displayImage(profile_pic_url, iv_bg, ImageOption.default_banner_options);
            }else {
                Bitmap bitmap = BitmapUtils.decodeBitmap(getActivity().getResources(), R.mipmap.fragment_my_bg);
                iv_bg.setImageBitmap(bitmap);
            }
            if (o.getResult().getUser().get(0).getUser_data().getUser_tags() != null) {
                tempList = o.getResult().getUser().get(0).getUser_data().getUser_tags();
            }
            ImageLoader.getInstance().displayImage(o.getResult().getUser().get(0).getUser_data().getProfile_pic_url(), mineAvatar, ImageOption.defaultOptions);
            mineName.setText(o.getResult().getUser().get(0).getUser_data().getUser_name());
//        tripOld.setText("todo");

            long birthday = o.getResult().getUser().get(0).getUser_data().getBirthday();
            if (birthday != 0) {
                try {
                    Date longToData = TimeUtil.getLongToData(birthday);
                    int age = TimeUtil.getAge(longToData);
                    tripOld.setText(age + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String text = TimeUtil.getTimeRange(o.getResult().getUser().get(0).getUpdated_at()+"");
            login_time.setText(text);

            if (o.getResult().getUser().get(0).getUser_data().getProvince().contains("市")) {//直辖市
                location = o.getResult().getUser().get(0).getUser_data().getProvince();
            } else {
                location = o.getResult().getUser().get(0).getUser_data().getUser_location();
            }

            if (!TextUtils.isEmpty(location)) {
                if (location.contains("市")) {
                    String substring = location.substring(0, location.length() - 1);
                    tripLocation.setText(substring);
                } else {
                    tripLocation.setText(location);
                }
            }

//            tripLocation.setText(o.getResult().getUser().get(0).getUser_data().getUser_location());//区或者市
            mineSign.setText(o.getResult().getUser().get(0).getUser_data().getUser_desc());
            mineTrendsCount.setText(o.getResult().getUser().get(0).getUser_data().getStatus_total_count() + "");
            mineFollowCount.setText(o.getResult().getUser().get(0).getUser_data().getFollow_count() + "");
            mineFansCount.setText(o.getResult().getUser().get(0).getUser_data().getFans_count() + "");

            if (!TextUtils.isEmpty(o.getResult().getUser().get(0).getUser_data().getGender())) {

                if (Integer.valueOf(o.getResult().getUser().get(0).getUser_data().getGender()) == 0) {
                    iv_sex.setImageResource(R.mipmap.sex_men);
                    bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                    tripLocation.setBackgroundResource(R.drawable.bg_color_ff9600);
                } else {
                    iv_sex.setImageResource(R.mipmap.sex_women);
                    bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                    tripLocation.setBackgroundResource(R.drawable.bg_color_fd89cb);
                }
            }
            if (o.getResult().getUser().get(0).getUser_data().getVerification_type() == 2) {
                iv_company.setVisibility(View.VISIBLE);
            }
            if (o.getResult().getUser().get(0).getUser_data().getVerification_type() == 1) {
                is_verification.setVisibility(View.VISIBLE);
            }
            tagAdapter = new TagAdapter<String>(tempList) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    TextView tv = (TextView) layoutInflater.inflate(R.layout.flag_adapter_tags,
                            allFlowLayout, false);
                    tv.setText(s);
                    return tv;
                }
            };
            allFlowLayout.setAdapter(tagAdapter);
            lookUserApplyState();
        }

    }

    private void lookUserApplyState() {
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(getActivity()))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<UserSureName> userSureNameCall = mineApi.person_sure_name(formBody);
        userSureNameCall.enqueue(new Callback<UserSureName>() {
            @Override
            public void onResponse(Call<UserSureName> call, Response<UserSureName> response) {
                UserSureName body = response.body();
                if (body != null) {
                    if (body.getResult() != null) {
                        if (body.getResult().getList().size() > 0) {
                            if (body.getResult().getList().get(0).getData().getApply_state() == 0) {
                                apply_state.setText("审核中");
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 1) {
                                apply_state.setText("已认证");
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 2) {
                                apply_state.setText("未通过");
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserSureName> call, Throwable t) {

            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.frg_mine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.business_center, R.id.setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.business_center:
                requestApplyState();
//                OpenBusinessActivity.start(getActivity());
                break;
            case R.id.setting:
                break;
        }
    }

    private void requestApplyState() {
        onShowProgressDlg();
        FormBody formBody = new FormBody.Builder()
                .add("uid", PreferenceUtils.getsessionId(getActivity()))
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<UserSureName> userSureNameCall = mineApi.person_sure_name(formBody);
        userSureNameCall.enqueue(new Callback<UserSureName>() {
            @Override
            public void onResponse(Call<UserSureName> call, Response<UserSureName> response) {
                cancelProgressDlg();
                UserSureName body = response.body();
                if (body != null) {
                    if (body.getResult() != null) {
                        if (body.getResult().getList().size() > 0) {
                            if (body.getResult().getList().get(0).getData().getApply_state() == 0) {
                                OpenBusinessActivity.start(getActivity());
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 1) {
                                BusinessCenterActivity.start(getActivity(), PreferenceUtils.getsessionId(getActivity()));
//                                OpenBusinessTypeActivity.start(getActivity());
                            }
                            if (body.getResult().getList().get(0).getData().getApply_state() == 2) {
                                OpenBusinessActivity.start(getActivity());
                            }
                        } else {
                            OpenBusinessActivity.start(getActivity());
                        }
                    } else {
                        OpenBusinessActivity.start(getActivity());
                    }
                } else {
                    OpenBusinessActivity.start(getActivity());
                }
            }

            @Override
            public void onFailure(Call<UserSureName> call, Throwable t) {
                cancelProgressDlg();
            }
        });
    }

}
