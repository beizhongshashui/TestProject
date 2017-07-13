package com.yuyoubang.activity.model;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yuyoubang.R;


/**
 * 退出账号
 * Created by hongchen on 16/10/18.
 */

public class RefundMoneyPopupWindow extends PopupWindow implements View.OnClickListener {
    private ImageView cancel;
    private Context context;
    private final View mMenuView;
    private final TextView et_pay_type;
    private final TextView et_num;
    private final TextView et_because;
    private final TextView et_price;

    public RefundMoneyPopupWindow(Context context,String pay_type,String num,String because,String price ) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popwindow_refund_money_layout, null);

        cancel = (ImageView) mMenuView.findViewById(R.id.iv_close);
        et_pay_type = (TextView) mMenuView.findViewById(R.id.et_pay_type);
        et_num = (TextView) mMenuView.findViewById(R.id.et_num);
        et_because = (TextView) mMenuView.findViewById(R.id.et_because);
        et_price = (TextView) mMenuView.findViewById(R.id.et_price);

        et_pay_type.setText(pay_type);
        et_num.setText(num);
        et_because.setText(because);
        et_price.setText(price);

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
            case R.id.iv_close:
                dismiss();
                break;
            default:
                break;
        }
    }

}
