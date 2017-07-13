package com.yuyoubang.activity.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.model.CommentPopupWindow;
import com.yuyoubang.bean.mine.CommentNew;
import com.yuyoubang.bean.mine.NoCommend;
import com.yuyoubang.listener.IsCloseClickListener;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.otto.CommentBus;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.ToastUtils;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/12/7.
 */

public class CommentActivity extends BaseActivity {

    private ImageView imageView;
    private TextView tv_title;
    private TextView tv_price;
    private NoCommend.ResultBean.ListBean data;
    private TextView tv_click_commit;
    private EditText tv_feed_back_content;
    private RatingBar fullRtb;
    private TextView orde_time;
    private int tempRat;
    private TextView intro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        data = (NoCommend.ResultBean.ListBean) getIntent().getSerializableExtra("data");
        imageView = getViewById(R.id.iv_pic);
        tv_title = getViewById(R.id.tv_title);
        tv_price = getViewById(R.id.tv_price);
        tv_click_commit = getViewById(R.id.tv_click_commit);
        tv_feed_back_content = getViewById(R.id.tv_feed_back_content);
        fullRtb = getViewById(R.id.full_assess_rating);
        orde_time = getViewById(R.id.orde_time);
        intro = getViewById(R.id.intro);
    }

    private void setListener() {
        ImageLoader.getInstance().displayImage(data.getTrip().getData().getTrip_cover_pic(), imageView, ImageOption.defaultOptions);
        tv_title.setText(data.getOp_data().getTrip_name());
        tv_price.setText(data.getOp_data().getTotal_price() + "");
        fullRtb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rat = Float.valueOf(v).intValue();
                switch (rat) {
                    case 1:
                        intro.setText("活动糟糕，不建议去");
                        break;
                    case 2:
                        intro.setText("比较差，勉强能接受");
                        break;
                    case 3:
                        intro.setText("一般般，没什么感");
                        break;
                    case 4:
                        intro.setText("活动很好，值得推荐");
                        break;
                    case 5:
                        intro.setText("体验非常好，强烈推荐");
                        break;
                    default:
                        intro.setText("点击来为活动评分");
                        break;
                }
            }
        });
        //// TODO: 16/12/14 缺少时间
//        orde_time.setText(DateUtils.getDateToMonth(data.getData().getStart_time()) + "一" + DateUtils.getDateToMonth(data.getData().getEnd_time()));
        tv_click_commit.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//发表评论
                newComment();
            }
        });
    }

    private void newComment() {
        float rating = fullRtb.getRating();
        tempRat = Float.valueOf(rating).intValue();
        if (tempRat == 0) {
            ToastUtils.showShort("请选一个评论等级");
            return;
        }
        String rat = String.valueOf(tempRat);
        String content = tv_feed_back_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showShort("评论内容不能为空");
            return;
        }
        FormBody formBody = new FormBody.Builder()
                .add("biz_uid", data.getOp_data().getTrip_biz_uid() + "")
                .add("comment", content)
                .add("trip_id", data.getOp_data().getTrip_id() + "")
                .add("trip_participate_id", data.getId() + "")
                .add("uid", data.getUid() + "")
                .add("value", rat)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<CommentNew> commentNewCall = mineApi.new_comment(formBody);
        commentNewCall.enqueue(new Callback<CommentNew>() {
            @Override
            public void onResponse(Call<CommentNew> call, Response<CommentNew> response) {
                CommentNew commentNew = response.body();
                if (commentNew != null) {
                    if (commentNew.getResult() != null) {
                        if (commentNew.getResult().size() > 0) {
                            ToastUtils.showShort("评论成功");
                            CommentBus commentBus = new CommentBus();
                            commentBus.data = data;
                            AppBus.getInstance().post(commentBus);
                            finish();
                        } else {
                            ToastUtils.showShort("评论失败");
                        }
                    } else {
                        ToastUtils.showShort("评论失败");
                    }
                } else {
                    ToastUtils.showShort("评论失败");
                }
            }

            @Override
            public void onFailure(Call<CommentNew> call, Throwable t) {
                LogUtils.e("CommentActivity", t);
                ToastUtils.showShort("评论失败");
            }
        });
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_comment;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        showPopuWindow();
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("评价");
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                showPopuWindow();
            }
        });
    }

    private void showPopuWindow() {
        CommentPopupWindow msgPopupWindow = new CommentPopupWindow(this, new IsCloseClickListener() {
            @Override
            public void sure() {
                finish();
            }

            @Override
            public void notSure() {
            }
        });
        msgPopupWindow.showAtLocation(
                getViewById(R.id.test), Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
