package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by xiaoyuan on 16/11/30.
 */
public class BizCommentValue implements Serializable {

    /**
     * biz_user_comment_avg_value : 2.4545454545454546
     * biz_user_comment_total_count : 11
     * biz_user_5_comment_count : 0
     * biz_user_4_comment_count : 0
     * biz_user_3_comment_count : 9
     * biz_user_2_comment_count : 0
     * biz_user_1_comment_count : 0
     */

    private ResultBean result;
    /**
     * result : {"biz_user_comment_avg_value":"2.4545454545454546","biz_user_comment_total_count":"11","biz_user_5_comment_count":"0","biz_user_4_comment_count":"0","biz_user_3_comment_count":"9","biz_user_2_comment_count":"0","biz_user_1_comment_count":"0"}
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
        private String biz_user_comment_avg_value;
        private String biz_user_comment_total_count;
        private String biz_user_5_comment_count;
        private String biz_user_4_comment_count;
        private String biz_user_3_comment_count;
        private String biz_user_2_comment_count;
        private String biz_user_1_comment_count;

        public String getBiz_user_comment_avg_value() {
            return biz_user_comment_avg_value;
        }

        public void setBiz_user_comment_avg_value(String biz_user_comment_avg_value) {
            this.biz_user_comment_avg_value = biz_user_comment_avg_value;
        }

        public String getBiz_user_comment_total_count() {
            return biz_user_comment_total_count;
        }

        public void setBiz_user_comment_total_count(String biz_user_comment_total_count) {
            this.biz_user_comment_total_count = biz_user_comment_total_count;
        }

        public String getBiz_user_5_comment_count() {
            return biz_user_5_comment_count;
        }

        public void setBiz_user_5_comment_count(String biz_user_5_comment_count) {
            this.biz_user_5_comment_count = biz_user_5_comment_count;
        }

        public String getBiz_user_4_comment_count() {
            return biz_user_4_comment_count;
        }

        public void setBiz_user_4_comment_count(String biz_user_4_comment_count) {
            this.biz_user_4_comment_count = biz_user_4_comment_count;
        }

        public String getBiz_user_3_comment_count() {
            return biz_user_3_comment_count;
        }

        public void setBiz_user_3_comment_count(String biz_user_3_comment_count) {
            this.biz_user_3_comment_count = biz_user_3_comment_count;
        }

        public String getBiz_user_2_comment_count() {
            return biz_user_2_comment_count;
        }

        public void setBiz_user_2_comment_count(String biz_user_2_comment_count) {
            this.biz_user_2_comment_count = biz_user_2_comment_count;
        }

        public String getBiz_user_1_comment_count() {
            return biz_user_1_comment_count;
        }

        public void setBiz_user_1_comment_count(String biz_user_1_comment_count) {
            this.biz_user_1_comment_count = biz_user_1_comment_count;
        }
    }
}
