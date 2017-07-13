package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/22.
 */

public class CommentDetail implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1482391644791,"updated_at":1482391644791,"to_uid":976528209936404,"uid":1002274307506177,"id":1046060156911624,"op_data":{"trip_name":"北京起止澳大利亚凯恩斯新西兰11日游","biz_user_name":"七月","user_name":"啊啊啊","biz_uid":976528209936404,"uid":1002274307506177,"trip_id":976687358607389,"trip_participate_id":1024077474103301,"value":5,"comment":"好"},"op_name":"trip_comment"}]}
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
         * created_at : 1482391644791
         * updated_at : 1482391644791
         * to_uid : 976528209936404
         * uid : 1002274307506177
         * id : 1046060156911624
         * op_data : {"trip_name":"北京起止澳大利亚凯恩斯新西兰11日游","biz_user_name":"七月","user_name":"啊啊啊","biz_uid":976528209936404,"uid":1002274307506177,"trip_id":976687358607389,"trip_participate_id":1024077474103301,"value":5,"comment":"好"}
         * op_name : trip_comment
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
             * trip_name : 北京起止澳大利亚凯恩斯新西兰11日游
             * biz_user_name : 七月
             * user_name : 啊啊啊
             * biz_uid : 976528209936404
             * uid : 1002274307506177
             * trip_id : 976687358607389
             * trip_participate_id : 1024077474103301
             * value : 5
             * comment : 好
             */

            private OpDataBean op_data;
            private String op_name;

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

            public static class OpDataBean implements Serializable{
                private String trip_name;
                private String biz_user_name;
                private String user_name;
                private long biz_uid;
                private long uid;
                private long trip_id;
                private long trip_participate_id;
                private int value;
                private String comment;
                private String biz_user_reply;

                public String getBiz_user_reply() {
                    return biz_user_reply;
                }

                public void setBiz_user_reply(String biz_user_reply) {
                    this.biz_user_reply = biz_user_reply;
                }

                public String getTrip_name() {
                    return trip_name;
                }

                public void setTrip_name(String trip_name) {
                    this.trip_name = trip_name;
                }

                public String getBiz_user_name() {
                    return biz_user_name;
                }

                public void setBiz_user_name(String biz_user_name) {
                    this.biz_user_name = biz_user_name;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public long getBiz_uid() {
                    return biz_uid;
                }

                public void setBiz_uid(long biz_uid) {
                    this.biz_uid = biz_uid;
                }

                public long getUid() {
                    return uid;
                }

                public void setUid(long uid) {
                    this.uid = uid;
                }

                public long getTrip_id() {
                    return trip_id;
                }

                public void setTrip_id(long trip_id) {
                    this.trip_id = trip_id;
                }

                public long getTrip_participate_id() {
                    return trip_participate_id;
                }

                public void setTrip_participate_id(long trip_participate_id) {
                    this.trip_participate_id = trip_participate_id;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }
            }
        }
    }
}
