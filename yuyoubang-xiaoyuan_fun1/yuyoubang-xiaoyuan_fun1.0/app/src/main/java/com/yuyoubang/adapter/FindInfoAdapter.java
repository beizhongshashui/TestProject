package com.yuyoubang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.bean.FindCommentList;
import com.yuyoubang.bean.FindFollowInfoBean;
import com.yuyoubang.bean.findfollowinfo.LikeListBean;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.QLog;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.view.CircleImageView;
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

public class FindInfoAdapter extends MultiBaseAdapter<FindCommentList.ResultBean.ListBean> {
    private Context context;
    private RoundImageView findInfoAvator;
    private TextView findInfoName;
    private TextView findInfoAge;
    private TextView findInfoLocation;
    private TextView findInfoFollow;
    private TextView findInfoTime;
    private TextView findInfoContent;
    private MultiImageView llContent;
    private TextView findInfoNowLocation;
    private RecyclerListView findInfoLike;
    private TextView findInfoCommentCount;
    private List<String> strings = new ArrayList<>();
    private TextView numLike;
    private ImageView iv_sex;
    private TextView tv_find_info_age;
    private List<String> pics;
    private LinearLayout bg_color;
    private String locationCity = "";

    public FindInfoAdapter(Context context, List<FindCommentList.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
        setHaed(true);//有头部

        strings.add(UrlConfig.TempPic);
        strings.add(UrlConfig.TempPic);
        strings.add(UrlConfig.TempPic);
        strings.add(UrlConfig.TempPic);
    }


    public void setHeadData(FindFollowInfoBean findFollowInfoBean) {
        initHead(findFollowInfoBean);
    }

