package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/22.
 */

public class OrderManager implements Serializable{

    /**
     * result : [{"created_at":1482141144942,"updated_at":1482389343799,"op_name":"participate","data_id":976630953607193,"uid":962080376160261,"id":1041857464303668,"op_data":{"finished_state":1,"biz_user_reply":"哈哈哈哈","contact_user_name":"报名用户的真实姓名","contact_phone":"联系电话","male_count":3,"female_count":3,"total_price":600,"participate_state":"confirmed","trip_id":976630953607193,"trip_biz_uid":962033467064324,"refund_state":"","refund_reason":"","refund_start_time":"","refund_biz_state":"biz_refund_refuse","refund_biz_comment":"商家拒绝用户退款的注释","refund_sys_state":"","refund_system_comment":"","refund_finance_state":"","refund_finance_comment":"","biz_cash":576,"sys_cash":24,"user_name":"测试用户001","trip_name":"聆听精灵岛西追逐海豚之旅","trip_start_time":1482639132000,"trip_biz_name":"【修改】陈鑫12"}}]
     * error_code : 0
     */

    private int error_code;
    /**
     * created_at : 1482141144942
     * updated_at : 1482389343799
     * op_name : participate
     * data_id : 976630953607193
     * uid : 962080376160261
     * id : 1041857464303668
     * op_data : {"finished_state":1,"biz_user_reply":"哈哈哈哈","contact_user_name":"报名用户的真实姓名","contact_phone":"联系电话","male_count":3,"female_count":3,"total_price":600,"participate_state":"confirmed","trip_id":976630953607193,"trip_biz_uid":962033467064324,"refund_state":"","refund_reason":"","refund_start_time":"","refund_biz_state":"biz_refund_refuse","refund_biz_comment":"商家拒绝用户退款的注释","refund_sys_state":"","refund_system_comment":"","refund_finance_state":"","refund_finance_comment":"","biz_cash":576,"sys_cash":24,"user_name":"测试用户001","trip_name":"聆听精灵岛西追逐海豚之旅","trip_start_time":1482639132000,"trip_biz_name":"【修改】陈鑫12"}
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
        private String op_name;
        private long data_id;
        private long uid;
        private long id;
        /**
         * finished_state : 1
         * biz_user_reply : 哈哈哈哈
         * contact_user_name : 报名用户的真实姓名
         * contact_phone : 联系电话
         * male_count : 3
         * female_count : 3
         * total_price : 600
         * participate_state : confirmed
         * trip_id : 976630953607193
         * trip_biz_uid : 962033467064324
         * refund_state :
         * refund_reason :
         * refund_start_time :
         * refund_biz_state : biz_refund_refuse
         * refund_biz_comment : 商家拒绝用户退款的注释
         * refund_sys_state :
         * refund_system_comment :
         * refund_finance_state :
         * refund_finance_comment :
         * biz_cash : 576
         * sys_cash : 24
         * user_name : 测试用户001
         * trip_name : 聆听精灵岛西追逐海豚之旅
         * trip_start_time : 1482639132000
         * trip_biz_name : 【修改】陈鑫12
         */

        private OpDataBean op_data;

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

        public static class OpDataBean implements Serializable{
            private int finished_state;
            private String biz_user_reply;
            private String contact_user_name;
            private String contact_phone;
            private int male_count;
            private int female_count;
            private int total_price;
            private String participate_state;
            private long trip_id;
            private long trip_biz_uid;
            private String refund_state;
            private String refund_reason;
            private String refund_start_time;
            private String refund_biz_state;
            private String refund_biz_comment;
            private String refund_sys_state;
            private String refund_system_comment;
            private String refund_finance_state;
            private String refund_finance_comment;
            private int biz_cash;
            private int sys_cash;
            private String user_name;
            private String trip_name;
            private long trip_start_time;
            private String trip_biz_name;

            public int getFinished_state() {
                return finished_state;
            }

            public void setFinished_state(int finished_state) {
                this.finished_state = finished_state;
            }

