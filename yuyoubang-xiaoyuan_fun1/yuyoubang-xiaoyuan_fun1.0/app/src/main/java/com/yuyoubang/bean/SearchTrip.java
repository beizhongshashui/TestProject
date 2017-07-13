package com.yuyoubang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongchen on 16/12/18.
 */

public class SearchTrip implements Serializable{

    private ResultBean result;
    /**
     * result : {"list":[{"created_at":1481536559100,"updated_at":1481814851041,"id":1031714211168290,"data":{"trip_name":"北京大学","trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1031714177613857?imageView2/1/w/600/h/600","participants_limit_count":25,"start_time":1481472000000,"end_time":1481731200000,"participate_end_time":1481299200000,"trip_tags":["自驾","户外","摄影"],"trip_price":25,"participants_male_count":0,"participants_female_count":0,"meeting_place":"北京","meeting_city":"北京市朝阳区","meeting_province":"北京","trip_intro":"北京大学","trip_equipment_intro":"北京大学","other_desc":"北京大学","trip_route_type":"登山","trip_state":1,"hot_index":0,"biz_user_name":"陈鑫陈鑫","trip_days":4,"user_create_group_id":1481536558300},"uid":995062101114881,"user_result":{"user_data":{"user_name":"陈鑫陈鑫","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","gender":1,"user_desc":"描述描述","user_location":"天津","verification_type":1,"user_tags":["标签、标签1"],"birthday":439660800000,"user_cover_pic_url":"www.baidu.com1","province":"山东"},"id":995062101114881,"created_at":1479351923757,"updated_at":1481785634484}},{"created_at":1481535803409,"updated_at":1481537557886,"id":1031701527592990,"data":{"user_create_group_id":1481535803058,"trip_days":5,"biz_user_name":"小源源","hot_index":0,"trip_state":2,"trip_route_type":"骑行","other_desc":"跑车","trip_equipment_intro":"跑车","trip_intro":"跑车","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":25,"trip_tags":["金融","时尚","摄影"],"participate_end_time":1481299200000,"end_time":1481817600000,"start_time":1481472000000,"participants_limit_count":25,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1031700655177757?imageView2/1/w/600/h/600","trip_name":"北京跑车"},"uid":980983181541377,"user_result":{"user_data":{"user_name":"小源源","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/985087459000333?imageView2/1/w/600/h/600","gender":0,"user_location":"北京","verification_type":1,"birthday":1478707200000},"id":980983181541377,"created_at":1478512754894,"updated_at":1481703168502}},{"created_at":1481464405914,"updated_at":1481464405914,"id":1030503667925003,"data":{"user_create_group_id":1481464406298,"trip_days":3,"biz_user_name":"陈鑫陈鑫","hot_index":0,"trip_state":0,"trip_route_type":"登山","other_desc":"美女","trip_equipment_intro":"美女","trip_intro":"风骚","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":25,"trip_tags":["码农","文艺","户外"],"participate_end_time":1481299200000,"end_time":1481558400000,"start_time":1481385600000,"participants_limit_count":25,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1030502896173066?imageView2/1/w/600/h/600","trip_name":"北京三人行"},"uid":995062101114881,"user_result":{"user_data":{"user_name":"陈鑫陈鑫","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","gender":1,"user_desc":"描述描述","user_location":"天津","verification_type":1,"user_tags":["标签、标签1"],"birthday":439660800000,"user_cover_pic_url":"www.baidu.com1","province":"山东"},"id":995062101114881,"created_at":1479351923757,"updated_at":1481785634484}},{"created_at":1481192879575,"updated_at":1481531499093,"id":1025948217573418,"data":{"user_create_group_id":1481192877996,"trip_days":10,"biz_user_name":"陈鑫陈鑫","hot_index":0,"trip_state":2,"trip_route_type":"登山","other_desc":"图库就离开了","trip_equipment_intro":"急急急","trip_intro":"老司机卡他","meeting_province":"北京","meeting_city":"北京市朝阳区","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":15,"trip_tags":["萝莉","美食","户外"],"participate_end_time":1480521600000,"end_time":1481904000000,"start_time":1481126400000,"participants_limit_count":25,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1025946757955625?imageView2/1/w/600/h/600","trip_name":"北京一日游"},"uid":995062101114881,"user_result":{"user_data":{"user_name":"陈鑫陈鑫","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","gender":1,"user_desc":"描述描述","user_location":"天津","verification_type":1,"user_tags":["标签、标签1"],"birthday":439660800000,"user_cover_pic_url":"www.baidu.com1","province":"山东"},"id":995062101114881,"created_at":1479351923757,"updated_at":1481785634484}},{"created_at":1481093003394,"updated_at":1481094476488,"id":1024272576348178,"data":{"recommend_index":1,"user_create_group_id":1481093001980,"trip_days":2,"biz_user_name":"小源","hot_index":0,"trip_state":1,"trip_route_type":"休闲","other_desc":"","trip_equipment_intro":"地铁票2元\n门票0元\n租自行车50元\n吃烤鸭190元","trip_intro":"上午参观北京大学，下午参观清华大学","meeting_province":"北京","meeting_city":"北京","meeting_place":"北京","participants_female_count":5,"participants_male_count":4,"trip_price":1,"trip_tags":["测试","哈哈"],"participate_end_time":1480694400000,"end_time":1480608000000,"start_time":1480521600000,"participants_limit_count":1,"trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1024265781575697?imageView2/1/w/600/h/600","trip_name":"博雅塔未名湖北京大学图书馆一塔湖图"},"uid":995062101114881,"user_result":{"user_data":{"user_name":"陈鑫陈鑫","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","gender":1,"user_desc":"描述描述","user_location":"天津","verification_type":1,"user_tags":["标签、标签1"],"birthday":439660800000,"user_cover_pic_url":"www.baidu.com1","province":"山东"},"id":995062101114881,"created_at":1479351923757,"updated_at":1481785634484}},{"created_at":1479715358280,"updated_at":1479715358280,"id":1001159528611889,"data":{"trip_days":0,"biz_user_name":"小源","hot_index":0,"trip_state":0,"trip_route_type":"登山","meeting_province":"北京","meeting_city":"北京","meeting_place":"北京","participants_female_count":0,"participants_male_count":0,"trip_price":"200","trip_tags":["自助","登山"],"participate_end_time":"1480003212","end_time":"1480089612","start_time":"1479657612","participants_limit_count":"10","trip_cover_pic":"http: //img1.qunarzz.com/travel/d3/1609/39/8ee040875fa30b5.jpg_r_1024x683x95_51f23c94.jpg","trip_name":"北京七日游"},"uid":980983181541377,"user_result":{"user_data":{"user_name":"小源源","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/985087459000333?imageView2/1/w/600/h/600","gender":0,"user_location":"北京","verification_type":1,"birthday":1478707200000},"id":980983181541377,"created_at":1478512754894,"updated_at":1481703168502}},{"created_at":1478432530660,"updated_at":1478595426748,"id":979637246164998,"data":{"trip_name":"北京到广西桂林漓江、巴马、德天瀑布、北海银滩双卧十日老年游","trip_cover_pic":"http://c.cncnimg.cn/044/625/847a_m.jpg","participants_limit_count":200,"start_time":1481639132000,"end_time":1492157532000,"participate_end_time":1477973532000,"trip_tags":["国内游"],"trip_price":15000,"participants_male_count":1,"participants_female_count":1,"meeting_place":"北京","trip_intro":"品质保证：纯玩不进店、北京成团、一车一导、全程陪同服务；特色美食：餐标升级为25标，阳朔啤酒鱼、桂林米粉宴、桂花宴、巴马长寿素宴。感受舌尖上的美食！不留遗憾；靖江王城：\u201c南方小故宫\u201d的龙脉福地 靖江王府邸---5A级景区【靖江王城】；畅游漓江：亲临新版人民币面值20元背面的画中漓江。畅游入选最球15条最美河流的5A级景区漓江山水画廊；养生讲坐：住1晚巴马，保证更充足的时间游览\u201c世界第五长寿乡\u201d；聆听\u201c养生讲座\u201d，学习养生长寿之术；走访寿星：访问坡月\u2014\u2014长寿村，走访百岁老人，探秘长寿养生秘决；感受养生氛围；洗肺之旅：洗肺慢游巴马；慢步清心体验仁寿山庄的独特魅力，慢条斯理品巴马的世外美食；中越边境: 亲临亚洲第一、世界第二大跨国瀑布【德天瀑布】入住《花千骨》长留仙境观瀑酒店；滨海城市：皇牌景点\u201c天下第一滩\u201d【银滩】；滨海冠头岭登山望海【冠头岭】于海边放飞和平鸽；","trip_equipment_intro":"请自备常用药品。旅游期间，部分旅游者可能出现水土不服症状，建议预备防肠胃不适、防暑降温、抗感冒、清火、晕车、抗过敏等药物；有慢性疾患的游客,请带好常备药。","other_desc":"跟团游住宿：桂林/阳朔舒适型酒店（当地四星未挂牌标准），南宁段入住旅游酒店双人标准间（当地三星未挂牌标准、独立卫生间、单冷空调、热水、电视）。出现自然单间我社将安排游客入住三人间或加钢丝床或游客自补房差。用餐：全程7早餐10正餐，正餐8菜一汤。10人一桌，正餐：25元/标，酒店含早餐5元/标，不足十人，一桌按照团餐餐标安排，菜量种类相应减少，但维持餐标不变。 如自动放弃团餐 则费用不退。用车：当地空调旅游巴士，根据实际人数当地选用空调旅游车,保证一人一个正座；门票：以上所列景点第一大门票。团队折扣价：（一江四湖35元、刘三姐大观园20元、兴坪船票55元、大榕树12元、王城独秀峰35元、德天瀑布70元、百鸟岩55元、硕龙镇往德天景区环保观光车45元/人自理 ）。导服：当地中文导游服务、持全国导游资格证上岗（已含服务费10元/人/天）。保险：已含旅行社责任险。不含旅游人身意外保险（请您积极购买意外保险、如出现意外由保险公司赔付）。大交通：北京-桂林/南宁-北京往返空调火车硬卧，桂林到南宁动车二等座，车次以我社发的出团通知单为准；特别说明：1）铁路票务系统随机出票，故无法指定连座或指定同一车厢，敬请见谅； 2）票源紧张时期，若出软卧送站时请客人按票面补差价！请您预知并理解！ 3）报名时需提供清晰的客人身份证扫描件！4）桂林票价：票价：硬卧上399/中414/下421；软卧上629/软卧下656；5）南宁票价：硬卧上464/中472/下497 软卧上737/ 下770；购物店：全程纯玩不进购物店。（提示：王城景区内有请太岁、拓画等活动；为景区项目，非旅行社安排的购物点，请告知。）","trip_route_type":"摄影","trip_state":1,"trip_state_desc":"","hot_index":0,"city_of_biz_user":"北京","biz_user_name":"七月","participate_state":"cancel","participate_state_desc":"by_business"},"uid":976528209936404,"user_result":{"user_data":{"user_name":"七月","profile_pic_url":"http://static.risfond.com/images2/2016/2/232d68c5013245eea8c51bc01373145c.jpg","gender":0,"user_desc":"钱到用时方恨少，世界那么大，我想去看看，钱包这么小，哪也去不了。","user_location":"北京","verification_type":2,"user_tags":["背包客","驴友"],"birthday":647493132000,"user_cover_pic_url":"http://img.blog.csdn.net/20161104163406312?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center"},"id":976528209936404,"created_at":1478247217785,"updated_at":1481645291967}},{"created_at":1478256703280,"updated_at":1478595103146,"id":976687358607389,"data":{"city_of_biz_user":"北京","hot_index":0,"trip_state_desc":"","trip_state":1,"trip_route_type":"摄影","other_desc":"不含项目1 护照费用；2 全程小费RMB400/人3 客人在境外一切自愿消费；4 酒店内电话、传真、洗熨、收费电视、饮料、行李搬运、烟酒等费用及其他相关费用；5 单人房差费用：依照旅游业现行作业规定，我社有权依据最终成团人数情况，为避免单男单女住宿的情况而调整房间分配情况（包括规定分开住宿或加床，或家庭成员分开住宿等），如客人执意住在一起而造成我社团队出现的单房差，均由客人自行承担；根据地接社相关规定12岁以上儿童必须占床，12岁以下儿童可以选择是否占床，如不占床，请游客提前说明，具体费用根据所报团队情况而定；6 服务项目未提及的其他的一切费用，例如特种门票（夜总会、博览会、缆车等费用）；7 酒店不提供拖鞋、牙刷等个人卫生贴身物品；8 驾驶员每天平均工作八小时，超过工作时间，会更换司机及用车；","trip_equipment_intro":"请自备常用药品。旅游期间，部分旅游者可能出现水土不服症状，建议预备防肠胃不适、防暑降温、抗感冒、清火、晕车、抗过敏等药物；有慢性疾患的游客,请带好常备药。","trip_intro":"独家赠送6大超值项目：1、 航空公司：2、 升级全程午晚餐3、 大猫号游船豪华自助午餐：4、 赠送\u201c国照\u201d ：5、 新西兰蜂蜜或橄榄茶：6、 境外旅行意外伤害保险：包含7大特色景点：1、 绿岛大堡礁：2、 悉尼港晚餐游船：3、 梦幻世界：4、 SKYPOINT观景台：5、 激浪世界6、 土著毛利文化村：7、 爱歌顿农场：赠送8大优选景点：1、 悉尼歌剧院及海港大桥：2、 悉尼百年纪念公园：3、 悉尼奥林匹克公园： 4、 悉尼大学：5、 昆士兰大学：6、 红树森林： 7、 罗托鲁瓦湖： 8、 弗莱克植物园：","meeting_place":"北京","participants_female_count":6,"participants_male_count":3,"trip_price":15000,"trip_tags":["天天发团"],"participate_end_time":1477973532000,"end_time":1492157532000,"start_time":1481639132000,"participants_limit_count":200,"trip_cover_pic":"http://c.cncnimg.cn/036/962/c1d1_m.jpg","trip_name":"北京起止澳大利亚凯恩斯新西兰11日游","participate_state":"cancel","participate_state_desc":"by_business"},"uid":976528209936404,"user_result":{"user_data":{"user_name":"七月","profile_pic_url":"http://static.risfond.com/images2/2016/2/232d68c5013245eea8c51bc01373145c.jpg","gender":0,"user_desc":"钱到用时方恨少，世界那么大，我想去看看，钱包这么小，哪也去不了。","user_location":"北京","verification_type":2,"user_tags":["背包客","驴友"],"birthday":647493132000,"user_cover_pic_url":"http://img.blog.csdn.net/20161104163406312?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center"},"id":976528209936404,"created_at":1478247217785,"updated_at":1481645291967}},{"created_at":1478255404643,"updated_at":1478595287652,"id":976665565003804,"data":{"participate_state_desc":"by_business","participate_state":"cancel","trip_name":"日本东京7日游【北京直飞东京大阪各1天自由活动一价全含】","trip_cover_pic":"http://c.cncnimg.cn/036/962/c1d1_m.jpg","participants_limit_count":200,"start_time":1491639132000,"end_time":1492157532000,"participate_end_time":1477973532000,"trip_tags":["天天发团"],"trip_price":5580,"participants_male_count":2,"participants_female_count":2,"meeting_place":"北京","trip_intro":"日本东京7日游【北京直飞东京大阪各1天自由活动一价全含】★北京直飞，名古屋往返！★东方航空，正点正价，开启舒适之旅！★无自费无小费，享受安心之旅！★两晚正宗温泉酒店，穿和服泡名汤乐趣无穷！ ★游双古都京都奈良，深度体验日本文化！★亲临世界文化遗产富士山五合目，零距离接触日本象征！★亲历日本国宝级景点，世界文化遗产金阁寺！★吃货天堂\u201c流连忘返\u201d：正宗温泉料理、日式拉面、火锅、寿司、烤肉、天妇罗... ...★东京、大阪两地均可自由活动，跟团也可自由自在！★独家安排：日本最大茶园品香茗，体味中日茶文化的差异！★季节限定：京都赏枫名胜\u2014\u2014跟随伟人的足迹走近岚山，追忆周总理岚山抒怀！","trip_equipment_intro":"1、本产品报价是按照2成人入住1间房计算的价格，如出现单男单女，尽量安排该客人与其他同性别团友拼房（领队及导游有权调整用房）；如不愿拼房或未能拼房，请补缴单房差（如需升级或延住酒店请与客服联系补差）。 2、儿童费用已包含往返程机票，车位费，导游费，含早餐及正餐半餐。 3、由于12岁以下儿童费用为不占床之报价，若儿童需占床，请补房差。 4、游客入住酒店后如需升级或者更换房型，需补交升级或者更换的房差费用。 5、由于列车、航班等公共交通工具延误或取消，以及第三方侵害等不可归责于旅行社的原因导致旅游者人身、财产权受到损害的， 旅行社不承担 责任，但会积极协助解决旅游者与责任方之间的纠纷。 6、机票均属特价机票，一经开出不得签转、退票，如果因客人原因导致机票姓名与证件不符产生损失将由客人自行承担；如遇航空 公司临时调 价，我社有权调整价格。如遇航空公司临时取消航班，我社会通知您更换临近的班期出发，敬请谅解！ ","other_desc":"报价包含1.交通：北京-日本往返国际团队机票经济舱费用及机场建设税；2.住宿：精选日本舒适型当地3-4星酒店住宿（特别安排2晚温泉酒店住宿，旺季期间如遇酒店紧张，可能安排2-4人间）；3.用车：境外旅游巴士及司机（根据团队人数，通常为18-45座）；4.导游：当地专业中文导游服务；5.餐食：行程内所列餐食，6正5早，正餐餐标平均为1000日元/人；(备注：温泉料理餐是含在酒店住宿费里的，不吃不退)；6.景点门票：行程内所列景点的首道门票；7.签证：ADS团队旅游签证费用；8.小费：司机+导游小费；9.特别赠送：42.7万境外旅游人身意外伤害保险；10.儿童价特殊说明：2-6周岁儿童可以不占床，不占床费用减200元。如果占床同成人价格。7周岁以上（含）必须占床。11.外籍附加费：外籍人员（包括港澳台）附加费800元/人。报价不含1. 航空险；2. 护照费用；签证相关的例如未成年人公证、认证等相关费用；3. 服务项目未提及到的其他一切费用，例如：特殊门票费用、游船（轮）、缆车、地铁票等费用；4. 单房差或加床费用（单房：房间内为一张单人床，单人房差费用为550元/人/晚）；5. 中国境内机场（车站）接送费用；6. 行李物品托管或超重费、管理费，行李海关课税；7. 个人费用：酒店内电话、传真、洗熨、收费电视、饮料、烟酒、行李搬运等费用；8. 旅游者因违约、自身过错、自由活动期间内行为或自身疾病引起的人身和财产损失；9. 因交通延阻、罢工、大风、大雾、航班取消或更改时间等人力不可抗拒原因所引致的额外费用；10. 自由活动期间费用自理。","trip_route_type":"摄影","trip_state":1,"trip_state_desc":"","hot_index":0,"city_of_biz_user":"北京"},"uid":976528209936404,"user_result":{"user_data":{"user_name":"七月","profile_pic_url":"http://static.risfond.com/images2/2016/2/232d68c5013245eea8c51bc01373145c.jpg","gender":0,"user_desc":"钱到用时方恨少，世界那么大，我想去看看，钱包这么小，哪也去不了。","user_location":"北京","verification_type":2,"user_tags":["背包客","驴友"],"birthday":647493132000,"user_cover_pic_url":"http://img.blog.csdn.net/20161104163406312?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center"},"id":976528209936404,"created_at":1478247217785,"updated_at":1481645291967}}]}
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
         * created_at : 1481536559100
         * updated_at : 1481814851041
         * id : 1031714211168290
         * data : {"trip_name":"北京大学","trip_cover_pic":"http://ofdx4t772.bkt.clouddn.com/1031714177613857?imageView2/1/w/600/h/600","participants_limit_count":25,"start_time":1481472000000,"end_time":1481731200000,"participate_end_time":1481299200000,"trip_tags":["自驾","户外","摄影"],"trip_price":25,"participants_male_count":0,"participants_female_count":0,"meeting_place":"北京","meeting_city":"北京市朝阳区","meeting_province":"北京","trip_intro":"北京大学","trip_equipment_intro":"北京大学","other_desc":"北京大学","trip_route_type":"登山","trip_state":1,"hot_index":0,"biz_user_name":"陈鑫陈鑫","trip_days":4,"user_create_group_id":1481536558300}
         * uid : 995062101114881
         * user_result : {"user_data":{"user_name":"陈鑫陈鑫","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","gender":1,"user_desc":"描述描述","user_location":"天津","verification_type":1,"user_tags":["标签、标签1"],"birthday":439660800000,"user_cover_pic_url":"www.baidu.com1","province":"山东"},"id":995062101114881,"created_at":1479351923757,"updated_at":1481785634484}
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
             * trip_name : 北京大学
             * trip_cover_pic : http://ofdx4t772.bkt.clouddn.com/1031714177613857?imageView2/1/w/600/h/600
             * participants_limit_count : 25
             * start_time : 1481472000000
             * end_time : 1481731200000
             * participate_end_time : 1481299200000
             * trip_tags : ["自驾","户外","摄影"]
             * trip_price : 25
             * participants_male_count : 0
             * participants_female_count : 0
             * meeting_place : 北京
             * meeting_city : 北京市朝阳区
             * meeting_province : 北京
             * trip_intro : 北京大学
             * trip_equipment_intro : 北京大学
             * other_desc : 北京大学
             * trip_route_type : 登山
             * trip_state : 1
             * hot_index : 0
             * biz_user_name : 陈鑫陈鑫
             * trip_days : 4
             * user_create_group_id : 1481536558300
             */

