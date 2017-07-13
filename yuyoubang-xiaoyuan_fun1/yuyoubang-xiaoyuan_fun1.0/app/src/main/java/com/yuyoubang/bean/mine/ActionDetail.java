package com.yuyoubang.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/13.
 */

public class ActionDetail implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481695758152,"updated_at":1481695758152,"op_name":"participate","data_id":1034379422924806,"uid":982165555838977,"id":1034385127178247,"op_data":{"trip_biz_name":"啊啊啊","trip_start_time":1482163200000,"trip_name":"相约98","user_name":"锋在吹","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":"1002274307506177","trip_id":"1034379422924806","participate_state":"book","total_price":"1200","female_count":"0","male_count":"6","contact_phone":"13520232775","contact_user_name":"雷勇锋"},"trip":{"data":{"recommend_index":"4","user_create_group_id":1481695418296,"trip_days":6,"biz_user_name":"啊啊啊","hot_index":0,"trip_state":2,"trip_route_type":"休闲","other_desc":"","trip_equipment_intro":"开大趴","trip_intro":"相约98","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":200,"trip_tags":["旅行","户外","摄影"],"participate_end_time":1482076800000,"end_time":1482595200000,"start_time":1482163200000,"participants_limit_count":200,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1034377711648773?imageView2/1/w/600/h/600","trip_name":"相约98"},"id":1034379422924806,"created_at":1481695418768,"updated_at":1481967284602},"user":{"user_data":{"verification_type":2,"recommend_by_sys":0,"state":0,"birthday":"721497600000","is_verification":0,"user_group":"user","gender":"0","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2","user_name":"锋在吹","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2"},"id":982165555838977,"created_at":1478583229087,"updated_at":1482374348676}}],"trip":[{"created_at":1481695418768,"updated_at":1481967284602,"id":1034379422924806,"data":{"recommend_index":"4","user_create_group_id":1481695418296,"trip_days":6,"biz_user_name":"啊啊啊","hot_index":0,"trip_state":2,"trip_route_type":"休闲","other_desc":"","trip_equipment_intro":"开大趴","trip_intro":"相约98","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":200,"trip_tags":["旅行","户外","摄影"],"participate_end_time":1482076800000,"end_time":1482595200000,"start_time":1482163200000,"participants_limit_count":200,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1034377711648773?imageView2/1/w/600/h/600","trip_name":"相约98","a_trip_male_confirmed_total_count":0,"a_trip_female_confirmed_total_count":0,"a_trip_male_particpate_and_payed_total_count":0,"a_trip_female_particpate_and_payed_total_count":0},"uid":1002274307506177,"user":{"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482032609497}}]}
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
         * created_at : 1481695758152
         * updated_at : 1481695758152
         * op_name : participate
         * data_id : 1034379422924806
         * uid : 982165555838977
         * id : 1034385127178247
         * op_data : {"trip_biz_name":"啊啊啊","trip_start_time":1482163200000,"trip_name":"相约98","user_name":"锋在吹","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":"1002274307506177","trip_id":"1034379422924806","participate_state":"book","total_price":"1200","female_count":"0","male_count":"6","contact_phone":"13520232775","contact_user_name":"雷勇锋"}
         * trip : {"data":{"recommend_index":"4","user_create_group_id":1481695418296,"trip_days":6,"biz_user_name":"啊啊啊","hot_index":0,"trip_state":2,"trip_route_type":"休闲","other_desc":"","trip_equipment_intro":"开大趴","trip_intro":"相约98","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":200,"trip_tags":["旅行","户外","摄影"],"participate_end_time":1482076800000,"end_time":1482595200000,"start_time":1482163200000,"participants_limit_count":200,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1034377711648773?imageView2/1/w/600/h/600","trip_name":"相约98"},"id":1034379422924806,"created_at":1481695418768,"updated_at":1481967284602}
         * user : {"user_data":{"verification_type":2,"recommend_by_sys":0,"state":0,"birthday":"721497600000","is_verification":0,"user_group":"user","gender":"0","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2","user_name":"锋在吹","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2"},"id":982165555838977,"created_at":1478583229087,"updated_at":1482374348676}
         */

        private List<ListBean> list;
        /**
         * created_at : 1481695418768
         * updated_at : 1481967284602
         * id : 1034379422924806
         * data : {"recommend_index":"4","user_create_group_id":1481695418296,"trip_days":6,"biz_user_name":"啊啊啊","hot_index":0,"trip_state":2,"trip_route_type":"休闲","other_desc":"","trip_equipment_intro":"开大趴","trip_intro":"相约98","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":200,"trip_tags":["旅行","户外","摄影"],"participate_end_time":1482076800000,"end_time":1482595200000,"start_time":1482163200000,"participants_limit_count":200,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1034377711648773?imageView2/1/w/600/h/600","trip_name":"相约98","a_trip_male_confirmed_total_count":0,"a_trip_female_confirmed_total_count":0,"a_trip_male_particpate_and_payed_total_count":0,"a_trip_female_particpate_and_payed_total_count":0}
         * uid : 1002274307506177
         * user : {"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482032609497}
         */

        private List<TripBean> trip;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<TripBean> getTrip() {
            return trip;
        }

        public void setTrip(List<TripBean> trip) {
            this.trip = trip;
        }

        public static class ListBean implements Serializable{
            private long created_at;
            private long updated_at;
            private String op_name;
            private long data_id;
            private long uid;
            private long id;
            /**
             * trip_biz_name : 啊啊啊
             * trip_start_time : 1482163200000
             * trip_name : 相约98
             * user_name : 锋在吹
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
             * trip_biz_uid : 1002274307506177
             * trip_id : 1034379422924806
             * participate_state : book
             * total_price : 1200
             * female_count : 0
             * male_count : 6
             * contact_phone : 13520232775
             * contact_user_name : 雷勇锋
             */

            private OpDataBean op_data;
            /**
             * data : {"recommend_index":"4","user_create_group_id":1481695418296,"trip_days":6,"biz_user_name":"啊啊啊","hot_index":0,"trip_state":2,"trip_route_type":"休闲","other_desc":"","trip_equipment_intro":"开大趴","trip_intro":"相约98","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":200,"trip_tags":["旅行","户外","摄影"],"participate_end_time":1482076800000,"end_time":1482595200000,"start_time":1482163200000,"participants_limit_count":200,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1034377711648773?imageView2/1/w/600/h/600","trip_name":"相约98"}
             * id : 1034379422924806
             * created_at : 1481695418768
             * updated_at : 1481967284602
             */

            private TripBean trip;
            /**
             * user_data : {"verification_type":2,"recommend_by_sys":0,"state":0,"birthday":"721497600000","is_verification":0,"user_group":"user","gender":"0","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2","user_name":"锋在吹","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2"}
             * id : 982165555838977
             * created_at : 1478583229087
             * updated_at : 1482374348676
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

            public TripBean getTrip() {
                return trip;
            }

            public void setTrip(TripBean trip) {
                this.trip = trip;
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
                private String trip_biz_uid;
                private String trip_id;
                private String participate_state;
                private String total_price;
                private String female_count;
                private String male_count;
                private String contact_phone;
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

                public String getTrip_biz_uid() {
                    return trip_biz_uid;
                }

                public void setTrip_biz_uid(String trip_biz_uid) {
                    this.trip_biz_uid = trip_biz_uid;
                }

                public String getTrip_id() {
                    return trip_id;
                }

                public void setTrip_id(String trip_id) {
                    this.trip_id = trip_id;
                }

                public String getParticipate_state() {
                    return participate_state;
                }

                public void setParticipate_state(String participate_state) {
                    this.participate_state = participate_state;
                }

                public String getTotal_price() {
                    return total_price;
                }

                public void setTotal_price(String total_price) {
                    this.total_price = total_price;
                }

                public String getFemale_count() {
                    return female_count;
                }

                public void setFemale_count(String female_count) {
                    this.female_count = female_count;
                }

                public String getMale_count() {
                    return male_count;
                }

                public void setMale_count(String male_count) {
                    this.male_count = male_count;
                }

                public String getContact_phone() {
                    return contact_phone;
                }

                public void setContact_phone(String contact_phone) {
                    this.contact_phone = contact_phone;
                }

                public String getContact_user_name() {
                    return contact_user_name;
                }

                public void setContact_user_name(String contact_user_name) {
                    this.contact_user_name = contact_user_name;
                }
            }

            public static class TripBean implements Serializable{
                /**
                 * recommend_index : 4
                 * user_create_group_id : 1481695418296
                 * trip_days : 6
                 * biz_user_name : 啊啊啊
                 * hot_index : 0
                 * trip_state : 2
                 * trip_route_type : 休闲
                 * other_desc :
                 * trip_equipment_intro : 开大趴
                 * trip_intro : 相约98
                 * meeting_province : 北京
                 * meeting_city : 北京市朝阳区
                 * meeting_place : 北京
                 * participants_female_count : 0
                 * participants_male_count : 0
                 * trip_price : 200
                 * trip_tags : ["旅行","户外","摄影"]
                 * participate_end_time : 1482076800000
                 * end_time : 1482595200000
                 * start_time : 1482163200000
                 * participants_limit_count : 200
                 * trip_cover_pic : http://ofdx4t772.bkt.clouddn.com/1034377711648773?imageView2/1/w/600/h/600
                 * trip_name : 相约98
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
                    private String recommend_index;
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
                    private List<String> trip_tags;

                    public String getRecommend_index() {
                        return recommend_index;
                    }

                    public void setRecommend_index(String recommend_index) {
                        this.recommend_index = recommend_index;
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

                    public List<String> getTrip_tags() {
                        return trip_tags;
                    }

                    public void setTrip_tags(List<String> trip_tags) {
                        this.trip_tags = trip_tags;
                    }
                }
            }

            public static class UserBean implements Serializable{
                /**
                 * verification_type : 2
                 * recommend_by_sys : 0
                 * state : 0
                 * birthday : 721497600000
                 * is_verification : 0
                 * user_group : user
                 * gender : 0
                 * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2
                 * user_name : 锋在吹
                 * user_cover_pic_url : http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2
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
                    private int verification_type;
                    private int recommend_by_sys;
                    private int state;
                    private String birthday;
                    private int is_verification;
                    private String user_group;
                    private String gender;
                    private String profile_pic_url;
                    private String user_name;
                    private String user_cover_pic_url;

                    public int getVerification_type() {
                        return verification_type;
                    }

                    public void setVerification_type(int verification_type) {
                        this.verification_type = verification_type;
                    }

                    public int getRecommend_by_sys() {
                        return recommend_by_sys;
                    }

                    public void setRecommend_by_sys(int recommend_by_sys) {
                        this.recommend_by_sys = recommend_by_sys;
                    }

                    public int getState() {
                        return state;
                    }

                    public void setState(int state) {
                        this.state = state;
                    }

                    public String getBirthday() {
                        return birthday;
                    }

                    public void setBirthday(String birthday) {
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

                    public String getUser_cover_pic_url() {
                        return user_cover_pic_url;
                    }

                    public void setUser_cover_pic_url(String user_cover_pic_url) {
                        this.user_cover_pic_url = user_cover_pic_url;
                    }
                }
            }
        }

        public static class TripBean implements Serializable{
            private long created_at;
            private long updated_at;
            private long id;
            /**
             * recommend_index : 4
             * user_create_group_id : 1481695418296
             * trip_days : 6
             * biz_user_name : 啊啊啊
             * hot_index : 0
             * trip_state : 2
             * trip_route_type : 休闲
             * other_desc :
             * trip_equipment_intro : 开大趴
             * trip_intro : 相约98
             * meeting_province : 北京
             * meeting_city : 北京市朝阳区
             * meeting_place : 北京
             * participants_female_count : 0
             * participants_male_count : 0
             * trip_price : 200
             * trip_tags : ["旅行","户外","摄影"]
             * participate_end_time : 1482076800000
             * end_time : 1482595200000
             * start_time : 1482163200000
             * participants_limit_count : 200
             * trip_cover_pic : http://ofdx4t772.bkt.clouddn.com/1034377711648773?imageView2/1/w/600/h/600
             * trip_name : 相约98
             * a_trip_male_confirmed_total_count : 0
             * a_trip_female_confirmed_total_count : 0
             * a_trip_male_particpate_and_payed_total_count : 0
             * a_trip_female_particpate_and_payed_total_count : 0
             */

            private DataBean data;
            private long uid;
            /**
             * user_data : {"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"}
             * id : 1002274307506177
             * created_at : 1479781804088
             * updated_at : 1482032609497
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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class DataBean implements Serializable{
                private String recommend_index;
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
                private int a_trip_male_confirmed_total_count;
                private int a_trip_female_confirmed_total_count;
                private int a_trip_male_particpate_and_payed_total_count;
                private int a_trip_female_particpate_and_payed_total_count;
                private List<String> trip_tags;

                public String getRecommend_index() {
                    return recommend_index;
                }

                public void setRecommend_index(String recommend_index) {
                    this.recommend_index = recommend_index;
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

                public int getA_trip_male_confirmed_total_count() {
                    return a_trip_male_confirmed_total_count;
                }

                public void setA_trip_male_confirmed_total_count(int a_trip_male_confirmed_total_count) {
                    this.a_trip_male_confirmed_total_count = a_trip_male_confirmed_total_count;
                }

                public int getA_trip_female_confirmed_total_count() {
                    return a_trip_female_confirmed_total_count;
                }

                public void setA_trip_female_confirmed_total_count(int a_trip_female_confirmed_total_count) {
                    this.a_trip_female_confirmed_total_count = a_trip_female_confirmed_total_count;
                }

                public int getA_trip_male_particpate_and_payed_total_count() {
                    return a_trip_male_particpate_and_payed_total_count;
                }

                public void setA_trip_male_particpate_and_payed_total_count(int a_trip_male_particpate_and_payed_total_count) {
                    this.a_trip_male_particpate_and_payed_total_count = a_trip_male_particpate_and_payed_total_count;
                }

                public int getA_trip_female_particpate_and_payed_total_count() {
                    return a_trip_female_particpate_and_payed_total_count;
                }

                public void setA_trip_female_particpate_and_payed_total_count(int a_trip_female_particpate_and_payed_total_count) {
                    this.a_trip_female_particpate_and_payed_total_count = a_trip_female_particpate_and_payed_total_count;
                }

                public List<String> getTrip_tags() {
                    return trip_tags;
                }

                public void setTrip_tags(List<String> trip_tags) {
                    this.trip_tags = trip_tags;
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
