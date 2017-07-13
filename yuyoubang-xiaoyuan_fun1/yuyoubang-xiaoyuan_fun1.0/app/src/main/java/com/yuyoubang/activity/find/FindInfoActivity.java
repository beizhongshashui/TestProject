package com.yuyoubang.activity.find;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.UMShareAPI;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseNetActivity;
import com.yuyoubang.adapter.FindInfoAdapter;
import com.yuyoubang.bean.FindCloseStar;
import com.yuyoubang.bean.FindCommentList;
import com.yuyoubang.bean.FindFollowBean;
import com.yuyoubang.bean.FindFollowInfoBean;
import com.yuyoubang.bean.FindOpenStar;
import com.yuyoubang.bean.SendComment;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.network.BaseCallback;
import com.yuyoubang.network.RetrofitManager;
import com.yuyoubang.network.api.FindApi;
import com.yuyoubang.utils.KeyBoardUtil;
import com.yuyoubang.utils.LaunchOperate;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ToastUtils;
import com.yuyoubang.view.SharePopwin;
import com.yuyoubang.widget.adapter.ViewHolder;
import com.yuyoubang.widget.adapter.interfaces.OnLoadMoreListener;
import com.yuyoubang.widget.adapter.interfaces.OnMultiItemClickListeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 16/11/17.
 */
public class FindInfoActivity extends BaseNetActivity<FindCommentList> {
    @Bind(R.id.find_info_rv)
    RecyclerView findInfoRv;
    @Bind(R.id.editTextMessage)
    EditText editTextMessage;
    @Bind(R.id.send_message)
    TextView send_message;
    @Bind(R.id.iv_star)
    ImageView iv_star;
    private FindInfoAdapter mAdapter;
    private String cursor;

    private boolean isStar = false;

    private FindFollowBean.ResultBean.ListBean listBean;
    private List<FindCommentList.ResultBean.ListBean> comment_list = new ArrayList<>();
    private List<FindCommentList.ResultBean.ListBean> data = new ArrayList<>();
    private int is_liked;
    private String image;
    private ImageView left_iv;
    private ImageView right_iv;
    private ImageView right;


