package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/12/2.
 */
public class BizBean implements Serializable {


    /**
     * waiting_online_trip_count : 12
     * already_online_trip_count : 2
     * trip_comment_not_reply_count : 11
     * not_paied_participate_count : 0
     * paied_participate_count : 3
     * refund_participate_count : 0
     * cancelled_participate_count : 0
     * finished_participate_count : 0
     * not_paied_not_confirmed_participate_count : 8
     * not_paied_confirmed_participate_count : 1
     * biz_cash_value : {"created_at":1478590089276,"updated_at":1479289858234,"account_name":"biz_cash_account","uid":962033467064324,"id":982280647540738,"value":7896.399999999998}
     * user : [{"created_at":1477383263576,"updated_at":1479697836107,"id":962033467064324,"user_data":{"user_cover_pic_url":"www.baidu.com","user_tags":["阳光、帅气、洒脱"],"is_verification":0,"birthday":"1478670000","verification_type":"2","user_group":"user","user_location":"【修改】北京","user_desc":"【修改】描述","gender":"1","profile_pic_url":"www.baidu.com","user_name":"【修改】陈鑫12","state":0}}]
     */

    private ResultBean result;
    /**
     * result : {"waiting_online_trip_count":12,"already_online_trip_count":2,"trip_comment_not_reply_count":11,"not_paied_participate_count":0,"paied_participate_count":3,"refund_participate_count":0,"cancelled_participate_count":0,"finished_participate_count":0,"not_paied_not_confirmed_participate_count":8,"not_paied_confirmed_participate_count":1,"biz_cash_value":{"created_at":1478590089276,"updated_at":1479289858234,"account_name":"biz_cash_account","uid":962033467064324,"id":982280647540738,"value":7896.399999999998},"user":[{"created_at":1477383263576,"updated_at":1479697836107,"id":962033467064324,"user_data":{"user_cover_pic_url":"www.baidu.com","user_tags":["阳光、帅气、洒脱"],"is_verification":0,"birthday":"1478670000","verification_type":"2","user_group":"user","user_location":"【修改】北京","user_desc":"【修改】描述","gender":"1","profile_pic_url":"www.baidu.com","user_name":"【修改】陈鑫12","state":0}}]}
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

    public static class ResultBean implements Serializable {
        private int waiting_online_trip_count;
        private int already_online_trip_count;
        private int trip_comment_not_reply_count;
        private int not_paied_participate_count;
        private int paied_participate_count;
        private int refund_participate_count;
        private int cancelled_participate_count;
        private int finished_participate_count;
        private int not_paied_not_confirmed_participate_count;
        private int not_paied_confirmed_participate_count;
        /**
         * created_at : 1478590089276
         * updated_at : 1479289858234
         * account_name : biz_cash_account
         * uid : 962033467064324
         * id : 982280647540738
         * value : 7896.399999999998
         */

        private BizCashValueBean biz_cash_value;
        /**
         * created_at : 1477383263576
         * updated_at : 1479697836107
         * id : 962033467064324
         * user_data : {"user_cover_pic_url":"www.baidu.com","user_tags":["阳光、帅气、洒脱"],"is_verification":0,"birthday":"1478670000","verification_type":"2","user_group":"user","user_location":"【修改】北京","user_desc":"【修改】描述","gender":"1","profile_pic_url":"www.baidu.com","user_name":"【修改】陈鑫12","state":0}
         */

        private List<UserBean> user;

        public int getWaiting_online_trip_count() {
            return waiting_online_trip_count;
        }

        public void setWaiting_online_trip_count(int waiting_online_trip_count) {
            this.waiting_online_trip_count = waiting_online_trip_count;
        }

        public int getAlready_online_trip_count() {
            return already_online_trip_count;
        }

        public void setAlready_online_trip_count(int already_online_trip_count) {
            this.already_online_trip_count = already_online_trip_count;
        }

        public int getTrip_comment_not_reply_count() {
            return trip_comment_not_reply_count;
        }

        public void setTrip_comment_not_reply_count(int trip_comment_not_reply_count) {
            this.trip_comment_not_reply_count = trip_comment_not_reply_count;
        }

        public int getNot_paied_participate_count() {
            return not_paied_participate_count;
        }

        public void setNot_paied_participate_count(int not_paied_participate_count) {
            this.not_paied_participate_count = not_paied_participate_count;
        }

