package com.yuyoubang.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.yuyoubang.app.GlobalParams;
import com.yuyoubang.bean.PayWx;
import com.yuyoubang.view.LoadingDialog;
import com.yuyoubang.wxapi.Util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hongchen on 16/12/13.
 */

public class WeixinPayUtils {

    private PayReq req;
    private IWXAPI msgApi;
    private String orderID;
    private LoadingDialog dialog;
    private Context context;
    private PayWx payWx;

    public void initPay(Context context, String orderID, IWXAPI msgApi, PayWx payWx) {
        req = new PayReq();
        this.msgApi = msgApi;
        this.orderID = orderID;
        this.context = context;
        this.payWx = payWx;
        msgApi.registerApp(GlobalParams.WEIXIN_APP_KEY);

        GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
        getPrepayId.execute();
    }

    /**
     * 获取prepay_id
     */

    public class GetPrepayIdTask extends
            AsyncTask<Void, Void, Map<String, String>> {


        @Override
        protected void onPreExecute() {
            onShowProgressDlg();
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (!msgApi.isWXAppInstalled()) {
                ToastUtils.showShort("未安装微信");
            }
            cancelProgressDlg();
            /*req.appId = "wx93f2363ed1fc5411";
            req.partnerId = "1375291902";
            req.prepayId = "wx201612201631424e9be0aa120064763917";
            req.packageValue = "Sign=WXPay";
            req.nonceStr = "74ICK7WROBF9AJ677SUTRD8VB";
            req.timeStamp = "1482222702";
            req.sign = "81DD457CE912D530A320F76B8627502A";
            msgApi.sendReq(req);*/
            req.appId = payWx.getResult().getAppid();
            req.partnerId = payWx.getResult().getPartnerid();
            req.prepayId = payWx.getResult().getPrepayid();
            req.packageValue = payWx.getResult().getPackage_value();
            req.nonceStr = payWx.getResult().getNoncestr();
            req.timeStamp = payWx.getResult().getTimestamp();
            req.sign = payWx.getResult().getSign();
            msgApi.sendReq(req);
            /*req.appId = GlobalParams.WEIXIN_APP_KEY;
            req.partnerId = GlobalParams.MCH_ID;
            req.prepayId = result.get("prepay_id");
            req.packageValue = "Sign=WXPay";
            req.nonceStr = Util.genNonceStr();
            req.timeStamp = String.valueOf(Util.genTimeStamp());
            List<NameValuePair> signParams = new LinkedList<>();
            signParams.add(new BasicNameValuePair("appid", req.appId));
            signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
            signParams.add(new BasicNameValuePair("package", req.packageValue));
            signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
            signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
            signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
            req.sign = Util.genAppSign(signParams);
            msgApi.sendReq(req);*/

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String, String> doInBackground(Void... params) {
            String url = String
                    .format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = CreateProductArgs();
            byte[] buf = Util.httpPost(url, entity);
            String content = new String(buf);
            Log.e("orion", content);
            Map<String, String> xml = Util.decodeXml(content);
            return xml;
        }
    }

    /**
     * 生成订单信息
     *
     * @return
     */
    public String CreateProductArgs() {
        StringBuffer xml = new StringBuffer();

        try {
            String nonceStr = Util.genNonceStr();

            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", GlobalParams.WEIXIN_APP_KEY));
            packageParams.add(new BasicNameValuePair("body", "遇游邦"));
            packageParams.add(new BasicNameValuePair("detail",
                    "测试"));
            packageParams
                    .add(new BasicNameValuePair("mch_id", GlobalParams.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url",
                    "http://www.api.geekniu.com/pay/callback.json"));//回调地址
            packageParams.add(new BasicNameValuePair("out_trade_no",
                    orderID));
            packageParams.add(new BasicNameValuePair("spbill_create_ip",
                    "127.0.0.1"));
            packageParams.add(new BasicNameValuePair("total_fee", 1 + ""));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));

            String sign = Util.genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));

            String xmlstring = Util.toXml(packageParams);

            return new String(xmlstring.toString().getBytes(), "ISO8859-1");

        } catch (Exception e) {
            return null;
        }
    }

    public void onShowProgressDlg() {
        if (dialog == null) {
            dialog = new LoadingDialog(context, "");
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

    }

    public void onShowProgressDlg(String text) {
        if (dialog == null) {
            dialog = new LoadingDialog(context, text);
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

    }

    public void cancelProgressDlg() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }

    }
}
