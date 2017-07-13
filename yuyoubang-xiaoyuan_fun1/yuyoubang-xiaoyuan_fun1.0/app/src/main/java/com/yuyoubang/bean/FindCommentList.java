package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/11/23.
 */

public class FindCommentList implements Serializable{

    /**
     * list : [{"created_at":1479813802937,"updated_at":1479813802937,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002811144863782,"op_data":{"comment":"逆魔"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810881929,"updated_at":1479810881929,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002762138615842,"op_data":{"comment":"哦送"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810620410,"updated_at":1479810620410,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002757759762465,"op_data":{"comment":"提Mook理工"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810608465,"updated_at":1479810608465,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002757558435872,"op_data":{"comment":"提Mook"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810478155,"updated_at":1479810478155,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002755377397791,"op_data":{"comment":"dsmvkldsmklv"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810462285,"updated_at":1479810462285,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002755108962334,"op_data":{"comment":""},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479809483518,"updated_at":1479809483518,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002738684067869,"op_data":{"comment":"sdbjdsvj"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}}]
     * total_count : 7
     */

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1479813802937,"updated_at":1479813802937,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002811144863782,"op_data":{"comment":"逆魔"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810881929,"updated_at":1479810881929,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002762138615842,"op_data":{"comment":"哦送"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810620410,"updated_at":1479810620410,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002757759762465,"op_data":{"comment":"提Mook理工"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810608465,"updated_at":1479810608465,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002757558435872,"op_data":{"comment":"提Mook"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810478155,"updated_at":1479810478155,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002755377397791,"op_data":{"comment":"dsmvkldsmklv"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479810462285,"updated_at":1479810462285,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002755108962334,"op_data":{"comment":""},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}},{"created_at":1479809483518,"updated_at":1479809483518,"op_name":"status_comment","data_id":1001440681197630,"uid":980983181541377,"id":1002738684067869,"op_data":{"comment":"sdbjdsvj"},"user":{"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}}],"total_count":7}
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
        private int total_count;
        /**
         * created_at : 1479813802937
         * updated_at : 1479813802937
         * op_name : status_comment
         * data_id : 1001440681197630
         * uid : 980983181541377
         * id : 1002811144863782
         * op_data : {"comment":"逆魔"}
         * user : {"user_data":{"user_name":"小源源","gender":0,"user_location":"北京"},"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698}
         */

        private List<ListBean> list;

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

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
            /**
             * comment : 逆魔
             */

            private OpDataBean op_data;
            /**
             * user_data : {"user_name":"小源源","gender":0,"user_location":"北京"}
             * id : 980983181541377
             * created_at : 1478512754894
             * updated_at : 1478763226698
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

            public static class OpDataBean implements Serializable{
                private String comment;

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }
            }

            public static class UserBean implements Serializable{
                /**
                 * user_name : 小源源
                 * gender : 0
                 * user_location : 北京
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
                    private int gender;
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
                }
            }
        }
    }
}
