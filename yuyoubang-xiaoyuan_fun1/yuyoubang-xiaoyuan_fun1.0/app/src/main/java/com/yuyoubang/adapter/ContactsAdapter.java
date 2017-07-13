package com.yuyoubang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuyoubang.R;
import com.yuyoubang.activity.sort.SortModel;
import com.yuyoubang.bean.RemoveUser;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.manager.ImageOption;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.TimeUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.CircleImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsAdapter extends BaseAdapter implements SectionIndexer {
    private List<SortModel> list = null;
    private Context mContext;
    private String flags;
    List<Long> listSelectorId = new ArrayList<>();

    public ContactsAdapter(Context mContext, List<SortModel> list, String flags) {
        this.mContext = mContext;
        this.list = list;
        this.flags = flags;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_contacts_item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.avatar = (CircleImageView) view.findViewById(R.id.avatar);
            viewHolder.iv_sex = (ImageView) view.findViewById(R.id.iv_sex);
            viewHolder.cb = (ImageView) view.findViewById(R.id.cb);
            viewHolder.trip_old = (TextView) view.findViewById(R.id.trip_old);
            viewHolder.trip_location = (TextView) view.findViewById(R.id.trip_location);
            viewHolder.messageLayout = (LinearLayout) view.findViewById(R.id.item_message);
            viewHolder.bg_color = (LinearLayout)view.findViewById(R.id.bg_color);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //���position��ȡ���������ĸ��Char asciiֵ
        int section = getSectionForPosition(position);

        //���ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(list.get(position).getGender())) {
            if (Integer.valueOf(list.get(position).getGender()) == 0) {
                viewHolder.iv_sex.setImageResource(R.mipmap.sex_men);
                viewHolder.bg_color.setBackgroundResource(R.drawable.bg_color_ff9600);
                viewHolder.trip_location.setBackgroundResource(R.drawable.bg_color_ff9600);
            } else {
                viewHolder.iv_sex.setImageResource(R.mipmap.sex_women);
                viewHolder.bg_color.setBackgroundResource(R.drawable.bg_color_fd89cb);
                viewHolder.trip_location.setBackgroundResource(R.drawable.bg_color_fd89cb);
            }
        }

        long birthday = list.get(position).getBirthday();
        if (birthday != 0) {
            try {
                Date longToData = TimeUtil.getLongToData(birthday);
                int age = TimeUtil.getAge(longToData);
                viewHolder.trip_old.setText(age + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ImageLoader.getInstance().displayImage(list.get(position).getAvatarUrl(), viewHolder.avatar, ImageOption.defaultOptions);

        viewHolder.tvTitle.setText(this.list.get(position).getName());
        viewHolder.trip_location.setText(this.list.get(position).getLocation());


        if (flags.equals("1000")) {
            viewHolder.messageLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDelDialog(position);
                    return true;
                }
            });

            viewHolder.messageLayout.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    ChatActivity.start(mContext, String.valueOf(list.get(position).getId()), ChatActivity.SINGLE);
                }
            });
        } else {

            if (flags.equals("1002")) {
                if (listSelectorId.contains(list.get(position).getId())) {
                    viewHolder.cb.setVisibility(View.VISIBLE);
//                    list.get(position).setIsChoose(1);
                    list.get(position).setIsSelector(1);
                } else if (list.get(position).getIsChoose() == 1) {
                    viewHolder.cb.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.cb.setVisibility(View.GONE);
                }
            }
            if (flags.equals("1001")) {
                if (list.get(position).getIsChoose() == 1) {
                    viewHolder.cb.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.cb.setVisibility(View.GONE);
                }
            }
//            if (list.get(position).getIsChoose() == 1 || list.get(position).getIsSelector() == 1) {
//                viewHolder.cb.setVisibility(View.VISIBLE);
//            } else {
//                viewHolder.cb.setVisibility(View.GONE);
//            }
            /*viewHolder.messageLayout.setOnClickListener(new OnClickListener() {
                @Override
                protected void clickOperate() {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIsChoose(0);
                    }
                }
            });*/
        }
        return view;
    }

    private void showDelDialog(final int position) {
        new AlertDialog.Builder(mContext).setCancelable(true)
                .setTitle("确定删除该好友？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //2.删除远程(取消关注)
                        cancelCare(position);
                    }
                }).show();
    }

    private void cancelCare(final int position) {
        FormBody formBody = new FormBody.Builder()
                .add("to_uid", String.valueOf(list.get(position).getId()))
                .add("uid", PreferenceUtils.getsessionId(mContext))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<RemoveUser> followUserCall = findApi.remove_follow_user(formBody);
        followUserCall.enqueue(new Callback<RemoveUser>() {
            @Override
            public void onResponse(Call<RemoveUser> call, Response<RemoveUser> response) {
                RemoveUser body = response.body();
                if (body.getError_code() == 0) {
                    list.remove(position);
                    notifyDataSetChanged();
                    //1.删除本地
                    /*resultList.remove(position);
                    sourceDateList = filledData(resultList);
                    Collections.sort(sourceDateList, pinyinComparator);
                    adapter.updateListView(sourceDateList);
                    adapter.notifyDataSetChanged();*/
                    ToastUtils.showShort("删除成功");
                }
            }

            @Override
            public void onFailure(Call<RemoveUser> call, Throwable t) {

            }
        });
    }


    public List<SortModel> getList() {
        return list;
    }


    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        TextView trip_old;
        TextView trip_location;
        CircleImageView avatar;
        ImageView iv_sex;
        ImageView cb;
        LinearLayout messageLayout;
        LinearLayout bg_color;
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