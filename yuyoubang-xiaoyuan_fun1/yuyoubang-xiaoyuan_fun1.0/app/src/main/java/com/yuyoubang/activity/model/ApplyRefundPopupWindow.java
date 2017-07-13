package com.yuyoubang.activity.model;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.listener.ApplyRefundClickListener;
import com.yuyoubang.listener.IsCloseClickListener;
import com.yuyoubang.utils.ToastUtils;


/**
 * 退出账号
 * Created by hongchen on 16/10/18.
 */

public class ApplyRefundPopupWindow extends PopupWindow implements View.OnClickListener {
    private ImageView cancel;
    private TextView sure;
    private Context context;
    private ApplyRefundClickListener isLogoutClickListener;
    private final View mMenuView;
    private final EditText et_pay_type;
    private final EditText et_num;
    private final EditText et_because;

    public ApplyRefundPopupWindow(Context context, ApplyRefundClickListener isLogoutClickListener) {
        super(context);
        this.context = context;
        this.isLogoutClickListener = isLogoutClickListener;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popwindow_refund_layout, null);

        cancel = (ImageView) mMenuView.findViewById(R.id.iv_close);
        sure = (TextView) mMenuView.findViewById(R.id.tv_sure);
        et_pay_type = (EditText) mMenuView.findViewById(R.id.et_pay_type);
        et_num = (EditText) mMenuView.findViewById(R.id.et_num);
        et_because = (EditText) mMenuView.findViewById(R.id.et_because);

        // 设置按钮监听
        sure.setOnClickListener(this);
        cancel.setOnClickListener(this);

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int top = mMenuView.findViewById(R.id.pop_layout).getTop();
                int left = mMenuView.findViewById(R.id.pop_layout).getLeft();
                int width = mMenuView.findViewById(R.id.pop_layout).getWidth();
                int height = mMenuView.findViewById(R.id.pop_layout).getHeight();

                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < top) {
                        dismiss();
                    }
                    if (y > top + height) {
                        dismiss();
                    }
                    if (x < left) {
                        dismiss();
                    }
                    if (x > left + width) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ActionBar.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x50000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sure:
                String type = et_pay_type.getText().toString().trim();
                String num = et_num.getText().toString().trim();
                String because = et_because.getText().toString().trim();
                if (TextUtils.isEmpty(type)) {
                    ToastUtils.showShort("请输入退款方式");
                    return;
                }
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请输入退款账号");
                    return;
                }
                if (TextUtils.isEmpty(because)) {
                    ToastUtils.showShort("请输入退款原因");
                    return;
                }
                isLogoutClickListener.sure(type, num, because);
                dismiss();
                break;
            case R.id.iv_close:
                isLogoutClickListener.notSure();
                dismiss();
                break;
            default:
                break;
        }
    }

}
