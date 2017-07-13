package com.yuyoubang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.ChooseStatusResult;
import com.yuyoubang.bean.FindFollowBean;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.view.MultiImageView;
import com.yuyoubang.view.RoundImageView;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoyuan
 */

public class FindSerachAdapter extends MultiBaseAdapter<FindFollowBean.ResultBean.ListBean> {
    private Context context;
    private List<String> tempList = new ArrayList<>();
    private String locationCity = "";

    public FindSerachAdapter(Context context, List<FindFollowBean.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, FindFollowBean.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final FindFollowBean.ResultBean.ListBean data, int viewType) {
        MultiImageView multiImageView = holder.getView(R.id.ll_content);
        RoundImageView avatar = holder.getView(R.id.avatar);
        TextView name = holder.getView(R.id.trip_name);
        TextView location = holder.getView(R.id.trip_location);
        TextView content = holder.getView(R.id.content);
        TextView now_location = holder.getView(R.id.now_location);
        TextView tv_like_num = holder.getView(R.id.tv_like_num);
        TextView tv_comment_num = holder.getView(R.id.tv_comment_num);
        TextView message_item_time = holder.getView(R.id.message_item_time);
        ImageView iv_sex = holder.getView(R.id.iv_sex);
        TextView trip_old = holder.getView(R.id.trip_old);
        LinearLayout bg_color = holder.getView(R.id.bg_color);

        name.setText(data.getData().getUser_name());
        content.setText(data.getData().getContent());
        now_location.setText(data.getData().getLocation());
        tv_like_num.setText(data.getData().getLike_count());
        tv_comment_num.setText(data.getData().getComment_count());

        String text = TimeUtil.getTimeShowString(data.getCreated_at(), false);
        message_item_time.setText(text);

        if (data.getUser() != null) {
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
//            location.setText(data.getUser().getUser_data().getUser_location());
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

            if (data.getUser().getUser_data().getGender() == 0) {
                iv_sex.setImageResource(R.mipmap.sex_men);
                bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                location.setBackgroundResource(R.drawable.bg_color_ff9600);
            } else {
                iv_sex.setImageResource(R.mipmap.sex_women);
                bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                location.setBackgroundResource(R.drawable.bg_color_fd89cb);
            }

            if (data.getUser().getUser_data().getProfile_pic_url() == null) {
                avatar.setImageResource(R.mipmap.building);
            } else {
                ImageLoader.getInstance().displayImage(data.getUser().getUser_data().getProfile_pic_url(), avatar, ImageOption.defaultOptions);
            }
        }

        if (data.getData().getPics() == null) {
            multiImageView.setList(tempList);
        } else if (data.getData().getPics().size() == 0) {
            multiImageView.setList(tempList);
        } else {
            multiImageView.setList(data.getData().getPics());
        }

        multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LaunchOperate.openImgScan(mContext, data.getData().getPics(), position);
            }
        });
        avatar.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openOtherHome(mContext, String.valueOf(data.getUid()));
            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.find_follow_item;
    }

}
