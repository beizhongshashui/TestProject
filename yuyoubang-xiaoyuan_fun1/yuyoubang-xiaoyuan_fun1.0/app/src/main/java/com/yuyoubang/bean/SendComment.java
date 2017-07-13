package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/11/22.
 */

public class SendComment implements Serializable{

    /**
     * result : [{"created_at":1479732116818,"updated_at":1479809483603,"id":1001440681197630,"data":{"like_comment_total_count":2,"content":"今天的心情不错","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"location":"英国","user_location":"北京","user_name":"小源源"},"uid":980983181541377}]
     * error_code : 0
     */

    private int error_code;
    /**
     * created_at : 1479732116818
     * updated_at : 1479809483603
     * id : 1001440681197630
     * data : {"like_comment_total_count":2,"content":"今天的心情不错","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"location":"英国","user_location":"北京","user_name":"小源源"}
     * uid : 980983181541377
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
        private long id;
        /**
         * like_comment_total_count : 2
         * content : 今天的心情不错
         * pics : ["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"]
         * location : 英国
         * user_location : 北京
         * user_name : 小源源
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
            private int like_comment_total_count;
            private String content;
            private String location;
            private String user_location;
            private String user_name;
            private List<String> pics;

            public int getLike_comment_total_count() {
                return like_comment_total_count;
            }

            public void setLike_comment_total_count(int like_comment_total_count) {
                this.like_comment_total_count = like_comment_total_count;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getUser_location() {
                return user_location;
            }

            public void setUser_location(String user_location) {
                this.user_location = user_location;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
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
