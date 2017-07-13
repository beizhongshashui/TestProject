package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/12/13.
 */
public class TripLikeListBean implements Serializable {


    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481512740745,"updated_at":1481512740745,"op_name":"like","data_id":976647764377626,"uid":982165555838977,"id":1031314594660356,"op_data":null,"user":{"user_data":{"user_name":"锋在吹"},"id":982165555838977,"created_at":1478583229087,"updated_at":1481286257575}}]}
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
         * created_at : 1481512740745
         * updated_at : 1481512740745
         * op_name : like
         * data_id : 976647764377626
         * uid : 982165555838977
         * id : 1031314594660356
         * op_data : null
         * user : {"user_data":{"user_name":"锋在吹"},"id":982165555838977,"created_at":1478583229087,"updated_at":1481286257575}
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
             * user_data : {"user_name":"锋在吹"}
             * id : 982165555838977
             * created_at : 1478583229087
             * updated_at : 1481286257575
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

                public static class UserDataBean {
                    private String user_name;

                    public String getUser_name() {
                        return user_name;
                    }

                    public void setUser_name(String user_name) {
                        this.user_name = user_name;
                    }
                }
            }
        }
    }
}
