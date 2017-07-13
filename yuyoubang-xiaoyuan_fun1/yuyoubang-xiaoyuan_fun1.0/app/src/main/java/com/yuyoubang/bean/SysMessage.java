package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/26.
 */

public class SysMessage implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1482478641413,"updated_at":1482478641413,"id":1047519724372037,"data":{"name":"系统消息","message":"欢迎加入遇游邦"},"uid":962029406978050}]}
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
         * created_at : 1482478641413
         * updated_at : 1482478641413
         * id : 1047519724372037
         * data : {"name":"系统消息","message":"欢迎加入遇游邦"}
         * uid : 962029406978050
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
             * name : 系统消息
             * message : 欢迎加入遇游邦
             */

            private DataBean data;
            private long uid;

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

            public static class DataBean implements Serializable{
                private String name;
                private String message;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }
            }
        }
    }
}
