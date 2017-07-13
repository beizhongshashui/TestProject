package com.yuyoubang.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/7.
 */

public class CommentNew implements Serializable{

    /**
     * result : [{"created_at":1481099218504,"updated_at":1481099218504,"to_uid":976528209936404,"uid":1002274307506177,"id":1024376846745622,"op_data":{"biz_user_name":"七月","user_name":"啊啊啊","uid":"1002274307506177","trip_id":"976665565003804","trip_participate_id":"1024076954009604","value":"1","comment":"abc"},"op_name":"trip_comment"}]
     * error_code : 0
     */

    private int error_code;
    /**
     * created_at : 1481099218504
     * updated_at : 1481099218504
     * to_uid : 976528209936404
     * uid : 1002274307506177
     * id : 1024376846745622
     * op_data : {"biz_user_name":"七月","user_name":"啊啊啊","uid":"1002274307506177","trip_id":"976665565003804","trip_participate_id":"1024076954009604","value":"1","comment":"abc"}
     * op_name : trip_comment
     */

    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        private long created_at;
        private long updated_at;
        private long to_uid;
        private long uid;
        private long id;
        /**
         * biz_user_name : 七月
         * user_name : 啊啊啊
         * uid : 1002274307506177
         * trip_id : 976665565003804
         * trip_participate_id : 1024076954009604
         * value : 1
         * comment : abc
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
            private String biz_user_name;
            private String user_name;
            private String uid;
            private String trip_id;
            private String trip_participate_id;
            private String value;
            private String comment;

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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getTrip_id() {
                return trip_id;
            }

            public void setTrip_id(String trip_id) {
                this.trip_id = trip_id;
            }

            public String getTrip_participate_id() {
                return trip_participate_id;
            }

            public void setTrip_participate_id(String trip_participate_id) {
                this.trip_participate_id = trip_participate_id;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
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
