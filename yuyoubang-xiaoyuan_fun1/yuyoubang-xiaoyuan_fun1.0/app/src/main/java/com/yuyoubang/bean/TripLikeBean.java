package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/30.
 */
public class TripLikeBean implements Serializable {


    /**
     * result : [{"created_at":1481536902928,"updated_at":1482494963916,"id":1031719965753381,"data":{"participate_like_total_count":2,"user_create_group_id":1481536902182,"trip_days":2,"biz_user_name":"陈鑫陈鑫","hot_index":0,"trip_state":1,"trip_route_type":"烧烤","other_desc":"兔兔","trip_equipment_intro":" 涂","trip_intro":"途家","meeting_province":"澳门","meeting_city":"澳门澳门","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":1,"trip_tags":["自驾","大叔","摄影"],"participate_end_time":1480521600000,"end_time":1481126400000,"start_time":1481040000000,"participants_limit_count":1,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1031718573244451?imageView2/1/w/600/h/600","trip_name":"可口可乐"},"uid":995062101114881}]
     * error_code : 0
     */

    private int error_code;
    /**
     * created_at : 1481536902928
     * updated_at : 1482494963916
     * id : 1031719965753381
     * data : {"participate_like_total_count":2,"user_create_group_id":1481536902182,"trip_days":2,"biz_user_name":"陈鑫陈鑫","hot_index":0,"trip_state":1,"trip_route_type":"烧烤","other_desc":"兔兔","trip_equipment_intro":" 涂","trip_intro":"途家","meeting_province":"澳门","meeting_city":"澳门澳门","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":1,"trip_tags":["自驾","大叔","摄影"],"participate_end_time":1480521600000,"end_time":1481126400000,"start_time":1481040000000,"participants_limit_count":1,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1031718573244451?imageView2/1/w/600/h/600","trip_name":"可口可乐"}
     * uid : 995062101114881
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
         * participate_like_total_count : 2
         * user_create_group_id : 1481536902182
         * trip_days : 2
         * biz_user_name : 陈鑫陈鑫
         * hot_index : 0
         * trip_state : 1
         * trip_route_type : 烧烤
         * other_desc : 兔兔
         * trip_equipment_intro :  涂
         * trip_intro : 途家
         * meeting_province : 澳门
         * meeting_city : 澳门澳门
         * meeting_place : 北京
         * participants_female_count : 0
         * participants_male_count : 0
         * trip_price : 1
         * trip_tags : ["自驾","大叔","摄影"]
         * participate_end_time : 1480521600000
         * end_time : 1481126400000
         * start_time : 1481040000000
         * participants_limit_count : 1
         * trip_cover_pic : http://ofdx4t772.bkt.clouddn.com/1031718573244451?imageView2/1/w/600/h/600
         * trip_name : 可口可乐
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

        public static class DataBean implements Serializable {
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

            public List<String> getTrip_tags() {
                return trip_tags;
            }

            public void setTrip_tags(List<String> trip_tags) {
                this.trip_tags = trip_tags;
            }
        }
    }
}
