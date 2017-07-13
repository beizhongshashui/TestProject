package com.yuyoubang.activity.msg;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.login.LoginAccountActivity;
import com.yuyoubang.activity.sort.CharacterParser;
import com.yuyoubang.activity.sort.PinyinComparator;
import com.yuyoubang.activity.sort.SideBar;
import com.yuyoubang.activity.sort.SortModel;
import com.yuyoubang.adapter.ContactsAdapter;
import com.yuyoubang.adapter.SetTeamsUserAvatarAdapter;
import com.yuyoubang.app.YuYouBangApp;
import com.yuyoubang.bean.ApplyGroup;
import com.yuyoubang.bean.LoginUserBean;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.bean.UploadBean;
import com.yuyoubang.bean.findfollowinfo.MoreLikeList;
import com.yuyoubang.fragment.message.ChatActivity;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.network.api.HomeApi;
import com.yuyoubang.network.api.UploadApi;
import com.yuyoubang.utils.BitmapUtil;
import com.yuyoubang.utils.BitmapUtils;
import com.yuyoubang.utils.ChatUtil;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.PropertiesUtil;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.widget.recycleview.RecyclerListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.value;


/**
 * Created by hongchen on 16/12/15.
 */

public class ContactsActivity extends BaseActivity {

    private ListView lvAllCity;
    private TextView dialog;
    private SideBar sidebar;
    private EditText searchCity;
    private String cursor;

    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private ContactsAdapter adapter;
    private List<SortModel> sourceDateList;

