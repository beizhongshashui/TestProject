package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/12/5.
 */
public class Order implements Serializable {

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1480593647581,"updated_at":1480593647581,"op_name":"participate","data_id":996909708804102,"uid":962033467064324,"id":1015894772875281,"op_data":{"trip_start_time":"1481515932000","trip_name":"滕州三日游","trip_biz_name":"【修改】陈鑫12","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":"962033467064324","trip_id":"996909708804102","participate_state":"book","total_price":"200","female_count":"10","male_count":"12","contact_phone":"18622881126","contact_user_name":"xiaoyua "},"trip":{"data":{"trip_name":"滕州三日游","start_time":"1481515932000","end_time":"1481515932000"},"id":996909708804102,"created_at":1479462049781,"updated_at":1479462493889}}]}
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

    public static class ResultBean implements Serializable {
        /**
         * created_at : 1480593647581
         * updated_at : 1480593647581
         * op_name : participate
         * data_id : 996909708804102
         * uid : 962033467064324
         * id : 1015894772875281
         * op_data : {"trip_start_time":"1481515932000","trip_name":"滕州三日游","trip_biz_name":"【修改】陈鑫12","sys_cash":0,"biz_cash":0,"refund_finance_comment":"","refund_finance_state":"","refund_system_comment":"","refund_sys_state":"","refund_biz_comment":"","refund_biz_state":"","refund_start_time":"","refund_reason":"","refund_state":"","trip_biz_uid":"962033467064324","trip_id":"996909708804102","participate_state":"book","total_price":"200","female_count":"10","male_count":"12","contact_phone":"18622881126","contact_user_name":"xiaoyua "}
         * trip : {"data":{"trip_name":"滕州三日游","start_time":"1481515932000","end_time":"1481515932000"},"id":996909708804102,"created_at":1479462049781,"updated_at":1479462493889}
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
             * trip_start_time : 1481515932000
             * trip_name : 滕州三日游
             * trip_biz_name : 【修改】陈鑫12
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
             * trip_biz_uid : 962033467064324
             * trip_id : 996909708804102
             * participate_state : book
             * total_price : 200
             * female_count : 10
             * male_count : 12
             * contact_phone : 18622881126
             * contact_user_name : xiaoyua
             */

            private OpDataBean op_data;
            /**
             * data : {"trip_name":"滕州三日游","start_time":"1481515932000","end_time":"1481515932000"}
             * id : 996909708804102
             * created_at : 1479462049781
             * updated_at : 1479462493889
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
                private String trip_start_time;
                private String trip_name;
                private String trip_biz_name;
                private String sys_cash;
                private String biz_cash;
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
                private String trip_comment_state;

                public String getTrip_comment_state() {
                    return trip_comment_state;
                }

                public void setTrip_comment_state(String trip_comment_state) {
                    this.trip_comment_state = trip_comment_state;
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

                public String getTrip_biz_name() {
                    return trip_biz_name;
                }

                public void setTrip_biz_name(String trip_biz_name) {
                    this.trip_biz_name = trip_biz_name;
                }

                public String getSys_cash() {
                    return sys_cash;
                }

                public void setSys_cash(String sys_cash) {
                    this.sys_cash = sys_cash;
                }

                public String getBiz_cash() {
                    return biz_cash;
                }

                public void setBiz_cash(String biz_cash) {
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
                 * trip_name : 滕州三日游
                 * start_time : 1481515932000
                 * end_time : 1481515932000
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
                    private String trip_price;
                    private String trip_cover_pic;

                    public String getTrip_cover_pic() {
                        return trip_cover_pic;
                    }

                    public void setTrip_cover_pic(String trip_cover_pic) {
                        this.trip_cover_pic = trip_cover_pic;
                    }

                    public String getTrip_price() {
                        return trip_price;
                    }

                    public void setTrip_price(String trip_price) {
                        this.trip_price = trip_price;
                    }

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
                }
            }
        }
    }
}
