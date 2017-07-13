package com.yuyoubang.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yuyoubang.R;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.LogUtils;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;

/**
 * Created by xiaoyuan on 16/11/17.
 */
public class SharePopwin extends PopupWindow {
    private Context context;

    private View view;
    private TextView cancel;
    private final TextView shareWx;
    private final TextView shareWb;
    private final LinearLayout sharePyq;
    private final TextView shareQq;
    private String trip_id;
    private String titleShare;
    private int flags;
    //    UMImage image;
    private final UMImage umImage;
    private String url;
    private String Text = "[遇游邦] 单身部落, 户外交友, 请上遇游邦";

    public SharePopwin(final Context context, final String trip_id, final String image, final String titleShare, final int flags) {
        super(context);
        this.context = context;
        this.trip_id = trip_id;
        this.titleShare = titleShare;
        this.flags = flags;
        if (TextUtils.isEmpty(image)) {
            umImage = new UMImage(context, UrlConfig.TempPic);
        } else {
            umImage = new UMImage(context, image);
        }
        this.view = LayoutInflater.from(context).inflate(R.layout.share_pop, null);

        cancel = (TextView) view.findViewById(R.id.cancel);
        shareWx = (TextView) view.findViewById(R.id.tv_weixin);
        shareWb = (TextView) view.findViewById(R.id.tv_wb);
        sharePyq = (LinearLayout) view.findViewById(R.id.tv_pyq);
        shareQq = (TextView) view.findViewById(R.id.tv_qq);
        // 取消按钮
        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.pop_anim);

        switch (flags) {
            case 1:
                url = UrlConfig.TestShareUrl + UrlConfig.HOME_SHARE + trip_id + "/" + PreferenceUtils.getsessionId(context);
                break;
            case 2:
                url = UrlConfig.TestShareUrl + UrlConfig.STATUE_SHARE + trip_id + "/" + PreferenceUtils.getsessionId(context);
                break;
        }

        shareWb.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
                        .withMedia(umImage)
                        .withText(Text + url)//微博分享URL得和text放在一起
                        .withTitle(titleShare)
//                        .withTargetUrl(url)
                        .share();
                dismiss();
            }
        });

        shareWx.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                        .withMedia(umImage)
                        .withText(Text)
                        .withTitle(titleShare)
                        .withTargetUrl(url)
                        .share();

                dismiss();
            }
        });
        sharePyq.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                        .withMedia(umImage)
                        .withText(Text)
                        .withTitle(titleShare)
                        .withTargetUrl(url)
                        .share();

                dismiss();
            }
        });
        shareQq.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
                        .withMedia(umImage)
                        .withText(Text)
                        .withTitle(titleShare)
                        .withTargetUrl(url)//QQ必须http开头
                        .share();

                dismiss();
            }
        });
    }

    //分享回调必须在activity里实现onActivityResult方法
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showShort("分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            String message = t.getMessage();
            LogUtils.e("share", t);
            ToastUtils.showShort("分享失败啦");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showShort("分享取消了");
        }
    };
}
