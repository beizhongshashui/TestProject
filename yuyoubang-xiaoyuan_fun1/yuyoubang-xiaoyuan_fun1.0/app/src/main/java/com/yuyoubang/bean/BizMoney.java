package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 17/1/4.
 */

public class BizMoney implements Serializable{

    private ResultBean result;

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
         * created_at : 1482409503193
         * updated_at : 1482409503193
         * account_name : biz_cash_account
         * uid : 1002274307506177
         * id : 1046359781212186
         * value : 0
         */

        private AccountInfoBean account_info;
        private int total_count;
        /**
         * created_at : 1483502298811
         * updated_at : 1483502298811
         * timestamp : 1483502298811
         * op_type : decr
         * op_name : biz_take_cash
         * account_name : biz_cash_account
         * op_uid : 962033467064324
         * uid : 962033467064324
         * id : 1064693838970899
         * account_value : 30812.479999999996
         * value : -99
         * op_data : {"state":0,"sys_cash":0,"biz_cash":-99,"deposit_cash":0,"trip_id":0,"biz_uid":962033467064324,"trip_participate_id":0,"person_id_number":"110101010101010","account_name":"陈鑫","card_id":"998 888 3333","bank_name":"招商银行","user_name":"【修改】陈鑫12","uid":962033467064324}
         */

        private List<ListBean> list;

        public AccountInfoBean getAccount_info() {
            return account_info;
        }

        public void setAccount_info(AccountInfoBean account_info) {
            this.account_info = account_info;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AccountInfoBean implements Serializable{
            private long created_at;
            private long updated_at;
            private String account_name;
            private long uid;
            private long id;
            private String value;

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

            public String getAccount_name() {
                return account_name;
            }

            public void setAccount_name(String account_name) {
                this.account_name = account_name;
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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class ListBean implements Serializable{
            private long created_at;
            private long updated_at;
            private long timestamp;
            private String op_type;
            private String op_name;
            private String account_name;
            private long op_uid;
            private long uid;
            private long id;
            private double account_value;
            private String value;
            /**
             * state : 0
             * sys_cash : 0
             * biz_cash : -99
             * deposit_cash : 0
             * trip_id : 0
             * biz_uid : 962033467064324
             * trip_participate_id : 0
             * person_id_number : 110101010101010
             * account_name : 陈鑫
             * card_id : 998 888 3333
             * bank_name : 招商银行
             * user_name : 【修改】陈鑫12
             * uid : 962033467064324
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

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public String getOp_type() {
                return op_type;
            }

            public void setOp_type(String op_type) {
                this.op_type = op_type;
            }

            public String getOp_name() {
                return op_name;
            }

            public void setOp_name(String op_name) {
                this.op_name = op_name;
            }

            public String getAccount_name() {
                return account_name;
            }

            public void setAccount_name(String account_name) {
                this.account_name = account_name;
            }

            public long getOp_uid() {
                return op_uid;
            }

            public void setOp_uid(long op_uid) {
                this.op_uid = op_uid;
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

            public double getAccount_value() {
                return account_value;
            }

            public void setAccount_value(double account_value) {
                this.account_value = account_value;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public OpDataBean getOp_data() {
                return op_data;
            }

            public void setOp_data(OpDataBean op_data) {
                this.op_data = op_data;
            }

            public static class OpDataBean implements Serializable{
                private String state;
                private String sys_cash;
                private String biz_cash;
                private String deposit_cash;
                private String trip_id;
                private String biz_uid;
                private String trip_participate_id;
                private String person_id_number;
                private String account_name;
                private String card_id;
                private String bank_name;
                private String user_name;
                private String uid;

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
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

                public String getDeposit_cash() {
                    return deposit_cash;
                }

                public void setDeposit_cash(String deposit_cash) {
                    this.deposit_cash = deposit_cash;
                }

                public String getTrip_id() {
                    return trip_id;
                }

                public void setTrip_id(String trip_id) {
                    this.trip_id = trip_id;
                }

                public String getBiz_uid() {
                    return biz_uid;
                }

                public void setBiz_uid(String biz_uid) {
                    this.biz_uid = biz_uid;
                }

                public String getTrip_participate_id() {
                    return trip_participate_id;
                }

                public void setTrip_participate_id(String trip_participate_id) {
                    this.trip_participate_id = trip_participate_id;
                }

                public String getPerson_id_number() {
                    return person_id_number;
                }

                public void setPerson_id_number(String person_id_number) {
                    this.person_id_number = person_id_number;
                }

                public String getAccount_name() {
                    return account_name;
                }

                public void setAccount_name(String account_name) {
                    this.account_name = account_name;
                }

                public String getCard_id() {
                    return card_id;
                }

                public void setCard_id(String card_id) {
                    this.card_id = card_id;
                }

                public String getBank_name() {
                    return bank_name;
                }

                public void setBank_name(String bank_name) {
                    this.bank_name = bank_name;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }
            }
        }
    }
}
