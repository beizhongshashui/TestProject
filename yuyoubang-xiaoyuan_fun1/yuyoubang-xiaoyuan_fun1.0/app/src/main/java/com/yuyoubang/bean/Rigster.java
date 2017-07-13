package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by xiaoyuan on 16/11/7.
 */
public class Rigster extends BaseBean implements Serializable {


    /**
     * result : true
     * new_user : false
     * user : {"created_at":1482984315835,"updated_at":1482984315835,"id":1056003526295659,"user_data":{"phone":"18298336170"}}
     */

    private boolean result;
    private boolean new_user;
    /**
     * created_at : 1482984315835
     * updated_at : 1482984315835
     * id : 1056003526295659
     * user_data : {"phone":"18298336170"}
     */

    private UserBean user;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isNew_user() {
        return new_user;
    }

    public void setNew_user(boolean new_user) {
        this.new_user = new_user;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean implements Serializable {
        private long created_at;
        private long updated_at;
        private long id;
        /**
         * phone : 18298336170
         */

        private UserDataBean user_data;

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

        public UserDataBean getUser_data() {
            return user_data;
        }

        public void setUser_data(UserDataBean user_data) {
            this.user_data = user_data;
        }

        public static class UserDataBean implements Serializable {
            private String phone;
            private String user_name;

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
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
