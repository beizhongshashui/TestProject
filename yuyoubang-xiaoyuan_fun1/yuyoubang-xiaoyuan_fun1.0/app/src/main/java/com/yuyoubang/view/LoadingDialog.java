package com.yuyoubang.view;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyoubang.R;


/**
 * @author xiaoyuan
 */
public class LoadingDialog extends Dialog {
    private boolean cancelable = false;
    private ImageView loading;
    private Context mContext;
    private TextView tvContent;

    public LoadingDialog(Context context, String text) {
        super(context, R.style.dialog_style);
        init(context, text);
    }

    private void init(Context context, String text) {
        View contentView = View.inflate(getContext(), R.layout.progress_dialog, null);
        setContentView(contentView);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    dismiss();
                }
            }
        });

    }


    @Override
    public void dismiss() {
        super.dismiss();
    }


    @Override
    public void setCancelable(boolean flag) {
        cancelable = flag;
        super.setCancelable(flag);
    }

}
