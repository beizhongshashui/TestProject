package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public class SystemTripBean implements Serializable {


    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1477386887421,"updated_at":1477386887421,"id":962094267695111,"data":{"trip_route_type":"休闲"},"uid":962029406978050},{"created_at":1477386914456,"updated_at":1477386914456,"id":962094720679944,"data":{"trip_route_type":"徒步"},"uid":962029406978050},{"created_at":1477387176866,"updated_at":1477387176866,"id":962099116310537,"data":{"trip_route_type":"摄影"},"uid":962029406978050},{"created_at":1477387186602,"updated_at":1477387186602,"id":962099284082698,"data":{"trip_route_type":"登山"},"uid":962029406978050},{"created_at":1477387193941,"updated_at":1477387193941,"id":962099401523211,"data":{"trip_route_type":"骑行"},"uid":962029406978050},{"created_at":1477387201628,"updated_at":1477387201628,"id":962099535740940,"data":{"trip_route_type":"烧烤"},"uid":962029406978050},{"created_at":1477387208955,"updated_at":1477387208955,"id":962099653181453,"data":{"trip_route_type":"漂流"},"uid":962029406978050},{"created_at":1477387215276,"updated_at":1477387215276,"id":962099770621966,"data":{"trip_route_type":"温泉"},"uid":962029406978050},{"created_at":1477387221389,"updated_at":1477387221389,"id":962099871285263,"data":{"trip_route_type":"采摘"},"uid":962029406978050},{"created_at":1477387228426,"updated_at":1477387228426,"id":962099988725776,"data":{"trip_route_type":"农家乐"},"uid":962029406978050},{"created_at":1477387234469,"updated_at":1477387234469,"id":962100089389073,"data":{"trip_route_type":"综合"},"uid":962029406978050},{"created_at":1477387240284,"updated_at":1477387240284,"id":962100190052370,"data":{"trip_route_type":"公益"},"uid":962029406978050},{"created_at":1477387247412,"updated_at":1477387247412,"id":962100307492883,"data":{"trip_route_type":"其他"},"uid":962029406978050}]}
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

    public static class ResultBean  implements Serializable{
        /**
         * created_at : 1477386887421
         * updated_at : 1477386887421
         * id : 962094267695111
         * data : {"trip_route_type":"休闲"}
         * uid : 962029406978050
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean  implements Serializable{
            private long created_at;
            private long updated_at;
            private long id;

            /**
             * trip_route_type : 休闲
             */

            private DataBean data;
            private long uid;

            public int getIsChoose() {
                return isChoose;
            }

            public void setIsChoose(int isChoose) {
                this.isChoose = isChoose;
            }

            private int isChoose;

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

            public static class DataBean  implements Serializable{
                private String trip_route_type;
                private int selector;

                public int getSelector() {
                    return selector;
                }

                public void setSelector(int selector) {
                    this.selector = selector;
                }

                public String getTrip_route_type() {
                    return trip_route_type;
                }

                public void setTrip_route_type(String trip_route_type) {
                    this.trip_route_type = trip_route_type;
                }
            }
        }
    }
}
