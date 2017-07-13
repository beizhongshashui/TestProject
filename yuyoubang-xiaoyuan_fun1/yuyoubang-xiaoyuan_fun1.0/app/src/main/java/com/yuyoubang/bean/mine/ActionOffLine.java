package com.yuyoubang.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/8.
 */

public class ActionOffLine implements Serializable{

    /**
     * result : [{"created_at":1481176719709,"updated_at":1481179569156,"id":1025677097762839,"data":{"trip_name":"摘柿子","trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1025675688476694?imageView2","participants_limit_count":"15","start_time":"1481262955000","end_time":"1481349355000","participate_end_time":"1481349355000","trip_tags":["周边游","周末出游","短途游"],"trip_price":"20","participants_male_count":0,"participants_female_count":0,"meeting_place":"地铁站","meeting_city":"朝阳区","meeting_province":"北京","trip_intro":"摘柿子","trip_equipment_intro":"坐车","other_desc":"吃饭","trip_route_type":"徒步","trip_state":2,"hot_index":0,"biz_user_name":"锋在吹","trip_days":2,"user_create_group_id":"1481176719554"},"uid":982165555838977}]
     * error_code : 0
     */

    private int error_code;
    /**
     * created_at : 1481176719709
     * updated_at : 1481179569156
     * id : 1025677097762839
     * data : {"trip_name":"摘柿子","trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1025675688476694?imageView2","participants_limit_count":"15","start_time":"1481262955000","end_time":"1481349355000","participate_end_time":"1481349355000","trip_tags":["周边游","周末出游","短途游"],"trip_price":"20","participants_male_count":0,"participants_female_count":0,"meeting_place":"地铁站","meeting_city":"朝阳区","meeting_province":"北京","trip_intro":"摘柿子","trip_equipment_intro":"坐车","other_desc":"吃饭","trip_route_type":"徒步","trip_state":2,"hot_index":0,"biz_user_name":"锋在吹","trip_days":2,"user_create_group_id":"1481176719554"}
     * uid : 982165555838977
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
         * trip_name : 摘柿子
         * trip_cover_pic : http://ofdx4t772.bkt.clouddn.com/1025675688476694?imageView2
         * participants_limit_count : 15
         * start_time : 1481262955000
         * end_time : 1481349355000
         * participate_end_time : 1481349355000
         * trip_tags : ["周边游","周末出游","短途游"]
         * trip_price : 20
         * participants_male_count : 0
         * participants_female_count : 0
         * meeting_place : 地铁站
         * meeting_city : 朝阳区
         * meeting_province : 北京
         * trip_intro : 摘柿子
         * trip_equipment_intro : 坐车
         * other_desc : 吃饭
         * trip_route_type : 徒步
         * trip_state : 2
         * hot_index : 0
         * biz_user_name : 锋在吹
         * trip_days : 2
         * user_create_group_id : 1481176719554
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
            private String trip_name;
            private String trip_cover_pic;
            private String participants_limit_count;
            private String start_time;
            private String end_time;
            private String participate_end_time;
            private String trip_price;
            private int participants_male_count;
            private int participants_female_count;
            private String meeting_place;
            private String meeting_city;
            private String meeting_province;
            private String trip_intro;
            private String trip_equipment_intro;
            private String other_desc;
            private String trip_route_type;
            private int trip_state;
            private int hot_index;
            private String biz_user_name;
            private int trip_days;
            private String user_create_group_id;
            private List<String> trip_tags;

            public String getTrip_name() {
                return trip_name;
            }

            public void setTrip_name(String trip_name) {
                this.trip_name = trip_name;
            }

            public String getTrip_cover_pic() {
                return trip_cover_pic;
            }

            public void setTrip_cover_pic(String trip_cover_pic) {
                this.trip_cover_pic = trip_cover_pic;
            }

            public String getParticipants_limit_count() {
                return participants_limit_count;
            }

            public void setParticipants_limit_count(String participants_limit_count) {
                this.participants_limit_count = participants_limit_count;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getParticipate_end_time() {
                return participate_end_time;
            }

            public void setParticipate_end_time(String participate_end_time) {
                this.participate_end_time = participate_end_time;
            }

            public String getTrip_price() {
                return trip_price;
            }

            public void setTrip_price(String trip_price) {
                this.trip_price = trip_price;
            }

            public int getParticipants_male_count() {
                return participants_male_count;
            }

            public void setParticipants_male_count(int participants_male_count) {
                this.participants_male_count = participants_male_count;
            }

            public int getParticipants_female_count() {
                return participants_female_count;
            }

            public void setParticipants_female_count(int participants_female_count) {
                this.participants_female_count = participants_female_count;
            }

            public String getMeeting_place() {
                return meeting_place;
            }

            public void setMeeting_place(String meeting_place) {
                this.meeting_place = meeting_place;
            }

            public String getMeeting_city() {
                return meeting_city;
            }

            public void setMeeting_city(String meeting_city) {
                this.meeting_city = meeting_city;
            }

            public String getMeeting_province() {
                return meeting_province;
            }

            public void setMeeting_province(String meeting_province) {
                this.meeting_province = meeting_province;
            }

            public String getTrip_intro() {
                return trip_intro;
            }

            public void setTrip_intro(String trip_intro) {
                this.trip_intro = trip_intro;
            }

            public String getTrip_equipment_intro() {
                return trip_equipment_intro;
            }

            public void setTrip_equipment_intro(String trip_equipment_intro) {
                this.trip_equipment_intro = trip_equipment_intro;
            }

            public String getOther_desc() {
                return other_desc;
            }

            public void setOther_desc(String other_desc) {
                this.other_desc = other_desc;
            }

            public String getTrip_route_type() {
                return trip_route_type;
            }

            public void setTrip_route_type(String trip_route_type) {
                this.trip_route_type = trip_route_type;
            }

            public int getTrip_state() {
                return trip_state;
            }

            public void setTrip_state(int trip_state) {
                this.trip_state = trip_state;
            }

            public int getHot_index() {
                return hot_index;
            }

            public void setHot_index(int hot_index) {
                this.hot_index = hot_index;
            }

            public String getBiz_user_name() {
                return biz_user_name;
            }

            public void setBiz_user_name(String biz_user_name) {
                this.biz_user_name = biz_user_name;
            }

            public int getTrip_days() {
                return trip_days;
            }

            public void setTrip_days(int trip_days) {
                this.trip_days = trip_days;
            }

            public String getUser_create_group_id() {
                return user_create_group_id;
            }

            public void setUser_create_group_id(String user_create_group_id) {
                this.user_create_group_id = user_create_group_id;
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
