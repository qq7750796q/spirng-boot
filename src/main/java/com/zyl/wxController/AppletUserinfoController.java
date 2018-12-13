package com.zyl.wxController;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSONObject;

import com.zyl.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@Controller
public class AppletUserinfoController {

	
	public static final Log log = LogFactory.getLog(AppletUserinfoController.class);

	@Value("${applet.url}")
	private String url;
	@Value("${applet.appid}")
	private String appid;
	@Value("${applet.appSecret}")
	private String appSecret;
	@Value("${applet.grenType}")
	private String grenType;
	private static final String WATERMARK = "watermark";
	/**
	 * 获取进入app用户的信息
	 * @param
	 */
	@RequestMapping("/louguser.htm")
	@ResponseBody
	public Object saveAppUserInfo(HttpServletRequest request){
		String code = request.getParameter("code");//小程序前台调用登录返回的code
		 HashMap<String,Object> map = new HashMap<>();

		try {
			if (null == code|| !StringUtils.isNotBlank(code)) {
				throw new Exception();
			}
			String param = "appid="+appid+"&secret="+appSecret+"&js_code="+code+"&grant_type="+grenType;
			log.info("请求微信接口参数》》》》》》》》》》》》》》》》》》》"+param);
			String s1 = HttpUtils.sendPost(url, param, null);
			log.info("微信接口参数post请求返回结果》》》》》》》》》》》》》》》》》》》"+s1);
			JSONObject jsonObject = JSONObject.parseObject(s1);
			Object openid = jsonObject.get("openid");
			if (null != openid) {
					//openid 是微信小程序用户的唯一id 》》 存在 进行存储操作 返回当前程序存储的唯一标示 放入map

				map.put("openid",openid);//也可以返回用户的唯一标示 openid  但是不建议这样操作
			} else {
				//可返回状态码  也可抛出异常
			}

		}catch (Exception e) {
			//异常处理
		}
		//返回结果
		return map;

	}


}
