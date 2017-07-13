package com.yuyoubang.bean.mine;

import java.io.Serializable;

/**
 * Created by hongchen on 16/11/30.
 */

public class Feedback implements Serializable{

    /**
     * created_at : 1480507717923
     * updated_at : 1480507717923
     * id : 1014453106704400
     * data : {"feedback_type":"feedback","user_name":"","phone_qq_number":"123456789","feedback":"测试"}
     * uid : 1002274307506177
     */

    private ResultBean result;
    /**
     * result : {"created_at":1480507717923,"updated_at":1480507717923,"id":1014453106704400,"data":{"feedback_type":"feedback","user_name":"","phone_qq_number":"123456789","feedback":"测试"},"uid":1002274307506177}
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
        private long created_at;
        private long updated_at;
        private long id;
        /**
         * feedback_type : feedback
         * user_name :
         * phone_qq_number : 123456789
         * feedback : 测试
         */

        private DataBean data;
        private long uid;

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public long getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(long updated_at) {
            this.updated_at = updated_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public static class DataBean implements Serializable{
            private String feedback_type;
            private String user_name;
            private String phone_qq_number;
            private String feedback;

            public String getFeedback_type() {
                return feedback_type;
            }

            public void setFeedback_type(String feedback_type) {
                this.feedback_type = feedback_type;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getPhone_qq_number() {
                return phone_qq_number;
            }

            public void setPhone_qq_number(String phone_qq_number) {
                this.phone_qq_number = phone_qq_number;
            }

            public String getFeedback() {
                return feedback;
            }

            public void setFeedback(String feedback) {
                this.feedback = feedback;
            }
        }
    }
}
