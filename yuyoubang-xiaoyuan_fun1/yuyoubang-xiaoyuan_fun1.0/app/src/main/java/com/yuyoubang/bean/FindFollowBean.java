package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public class FindFollowBean implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1479294591874,"updated_at":1479294591874,"id":994100229767196,"data":{"user_name":"测试用户001","user_location":"北京","location":"北京","pics":[],"content":"也是我的小动态"},"uid":962080376160261,"user":{"user_data":{"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0},"id":962080376160261,"created_at":1477386059719,"updated_at":1478959953447}},{"created_at":1478183129627,"updated_at":1478183692895,"id":975452991717378,"data":{"location":"北京","content":"我的小动态"},"uid":962080376160261,"user":{"user_data":{"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0},"id":962080376160261,"created_at":1477386059719,"updated_at":1478959953447}}]}
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

    public static class ResultBean implements Serializable {
        /**
         * created_at : 1479294591874
         * updated_at : 1479294591874
         * id : 994100229767196
         * data : {"user_name":"测试用户001","user_location":"北京","location":"北京","pics":[],"content":"也是我的小动态"}
         * uid : 962080376160261
         * user : {"user_data":{"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0},"id":962080376160261,"created_at":1477386059719,"updated_at":1478959953447}
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
             * user_name : 测试用户001
             * user_location : 北京
             * location : 北京
             * pics : []
             * content : 也是我的小动态
             */

            private DataBean data;
            private long uid;
            /**
             * user_data : {"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0}
             * id : 962080376160261
             * created_at : 1477386059719
             * updated_at : 1478959953447
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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class DataBean implements Serializable {
                private String user_name;
                private String user_location;
                private String location;
                private String content;
                private String status_comment_count;
                private String status_like_count;
                private String like_count;
                private String comment_count;

                public String getLike_count() {
                    return like_count;
                }

                public void setLike_count(String like_count) {
                    this.like_count = like_count;
                }

                public String getComment_count() {
                    return comment_count;
                }

                public void setComment_count(String comment_count) {
                    this.comment_count = comment_count;
                }

                private List<String> pics;

                public String getStatus_comment_count() {
                    return status_comment_count;
                }

                public void setStatus_comment_count(String status_comment_count) {
                    this.status_comment_count = status_comment_count;
                }

                public String getStatus_like_count() {
                    return status_like_count;
                }

                public void setStatus_like_count(String status_like_count) {
                    this.status_like_count = status_like_count;
                }

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

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public List<String> getPics() {
                    return pics;
                }

                public void setPics(List<String> pics) {
                    this.pics = pics;
                }
            }

            public static class UserBean  implements Serializable{
                /**
                 * user_name : 测试用户001
                 * profile_pic_url : http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg
                 * gender : 1
                 * user_desc : 测试用户（test_user_001）
                 * user_location : 深圳
                 * user_group : user
                 * verification_type : 1
                 * birthday : 1467354028000
                 * state : 0
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
                    private String user_name;
                    private String profile_pic_url;
                    private int gender;
                    private String user_desc;
                    private String user_location;
                    private String user_group;
                    private int verification_type;
                    private long birthday;
                    private int state;
                    private String province;

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
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

                    public int getGender() {
                        return gender;
                    }

                    public void setGender(int gender) {
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

                    public int getState() {
                        return state;
                    }

                    public void setState(int state) {
                        this.state = state;
                    }
                }
            }
        }
    }
}
