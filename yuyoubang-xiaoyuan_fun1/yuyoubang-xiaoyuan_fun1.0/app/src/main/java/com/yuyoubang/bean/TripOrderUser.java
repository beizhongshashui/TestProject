package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/26.
 */

public class TripOrderUser implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1482737036070,"updated_at":1482737036070,"op_name":"participate","data_id":1025671460618261,"uid":1002274307506177,"id":1051854873100344,"op_data":{"trip_biz_name":"锋在吹","trip_start_time":"1481327411000","trip_name":"野鸭湖","user_name":"啊啊啊","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":982165555838977,"trip_id":1025671460618261,"participate_state":"book","total_price":1000,"female_count":1,"male_count":1,"contact_phone":13333333333,"contact_user_name":"进口"},"user":{"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482032609497}},{"created_at":1482736965161,"updated_at":1482736965161,"op_name":"participate","data_id":1025671460618261,"uid":1002274307506177,"id":1051853681918007,"op_data":{"trip_biz_name":"锋在吹","trip_start_time":"1481327411000","trip_name":"野鸭湖","user_name":"啊啊啊","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":982165555838977,"trip_id":1025671460618261,"participate_state":"book","total_price":1000,"female_count":1,"male_count":1,"contact_phone":16656666666,"contact_user_name":"诺"},"user":{"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482032609497}},{"created_at":1482736847373,"updated_at":1482736847373,"op_name":"participate","data_id":1025671460618261,"uid":1002274307506177,"id":1051851702206518,"op_data":{"trip_biz_name":"锋在吹","trip_start_time":"1481327411000","trip_name":"野鸭湖","user_name":"啊啊啊","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":982165555838977,"trip_id":1025671460618261,"participate_state":"book","total_price":2000,"female_count":2,"male_count":2,"contact_phone":13333333333,"contact_user_name":"陈宏"},"user":{"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482032609497}},{"created_at":1482736618317,"updated_at":1482736618317,"op_name":"participate","data_id":1025671460618261,"uid":1002274307506177,"id":1051847860224053,"op_data":{"trip_biz_name":"锋在吹","trip_start_time":"1481327411000","trip_name":"野鸭湖","user_name":"啊啊啊","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":982165555838977,"trip_id":1025671460618261,"participate_state":"book","total_price":1000,"female_count":1,"male_count":1,"contact_phone":13333333333,"contact_user_name":"陈宏"},"user":{"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482032609497}},{"created_at":1482569905610,"updated_at":1482569905610,"op_name":"participate","data_id":1025671460618261,"uid":982165555838977,"id":1049050880213008,"op_data":{"trip_start_time":"1481327411000","trip_name":"野鸭湖","trip_biz_name":"锋在吹","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":"982165555838977","trip_id":"1025671460618261","participate_state":"book","total_price":"1000","female_count":"1","male_count":"1","contact_phone":"13520232775","contact_user_name":"锋在吹"},"user":{"user_data":{"user_tags":["我的名字"],"province":"北京","user_location":"昌平区","verification_type":2,"recommend_by_sys":0,"state":0,"birthday":721497600000,"is_verification":0,"user_group":"user","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1045769961406466?imageView2","user_name":"锋在吹","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027471605563419?imageView2","user_desc":"我就是我，不一样的烟火"},"id":982165555838977,"created_at":1478583229087,"updated_at":1482668965316}},{"created_at":1482552988949,"updated_at":1482552988949,"op_name":"participate","data_id":1025671460618261,"uid":976528209936404,"id":1048767060049925,"op_data":{"trip_biz_name":"锋在吹","trip_start_time":"1481327411000","trip_name":"野鸭湖","user_name":"七月","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":"982165555838977","trip_id":"1025671460618261","participate_state":"book","total_price":"1000","female_count":"1","male_count":"1","contact_phone":"15712921838","contact_user_name":"七月"},"user":{"user_data":{"verification_type":2,"user_name":"七月","profile_pic_url":"http://static.risfond.com/images2/2016/2/232d68c5013245eea8c51bc01373145c.jpg","gender":0,"user_desc":"钱到用时方恨少，世界那么大，我想去看看，钱包这么小，哪也去不了。","user_location":"北京","user_group":"user","is_verification":0,"user_tags":["背包客","驴友"],"birthday":647493132000,"user_cover_pic_url":"http://img.blog.csdn.net/20161104163406312?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center","state":0},"id":976528209936404,"created_at":1478247217785,"updated_at":1481645291967}},{"created_at":1482408247447,"updated_at":1482569432963,"op_name":"participate","data_id":1025671460618261,"uid":1046316244336656,"id":1046338709028889,"op_data":{"contact_user_name":"额废话","contact_phone":123456789,"male_count":0,"female_count":1,"total_price":500,"participate_state":"confirmed","trip_id":1025671460618261,"trip_biz_uid":982165555838977,"refund_state":"","refund_reason":"","refund_start_time":"","refund_biz_state":"","refund_biz_comment":"","refund_sys_state":"","refund_system_comment":"","refund_finance_state":"","refund_finance_comment":"","biz_cash":0,"sys_cash":0,"user_name":"","trip_name":"野鸭湖","trip_start_time":"1481327411000","trip_biz_name":"锋在吹"},"user":{"user_data":{"state":0,"birthday":1482336000000,"is_verification":0,"user_group":"user","user_location":"北京","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1046316831539217?imageView2/1/w/600/h/600"},"id":1046316244336656,"created_at":1482406908133,"updated_at":1482406946677}}]}
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
         * created_at : 1482737036070
         * updated_at : 1482737036070
         * op_name : participate
         * data_id : 1025671460618261
         * uid : 1002274307506177
         * id : 1051854873100344
         * op_data : {"trip_biz_name":"锋在吹","trip_start_time":"1481327411000","trip_name":"野鸭湖","user_name":"啊啊啊","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":982165555838977,"trip_id":1025671460618261,"participate_state":"book","total_price":1000,"female_count":1,"male_count":1,"contact_phone":13333333333,"contact_user_name":"进口"}
         * user : {"user_data":{"state":0,"birthday":683650800000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","萝莉"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1482032609497}
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
             * trip_biz_name : 锋在吹
             * trip_start_time : 1481327411000
             * trip_name : 野鸭湖
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
             * trip_biz_uid : 982165555838977
             * trip_id : 1025671460618261
             * participate_state : book
             * total_price : 1000
             * female_count : 1
             * male_count : 1
             * contact_phone : 13333333333
             * contact_user_name : 进口
             */

            private OpDataBean op_data;
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
                private String trip_start_time;
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

                public String getTrip_start_time() {
                    return trip_start_time;
                }

                public void setTrip_start_time(String trip_start_time) {
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
                    private String gender;
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
