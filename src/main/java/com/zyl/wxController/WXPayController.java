package com.zyl.wxController;

import com.alibaba.fastjson.JSON;
import com.zyl.utils.WXUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WXPayController {



    // 小程序appid
    public static final String appid = "wx803aa66d1bb25581";
    // app 密钥
    public static final String secret = "cb4deae5c7325b04548a2bd3a3414702";
    // 微信支付的商户id
    public static final String mch_id = "1520538691";
    // 微信支付的商户密钥
    public static final String key = "cb4deae5c7325b04548a2bd3a3414702";
    // 支付成功后的服务器回调url
    public static final String notify_url = "http://39.96.49.212/notifi"; //自己域名加回调接口 需要配置授权目录
    // 签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    // 交易类型，小程序支付的固定值为JSAPI
    public static final String trade_type = "JSAPI";
    // 微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 商品描述
    public static final String body = "商品描述";
    // 交易类型 ： 这里是微信小程序支付
    public static final String JSAPI = "JSAPI";


    /**
     * 支付接口
     *
     * @param openid  用户唯一标识
     * @return
     */
    @RequestMapping(value = "pay", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String pay(HttpServletRequest request, String openid) {


        // 商户订单号--->订单号唯一
        //这里用时间戳模拟  商户订单号
        String out_trade_no = System.currentTimeMillis()+"";
        // 生成商户订单类      预支付信息组装    用于存入数据库
        /**
         * 这里省略 ....个人生成自己的 订单类存入数据
         */
        // 生成随机串
        String nonce_str = WXUtils.getRandomStringByLength(32);
        // 获取用户后台IP地址(即使用微信小程序的手机IP)
        String spbill_create_ip = WXUtils.getIpAddr(request);


        // 组装参数，用户生成统一下单接口的签名
        Map<String, String> preParams = new HashMap<String, String>();

        //attach可以存储你想要在回调中获取的数据
        String attach = out_trade_no;


        preParams.put("appid", appid);
        preParams.put("attach", attach);
        preParams.put("mch_id", mch_id);
        preParams.put("nonce_str", nonce_str);
        preParams.put("body", body);
        preParams.put("out_trade_no", out_trade_no);
        preParams.put("total_fee", "1");// hospital.getHosPrice()
        preParams.put("spbill_create_ip", spbill_create_ip);// 用户ip
        preParams.put("notify_url", notify_url);
        preParams.put("trade_type", trade_type);
        preParams.put("openid", openid);


        // 参数按照参数名ASCII码从小到大排序拼接 （即key1=value1&key2=value2…）
        Map<String, String> cleanSignArray = WXUtils.cleanSignArray(preParams);
        String preSign = WXUtils.createLinkString(cleanSignArray);


        System.out.println(preSign);
        // MD5运算生成签名，这里是第一次签名，用于调用统一下单接口 --签名时 需要全部转换编码
        System.out.println(WXUtils.sign(preSign, key, "utf-8"));
        String fistSign = WXUtils.sign(preSign, key, "utf-8").toUpperCase(); // 问题商户密钥


        // 拼接统一下单接口使用的xml数据，和params存储的数据一样，并且排列顺序安装a.b.c.d...的从小到大顺序排列  要将上一步生成的签名fistSign一起拼接进去
        String xml = "<xml>" + "<appid><![CDATA[" + appid + "]]></appid>" + "<attach><![CDATA[" + attach
                + "]]></attach>" + "<body><![CDATA[" + body + "]]></body>" + "<mch_id><![CDATA[" + mch_id
                + "]]></mch_id>" + "<nonce_str><![CDATA[" + nonce_str + "]]></nonce_str>" + "<notify_url><![CDATA["
                + notify_url + "]]></notify_url>" + "<openid><![CDATA[" + openid + "]]></openid>"
                + "<out_trade_no><![CDATA[" + out_trade_no + "]]></out_trade_no>" + "<spbill_create_ip><![CDATA["
                + spbill_create_ip + "]]></spbill_create_ip>" + "<total_fee><![CDATA[1]]></total_fee>"
                + "<trade_type><![CDATA[" + JSAPI + "]]></trade_type>" + "<sign><![CDATA[" + fistSign + "]]></sign>"
                + "</xml>";


        System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);


        // 调用统一下单接口，并接受返回的结果 ---》发送请求时 参数需要转换统一编码
        String result = WXUtils.httpRequest(pay_url, "POST", xml);


        System.out.println("调试模式_统一下单接口 返回XML数据：" + result);


        // 这里解析的是xml数据
        Map returnMap = WXUtils.doXMLParse(result);
        // 返回状态码
        String return_code = (String) returnMap.get("return_code");


        System.out.println("返回状态码" + return_code);
        // 返回给小程序端的结果
        Map<String, Object> respone = new HashMap<String, Object>();
        // 返回给小程序端需要的参数
        Map<String, String> data = new HashMap<String, String>();


        // 有返回结果
        if (return_code.equals("SUCCESS")) {
            // 业务结果
            String result_code = (String) returnMap.get("result_code");


            if (!"SUCCESS".equals(result_code)) {
                //业务结果为fail
                respone.put("code", 1);
                String err_code_des = (String) returnMap.get("err_code_des");
                respone.put("msg", err_code_des);
            }


            String prepay_id = (String) returnMap.get("prepay_id");
            data.put("appId", appid);
            data.put("timeStamp", System.currentTimeMillis() + "");
            data.put("nonceStr", "m9fil9bt27e49ag1jz54vtxffwci7e08");
            data.put("package", "prepay_id=" + prepay_id);
            data.put("signType", "MD5");


            // 拼接签名需要的参数
            // 参数按照参数名ASCII码从小到大排序拼接 （即key1=value1&key2=value2…）
            Map<String, String> signTemp = WXUtils.cleanSignArray(data);
            // 生成签名字符串
            String tempSign = WXUtils.createLinkString(signTemp);
            // 再次签名，这个签名用于小程序端调用wx.requesetPayment方法
            String resSign = WXUtils.sign(tempSign, key, "utf-8");
            data.put("paySign", resSign);


            respone.put("data", data);
            respone.put("code", 0);
            respone.put("msg", "success");
        } else {
            //返回结果失败
            respone.put("code", 1);
            String return_msg = (String) returnMap.get("return_msg");
            respone.put("msg", return_msg);
        }
        //返回的数据用于小 程序  调起支付  或 展示 错误信息
        return JSON.toJSONString(respone);
    }

    /**
     * 微信通知后台的接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "notifi")
    @ResponseBody
    public void notifi(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 当收到通知进行处理时，应检查对应业务数据的状态，判断该通知是否已经处理过
         * 涉及查询数据库 这里省略
         * 直接默认未处理
         */
        String resXml = "";
        try {
            // 通过ServletInputStream读取http请求传入的数据
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((ServletInputStream) request.getInputStream()));
            String temp = null;
            // 获取微信返回的xml
            StringBuffer wxRes = new StringBuffer();
            while ((temp = br.readLine()) != null) {
                wxRes.append(temp);
            }
            br.close();
            // 处理微信返回的xml数据
            String notityXml = wxRes.toString();
            System.out.println("接收到微信的返回值（notityXml）：" + notityXml);
            Map map = WXUtils.doXMLParse(notityXml);
            // 判断返回结果
            String returnCode = (String) map.get("return_code");
            System.out.println("微信返回结果returnCode：" + returnCode);
            if ("SUCCESS".equals(returnCode)) {
                // 判断result_code--->交易是否成功需要查看result_code来判断
                String result_code = (String) map.get("result_code");
                System.out.println("微信业务返回结果result_code：" + result_code);
                if ("SUCCESS".equals(result_code)) {
                    // 成功
                    // 获取签名
                    String notitySign = (String) map.get("sign");
                    // 去除原sign
                    map.remove("sign");

                    // 对微信传回的数据进行2次签名验证
                    if (WXUtils.verify(WXUtils.createLinkString(map), notitySign, key, "utf-8")) {
                        // 修改订单信息 --根据订单号order_id
                        /**
                         * 修改 根据out_trade_no(我们自己的订单号-->从微信返回的XML中获取 然后修改数据库的订单记录下面三项作为举例)
                         *
                         * 支付时间(pay_time)
                         * 支付状态(order_status=1，支付成功)
                         * 微信流水号(wexin_serial_num)
                         *
                         */
                        // 获取参数
                        // 微信支付订单号
                        String wxOrderId = (String) map.get("transaction_id");
                        // 商户订单号(我们自己的订单号)
                        String out_trade_no = (String) map.get("out_trade_no");
                        // 支付时间
                        String payTime = (String) map.get("time_end");
                        // 生成order类
                       /* HosOrder order = new HosOrder();
                        order.setOrderId(out_trade_no);
                        order.setPayTime(DateKit.localDateTime2Date(payTime));
                        order.setOrderStatus(1);
                        order.setWexinSerialNum(wxOrderId);*/
                        // 修改订单信息
                        /**
                         * 根据serviceImpl  修改 对应订单数据
                         */

                        // 通知微信服务器已经处理成功
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        //签名2次验证失败
                        System.out.println("微信签名验证失败------");
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                                + "<return_msg><![CDATA[签名验证失败！]]></return_msg>" + "</xml> ";
                    }
                } else {
                    //微信业务结果失败
                    String err_code_des = (String) map.get("err_code_des");
                    System.out.println("微信业务结果失败，错误信息err_code_des：" + err_code_des);
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA["
                            + err_code_des + "]]></return_msg>" + "</xml> ";
                }

            }

            System.out.println("微信支付回调数据结束resXml:" + resXml);
            // 返回结果
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

}
