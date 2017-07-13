package com.yuyoubang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.MineVisit;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.view.CircleImageView;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.Date;
import java.util.List;

/**
 * @author xiaoyuan
 */

public class VisitMsgAdapter extends MultiBaseAdapter<MineVisit.ResultBean.ListBean> {
    private Context context;
    private String locationCity = "";

    public VisitMsgAdapter(Context context, List<MineVisit.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, MineVisit.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final MineVisit.ResultBean.ListBean data, int viewType) {
        CircleImageView avatar = holder.getView(R.id.avatar);
        TextView name = holder.getView(R.id.trip_name);
        TextView location = holder.getView(R.id.trip_location);
        TextView is_verification = holder.getView(R.id.is_verification);
        ImageView verification_type = holder.getView(R.id.verification_type);
        ImageView iv_sex = holder.getView(R.id.iv_sex);
        TextView trip_old = holder.getView(R.id.trip_old);
        TextView tv_send_msg = holder.getView(R.id.tv_send_msg);
        LinearLayout foot_layout = holder.getView(R.id.foot_layout);
        LinearLayout bg_color = holder.getView(R.id.bg_color);

        if (data.getUser() != null) {
            if (data.getUser().getUser_data() != null) {
                if (!TextUtils.isEmpty(data.getUser().getUser_data().getUser_name())) {
                    name.setText(data.getUser().getUser_data().getUser_name());
                }
                if (data.getUser().getUser_data().getProvince().contains("市")) {//直辖市
                    locationCity = data.getUser().getUser_data().getProvince();
                } else {
                    locationCity = data.getUser().getUser_data().getUser_location();
                }

                if (!TextUtils.isEmpty(locationCity)) {
                    if (locationCity.contains("市")){
                        String substring = locationCity.substring(0, locationCity.length() - 1);
                        location.setText(substring);
                    }else {
                        location.setText(locationCity);
                    }
                }
//                location.setText(data.getUser().getUser_data().getUser_location());
                if (!TextUtils.isEmpty(data.getUser().getUser_data().getGender())) {
                    if (Integer.valueOf(data.getUser().getUser_data().getGender()) == 0) {
                        iv_sex.setImageResource(R.mipmap.sex_men);
                        bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                        location.setBackgroundResource(R.drawable.bg_color_ff9600);
                    } else {
                        iv_sex.setImageResource(R.mipmap.sex_women);
                        bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                        location.setBackgroundResource(R.drawable.bg_color_fd89cb);
                    }
                }

                if (data.getUser().getUser_data().getVerification_type() == 1) {   //实名
                    verification_type.setVisibility(View.GONE);
                    is_verification.setVisibility(View.VISIBLE);
                } else {
                    verification_type.setVisibility(View.GONE);
                }

                if (data.getUser().getUser_data().getVerification_type() == 2) {   //企业
                    verification_type.setVisibility(View.GONE);
                    verification_type.setVisibility(View.VISIBLE);
                } else {
                    verification_type.setVisibility(View.GONE);
                }

                long birthday = data.getUser().getUser_data().getBirthday();
                if (birthday != 0) {
                    try {
                        Date longToData = TimeUtil.getLongToData(birthday);
                        int age = TimeUtil.getAge(longToData);
                        trip_old.setText(age + "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                ImageLoader.getInstance().displayImage(data.getUser().getUser_data().getProfile_pic_url(), avatar, ImageOption.defaultOptions);

            }
        }
        tv_send_msg.setText(TimeUtil.getString(data.getCreated_at()));

        foot_layout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOtherHome(mContext, String.valueOf(data.getUid()));
            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_visit_msg_item;
    }


}
