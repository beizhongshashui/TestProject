package com.yuyoubang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.bean.FindRecomendBean;
import com.yuyoubang.bean.SysMessage;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.List;

/**
 * @author xiaoyuan
 */

public class SysMsgAdapter extends MultiBaseAdapter<SysMessage.ResultBean.ListBean> {
    private Context context;

    public SysMsgAdapter(Context context, List<SysMessage.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, SysMessage.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, SysMessage.ResultBean.ListBean data, int viewType) {
        TextView rmb = holder.getView(R.id.rmb);
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_send_msg = holder.getView(R.id.tv_send_msg);

        rmb.setText(data.getData().getName());
        tv_title.setText(data.getData().getMessage());
        tv_send_msg.setText(TimeUtil.getString(data.getCreated_at()));
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_sys_msg_item;
    }


}