    @Override
    protected int getViewType(int position, FindCommentList.ResultBean.ListBean data) {
        if (data == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    protected void convert(ViewHolder holder, final FindCommentList.ResultBean.ListBean data, int viewType) {
        if (viewType == 0) {
            findInfoAvator = holder.getView(R.id.iv_find_info_avtor);
            findInfoName = holder.getView(R.id.tv_find_info_name);
            findInfoAge = holder.getView(R.id.tv_find_info_age);
            findInfoLocation = holder.getView(R.id.tv_find_info_location);
            findInfoFollow = holder.getView(R.id.tv_find_info_follow);
            findInfoTime = holder.getView(R.id.tv_find_info_time);
            findInfoContent = holder.getView(R.id.tv_find_info_content);
            llContent = holder.getView(R.id.ll_content);
            findInfoNowLocation = holder.getView(R.id.tv_find_info_now_location);
            findInfoLike = holder.getView(R.id.tv_find_info_like);
            findInfoCommentCount = holder.getView(R.id.tv_find_info_comment_count);
            numLike = holder.getView(R.id.tv_num);
            iv_sex = holder.getView(R.id.iv_sex);
            tv_find_info_age = holder.getView(R.id.tv_find_info_age);
            bg_color = holder.getView(R.id.bg_color);
        } else {
            CircleImageView img_avatar = holder.getView(R.id.img_avatar);
            TextView user_name = holder.getView(R.id.user_name);
            TextView tv_time = holder.getView(R.id.tv_time);
            TextView tv_content = holder.getView(R.id.tv_content);
            RelativeLayout item = holder.getView(R.id.item);

            /*item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    backDialog(data);
                    return false;
                }
            });*/
            if (data.getUser().getUser_data().getProfile_pic_url() != null) {
                ImageLoader.getInstance().displayImage(data.getUser().getUser_data().getProfile_pic_url(), img_avatar, ImageOption.defaultOptions);
            } else {
                img_avatar.setImageResource(R.mipmap.building);
            }
            user_name.setText(data.getUser().getUser_data().getUser_name());
            tv_time.setText(TimeUtil.getDateToString(data.getCreated_at()));
            tv_content.setText(data.getOp_data().getComment());

            if (mDatas.size() > 1) {
                findInfoCommentCount.setText("评价 " + (mDatas.size() - 1));
            } else {
                findInfoCommentCount.setText("评价 " + 0);
            }
        }
    }

    private void initHead(final FindFollowInfoBean findFollowInfoBean) {
        if (findFollowInfoBean.getResult() != null) {
            if (findFollowInfoBean.getResult().getStatus() != null) {
                if (findFollowInfoBean.getResult().getStatus().size() > 0) {
                    findInfoName.setText(findFollowInfoBean.getResult().getStatus().get(0).getData().getUser_name());
                    QLog.d("FindInfoAdapter",""+findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getProvince());
                    QLog.d("FindInfoAdapter","user_location"+findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getUser_location());
                    if (findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getUser_location().contains("市")) {//直辖市
                        locationCity = findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getUser_location();
                    } else {
                        locationCity = findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getUser_location();
                    }

                    if (!TextUtils.isEmpty(locationCity)) {
                        if (locationCity.contains("市")){
                            String substring = locationCity.substring(0, locationCity.length() - 1);
                            findInfoLocation.setText(substring);
                        }else {
                            findInfoLocation.setText(locationCity);
                        }
                    }
//                    findInfoLocation.setText(findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getUser_location());
                    if (findFollowInfoBean.getResult().getStatus().get(0).getData().getIs_like() == 0) {
                        findInfoFollow.setText("未关注");
                    } else {
                        findInfoFollow.setText("已关注");
                    }
                    if (findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getProfile_pic_url() == null) {
                        ImageLoader.getInstance().displayImage(UrlConfig.TempPic, findInfoAvator, ImageOption.defaultOptions);
                    } else {
                        ImageLoader.getInstance().displayImage(findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getProfile_pic_url(), findInfoAvator, ImageOption.defaultOptions);
                    }

                    if (!TextUtils.isEmpty(findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getGender())) {
                        if (Integer.valueOf(findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getGender()) == 0) {
                            iv_sex.setImageResource(R.mipmap.sex_men);
                            bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                            findInfoLocation.setBackgroundResource(R.drawable.bg_color_ff9600);
                        } else {
                            iv_sex.setImageResource(R.mipmap.sex_women);
                            bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                            findInfoLocation.setBackgroundResource(R.drawable.bg_color_fd89cb);
                        }
                    }

                    long birthday = findFollowInfoBean.getResult().getStatus().get(0).getUser().getUser_data().getBirthday();
                    if (birthday != 0) {
                        try {
                            Date longToData = TimeUtil.getLongToData(birthday);
                            int age = TimeUtil.getAge(longToData);
                            tv_find_info_age.setText(age + "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    String text = TimeUtil.getTimeShowString(findFollowInfoBean.getResult().getStatus().get(0).getCreated_at(), false);
                    findInfoTime.setText(text);

                    if (findFollowInfoBean.getResult().getStatus().get(0).getData().getPics() == null) {
                    } else if (findFollowInfoBean.getResult().getStatus().get(0).getData().getPics().size() == 0) {
                    } else {
                        pics = findFollowInfoBean.getResult().getStatus().get(0).getData().getPics();
                        llContent.setList(pics);
                    }
                    findInfoContent.setText(findFollowInfoBean.getResult().getStatus().get(0).getData().getContent() + "");
                    findInfoNowLocation.setText(findFollowInfoBean.getResult().getStatus().get(0).getData().getLocation());

                }
            }
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        findInfoLike.setLayoutManager(mLayoutManager);

        List<String> list = new ArrayList<>();
        list.add("");
        List<LikeListBean> like_list = findFollowInfoBean.getResult().getLike_list();//接口有问题
        if (like_list == null || like_list.size() == 0) {
            numLike.setText("0");
        }

        if (like_list.size() > 0) {
            for (int i = 0; i < like_list.size(); i++) {
                list.add(findFollowInfoBean.getResult().getLike_list().get(i).getUser().getUser_data().getProfile_pic_url());
            }
            numLike.setText(like_list.size() + "");
        }

        numLike.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//点赞列表
                LaunchOperate.openStatusLikeActivity(mContext, String.valueOf(findFollowInfoBean.getResult().getStatus().get(0).getId()));
            }
        });


        findInfoLike.setAdapter(new FindLikeAvatarAdapter(findInfoLike, mContext, list));
        findInfoLike.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    LaunchOperate.openStatusLikeActivity(mContext, String.valueOf(findFollowInfoBean.getResult().getStatus().get(0).getId()));
                } else {
                    LaunchOperate.openOtherHome(mContext, String.valueOf(findFollowInfoBean.getResult().getLike_list().get(position - 1).getUid()));
                }
            }
        });

        findInfoLike.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                LaunchOperate.openStatusLikeActivity(mContext, String.valueOf(findFollowInfoBean.getResult().getStatus().get(0).getId()));
                return false;
            }
        });

        llContent.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LaunchOperate.openImgScan(mContext, pics, position);
            }
        });
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.find_action_info_head;
        }
        return R.layout.find_follow_info_item;
    }

    private void backDialog(final FindCommentList.ResultBean.ListBean listBean) {
        new AlertDialog.Builder(mContext).setCancelable(true)
                .setTitle("确定删除该条评论？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (listBean != null) {
                            //本地删除
                            if (mDatas.size() == 2) {
                                findInfoCommentCount.setText("评价 " + 0);
                            }
                            mDatas.remove(listBean);
                            notifyDataSetChanged();
                            //删除远程
                        }
                    }
                }).show();
    }

    public void delComment(String cmt_id, String status_id) {

    }
}