            private DataBean data;
            private long uid;
            /**
             * user_data : {"user_name":"陈鑫陈鑫","profile_pic_url":"http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600","gender":1,"user_desc":"描述描述","user_location":"天津","verification_type":1,"user_tags":["标签、标签1"],"birthday":439660800000,"user_cover_pic_url":"www.baidu.com1","province":"山东"}
             * id : 995062101114881
             * created_at : 1479351923757
             * updated_at : 1481785634484
             */

            private UserResultBean user_result;

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

            public UserResultBean getUser_result() {
                return user_result;
            }

            public void setUser_result(UserResultBean user_result) {
                this.user_result = user_result;
            }

            public static class DataBean implements Serializable{
                private String trip_name;
                private String trip_cover_pic;
                private int participants_limit_count;
                private long start_time;
                private long end_time;
                private long participate_end_time;
                private int trip_price;
                private int participants_male_count;
                private int participants_female_count;
                private String meeting_place;
                private String meeting_city;
                private String meeting_province;
                private String trip_intro;
                private String trip_equipment_intro;
                private String other_desc;
                private String trip_route_type;
                private int trip_state;
                private int hot_index;
                private String biz_user_name;
                private int trip_days;
                private long user_create_group_id;
                private List<String> trip_tags;

                public String getTrip_name() {
                    return trip_name;
                }

