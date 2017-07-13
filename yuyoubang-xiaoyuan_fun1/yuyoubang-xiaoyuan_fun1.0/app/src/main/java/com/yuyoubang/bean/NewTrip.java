package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public class NewTrip implements Serializable{
    private String biz_user_name;
    private String city_of_biz_user;
    private String end_time;
    private String meeting_city;
    private String meeting_place;
    private String meeting_province;
    private String other_desc;
    private String participants_limit_count;
    private String participate_end_time;
    private String start_time;
    private String trip_cover_pic;
    private String trip_equipment_intro;
    private String trip_intro;
    private String trip_name;
    private String trip_price;
    private String trip_route_type;
    private String trip_tags;
    private String uid;
    private String trip_cost;
    private String trip_other;
    private String meeting_province_code;
    private String meeting_city_code;

    public String getMeeting_province_code() {
        return meeting_province_code;
    }

    public void setMeeting_province_code(String meeting_province_code) {
        this.meeting_province_code = meeting_province_code;
    }

    public String getMeeting_city_code() {
        return meeting_city_code;
    }

    public void setMeeting_city_code(String meeting_city_code) {
        this.meeting_city_code = meeting_city_code;
    }

    public List<Integer> getLabel() {
        return label;
    }

    public void setLabel(List<Integer> label) {
        this.label = label;
    }

    private List<Integer> label;//自用字段

    public String getTvlabel() {
        return tvlabel;
    }

    public void setTvlabel(String tvlabel) {
        this.tvlabel = tvlabel;
    }

    private String tvlabel;//自用字段

    public String getTrip_cost() {
        return trip_cost;
    }

    public void setTrip_cost(String trip_cost) {
        this.trip_cost = trip_cost;
    }

    public String getTrip_other() {
        return trip_other;
    }

    public void setTrip_other(String trip_other) {
        this.trip_other = trip_other;
    }

    public String getBiz_user_name() {
        return biz_user_name;
    }

    public void setBiz_user_name(String biz_user_name) {
        this.biz_user_name = biz_user_name;
    }

    public String getCity_of_biz_user() {
        return city_of_biz_user;
    }

    public void setCity_of_biz_user(String city_of_biz_user) {
        this.city_of_biz_user = city_of_biz_user;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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

    public String getMeeting_province() {
        return meeting_province;
    }

    public void setMeeting_province(String meeting_province) {
        this.meeting_province = meeting_province;
    }

    public String getOther_desc() {
        return other_desc;
    }

    public void setOther_desc(String other_desc) {
        this.other_desc = other_desc;
    }

    public String getParticipants_limit_count() {
        return participants_limit_count;
    }

    public void setParticipants_limit_count(String participants_limit_count) {
        this.participants_limit_count = participants_limit_count;
    }

    public String getParticipate_end_time() {
        return participate_end_time;
    }

    public void setParticipate_end_time(String participate_end_time) {
        this.participate_end_time = participate_end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTrip_cover_pic() {
        return trip_cover_pic;
    }

    public void setTrip_cover_pic(String trip_cover_pic) {
        this.trip_cover_pic = trip_cover_pic;
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

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getTrip_price() {
        return trip_price;
    }

    public void setTrip_price(String trip_price) {
        this.trip_price = trip_price;
    }

    public String getTrip_route_type() {
        return trip_route_type;
    }

    public void setTrip_route_type(String trip_route_type) {
        this.trip_route_type = trip_route_type;
    }

    public String getTrip_tags() {
        return trip_tags;
    }

    public void setTrip_tags(String trip_tags) {
        this.trip_tags = trip_tags;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
