package com.yuyoubang.app;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/29.
 */

public class TripIsOrder implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1483005307559,"updated_at":1483005307559,"op_name":"participate","data_id":962108511551511,"uid":1002274307506177,"id":1056355713614007,"op_data":{"trip_biz_name":"【修改】陈鑫12","trip_start_time":1479190828000,"trip_name":"稻城亚丁-丽江-大理-玉龙雪山 畅游8-10天","user_name":"啊啊啊","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":962033467064324,"trip_id":962108511551511,"participate_state":"book","total_price":760,"female_count":1,"male_count":1,"contact_phone":13333333333,"contact_user_name":"大头"},"user":{"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482989278129}}]}
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
         * created_at : 1483005307559
         * updated_at : 1483005307559
         * op_name : participate
         * data_id : 962108511551511
         * uid : 1002274307506177
         * id : 1056355713614007
         * op_data : {"trip_biz_name":"【修改】陈鑫12","trip_start_time":1479190828000,"trip_name":"稻城亚丁-丽江-大理-玉龙雪山 畅游8-10天","user_name":"啊啊啊","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":962033467064324,"trip_id":962108511551511,"participate_state":"book","total_price":760,"female_count":1,"male_count":1,"contact_phone":13333333333,"contact_user_name":"大头"}
         * user : {"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482989278129}
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
            private String op_name;
            private long data_id;
            private long uid;
            private long id;
            /**
             * trip_biz_name : 【修改】陈鑫12
             * trip_start_time : 1479190828000
             * trip_name : 稻城亚丁-丽江-大理-玉龙雪山 畅游8-10天
             * user_name : 啊啊啊
             * sys_cash : 0
             * biz_cash : 0
             * refund_finance_comment :
             * refund_finance_state :
             * refund_system_comment :
             * refund_sys_state :
             * refund_biz_comment :
             * refund_biz_state :
             * refund_start_time :
             * refund_reason :
             * refund_state :
             * trip_biz_uid : 962033467064324
             * trip_id : 962108511551511
             * participate_state : book
             * total_price : 760
             * female_count : 1
             * male_count : 1
             * contact_phone : 13333333333
             * contact_user_name : 大头
             */

            private OpDataBean op_data;
            /**
             * user_data : {"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"}
             * id : 1002274307506177
             * created_at : 1479781804088
             * updated_at : 1482989278129
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

            public String getOp_name() {
                return op_name;
            }

            public void setOp_name(String op_name) {
                this.op_name = op_name;
            }

            public long getData_id() {
                return data_id;
            }

            public void setData_id(long data_id) {
                this.data_id = data_id;
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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class OpDataBean implements Serializable{
                private String trip_biz_name;
                private long trip_start_time;
                private String trip_name;
                private String user_name;
                private int sys_cash;
                private int biz_cash;
                private String refund_finance_comment;
                private String refund_finance_state;
                private String refund_system_comment;
                private String refund_sys_state;
                private String refund_biz_comment;
                private String refund_biz_state;
                private String refund_start_time;
                private String refund_reason;
                private String refund_state;
                private long trip_biz_uid;
                private long trip_id;
                private String participate_state;
                private int total_price;
                private int female_count;
                private int male_count;
                private long contact_phone;
                private String contact_user_name;

                public String getTrip_biz_name() {
                    return trip_biz_name;
                }

                public void setTrip_biz_name(String trip_biz_name) {
                    this.trip_biz_name = trip_biz_name;
                }

                public long getTrip_start_time() {
                    return trip_start_time;
                }

                public void setTrip_start_time(long trip_start_time) {
                    this.trip_start_time = trip_start_time;
                }

                public String getTrip_name() {
                    return trip_name;
                }

                public void setTrip_name(String trip_name) {
                    this.trip_name = trip_name;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public int getSys_cash() {
                    return sys_cash;
                }

                public void setSys_cash(int sys_cash) {
                    this.sys_cash = sys_cash;
                }

                public int getBiz_cash() {
                    return biz_cash;
                }

                public void setBiz_cash(int biz_cash) {
                    this.biz_cash = biz_cash;
                }

                public String getRefund_finance_comment() {
                    return refund_finance_comment;
                }

                public void setRefund_finance_comment(String refund_finance_comment) {
                    this.refund_finance_comment = refund_finance_comment;
                }

                public String getRefund_finance_state() {
                    return refund_finance_state;
                }

                public void setRefund_finance_state(String refund_finance_state) {
                    this.refund_finance_state = refund_finance_state;
                }

                public String getRefund_system_comment() {
                    return refund_system_comment;
                }

                public void setRefund_system_comment(String refund_system_comment) {
                    this.refund_system_comment = refund_system_comment;
                }

                public String getRefund_sys_state() {
                    return refund_sys_state;
                }

                public void setRefund_sys_state(String refund_sys_state) {
                    this.refund_sys_state = refund_sys_state;
                }

                public String getRefund_biz_comment() {
                    return refund_biz_comment;
                }

                public void setRefund_biz_comment(String refund_biz_comment) {
                    this.refund_biz_comment = refund_biz_comment;
                }

                public String getRefund_biz_state() {
                    return refund_biz_state;
                }

                public void setRefund_biz_state(String refund_biz_state) {
                    this.refund_biz_state = refund_biz_state;
                }

                public String getRefund_start_time() {
                    return refund_start_time;
                }

                public void setRefund_start_time(String refund_start_time) {
                    this.refund_start_time = refund_start_time;
                }

                public String getRefund_reason() {
                    return refund_reason;
                }

                public void setRefund_reason(String refund_reason) {
                    this.refund_reason = refund_reason;
                }

                public String getRefund_state() {
                    return refund_state;
                }

                public void setRefund_state(String refund_state) {
                    this.refund_state = refund_state;
                }

                public long getTrip_biz_uid() {
                    return trip_biz_uid;
                }

                public void setTrip_biz_uid(long trip_biz_uid) {
                    this.trip_biz_uid = trip_biz_uid;
                }

                public long getTrip_id() {
                    return trip_id;
                }

                public void setTrip_id(long trip_id) {
                    this.trip_id = trip_id;
                }

                public String getParticipate_state() {
                    return participate_state;
                }

                public void setParticipate_state(String participate_state) {
                    this.participate_state = participate_state;
                }

                public int getTotal_price() {
                    return total_price;
                }

                public void setTotal_price(int total_price) {
                    this.total_price = total_price;
                }

                public int getFemale_count() {
                    return female_count;
                }

                public void setFemale_count(int female_count) {
                    this.female_count = female_count;
                }

                public int getMale_count() {
                    return male_count;
                }

                public void setMale_count(int male_count) {
                    this.male_count = male_count;
                }

                public long getContact_phone() {
                    return contact_phone;
                }

                public void setContact_phone(long contact_phone) {
                    this.contact_phone = contact_phone;
                }

                public String getContact_user_name() {
                    return contact_user_name;
                }

                public void setContact_user_name(String contact_user_name) {
                    this.contact_user_name = contact_user_name;
                }
            }

            public static class UserBean implements Serializable{
                /**
                 * state : 0
                 * birthday : 683650800000
                 * is_verification : 0
                 * user_group : user
                 * user_location : 澳门
                 * gender : 0
                 * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600
                 * user_name : 啊啊啊
                 * user_cover_pic_url : http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600
                 * verification_type : 2
                 * user_desc : 什么鬼
                 * user_tags : ["大叔","个性","萝莉"]
                 * province : 北京
                 */

                private UserDataBean user_data;
                private long id;
                private long created_at;
                private long updated_at;

                public UserDataBean getUser_data() {
                    return user_data;
                }

                public void setUser_data(UserDataBean user_data) {
                    this.user_data = user_data;
                }

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

                public static class UserDataBean implements Serializable{
                    private int state;
                    private long birthday;
                    private int is_verification;
                    private String user_group;
                    private String user_location;
                    private int gender;
                    private String profile_pic_url;
                    private String user_name;
                    private String user_cover_pic_url;
                    private int verification_type;
                    private String user_desc;
                    private String province;
                    private List<String> user_tags;

                    public int getState() {
                        return state;
                    }

                    public void setState(int state) {
                        this.state = state;
                    }

                    public long getBirthday() {
                        return birthday;
                    }

                    public void setBirthday(long birthday) {
                        this.birthday = birthday;
                    }

                    public int getIs_verification() {
                        return is_verification;
                    }

                    public void setIs_verification(int is_verification) {
                        this.is_verification = is_verification;
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

                    public int getGender() {
                        return gender;
                    }

                    public void setGender(int gender) {
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

                    public String getUser_cover_pic_url() {
                        return user_cover_pic_url;
                    }

                    public void setUser_cover_pic_url(String user_cover_pic_url) {
                        this.user_cover_pic_url = user_cover_pic_url;
                    }

                    public int getVerification_type() {
                        return verification_type;
                    }

                    public void setVerification_type(int verification_type) {
                        this.verification_type = verification_type;
                    }

                    public String getUser_desc() {
                        return user_desc;
                    }

                    public void setUser_desc(String user_desc) {
                        this.user_desc = user_desc;
                    }

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
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
}
