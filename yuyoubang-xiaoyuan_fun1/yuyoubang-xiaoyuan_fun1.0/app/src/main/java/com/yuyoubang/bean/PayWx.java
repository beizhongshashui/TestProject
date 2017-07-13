package com.yuyoubang.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hongchen on 16/12/27.
 */

public class PayWx implements Serializable{

    /**
     * order_id : 12345673717
     * prepay_id : wx201612201920319c5be1465a0318363863
     * sign : 9E47D989887F18B6CDEE69807A1E4DD2
     * appid : wx93f2363ed1fc5411
     * partnerid : 1375291902
     * prepayid : wx201612201920319c5be1465a0318363863
     * package : Sign=WXPay
     * noncestr : TUA4PMNZPXT6U9VYDB26YX53D
     * timestamp : 1482232831
     */

    private ResultBean result;
    /**
     * result : {"order_id":"12345673717","prepay_id":"wx201612201920319c5be1465a0318363863","sign":"9E47D989887F18B6CDEE69807A1E4DD2","appid":"wx93f2363ed1fc5411","partnerid":"1375291902","prepayid":"wx201612201920319c5be1465a0318363863","package":"Sign=WXPay","noncestr":"TUA4PMNZPXT6U9VYDB26YX53D","timestamp":"1482232831"}
     * error_code : 0
     */

    private int error_code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Serializable{
        private String order_id;
        private String prepay_id;
        private String sign;
        private String appid;
        private String partnerid;
        private String prepayid;
        private String package_value;
        private String noncestr;
        private String timestamp;

        public String getPackage_value() {
            return package_value;
        }

        public void setPackage_value(String package_value) {
            this.package_value = package_value;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
