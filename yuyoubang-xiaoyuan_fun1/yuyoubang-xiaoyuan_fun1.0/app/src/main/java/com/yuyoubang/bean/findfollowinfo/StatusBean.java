package com.yuyoubang.bean.findfollowinfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/11/24.
 */

public class StatusBean implements Serializable {

    /**
     * created_at : 1481032382236
     * updated_at : 1481032382236
     * id : 1023255524737143
     * data : {"user_name":"锋在吹","user_location":"北京 昌平区 回龙观龙锦苑四区","location":"北京 昌平区 回龙观龙锦苑四区","pics":["http://ofdx4t772.bkt.clouddn.com/1023255474405493?imageView2","http://ofdx4t772.bkt.clouddn.com/1023255507959926?imageView2"],"content":"包场的感觉，就是不一般","is_liked":0}
     * uid : 982165555838977
     * user : {"user_data":{"recommend_by_sys":0,"state":0,"birthday":"721497600000","is_verification":0,"user_group":"user","gender":"0","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1002723316138012?imageView2","user_name":"锋在吹"},"id":982165555838977,"created_at":1478583229087,"updated_at":1480066231087}
     */

    private long created_at;
    private long updated_at;
    private long id;
    /**
     * user_name : 锋在吹
     * user_location : 北京 昌平区 回龙观龙锦苑四区
     * location : 北京 昌平区 回龙观龙锦苑四区
     * pics : ["http://ofdx4t772.bkt.clouddn.com/1023255474405493?imageView2","http://ofdx4t772.bkt.clouddn.com/1023255507959926?imageView2"]
     * content : 包场的感觉，就是不一般
     * is_liked : 0
     */

    private DataBean data;
    private long uid;
    /**
     * user_data : {"recommend_by_sys":0,"state":0,"birthday":"721497600000","is_verification":0,"user_group":"user","gender":"0","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1002723316138012?imageView2","user_name":"锋在吹"}
     * id : 982165555838977
     * created_at : 1478583229087
     * updated_at : 1480066231087
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class DataBean implements Serializable{
        private String user_name;
        private String user_location;
        private String location;
        private String content;
        private int is_like;
        private List<String> pics;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_location() {
            return user_location;
        }

        public void setUser_location(String user_location) {
            this.user_location = user_location;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }
    }

    public static class UserBean implements Serializable{
        /**
         * recommend_by_sys : 0
         * state : 0
         * birthday : 721497600000
         * is_verification : 0
         * user_group : user
         * gender : 0
         * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1002723316138012?imageView2
         * user_name : 锋在吹
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
            private int recommend_by_sys;
            private int state;
            private long birthday;
            private int is_verification;
            private String user_group;
            private String gender;
            private String profile_pic_url;
            private String user_name;
            private String user_location;
            private String province;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getUser_location() {
                return user_location;
            }

            public void setUser_location(String user_location) {
                this.user_location = user_location;
            }

            public int getRecommend_by_sys() {
                return recommend_by_sys;
            }

            public void setRecommend_by_sys(int recommend_by_sys) {
                this.recommend_by_sys = recommend_by_sys;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public int getIs_verification() {
                return is_verification;
            }

            public void setIs_verification(int is_verification) {
                this.is_verification = is_verification;
            }

            public String getUser_group() {
                return user_group;
            }

            public void setUser_group(String user_group) {
                this.user_group = user_group;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getProfile_pic_url() {
                return profile_pic_url;
            }

            public void setProfile_pic_url(String profile_pic_url) {
                this.profile_pic_url = profile_pic_url;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}

