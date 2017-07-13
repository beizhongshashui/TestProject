package com.yuyoubang.bean;

import com.yuyoubang.bean.findfollowinfo.CmtListBean;
import com.yuyoubang.bean.findfollowinfo.LikeListBean;
import com.yuyoubang.bean.findfollowinfo.StatusBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaoyuan on 16/11/21.
 */
public class FindFollowInfoBean implements Serializable {

    /**
     * status : [{"created_at":1479816201883,"updated_at":1479884235187,"id":1002851393404971,"data":{"user_name":"陈鑫陈鑫","user_location":"北京","location":"南京夫子庙","pics":["111","222","333","444","555","666","777","888","999"],"content":123456789,"like_comment_total_count":14,"is_liked":0},"uid":995062101114881}]
     * like_list : [{"created_at":1479817422593,"updated_at":1479817422593,"op_name":"status_like","data_id":1002851393404971,"uid":995062101114881,"id":1002871878385716,"op_data":{},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}}]
     * cmt_list : [{"created_at":1479884235112,"updated_at":1479884235112,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1003992814518281,"op_data":{"comment":"哦JOJO"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479883629785,"updated_at":1479883629785,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1003982647525383,"op_data":{"comment":"明"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479883455893,"updated_at":1479883455893,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1003979728289795,"op_data":{"comment":"哦pool"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479819371457,"updated_at":1479819371457,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1002904577179708,"op_data":{"comment":"AAAAAAAAAAAAAAAAAAAAAAAAA"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479819074058,"updated_at":1479819074058,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1002899594346555,"op_data":{"comment":"AAAAAAAAAAAAAAAAAAAAAAAAA"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479819061082,"updated_at":1479819061082,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1002899376242746,"op_data":{"comment":"ppppppppppppppppppppp"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479817475211,"updated_at":1479817475211,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1002872767578166,"op_data":{"comment":"你的动态很好看"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}}]
     * status_like_total_count : 1
     * status_comment_total_count : 7
     */

    private ResultBean result;
    /**
     * result : {"status":[{"created_at":1479816201883,"updated_at":1479884235187,"id":1002851393404971,"data":{"user_name":"陈鑫陈鑫","user_location":"北京","location":"南京夫子庙","pics":["111","222","333","444","555","666","777","888","999"],"content":123456789,"like_comment_total_count":14,"is_liked":0},"uid":995062101114881}],"like_list":[{"created_at":1479817422593,"updated_at":1479817422593,"op_name":"status_like","data_id":1002851393404971,"uid":995062101114881,"id":1002871878385716,"op_data":{},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}}],"cmt_list":[{"created_at":1479884235112,"updated_at":1479884235112,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1003992814518281,"op_data":{"comment":"哦JOJO"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479883629785,"updated_at":1479883629785,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1003982647525383,"op_data":{"comment":"明"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479883455893,"updated_at":1479883455893,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1003979728289795,"op_data":{"comment":"哦pool"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479819371457,"updated_at":1479819371457,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1002904577179708,"op_data":{"comment":"AAAAAAAAAAAAAAAAAAAAAAAAA"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479819074058,"updated_at":1479819074058,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1002899594346555,"op_data":{"comment":"AAAAAAAAAAAAAAAAAAAAAAAAA"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479819061082,"updated_at":1479819061082,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1002899376242746,"op_data":{"comment":"ppppppppppppppppppppp"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}},{"created_at":1479817475211,"updated_at":1479817475211,"op_name":"status_comment","data_id":1002851393404971,"uid":995062101114881,"id":1002872767578166,"op_data":{"comment":"你的动态很好看"},"user":{"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}}],"status_like_total_count":1,"status_comment_total_count":7}
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
        private int status_like_total_count;
        private int status_comment_total_count;
        /**
         * created_at : 1479816201883
         * updated_at : 1479884235187
         * id : 1002851393404971
         * data : {"user_name":"陈鑫陈鑫","user_location":"北京","location":"南京夫子庙","pics":["111","222","333","444","555","666","777","888","999"],"content":123456789,"like_comment_total_count":14,"is_liked":0}
         * uid : 995062101114881
         */

        private List<StatusBean> status;
        /**
         * created_at : 1479817422593
         * updated_at : 1479817422593
         * op_name : status_like
         * data_id : 1002851393404971
         * uid : 995062101114881
         * id : 1002871878385716
         * op_data : {}
         * user : {"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}
         */

        private List<LikeListBean> like_list;
        /**
         * created_at : 1479884235112
         * updated_at : 1479884235112
         * op_name : status_comment
         * data_id : 1002851393404971
         * uid : 995062101114881
         * id : 1003992814518281
         * op_data : {"comment":"哦JOJO"}
         * user : {"user_data":{"user_name":"陈鑫陈鑫","gender":"1","user_location":"北京北京"},"id":995062101114881,"created_at":1479351923757,"updated_at":1479802505193}
         */

        private List<CmtListBean> cmt_list;

        public int getStatus_like_total_count() {
            return status_like_total_count;
        }

        public void setStatus_like_total_count(int status_like_total_count) {
            this.status_like_total_count = status_like_total_count;
        }

        public int getStatus_comment_total_count() {
            return status_comment_total_count;
        }

        public void setStatus_comment_total_count(int status_comment_total_count) {
            this.status_comment_total_count = status_comment_total_count;
        }

        public List<StatusBean> getStatus() {
            return status;
        }

        public void setStatus(List<StatusBean> status) {
            this.status = status;
        }

        public List<LikeListBean> getLike_list() {
            return like_list;
        }

        public void setLike_list(List<LikeListBean> like_list) {
            this.like_list = like_list;
        }

        public List<CmtListBean> getCmt_list() {
            return cmt_list;
        }

        public void setCmt_list(List<CmtListBean> cmt_list) {
            this.cmt_list = cmt_list;
        }

    }
}
