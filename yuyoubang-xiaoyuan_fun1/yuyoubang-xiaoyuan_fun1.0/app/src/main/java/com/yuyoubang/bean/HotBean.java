package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

public class HotBean implements Serializable {


    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1480675090666,"updated_at":1480675125072,"id":1017261159678040,"data":{"recommend_index":1,"user_create_group_id":1480675089334,"trip_days":1,"biz_user_name":"小源","hot_index":0,"trip_state":1,"trip_route_type":"漂流","other_desc":"","trip_equipment_intro":"退款","trip_intro":"兔兔","meeting_province":"北京","meeting_city":"北京","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":"北京","trip_tags":["测试","哈哈"],"participate_end_time":1482336000000,"end_time":1482336000000,"start_time":1482336000000,"participants_limit_count":10,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1017260572475479?imageView2/1/w/600/h/600","trip_name":"悲剧了","a_trip_male_confirmed_total_count":0,"a_trip_female_confirmed_total_count":0},"uid":995062101114881,"user_result":{"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"www.baidu.com1","birthday":592848000000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"北京","user_desc":"描述描述","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1017242318864455?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":2},"id":995062101114881,"created_at":1479351923757,"updated_at":1480673972536}}]}
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
        /**
         * created_at : 1480675090666
         * updated_at : 1480675125072
         * id : 1017261159678040
         * data : {"recommend_index":1,"user_create_group_id":1480675089334,"trip_days":1,"biz_user_name":"小源","hot_index":0,"trip_state":1,"trip_route_type":"漂流","other_desc":"","trip_equipment_intro":"退款","trip_intro":"兔兔","meeting_province":"北京","meeting_city":"北京","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":"北京","trip_tags":["测试","哈哈"],"participate_end_time":1482336000000,"end_time":1482336000000,"start_time":1482336000000,"participants_limit_count":10,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1017260572475479?imageView2/1/w/600/h/600","trip_name":"悲剧了","a_trip_male_confirmed_total_count":0,"a_trip_female_confirmed_total_count":0}
         * uid : 995062101114881
         * user_result : {"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"www.baidu.com1","birthday":592848000000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"北京","user_desc":"描述描述","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1017242318864455?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":2},"id":995062101114881,"created_at":1479351923757,"updated_at":1480673972536}
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private long created_at;
            private long updated_at;
            private long id;
            /**
             * recommend_index : 1
             * user_create_group_id : 1480675089334
             * trip_days : 1
             * biz_user_name : 小源
             * hot_index : 0
             * trip_state : 1
             * trip_route_type : 漂流
             * other_desc :
             * trip_equipment_intro : 退款
             * trip_intro : 兔兔
             * meeting_province : 北京
             * meeting_city : 北京
             * meeting_place : 北京
             * participants_female_count : 0
             * participants_male_count : 0
             * trip_price : 北京
             * trip_tags : ["测试","哈哈"]
             * participate_end_time : 1482336000000
             * end_time : 1482336000000
             * start_time : 1482336000000
             * participants_limit_count : 10
             * trip_cover_pic : http://ofdx4t772.bkt.clouddn.com/1017260572475479?imageView2/1/w/600/h/600
             * trip_name : 悲剧了
             * a_trip_male_confirmed_total_count : 0
             * a_trip_female_confirmed_total_count : 0
             */

            private DataBean data;
            private long uid;
            /**
             * user_data : {"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"www.baidu.com1","birthday":592848000000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"北京","user_desc":"描述描述","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1017242318864455?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":2}
             * id : 995062101114881
             * created_at : 1479351923757
             * updated_at : 1480673972536
             */

            private UserResultBean user_result;

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

            public UserResultBean getUser_result() {
                return user_result;
            }

            public void setUser_result(UserResultBean user_result) {
                this.user_result = user_result;
            }

            public static class DataBean {
                private String recommend_index;
                private String user_create_group_id;
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
                private String trip_price;
                private String participate_end_time;
                private String end_time;
                private String start_time;
                private String participants_limit_count;
                private String trip_cover_pic;
                private String trip_name;
                private int a_trip_male_confirmed_total_count;
                private int a_trip_female_confirmed_total_count;
                private List<String> trip_tags;

                public String getRecommend_index() {
                    return recommend_index;
                }

                public void setRecommend_index(String recommend_index) {
                    this.recommend_index = recommend_index;
                }

                public String getUser_create_group_id() {
                    return user_create_group_id;
                }

                public void setUser_create_group_id(String user_create_group_id) {
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

                public List<String> getTrip_tags() {
                    return trip_tags;
                }

                public void setTrip_tags(List<String> trip_tags) {
                    this.trip_tags = trip_tags;
                }
            }

            public static class UserResultBean {
                /**
                 * real_name : 修改后的真实姓名
                 * recommend_by_sys : 1
                 * state : 0
                 * province : 山东
                 * user_cover_pic_url : www.baidu.com1
                 * birthday : 592848000000
                 * user_tags : ["标签、标签1"]
                 * is_verification : 0
                 * user_group : user
                 * user_location : 北京
                 * user_desc : 描述描述
                 * gender : 0
                 * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1017242318864455?imageView2/1/w/600/h/600
                 * user_name : 陈鑫陈鑫
                 * verification_type : 2
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

                public static class UserDataBean {
                    private String real_name;
                    private int recommend_by_sys;
                    private int state;
                    private String province;
                    private String user_cover_pic_url;
                    private String birthday;
                    private int is_verification;
                    private String user_group;
                    private String user_location;
                    private String user_desc;
                    private String gender;
                    private String profile_pic_url;
                    private String user_name;
                    private String verification_type;
                    private List<String> user_tags;

                    public String getReal_name() {
                        return real_name;
                    }

                    public void setReal_name(String real_name) {
                        this.real_name = real_name;
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

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
                    }

                    public String getUser_cover_pic_url() {
                        return user_cover_pic_url;
                    }

                    public void setUser_cover_pic_url(String user_cover_pic_url) {
                        this.user_cover_pic_url = user_cover_pic_url;
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

                    public String getVerification_type() {
                        return verification_type;
                    }

                    public void setVerification_type(String verification_type) {
                        this.verification_type = verification_type;
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