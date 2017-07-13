package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/22.
 */

public class OrderShow implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1482141144942,"updated_at":1482290565159,"op_name":"participate","data_id":976630953607193,"uid":962080376160261,"id":1041857464303668,"op_data":{"trip_biz_name":"【修改】陈鑫12","trip_start_time":1482639132000,"trip_name":"聆听精灵岛西追逐海豚之旅","user_name":"测试用户001","sys_cash":24,"biz_cash":576,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"商家拒绝用户退款的注释","refund_biz_state":"biz_refund_refuse","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":962033467064324,"trip_id":976630953607193,"participate_state":"payed","total_price":600,"female_count":3,"male_count":3,"contact_phone":"联系电话","contact_user_name":"报名用户的真实姓名","biz_user_reply":"哈哈哈哈","finished_state":1},"trip":{"data":{"participate_state_desc":"by_business","participate_state":"cancel","hot_index":0,"trip_state_desc":"","trip_state":-1,"trip_route_type":"摄影","other_desc":"中午从七色土景区回到市区用餐，一定要尝尝咖喱鸡肉大虾和百万富翁沙拉。欣赏完大浪湾的美景，可以在周边享用龙虾大餐。吃完饭可以再去圣奥宾庄园 St Albin，在200 年老宅中品朗姆酒。喜爱肉食的朋友，可以去萨维尼亚牛排西餐厅享受新鲜进口的牛肉。","trip_equipment_intro":"日常生活必备品即可，着装方便活动为主。","trip_intro":"看海豚通常要很早出发，可以提前通过旅行社或酒店预订。岛西经营海豚行程的旅行社及船公司非常多，发船点也各不相同。旅行者预定行程时需要明确上船地点和时间，避免延误。推荐塔马兰海湾小渔村。塔马兰海湾是天然海湾，没有码头，须涉水上船。每年 7~9 月是座头鲸出现在毛里求斯海域的季节，观鲸位置比海豚更靠外海。运气好的话不仅可以看到，还可以与这种温顺的庞然大物共游。 下午可以去Casela 鸟公园\u2014\u2014体验非洲自然风情。Casela 鸟公园是毛里求斯知名的非洲风情动物园。可以体验乘坐大篷车看动物(Safari)、与狮同行(Walk with Lion)、四驱车 (Quad)、高空溜索（Ziplines）、攀岩（Canyoning）、登山（Via Ferrata）等项目。","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":5999,"trip_tags":["自助游"],"participate_end_time":1478837532000,"end_time":1483157532000,"start_time":1482639132000,"participants_limit_count":100,"trip_cover_pic":"http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg","trip_name":"聆听精灵岛西追逐海豚之旅","participate_like_total_count":2},"id":976630953607193,"created_at":1478253341304,"updated_at":1482142600533},"user":{"user_data":{"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0},"id":962080376160261,"created_at":1477386059719,"updated_at":1478959953447}}]}
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
         * created_at : 1482141144942
         * updated_at : 1482290565159
         * op_name : participate
         * data_id : 976630953607193
         * uid : 962080376160261
         * id : 1041857464303668
         * op_data : {"trip_biz_name":"【修改】陈鑫12","trip_start_time":1482639132000,"trip_name":"聆听精灵岛西追逐海豚之旅","user_name":"测试用户001","sys_cash":24,"biz_cash":576,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"商家拒绝用户退款的注释","refund_biz_state":"biz_refund_refuse","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":962033467064324,"trip_id":976630953607193,"participate_state":"payed","total_price":600,"female_count":3,"male_count":3,"contact_phone":"联系电话","contact_user_name":"报名用户的真实姓名","biz_user_reply":"哈哈哈哈","finished_state":1}
         * trip : {"data":{"participate_state_desc":"by_business","participate_state":"cancel","hot_index":0,"trip_state_desc":"","trip_state":-1,"trip_route_type":"摄影","other_desc":"中午从七色土景区回到市区用餐，一定要尝尝咖喱鸡肉大虾和百万富翁沙拉。欣赏完大浪湾的美景，可以在周边享用龙虾大餐。吃完饭可以再去圣奥宾庄园 St Albin，在200 年老宅中品朗姆酒。喜爱肉食的朋友，可以去萨维尼亚牛排西餐厅享受新鲜进口的牛肉。","trip_equipment_intro":"日常生活必备品即可，着装方便活动为主。","trip_intro":"看海豚通常要很早出发，可以提前通过旅行社或酒店预订。岛西经营海豚行程的旅行社及船公司非常多，发船点也各不相同。旅行者预定行程时需要明确上船地点和时间，避免延误。推荐塔马兰海湾小渔村。塔马兰海湾是天然海湾，没有码头，须涉水上船。每年 7~9 月是座头鲸出现在毛里求斯海域的季节，观鲸位置比海豚更靠外海。运气好的话不仅可以看到，还可以与这种温顺的庞然大物共游。 下午可以去Casela 鸟公园\u2014\u2014体验非洲自然风情。Casela 鸟公园是毛里求斯知名的非洲风情动物园。可以体验乘坐大篷车看动物(Safari)、与狮同行(Walk with Lion)、四驱车 (Quad)、高空溜索（Ziplines）、攀岩（Canyoning）、登山（Via Ferrata）等项目。","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":5999,"trip_tags":["自助游"],"participate_end_time":1478837532000,"end_time":1483157532000,"start_time":1482639132000,"participants_limit_count":100,"trip_cover_pic":"http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg","trip_name":"聆听精灵岛西追逐海豚之旅","participate_like_total_count":2},"id":976630953607193,"created_at":1478253341304,"updated_at":1482142600533}
         * user : {"user_data":{"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0},"id":962080376160261,"created_at":1477386059719,"updated_at":1478959953447}
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
             * trip_start_time : 1482639132000
             * trip_name : 聆听精灵岛西追逐海豚之旅
             * user_name : 测试用户001
             * sys_cash : 24
             * biz_cash : 576
             * refund_finance_comment :
             * refund_finance_state :
             * refund_system_comment :
             * refund_sys_state :
             * refund_biz_comment : 商家拒绝用户退款的注释
             * refund_biz_state : biz_refund_refuse
             * refund_start_time :
             * refund_reason :
             * refund_state :
             * trip_biz_uid : 962033467064324
             * trip_id : 976630953607193
             * participate_state : payed
             * total_price : 600
             * female_count : 3
             * male_count : 3
             * contact_phone : 联系电话
             * contact_user_name : 报名用户的真实姓名
             * biz_user_reply : 哈哈哈哈
             * finished_state : 1
             */

            private OpDataBean op_data;
            /**
             * data : {"participate_state_desc":"by_business","participate_state":"cancel","hot_index":0,"trip_state_desc":"","trip_state":-1,"trip_route_type":"摄影","other_desc":"中午从七色土景区回到市区用餐，一定要尝尝咖喱鸡肉大虾和百万富翁沙拉。欣赏完大浪湾的美景，可以在周边享用龙虾大餐。吃完饭可以再去圣奥宾庄园 St Albin，在200 年老宅中品朗姆酒。喜爱肉食的朋友，可以去萨维尼亚牛排西餐厅享受新鲜进口的牛肉。","trip_equipment_intro":"日常生活必备品即可，着装方便活动为主。","trip_intro":"看海豚通常要很早出发，可以提前通过旅行社或酒店预订。岛西经营海豚行程的旅行社及船公司非常多，发船点也各不相同。旅行者预定行程时需要明确上船地点和时间，避免延误。推荐塔马兰海湾小渔村。塔马兰海湾是天然海湾，没有码头，须涉水上船。每年 7~9 月是座头鲸出现在毛里求斯海域的季节，观鲸位置比海豚更靠外海。运气好的话不仅可以看到，还可以与这种温顺的庞然大物共游。 下午可以去Casela 鸟公园\u2014\u2014体验非洲自然风情。Casela 鸟公园是毛里求斯知名的非洲风情动物园。可以体验乘坐大篷车看动物(Safari)、与狮同行(Walk with Lion)、四驱车 (Quad)、高空溜索（Ziplines）、攀岩（Canyoning）、登山（Via Ferrata）等项目。","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":5999,"trip_tags":["自助游"],"participate_end_time":1478837532000,"end_time":1483157532000,"start_time":1482639132000,"participants_limit_count":100,"trip_cover_pic":"http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg","trip_name":"聆听精灵岛西追逐海豚之旅","participate_like_total_count":2}
             * id : 976630953607193
             * created_at : 1478253341304
             * updated_at : 1482142600533
             */

            private TripBean trip;
            /**
             * user_data : {"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0}
             * id : 962080376160261
             * created_at : 1477386059719
             * updated_at : 1478959953447
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
                private long trip_biz_uid;
                private long trip_id;
                private String participate_state;
                private int total_price;
                private int female_count;
                private int male_count;
                private String contact_phone;
                private String contact_user_name;
                private String biz_user_reply;
                private int finished_state;

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

                public String getBiz_user_reply() {
                    return biz_user_reply;
                }

                public void setBiz_user_reply(String biz_user_reply) {
                    this.biz_user_reply = biz_user_reply;
                }

                public int getFinished_state() {
                    return finished_state;
                }

                public void setFinished_state(int finished_state) {
                    this.finished_state = finished_state;
                }
            }

            public static class TripBean implements Serializable{
                /**
                 * participate_state_desc : by_business
                 * participate_state : cancel
                 * hot_index : 0
                 * trip_state_desc :
                 * trip_state : -1
                 * trip_route_type : 摄影
                 * other_desc : 中午从七色土景区回到市区用餐，一定要尝尝咖喱鸡肉大虾和百万富翁沙拉。欣赏完大浪湾的美景，可以在周边享用龙虾大餐。吃完饭可以再去圣奥宾庄园 St Albin，在200 年老宅中品朗姆酒。喜爱肉食的朋友，可以去萨维尼亚牛排西餐厅享受新鲜进口的牛肉。
                 * trip_equipment_intro : 日常生活必备品即可，着装方便活动为主。
                 * trip_intro : 看海豚通常要很早出发，可以提前通过旅行社或酒店预订。岛西经营海豚行程的旅行社及船公司非常多，发船点也各不相同。旅行者预定行程时需要明确上船地点和时间，避免延误。推荐塔马兰海湾小渔村。塔马兰海湾是天然海湾，没有码头，须涉水上船。每年 7~9 月是座头鲸出现在毛里求斯海域的季节，观鲸位置比海豚更靠外海。运气好的话不仅可以看到，还可以与这种温顺的庞然大物共游。 下午可以去Casela 鸟公园——体验非洲自然风情。Casela 鸟公园是毛里求斯知名的非洲风情动物园。可以体验乘坐大篷车看动物(Safari)、与狮同行(Walk with Lion)、四驱车 (Quad)、高空溜索（Ziplines）、攀岩（Canyoning）、登山（Via Ferrata）等项目。
                 * meeting_place : 北京
                 * participants_female_count : 0
                 * participants_male_count : 0
                 * trip_price : 5999
                 * trip_tags : ["自助游"]
                 * participate_end_time : 1478837532000
                 * end_time : 1483157532000
                 * start_time : 1482639132000
                 * participants_limit_count : 100
                 * trip_cover_pic : http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg
                 * trip_name : 聆听精灵岛西追逐海豚之旅
                 * participate_like_total_count : 2
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
                    private String participate_state_desc;
                    private String participate_state;
                    private int hot_index;
                    private String trip_state_desc;
                    private int trip_state;
                    private String trip_route_type;
                    private String other_desc;
                    private String trip_equipment_intro;
                    private String trip_intro;
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
                    private int participate_like_total_count;
                    private List<String> trip_tags;

                    public String getParticipate_state_desc() {
                        return participate_state_desc;
                    }

                    public void setParticipate_state_desc(String participate_state_desc) {
                        this.participate_state_desc = participate_state_desc;
                    }

                    public String getParticipate_state() {
                        return participate_state;
                    }

                    public void setParticipate_state(String participate_state) {
                        this.participate_state = participate_state;
                    }

                    public int getHot_index() {
                        return hot_index;
                    }

                    public void setHot_index(int hot_index) {
                        this.hot_index = hot_index;
                    }

                    public String getTrip_state_desc() {
                        return trip_state_desc;
                    }

                    public void setTrip_state_desc(String trip_state_desc) {
                        this.trip_state_desc = trip_state_desc;
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

                    public int getParticipate_like_total_count() {
                        return participate_like_total_count;
                    }

                    public void setParticipate_like_total_count(int participate_like_total_count) {
                        this.participate_like_total_count = participate_like_total_count;
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
                 * user_name : 测试用户001
                 * profile_pic_url : http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg
                 * gender : 1
                 * user_desc : 测试用户（test_user_001）
                 * user_location : 深圳
                 * user_group : user
                 * verification_type : 1
                 * birthday : 1467354028000
                 * state : 0
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
                    private String user_name;
                    private String profile_pic_url;
                    private int gender;
                    private String user_desc;
                    private String user_location;
                    private String user_group;
                    private int verification_type;
                    private long birthday;
                    private int state;

                    public String getUser_name() {
                        return user_name;
                    }

                    public void setUser_name(String user_name) {
                        this.user_name = user_name;
                    }

                    public String getProfile_pic_url() {
                        return profile_pic_url;
                    }

                    public void setProfile_pic_url(String profile_pic_url) {
                        this.profile_pic_url = profile_pic_url;
                    }

                    public int getGender() {
                        return gender;
                    }

                    public void setGender(int gender) {
                        this.gender = gender;
                    }

                    public String getUser_desc() {
                        return user_desc;
                    }

                    public void setUser_desc(String user_desc) {
                        this.user_desc = user_desc;
                    }

                    public String getUser_location() {
                        return user_location;
                    }

                    public void setUser_location(String user_location) {
                        this.user_location = user_location;
                    }

                    public String getUser_group() {
                        return user_group;
                    }

                    public void setUser_group(String user_group) {
                        this.user_group = user_group;
                    }

                    public int getVerification_type() {
                        return verification_type;
                    }

                    public void setVerification_type(int verification_type) {
                        this.verification_type = verification_type;
                    }

                    public long getBirthday() {
                        return birthday;
                    }

                    public void setBirthday(long birthday) {
                        this.birthday = birthday;
                    }

                    public int getState() {
                        return state;
                    }

                    public void setState(int state) {
                        this.state = state;
                    }
                }
            }
        }
    }
}
