package com.yuyoubang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.bean.FindFollowBean;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.MultiImageView;
import com.yuyoubang.view.RoundImageView;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;
import com.yuyoubang.widget.recycleview.OnItemClickListener;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoyuan
 */

public class FindFollowAdapter extends MultiBaseAdapter<FindFollowBean.ResultBean.ListBean> {
    private Context context;
    private FindFollowMoreAdapter adapter;
    private List<FindRecomendBean.ResultBean.ListBean> listBeen = new ArrayList<>();
    private RecyclerListView recyclerListView;
    private TextView more_user;
    private List<String> tempList = new ArrayList<>();
    private String locationCity = "";

    public FindFollowAdapter(Context context, List<FindFollowBean.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
        setHaed(true);//有头部
    }

    public void setHeadData(List<FindRecomendBean.ResultBean.ListBean> data) {
        this.listBeen = data;
        if (recyclerListView != null) {
            initHead(recyclerListView, data);
        }
    }

    @Override
    protected int getViewType(int position, FindFollowBean.ResultBean.ListBean data) {
        if (data == null) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    protected void convert(ViewHolder holder, final FindFollowBean.ResultBean.ListBean data, int viewType) {
        if (viewType == 0) {
            recyclerListView = holder.getView(R.id.more_follow);
            more_user = holder.getView(R.id.more_user);

            more_user.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    LaunchOperate.openRecommendUserList(context);
                }
            });
        } else {
            MultiImageView multiImageView = holder.getView(R.id.ll_content);
            RoundImageView avatar = holder.getView(R.id.avatar);
            TextView name = holder.getView(R.id.trip_name);
            TextView location = holder.getView(R.id.trip_location);
            final TextView content = holder.getView(R.id.content);
            TextView now_location = holder.getView(R.id.now_location);
            TextView tv_like_num = holder.getView(R.id.tv_like_num);
            TextView tv_comment_num = holder.getView(R.id.tv_comment_num);
            TextView message_item_time = holder.getView(R.id.message_item_time);
            TextView trip_old = holder.getView(R.id.trip_old);
            ImageView iv_sex = holder.getView(R.id.iv_sex);
            LinearLayout bg_color = holder.getView(R.id.bg_color);
            TextView line = holder.getView(R.id.line);

            name.setText(data.getData().getUser_name());
            content.setText(data.getData().getContent());
            if (TextUtils.isEmpty(data.getData().getLocation())) {
                now_location.setVisibility(View.GONE);
                line.setVisibility(View.VISIBLE);
            } else {
                now_location.setVisibility(View.VISIBLE);
                line.setVisibility(View.GONE);
                now_location.setText(data.getData().getLocation());
            }
            tv_like_num.setText(data.getData().getLike_count());
            tv_comment_num.setText(data.getData().getComment_count());

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
//                location.setText(data.getUser().getUser_data().getUser_location());
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
                if (data.getUser().getUser_data().getGender() == 0) {//#5895ED
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

            String text = TimeUtil.getTimeShowString(data.getCreated_at(), false);
            message_item_time.setText(text);

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
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.find_follow_head;
        }
        return R.layout.find_follow_item;
    }


    private void initHead(RecyclerListView recyclerListView, List<FindRecomendBean.ResultBean.ListBean> listBeen) {
        /*LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerListView.setLayoutManager(mLayoutManager);*/
        adapter = new FindFollowMoreAdapter(recyclerListView, context, listBeen);
        recyclerListView.setAdapter(adapter);
    }

}
