package com.yuyoubang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.sort.SortModel;
import com.yuyoubang.bean.ApplyGroup;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hongchen on 16/10/25.
 */

public class PostTeamsAdapter extends BaseAdapter implements SectionIndexer {

    private List<SortModel> list = null;
    private Context mContext;
    List<Long> listSelectorId = new ArrayList<>();

    public PostTeamsAdapter(Context mContext, List<SortModel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * ��ListView��ݷ���仯ʱ,���ô˷���������ListView
     *
     * @param list
     */
    public void updateListView(List<SortModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setSelectorID(List<Long> selectorID) {
        listSelectorId = selectorID;
    }

    public int getCount() {
        return this.list.size();
    }

    public List<SortModel> getData() {
        return list;
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final SortModel mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_join_team, null);


            viewHolder.rootLayout = (LinearLayout) view.findViewById(R.id.root_layout);
            viewHolder.tvNum = (TextView) view.findViewById(R.id.tv_team_name);
            viewHolder.teamAvatar = (RoundImageView) view.findViewById(R.id.iv_avatar);
            viewHolder.line = view.findViewById(R.id.line_right_arrow_blue);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getTeamUrl(), viewHolder.teamAvatar, ImageOption.default_group_avatar);
        viewHolder.tvNum.setText(list.get(position).getTeamName()/* + "(" + 0 + ")"*/);


        viewHolder.rootLayout.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                showDelDialog(String.valueOf(list.get(position).getId()), list.get(position).getGroup_id(), list.get(position).getTeamUrl(), list.get(position).getTeamName());
            }
        });

        return view;
    }

    private void showDelDialog(final String uid, final String group_id, final String avatar, final String name) {
        new AlertDialog.Builder(mContext).setCancelable(true)
                .setTitle("是否申请加入[" + name + "]小组")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        sendTeamsInvitation(uid, group_id, avatar, name);
                    }
                }).show();
    }

    private void sendTeamsInvitation(String uid, String groupId, String description, String groupName) {
        FormBody formBody = new FormBody.Builder()
                .add("group_id", groupId)
                .add("group_name", groupName)
                .add("group_pic", description)
                .add("ids", uid)
                .add("type", "0")//申请入群
                .add("uid", PreferenceUtils.getsessionId(mContext))
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<ApplyGroup> applyGroupCall = homeApi.apply_to_group(formBody);
        applyGroupCall.enqueue(new Callback<ApplyGroup>() {
            @Override
            public void onResponse(Call<ApplyGroup> call, Response<ApplyGroup> response) {
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("发送成功, 等待群主同意");
                }
            }

            @Override
            public void onFailure(Call<ApplyGroup> call, Throwable t) {
                ToastUtils.showShort("发送失败");
            }
        });
    }


    public List<SortModel> getList() {
        return list;
    }


    final static class ViewHolder {
        LinearLayout rootLayout;
        TextView tvNum;
        RoundImageView teamAvatar;
        View line;
    }


    /**
     * ���ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    /**
     * ��ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
