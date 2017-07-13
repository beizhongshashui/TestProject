package com.yuyoubang.wxapi;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;
import com.yuyoubang.activity.base.ToolBarActivity;
import com.yuyoubang.app.GlobalParams;


/**
 * 微信分享回调
 * Created by ch on 16/8/4.
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler /*WXCallbackActivity*/ {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        IWXAPI api = WXAPIFactory.createWXAPI(this, GlobalParams.WEIXIN_APP_KEY, false);
        api.handleIntent(getIntent(), this);
    }

    /*@Override
    protected int getContentResId() {
        return R.layout.activity_progress;
    }

    @Override
    protected void initTitleBar(ToolBarActivity.HeaderBuilder builder) {

    }*/

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if (resp instanceof SendAuth.Resp) {
                    SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                    String code = sendResp.code;
                    /*final UserApi userApi = RetrofitManager.wxLogin().create(UserApi.class);
                    Call<WxLoginSecret> dataCodeMsgCall = userApi.getWxLoginSecret(GlobalParams.WEIXIN_APP_KEY, GlobalParams.WEIXIN_APP_SECRET, code, "authorization_code");
                    dataCodeMsgCall.enqueue(new BaseCallback<WxLoginSecret>() {
                        @Override
                        public void onResponse(Call<WxLoginSecret> call, Response<WxLoginSecret> response) {
                            if (response != null) {
                                WxLoginSecret wxLoginSecret = response.body();
                                //UserApi userApi = RetrofitManager.wxLogin().create(UserApi.class);
                                Call<WechatUser> wxLogin = userApi.getWxLogin(wxLoginSecret.getAccess_token(), wxLoginSecret.getOpenid());
                                wxLogin.enqueue(new BaseCallback<WechatUser>() {
                                    @Override
                                    public void onResponse(Call<WechatUser> call, Response<WechatUser> response) {
                                        if (response != null && response.body() != null) {
                                            WechatUser bodyData = response.body();
                                            new LoginModel().loginByWechat(bodyData, new OnLoginListener() {
                                                @Override
                                                public void loginSuccess() {
                                                    loginCompleted();
                                                    finish();
                                                }

                                                @Override
                                                public void loginFail() {
                                                    ToastUtils.showShort("登录失败");
                                                    finish();
                                                }
                                            });
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<WechatUser> call, Throwable t) {
                                        super.onFailure(call, t);
                                        ToastUtils.showShort("获取微信用户信息失败");
                                        finish();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<WxLoginSecret> call, Throwable t) {
                            super.onFailure(call, t);
                            ToastUtils.showShort("微信授权失败");
                            finish();
                        }
                    });*/
                } else {
                    Toast.makeText(this, "分享成功", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //分享取消
                if (resp instanceof SendAuth.Resp) {
                    Toast.makeText(this, "登录失败，请用其他方式登录。", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "分享取消了", Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                if (resp instanceof SendAuth.Resp) {
                    Toast.makeText(this, "登录失败，请用其他方式登录。", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "分享失败啦", Toast.LENGTH_LONG).show();
                }
                finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
