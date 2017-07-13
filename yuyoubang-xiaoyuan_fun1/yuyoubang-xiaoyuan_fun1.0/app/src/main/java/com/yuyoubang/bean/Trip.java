package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public class Trip implements Serializable {

    /**
     * created_at : 1479715358280
     * updated_at : 1479715358280
     * id : 1001159528611889
     * data : {"trip_days":0,"biz_user_name":"小源","hot_index":0,"trip_state":0,"trip_route_type":"登山","meeting_province":"北京","meeting_city":"北京","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":"200","trip_tags":["自助","登山"],"participate_end_time":"1480003212","end_time":"1480089612","start_time":"1479657612","participants_limit_count":"10","trip_cover_pic":"http: //img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg","trip_name":"北京七日游"}
     * uid : 980983181541377
     */

    private ResultBean result;
    /**
     * result : {"created_at":1479715358280,"updated_at":1479715358280,"id":1001159528611889,"data":{"trip_days":0,"biz_user_name":"小源","hot_index":0,"trip_state":0,"trip_route_type":"登山","meeting_province":"北京","meeting_city":"北京","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":"200","trip_tags":["自助","登山"],"participate_end_time":"1480003212","end_time":"1480089612","start_time":"1479657612","participants_limit_count":"10","trip_cover_pic":"http: //img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg","trip_name":"北京七日游"},"uid":980983181541377}
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
         * trip_days : 0
         * biz_user_name : 小源
         * hot_index : 0
         * trip_state : 0
         * trip_route_type : 登山
         * meeting_province : 北京
         * meeting_city : 北京
         * meeting_place : 北京
         * participants_female_count : 0
         * participants_male_count : 0
         * trip_price : 200
         * trip_tags : ["自助","登山"]
         * participate_end_time : 1480003212
         * end_time : 1480089612
         * start_time : 1479657612
         * participants_limit_count : 10
         * trip_cover_pic : http: //img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg
         * trip_name : 北京七日游
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
            private int trip_days;
            private String biz_user_name;
            private int hot_index;
            private int trip_state;
            private String trip_route_type;
            private String meeting_province;
            private String meeting_city;
            private String meeting_place;
            private int participants_female_count;
            private int participants_male_count;
            private String trip_price;
            private String participate_end_time;
            private String end_time;
            private String start_time;
            private String participants_limit_count;
            private String trip_cover_pic;
            private String trip_name;
            private List<String> trip_tags;

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

            public String getTrip_price() {
                return trip_price;
            }

            public void setTrip_price(String trip_price) {
                this.trip_price = trip_price;
            }

            public String getParticipate_end_time() {
                return participate_end_time;
            }

            public void setParticipate_end_time(String participate_end_time) {
                this.participate_end_time = participate_end_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getParticipants_limit_count() {
                return participants_limit_count;
            }

            public void setParticipants_limit_count(String participants_limit_count) {
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
