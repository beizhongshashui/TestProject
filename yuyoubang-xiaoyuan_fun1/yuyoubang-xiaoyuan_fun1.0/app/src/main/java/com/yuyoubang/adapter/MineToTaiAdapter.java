package com.yuyoubang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.bean.RemoveState;
import com.yuyoubang.bean.mine.MineDoTan;
import com.yuyoubang.config.UrlConfig;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.MineApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.MultiImageView;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.base.MultiBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author xiaoyuan
 */

public class MineToTaiAdapter extends MultiBaseAdapter<MineDoTan.ResultBean.ListBean> {
    private Context context;

    public MineToTaiAdapter(Context context, List<MineDoTan.ResultBean.ListBean> listBean, boolean isOpenLoadMore) {
        super(context, listBean, isOpenLoadMore);
        this.context = context;
    }

    @Override
    protected int getViewType(int position, MineDoTan.ResultBean.ListBean data) {
        return 1;
    }

    @Override
    protected void convert(ViewHolder holder, final MineDoTan.ResultBean.ListBean data, int viewType) {
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView content = holder.getView(R.id.content);
        MultiImageView ll_content = holder.getView(R.id.ll_content);
        TextView now_location = holder.getView(R.id.now_location);
        TextView tv_like_num = holder.getView(R.id.tv_like_num);
        TextView tv_comment_num = holder.getView(R.id.tv_comment_num);
        ImageView del_state = holder.getView(R.id.del_state);

        String time = TimeUtil.getTimeShowString(data.getCreated_at(), false);
        tv_time.setText(time);

        if(!TextUtils.isEmpty(data.getData().getContent())){
            content.setText(String.valueOf(data.getData().getContent()));
        }

        now_location.setText(data.getData().getLocation());
        final List<String> list = new ArrayList<>();

        list.add(UrlConfig.TempPic);
        list.add(UrlConfig.TempPic);

        tv_like_num.setText(data.getData().getStatus_like_count());
        tv_comment_num.setText(data.getData().getStatus_comment_count());

        if (data.getData().getPics() == null) {
//            ll_content.setList(list);
        } else if (data.getData().getPics().size() == 0) {
//            ll_content.setList(list);
        } else {
            ll_content.setList(data.getData().getPics());
        }

        ll_content.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LaunchOperate.openImgScan(mContext, data.getData().getPics(), position);
            }
        });

        del_state.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//删除一条动态
                showDelDialog(String.valueOf(data.getId()));
            }
        });
    }

    private void showDelDialog(final String id) {
        new AlertDialog.Builder(mContext).setCancelable(true)
                .setTitle("确定删除该动态？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        removeState(id);
                    }
                }).show();
    }


    private void removeState(final String id) {
        FormBody formBody = new FormBody.Builder()
                .add("id", id)
                .build();
        MineApi mineApi = RetrofitManager.getTestRetrofit().create(MineApi.class);
        Call<RemoveState> removeStateCall = mineApi.remove_state(formBody);
        removeStateCall.enqueue(new Callback<RemoveState>() {
            @Override
            public void onResponse(Call<RemoveState> call, Response<RemoveState> response) {
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("删除成功");
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mDatas.get(i).getId() == Long.valueOf(id)) {
                            remove(i);
                        }
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RemoveState> call, Throwable t) {

            }
        });
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.find_totai_info_item;
    }
}
