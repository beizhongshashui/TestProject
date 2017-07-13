package com.yuyoubang.bean.findfollowinfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/11/30.
 */

public class OtherHome implements Serializable{

    private ResultBean result;
    /**
     * result : {"user":[{"created_at":1479351923757,"updated_at":1480304971677,"id":995062101114881,"user_data":{"verification_type":2,"user_name":"陈鑫陈鑫","profile_pic_url":"www.baidu.com","gender":1,"user_desc":"描述描述","user_location":"滕州","user_group":"user","is_verification":0,"user_tags":["标签、标签1"],"birthday":"14786700001","user_cover_pic_url":"www.baidu.com1","province":"山东","state":0,"recommend_by_sys":1,"real_name":"修改后的真实姓名","follow_count":2,"fans_count":0,"status_total_count":2,"is_followed":0}}],"list":[{"created_at":1479816201883,"updated_at":1479893397482,"id":1002851393404971,"data":{"user_name":"陈鑫陈鑫","user_location":"北京","location":"南京夫子庙","pics":["111","222","333","444","555","666","777","888","999"],"content":123456789,"like_comment_total_count":18,"status_comment_count":9,"status_like_count":1},"uid":995062101114881},{"created_at":1479815981215,"updated_at":1479883540871,"id":1002847702417450,"data":{"state":1,"content":"内容内容内容。。。。。123","pics":["aaaaaaaaaaaa","bbbbbbbbbbbbbbbbbb","ccccccccccccccccccccc","dddddddddddddddddddddd","eeeeeeeeeeeeeeeeeeeeeee","ffffffffffffffffffffffffffffffff","gggggggggggggggggggggggggggggg","hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"],"location":"北京市朝阳区望京洪泰众创空间2楼","user_location":"北京","user_name":"陈鑫陈鑫","like_comment_total_count":8,"status_comment_count":4,"status_like_count":0},"uid":995062101114881}]}
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
         * created_at : 1479351923757
         * updated_at : 1480304971677
         * id : 995062101114881
         * user_data : {"verification_type":2,"user_name":"陈鑫陈鑫","profile_pic_url":"www.baidu.com","gender":1,"user_desc":"描述描述","user_location":"滕州","user_group":"user","is_verification":0,"user_tags":["标签、标签1"],"birthday":"14786700001","user_cover_pic_url":"www.baidu.com1","province":"山东","state":0,"recommend_by_sys":1,"real_name":"修改后的真实姓名","follow_count":2,"fans_count":0,"status_total_count":2,"is_followed":0}
         */

        private List<UserBean> user;
        /**
         * created_at : 1479816201883
         * updated_at : 1479893397482
         * id : 1002851393404971
         * data : {"user_name":"陈鑫陈鑫","user_location":"北京","location":"南京夫子庙","pics":["111","222","333","444","555","666","777","888","999"],"content":123456789,"like_comment_total_count":18,"status_comment_count":9,"status_like_count":1}
         * uid : 995062101114881
         */

