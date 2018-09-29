package com.wx.commons.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wx.core.exception.BusinessJsonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.wx.commons.enums.error.CommonEnumError.JsonConvertError.*;
import static com.wx.commons.utils.PARAM_CONSTANT.*;


/**
 * @Author wx
 * @Description json工具类常量
 * @Date 2018-08-19
 */
interface PARAM_CONSTANT {
	String PARAM_ERR_1 = "json字符串为空";

	String PARAM_ERR_2 = "clazz为空";

	String PARAM_ERR_3 = "泛型为空";

	String PARAM_ERR_4 = "typeReference为空";

	String PARAM_ERR_5 = "map为空";

	String PARAM_ERR_6 = "对象为空";
}

/**
 * @ClassName JsonUtils
 * @Author wx
 * @Description Json转换工具类
 * @Date 2018-08-19-0:17
 */
@Slf4j
public class JsonUtils {
	private static final ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国上海时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 序列化时，日期的统一格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	}

	/**
     * @methodName: toObject
     * @author: wx
     * @description: json字符串转换为对象
     * @param json json字符串
     * @param clazz 对象模板
     * @date: 2018/8/19
     * @return: T
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            BusinessValidatorUtils.isTure(clazz != null,PARAM_ERR_2);
            if (StringUtils.isBlank(json)){
                return clazz.newInstance();
            }
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BusinessJsonException(JSON_CONVERT_ERROR_0);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
            throw new BusinessJsonException(JSON_CONVERT_ERROR_0);
        } catch (InstantiationException e) {
            log.error(e.getMessage());
            throw new BusinessJsonException(JSON_CONVERT_ERROR_0);
        }
    }

	/**
	 * @methodName: toJson
	 * @author: wx
	 * @description: 对象转换成json字符串
	 * @param entity 对象
	 * @date: 2018/8/19
	 * @return: java.lang.String
	 */
	public static String toJson(Object entity) {
		try {
			BusinessValidatorUtils.notNull(entity, PARAM_ERR_6);
			return objectMapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
			throw new BusinessJsonException(JSON_CONVERT_ERROR_1);
		}

	}

	/**
	 * @methodName: toCollection
	 * @author: wx
	 * @description: json字符串转换成带泛型对象
	 * @param json json字符串
	 * @param typeReference 具体类型
	 * @date: 2018/8/19
	 * @return: T
	 */
	public static <T> T toCollection(String json, TypeReference<T> typeReference) {
		try {
			BusinessValidatorUtils.notBlank(json, PARAM_ERR_1);
			BusinessValidatorUtils.notNull(typeReference, PARAM_ERR_4);
			return objectMapper.readValue(json, typeReference);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new BusinessJsonException(JSON_CONVERT_ERROR_2);
		}

	}

	/**
	 * @methodName: json2map
	 * @author: wx
	 * @description: json字符串转换为map
	 * @param jsonStr json字符串
	 * @date: 2018/8/19
	 * @return: java.util.Map<java.lang.String,java.lang.Object>
	 */
	public static Map<String, Object> json2map(String jsonStr) {
		try {
			BusinessValidatorUtils.notBlank(jsonStr, PARAM_ERR_1);
			return objectMapper.readValue(jsonStr, Map.class);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new BusinessJsonException(JSON_CONVERT_ERROR_3);
		}
	}

	/**
	 * @methodName: json2map
	 * @author: wx
	 * @description: json字符串转换为指定类型的Map
	 * @param jsonStr json字符串
	 * @param clazz 类型class
	 * @date: 2018/8/19
	 * @return: java.util.Map<java.lang.String,T>
	 */
	public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
		BusinessValidatorUtils.notBlank(jsonStr, PARAM_ERR_1);
		BusinessValidatorUtils.isTure(clazz != null, PARAM_ERR_2);
		Map<String, T> result = new HashMap();
		try {
			Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
			});
			map.forEach((k, v) -> {
				result.put(String.valueOf(v), map2pojo(v, clazz));
			});
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new BusinessJsonException(JSON_CONVERT_ERROR_4);
		}

		return result;
	}

	/**
	 * @methodName: json2list
	 * @author: wx
	 * @description: json字符串转换为指定类型的list
	 * @param jsonArrayStr json字符串{{}，{}}
	 * @param clazz 类型class
	 * @date: 2018/8/19
	 * @return: java.util.List<T>
	 */
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
		BusinessValidatorUtils.notBlank(jsonArrayStr, PARAM_ERR_1);
		BusinessValidatorUtils.isTure(clazz != null, PARAM_ERR_2);
		List<T> result = new ArrayList<T>();
		try {
			List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
			});
			list.forEach(map -> {
				result.add(map2pojo(map, clazz));
			});
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new BusinessJsonException(JSON_CONVERT_ERROR_5);
		}

		return result;
	}

	/**
	 * @methodName: map2pojo
	 * @author: wx
	 * @description: Map转换为对象
	 * @param map map
	 * @param clazz 类型class
	 * @date: 2018/8/19
	 * @return: T
	 */
	public static <T> T map2pojo(Map map, Class<T> clazz) {
		BusinessValidatorUtils.isTure(!CollectionUtils.isEmpty(map), PARAM_ERR_5);
		BusinessValidatorUtils.isTure(clazz != null, PARAM_ERR_2);
		try {
			return objectMapper.convertValue(map, clazz);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessJsonException(JSON_CONVERT_ERROR_6);
		}

	}

}