    private String flag;
    private List<MoreLikeList.ResultBean.ListBean> resultList;
    private RecyclerListView more_follow;
    private SetTeamsUserAvatarAdapter setTeamsUserAvatarAdapter;
    private List<MyBitmapEntity> mEntityList;
    private String sure = "创建(0)";
    private String sureJoin = "添加(0)";
    private int count;
    private int count_1002;
    private LoginUserBean.ResultBean.UserDataBean user;
    private String locationCity = "";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                ToastUtils.showShort("创建成功");
                finish();
            }
            if (msg.what == 101) {
                ToastUtils.showShort("邀请成功, 等待对方同意");
                finish();
                TeamsDetailsActivity.instance.finish();
            }
        }
    };
    private List<TeamsMember.ResultBean.ListBean> memList;
    private String group_id;
    private EMGroup group;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memList = (List<TeamsMember.ResultBean.ListBean>) getIntent().getSerializableExtra("memList");
        try {
            user = PreferenceUtils.deSerialization(PreferenceUtils.getPrefString(this, "user", ""));
            if (user == null) {
                ToastUtils.showShort("账号异常");
                YuYouBangApp.logOut();
                qStartActivity(LoginAccountActivity.class);
                finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        initView();
        setListener();
    }

    private void getData(final String cursor) {
        final String flags = getIntent().getStringExtra("flag");
        onShowProgressDlg();
        this.cursor = cursor;
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body;
        if (TextUtils.isEmpty(cursor)) {
            body = new FormBody.Builder().add("uid", PreferenceUtils.getsessionId(this)).build();
        } else {
            body = new FormBody.Builder().add("cursor", cursor).add("uid", PreferenceUtils.getsessionId(this)).build();
        }
        Call<MoreLikeList> find = findApi.my_follow_list(body);
        find.enqueue(new Callback<MoreLikeList>() {
            @Override
            public void onResponse(Call<MoreLikeList> call, Response<MoreLikeList> response) {
                MoreLikeList moreLikeList = response.body();
                resultList = moreLikeList.getResult().getList();
                if (resultList.size() == 0) {
                    ToastUtils.showShort("您还没有好友, 快去关注用户吧");
                }
                sourceDateList = filledData(resultList);
                Collections.sort(sourceDateList, pinyinComparator);
                adapter = new ContactsAdapter(ContactsActivity.this, sourceDateList, flags);
                lvAllCity.setAdapter(adapter);
                setTeamsUserAvatarAdapter = new SetTeamsUserAvatarAdapter(more_follow, ContactsActivity.this, sourceDateList);
                more_follow.setAdapter(setTeamsUserAvatarAdapter);
//                if (flag.equals("1002")) {
//                    if (memList.size() > resultList.size()) {
//                        for (int i = 0; i < resultList.size(); i++) {
//                            for (int j = 0; j < memList.size(); j++) {
//                                if (resultList.get(i).getUser().getId() == memList.get(j).getId()) {
////                                    adapter.getData().get(i).setIsChoose(1);
//                                    resultList.get(i).getUser().setIsChoose(1);
//                                    adapter.getData().get(i).setIsSelector(1);
////                                    lvAllCity.setEnabled(false);
//                                } else {
//                                }
//                            }
//                        }
//                    } else if (memList.size() < resultList.size()) {
//                        for (int i = 0; i < memList.size(); i++) {
//                            for (int j = 0; j < resultList.size(); j++) {
//                                if (resultList.get(j).getUser().getId() == memList.get(i).getId()) {
////                                    adapter.getData().get(j).setIsChoose(1);
//                                    resultList.get(j).getUser().setIsChoose(1);
//                                    adapter.getData().get(j).setIsSelector(1);
////                                    lvAllCity.setEnabled(false);
//                                } else {
//                                }
//                            }
//                        }
//                    } else {
//                        for (int i = 0; i < memList.size(); i++) {
//                            for (int j = 0; j < resultList.size(); j++) {
//                                if (resultList.get(j).getUser().getId() == memList.get(i).getId()) {
////                                    adapter.getData().get(i).setIsChoose(1);
//                                    resultList.get(i).getUser().setIsChoose(1);
//                                    adapter.getData().get(i).setIsSelector(1);
////                                    lvAllCity.setEnabled(false);
//                                } else {
//                                }
//                            }
//
//                        }
//                    }
//                    /*for (int i = 0; i < memList.size(); i++) {
//                        for (int j = 0; j < adapter.getData().size(); j++) {
//                            if (memList.get(i).getId() == adapter.getData().get(j).getId()) {
//                                adapter.getData().get(i).setIsChoose(1);
//                                lvAllCity.setEnabled(false);
//                            } else {
//                                lvAllCity.setEnabled(true);
//                                adapter.getData().get(i).setIsChoose(0);
//                            }
//                        }
//                    }*/
//                }


                List<Long> listSelectorId = new ArrayList<>();
                for (int i = 0; i < memList.size(); i++) {
                    listSelectorId.add(memList.get(i).getId());
                }


                adapter.setSelectorID(listSelectorId);
                adapter.notifyDataSetChanged();
                cancelProgressDlg();
            }

            @Override
            public void onFailure(Call<MoreLikeList> call, Throwable t) {
                cancelProgressDlg();
            }
        });
    }

    private void setListener() {
        getData("");
        searchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        characterParser = CharacterParser.getInstance();


        pinyinComparator = new PinyinComparator();

        sidebar.setTextView(dialog);

        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    lvAllCity.setSelection(position);
                }

            }
        });

        if (!flag.equals("1000")) {
            more_follow.setVisibility(View.VISIBLE);
            if (flag.equals("1001")) {
                lvAllCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        if (adapter.getData().get(position).getIsChoose() == 0) {
                            adapter.getData().get(position).setIsChoose(1);
                        } else {
                            adapter.getData().get(position).setIsChoose(0);
                        }
                        setTeamsUserAvatarAdapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                        for (int i = 0; i < adapter.getData().size(); i++) {
                            if (adapter.getData().get(i).getIsChoose() == 1) {
                                count++;
                            }
                        }
                        sure = "创建(" + count + ")";
                        sureNum(sure);
                    }
                });
            } else {
                lvAllCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        if (adapter.getData().get(position).getIsSelector() == 1) {
                            return;//已经是某群的群成员了
                        }
                        if (adapter.getData().get(position).getIsChoose() == 0) {
                            adapter.getData().get(position).setIsChoose(1);
                        } else {
                            adapter.getData().get(position).setIsChoose(0);
                        }
                        setTeamsUserAvatarAdapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                        if (adapter.getData().get(position).getIsChoose() == 1) {
                            count_1002++;
                        }
                        if (adapter.getData().get(position).getIsChoose() == 0) {
                            count_1002--;
                        }
                        /*for (int i = 0; i < adapter.getData().size(); i++) {
                            if (adapter.getData().get(position).getIsChoose() == 1) {
                                count_1002++;
                            }
                            if (adapter.getData().get(position).getIsChoose() == 0) {
                                count_1002--;
                            }
                            if (adapter.getData().get(i).getIsChoose() == 1) {
                                *//*if (adapter.getData().get(i).getId() == adapter.getData().get(position).getId()) {
                                    count++;
                                }*//*
                                count_1002++;
                            } *//*else {
                                count--;
                            }*//*
                        }*/
                        sureJoin = "添加(" + count_1002 + ")";
                        sureNum(sureJoin);
                    }
                });
            }
        }
    }

    private void initView() {
        lvAllCity = getViewById(R.id.lv_all_city);
        dialog = getViewById(R.id.dialog);
        sidebar = getViewById(R.id.sidrbar);
        searchCity = getViewById(R.id.search_city);
        more_follow = getViewById(R.id.more_follow);
    }

    private List<SortModel> filledData(List<MoreLikeList.ResultBean.ListBean> date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setBirthday(date.get(i).getUser().getUser_data().getBirthday());
            sortModel.setName(date.get(i).getUser().getUser_data().getUser_name());
            sortModel.setGender(date.get(i).getUser().getUser_data().getGender());
            if (date.get(i).getUser().getUser_data().getProvince().contains("市")) {//直辖市
                locationCity = date.get(i).getUser().getUser_data().getProvince();
            } else {
                locationCity = date.get(i).getUser().getUser_data().getUser_location();
            }
            if (!TextUtils.isEmpty(locationCity)) {
                if (locationCity.contains("市")) {
                    String substring = locationCity.substring(0, locationCity.length() - 1);
//                    location.setText(substring);
                    sortModel.setLocation(substring);
                } else {
//                    location.setText(locationCity);
                    sortModel.setLocation(locationCity);
                }
            }
//            sortModel.setLocation(date.get(i).getUser().getUser_data().getUser_location());
            sortModel.setAvatarUrl(date.get(i).getUser().getUser_data().getProfile_pic_url());
            sortModel.setId(date.get(i).getUser().getId());
            if (!TextUtils.isEmpty(date.get(i).getUser().getUser_data().getUser_name())) {
                String pinyin = characterParser.getSelling(date.get(i).getUser().getUser_data().getUser_name());
                String sortString = pinyin.substring(0, 1).toUpperCase();
                if (sortString.matches("[A-Z]")) {
                    sortModel.setSortLetters(sortString.toUpperCase());
                } else {
                    sortModel.setSortLetters("#");
                }
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;

    }

    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = sourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : sourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // ���a-z��������
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }


    @Override
    protected int getContentResId() {
        return R.layout.act_contacts;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        this.builder = builder;
        flag = getIntent().getStringExtra("flag");
        builder.setTitle("好友列表");
        builder.setBackGround(Color.WHITE);
        builder.setTitleColor(Color.BLACK);
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        if (flag.equals("1002")) {
            sureNum(sureJoin);
        } else {
            sureNum(sure);
        }
    }

    private HeaderBuilder builder;
    private List<SortModel> tempList = new ArrayList();
    Bitmap[] mBitmaps;

    private void sureNum(String sure) {
        group_id = getIntent().getStringExtra("group_id");
        if (flag.equals("1002")) {//邀请群成员加入群
            builder.setRightTvColor(R.color.color_ff9600);
            builder.setRightTvText(sure, new OnClickListener() {
                @Override
                protected void clickOperate() {
                    onShowProgressDlg();
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        if (adapter.getData().get(i).getIsChoose() == 1) {
                            tempList.add(adapter.getData().get(i));
                        }
                    }
                    if (count_1002 == 0) {
                        cancelProgressDlg();
                        ToastUtils.showShort("您还没有邀请好友");
                        return;
                    }

                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < tempList.size(); i++) {
                        stringBuffer.append(tempList.get(i).getId()).append(",");
                    }
                    if (TextUtils.isEmpty(stringBuffer)) {
                        return;
                    }
                    final String sb = stringBuffer.substring(0, stringBuffer.length() - 1);
                    group = EMClient.getInstance().groupManager().getGroup(group_id);
                    if (group == null) {
                        EMClient.getInstance().groupManager().asyncGetGroupFromServer(group_id, new EMValueCallBack<EMGroup>() {
                            @Override
                            public void onSuccess(EMGroup emGroup) {
                                sendTeamsInvitation(emGroup.getGroupId(), emGroup.getGroupName(), emGroup.getDescription(), sb);
                            }

                            @Override
                            public void onError(int i, String s) {
                                cancelProgressDlg();
                            }
                        });
                    } else {
                        sendTeamsInvitation(group.getGroupId(), group.getGroupName(), group.getDescription(), sb);
                    }
                    /*List<String> uids = new ArrayList<String>();
                    for (int i = 0; i < tempList.size(); i++) {
                        uids.add(String.valueOf(tempList.get(i).getId()));
                    }
                    String[] allmebers = uids.toArray(new String[uids.size()]);
                    EMClient.getInstance().groupManager().asyncAddUsersToGroup(group_id, allmebers, new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            cancelProgressDlg();
                            handler.sendEmptyMessage(101);
                            count_1002 = 0;
                        }

                        @Override
                        public void onError(int i, String s) {

                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });*/
                }
            });
        }
        if (flag.equals("1001")) {//创建群
            builder.setRightTvColor(R.color.color_ff9600);
            builder.setRightTvText(sure, new OnClickListener() {
                @Override
                protected void clickOperate() {
                    onShowProgressDlg();
                    for (int i = 0; i < adapter.getData().size(); i++) {
                        if (adapter.getData().get(i).getIsChoose() == 1) {
                            tempList.add(adapter.getData().get(i));
                        }
                    }
                    switch (tempList.size()) {
                        case 0:
                            cancelProgressDlg();
                            ToastUtils.showShort("您还没有选择要添加的人");
                            return;
                        case 1:
                            cancelProgressDlg();
                            ChatActivity.start(ContactsActivity.this, String.valueOf(tempList.get(0).getId()), ChatActivity.SINGLE);
                            finish();
                            return;
                        case 2:
                            mEntityList = getBitmapEntitys(tempList.size() + 1);
                            mBitmaps = new Bitmap[]{
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(user.getProfile_pic_url()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(0).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(1).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width)};

                            break;
                        case 3:
                            mEntityList = getBitmapEntitys(tempList.size() + 1);
                            mBitmaps = new Bitmap[]{
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(user.getProfile_pic_url()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(0).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(1).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(2).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width)};
                            break;
                        case 4:
                            mEntityList = getBitmapEntitys(tempList.size() + 1);
                            mBitmaps = new Bitmap[]{
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(user.getProfile_pic_url()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(0).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(1).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(2).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(3).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width)};

                            break;
                        case 5:
                            mEntityList = getBitmapEntitys(tempList.size() + 1);
                            mBitmaps = new Bitmap[]{
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(user.getProfile_pic_url()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(0).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(1).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(2).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(3).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(4).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width)};
                            break;
                        case 6:
                            mEntityList = getBitmapEntitys(tempList.size() + 1);
                            mBitmaps = new Bitmap[]{
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(user.getProfile_pic_url()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(0).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(1).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(2).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(3).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(4).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(5).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width)};
                            break;
                        case 7:
                            mEntityList = getBitmapEntitys(tempList.size() + 1);
                            mBitmaps = new Bitmap[]{
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(user.getProfile_pic_url()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(0).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(1).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(2).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(3).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(4).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(5).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(6).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width)};
                            break;
                        case 8:
                            mEntityList = getBitmapEntitys(tempList.size() + 1);
                            mBitmaps = new Bitmap[]{
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(user.getProfile_pic_url()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(0).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(1).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(2).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(3).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(4).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(5).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(6).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(7).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width)};

                            break;
                        default:
                            mEntityList = getBitmapEntitys(tempList.size() + 1);
                            mBitmaps = new Bitmap[]{
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(user.getProfile_pic_url()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(0).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(1).getAvatarUrl()), (int) mEntityList
                                            .get(0).width, (int) mEntityList.get(0).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(2).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(3).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(4).getAvatarUrl()), (int) mEntityList
                                            .get(1).width, (int) mEntityList.get(1).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(5).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(6).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width),
                                    ThumbnailUtils.extractThumbnail(ImageLoader.getInstance().loadImageSync(tempList.get(7).getAvatarUrl()), (int) mEntityList
                                            .get(2).width, (int) mEntityList.get(2).width)};
                            break;
                    }
                    Drawable drawable = getResources().getDrawable(R.mipmap.ease_default_avatar);
                    Bitmap tempBitmap = BitmapUtil.drawableToBitmap(drawable);
                    Bitmap bitmap = ThumbnailUtils.extractThumbnail(tempBitmap, (int) mEntityList
                            .get(0).width, (int) mEntityList.get(0).width);
                    Bitmap combineBitmap = BitmapUtils.getCombineBitmaps(mEntityList, bitmap, mBitmaps);
                    File cacheDir = StorageUtils.getOwnCacheDirectory(ContactsActivity.this,
                            "yuyoubang/Cache");
                    String fileName = cacheDir.getPath() + "/"
                            + "pic" + System.currentTimeMillis();
                    FileOutputStream mFileOutputStream = null;
                    try {
                        mFileOutputStream = new FileOutputStream(fileName);
                        combineBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                                mFileOutputStream);// 把数据写入文件
                        File file = new File(fileName);
                        upLoad(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            mFileOutputStream.flush();
                            mFileOutputStream.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    try {
                        BitmapUtils.saveMyBitmap(ContactsActivity.this, combineBitmap, "yuyoubang_avatar");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            count = 0;
        }
    }

    private void sendTeamsInvitation(String groupId, String groupName, String description, String sb) {
        FormBody formBody = new FormBody.Builder()
                .add("group_id", groupId)
                .add("group_name", groupName)
                .add("group_pic", description)
                .add("ids", sb)
                .add("type", "1")
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<ApplyGroup> applyGroupCall = homeApi.apply_to_group(formBody);
        applyGroupCall.enqueue(new Callback<ApplyGroup>() {
            @Override
            public void onResponse(Call<ApplyGroup> call, Response<ApplyGroup> response) {
                cancelProgressDlg();
                if (response.body().getError_code() == 0) {
                    ToastUtils.showShort("邀请成功, 等待对方同意");
                    finish();
                    TeamsDetailsActivity.instance.finish();
                    count_1002 = 0;
                }
            }

            @Override
            public void onFailure(Call<ApplyGroup> call, Throwable t) {
                cancelProgressDlg();
            }
        });
    }

    private List<MyBitmapEntity> getBitmapEntitys(int count) {
        List<MyBitmapEntity> mList = new LinkedList<>();
        String value = "dd";/*PropertiesUtil.readData(this, String.valueOf(count),
                R.raw.data);*/
        String[] arr1 = value.split(";");
        int length = arr1.length;
        for (int i = 0; i < length; i++) {
            String content = arr1[i];
            String[] arr2 = content.split(",");
            MyBitmapEntity entity = null;
            for (int j = 0; j < arr2.length; j++) {
                entity = new MyBitmapEntity();
                entity.x = Float.valueOf(arr2[0]);
                entity.y = Float.valueOf(arr2[1]);
                entity.width = Float.valueOf(arr2[2]);
                entity.height = Float.valueOf(arr2[3]);
            }
            mList.add(entity);
        }
        return mList;
    }

    public static class MyBitmapEntity {
        public float x;
        public float y;
        float width;
        float height;
        static int devide = 1;
        int index = -1;

        @Override
        public String toString() {
            return "MyBitmap [x=" + x + ", y=" + y + ", width=" + width
                    + ", height=" + height + ", devide=" + devide + ", index="
                    + index + "]";
        }
    }

    private void upLoad(File file) {
        Log.e("LoginInfoActivity", "file.name=" + file.getName());
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("pic", file.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), file));
        UploadApi uploadApi = RetrofitManager.getTestRetrofit().create(UploadApi.class);
        Call<UploadBean> upload = uploadApi.upload(filePart);
        upload.enqueue(new BaseCallback<UploadBean>() {
            @Override
            public void onResponse(Call<UploadBean> call, Response<UploadBean> response) {
                Log.e("LoginInfoActivity", "response=" + response.body().getResult().getBmiddle_pic());
                if (response.body().getError_code() == 0) {
                    String profile_pic_url = response.body().getResult().getBmiddle_pic();
                    List<SortModel> temp = new ArrayList<>();
                    SortModel sortModel = new SortModel();
                    sortModel.setName(user.getUser_name());
                    temp.add(sortModel);
                    temp.addAll(tempList);
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < 3; i++) {
                        sb.append(temp.get(i).getName()).append("、");
                    }
                    if (TextUtils.isEmpty(sb)) {
                        ToastUtils.showShort("创建失败");
                        return;
                    }
                    final String groupName = sb.substring(0, sb.length() - 1);
                    List<String> uids = new ArrayList<String>();
                    for (int i = 0; i < tempList.size(); i++) {
                        uids.add(String.valueOf(tempList.get(i).getId()));
                    }
                    String[] allmebers = uids.toArray(new String[uids.size()]);
                    ChatUtil.CreateGroup(groupName, profile_pic_url, allmebers, null, new EMValueCallBack<EMGroup>() {
                        @Override
                        public void onSuccess(EMGroup emGroup) {
                            cancelProgressDlg();
                            handler.sendEmptyMessage(100);
                            //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                            EMMessage message = EMMessage.createTxtSendMessage(user.getUser_name() + "邀请您加入了群聊", emGroup.getGroupId());
//                          //如果是群聊，设置chattype，默认是单聊
                            message.setChatType(EMMessage.ChatType.GroupChat);
//                          //发送消息
                            EMClient.getInstance().chatManager().sendMessage(message);
                            // TODO: 16/11/30
                            ChatActivity.start(ContactsActivity.this, emGroup.getGroupId(), ChatActivity.Group);
                            finish();
                        }

                        @Override
                        public void onError(int i, String s) {
                            ToastUtils.showLong(s);
                        }
                    });
                } else {
                }
                cancelProgressDlg();

            }

            @Override
            public void onFailure(Call<UploadBean> call, Throwable t) {
                super.onFailure(call, t);
                cancelProgressDlg();
                ToastUtils.showLong("网络连接失败");
                Log.e("RigisterActivity", "测试失败");
            }
        });
    }

}