                public void setTrip_name(String trip_name) {
                    this.trip_name = trip_name;
                }

                public String getTrip_cover_pic() {
                    return trip_cover_pic;
                }

                public void setTrip_cover_pic(String trip_cover_pic) {
                    this.trip_cover_pic = trip_cover_pic;
                }

                public int getParticipants_limit_count() {
                    return participants_limit_count;
                }

                public void setParticipants_limit_count(int participants_limit_count) {
                    this.participants_limit_count = participants_limit_count;
                }

                public long getStart_time() {
                    return start_time;
                }

                public void setStart_time(long start_time) {
                    this.start_time = start_time;
                }

                public long getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(long end_time) {
                    this.end_time = end_time;
                }

                public long getParticipate_end_time() {
                    return participate_end_time;
                }

                public void setParticipate_end_time(long participate_end_time) {
                    this.participate_end_time = participate_end_time;
                }

                public int getTrip_price() {
                    return trip_price;
                }

                public void setTrip_price(int trip_price) {
                    this.trip_price = trip_price;
                }

                public int getParticipants_male_count() {
                    return participants_male_count;
                }

                public void setParticipants_male_count(int participants_male_count) {
                    this.participants_male_count = participants_male_count;
                }

                public int getParticipants_female_count() {
                    return participants_female_count;
                }

