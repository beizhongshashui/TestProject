package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by hongchen on 16/12/26.
 */

public class ApplyGroup implements Serializable{

    /**
     * created_at : 1482743592386
     * updated_at : 1482743592386
     * to_uid : 976528209936404
     * uid : 982165555838977
     * id : 1051964864528578
     * op_data : {"group_pic":"http://ofdx4t772.bkt.clouddn.com/1051961911738550?imageView2","group_name":"锋在吹、测试生日、陈鑫陈鑫","type":1,"state":0,"group_id":1482743417262}
     * op_name : apply_to_group
     */

    private ResultBean result;
    /**
     * result : {"created_at":1482743592386,"updated_at":1482743592386,"to_uid":976528209936404,"uid":982165555838977,"id":1051964864528578,"op_data":{"group_pic":"http://ofdx4t772.bkt.clouddn.com/1051961911738550?imageView2","group_name":"锋在吹、测试生日、陈鑫陈鑫","type":1,"state":0,"group_id":1482743417262},"op_name":"apply_to_group"}
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
        /**
         * group_pic : http://ofdx4t772.bkt.clouddn.com/1051961911738550?imageView2
         * group_name : 锋在吹、测试生日、陈鑫陈鑫
         * type : 1
         * state : 0
         * group_id : 1482743417262
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
            private String group_pic;
            private String group_name;
            private int type;
            private int state;
            private long group_id;

            public String getGroup_pic() {
                return group_pic;
            }

            public void setGroup_pic(String group_pic) {
                this.group_pic = group_pic;
            }

            public String getGroup_name() {
                return group_name;
            }

            public void setGroup_name(String group_name) {
                this.group_name = group_name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public long getGroup_id() {
                return group_id;
            }

            public void setGroup_id(long group_id) {
                this.group_id = group_id;
            }
        }
    }
}
