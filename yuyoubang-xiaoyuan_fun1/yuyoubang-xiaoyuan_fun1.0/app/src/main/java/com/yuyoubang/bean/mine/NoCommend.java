package com.yuyoubang.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/7.
 */

public class NoCommend implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481613071757,"updated_at":1482312644923,"op_name":"participate","data_id":1031724831146023,"uid":1002274307506177,"id":1032997869518904,"op_data":{"trip_biz_name":"小源源","trip_start_time":"1481709654000","trip_name":"中秋","user_name":"啊啊啊","sys_cash":5.76,"biz_cash":138.24,"refund_finance_comment":"","refund_finance_state":"has_process","refund_system_comment":"退款操作注释","refund_sys_state":"sys_refund_agree","refund_biz_comment":"","refund_biz_state":"","refund_start_time":1481613819912,"refund_reason":"我要退款","refund_state":"refund_apply","trip_biz_uid":980983181541377,"trip_id":1031724831146023,"participate_state":"finished","total_price":244,"female_count":1,"male_count":1,"contact_phone":19999999999,"contact_user_name":"陈宏","refund_account":123456,"refund_account_type":"支付宝","refund_value":100,"finished_state":1},"tirp_data":{"data":{"trip_name":"中秋","start_time":"1481709654000","end_time":"1481882454000","participate_end_time":"1481882454000","trip_tags":["短途游","长线游","同城活动"],"trip_price":"122","participants_male_count":0,"participants_female_count":0,"meeting_place":"所以这破","trip_intro":"婆婆MSN","trip_equipment_intro":"热热热热","other_desc":"热婆婆婆婆","trip_route_type":"徒步","participants_limit_count":"13","trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1031718724239396?imageView2","trip_days":3,"meeting_city":"崇文区","meeting_province":"北京","user_create_group_id":"1481537192707"},"id":1031724831146023,"created_at":1481537192996,"updated_at":1481537236649}},{"created_at":1481081374199,"updated_at":1481091713389,"op_name":"participate","data_id":976687358607389,"uid":1002274307506177,"id":1024077474103301,"op_data":{"contact_user_name":"女女","contact_phone":6766566565,"male_count":1,"female_count":2,"total_price":45000,"participate_state":"finished","trip_id":976687358607389,"trip_biz_uid":976528209936404,"refund_state":"","refund_reason":"","refund_start_time":"","refund_biz_state":"","refund_biz_comment":"","refund_sys_state":"","refund_system_comment":"","refund_finance_state":"","refund_finance_comment":"","biz_cash":0,"sys_cash":0,"user_name":"啊啊啊","trip_name":"北京起止澳大利亚凯恩斯新西兰11日游","trip_start_time":1481639132000,"trip_biz_name":"七月"},"tirp_data":{"data":{"trip_name":"北京起止澳大利亚凯恩斯新西兰11日游","start_time":1481639132000,"end_time":1492157532000,"participate_end_time":1477973532000,"trip_tags":["天天发团"],"trip_price":15000,"participants_male_count":0,"participants_female_count":0,"meeting_place":"北京","trip_intro":"独家赠送6大超值项目：1、 航空公司：2、 升级全程午晚餐3、 大猫号游船豪华自助午餐：4、 赠送\u201c国照\u201d ：5、 新西兰蜂蜜或橄榄茶：6、 境外旅行意外伤害保险：包含7大特色景点：1、 绿岛大堡礁：2、 悉尼港晚餐游船：3、 梦幻世界：4、 SKYPOINT观景台：5、 激浪世界6、 土著毛利文化村：7、 爱歌顿农场：赠送8大优选景点：1、 悉尼歌剧院及海港大桥：2、 悉尼百年纪念公园：3、 悉尼奥林匹克公园： 4、 悉尼大学：5、 昆士兰大学：6、 红树森林： 7、 罗托鲁瓦湖： 8、 弗莱克植物园：","trip_equipment_intro":"请自备常用药品。旅游期间，部分旅游者可能出现水土不服症状，建议预备防肠胃不适、防暑降温、抗感冒、清火、晕车、抗过敏等药物；有慢性疾患的游客,请带好常备药。","other_desc":"不含项目1 护照费用；2 全程小费RMB400/人3 客人在境外一切自愿消费；4 酒店内电话、传真、洗熨、收费电视、饮料、行李搬运、烟酒等费用及其他相关费用；5 单人房差费用：依照旅游业现行作业规定，我社有权依据最终成团人数情况，为避免单男单女住宿的情况而调整房间分配情况（包括规定分开住宿或加床，或家庭成员分开住宿等），如客人执意住在一起而造成我社团队出现的单房差，均由客人自行承担；根据地接社相关规定12岁以上儿童必须占床，12岁以下儿童可以选择是否占床，如不占床，请游客提前说明，具体费用根据所报团队情况而定；6 服务项目未提及的其他的一切费用，例如特种门票（夜总会、博览会、缆车等费用）；7 酒店不提供拖鞋、牙刷等个人卫生贴身物品；8 驾驶员每天平均工作八小时，超过工作时间，会更换司机及用车；","trip_route_type":"摄影","participants_limit_count":200,"trip_cover_pic":"http://c.cncnimg.cn/036/962/c1d1_m.jpg"},"id":976687358607389,"created_at":1478256703280,"updated_at":1478595103146}}]}
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
         * created_at : 1481613071757
         * updated_at : 1482312644923
         * op_name : participate
         * data_id : 1031724831146023
         * uid : 1002274307506177
         * id : 1032997869518904
         * op_data : {"trip_biz_name":"小源源","trip_start_time":"1481709654000","trip_name":"中秋","user_name":"啊啊啊","sys_cash":5.76,"biz_cash":138.24,"refund_finance_comment":"","refund_finance_state":"has_process","refund_system_comment":"退款操作注释","refund_sys_state":"sys_refund_agree","refund_biz_comment":"","refund_biz_state":"","refund_start_time":1481613819912,"refund_reason":"我要退款","refund_state":"refund_apply","trip_biz_uid":980983181541377,"trip_id":1031724831146023,"participate_state":"finished","total_price":244,"female_count":1,"male_count":1,"contact_phone":19999999999,"contact_user_name":"陈宏","refund_account":123456,"refund_account_type":"支付宝","refund_value":100,"finished_state":1}
         * tirp_data : {"data":{"trip_name":"中秋","start_time":"1481709654000","end_time":"1481882454000","participate_end_time":"1481882454000","trip_tags":["短途游","长线游","同城活动"],"trip_price":"122","participants_male_count":0,"participants_female_count":0,"meeting_place":"所以这破","trip_intro":"婆婆MSN","trip_equipment_intro":"热热热热","other_desc":"热婆婆婆婆","trip_route_type":"徒步","participants_limit_count":"13","trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1031718724239396?imageView2","trip_days":3,"meeting_city":"崇文区","meeting_province":"北京","user_create_group_id":"1481537192707"},"id":1031724831146023,"created_at":1481537192996,"updated_at":1481537236649}
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
             * trip_biz_name : 小源源
             * trip_start_time : 1481709654000
             * trip_name : 中秋
             * user_name : 啊啊啊
             * sys_cash : 5.76
             * biz_cash : 138.24
             * refund_finance_comment :
             * refund_finance_state : has_process
             * refund_system_comment : 退款操作注释
             * refund_sys_state : sys_refund_agree
             * refund_biz_comment :
             * refund_biz_state :
             * refund_start_time : 1481613819912
             * refund_reason : 我要退款
             * refund_state : refund_apply
             * trip_biz_uid : 980983181541377
             * trip_id : 1031724831146023
             * participate_state : finished
             * total_price : 244
             * female_count : 1
             * male_count : 1
             * contact_phone : 19999999999
             * contact_user_name : 陈宏
             * refund_account : 123456
             * refund_account_type : 支付宝
             * refund_value : 100
             * finished_state : 1
             */

            private OpDataBean op_data;
            /**
             * data : {"trip_name":"中秋","start_time":"1481709654000","end_time":"1481882454000","participate_end_time":"1481882454000","trip_tags":["短途游","长线游","同城活动"],"trip_price":"122","participants_male_count":0,"participants_female_count":0,"meeting_place":"所以这破","trip_intro":"婆婆MSN","trip_equipment_intro":"热热热热","other_desc":"热婆婆婆婆","trip_route_type":"徒步","participants_limit_count":"13","trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1031718724239396?imageView2","trip_days":3,"meeting_city":"崇文区","meeting_province":"北京","user_create_group_id":"1481537192707"}
             * id : 1031724831146023
             * created_at : 1481537192996
             * updated_at : 1481537236649
             */

            private TirpDataBean trip;

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

            public TirpDataBean getTrip() {
                return trip;
            }

            public void setTrip(TirpDataBean trip) {
                this.trip = trip;
            }

            public static class OpDataBean implements Serializable{
                private String trip_biz_name;
                private String trip_start_time;
                private String trip_name;
                private String user_name;
                private double sys_cash;
                private double biz_cash;
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
                private String refund_account;
                private String refund_account_type;
                private String refund_value;
                private int finished_state;
                private String trip_cover_pic;

                public String getTrip_cover_pic() {
                    return trip_cover_pic;
                }

                public void setTrip_cover_pic(String trip_cover_pic) {
                    this.trip_cover_pic = trip_cover_pic;
                }

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

                public double getSys_cash() {
                    return sys_cash;
                }

                public void setSys_cash(double sys_cash) {
                    this.sys_cash = sys_cash;
                }

                public double getBiz_cash() {
                    return biz_cash;
                }

                public void setBiz_cash(double biz_cash) {
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

                public String getRefund_account() {
                    return refund_account;
                }

                public void setRefund_account(String refund_account) {
                    this.refund_account = refund_account;
                }

                public String getRefund_account_type() {
                    return refund_account_type;
                }

                public void setRefund_account_type(String refund_account_type) {
                    this.refund_account_type = refund_account_type;
                }

                public String getRefund_value() {
                    return refund_value;
                }

                public void setRefund_value(String refund_value) {
                    this.refund_value = refund_value;
                }

                public int getFinished_state() {
                    return finished_state;
                }

                public void setFinished_state(int finished_state) {
                    this.finished_state = finished_state;
                }
            }

            public static class TirpDataBean implements Serializable{
                /**
                 * trip_name : 中秋
                 * start_time : 1481709654000
                 * end_time : 1481882454000
                 * participate_end_time : 1481882454000
                 * trip_tags : ["短途游","长线游","同城活动"]
                 * trip_price : 122
                 * participants_male_count : 0
                 * participants_female_count : 0
                 * meeting_place : 所以这破
                 * trip_intro : 婆婆MSN
                 * trip_equipment_intro : 热热热热
                 * other_desc : 热婆婆婆婆
                 * trip_route_type : 徒步
                 * participants_limit_count : 13
                 * trip_cover_pic : http://ofdx4t772.bkt.clouddn.com/1031718724239396?imageView2
                 * trip_days : 3
                 * meeting_city : 崇文区
                 * meeting_province : 北京
                 * user_create_group_id : 1481537192707
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
                    private String trip_name;
                    private String start_time;
                    private String end_time;
                    private String participate_end_time;
                    private String trip_price;
                    private int participants_male_count;
                    private int participants_female_count;
                    private String meeting_place;
                    private String trip_intro;
                    private String trip_equipment_intro;
                    private String other_desc;
                    private String trip_route_type;
                    private String participants_limit_count;
                    private String trip_cover_pic;
                    private int trip_days;
                    private String meeting_city;
                    private String meeting_province;
                    private String user_create_group_id;
                    private List<String> trip_tags;

                    public String getTrip_name() {
                        return trip_name;
                    }

                    public void setTrip_name(String trip_name) {
                        this.trip_name = trip_name;
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

                    public int getTrip_days() {
                        return trip_days;
                    }

                    public void setTrip_days(int trip_days) {
                        this.trip_days = trip_days;
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
    }
}