        public int getPaied_participate_count() {
            return paied_participate_count;
        }

        public void setPaied_participate_count(int paied_participate_count) {
            this.paied_participate_count = paied_participate_count;
        }

        public int getRefund_participate_count() {
            return refund_participate_count;
        }

        public void setRefund_participate_count(int refund_participate_count) {
            this.refund_participate_count = refund_participate_count;
        }

        public int getCancelled_participate_count() {
            return cancelled_participate_count;
        }

        public void setCancelled_participate_count(int cancelled_participate_count) {
            this.cancelled_participate_count = cancelled_participate_count;
        }

        public int getFinished_participate_count() {
            return finished_participate_count;
        }

        public void setFinished_participate_count(int finished_participate_count) {
            this.finished_participate_count = finished_participate_count;
        }

        public int getNot_paied_not_confirmed_participate_count() {
            return not_paied_not_confirmed_participate_count;
        }

        public void setNot_paied_not_confirmed_participate_count(int not_paied_not_confirmed_participate_count) {
            this.not_paied_not_confirmed_participate_count = not_paied_not_confirmed_participate_count;
        }

        public int getNot_paied_confirmed_participate_count() {
            return not_paied_confirmed_participate_count;
        }

        public void setNot_paied_confirmed_participate_count(int not_paied_confirmed_participate_count) {
            this.not_paied_confirmed_participate_count = not_paied_confirmed_participate_count;
        }

        public BizCashValueBean getBiz_cash_value() {
            return biz_cash_value;
        }

        public void setBiz_cash_value(BizCashValueBean biz_cash_value) {
            this.biz_cash_value = biz_cash_value;
        }

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public static class BizCashValueBean implements Serializable {
            private long created_at;
            private long updated_at;
            private String account_name;
            private long uid;
            private long id;
            private double value;

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

            public String getAccount_name() {
                return account_name;
            }

            public void setAccount_name(String account_name) {
                this.account_name = account_name;
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

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }

        public static class UserBean implements Serializable{
            private long created_at;
            private long updated_at;
            private long id;
            /**
             * user_cover_pic_url : www.baidu.com
             * user_tags : ["阳光、帅气、洒脱"]
             * is_verification : 0
             * birthday : 1478670000
             * verification_type : 2
             * user_group : user
             * user_location : 【修改】北京
             * user_desc : 【修改】描述
             * gender : 1
             * profile_pic_url : www.baidu.com
             * user_name : 【修改】陈鑫12
             * state : 0
             */

            private UserDataBean user_data;

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

            public UserDataBean getUser_data() {
                return user_data;
            }

            public void setUser_data(UserDataBean user_data) {
                this.user_data = user_data;
            }

            public static class UserDataBean implements Serializable {
                private String user_cover_pic_url;
                private int is_verification;
                private String birthday;
                private String verification_type;
                private String user_group;
                private String user_location;
                private String user_desc;
                private String gender;
                private String profile_pic_url;
                private String user_name;
                private int state;
                private List<String> user_tags;

                public String getUser_cover_pic_url() {
                    return user_cover_pic_url;
                }

                public void setUser_cover_pic_url(String user_cover_pic_url) {
                    this.user_cover_pic_url = user_cover_pic_url;
                }

                public int getIs_verification() {
                    return is_verification;
                }

                public void setIs_verification(int is_verification) {
                    this.is_verification = is_verification;
                }

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public String getVerification_type() {
                    return verification_type;
                }

                public void setVerification_type(String verification_type) {
                    this.verification_type = verification_type;
                }

                public String getUser_group() {
                    return user_group;
                }

                public void setUser_group(String user_group) {
                    this.user_group = user_group;
                }

                public String getUser_location() {
                    return user_location;
                }

                public void setUser_location(String user_location) {
                    this.user_location = user_location;
                }

                public String getUser_desc() {
                    return user_desc;
                }

                public void setUser_desc(String user_desc) {
                    this.user_desc = user_desc;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public String getProfile_pic_url() {
                    return profile_pic_url;
                }

                public void setProfile_pic_url(String profile_pic_url) {
                    this.profile_pic_url = profile_pic_url;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public List<String> getUser_tags() {
                    return user_tags;
                }

                public void setUser_tags(List<String> user_tags) {
                    this.user_tags = user_tags;
                }
            }
        }
    }
}