                public void setParticipants_female_count(int participants_female_count) {
                    this.participants_female_count = participants_female_count;
                }

                public String getMeeting_place() {
                    return meeting_place;
                }

                public void setMeeting_place(String meeting_place) {
                    this.meeting_place = meeting_place;
                }

                public String getMeeting_city() {
                    return meeting_city;
                }

                public void setMeeting_city(String meeting_city) {
                    this.meeting_city = meeting_city;
                }

                public String getMeeting_province() {
                    return meeting_province;
                }

                public void setMeeting_province(String meeting_province) {
                    this.meeting_province = meeting_province;
                }

                public String getTrip_intro() {
                    return trip_intro;
                }

                public void setTrip_intro(String trip_intro) {
                    this.trip_intro = trip_intro;
                }

                public String getTrip_equipment_intro() {
                    return trip_equipment_intro;
                }

                public void setTrip_equipment_intro(String trip_equipment_intro) {
                    this.trip_equipment_intro = trip_equipment_intro;
                }

                public String getOther_desc() {
                    return other_desc;
                }

                public void setOther_desc(String other_desc) {
                    this.other_desc = other_desc;
                }

                public String getTrip_route_type() {
                    return trip_route_type;
                }

                public void setTrip_route_type(String trip_route_type) {
                    this.trip_route_type = trip_route_type;
                }

