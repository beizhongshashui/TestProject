package com.yuyoubang.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuyoubang.R;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.otto.AppBus;
import com.yuyoubang.otto.PayEvent;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, GlobalParams.WEIXIN_APP_KEY);

        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (resp.errCode) {
			case 0:
				Toast.makeText(WXPayEntryActivity.this, "支付成功", Toast.LENGTH_LONG).show();
				AppBus.getInstance().post(new PayEvent());
				finish();
				break;
			case -1:
				Toast.makeText(WXPayEntryActivity.this, "支付失败", Toast.LENGTH_LONG).show();
				finish();
				break;
			case -2:
				Toast.makeText(WXPayEntryActivity.this, "取消支付", Toast.LENGTH_LONG).show();
//				Intent intent1 = new Intent();
//				intent1.putExtra("bill_type", 2);
//				intent1.setAction(Constant.CANCEL);
//				sendBroadcast(intent1);
				finish();
				break;
			default:
				break;
			}
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle(R.string.app_tip);
//			builder.setMessage(getString(R.string.pay_result_callback_msg, resp.errStr +";code=" + String.valueOf(resp.errCode)));
//			builder.show();
		
			
		}
	}
	
}