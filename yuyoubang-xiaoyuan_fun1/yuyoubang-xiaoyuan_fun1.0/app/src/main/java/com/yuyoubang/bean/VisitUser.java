package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by hongchen on 16/12/26.
 */

public class VisitUser implements Serializable{

    /**
     * created_at : 1482720935904
     * updated_at : 1482720935904
     * to_uid : 1002274307506177
     * uid : 982165555838977
     * id : 1051584743145478
     * op_data : {}
     * op_name : visit
     */

    private ResultBean result;
    /**
     * result : {"created_at":1482720935904,"updated_at":1482720935904,"to_uid":1002274307506177,"uid":982165555838977,"id":1051584743145478,"op_data":{},"op_name":"visit"}
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
        private long created_at;
        private long updated_at;
        private long to_uid;
        private long uid;
        private long id;
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

        public String getOp_name() {
            return op_name;
        }

        public void setOp_name(String op_name) {
            this.op_name = op_name;
        }
    }
}
