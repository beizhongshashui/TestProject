package com.yuyoubang.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/2.
 */

public class UserSureName implements Serializable {

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481197468964,"updated_at":1481197599450,"id":1026025208217645,"data":{"user_name":"啊啊啊","real_name":"陈宏","vip_card_number":"37048119881015095X","pic2":"www.google.com","pic3":"www.renren.com","apply_state":1,"phone":18519330818},"uid":1002274307506177}]}
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
        /**
         * created_at : 1481197468964
         * updated_at : 1481197599450
         * id : 1026025208217645
         * data : {"user_name":"啊啊啊","real_name":"陈宏","vip_card_number":"37048119881015095X","pic2":"www.google.com","pic3":"www.renren.com","apply_state":1,"phone":18519330818}
         * uid : 1002274307506177
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            private long created_at;
            private long updated_at;
            private long id;
            /**
             * user_name : 啊啊啊
             * real_name : 陈宏
             * vip_card_number : 37048119881015095X
             * pic2 : www.google.com
             * pic3 : www.renren.com
             * apply_state : 1
             * phone : 18519330818
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
                private String user_name;
                private String real_name;
                private String vip_card_number;
                private String pic2;
                private String pic3;
                private int apply_state;
                private String phone;

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getReal_name() {
                    return real_name;
                }

                public void setReal_name(String real_name) {
                    this.real_name = real_name;
                }

                public String getVip_card_number() {
                    return vip_card_number;
                }

                public void setVip_card_number(String vip_card_number) {
                    this.vip_card_number = vip_card_number;
                }

                public String getPic2() {
                    return pic2;
                }

                public void setPic2(String pic2) {
                    this.pic2 = pic2;
                }

                public String getPic3() {
                    return pic3;
                }

                public void setPic3(String pic3) {
                    this.pic3 = pic3;
                }

                public int getApply_state() {
                    return apply_state;
                }

                public void setApply_state(int apply_state) {
                    this.apply_state = apply_state;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }
            }
        }
    }
}
