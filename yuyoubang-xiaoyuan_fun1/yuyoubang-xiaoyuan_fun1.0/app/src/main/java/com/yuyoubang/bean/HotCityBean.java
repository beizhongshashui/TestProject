package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/12/2.
 */
public class HotCityBean implements Serializable {

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1477471407764,"updated_at":1477471407764,"id":963512277991467,"data":{"city_name":"北京"},"uid":962029406978050},{"created_at":1477471412861,"updated_at":1477471412861,"id":963512361877548,"data":{"city_name":"上海"},"uid":962029406978050},{"created_at":1477471418028,"updated_at":1477471418028,"id":963512462540845,"data":{"city_name":"广州"},"uid":962029406978050},{"created_at":1477471424042,"updated_at":1477471424042,"id":963512563204142,"data":{"city_name":"深圳"},"uid":962029406978050},{"created_at":1477471430231,"updated_at":1477471430231,"id":963512663867439,"data":{"city_name":"杭州"},"uid":962029406978050},{"created_at":1477471435963,"updated_at":1477471435963,"id":963512747753520,"data":{"city_name":"成都"},"uid":962029406978050}]}
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
         * created_at : 1477471407764
         * updated_at : 1477471407764
         * id : 963512277991467
         * data : {"city_name":"北京"}
         * uid : 962029406978050
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
            private long id;
            /**
             * city_name : 北京
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

            public static class DataBean implements Serializable {
                private String city_name;

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }
            }
        }
    }
}
