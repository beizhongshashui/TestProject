package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/22.
 */
public class CommentBean implements Serializable {

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1479297087682,"updated_at":1479298464732,"to_uid":962033467064324,"uid":980983181541377,"id":994142105698336,"op_data":{"biz_user_reply":"这里是邦主的回复","comment":"陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}},{"created_at":1479297131724,"updated_at":1479298464733,"to_uid":962033467064324,"uid":980983181541377,"id":994142843895841,"op_data":{"biz_user_reply":"这里是邦主的回复","comment":"陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}},{"created_at":1479297140634,"updated_at":1479298464733,"to_uid":962033467064324,"uid":980983181541377,"id":994142994890786,"op_data":{"biz_user_reply":"这里是邦主的回复","comment":"陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}},{"created_at":1479297177305,"updated_at":1479298464733,"to_uid":962033467064324,"uid":980983181541377,"id":994143615647779,"op_data":{"biz_user_reply":"这里是邦主的回复","comment":"陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}},{"created_at":1479297341326,"updated_at":1479298464733,"to_uid":962033467064324,"uid":980983181541377,"id":994146367111204,"op_data":{"biz_user_reply":"这里是邦主的回复","comment":"陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}},{"created_at":1479297562705,"updated_at":1479298464733,"to_uid":962033467064324,"uid":980983181541377,"id":994150074875941,"op_data":{"biz_user_reply":"这里是邦主的回复","comment":"陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}},{"created_at":1479297809269,"updated_at":1479298464734,"to_uid":962033467064324,"uid":980983181541377,"id":994154218848294,"op_data":{"biz_user_reply":"这里是邦主的回复","comment":"陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}},{"created_at":1479297881044,"updated_at":1479298464734,"to_uid":962033467064324,"uid":980983181541377,"id":994155426807847,"op_data":{"biz_user_reply":"这里是邦主的回复","comment":"2016-11-16-20-04|||2016-11-16-20-04","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}},{"created_at":1479298575178,"updated_at":1479298575178,"to_uid":962033467064324,"uid":980983181541377,"id":994167070195752,"op_data":{"biz_user_name":"【修改】陈鑫13","user_name":"小源源","trip_id":988217802293249,"trip_participate_id":988258621259778,"value":3,"comment":"2016-11-16-20-04|||2016-11-16-20-04"},"op_name":"trip_comment","user":{"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}}]}
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
         * created_at : 1479297087682
         * updated_at : 1479298464732
         * to_uid : 962033467064324
         * uid : 980983181541377
         * id : 994142105698336
         * op_data : {"biz_user_reply":"这里是邦主的回复","comment":"陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好","value":3,"trip_participate_id":988258621259778,"trip_id":988217802293249,"user_name":"小源源","biz_user_name":"【修改】陈鑫13"}
         * op_name : trip_comment
         * user : {"id":980983181541377,"created_at":1478512754894,"updated_at":1478763226698,"user_data":{}}
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
             * biz_user_reply : 这里是邦主的回复
             * comment : 陈鑫评价一个活动【962033467064324】的活动【988217802293249】非常好
             * value : 3
             * trip_participate_id : 988258621259778
             * trip_id : 988217802293249
             * user_name : 小源源
             * biz_user_name : 【修改】陈鑫13
             */

            private OpDataBean op_data;
            private String op_name;
            /**
             * id : 980983181541377
             * created_at : 1478512754894
             * updated_at : 1478763226698
             * user_data : {}
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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class OpDataBean implements Serializable{
                private String biz_user_reply;
                private String comment;
                private int value;
                private long trip_participate_id;
                private long trip_id;
                private String user_name;
                private String biz_user_name;

                public String getBiz_user_reply() {
                    return biz_user_reply;
                }

                public void setBiz_user_reply(String biz_user_reply) {
                    this.biz_user_reply = biz_user_reply;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public long getTrip_participate_id() {
                    return trip_participate_id;
                }

                public void setTrip_participate_id(long trip_participate_id) {
                    this.trip_participate_id = trip_participate_id;
                }

                public long getTrip_id() {
                    return trip_id;
                }

                public void setTrip_id(long trip_id) {
                    this.trip_id = trip_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getBiz_user_name() {
                    return biz_user_name;
                }

                public void setBiz_user_name(String biz_user_name) {
                    this.biz_user_name = biz_user_name;
                }
            }

            public static class UserBean implements Serializable{
                private long id;
                private long created_at;
                private long updated_at;
                private UserDataBean user_data;

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

                public UserDataBean getUser_data() {
                    return user_data;
                }

                public void setUser_data(UserDataBean user_data) {
                    this.user_data = user_data;
                }

                public static class UserDataBean {
                }
            }
        }
    }
}
