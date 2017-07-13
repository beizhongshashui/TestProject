package com.yuyoubang.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 17/1/13.
 */

public class ApplyCommit implements Serializable{

    /**
     * result : [{"created_at":1484285660370,"updated_at":1484285660370,"id":1077836472451109,"data":{"phone":"18298336170","apply_state":0,"pic3":"http%3A%2F%2Fofdx4t772.bkt.clouddn.com%2F1077764296867870%3FimageView2","pic2":"http%3A%2F%2Fofdx4t772.bkt.clouddn.com%2F1077764129095709%3FimageView2","vip_card_number":"133333333333333330","real_name":"测试","user_name":"测试"},"uid":1056003526295659}]
     * error_code : 0
     */

    private int error_code;
    /**
     * created_at : 1484285660370
     * updated_at : 1484285660370
     * id : 1077836472451109
     * data : {"phone":"18298336170","apply_state":0,"pic3":"http%3A%2F%2Fofdx4t772.bkt.clouddn.com%2F1077764296867870%3FimageView2","pic2":"http%3A%2F%2Fofdx4t772.bkt.clouddn.com%2F1077764129095709%3FimageView2","vip_card_number":"133333333333333330","real_name":"测试","user_name":"测试"}
     * uid : 1056003526295659
     */

    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        private long created_at;
        private long updated_at;
        private long id;
        /**
         * phone : 18298336170
         * apply_state : 0
         * pic3 : http%3A%2F%2Fofdx4t772.bkt.clouddn.com%2F1077764296867870%3FimageView2
         * pic2 : http%3A%2F%2Fofdx4t772.bkt.clouddn.com%2F1077764129095709%3FimageView2
         * vip_card_number : 133333333333333330
         * real_name : 测试
         * user_name : 测试
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
            private String phone;
            private int apply_state;
            private String pic3;
            private String pic2;
            private String vip_card_number;
            private String real_name;
            private String user_name;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getApply_state() {
                return apply_state;
            }

            public void setApply_state(int apply_state) {
                this.apply_state = apply_state;
            }

            public String getPic3() {
                return pic3;
            }

            public void setPic3(String pic3) {
                this.pic3 = pic3;
            }

            public String getPic2() {
                return pic2;
            }

            public void setPic2(String pic2) {
                this.pic2 = pic2;
            }

            public String getVip_card_number() {
                return vip_card_number;
            }

            public void setVip_card_number(String vip_card_number) {
                this.vip_card_number = vip_card_number;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}
