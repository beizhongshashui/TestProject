package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public class FindRecomendBean implements Serializable {

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481114950501,"updated_at":1481114950501,"to_uid":995062101114881,"uid":962029406978050,"id":1024640785907752,"op_data":{"recommend_index":1,"is_like_to_user":0,"is_like_me":0},"op_name":"recommend","user":{"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"www.baidu.com1","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1},"id":995062101114881,"created_at":1479351923757,"updated_at":1481785634484}},{"created_at":1480066257369,"updated_at":1480066257369,"to_uid":1006883109404673,"uid":962029406978050,"id":1007046636929033,"op_data":{"recommend_index":8,"is_like_to_user":0,"is_like_me":0},"op_name":"recommend","user":{"user_data":{"verification_type":1,"birthday":1480056485000,"sys_comment":"1测试后台创建，2测试昵称和手机号唯一","state":1,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"www.baidu.com","user_name":"测试生日","recommend_by_sys":1},"id":1006883109404673,"created_at":1480056510051,"updated_at":1481732112150}},{"created_at":1481728369143,"updated_at":1481728369143,"to_uid":1001224037007411,"uid":1003969359970306,"id":1034932248969241,"op_data":{"recommend_index":"1","is_like_to_user":0,"is_like_me":0},"op_name":"recommend","user":{"user_data":{"verification_type":1,"user_name":"测试商家001","profile_pic_url":"www.baidu.com","gender":0,"user_location":"北京","user_group":"user","state":0,"sys_comment":"【请勿修改】","recommend_by_sys":1},"id":1001224037007411,"created_at":1479719203532,"updated_at":1481728369185}}]}
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
         * created_at : 1481114950501
         * updated_at : 1481114950501
         * to_uid : 995062101114881
         * uid : 962029406978050
         * id : 1024640785907752
         * op_data : {"recommend_index":1,"is_like_to_user":0,"is_like_me":0}
         * op_name : recommend
         * user : {"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"www.baidu.com1","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1},"id":995062101114881,"created_at":1479351923757,"updated_at":1481785634484}
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
             * recommend_index : 1
             * is_like_to_user : 0
             * is_like_me : 0
             */

            private OpDataBean op_data;
            private String op_name;
            /**
             * user_data : {"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"www.baidu.com1","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1}
             * id : 995062101114881
             * created_at : 1479351923757
             * updated_at : 1481785634484
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

            public static class OpDataBean implements Serializable{
                private int recommend_index;
                private int is_like_to_user;
                private int is_like_me;

                public int getRecommend_index() {
                    return recommend_index;
                }

                public void setRecommend_index(int recommend_index) {
                    this.recommend_index = recommend_index;
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
            }

            public static class UserBean implements Serializable{
                /**
                 * real_name : 修改后的真实姓名
                 * recommend_by_sys : 1
                 * state : 0
                 * province : 山东
                 * user_cover_pic_url : www.baidu.com1
                 * birthday : 439660800000
                 * user_tags : ["标签、标签1"]
                 * is_verification : 0
                 * user_group : user
                 * user_location : 天津
                 * user_desc : 描述描述
                 * gender : 1
                 * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600
                 * user_name : 陈鑫陈鑫
                 * verification_type : 1
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
                    private String real_name;
                    private int recommend_by_sys;
                    private int state;
                    private String province;
                    private String user_cover_pic_url;
                    private long birthday;
                    private int is_verification;
                    private String user_group;
                    private String user_location;
                    private String user_desc;
                    private int gender;
                    private String profile_pic_url;
                    private String user_name;
                    private int verification_type;
                    private List<String> user_tags;

                    public String getReal_name() {
                        return real_name;
                    }

                    public void setReal_name(String real_name) {
                        this.real_name = real_name;
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

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
                    }

                    public String getUser_cover_pic_url() {
                        return user_cover_pic_url;
                    }

                    public void setUser_cover_pic_url(String user_cover_pic_url) {
                        this.user_cover_pic_url = user_cover_pic_url;
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

                    public String getUser_desc() {
                        return user_desc;
                    }

                    public void setUser_desc(String user_desc) {
                        this.user_desc = user_desc;
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

                    public int getVerification_type() {
                        return verification_type;
                    }

                    public void setVerification_type(int verification_type) {
                        this.verification_type = verification_type;
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
