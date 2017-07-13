package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 17/1/3.
 */

public class ChooseStatusResult implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1483004617147,"updated_at":1483004617147,"id":1056344137334963,"data":{"user_name":"大头君","state":0,"user_location":"北京","location":"星巴克咖啡(北京望京鹏景阁店)","pics":["http://ofdx4t772.bkt.clouddn.com/1056344120557746?imageView2"],"content":"哈哈","like_count":0,"comment_count":0},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1483004588152,"updated_at":1483007976502,"id":1056343650795697,"data":{"user_name":"大头君","state":0,"user_location":"北京","location":"星巴克咖啡(北京望京鹏景阁店)","content":"嘎嘎","like_comment_total_count":2,"like_count":1,"comment_count":1},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1483004242611,"updated_at":1483004242611,"id":1056337845878960,"data":{"user_name":"大头君","state":0,"user_location":"北京","location":"星巴克咖啡(北京望京鹏景阁店)","pics":["http://ofdx4t772.bkt.clouddn.com/1056337728438446?imageView2","http://ofdx4t772.bkt.clouddn.com/1056337845878959?imageView2"],"content":"哈喽","like_count":0,"comment_count":0},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1483004048471,"updated_at":1483004048471,"id":1056334591099053,"data":{"user_name":"大头君","state":0,"user_location":"北京","location":"鹏景阁大厦","pics":["http://ofdx4t772.bkt.clouddn.com/1056334440104107?imageView2","http://ofdx4t772.bkt.clouddn.com/1056334574321836?imageView2"],"content":"动态和图片","like_count":0,"comment_count":0},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1483004009791,"updated_at":1483004009791,"id":1056333936787626,"data":{"user_name":"大头君","state":0,"user_location":"北京","location":"鹏景阁大厦","content":"这是一条动态","like_count":0,"comment_count":0},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1483003392463,"updated_at":1483003418802,"id":1056323585245347,"data":{"like_comment_total_count":2,"content":"哈哈","pics":[],"location":"","user_location":"北京","state":0,"user_name":"大头君","like_count":0,"comment_count":1},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1482999501720,"updated_at":1483086689945,"id":1056258305097870,"data":{"user_name":"大头君","state":0,"user_location":"北京","location":"北京市朝阳区星巴克咖啡(北京望京鹏景阁店)","pics":["http://ofdx4t772.bkt.clouddn.com/1056258305097869?imageView2"],"content":"此刻有什么想法没啊","like_comment_total_count":2,"like_count":1,"comment_count":1},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1482998663097,"updated_at":1482998663097,"id":1056244245790860,"data":{"user_name":"大头君","state":0,"user_location":"北京","location":"星巴克咖啡(北京望京鹏景阁店)","pics":["http://ofdx4t772.bkt.clouddn.com/1056244229013643?imageView2"],"content":"哈哈","like_count":0,"comment_count":0},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1482993029106,"updated_at":1482993029106,"id":1056149722955908,"data":{"user_name":"大头君","state":0,"user_location":"北京","location":"北京市朝阳区广顺北大街靠近中国银行(北京望京科技园支行)","pics":["http://ofdx4t772.bkt.clouddn.com/1056149639069819?imageView2","http://ofdx4t772.bkt.clouddn.com/1056149655847036?imageView2","http://ofdx4t772.bkt.clouddn.com/1056149672624253?imageView2","http://ofdx4t772.bkt.clouddn.com/1056149672624254?imageView2","http://ofdx4t772.bkt.clouddn.com/1056149672624255?imageView2","http://ofdx4t772.bkt.clouddn.com/1056149689401472?imageView2","http://ofdx4t772.bkt.clouddn.com/1056149689401473?imageView2","http://ofdx4t772.bkt.clouddn.com/1056149689401474?imageView2","http://ofdx4t772.bkt.clouddn.com/1056149706178691?imageView2"],"content":"这是一条动态","like_count":0,"comment_count":0},"uid":1056003526295659,"status":"status","user":{"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}},{"created_at":1483007971073,"updated_at":1483007971073,"id":1056400408117508,"data":{"user_name":"Tina","state":0,"user_location":"蒋忠洞王猪蹄包肉(望京西园店)","location":"蒋忠洞王猪蹄包肉(望京西园店)","pics":["http://ofdx4t772.bkt.clouddn.com/1056400072573179?imageView2","http://ofdx4t772.bkt.clouddn.com/1056400122904828?imageView2","http://ofdx4t772.bkt.clouddn.com/1056400156459261?imageView2","http://ofdx4t772.bkt.clouddn.com/1056400206790910?imageView2","http://ofdx4t772.bkt.clouddn.com/1056400240345343?imageView2","http://ofdx4t772.bkt.clouddn.com/1056400273899776?imageView2","http://ofdx4t772.bkt.clouddn.com/1056400324231425?imageView2","http://ofdx4t772.bkt.clouddn.com/1056400357785858?imageView2","http://ofdx4t772.bkt.clouddn.com/1056400391340291?imageView2"],"content":"秦 淮","like_count":0,"comment_count":0},"uid":1053033187311618,"status":"status","user":{"user_data":{"user_name":"Tina","gender":1,"state":0,"is_verification":0,"user_group":"user","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1053078183804957?imageView2","user_desc":"一直在路上？！","user_location":"北京","user_tags":["旅行","文艺"],"birthday":1135612800000,"province":"北京"},"id":1053033187311618,"created_at":1482807269775,"updated_at":1482809956915}},{"created_at":1483007553473,"updated_at":1483007925414,"id":1056393395241199,"data":{"user_name":"Tina","state":0,"user_location":"Loftel大厦","location":"Loftel大厦","pics":["http://ofdx4t772.bkt.clouddn.com/1056393378463982?imageView2"],"content":"冰冰酱，我们一起去吧","like_comment_total_count":2,"like_count":1,"comment_count":1},"uid":1053033187311618,"status":"status","user":{"user_data":{"user_name":"Tina","gender":1,"state":0,"is_verification":0,"user_group":"user","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1053078183804957?imageView2","user_desc":"一直在路上？！","user_location":"北京","user_tags":["旅行","文艺"],"birthday":1135612800000,"province":"北京"},"id":1053033187311618,"created_at":1482807269775,"updated_at":1482809956915}},{"created_at":1482979720003,"updated_at":1483007364996,"id":1055926418210847,"data":{"like_comment_total_count":0,"content":"啦啦啦啦啦啦","pics":["http://ofdx4t772.bkt.clouddn.com/1055926317547542?imageView2","http://ofdx4t772.bkt.clouddn.com/1055926334324759?imageView2","http://ofdx4t772.bkt.clouddn.com/1055926334324760?imageView2","http://ofdx4t772.bkt.clouddn.com/1055926351101977?imageView2","http://ofdx4t772.bkt.clouddn.com/1055926367879194?imageView2","http://ofdx4t772.bkt.clouddn.com/1055926384656411?imageView2","http://ofdx4t772.bkt.clouddn.com/1055926401433628?imageView2","http://ofdx4t772.bkt.clouddn.com/1055926418210845?imageView2","http://ofdx4t772.bkt.clouddn.com/1055926418210846?imageView2"],"location":"北京 昌平区 回龙观龙锦苑四区","user_location":"北京 昌平区 回龙观龙锦苑四区","state":0,"user_name":"Tina","like_count":1,"comment_count":0},"uid":1053033187311618,"status":"status","user":{"user_data":{"user_name":"Tina","gender":1,"state":0,"is_verification":0,"user_group":"user","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1053078183804957?imageView2","user_desc":"一直在路上？！","user_location":"北京","user_tags":["旅行","文艺"],"birthday":1135612800000,"province":"北京"},"id":1053033187311618,"created_at":1482807269775,"updated_at":1482809956915}},{"created_at":1482810217264,"updated_at":1482979950197,"id":1053082646544427,"data":{"like_comment_total_count":8,"content":"好怀念阳澄湖的大闸蟹","pics":["http://ofdx4t772.bkt.clouddn.com/1053082495549475?imageView2","http://ofdx4t772.bkt.clouddn.com/1053082512326692?imageView2","http://ofdx4t772.bkt.clouddn.com/1053082529103909?imageView2","http://ofdx4t772.bkt.clouddn.com/1053082545881126?imageView2","http://ofdx4t772.bkt.clouddn.com/1053082579435559?imageView2","http://ofdx4t772.bkt.clouddn.com/1053082596212776?imageView2","http://ofdx4t772.bkt.clouddn.com/1053082612989993?imageView2","http://ofdx4t772.bkt.clouddn.com/1053082629767210?imageView2"],"location":"北京 昌平区 回龙观龙锦苑四区","user_location":"北京 昌平区 回龙观龙锦苑四区","state":0,"user_name":"Tina","like_count":3,"comment_count":4},"uid":1053033187311618,"status":"status","user":{"user_data":{"user_name":"Tina","gender":1,"state":0,"is_verification":0,"user_group":"user","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1053078183804957?imageView2","user_desc":"一直在路上？！","user_location":"北京","user_tags":["旅行","文艺"],"birthday":1135612800000,"province":"北京"},"id":1053033187311618,"created_at":1482807269775,"updated_at":1482809956915}},{"created_at":1483007892886,"updated_at":1483167262884,"id":1056399082717433,"data":{"user_name":"冰冰酱","state":0,"user_location":"鹏景阁大厦","location":"鹏景阁大厦","pics":["http://ofdx4t772.bkt.clouddn.com/1056399065940214?imageView2","http://ofdx4t772.bkt.clouddn.com/1056399082717431?imageView2","http://ofdx4t772.bkt.clouddn.com/1056399082717432?imageView2"],"content":"想谈天，大笑，然后和猫打滚","like_comment_total_count":2,"like_count":1,"comment_count":1},"uid":1053033036316673,"status":"status","user":{"user_data":{"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1055936652312643?imageView2","birthday":769881600000,"state":0,"is_verification":0,"user_group":"user","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1053075180683292?imageView2","user_desc":"啊嘞","user_location":"北京","user_tags":["旅行","帅气","啊啦啦啦啦啦啦啦"],"province":"北京","gender":1,"user_name":"冰冰酱"},"id":1053033036316673,"created_at":1482807260867,"updated_at":1482980330765}},{"created_at":1482979806337,"updated_at":1483007365371,"id":1055927877828649,"data":{"user_name":"冰冰酱","state":0,"user_location":"北京 昌平区 回龙观龙锦苑四区","location":"北京 昌平区 回龙观龙锦苑四区","pics":["http://ofdx4t772.bkt.clouddn.com/1055927827496992?imageView2","http://ofdx4t772.bkt.clouddn.com/1055927827496993?imageView2","http://ofdx4t772.bkt.clouddn.com/1055927827496994?imageView2","http://ofdx4t772.bkt.clouddn.com/1055927844274211?imageView2","http://ofdx4t772.bkt.clouddn.com/1055927844274212?imageView2","http://ofdx4t772.bkt.clouddn.com/1055927844274213?imageView2","http://ofdx4t772.bkt.clouddn.com/1055927861051430?imageView2","http://ofdx4t772.bkt.clouddn.com/1055927861051431?imageView2","http://ofdx4t772.bkt.clouddn.com/1055927877828648?imageView2"],"content":"巴拉巴拉小魔仙，biubiubiu","like_comment_total_count":0,"like_count":3,"comment_count":0},"uid":1053033036316673,"status":"status","user":{"user_data":{"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1055936652312643?imageView2","birthday":769881600000,"state":0,"is_verification":0,"user_group":"user","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1053075180683292?imageView2","user_desc":"啊嘞","user_location":"北京","user_tags":["旅行","帅气","啊啦啦啦啦啦啦啦"],"province":"北京","gender":1,"user_name":"冰冰酱"},"id":1053033036316673,"created_at":1482807260867,"updated_at":1482980330765}},{"created_at":1482758061692,"updated_at":1482831606948,"id":1052207614066930,"data":{"user_name":"","state":0,"user_location":"北京 昌平区 回龙观龙锦苑四区","location":"北京 昌平区 回龙观龙锦苑四区","pics":["http://ofdx4t772.bkt.clouddn.com/1052207597289713?imageView2"],"content":"hshdshnsnsndndn","like_comment_total_count":2,"like_count":1,"comment_count":1},"uid":1052202849337582,"status":"status","user":{"user_data":{"state":0,"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052210751406324?imageView2","is_verification":0,"user_group":"user"},"id":1052202849337582,"created_at":1482757777497,"updated_at":1482758249582}},{"created_at":1481769695123,"updated_at":1482760117602,"id":1035625584197634,"data":{"user_name":"小源源","state":0,"user_location":"北京","location":"北京市北京市朝阳区湖光中街靠近鹏景阁大厦","pics":["http://ofdx4t772.bkt.clouddn.com/1035625567420417?imageView2"],"content":"今天心情美丽","like_comment_total_count":6,"like_count":4,"comment_count":3},"uid":980983181541377,"status":"status","user":{"user_data":{"user_name":"小源","state":0,"birthday":1482681600000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052107437310169?imageView2/1/w/600/h/600"},"id":980983181541377,"created_at":1482752070690,"updated_at":1482752112829}},{"created_at":1481711327017,"updated_at":1482733944356,"id":1034646331654165,"data":{"like_comment_total_count":0,"content":"这个🌸漂亮吧","pics":["http://ofdx4t772.bkt.clouddn.com/1034646314876948?imageView2"],"location":"英国","user_location":"北京","state":0,"user_name":"小源源","like_count":1,"comment_count":0},"uid":980983181541377,"status":"status","user":{"user_data":{"user_name":"小源","state":0,"birthday":1482681600000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052107437310169?imageView2/1/w/600/h/600"},"id":980983181541377,"created_at":1482752070690,"updated_at":1482752112829}},{"created_at":1480997152271,"updated_at":1480997152271,"id":1022664463417368,"data":{"user_name":"小源源","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1022664345976847?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022664362754064?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022664362754065?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022664379531282?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022664396308499?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022664413085716?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022664429862933?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022664429862934?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022664446640151?imageView2/1/w/600/h/600"],"content":"测试一下","like_count":0,"comment_count":0},"uid":980983181541377,"status":"status","user":{"user_data":{"user_name":"小源","state":0,"birthday":1482681600000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052107437310169?imageView2/1/w/600/h/600"},"id":980983181541377,"created_at":1482752070690,"updated_at":1482752112829}},{"created_at":1479732116818,"updated_at":1483102904657,"id":1001440681197630,"data":{"like_comment_total_count":20,"content":"今天的心情不错","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"location":"英国","user_location":"北京","user_name":"小源源","like_count":1,"comment_count":10},"uid":980983181541377,"status":"status","user":{"user_data":{"user_name":"小源","state":0,"birthday":1482681600000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052107437310169?imageView2/1/w/600/h/600"},"id":980983181541377,"created_at":1482752070690,"updated_at":1482752112829}},{"created_at":1479730394339,"updated_at":1482553990394,"id":1001411790831677,"data":{"state":-1,"user_name":"小源源","user_location":"北京","location":"英国","pics":["http: //img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"content":"今天是一个美好的一天","like_comment_total_count":2,"like_count":2,"comment_count":1},"uid":980983181541377,"status":"status","user":{"user_data":{"user_name":"小源","state":0,"birthday":1482681600000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052107437310169?imageView2/1/w/600/h/600"},"id":980983181541377,"created_at":1482752070690,"updated_at":1482752112829}},{"created_at":1483157833623,"updated_at":1483433241191,"id":1058914675261462,"data":{"like_comment_total_count":0,"content":"第二次测试","pics":["http://ofdx4t772.bkt.clouddn.com/1058914524266509?imageView2","http://ofdx4t772.bkt.clouddn.com/1058914541043726?imageView2","http://ofdx4t772.bkt.clouddn.com/1058914557820943?imageView2","http://ofdx4t772.bkt.clouddn.com/1058914574598160?imageView2","http://ofdx4t772.bkt.clouddn.com/1058914591375377?imageView2","http://ofdx4t772.bkt.clouddn.com/1058914608152594?imageView2","http://ofdx4t772.bkt.clouddn.com/1058914641707027?imageView2","http://ofdx4t772.bkt.clouddn.com/1058914658484244?imageView2","http://ofdx4t772.bkt.clouddn.com/1058914675261461?imageView2"],"location":"点典文化","user_location":"点典文化","state":0,"user_name":"小魏","like_count":1,"comment_count":0},"uid":1049387598938129,"status":"status","user":{"user_data":{"user_name":"小魏","province":"广东","birthday":536342400000,"user_location":"深圳","gender":1,"user_group":"user","is_verification":0,"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1049394662146070?imageView2","state":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1058912460668940?imageView2","user_desc":"哈哈，测试","user_tags":["摄影","时尚","恭喜发财"]},"id":1049387598938129,"created_at":1482589975561,"updated_at":1483172032632}},{"created_at":1482590340857,"updated_at":1483433213384,"id":1049393722621972,"data":{"like_comment_total_count":6,"content":"今天天气好","pics":["http://ofdx4t772.bkt.clouddn.com/1049393689067538?imageView2","http://ofdx4t772.bkt.clouddn.com/1049393722621971?imageView2"],"location":"北京 昌平区 回龙观龙锦苑四区","user_location":"北京 昌平区 回龙观龙锦苑四区","state":0,"user_name":"","like_count":2,"comment_count":3},"uid":1049387598938129,"status":"status","user":{"user_data":{"user_name":"小魏","province":"广东","birthday":536342400000,"user_location":"深圳","gender":1,"user_group":"user","is_verification":0,"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1049394662146070?imageView2","state":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1058912460668940?imageView2","user_desc":"哈哈，测试","user_tags":["摄影","时尚","恭喜发财"]},"id":1049387598938129,"created_at":1482589975561,"updated_at":1483172032632}},{"created_at":1483402425501,"updated_at":1483412901628,"id":1063018248077320,"data":{"user_name":"","state":0,"user_location":"北京","location":"竣酒店餐厅","pics":["http://ofdx4t772.bkt.clouddn.com/1063018231300103?imageView2"],"content":"哇偶","like_comment_total_count":2,"like_count":1,"comment_count":1},"uid":1046316244336656,"status":"status","user":{"user_data":{"state":0,"birthday":1482336000000,"is_verification":0,"user_group":"user","user_location":"北京","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1046316831539217?imageView2/1/w/600/h/600"},"id":1046316244336656,"created_at":1482406908133,"updated_at":1482406946677}},{"created_at":1483424331141,"updated_at":1483424331141,"id":1063385769771077,"data":{"user_name":"","state":0,"user_location":"北京","location":"星巴克咖啡(北京望京鹏景阁店)","pics":["http://ofdx4t772.bkt.clouddn.com/1063385752993860?imageView2"],"content":"能不能发动态啊","like_count":0,"comment_count":0},"uid":1031364271996943,"status":"status","user":{"user_data":{"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052217512624374?imageView2","user_group":"user","is_verification":0,"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052220817735927?imageView2","state":0,"user_desc":"花开两朵 天各一方","user_location":"北京","user_tags":["大叔","个性","另类"],"birthday":569779200000,"province":"北京"},"id":1031364271996943,"created_at":1481515701338,"updated_at":1482758849157}},{"created_at":1483010339391,"updated_at":1483157944201,"id":1056440136565021,"data":{"like_comment_total_count":6,"content":"小尼姑年方二八","pics":["http://ofdx4t772.bkt.clouddn.com/1056440119787804?imageView2"],"location":"北京","user_location":"北京","state":0,"user_name":"","like_count":3,"comment_count":3},"uid":1031364271996943,"status":"status","user":{"user_data":{"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052217512624374?imageView2","user_group":"user","is_verification":0,"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1052220817735927?imageView2","state":0,"user_desc":"花开两朵 天各一方","user_location":"北京","user_tags":["大叔","个性","另类"],"birthday":569779200000,"province":"北京"},"id":1031364271996943,"created_at":1481515701338,"updated_at":1482758849157}},{"created_at":1483009337260,"updated_at":1483157952661,"id":1056423325794581,"data":{"like_comment_total_count":2,"content":"hhhhh","location":"J Fitness专属健身私教工作室(健身中心望京店)","user_location":"澳门","state":0,"user_name":"","like_count":2,"comment_count":1},"uid":1031317346123781,"status":"status","user":{"user_data":{"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056273991794846?imageView2/1/w/600/h/600","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056274377670815?imageView2/1/w/600/h/600","gender":0,"user_desc":"fff","user_location":"澳门","user_group":"user","is_verification":0,"user_tags":["美食","萝莉","孤独"],"birthday":1483113600000,"state":0},"id":1031317346123781,"created_at":1481512904114,"updated_at":1483437124501}},{"created_at":1483249878859,"updated_at":1483413559244,"id":1060458934108169,"data":{"like_comment_total_count":0,"content":"测试一波，修复图片bug","pics":["http://ofdx4t772.bkt.clouddn.com/1060458816667653?imageView2","http://ofdx4t772.bkt.clouddn.com/1060458883776518?imageView2","http://ofdx4t772.bkt.clouddn.com/1060458900553735?imageView2","http://ofdx4t772.bkt.clouddn.com/1060458934108168?imageView2"],"location":"泰福苑","user_location":"澳门","state":0,"user_name":"啊啊啊","like_count":1,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1483245918543,"updated_at":1483245918543,"id":1060392496332804,"data":{"user_name":"啊啊啊","state":0,"user_location":"澳门","location":"泰福苑","pics":["http://ofdx4t772.bkt.clouddn.com/1060392446001154?imageView2","http://ofdx4t772.bkt.clouddn.com/1060392479555587?imageView2"],"content":"不是可以发么","like_count":0,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1482819709710,"updated_at":1482979901770,"id":1053241895878746,"data":{"user_name":"啊啊啊","state":0,"user_location":"澳门","location":"北京市朝阳区广顺北大街靠近中国银行(北京望京科技园支行)","pics":["http://ofdx4t772.bkt.clouddn.com/1053241879101528?imageView2","http://ofdx4t772.bkt.clouddn.com/1053241895878745?imageView2"],"content":"人生何处不相逢","like_comment_total_count":0,"like_count":2,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1482046369063,"updated_at":1482752334420,"id":1040267403657262,"data":{"user_name":"啊啊啊","state":"-1","user_location":"澳门","location":"","pics":["http://ofdx4t772.bkt.clouddn.com/1040267353325612?imageView2","http://ofdx4t772.bkt.clouddn.com/1040267386880045?imageView2"],"content":"测试一波","like_comment_total_count":6,"like_count":1,"comment_count":3},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1481596396696,"updated_at":1481596396696,"id":1032718109442073,"data":{"user_name":"啊啊啊","state":0,"user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1032717555793936?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032717874561041?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032717958447122?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032718042333203?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032718075887636?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032718092664853?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032718092664854?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032718109442071?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032718109442072?imageView2/1/w/600/h/600"],"content":"测试","like_count":1,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1481596155183,"updated_at":1481596155183,"id":1032714066133007,"data":{"user_name":"啊啊啊","state":0,"user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1032713462153222?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032713814474759?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032713898360840?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032713999024137?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032714032578570?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032714032578571?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032714049355788?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032714049355789?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1032714049355790?imageView2/1/w/600/h/600"],"content":"斗鱼直播","like_count":1,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1481027921973,"updated_at":1481027921973,"id":1023180681576562,"data":{"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1023180664799337?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1023180664799338?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1023180664799339?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1023180664799340?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1023180681576557?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1023180681576558?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1023180681576559?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1023180681576560?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1023180681576561?imageView2/1/w/600/h/600"],"content":"看看","like_count":1,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1481002267257,"updated_at":1482407769531,"id":1022750278877228,"data":{"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1022749809115171?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022749976887332?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022750043996197?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022750144659494?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022750228545575?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022750245322792?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022750262100009?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022750262100010?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022750278877227?imageView2/1/w/600/h/600"],"content":"测试测试测试","like_comment_total_count":0,"like_count":2,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1481001504247,"updated_at":1481001504247,"id":1022737477861410,"data":{"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1022737024876569?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022737192648730?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022737276534811?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022737377198108?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022737461084189?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022737461084190?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022737461084191?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022737461084192?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022737461084193?imageView2/1/w/600/h/600"],"content":"测试","like_count":0,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1480996538209,"updated_at":1480996538209,"id":1022654162206734,"data":{"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1022653709221893?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022653876994054?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022653960880135?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022654044766216?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022654128652297?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022654145429514?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022654145429515?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022654145429516?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1022654162206733?imageView2/1/w/600/h/600"],"content":"诺空哦solo","like_count":0,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1480651386906,"updated_at":1482553552327,"id":1016863472549931,"data":{"state":0,"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1016863371886628?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863388663845?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863405441062?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863438995496?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863438995497?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863405441063?imageView2/1/w/600/h/600"],"content":"也破是下午诺JOJO蛇精男","like_comment_total_count":0,"like_count":1,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1480651384432,"updated_at":1482553567324,"id":1016863438995498,"data":{"state":0,"content":"也破是下午诺JOJO蛇精男","pics":["http://ofdx4t772.bkt.clouddn.com/1016863371886628?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863388663845?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863405441062?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863438995496?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1016863438995497?imageView2/1/w/600/h/600"],"location":"英国","user_location":"北京","user_name":"啊啊啊","like_count":0,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1480651335513,"updated_at":1482808577757,"id":1016862616911907,"data":{"like_comment_total_count":0,"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1016862616911906?imageView2/1/w/600/h/600"],"content":"哦搜狗","state":0,"like_count":1,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1480651236846,"updated_at":1480651236846,"id":1016860955967519,"data":{"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"content":"你们","like_count":0,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1480649677872,"updated_at":1480649677872,"id":1016834800287749,"data":{"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"content":"看看咯","like_count":0,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1480582629884,"updated_at":1480582629884,"id":1015709921509386,"data":{"user_name":"啊啊啊","user_location":"北京","location":"英国","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"content":"什么鬼","like_count":0,"comment_count":0},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1479891913649,"updated_at":1480498716193,"id":1004121629982735,"data":{"like_comment_total_count":12,"content":"没意思","pics":["http://img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg"],"location":"英国","user_location":"北京","user_name":"啊啊啊","like_count":1,"comment_count":6},"uid":1002274307506177,"status":"status","user":{"user_data":{"state":0,"birthday":684255600000,"is_verification":0,"user_group":"user","user_location":"澳门","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027114603184146?imageView2/1/w/600/h/600","user_name":"啊啊啊","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","verification_type":2,"user_desc":"什么鬼","user_tags":["大叔","个性","旅行"],"province":"北京"},"id":1002274307506177,"created_at":1479781804088,"updated_at":1483436114002}},{"created_at":1481600252048,"updated_at":1482590506089,"id":1032782802386985,"data":{"like_comment_total_count":0,"content":"我上传九张","pics":["http://ofdx4t772.bkt.clouddn.com/1032782601060384?imageView2","http://ofdx4t772.bkt.clouddn.com/1032782634614817?imageView2","http://ofdx4t772.bkt.clouddn.com/1032782651392034?imageView2","http://ofdx4t772.bkt.clouddn.com/1032782668169251?imageView2","http://ofdx4t772.bkt.clouddn.com/1032782684946468?imageView2","http://ofdx4t772.bkt.clouddn.com/1032782718500901?imageView2","http://ofdx4t772.bkt.clouddn.com/1032782735278118?imageView2","http://ofdx4t772.bkt.clouddn.com/1032782768832551?imageView2","http://ofdx4t772.bkt.clouddn.com/1032782785609768?imageView2"],"location":"英国","user_location":"北京","state":0,"user_name":"普通用户","like_count":2,"comment_count":0},"uid":996532036894722,"status":"status","user":{"user_data":{"sys_comment":"1测试后台创建，2测试昵称和手机号唯一","state":0,"user_group":"user","user_location":"石家庄","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","user_name":"普通用户","is_verification":0,"user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600"},"id":996532036894722,"created_at":1479439538127,"updated_at":1482551062954}},{"created_at":1481283771064,"updated_at":1483007381265,"id":1027473132290077,"data":{"user_name":"陈鑫陈鑫","state":0,"user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1027473115512860?imageView2/1/w/600/h/600"],"content":"接口修改后第一张图片","like_comment_total_count":4,"like_count":3,"comment_count":2},"uid":995062101114881,"status":"status","user":{"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1},"id":995062101114881,"created_at":1479351923757,"updated_at":1482742113218}},{"created_at":1481170563764,"updated_at":1481270355721,"id":1025573817221129,"data":{"state":-1,"user_name":"陈鑫陈鑫","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1025573817221128?imageView2/1/w/600/h/600"],"content":"和","like_comment_total_count":0,"like_count":2,"comment_count":0},"uid":995062101114881,"status":"status","user":{"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1},"id":995062101114881,"created_at":1479351923757,"updated_at":1482742113218}},{"created_at":1480909736915,"updated_at":1482553799400,"id":1021197866303496,"data":{"state":0,"user_name":"陈鑫陈鑫","user_location":"北京","location":"英国","pics":["http://ofdx4t772.bkt.clouddn.com/1021197866303495?imageView2/1/w/600/h/600"],"content":"银河系漫游指南","like_comment_total_count":0,"like_count":1,"comment_count":0},"uid":995062101114881,"status":"status","user":{"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1},"id":995062101114881,"created_at":1479351923757,"updated_at":1482742113218}},{"created_at":1480674108980,"updated_at":1482808244036,"id":1017244684451918,"data":{"state":0,"like_comment_total_count":4,"content":"周五啦","pics":["http://ofdx4t772.bkt.clouddn.com/1017243694596168?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1017243895922761?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1017244097249354?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1017244130803787?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1017244332130380?imageView2/1/w/600/h/600","http://ofdx4t772.bkt.clouddn.com/1017244600565837?imageView2/1/w/600/h/600"],"location":"英国","user_location":"北京","user_name":"陈鑫陈鑫","like_count":1,"comment_count":2},"uid":995062101114881,"status":"status","user":{"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1},"id":995062101114881,"created_at":1479351923757,"updated_at":1482742113218}},{"created_at":1479816201883,"updated_at":1482551368917,"id":1002851393404971,"data":{"state":-1,"user_name":"陈鑫陈鑫","user_location":"北京","location":"南京夫子庙","pics":["111","222","333","444","555","666","777","888","999"],"content":123456789,"like_comment_total_count":18,"like_count":2,"comment_count":9},"uid":995062101114881,"status":"status","user":{"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1},"id":995062101114881,"created_at":1479351923757,"updated_at":1482742113218}},{"created_at":1479815981215,"updated_at":1482551786753,"id":1002847702417450,"data":{"like_comment_total_count":8,"user_name":"陈鑫陈鑫","user_location":"北京","location":"北京市朝阳区望京洪泰众创空间2楼","pics":["aaaaaaaaaaaa","bbbbbbbbbbbbbbbbbb","ccccccccccccccccccccc","dddddddddddddddddddddd","eeeeeeeeeeeeeeeeeeeeeee","ffffffffffffffffffffffffffffffff","gggggggggggggggggggggggggggggg","hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"],"content":"内容内容内容。。。。。123","state":-1,"like_count":0,"comment_count":4},"uid":995062101114881,"status":"status","user":{"user_data":{"real_name":"修改后的真实姓名","recommend_by_sys":1,"state":0,"province":"山东","user_cover_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","birthday":439660800000,"user_tags":["标签、标签1"],"is_verification":0,"user_group":"user","user_location":"天津","user_desc":"描述描述","gender":1,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1027065831817232?imageView2/1/w/600/h/600","user_name":"陈鑫陈鑫","verification_type":1},"id":995062101114881,"created_at":1479351923757,"updated_at":1482742113218}},{"created_at":1479294591874,"updated_at":1482137468660,"id":994100229767196,"data":{"like_comment_total_count":0,"content":"也是我的小动态","pics":[],"location":"北京","user_location":"北京","user_name":"测试用户001","like_count":3,"comment_count":0},"uid":962080376160261,"status":"status","user":{"user_data":{"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0},"id":962080376160261,"created_at":1477386059719,"updated_at":1478959953447}},{"created_at":1478183129627,"updated_at":1483102824230,"id":975452991717378,"data":{"location":"北京","content":"我的小动态","like_comment_total_count":2,"state":0,"like_count":4,"comment_count":1},"uid":962080376160261,"status":"status","user":{"user_data":{"user_name":"测试用户001","profile_pic_url":"http://tva2.sinaimg.cn/crop.0.0.180.180.50/ceb44716jw1f6p1b1ccmbj20500503yu.jpg","gender":1,"user_desc":"测试用户（test_user_001）","user_location":"深圳","user_group":"user","verification_type":1,"birthday":1467354028000,"state":0},"id":962080376160261,"created_at":1477386059719,"updated_at":1478959953447}}]}
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
         * created_at : 1483004617147
         * updated_at : 1483004617147
         * id : 1056344137334963
         * data : {"user_name":"大头君","state":0,"user_location":"北京","location":"星巴克咖啡(北京望京鹏景阁店)","pics":["http://ofdx4t772.bkt.clouddn.com/1056344120557746?imageView2"],"content":"哈哈","like_count":0,"comment_count":0}
         * uid : 1056003526295659
         * status : status
         * user : {"user_data":{"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]},"id":1056003526295659,"created_at":1482984315835,"updated_at":1483436905191}
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
             * user_name : 大头君
             * state : 0
             * user_location : 北京
             * location : 星巴克咖啡(北京望京鹏景阁店)
             * pics : ["http://ofdx4t772.bkt.clouddn.com/1056344120557746?imageView2"]
             * content : 哈哈
             * like_count : 0
             * comment_count : 0
             */

            private DataBean data;
            private long uid;
            private String status;
            /**
             * user_data : {"user_name":"大头君","state":0,"birthday":1482940800000,"is_verification":0,"user_group":"user","user_location":"北京","gender":0,"profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600","user_desc":"啦啦啦","user_tags":["摄影","户外","旅行"]}
             * id : 1056003526295659
             * created_at : 1482984315835
             * updated_at : 1483436905191
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class DataBean implements Serializable{
                private String user_name;
                private int state;
                private String user_location;
                private String location;
                private String content;
                private int like_count;
                private int comment_count;
                private List<String> pics;

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
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

                public int getLike_count() {
                    return like_count;
                }

                public void setLike_count(int like_count) {
                    this.like_count = like_count;
                }

                public int getComment_count() {
                    return comment_count;
                }

                public void setComment_count(int comment_count) {
                    this.comment_count = comment_count;
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
                 * user_name : 大头君
                 * state : 0
                 * birthday : 1482940800000
                 * is_verification : 0
                 * user_group : user
                 * user_location : 北京
                 * gender : 0
                 * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1056133633605752?imageView2/1/w/600/h/600
                 * user_desc : 啦啦啦
                 * user_tags : ["摄影","户外","旅行"]
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
                    private String user_name;
                    private int state;
                    private long birthday;
                    private int is_verification;
                    private String user_group;
                    private String user_location;
                    private int gender;
                    private String profile_pic_url;
                    private String user_desc;
                    private List<String> user_tags;

                    public String getUser_name() {
                        return user_name;
                    }

                    public void setUser_name(String user_name) {
                        this.user_name = user_name;
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

                    public String getUser_location() {
                        return user_location;
                    }

                    public void setUser_location(String user_location) {
                        this.user_location = user_location;
                    }

                    public int getGender() {
                        return gender;
                    }

                    public void setGender(int gender) {
                        this.gender = gender;
                    }

                    public String getProfile_pic_url() {
                        return profile_pic_url;
                    }

                    public void setProfile_pic_url(String profile_pic_url) {
                        this.profile_pic_url = profile_pic_url;
                    }

                    public String getUser_desc() {
                        return user_desc;
                    }

                    public void setUser_desc(String user_desc) {
                        this.user_desc = user_desc;
                    }

                    public List<String> getUser_tags() {
                        return user_tags;
                    }

                    public void setUser_tags(List<String> user_tags) {
                        this.user_tags = user_tags;
                    }
                }
            }
        }
    }
}