            public String getBiz_user_reply() {
                return biz_user_reply;
            }

            public void setBiz_user_reply(String biz_user_reply) {
                this.biz_user_reply = biz_user_reply;
            }

            public String getContact_user_name() {
                return contact_user_name;
            }

            public void setContact_user_name(String contact_user_name) {
                this.contact_user_name = contact_user_name;
            }

            public String getContact_phone() {
                return contact_phone;
            }

            public void setContact_phone(String contact_phone) {
                this.contact_phone = contact_phone;
            }

            public int getMale_count() {
                return male_count;
            }

            public void setMale_count(int male_count) {
                this.male_count = male_count;
            }

            public int getFemale_count() {
                return female_count;
            }

            public void setFemale_count(int female_count) {
                this.female_count = female_count;
            }

            public int getTotal_price() {
                return total_price;
            }

            public void setTotal_price(int total_price) {
                this.total_price = total_price;
            }

            public String getParticipate_state() {
                return participate_state;
            }

            public void setParticipate_state(String participate_state) {
                this.participate_state = participate_state;
            }

            public long getTrip_id() {
                return trip_id;
            }

            public void setTrip_id(long trip_id) {
                this.trip_id = trip_id;
            }

            public long getTrip_biz_uid() {
                return trip_biz_uid;
            }

            public void setTrip_biz_uid(long trip_biz_uid) {
                this.trip_biz_uid = trip_biz_uid;
            }

            public String getRefund_state() {
                return refund_state;
            }

            public void setRefund_state(String refund_state) {
                this.refund_state = refund_state;
            }

            public String getRefund_reason() {
                return refund_reason;
            }

            public void setRefund_reason(String refund_reason) {
                this.refund_reason = refund_reason;
            }

            public String getRefund_start_time() {
                return refund_start_time;
            }

            public void setRefund_start_time(String refund_start_time) {
                this.refund_start_time = refund_start_time;
            }

            public String getRefund_biz_state() {
                return refund_biz_state;
            }

            public void setRefund_biz_state(String refund_biz_state) {
                this.refund_biz_state = refund_biz_state;
            }

            public String getRefund_biz_comment() {
                return refund_biz_comment;
            }

            public void setRefund_biz_comment(String refund_biz_comment) {
                this.refund_biz_comment = refund_biz_comment;
            }

            public String getRefund_sys_state() {
                return refund_sys_state;
            }

            public void setRefund_sys_state(String refund_sys_state) {
                this.refund_sys_state = refund_sys_state;
            }

            public String getRefund_system_comment() {
                return refund_system_comment;
            }

            public void setRefund_system_comment(String refund_system_comment) {
                this.refund_system_comment = refund_system_comment;
            }

            public String getRefund_finance_state() {
                return refund_finance_state;
            }

            public void setRefund_finance_state(String refund_finance_state) {
                this.refund_finance_state = refund_finance_state;
            }

            public String getRefund_finance_comment() {
                return refund_finance_comment;
            }

            public void setRefund_finance_comment(String refund_finance_comment) {
                this.refund_finance_comment = refund_finance_comment;
            }

            public int getBiz_cash() {
                return biz_cash;
            }

            public void setBiz_cash(int biz_cash) {
                this.biz_cash = biz_cash;
            }

            public int getSys_cash() {
                return sys_cash;
            }

            public void setSys_cash(int sys_cash) {
                this.sys_cash = sys_cash;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getTrip_name() {
                return trip_name;
            }

            public void setTrip_name(String trip_name) {
                this.trip_name = trip_name;
            }

            public long getTrip_start_time() {
                return trip_start_time;
            }

            public void setTrip_start_time(long trip_start_time) {
                this.trip_start_time = trip_start_time;
            }

            public String getTrip_biz_name() {
                return trip_biz_name;
            }

            public void setTrip_biz_name(String trip_biz_name) {
                this.trip_biz_name = trip_biz_name;
            }
        }
    }
}
