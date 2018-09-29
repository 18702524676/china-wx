package com.wx.core.response;





import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.wx.core.constant.SystemConstant.JsonDataField.MESSAGE;
import static com.wx.core.constant.SystemConstant.JsonDataField.STATUS;


/**
 * 请求返回的json格式对象
 * 2018-7-1
 * @author wx
 */
@Data
public class JsonData implements Serializable {

	private static final long serialVersionUID = -1106356951410452378L;

	/** 状态 */
	private Integer status;

	/** 提示信息 */
	private String message;

	/** 请求结果 */
	private Object data;

	private JsonData(){}

	/**
	 * @methodName: ResultMsgSuccess
	 * @author: wx
	 * @description: 成功
	 * @param message 消息
	 * @param data    数据
	 * @date: 2018/8/13
	 * @return: com.wx.mypermission.common.response.ResultMsg
	 */
	public static JsonData ResultMsgSuccess(String message, Object data){
		JsonData jsonData = new JsonData();
		jsonData.setStatus(HttpStatus.OK.value());
		jsonData.setMessage(message);
		jsonData.setData(data);
		return jsonData;
	}

	/**
	 * @methodName: ResultMsgSuccess
	 * @author: wx
	 * @description: 成功
	 * @param data 数据
	 * @date: 2018/8/19
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	public static JsonData ResultMsgSuccess(Object data){
		return ResultMsgSuccess(null,data);
	}

	/**
	 * @methodName: ResultMsgSuccess
	 * @author: wx
	 * @description: 成功
	 * @param message 消息
	 * @date: 2018/8/19
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	public static JsonData ResultMsgSuccess(String message){
		return ResultMsgSuccess(message,null);
	}

	/**
	 * @methodName: ResultMsgError
	 * @author: wx
	 * @description: 失败异常
	 * @param message 消息
	 * @date: 2018/8/13
	 * @return: com.wx.mypermission.common.response.ResultMsg
	 */
	public static JsonData ResultMsgError(String message){
		return ResultMsgError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}
	/**
	 * @methodName: ResultMsgError
	 * @author: wx
	 * @description: 失败异常
	 * @param status 状态码
	 * @param message 异常消息
	 * @date: 2018/8/13
	 * @return: com.wx.mypermission.common.response.JsonData
	 */
	public static JsonData ResultMsgError(int status, String message){
		JsonData jsonData = new JsonData();
		jsonData.setStatus(status);
		jsonData.setMessage(message);
		return jsonData;

	}

	/**
	 * @methodName: toMap
	 * @author: wx
	 * @description: 返回错误信息map类型
	 * @param message 错误信息
	 * @param status 错误状态
	 * @date: 2018/8/13
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 */
	public static Map<String,Object> toMap(Integer status,String message){
		Map<String,Object> map = new HashMap<>();
		map.put(STATUS,status);
		map.put(MESSAGE,message);
		return  map;
	}


}

