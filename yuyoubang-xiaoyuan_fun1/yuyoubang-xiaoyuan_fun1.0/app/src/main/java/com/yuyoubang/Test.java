package com.yuyoubang;

import java.util.Map;

/**
 * Created by ZYSZYS on 2017/4/19.18:18
 */

public class Test {

    public static String edbTradeAdd() {
//        EdbLib edb = new EdbLib();
//        Map<String, String> params = edb.edbGetCommonParams("edbTradeAdd");
        StringBuilder builder = new StringBuilder();
        builder.append("<info>");
        builder.append("<orderInfo>");
        builder.append("<out_tid>20549778</out_tid>");
        builder.append("<shop_id>2</shop_id>");
        builder.append("<storage_id>1</storage_id>");
        builder.append("<buyer_id>王菁雯</buyer_id>");
        builder.append("<buyer_msg>【发件人: 韦林红】要求西安发货，麻烦确保质量，谢谢</buyer_msg>");
        builder.append("<seller_remark/>");
        builder.append("<consignee>王菁雯</consignee>");
        builder.append("<telephone>13588217105</telephone>");
        builder.append("<privince>陕西省</privince>");
        builder.append("<city>西安市</city>");
        builder.append("<area>高新区</area>");
        builder.append("<address>陕西省西安市高新区丈八五路46号长盛科技产业园</address>");
        builder.append("<actual_freight_get>0</actual_freight_get>");
        builder.append("<is_COD>0</is_COD>");
        builder.append("<order_totalMoney>138</order_totalMoney>");
        builder.append("<product_totalMoney>138</product_totalMoney>");
        builder.append("<is_invoiceOpened>0</is_invoiceOpened>");
        builder.append("<favorable_money>0</favorable_money>");
        builder.append("<terminal_type>微信</terminal_type>");
        builder.append("<pay_method>微信支付</pay_method>");
        builder.append("<out_payNo>20549778_02081653</out_payNo>");
        builder.append("<pay_date>2016-08-02 08:16</pay_date>");
        builder.append("<order_date>2016-08-02 08:16</order_date>");
        builder.append("<express>韵达</express>");
        builder.append("<pay_status>已付款</pay_status>");
        builder.append("</orderInfo>");

        builder.append("<orderInfo>");
        builder.append("<out_tid>20549779</out_tid>");
        builder.append("<shop_id>2</shop_id>");
        builder.append("<storage_id>2</storage_id>");
        builder.append("<buyer_id>王菁雯</buyer_id>");
        builder.append("<buyer_msg>【发件人: 韦林红】要求西安发货，麻烦确保质量，谢谢</buyer_msg>");
        builder.append("<seller_remark/>");
        builder.append("<consignee>王菁雯</consignee>");
        builder.append("<telephone>13588217105</telephone>");
        builder.append("<privince></privince>");
        builder.append("<city></city>");
        builder.append("<area></area>");
        builder.append("<address>陕西省西安市高新区丈八五路46号长盛科技产业园</address>");
        builder.append("<actual_freight_get>0</actual_freight_get>");
        builder.append("<is_COD>0</is_COD>");
        builder.append("<order_totalMoney>138</order_totalMoney>");
        builder.append("<product_totalMoney>138</product_totalMoney>");
        builder.append("<is_invoiceOpened>0</is_invoiceOpened>");
        builder.append("<favorable_money>0</favorable_money>");
        builder.append("<terminal_type>微信</terminal_type>");
        builder.append("<pay_method>微信支付</pay_method>");
        builder.append("<out_payNo>20549778_02081653</out_payNo>");
        builder.append("<pay_date>2016-08-02 08:16</pay_date>");
        builder.append("<order_date>2016-08-02 08:16</order_date>");
        builder.append("<express>韵达</express>");
        builder.append("<pay_status>已付款</pay_status>");
        builder.append("</orderInfo>");

        builder.append("<product_info>");
        builder.append("<product_item>");
        builder.append("<barCode>TM160729191844710</barCode>");
        builder.append("<product_title>二阳锅巴</product_title>");
        builder.append("<standard>麻辣（4袋）X2份</standard>");
        builder.append("<favorite_money>0</favorite_money>");
        builder.append("<orderGoods_Num>0</orderGoods_Num>");
        builder.append("<cost_Price>46</cost_Price>");
        builder.append("<out_tid>20549778</out_tid>");//对应orderInfo中的out_tid表示订单明细
        builder.append("</product_item>");
        builder.append("<product_item>");
        builder.append("<out_tid>20549778</out_tid>");//对应orderInfo中的out_tid表示订单明细
        builder.append("<barCode>TM160729192034450</barCode>");
        builder.append("<product_title>二阳锅巴</product_title>");
        builder.append("<standard>五香（4袋）X1份</standard>");
        builder.append("<favorite_money>0</favorite_money>");
        builder.append("<orderGoods_Num>1</orderGoods_Num>");
        builder.append("<cost_Price>46</cost_Price>");
        builder.append("</product_item>");

        builder.append("<product_item>");

        builder.append("<barCode>TM160729191844710</barCode>");
        builder.append("<product_title>二阳锅巴</product_title>");
        builder.append("<standard>麻辣（4袋）X2份</standard>");
        builder.append("<favorite_money>0</favorite_money>");
        builder.append("<orderGoods_Num>0</orderGoods_Num>");
        builder.append("<cost_Price>46</cost_Price>");
        builder.append("<out_tid>20549779</out_tid>");//对应orderInfo中的out_tid表示订单明细
        builder.append("</product_item>");
        builder.append("<product_item>");
        builder.append("<out_tid>20549779</out_tid>");//对应orderInfo中的out_tid表示订单明细
        builder.append("<barCode>TM160729192034450</barCode>");
        builder.append("<product_title>二阳锅巴</product_title>");
        builder.append("<standard>五香（4袋）X1份</standard>");
        builder.append("<favorite_money>0</favorite_money>");
        builder.append("<orderGoods_Num>1</orderGoods_Num>");
        builder.append("<cost_Price>46</cost_Price>");
        builder.append("</product_item>");

        builder.append("</product_info>");
        builder.append("</info>");
         return  builder.toString();
//        params.put("xmlValues", builder.toString());
//        String res = edb.edbRequstPost(params);
        // String res = edb.name(params);
    }
    public static void main(String args[]){

        System.out.print( edbTradeAdd());
    }
}
