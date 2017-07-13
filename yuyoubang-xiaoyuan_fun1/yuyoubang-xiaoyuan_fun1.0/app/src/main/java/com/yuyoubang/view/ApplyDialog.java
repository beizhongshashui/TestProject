package com.yuyoubang.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuyoubang.R;

/**
 * Created by xiaoyuan on 16/12/7.
 */
public class ApplyDialog extends AlertDialog {

    private TextView joinGroup;//加群
    private TextView lookApply;//查看报名
    private onJoinGroupListener onJoinGroupListener;
    private onLookApplyListener onLookApplyClick;

    /**
     * 设置\
     *
     */
    public void setJoinclickListener(onJoinGroupListener onJoinGroupListener) {

        this.onJoinGroupListener = onJoinGroupListener;
    }

    /**
     * 设置确定按钮的显示内容和监
     *
     */
    public void setLookApplyOnclickListener( onLookApplyListener onLookApplyClick) {

        this.onLookApplyClick = onLookApplyClick;
    }

    public ApplyDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_dialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        joinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJoinGroupListener != null) {
                    onJoinGroupListener.onJoinGroupClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        lookApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLookApplyClick != null) {
                    onLookApplyClick.onLookApplyClick();
                }
            }
        });
    }


    /**
     * 初始化界面控件
     */
    private void initView() {
        joinGroup = (TextView) findViewById(R.id.join_group);
        lookApply = (TextView) findViewById(R.id.look_apply);
    }



    /**
     * 设置监听接口
     */
    public interface onJoinGroupListener {
        public void onJoinGroupClick();
    }

    public interface onLookApplyListener {
        public void onLookApplyClick();
    }
}