                public int getTrip_state() {
                    return trip_state;
                }

                public void setTrip_state(int trip_state) {
                    this.trip_state = trip_state;
                }

                public int getHot_index() {
                    return hot_index;
                }

                public void setHot_index(int hot_index) {
                    this.hot_index = hot_index;
                }

                public String getBiz_user_name() {
                    return biz_user_name;
                }

                public void setBiz_user_name(String biz_user_name) {
                    this.biz_user_name = biz_user_name;
                }

                public int getTrip_days() {
                    return trip_days;
                }

                public void setTrip_days(int trip_days) {
                    this.trip_days = trip_days;
                }

                public long getUser_create_group_id() {
                    return user_create_group_id;
                }

                public void setUser_create_group_id(long user_create_group_id) {
                    this.user_create_group_id = user_create_group_id;
                }

                public List<String> getTrip_tags() {
                    return trip_tags;
                }

                public void setTrip_tags(List<String> trip_tags) {
                    this.trip_tags = trip_tags;
                }
            }

            public static class UserResultBean implements Serializable{
                /**
                 * user_name : 陈鑫陈鑫
                 * profile_pic_url : http://ofdx4t772.bkt.clouddn.com/1024134751518731?imageView2/1/w/600/h/600
                 * gender : 1
                 * user_desc : 描述描述
                 * user_location : 天津
                 * verification_type : 1
                 * user_tags : ["标签、标签1"]
                 * birthday : 439660800000
                 * user_cover_pic_url : www.baidu.com1
                 * province : 山东
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
                    private String profile_pic_url;
                    private int gender;
                    private String user_desc;
                    private String user_location;
                    private String verification_type;
                    private long birthday;
                    private String user_cover_pic_url;
                    private String province;
                    private List<String> user_tags;

                    public String getUser_name() {
                        return user_name;
                    }

                    public void setUser_name(String user_name) {
                        this.user_name = user_name;
                    }

                    public String getProfile_pic_url() {
                        return profile_pic_url;
                    }

                    public void setProfile_pic_url(String profile_pic_url) {
                        this.profile_pic_url = profile_pic_url;
                    }

                    public int getGender() {
                        return gender;
                    }

                    public void setGender(int gender) {
                        this.gender = gender;
                    }

                    public String getUser_desc() {
                        return user_desc;
                    }

                    public void setUser_desc(String user_desc) {
                        this.user_desc = user_desc;
                    }

                    public String getUser_location() {
                        return user_location;
                    }

                    public void setUser_location(String user_location) {
                        this.user_location = user_location;
                    }

                    public String getVerification_type() {
                        return verification_type;
                    }

                    public void setVerification_type(String verification_type) {
                        this.verification_type = verification_type;
                    }

                    public long getBirthday() {
                        return birthday;
                    }

                    public void setBirthday(long birthday) {
                        this.birthday = birthday;
                    }

                    public String getUser_cover_pic_url() {
                        return user_cover_pic_url;
                    }

                    public void setUser_cover_pic_url(String user_cover_pic_url) {
                        this.user_cover_pic_url = user_cover_pic_url;
                    }

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
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
