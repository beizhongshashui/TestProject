package com.yuyoubang.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/8.
 */

public class ApplyCompanyState implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481167066576,"updated_at":1481167066576,"id":1025515147296771,"data":{"user_name":"测试","apply_state":0,"contact_phone":"13333333333","business_license_pic":"www.baidu.com","legal_person":"测试","company_address":"北京","company_name":"极牛","contact_user_name":"测试"},"uid":976528209936404}]}
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
         * created_at : 1481167066576
         * updated_at : 1481167066576
         * id : 1025515147296771
         * data : {"user_name":"测试","apply_state":0,"contact_phone":"13333333333","business_license_pic":"www.baidu.com","legal_person":"测试","company_address":"北京","company_name":"极牛","contact_user_name":"测试"}
         * uid : 976528209936404
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
             * user_name : 测试
             * apply_state : 0
             * contact_phone : 13333333333
             * business_license_pic : www.baidu.com
             * legal_person : 测试
             * company_address : 北京
             * company_name : 极牛
             * contact_user_name : 测试
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
                private int apply_state;
                private String contact_phone;
                private String business_license_pic;
                private String legal_person;
                private String company_address;
                private String company_name;
                private String contact_user_name;

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public int getApply_state() {
                    return apply_state;
                }

                public void setApply_state(int apply_state) {
                    this.apply_state = apply_state;
                }

                public String getContact_phone() {
                    return contact_phone;
                }

                public void setContact_phone(String contact_phone) {
                    this.contact_phone = contact_phone;
                }

                public String getBusiness_license_pic() {
                    return business_license_pic;
                }

                public void setBusiness_license_pic(String business_license_pic) {
                    this.business_license_pic = business_license_pic;
                }

                public String getLegal_person() {
                    return legal_person;
                }

                public void setLegal_person(String legal_person) {
                    this.legal_person = legal_person;
                }

                public String getCompany_address() {
                    return company_address;
                }

                public void setCompany_address(String company_address) {
                    this.company_address = company_address;
                }

                public String getCompany_name() {
                    return company_name;
                }

                public void setCompany_name(String company_name) {
                    this.company_name = company_name;
                }

                public String getContact_user_name() {
                    return contact_user_name;
                }

                public void setContact_user_name(String contact_user_name) {
                    this.contact_user_name = contact_user_name;
                }
            }
        }
    }
}
