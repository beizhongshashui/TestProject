package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/26.
 */

public class MineVisit implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1482720935904,"updated_at":1482720935904,"to_uid":1002274307506177,"uid":982165555838977,"id":1051584743145478,"op_data":{"is_like_to_user":1},"op_name":"visit","user":{"user_data":{"user_tags":["我的名字"],"province":"北京","user_location":"昌平区","verification_type":2,"recommend_by_sys":0,"state":0,"birthday":721497600000,"is_verification":0,"user_group":"user","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2","user_name":"锋在吹","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2","user_desc":"我就是我，不一样的烟火"},"id":982165555838977,"created_at":1478583229087,"updated_at":1482668965316}}]}
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
         * created_at : 1482720935904
         * updated_at : 1482720935904
         * to_uid : 1002274307506177
         * uid : 982165555838977
         * id : 1051584743145478
         * op_data : {"is_like_to_user":1}
         * op_name : visit
         * user : {"user_data":{"user_tags":["我的名字"],"province":"北京","user_location":"昌平区","verification_type":2,"recommend_by_sys":0,"state":0,"birthday":721497600000,"is_verification":0,"user_group":"user","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2","user_name":"锋在吹","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2","user_desc":"我就是我，不一样的烟火"},"id":982165555838977,"created_at":1478583229087,"updated_at":1482668965316}
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
            private long to_uid;
            private long uid;
            private long id;
            /**
             * is_like_to_user : 1
             */

            private OpDataBean op_data;
            private String op_name;
            /**
             * user_data : {"user_tags":["我的名字"],"province":"北京","user_location":"昌平区","verification_type":2,"recommend_by_sys":0,"state":0,"birthday":721497600000,"is_verification":0,"user_group":"user","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2","user_name":"锋在吹","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2","user_desc":"我就是我，不一样的烟火"}
             * id : 982165555838977
             * created_at : 1478583229087
             * updated_at : 1482668965316
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

            public long getTo_uid() {
                return to_uid;
            }

            public void setTo_uid(long to_uid) {
                this.to_uid = to_uid;
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

            public String getOp_name() {
                return op_name;
            }

            public void setOp_name(String op_name) {
                this.op_name = op_name;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class OpDataBean {
                private int is_like_to_user;

                public int getIs_like_to_user() {
                    return is_like_to_user;
                }

                public void setIs_like_to_user(int is_like_to_user) {
                    this.is_like_to_user = is_like_to_user;
                }
            }

            public static class UserBean implements Serializable{
                /**
                 * user_tags : ["我的名字"]
                 * province : 北京
                 * user_location : 昌平区
                 * verification_type : 2
                 * recommend_by_sys : 0
                 * state : 0
                 * birthday : 721497600000
                 * is_verification : 0
                 * user_group : user
                 * gender : 0
                 * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2
                 * user_name : 锋在吹
                 * user_cover_pic_url : http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2
                 * user_desc : 我就是我，不一样的烟火
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

                public static class UserDataBean implements Serializable{
                    private String province;
                    private String user_location;
                    private int verification_type;
                    private int recommend_by_sys;
                    private int state;
                    private long birthday;
                    private int is_verification;
                    private String user_group;
                    private String gender;
                    private String profile_pic_url;
                    private String user_name;
                    private String user_cover_pic_url;
                    private String user_desc;
                    private List<String> user_tags;

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
                    }

                    public String getUser_location() {
                        return user_location;
                    }

                    public void setUser_location(String user_location) {
                        this.user_location = user_location;
                    }

                    public int getVerification_type() {
                        return verification_type;
                    }

                    public void setVerification_type(int verification_type) {
                        this.verification_type = verification_type;
                    }

                    public int getRecommend_by_sys() {
                        return recommend_by_sys;
                    }

                    public void setRecommend_by_sys(int recommend_by_sys) {
                        this.recommend_by_sys = recommend_by_sys;
                    }

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

                    public String getUser_desc() {
                        return user_desc;
                    }

                    public void setUser_desc(String user_desc) {
                        this.user_desc = user_desc;
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
}
