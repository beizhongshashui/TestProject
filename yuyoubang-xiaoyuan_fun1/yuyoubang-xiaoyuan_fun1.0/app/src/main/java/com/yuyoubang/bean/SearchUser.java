package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/18.
 */

public class SearchUser implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1477383263576,"updated_at":1482073142991,"id":962033467064324,"user_data":{"province":"广东省 深圳市","state":0,"user_name":"【修改】陈鑫12","profile_pic_url":"http://tva1.sinaimg.cn/crop.0.1.1242.1242.180/6f75f9e1jw8f86j6wlltvj20yi0ykael.jpg","gender":"","user_desc":"测试商家用户","user_location":"用户所在地","user_group":"user","verification_type":2,"birthday":655524610000,"is_verification":0,"user_tags":["阳光","帅气"],"user_cover_pic_url":"http://tc.sinaimg.cn/maxwidth.800/tc.service.weibo.com/p1_pstatp_com/c4807f7b55f8bdb0eea7ae0ae0e321e7.jpg","is_like_to_user":0,"is_like_me":0}},{"created_at":1479134471509,"updated_at":1481627988986,"id":991413861941256,"user_data":{"state":0,"user_group":"system","real_name":"陈鑫1114","user_desc":"2016年11月11日创建","user_name":"测试创建\u2014陈鑫","is_like_to_user":0,"is_like_me":0}},{"created_at":1479134673835,"updated_at":1479134673849,"id":991417250938889,"user_data":{"state":0,"user_group":"system","real_name":"陈鑫1114","user_desc":"2016年11月11日创建","user_name":"测试创建\u2014陈鑫","is_like_to_user":0,"is_like_me":0}},{"created_at":1479134675196,"updated_at":1479134675213,"id":991417284493322,"user_data":{"state":0,"user_group":"system","real_name":"陈鑫1114","user_desc":"2016年11月11日创建","user_name":"测试创建\u2014陈鑫","is_like_to_user":0,"is_like_me":0}},{"created_at":1479351923757,"updated_at":1481785634484,"id":995062101114881,"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"www.baidu.com1","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1,"is_like_to_user":0,"is_like_me":0}}]}
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
         * created_at : 1477383263576
         * updated_at : 1482073142991
         * id : 962033467064324
         * user_data : {"province":"广东省 深圳市","state":0,"user_name":"【修改】陈鑫12","profile_pic_url":"http://tva1.sinaimg.cn/crop.0.1.1242.1242.180/6f75f9e1jw8f86j6wlltvj20yi0ykael.jpg","gender":"","user_desc":"测试商家用户","user_location":"用户所在地","user_group":"user","verification_type":2,"birthday":655524610000,"is_verification":0,"user_tags":["阳光","帅气"],"user_cover_pic_url":"http://tc.sinaimg.cn/maxwidth.800/tc.service.weibo.com/p1_pstatp_com/c4807f7b55f8bdb0eea7ae0ae0e321e7.jpg","is_like_to_user":0,"is_like_me":0}
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
             * province : 广东省 深圳市
             * state : 0
             * user_name : 【修改】陈鑫12
             * profile_pic_url : http://tva1.sinaimg.cn/crop.0.1.1242.1242.180/6f75f9e1jw8f86j6wlltvj20yi0ykael.jpg
             * gender :
             * user_desc : 测试商家用户
             * user_location : 用户所在地
             * user_group : user
             * verification_type : 2
             * birthday : 655524610000
             * is_verification : 0
             * user_tags : ["阳光","帅气"]
             * user_cover_pic_url : http://tc.sinaimg.cn/maxwidth.800/tc.service.weibo.com/p1_pstatp_com/c4807f7b55f8bdb0eea7ae0ae0e321e7.jpg
             * is_like_to_user : 0
             * is_like_me : 0
             */

            private UserDataBean user_data;
            private int is_like_to_user;
            private int is_like_me;

            public int getIs_like_to_user() {
                return is_like_to_user;
            }

            public void setIs_like_to_user(int is_like_to_user) {
                this.is_like_to_user = is_like_to_user;
            }

            public int getIs_like_me() {
                return is_like_me;
            }

            public void setIs_like_me(int is_like_me) {
                this.is_like_me = is_like_me;
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
                private String province;
                private int state;
                private String user_name;
                private String profile_pic_url;
                private String gender;
                private String user_desc;
                private String user_location;
                private String user_group;
                private int verification_type;
                private long birthday;
                private int is_verification;
                private String user_cover_pic_url;
                private int is_like_to_user;
                private int is_like_me;
                private List<String> user_tags;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getProfile_pic_url() {
                    return profile_pic_url;
                }

                public void setProfile_pic_url(String profile_pic_url) {
                    this.profile_pic_url = profile_pic_url;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public String getUser_desc() {
                    return user_desc;
                }

                public void setUser_desc(String user_desc) {
                    this.user_desc = user_desc;
                }

                public String getUser_location() {
                    return user_location;
                }

                public void setUser_location(String user_location) {
                    this.user_location = user_location;
                }

                public String getUser_group() {
                    return user_group;
                }

                public void setUser_group(String user_group) {
                    this.user_group = user_group;
                }

                public int getVerification_type() {
                    return verification_type;
                }

                public void setVerification_type(int verification_type) {
                    this.verification_type = verification_type;
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

                public String getUser_cover_pic_url() {
                    return user_cover_pic_url;
                }

                public void setUser_cover_pic_url(String user_cover_pic_url) {
                    this.user_cover_pic_url = user_cover_pic_url;
                }

                public int getIs_like_to_user() {
                    return is_like_to_user;
                }

                public void setIs_like_to_user(int is_like_to_user) {
                    this.is_like_to_user = is_like_to_user;
                }

                public int getIs_like_me() {
                    return is_like_me;
                }

                public void setIs_like_me(int is_like_me) {
                    this.is_like_me = is_like_me;
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