        private List<ListBean> list;

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class UserBean implements Serializable{
            private long created_at;
            private long updated_at;
            private long id;
            /**
             * verification_type : 2
             * user_name : 陈鑫陈鑫
             * profile_pic_url : www.baidu.com
             * gender : 1
             * user_desc : 描述描述
             * user_location : 滕州
             * user_group : user
             * is_verification : 0
             * user_tags : ["标签、标签1"]
             * birthday : 14786700001
             * user_cover_pic_url : www.baidu.com1
             * province : 山东
             * state : 0
             * recommend_by_sys : 1
             * real_name : 修改后的真实姓名
             * follow_count : 2
             * fans_count : 0
             * status_total_count : 2
             * is_followed : 0
             */

            private UserDataBean user_data;
            private int is_followed;
            private int is_like_to_user;

            public int getIs_followed() {
                return is_followed;
            }

            public void setIs_followed(int is_followed) {
                this.is_followed = is_followed;
            }

            public int getIs_like_to_user() {
                return is_like_to_user;
            }

            public void setIs_like_to_user(int is_like_to_user) {
                this.is_like_to_user = is_like_to_user;
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
                private int verification_type;
                private String user_name;
                private String profile_pic_url;
                private String gender;
                private String user_desc;
                private String user_location;
                private String user_group;
                private int is_verification;
                private long birthday;
                private String user_cover_pic_url;
                private String province;
                private int state;
                private int recommend_by_sys;
                private String real_name;
                private int follow_count;
                private int fans_count;
                private int status_total_count;
                private int is_followed;
                private int is_like_to_user;
                private List<String> user_tags;

                public int getIs_like_to_user() {
                    return is_like_to_user;
                }

                public void setIs_like_to_user(int is_like_to_user) {
                    this.is_like_to_user = is_like_to_user;
                }

                public int getVerification_type() {
                    return verification_type;
                }

                public void setVerification_type(int verification_type) {
                    this.verification_type = verification_type;
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

                public int getIs_verification() {
                    return is_verification;
                }

                public void setIs_verification(int is_verification) {
                    this.is_verification = is_verification;
                }

                public long getBirthday() {
                    return birthday;
                }

                public void setBirthday(long birthday) {
                    this.birthday = birthday;
                }

                public String getUser_cover_pic_url() {
                    return user_cover_pic_url;
                }

                public void setUser_cover_pic_url(String user_cover_pic_url) {
                    this.user_cover_pic_url = user_cover_pic_url;
                }

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

                public int getRecommend_by_sys() {
                    return recommend_by_sys;
                }

                public void setRecommend_by_sys(int recommend_by_sys) {
                    this.recommend_by_sys = recommend_by_sys;
                }

                public String getReal_name() {
                    return real_name;
                }

                public void setReal_name(String real_name) {
                    this.real_name = real_name;
                }

                public int getFollow_count() {
                    return follow_count;
                }

                public void setFollow_count(int follow_count) {
                    this.follow_count = follow_count;
                }

                public int getFans_count() {
                    return fans_count;
                }

                public void setFans_count(int fans_count) {
                    this.fans_count = fans_count;
                }

                public int getStatus_total_count() {
                    return status_total_count;
                }

                public void setStatus_total_count(int status_total_count) {
                    this.status_total_count = status_total_count;
                }

                public int getIs_followed() {
                    return is_followed;
                }

                public void setIs_followed(int is_followed) {
                    this.is_followed = is_followed;
                }

                public List<String> getUser_tags() {
                    return user_tags;
                }

                public void setUser_tags(List<String> user_tags) {
                    this.user_tags = user_tags;
                }
            }
        }

        public static class ListBean implements Serializable{
            private long created_at;
            private long updated_at;
            private long id;
            /**
             * user_name : 陈鑫陈鑫
             * user_location : 北京
             * location : 南京夫子庙
             * pics : ["111","222","333","444","555","666","777","888","999"]
             * content : 123456789
             * like_comment_total_count : 18
             * status_comment_count : 9
             * status_like_count : 1
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
                private String user_location;
                private String location;
                private Object content;
                private int like_comment_total_count;
                private int status_comment_count;
                private int status_like_count;
                private List<String> pics;

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getUser_location() {
                    return user_location;
                }

                public void setUser_location(String user_location) {
                    this.user_location = user_location;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public Object getContent() {
                    return content;
                }

                public void setContent(Object content) {
                    this.content = content;
                }

                public int getLike_comment_total_count() {
                    return like_comment_total_count;
                }

                public void setLike_comment_total_count(int like_comment_total_count) {
                    this.like_comment_total_count = like_comment_total_count;
                }

                public int getStatus_comment_count() {
                    return status_comment_count;
                }

                public void setStatus_comment_count(int status_comment_count) {
                    this.status_comment_count = status_comment_count;
                }

                public int getStatus_like_count() {
                    return status_like_count;
                }

                public void setStatus_like_count(int status_like_count) {
                    this.status_like_count = status_like_count;
                }

                public List<String> getPics() {
                    return pics;
                }

                public void setPics(List<String> pics) {
                    this.pics = pics;
                }
            }
        }
    }
}
