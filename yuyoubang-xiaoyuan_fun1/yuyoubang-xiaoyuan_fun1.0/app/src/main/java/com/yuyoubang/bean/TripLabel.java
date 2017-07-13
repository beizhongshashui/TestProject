package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/12/6.
 */
public class TripLabel implements Serializable {


    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1477466866017,"updated_at":1477466866017,"id":963436092653570,"data":{"tag_name":"旅行"},"uid":962029406978050},{"created_at":1477466891678,"updated_at":1477466891678,"id":963436512083971,"data":{"tag_name":"户外"},"uid":962029406978050},{"created_at":1477466902704,"updated_at":1477466902704,"id":963436696633348,"data":{"tag_name":"摄影"},"uid":962029406978050},{"created_at":1477466928210,"updated_at":1477466928210,"id":963437132840965,"data":{"tag_name":"自驾"},"uid":962029406978050},{"created_at":1477467024980,"updated_at":1477467024980,"id":963438743453702,"data":{"tag_name":"美食"},"uid":962029406978050},{"created_at":1477467034558,"updated_at":1477467034558,"id":963438911225863,"data":{"tag_name":"文艺"},"uid":962029406978050},{"created_at":1477467044984,"updated_at":1477467044984,"id":963439078998024,"data":{"tag_name":"时尚"},"uid":962029406978050},{"created_at":1477467053936,"updated_at":1477467053936,"id":963439229992969,"data":{"tag_name":"大叔"},"uid":962029406978050},{"created_at":1477467064073,"updated_at":1477467064073,"id":963439414542346,"data":{"tag_name":"萝莉"},"uid":962029406978050},{"created_at":1477467073054,"updated_at":1477467073054,"id":963439565537291,"data":{"tag_name":"码农"},"uid":962029406978050},{"created_at":1477467081035,"updated_at":1477467081035,"id":963439699755020,"data":{"tag_name":"金融"},"uid":962029406978050},{"created_at":1477467088538,"updated_at":1477467088538,"id":963439817195533,"data":{"tag_name":"个性"},"uid":962029406978050},{"created_at":1477467099616,"updated_at":1477467099616,"id":963440001744910,"data":{"tag_name":"孤独"},"uid":962029406978050},{"created_at":1477467107888,"updated_at":1477467107888,"id":963440135962639,"data":{"tag_name":"伤感"},"uid":962029406978050},{"created_at":1477467115959,"updated_at":1477467115959,"id":963440270180368,"data":{"tag_name":"青春"},"uid":962029406978050},{"created_at":1477467122795,"updated_at":1477467122795,"id":963440387620881,"data":{"tag_name":"搞笑"},"uid":962029406978050},{"created_at":1477467130301,"updated_at":1477467130301,"id":963440521838610,"data":{"tag_name":"可爱"},"uid":962029406978050},{"created_at":1477467138823,"updated_at":1477467138823,"id":963440656056339,"data":{"tag_name":"回忆"},"uid":962029406978050},{"created_at":1477467146515,"updated_at":1477467146515,"id":963440790274068,"data":{"tag_name":"自拍"},"uid":962029406978050},{"created_at":1477467154903,"updated_at":1477467154903,"id":963440924491797,"data":{"tag_name":"美女"},"uid":962029406978050},{"created_at":1477467162080,"updated_at":1477467162080,"id":963441058709526,"data":{"tag_name":"帅气"},"uid":962029406978050},{"created_at":1477467170643,"updated_at":1477467170643,"id":963441192927255,"data":{"tag_name":"潮流"},"uid":962029406978050},{"created_at":1477467177910,"updated_at":1477467177910,"id":963441310367768,"data":{"tag_name":"阳光"},"uid":962029406978050},{"created_at":1477467185717,"updated_at":1477467185717,"id":963441444585497,"data":{"tag_name":"另类"},"uid":962029406978050}]}
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
         * created_at : 1477466866017
         * updated_at : 1477466866017
         * id : 963436092653570
         * data : {"tag_name":"旅行"}
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
             * tag_name : 旅行
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

            public static class DataBean implements Serializable{
                private String tag_name;

                public String getTag_name() {
                    return tag_name;
                }

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }
            }
        }
    }
}
