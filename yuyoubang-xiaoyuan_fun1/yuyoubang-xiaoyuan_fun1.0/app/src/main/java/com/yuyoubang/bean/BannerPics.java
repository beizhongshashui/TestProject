package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/16.
 */

public class BannerPics implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481177922448,"updated_at":1481281457760,"id":1025697280753692,"data":{"index":1,"banner_jump_url":"www.baidu.com","banner_pic_url":"http://www.ctps.cn/PhotoNet/Profiles2011/20140725/201472584451123.jpg","title_name":"第一张轮播图"},"uid":962029406978050},{"created_at":1481177936830,"updated_at":1481281483427,"id":1025697515634717,"data":{"title_name":"第二章轮播图","banner_pic_url":"http://p.chanyouji.cn/122531/1400176231050p18o0g3dfj1gpjsav1iuccq91pjr3.jpg","banner_jump_url":"www.baidu.com","index":2},"uid":962029406978050},{"created_at":1481178074110,"updated_at":1481281508648,"id":1025699830890526,"data":{"title_name":"第三张轮播图","banner_pic_url":"http://img1.gtimg.com/ninja/1/2015/12/ninja145153529280176.jpg","banner_jump_url":"www.baidu.com","index":3},"uid":962029406978050}]}
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
         * created_at : 1481177922448
         * updated_at : 1481281457760
         * id : 1025697280753692
         * data : {"index":1,"banner_jump_url":"www.baidu.com","banner_pic_url":"http://www.ctps.cn/PhotoNet/Profiles2011/20140725/201472584451123.jpg","title_name":"第一张轮播图"}
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
             * index : 1
             * banner_jump_url : www.baidu.com
             * banner_pic_url : http://www.ctps.cn/PhotoNet/Profiles2011/20140725/201472584451123.jpg
             * title_name : 第一张轮播图
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
                private String index;
                private String banner_jump_url;
                private String banner_pic_url;
                private String title_name;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getBanner_jump_url() {
                    return banner_jump_url;
                }

                public void setBanner_jump_url(String banner_jump_url) {
                    this.banner_jump_url = banner_jump_url;
                }

                public String getBanner_pic_url() {
                    return banner_pic_url;
                }

                public void setBanner_pic_url(String banner_pic_url) {
                    this.banner_pic_url = banner_pic_url;
                }

                public String getTitle_name() {
                    return title_name;
                }

                public void setTitle_name(String title_name) {
                    this.title_name = title_name;
                }
            }
        }
    }
}
