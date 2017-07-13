package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 17/1/6.
 */

public class StatusLike implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1482733893180,"updated_at":1482733893180,"op_name":"status_like","data_id":1024680313028656,"uid":982165555838977,"id":1051802142310446,"op_data":null,"user":{"user_data":{"user_name":"锋在吹","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1065238695837785?imageView2","gender":0,"user_location":"北京","birthday":721497600000},"id":982165555838977,"created_at":1478583229087,"updated_at":1483535484012}},{"created_at":1482407560118,"updated_at":1482407560118,"op_name":"status_like","data_id":1024680313028656,"uid":1002274307506177,"id":1046327183081493,"op_data":null,"user":{"user_data":{"user_name":"啊啊啊","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1064910466383934?imageView2/1/w/600/h/600","gender":0,"user_location":"澳门","birthday":684255600000},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483613741291}}]}
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
         * created_at : 1482733893180
         * updated_at : 1482733893180
         * op_name : status_like
         * data_id : 1024680313028656
         * uid : 982165555838977
         * id : 1051802142310446
         * op_data : null
         * user : {"user_data":{"user_name":"锋在吹","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1065238695837785?imageView2","gender":0,"user_location":"北京","birthday":721497600000},"id":982165555838977,"created_at":1478583229087,"updated_at":1483535484012}
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
            private String op_name;
            private long data_id;
            private long uid;
            private long id;
            private Object op_data;
            /**
             * user_data : {"user_name":"锋在吹","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1065238695837785?imageView2","gender":0,"user_location":"北京","birthday":721497600000}
             * id : 982165555838977
             * created_at : 1478583229087
             * updated_at : 1483535484012
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

            public Object getOp_data() {
                return op_data;
            }

            public void setOp_data(Object op_data) {
                this.op_data = op_data;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean implements Serializable{
                /**
                 * user_name : 锋在吹
                 * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1065238695837785?imageView2
                 * gender : 0
                 * user_location : 北京
                 * birthday : 721497600000
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
                    private String user_location;
                    private long birthday;

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

                    public String getUser_location() {
                        return user_location;
                    }

                    public void setUser_location(String user_location) {
                        this.user_location = user_location;
                    }

                    public long getBirthday() {
                        return birthday;
                    }

                    public void setBirthday(long birthday) {
                        this.birthday = birthday;
                    }
                }
            }
        }
    }
}
