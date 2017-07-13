package com.yuyoubang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.findfollowinfo.OtherHome;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.view.MultiImageView;
import com.yuyoubang.view.RoundImageView;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hongchen
 */

public class FindOtherAdapter extends MultiBaseAdapter<OtherHome.ResultBean.ListBean> {
    private Context context;
    private RoundImageView avatar;
    private TextView other_name;
    private TextView trip_old;
    private TextView trip_location;
    private TextView tv_time_ago;
    private TextView intro;
    private TextView action;
    private TextView follow;
    private TextView like;
    private ImageView bg;
    private String location = "";

    private TagFlowLayout allFlowLayout;
    private TagAdapter<String> tagAdapter;

    private List<OtherHome.ResultBean.UserBean> otherHome = new ArrayList<>();

    public FindOtherAdapter(Context context, List<OtherHome.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
        setHaed(true);//有头部
    }

    public void setHeadData(List<OtherHome.ResultBean.UserBean> otherHome) {
        this.otherHome = otherHome;
        initHead(otherHome);
    }

    @Override
    protected int getViewType(int position, OtherHome.ResultBean.ListBean data) {
        if (data == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    protected void convert(ViewHolder holder, final OtherHome.ResultBean.ListBean data, int viewType) {
        if (viewType == 0) {
            avatar = holder.getView(R.id.avatar);
            other_name = holder.getView(R.id.other_name);
            trip_old = holder.getView(R.id.trip_old);
            trip_location = holder.getView(R.id.trip_location);
            tv_time_ago = holder.getView(R.id.tv_time_ago);
            intro = holder.getView(R.id.intro);
            action = holder.getView(R.id.action);
            follow = holder.getView(R.id.follow);
            like = holder.getView(R.id.like);
            bg = holder.getView(R.id.bg);
            allFlowLayout = holder.getView(R.id.id_flowlayout_two);
            ImageView iv_sex = holder.getView(R.id.iv_sex);
            ImageView iv_company = holder.getView(R.id.iv_company);
            TextView is_verification = holder.getView(R.id.is_verification);
            LinearLayout bg_color = holder.getView(R.id.bg_color);

            LinearLayout action_layout = holder.getView(R.id.action_layout);
            LinearLayout follow_layout = holder.getView(R.id.follow_layout);
            LinearLayout like_layout = holder.getView(R.id.like_layout);

            follow_layout.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    LaunchOperate.openCareUserList(mContext, String.valueOf(otherHome.get(0).getId()), "1001");
                }
            });

            like_layout.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    LaunchOperate.openLikeList(mContext, String.valueOf(otherHome.get(0).getId()), "1001");
                }
            });

            if (otherHome.get(0).getUser_data().getProfile_pic_url() != null) {
                ImageLoader.getInstance().displayImage(otherHome.get(0).getUser_data().getProfile_pic_url(), avatar, ImageOption.default_banner_options);
            } else {
                avatar.setImageResource(R.mipmap.building);
            }

            if (otherHome.get(0).getUser_data().getVerification_type() == 2) {
                iv_company.setVisibility(View.VISIBLE);
            }
            if (otherHome.get(0).getUser_data().getVerification_type() == 1) {
                is_verification.setVisibility(View.VISIBLE);
            }

            other_name.setText(otherHome.get(0).getUser_data().getUser_name());
//            trip_old.setText("18");
            if (otherHome.get(0).getUser_data().getProvince().contains("市")) {//直辖市
                location = otherHome.get(0).getUser_data().getProvince();
            } else {
                location = otherHome.get(0).getUser_data().getUser_location();
            }

            if (!TextUtils.isEmpty(location)) {
                if (location.contains("市")){
                    String substring = location.substring(0, location.length() - 1);
                    trip_location.setText(substring);
                }else {
                    trip_location.setText(location);
                }
            }
//            trip_location.setText(otherHome.get(0).getUser_data().getUser_location());
            String text = TimeUtil.getTimeShowString(otherHome.get(0).getUpdated_at(), false);
            tv_time_ago.setText(text);
            intro.setText(otherHome.get(0).getUser_data().getUser_desc());
            action.setText(otherHome.get(0).getUser_data().getStatus_total_count() + "");
            follow.setText(otherHome.get(0).getUser_data().getFollow_count() + "");
            like.setText(otherHome.get(0).getUser_data().getFans_count() + "");

            if (otherHome.get(0).getUser_data().getUser_cover_pic_url() != null) {
                ImageLoader.getInstance().displayImage(otherHome.get(0).getUser_data().getUser_cover_pic_url(), bg, ImageOption.default_banner_options);
            }

            if (!TextUtils.isEmpty(otherHome.get(0).getUser_data().getGender())) {
                if (Integer.valueOf(otherHome.get(0).getUser_data().getGender()) == 0) {
                    iv_sex.setImageResource(R.mipmap.sex_men);
                    bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                    trip_location.setBackgroundResource(R.drawable.bg_color_ff9600);
                } else {
                    iv_sex.setImageResource(R.mipmap.sex_women);
                    bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                    trip_location.setBackgroundResource(R.drawable.bg_color_fd89cb);
                }
            }

            long birthday = otherHome.get(0).getUser_data().getBirthday();
            if (birthday != 0) {
                try {
                    Date longToData = TimeUtil.getLongToData(birthday);
                    int age = TimeUtil.getAge(longToData);
                    trip_old.setText(age + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            tagAdapter = new TagAdapter<String>(otherHome.get(0).getUser_data().getUser_tags()) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    LayoutInflater layoutInflater = (LayoutInflater) context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    TextView tv = (TextView) layoutInflater.inflate(R.layout.flag_adapter_tags,
                            allFlowLayout, false);
                    tv.setText(s);
                    return tv;
                }
            };
            allFlowLayout.setAdapter(tagAdapter);

        } else {

            TextView tv_time = holder.getView(R.id.tv_time);
            TextView content = holder.getView(R.id.content);
            MultiImageView ll_content = holder.getView(R.id.ll_content);
            TextView now_location = holder.getView(R.id.now_location);
            TextView tv_like_num = holder.getView(R.id.tv_like_num);
            TextView tv_comment_num = holder.getView(R.id.tv_comment_num);

            content.setText(String.valueOf(data.getData().getContent()));

            String time = TimeUtil.getTimeShowString(data.getCreated_at(), false);
            tv_time.setText(time);

            now_location.setText(data.getData().getLocation());
            final List<String> list = new ArrayList<>();

            list.add(UrlConfig.TempPic);
            list.add(UrlConfig.TempPic);


            if (data.getData().getPics() == null) {
//                ll_content.setList(list);
            } else if (data.getData().getPics().size() == 0) {
//                ll_content.setList(list);
            } else {
                ll_content.setList(data.getData().getPics());
            }

            tv_like_num.setText(data.getData().getStatus_like_count() + "");
            tv_comment_num.setText(data.getData().getStatus_comment_count() + "");

            ll_content.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    LaunchOperate.openImgScan(mContext, data.getData().getPics(), position);
                }
            });
        }
    }

    private void initHead(List<OtherHome.ResultBean.UserBean> otherHome) {

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.find_other_info_head;
        }
        return R.layout.find_other_info_item;
    }
}
