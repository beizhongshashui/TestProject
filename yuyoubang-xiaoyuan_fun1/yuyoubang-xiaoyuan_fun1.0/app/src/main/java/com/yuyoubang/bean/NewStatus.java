package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public class NewStatus implements Serializable {


    /**
     * created_at : 1479730394339
     * updated_at : 1479730394339
     * id : 1001411790831677
     * data : {"user_name":"小源源","user_location":"北京","location":"英国","pics":["http: //img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"content":"今天是一个美好的一天"}
     * uid : 980983181541377
     */

    private ResultBean result;
    /**
     * result : {"created_at":1479730394339,"updated_at":1479730394339,"id":1001411790831677,"data":{"user_name":"小源源","user_location":"北京","location":"英国","pics":["http: //img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"content":"今天是一个美好的一天"},"uid":980983181541377}
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

    public static class ResultBean {
        private long created_at;
        private long updated_at;
        private long id;
        /**
         * user_name : 小源源
         * user_location : 北京
         * location : 英国
         * pics : ["http: //img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"]
         * content : 今天是一个美好的一天
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

        public static class DataBean {
            private String user_name;
            private String user_location;
            private String location;
            private String content;
            private List<String> pics;

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
    }
}