    public static void start(Context context, FindFollowBean.ResultBean.ListBean listBean) {
        Intent intent = new Intent(context, FindInfoActivity.class);
        intent.putExtra("find", listBean);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        left_iv = getViewById(R.id.left_iv);
        right_iv = getViewById(R.id.right_iv);
        right = getViewById(R.id.right);
        listBean = (FindFollowBean.ResultBean.ListBean) getIntent().getExtras().getSerializable("find");
        ButterKnife.bind(this);
        //初始化adapter
        mAdapter = new FindInfoAdapter(this, null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                getData(comment_list.get(comment_list.size() - 1).getId() + "", listBean.getId() + "");
            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<FindCommentList.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, FindCommentList.ResultBean.ListBean data, int position, int viewType) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findInfoRv.setLayoutManager(layoutManager);
        findInfoRv.setAdapter(mAdapter);

        send_message.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {//发送评价
                String trim = editTextMessage.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtils.showLong("评价内容不能为空");
                    return;
                }
                sendCommentContent(trim);
            }
        });

        left_iv.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });

        right_iv.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                showPopFormBottom();
            }
        });

        right.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                LaunchOperate.openFeedbackActivity(FindInfoActivity.this,1);
            }
        });
    }

    private void closeStar(String status_id, String uid) {
        FormBody formBody = new FormBody.Builder()
                .add("status_id", status_id)
                .add("uid", uid)
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<FindCloseStar> findCloseStarCall = findApi.status_like_remove(formBody);
        findCloseStarCall.enqueue(new Callback<FindCloseStar>() {
            @Override
            public void onResponse(Call<FindCloseStar> call, Response<FindCloseStar> response) {
                ToastUtils.showShort("取消赞成功");
            }

            @Override
            public void onFailure(Call<FindCloseStar> call, Throwable t) {
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }

    private void openStar(String status_id, String uid) {
        FormBody formBody = new FormBody.Builder()
                .add("status_id", status_id)
                .add("uid", uid)
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<FindOpenStar> findOpenStarCall = findApi.status_like(formBody);
        findOpenStarCall.enqueue(new Callback<FindOpenStar>() {
            @Override
            public void onResponse(Call<FindOpenStar> call, Response<FindOpenStar> response) {
                if(response.body().getError_code() == 0){
                    ToastUtils.showShort("点赞成功");
                }

            }

            @Override
            public void onFailure(Call<FindOpenStar> call, Throwable t) {
                ToastUtils.showLong(R.string.net_error);
            }
        });
    }

    private void sendCommentContent(String trim) {
        FormBody formBody = new FormBody.Builder()
                .add("comment", trim)
                .add("status_id", String.valueOf(listBean.getId()))
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<SendComment> sendCommentCall = findApi.status_comment(formBody);
        sendCommentCall.enqueue(new Callback<SendComment>() {
            @Override
            public void onResponse(Call<SendComment> call, Response<SendComment> response) {
                ToastUtils.showShort("发送成功");
                loadData();
                KeyBoardUtil.closeSoftEdit(FindInfoActivity.this, editTextMessage);
                editTextMessage.setText("");
            }

            @Override
            public void onFailure(Call<SendComment> call, Throwable t) {
                ToastUtils.showShort("发送失败");
            }
        });
    }

    @Override
    protected void loadData() {
        getData("", listBean.getId() + "");
    }


    @Override
    protected void processData(FindCommentList findCommentList) {
        if (findCommentList != null) {
            if (findCommentList.getResult() != null) {
                comment_list = findCommentList.getResult().getList();
                if (TextUtils.isEmpty(cursor)) {
                    getDataHead(listBean.getId() + "");
                    data.clear();
                    data.addAll(comment_list);
                    mAdapter.setNewData(data);
                    mAdapter.setLoadingView(R.layout.load_more_layout);
                    if (comment_list == null || comment_list.size() == 0 || comment_list.size() < 5) {
                        mAdapter.setLoadEndView(R.layout.load_end_layout);
                        return;
                    }
                } else {
                    if (comment_list == null || comment_list.size() == 0) {
                        mAdapter.setLoadEndView(R.layout.load_end_layout);
                        return;
                    }
                    data.addAll(comment_list);
                }
            }
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.act_find_info;
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        builder.setBackGround(Color.WHITE);
        builder.setTitle("动态详情");
        builder.setLeftOperate(R.mipmap.back_black, new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
        builder.setTitleColor(Color.BLACK);
        builder.setRightImgOperate(R.mipmap.act_info_share, new OnClickListener() {
            @Override
            protected void clickOperate() {
                showPopFormBottom();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHeaderBuilder.goneToolbar();
        listBean = (FindFollowBean.ResultBean.ListBean) getIntent().getExtras().getSerializable("find");
    }


    private void getData(final String cursor, final String status_id) {
        getCommentList(cursor, status_id);
    }

    private void getCommentList(final String cursor, String status_id) {
        this.cursor = cursor;
        FormBody formBody = new FormBody.Builder()
                .add("cursor", cursor)
                .add("page", "")
                .add("status_id", status_id)
                .build();
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        Call<FindCommentList> findCommentListCall = findApi.status_comment_list(formBody);
        findCommentListCall.enqueue(this);
    }

    private void getDataHead(final String status_id) {
        FindApi findApi = RetrofitManager.getTestRetrofit().create(FindApi.class);
        FormBody body = new FormBody.Builder()
                .add("status_id", status_id)
                .add("uid", PreferenceUtils.getsessionId(this))
                .build();
        Call<FindFollowInfoBean> find = findApi.status_show(body);
        find.enqueue(new BaseCallback<FindFollowInfoBean>() {
            @Override
            public void onResponse(Call<FindFollowInfoBean> call, Response<FindFollowInfoBean> response) {
                FindFollowInfoBean findFollowInfoBean = response.body();
                if (findFollowInfoBean != null) {
                    if (findFollowInfoBean.getResult() != null) {
                        is_liked = findFollowInfoBean.getResult().getStatus().get(0).getData().getIs_like();
                        mAdapter.setHeadData(findFollowInfoBean);
                        if (is_liked == 0) {
                            iv_star.setImageResource(R.mipmap.act_info_like);
                        } else {
                            iv_star.setImageResource(R.mipmap.act_info_like_focus);
                        }
                        iv_star.setOnClickListener(new OnClickListener() {
                            @Override
                            protected void clickOperate() {//点赞
                                if (is_liked == 1) {
                                    iv_star.setImageResource(R.mipmap.act_info_like);
                                    closeStar(listBean.getId() + "", PreferenceUtils.getsessionId(FindInfoActivity.this) + "");
                                    is_liked = 0;
                                } else {
                                    iv_star.setImageResource(R.mipmap.act_info_like_focus);
                                    openStar(listBean.getId() + "", PreferenceUtils.getsessionId(FindInfoActivity.this) + "");
                                    is_liked = 1;
                                }
                            }
                        });
                    }
                }
                goneLoading();
            }

            @Override
            public void onFailure(Call<FindFollowInfoBean> call, Throwable t) {
                super.onFailure(call, t);
                ToastUtils.showLong(R.string.net_error);
                goneLoading();
            }
        });


    }

    //弹出分享页
    public void showPopFormBottom() {
        if (listBean.getData().getPics() != null) {
            if (listBean.getData().getPics().size() > 0) {
                image = listBean.getData().getPics().get(0);
            } else {
                image = "";
            }
        } else {
            image = "";
        }
        SharePopwin sharePopwin = new SharePopwin(this, listBean.getId() + "", image, listBean.getData().getContent(), 2);
        //showAtLocation(View parent, int gravity, int x, int y)
        sharePopwin.showAtLocation(findViewById(R.id.frm_content), Gravity.CENTER, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
