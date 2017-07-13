package com.yuyoubang.adapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/26.
 */

public class CheckUser implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1479781804088,"updated_at":1482032609497,"id":1002274307506177,"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京","is_like_to_user":0}}]}
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
         * created_at : 1479781804088
         * updated_at : 1482032609497
         * id : 1002274307506177
         * user_data : {"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京","is_like_to_user":0}
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
             * state : 0
             * birthday : 683650800000
             * is_verification : 0
             * user_group : user
             * user_location : 澳门
             * gender : 0
             * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600
             * user_name : 啊啊啊
             * user_cover_pic_url : http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600
             * verification_type : 2
             * user_desc : 什么鬼
             * user_tags : ["大叔","个性","萝莉"]
             * province : 北京
             * is_like_to_user : 0
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
                private String gender;
                private String profile_pic_url;
                private String user_name;
                private String user_cover_pic_url;
                private int verification_type;
                private String user_desc;
                private String province;
                private int is_like_to_user;
                private List<String> user_tags;

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

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
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

                public String getUser_cover_pic_url() {
                    return user_cover_pic_url;
                }

                public void setUser_cover_pic_url(String user_cover_pic_url) {
                    this.user_cover_pic_url = user_cover_pic_url;
                }

                public int getVerification_type() {
                    return verification_type;
                }

                public void setVerification_type(int verification_type) {
                    this.verification_type = verification_type;
                }

                public String getUser_desc() {
                    return user_desc;
                }

                public void setUser_desc(String user_desc) {
                    this.user_desc = user_desc;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public int getIs_like_to_user() {
                    return is_like_to_user;
                }

                public void setIs_like_to_user(int is_like_to_user) {
                    this.is_like_to_user = is_like_to_user;
                }

                public List<String> getUser_tags() {
                    return user_tags;
                }

                public void setUser_tags(List<String> user_tags) {
                    this.user_tags = user_tags;
                }
            }
        }
    }
}
