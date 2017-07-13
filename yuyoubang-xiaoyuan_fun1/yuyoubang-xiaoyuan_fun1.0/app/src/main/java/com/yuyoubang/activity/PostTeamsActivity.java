package com.yuyoubang.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.activity.sort.CharacterParser;
import com.yuyoubang.activity.sort.PinyinComparator;
import com.yuyoubang.activity.sort.SortModel;
import com.yuyoubang.adapter.PostTeamsAdapter;
import com.yuyoubang.bean.PostTeams;
import com.yuyoubang.bean.TeamsMember;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;

/**
 * Created by hongchen on 17/1/6.
 */

public class PostTeamsActivity extends BaseNetActivity<PostTeams> {

    private List<PostTeams.ResultBean.ListBean> postList;
//    private List<PostTeams.ResultBean.ListBean> teamsList = new ArrayList<>();
    private ListView lessonListLine;
    private List<EMGroup> groupList = new ArrayList<>();
    private List<EMGroup> list = new ArrayList<>();
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private EditText searchCity;
    private PostTeamsAdapter adapter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                /*for (int i = 0; i < postList.size(); i++) {
                    teamsList.add(postList.get(i));
                }*/
                sourceDateList = filledData(postList);
                Collections.sort(sourceDateList, pinyinComparator);
                adapter = new PostTeamsAdapter(PostTeamsActivity.this, sourceDateList);
                lessonListLine.setAdapter(adapter);
                cancelProgressDlg();
                goneLoading();
            }
            if (msg.what == 101) {
                if (postList.size() < list.size()) {
                    for (int i = 0; i < postList.size(); i++) {
                        for (int j = 0; j < list.size(); j++) {
                            if (postList.get(i).getData().getUser_create_group_id().equals(list.get(j).getGroupId())) {
//                                                    teamsList.add(postList.get(i).getData().getUser_create_group_id());
                                postList.remove(i);
                            }
                        }
                    }
                } else if (postList.size() > list.size()) {
                    for (int i = 0; i < list.size(); i++) {
                        for (int j = 0; j < postList.size(); j++) {
                            if (list.get(i).getGroupId().equals(postList.get(j).getData().getUser_create_group_id())) {
                                postList.remove(i);
//                                                    teamsList.add(postList.get(i).getData().getUser_create_group_id());
                            } else {

                            }
                        }
                    }
//                                        for (int i = 0; i < emGroups.size(); i++) {
//                                            for (int j = 0; j < postList.size(); j++) {
//                                                if (emGroups.get(i).getGroupId().equals(postList.get(j).getData().getUser_create_group_id())) {
//                                                    postList.remove(i);
////                                                    teamsList.add(postList.get(j).getData().getUser_create_group_id());
//                                                }
//                                            }
//                                        }
                } else {
                    for (int i = 0; i < postList.size(); i++) {
                        for (int j = 0; j < list.size(); j++) {
                            if (postList.get(i).getData().getUser_create_group_id().equals(list.get(j).getGroupId())) {
                                postList.remove(i);
//                                                    teamsList.add(postList.get(i).getData().getUser_create_group_id());
                            }
                        }
                    }
                }

                /*for (int i = 0; i < postList.size(); i++) {
                    teamsList.add(postList.get(i).getData().getUser_create_group_id());
                }*/

                /*for (final String list : teamsList) {
                    try {
                        new Thread(new Runnable() {
                            public void run() {
                                //get detail from server
                                try {
                                    group = EMClient.getInstance().groupManager().getGroupFromServer(list);
                                    groupList.add(group);
                                } catch (final HyphenateException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }*/
                sourceDateList = filledData(postList);
                Collections.sort(sourceDateList, pinyinComparator);
                adapter = new PostTeamsAdapter(PostTeamsActivity.this, sourceDateList);
                lessonListLine.setAdapter(adapter);
                cancelProgressDlg();
            }
        }
    };
    private EMGroup group;
    private List<SortModel> sourceDateList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goneLoading();
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
    }

    @Override
    protected void initViews() {
        lessonListLine = getViewById(R.id.more_follow);
        searchCity = getViewById(R.id.search_city);
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
    }

    @Override
    protected void loadData() {
//        onShowProgressDlg();
        FormBody formBody = new FormBody.Builder()
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<PostTeams> postTeamsCall = findApi.post_teams(formBody);
        postTeamsCall.enqueue(this);
    }

    @Override
    protected void processData(PostTeams postTeams) {
        if (postTeams.getError_code() == 0) {
            if (postTeams.getResult() != null) {
                if (postTeams.getResult().getList() != null) {
                    if (postTeams.getResult().getList().size() > 0) {
                        postList = postTeams.getResult().getList();
                        EMClient.getInstance().groupManager().asyncGetJoinedGroupsFromServer(new EMValueCallBack<List<EMGroup>>() {
                            @Override
                            public void onSuccess(List<EMGroup> emGroups) {
                                if (emGroups.size() == 0 || emGroups == null) {
                                    handler.sendEmptyMessage(100);
                                } else {
                                    list.addAll(emGroups);
                                    handler.sendEmptyMessage(101);
                                }
                            }

                            @Override
                            public void onError(int i, String s) {
                                handler.sendEmptyMessage(102);
                                cancelProgressDlg();
                            }
                        });
                    } else {
                        goneLoading();
                        ToastUtils.showShort("暂无推荐小组");
                    }
                } else {
                    goneLoading();
                    ToastUtils.showShort("暂无推荐小组");
                }
            } else {
                goneLoading();
                ToastUtils.showShort("暂无推荐小组");
            }
        } else {
            goneLoading();
            ToastUtils.showShort("暂无推荐小组");
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_post_team;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setTitle("推荐小组");
        builder.setRightTvText("创建小组", new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openContactsActivity(PostTeamsActivity.this, "1001", new ArrayList<TeamsMember.ResultBean.ListBean>(), "1111");
            }
        });
    }

    private List<SortModel> filledData(List<PostTeams.ResultBean.ListBean> date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setTeamUrl(date.get(i).getData().getTrip_cover_pic());
            sortModel.setTeamName(date.get(i).getData().getTrip_name());
            sortModel.setId(date.get(i).getUid());
            sortModel.setTeamMems(0 + "");
            sortModel.setGroup_id(date.get(i).getData().getUser_create_group_id());
            if (!TextUtils.isEmpty(date.get(i).getData().getTrip_name())) {
                String pinyin = characterParser.getSelling(date.get(i).getData().getTrip_name());
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
                String name = sortModel.getTeamName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // ���a-z��������
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

}
