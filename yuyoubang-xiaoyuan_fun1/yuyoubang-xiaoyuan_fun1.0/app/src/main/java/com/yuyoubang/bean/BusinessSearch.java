package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 17/1/3.
 */

public class BusinessSearch implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481628782730,"updated_at":1482486839445,"op_name":"participate","data_id":1033222147342407,"uid":1002274307506177,"id":1033261456359498,"op_data":{"contact_user_name":"陈宏","contact_phone":13333333333,"male_count":1,"female_count":1,"total_price":400,"participate_state":"cancel","trip_id":1033222147342407,"trip_biz_uid":1002274307506177,"refund_state":"","refund_reason":"","refund_start_time":"","refund_biz_state":"","refund_biz_comment":"","refund_sys_state":"","refund_system_comment":"","refund_finance_state":"","refund_finance_comment":"","biz_cash":0,"sys_cash":0,"trip_biz_name":"啊啊啊","trip_name":"斗鱼直播","trip_start_time":1481904000000},"trip":{"data":{"participate_like_total_count":2,"user_create_group_id":1481626442041,"trip_days":4,"biz_user_name":"啊啊啊","hot_index":0,"trip_state":-1,"trip_route_type":"摄影","other_desc":"","trip_equipment_intro":"自带手纸","trip_intro":"斗鱼三骚来袭","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":200,"trip_tags":["旅行","户外","摄影"],"participate_end_time":1481731200000,"end_time":1482163200000,"start_time":1481904000000,"participants_limit_count":200,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1033220805165126?imageView2/1/w/600/h/600","trip_name":"斗鱼直播","recommend_index":"11"},"id":1033222147342407,"created_at":1481626439813,"updated_at":1482486839577}}]}
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
         * created_at : 1481628782730
         * updated_at : 1482486839445
         * op_name : participate
         * data_id : 1033222147342407
         * uid : 1002274307506177
         * id : 1033261456359498
         * op_data : {"contact_user_name":"陈宏","contact_phone":13333333333,"male_count":1,"female_count":1,"total_price":400,"participate_state":"cancel","trip_id":1033222147342407,"trip_biz_uid":1002274307506177,"refund_state":"","refund_reason":"","refund_start_time":"","refund_biz_state":"","refund_biz_comment":"","refund_sys_state":"","refund_system_comment":"","refund_finance_state":"","refund_finance_comment":"","biz_cash":0,"sys_cash":0,"trip_biz_name":"啊啊啊","trip_name":"斗鱼直播","trip_start_time":1481904000000}
         * trip : {"data":{"participate_like_total_count":2,"user_create_group_id":1481626442041,"trip_days":4,"biz_user_name":"啊啊啊","hot_index":0,"trip_state":-1,"trip_route_type":"摄影","other_desc":"","trip_equipment_intro":"自带手纸","trip_intro":"斗鱼三骚来袭","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":200,"trip_tags":["旅行","户外","摄影"],"participate_end_time":1481731200000,"end_time":1482163200000,"start_time":1481904000000,"participants_limit_count":200,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1033220805165126?imageView2/1/w/600/h/600","trip_name":"斗鱼直播","recommend_index":"11"},"id":1033222147342407,"created_at":1481626439813,"updated_at":1482486839577}
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
             * contact_user_name : 陈宏
             * contact_phone : 13333333333
             * male_count : 1
             * female_count : 1
             * total_price : 400
             * participate_state : cancel
             * trip_id : 1033222147342407
             * trip_biz_uid : 1002274307506177
             * refund_state :
             * refund_reason :
             * refund_start_time :
             * refund_biz_state :
             * refund_biz_comment :
             * refund_sys_state :
             * refund_system_comment :
             * refund_finance_state :
             * refund_finance_comment :
             * biz_cash : 0
             * sys_cash : 0
             * trip_biz_name : 啊啊啊
             * trip_name : 斗鱼直播
             * trip_start_time : 1481904000000
             */

            private OpDataBean op_data;
            /**
             * data : {"participate_like_total_count":2,"user_create_group_id":1481626442041,"trip_days":4,"biz_user_name":"啊啊啊","hot_index":0,"trip_state":-1,"trip_route_type":"摄影","other_desc":"","trip_equipment_intro":"自带手纸","trip_intro":"斗鱼三骚来袭","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":200,"trip_tags":["旅行","户外","摄影"],"participate_end_time":1481731200000,"end_time":1482163200000,"start_time":1481904000000,"participants_limit_count":200,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1033220805165126?imageView2/1/w/600/h/600","trip_name":"斗鱼直播","recommend_index":"11"}
             * id : 1033222147342407
             * created_at : 1481626439813
             * updated_at : 1482486839577
             */

            private TripBean trip;

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

            public TripBean getTrip() {
                return trip;
            }

            public void setTrip(TripBean trip) {
                this.trip = trip;
            }

            public static class OpDataBean implements Serializable{
                private String contact_user_name;
                private long contact_phone;
                private int male_count;
                private int female_count;
                private int total_price;
                private String participate_state;
                private long trip_id;
                private long trip_biz_uid;
                private String refund_state;
                private String refund_reason;
                private String refund_start_time;
                private String refund_biz_state;
                private String refund_biz_comment;
                private String refund_sys_state;
                private String refund_system_comment;
                private String refund_finance_state;
                private String refund_finance_comment;
                private String biz_cash;
                private String sys_cash;
                private String trip_biz_name;
                private String trip_name;
                private long trip_start_time;

                public String getContact_user_name() {
                    return contact_user_name;
                }

                public void setContact_user_name(String contact_user_name) {
                    this.contact_user_name = contact_user_name;
                }

                public long getContact_phone() {
                    return contact_phone;
                }

                public void setContact_phone(long contact_phone) {
                    this.contact_phone = contact_phone;
                }

                public int getMale_count() {
                    return male_count;
                }

                public void setMale_count(int male_count) {
                    this.male_count = male_count;
                }

                public int getFemale_count() {
                    return female_count;
                }

                public void setFemale_count(int female_count) {
                    this.female_count = female_count;
                }

                public int getTotal_price() {
                    return total_price;
                }

                public void setTotal_price(int total_price) {
                    this.total_price = total_price;
                }

                public String getParticipate_state() {
                    return participate_state;
                }

                public void setParticipate_state(String participate_state) {
                    this.participate_state = participate_state;
                }

                public long getTrip_id() {
                    return trip_id;
                }

                public void setTrip_id(long trip_id) {
                    this.trip_id = trip_id;
                }

                public long getTrip_biz_uid() {
                    return trip_biz_uid;
                }

                public void setTrip_biz_uid(long trip_biz_uid) {
                    this.trip_biz_uid = trip_biz_uid;
                }

                public String getRefund_state() {
                    return refund_state;
                }

                public void setRefund_state(String refund_state) {
                    this.refund_state = refund_state;
                }

                public String getRefund_reason() {
                    return refund_reason;
                }

                public void setRefund_reason(String refund_reason) {
                    this.refund_reason = refund_reason;
                }

                public String getRefund_start_time() {
                    return refund_start_time;
                }

                public void setRefund_start_time(String refund_start_time) {
                    this.refund_start_time = refund_start_time;
                }

                public String getRefund_biz_state() {
                    return refund_biz_state;
                }

                public void setRefund_biz_state(String refund_biz_state) {
                    this.refund_biz_state = refund_biz_state;
                }

                public String getRefund_biz_comment() {
                    return refund_biz_comment;
                }

                public void setRefund_biz_comment(String refund_biz_comment) {
                    this.refund_biz_comment = refund_biz_comment;
                }

                public String getRefund_sys_state() {
                    return refund_sys_state;
                }

                public void setRefund_sys_state(String refund_sys_state) {
                    this.refund_sys_state = refund_sys_state;
                }

                public String getRefund_system_comment() {
                    return refund_system_comment;
                }

                public void setRefund_system_comment(String refund_system_comment) {
                    this.refund_system_comment = refund_system_comment;
                }

                public String getRefund_finance_state() {
                    return refund_finance_state;
                }

                public void setRefund_finance_state(String refund_finance_state) {
                    this.refund_finance_state = refund_finance_state;
                }

                public String getRefund_finance_comment() {
                    return refund_finance_comment;
                }

                public void setRefund_finance_comment(String refund_finance_comment) {
                    this.refund_finance_comment = refund_finance_comment;
                }

                public String getBiz_cash() {
                    return biz_cash;
                }

                public void setBiz_cash(String biz_cash) {
                    this.biz_cash = biz_cash;
                }

                public String getSys_cash() {
                    return sys_cash;
                }

                public void setSys_cash(String sys_cash) {
                    this.sys_cash = sys_cash;
                }

                public String getTrip_biz_name() {
                    return trip_biz_name;
                }

                public void setTrip_biz_name(String trip_biz_name) {
                    this.trip_biz_name = trip_biz_name;
                }

                public String getTrip_name() {
                    return trip_name;
                }

                public void setTrip_name(String trip_name) {
                    this.trip_name = trip_name;
                }

                public long getTrip_start_time() {
                    return trip_start_time;
                }

                public void setTrip_start_time(long trip_start_time) {
                    this.trip_start_time = trip_start_time;
                }
            }

            public static class TripBean implements Serializable{
                /**
                 * participate_like_total_count : 2
                 * user_create_group_id : 1481626442041
                 * trip_days : 4
                 * biz_user_name : 啊啊啊
                 * hot_index : 0
                 * trip_state : -1
                 * trip_route_type : 摄影
                 * other_desc :
                 * trip_equipment_intro : 自带手纸
                 * trip_intro : 斗鱼三骚来袭
                 * meeting_province : 北京
                 * meeting_city : 北京市朝阳区
                 * meeting_place : 北京
                 * participants_female_count : 0
                 * participants_male_count : 0
                 * trip_price : 200
                 * trip_tags : ["旅行","户外","摄影"]
                 * participate_end_time : 1481731200000
                 * end_time : 1482163200000
                 * start_time : 1481904000000
                 * participants_limit_count : 200
                 * trip_cover_pic : http://ofdx4t772.bkt.clouddn.com/1033220805165126?imageView2/1/w/600/h/600
                 * trip_name : 斗鱼直播
                 * recommend_index : 11
                 */

                private DataBean data;
                private long id;
                private long created_at;
                private long updated_at;

                public DataBean getData() {
                    return data;
                }

                public void setData(DataBean data) {
                    this.data = data;
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

                public static class DataBean implements Serializable{
                    private int participate_like_total_count;
                    private long user_create_group_id;
                    private int trip_days;
                    private String biz_user_name;
                    private int hot_index;
                    private int trip_state;
                    private String trip_route_type;
                    private String other_desc;
                    private String trip_equipment_intro;
                    private String trip_intro;
                    private String meeting_province;
                    private String meeting_city;
                    private String meeting_place;
                    private int participants_female_count;
                    private int participants_male_count;
                    private int trip_price;
                    private long participate_end_time;
                    private long end_time;
                    private long start_time;
                    private int participants_limit_count;
                    private String trip_cover_pic;
                    private String trip_name;
                    private String recommend_index;
                    private List<String> trip_tags;

                    public int getParticipate_like_total_count() {
                        return participate_like_total_count;
                    }

                    public void setParticipate_like_total_count(int participate_like_total_count) {
                        this.participate_like_total_count = participate_like_total_count;
                    }

                    public long getUser_create_group_id() {
                        return user_create_group_id;
                    }

                    public void setUser_create_group_id(long user_create_group_id) {
                        this.user_create_group_id = user_create_group_id;
                    }

                    public int getTrip_days() {
                        return trip_days;
                    }

                    public void setTrip_days(int trip_days) {
                        this.trip_days = trip_days;
                    }

                    public String getBiz_user_name() {
                        return biz_user_name;
                    }

                    public void setBiz_user_name(String biz_user_name) {
                        this.biz_user_name = biz_user_name;
                    }

                    public int getHot_index() {
                        return hot_index;
                    }

                    public void setHot_index(int hot_index) {
                        this.hot_index = hot_index;
                    }

                    public int getTrip_state() {
                        return trip_state;
                    }

                    public void setTrip_state(int trip_state) {
                        this.trip_state = trip_state;
                    }

                    public String getTrip_route_type() {
                        return trip_route_type;
                    }

                    public void setTrip_route_type(String trip_route_type) {
                        this.trip_route_type = trip_route_type;
                    }

                    public String getOther_desc() {
                        return other_desc;
                    }

                    public void setOther_desc(String other_desc) {
                        this.other_desc = other_desc;
                    }

                    public String getTrip_equipment_intro() {
                        return trip_equipment_intro;
                    }

                    public void setTrip_equipment_intro(String trip_equipment_intro) {
                        this.trip_equipment_intro = trip_equipment_intro;
                    }

                    public String getTrip_intro() {
                        return trip_intro;
                    }

                    public void setTrip_intro(String trip_intro) {
                        this.trip_intro = trip_intro;
                    }

                    public String getMeeting_province() {
                        return meeting_province;
                    }

                    public void setMeeting_province(String meeting_province) {
                        this.meeting_province = meeting_province;
                    }

                    public String getMeeting_city() {
                        return meeting_city;
                    }

                    public void setMeeting_city(String meeting_city) {
                        this.meeting_city = meeting_city;
                    }

                    public String getMeeting_place() {
                        return meeting_place;
                    }

                    public void setMeeting_place(String meeting_place) {
                        this.meeting_place = meeting_place;
                    }

                    public int getParticipants_female_count() {
                        return participants_female_count;
                    }

                    public void setParticipants_female_count(int participants_female_count) {
                        this.participants_female_count = participants_female_count;
                    }

                    public int getParticipants_male_count() {
                        return participants_male_count;
                    }

                    public void setParticipants_male_count(int participants_male_count) {
                        this.participants_male_count = participants_male_count;
                    }

                    public int getTrip_price() {
                        return trip_price;
                    }

                    public void setTrip_price(int trip_price) {
                        this.trip_price = trip_price;
                    }

                    public long getParticipate_end_time() {
                        return participate_end_time;
                    }

                    public void setParticipate_end_time(long participate_end_time) {
                        this.participate_end_time = participate_end_time;
                    }

                    public long getEnd_time() {
                        return end_time;
                    }

                    public void setEnd_time(long end_time) {
                        this.end_time = end_time;
                    }

                    public long getStart_time() {
                        return start_time;
                    }

                    public void setStart_time(long start_time) {
                        this.start_time = start_time;
                    }

                    public int getParticipants_limit_count() {
                        return participants_limit_count;
                    }

                    public void setParticipants_limit_count(int participants_limit_count) {
                        this.participants_limit_count = participants_limit_count;
                    }

                    public String getTrip_cover_pic() {
                        return trip_cover_pic;
                    }

                    public void setTrip_cover_pic(String trip_cover_pic) {
                        this.trip_cover_pic = trip_cover_pic;
                    }

                    public String getTrip_name() {
                        return trip_name;
                    }

                    public void setTrip_name(String trip_name) {
                        this.trip_name = trip_name;
                    }

                    public String getRecommend_index() {
                        return recommend_index;
                    }

                    public void setRecommend_index(String recommend_index) {
                        this.recommend_index = recommend_index;
                    }

                    public List<String> getTrip_tags() {
                        return trip_tags;
                    }

                    public void setTrip_tags(List<String> trip_tags) {
                        this.trip_tags = trip_tags;
                    }
                }
            }
        }
    }
}
