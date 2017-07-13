package com.yuyoubang.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/1.
 */

public class MineDoTan implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1480582629884,"updated_at":1480582629884,"id":1015709921509386,"data":{"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"content":"什么鬼"},"uid":1002274307506177},{"created_at":1479891913649,"updated_at":1480498716193,"id":1004121629982735,"data":{"like_comment_total_count":12,"content":"没意思","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"location":"英国","user_location":"北京","user_name":"啊啊啊"},"uid":1002274307506177}]}
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
         * created_at : 1480582629884
         * updated_at : 1480582629884
         * id : 1015709921509386
         * data : {"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"content":"什么鬼"}
         * uid : 1002274307506177
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
             * user_name : 啊啊啊
             * user_location : 北京
             * location : 英国
             * pics : ["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"]
             * content : 什么鬼
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
                private String user_name;
                private String user_location;
                private String location;
                private String content;
                private String status_comment_count;
                private String status_like_count;
                private int like_comment_total_count;
                private List<String> pics;

                public String getStatus_comment_count() {
                    return status_comment_count;
                }

                public void setStatus_comment_count(String status_comment_count) {
                    this.status_comment_count = status_comment_count;
                }

                public String getStatus_like_count() {
                    return status_like_count;
                }

                public void setStatus_like_count(String status_like_count) {
                    this.status_like_count = status_like_count;
                }

                public int getLike_comment_total_count() {
                    return like_comment_total_count;
                }

                public void setLike_comment_total_count(int like_comment_total_count) {
                    this.like_comment_total_count = like_comment_total_count;
                }

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
}
