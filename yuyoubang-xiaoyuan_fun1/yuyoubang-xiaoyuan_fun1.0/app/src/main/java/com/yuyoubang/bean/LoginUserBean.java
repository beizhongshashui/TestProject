package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by xiaoyuan on 16/11/10.
 */
public class LoginUserBean extends BaseBean implements Serializable {

    /**
     * created_at : 1478512754894
     * updated_at : 1478757395868
     * id : 980983181541377
     * user_data : {"state":0,"birthday":1478707200000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/985087459000333?imageView2/1/w/600/h/600","user_name":"小源源"}
     */

    private ResultBean result;
    /**
     * result : {"created_at":1478512754894,"updated_at":1478757395868,"id":980983181541377,"user_data":{"state":0,"birthday":1478707200000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/985087459000333?imageView2/1/w/600/h/600","user_name":"小源源"}}
     * error_code : 0
     */


    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        private long created_at;
        private long updated_at;
        private long id;
        /**
         * state : 0
         * birthday : 1478707200000
         * is_verification : 0
         * user_group : user
         * user_location : 北京
         * gender : 0
         * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/985087459000333?imageView2/1/w/600/h/600
         * user_name : 小源源
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

        public static class UserDataBean implements Serializable{
            private int state;
            private long birthday;
            private int is_verification;
            private String user_group;
            private String user_location;
            private int gender;
            private String profile_pic_url;
            private String user_name;

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public int getIs_verification() {
                return is_verification;
            }

            public void setIs_verification(int is_verification) {
                this.is_verification = is_verification;
            }

            public String getUser_group() {
                return user_group;
            }

            public void setUser_group(String user_group) {
                this.user_group = user_group;
            }

            public String getUser_location() {
                return user_location;
            }

            public void setUser_location(String user_location) {
                this.user_location = user_location;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

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
        }
    }
}
