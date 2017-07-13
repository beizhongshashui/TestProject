package com.yuyoubang.bean.findfollowinfo;

import java.io.Serializable;

/**
 * Created by hongchen on 16/11/24.
 */

public class LikeListBean implements Serializable {
    private long created_at;
    private long updated_at;
    private String op_name;
    private long data_id;
    private long uid;
    private long id;
    private OpDataBean op_data;
    /**
     * user_data : {"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"}
     * id : 995062101114881
     * created_at : 1479351923757
     * updated_at : 1479802505193
     */

    private UserBean user;

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

    public String getOp_name() {
        return op_name;
    }

    public void setOp_name(String op_name) {
        this.op_name = op_name;
    }

    public long getData_id() {
        return data_id;
    }

    public void setData_id(long data_id) {
        this.data_id = data_id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OpDataBean getOp_data() {
        return op_data;
    }

    public void setOp_data(OpDataBean op_data) {
        this.op_data = op_data;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class OpDataBean implements Serializable {
    }

    public static class UserBean implements Serializable {
        /**
         * user_name : 陈鑫陈鑫
         * gender : 1
         * user_location : 北京北京
         */

        private UserDataBean user_data;
        private long id;
        private long created_at;
        private long updated_at;

        public UserDataBean getUser_data() {
            return user_data;
        }

        public void setUser_data(UserDataBean user_data) {
            this.user_data = user_data;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

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

        public static class UserDataBean implements Serializable {
            private String user_name;
            private String gender;
            private String user_location;
            private String profile_pic_url;

            public String getProfile_pic_url() {
                return profile_pic_url;
            }

            public void setProfile_pic_url(String profile_pic_url) {
                this.profile_pic_url = profile_pic_url;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getUser_location() {
                return user_location;
            }

            public void setUser_location(String user_location) {
                this.user_location = user_location;
            }
        }
    }
}
