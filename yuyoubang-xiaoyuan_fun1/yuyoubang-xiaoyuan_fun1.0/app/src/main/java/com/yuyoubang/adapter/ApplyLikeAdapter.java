package com.yuyoubang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.RemoveUser;
import com.yuyoubang.bean.SearchUser;
import com.yuyoubang.bean.TripOrderUser;
import com.yuyoubang.bean.findfollowinfo.FollowUser;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.view.CircleImageView;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author xiaoyuan
 */

public class ApplyLikeAdapter extends MultiBaseAdapter<CheckUser.ResultBean.ListBean> {
    private Context context;
    private TextView tv_find_info_follow;
    private String locationCity = "";

    public ApplyLikeAdapter(Context context, List<CheckUser.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, CheckUser.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final CheckUser.ResultBean.ListBean data, int viewType) {
        CircleImageView avatar = holder.getView(R.id.avatar);
        TextView name = holder.getView(R.id.trip_name);
        TextView location = holder.getView(R.id.trip_location);
        TextView content = holder.getView(R.id.content);
        TextView is_verification = holder.getView(R.id.is_verification);
        tv_find_info_follow = holder.getView(R.id.tv_find_info_follow);
        TextView trip_old = holder.getView(R.id.trip_old);
        ImageView verification_type = holder.getView(R.id.verification_type);
        ImageView iv_sex = holder.getView(R.id.iv_sex);
        RelativeLayout avatar_layout = holder.getView(R.id.avatar_layout);
        LinearLayout item_layout = holder.getView(R.id.item_layout);
        LinearLayout bg_color = holder.getView(R.id.bg_color);

        name.setText(data.getUser_data().getUser_name());
        if (data.getUser_data().getProvince().contains("市")) {//直辖市
            locationCity = data.getUser_data().getProvince();
        } else {
            locationCity = data.getUser_data().getUser_location();
        }

        if (!TextUtils.isEmpty(locationCity)) {
            if (locationCity.contains("市")){
                String substring = locationCity.substring(0, locationCity.length() - 1);
                location.setText(substring);
            }else {
                location.setText(locationCity);
            }
        }
//        location.setText(data.getUser_data().getUser_location());
        if (data.getUser_data().getUser_desc() != null) {
            content.setText(data.getUser_data().getUser_desc());
        } else {
            content.setText("");
        }

        if (String.valueOf(data.getId()).equals(PreferenceUtils.getsessionId(mContext))) {
            tv_find_info_follow.setVisibility(View.GONE);
        } else {
            tv_find_info_follow.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(data.getUser_data().getGender())) {
            if (Integer.valueOf(data.getUser_data().getGender()) == 0) {
                iv_sex.setImageResource(R.mipmap.sex_men);
                bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                location.setBackgroundResource(R.drawable.bg_color_ff9600);
            } else {
                iv_sex.setImageResource(R.mipmap.sex_women);
                bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                location.setBackgroundResource(R.drawable.bg_color_fd89cb);
            }
        }

        long birthday = data.getUser_data().getBirthday();
        if (birthday != 0) {
            try {
                Date longToData = TimeUtil.getLongToData(birthday);
                int age = TimeUtil.getAge(longToData);
                trip_old.setText(age + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (data.getUser_data().getVerification_type() == 1) {   //实名
            verification_type.setVisibility(View.GONE);
            is_verification.setVisibility(View.VISIBLE);
        } else {
            verification_type.setVisibility(View.GONE);
        }

        if (data.getUser_data().getVerification_type() == 2) {   //企业
            verification_type.setVisibility(View.GONE);
            verification_type.setVisibility(View.VISIBLE);
        } else {
            verification_type.setVisibility(View.GONE);
        }

        ImageLoader.getInstance().displayImage(data.getUser_data().getProfile_pic_url(), avatar, ImageOption.defaultOptions);

        if (data.getUser_data().getIs_like_to_user() == 0) {//未关注他人
            tv_find_info_follow.setText("+ 关注");
            tv_find_info_follow.setTextColor(Color.BLACK);
            tv_find_info_follow.setBackgroundResource(R.drawable.hot_city_grey_circle_normal);
        } else {
            tv_find_info_follow.setText("取消关注");
            tv_find_info_follow.setTextColor(Color.WHITE);
            tv_find_info_follow.setBackgroundResource(R.drawable.hot_city_grey_circle_pressed);
        }

        tv_find_info_follow.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//是否关注
                if (data.getUser_data().getIs_like_to_user() == 0) {//未关注他人
                    followUser(String.valueOf(data.getId()));
                } else {
                    cancelCare(String.valueOf(data.getId()));
                }
            }
        });

        avatar_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (String.valueOf(data.getId()).equals(PreferenceUtils.getsessionId(mContext))) {
                } else {
                    LaunchOperate.openOtherHome(mContext, String.valueOf(data.getId()));
                }
            }
        });

        item_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                if (String.valueOf(data.getId()).equals(PreferenceUtils.getsessionId(mContext))) {
                } else {
                    LaunchOperate.openOtherHome(mContext, String.valueOf(data.getId()));
                }
            }
        });
    }

    private void followUser(final String uid) {
        FormBody formBody = new FormBody.Builder()
                .add("to_uid", uid)
                .add("uid", PreferenceUtils.getsessionId(mContext))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<FollowUser> followUserCall = findApi.follow_user(formBody);
        followUserCall.enqueue(new Callback<FollowUser>() {
            @Override
            public void onResponse(Call<FollowUser> call, Response<FollowUser> response) {
                if (response.body().getError_code() == 0) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mDatas.get(i).getId() == Long.valueOf(uid)) {
                            mDatas.get(i).getUser_data().setIs_like_to_user(1);
                        }
                    }
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FollowUser> call, Throwable t) {

            }
        });
    }

    private void cancelCare(final String uid) {
        FormBody formBody = new FormBody.Builder()
                .add("to_uid", uid)
                .add("uid", PreferenceUtils.getsessionId(mContext))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<RemoveUser> followUserCall = findApi.remove_follow_user(formBody);
        followUserCall.enqueue(new Callback<RemoveUser>() {
            @Override
            public void onResponse(Call<RemoveUser> call, Response<RemoveUser> response) {
                RemoveUser body = response.body();
                if (body.getError_code() == 0) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mDatas.get(i).getId() == Long.valueOf(uid)) {
                            mDatas.get(i).getUser_data().setIs_like_to_user(0);
                        }
                    }
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RemoveUser> call, Throwable t) {

            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.find_more_like_item;
    }


}
