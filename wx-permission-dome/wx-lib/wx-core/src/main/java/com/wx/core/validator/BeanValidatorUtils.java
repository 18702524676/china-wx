package com.wx.core.validator;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.ValidatorChain;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baidu.unbiz.fluentvalidator.exception.RuntimeValidateException;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baidu.unbiz.fluentvalidator.util.ArrayUtil;
import com.google.common.base.Preconditions;
import com.wx.core.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

/**
 * @ClassName BeanValidatorUtils
 * @Author wx
 * @Description 参数验证工具类(fluent-validator结合Hibernate Validator使用)
 * @Date 2018-08-14-22:37
 */
@Slf4j
public class BeanValidatorUtils {
	/** Hibernate Validator验证器(快速返回模式) */
	private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
		.buildValidatorFactory().getValidator();

	/**
	 * @methodName: validate
	 * @author: wx
	 * @description: 参数验证器
	 * @param parameter 验证参数
	 * @param groups 验证组
	 * @date: 2018/8/15
	 * @return: com.baidu.unbiz.fluentvalidator.Result
	 */
	private static Result validate(Object parameter, Class... groups) {
		Preconditions.checkNotNull(parameter, "Parameter object is null");
		Class<?> parameterType = parameter.getClass();
		FluentValidator fluentValidator = FluentValidator.checkAll(groups);
		Result result;
		// 如果是集合
		if (Collection.class.isAssignableFrom(parameterType)) {
			result = fluentValidator.failOver()
				.onEach((Collection) parameter, new HibernateSupportedValidator().setHiberanteValidator(validator))
				.doValidate().result(toSimple());
		}
		// 如果是数组
		else if (parameterType.isArray()) {
			result = fluentValidator.failOver().onEach(ArrayUtil.toWrapperIfPrimitive(parameter),
				new HibernateSupportedValidator().setHiberanteValidator(validator)).doValidate().result(toSimple());
		}
		// 如果是单个
		else {
			result = fluentValidator.on(parameter, new HibernateSupportedValidator().setHiberanteValidator(validator))
				.doValidate().result(toSimple());
		}
		return result;
	}

	/**
	 * @methodName: doCascadeValidate
	 * @author: wx
	 * @description: 执行级联校验
	 * @param parameter 验证对象
	 * @date: 2018/8/16
	 * @return: com.baidu.unbiz.fluentvalidator.Result
	 */
	private static Result doCascadeValidate(Object parameter) {
		Result result = null;
		try {
			Class<?> clazz = parameter.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				FluentValid fv = field.getAnnotation(FluentValid.class);
				if (fv == null) {
					continue;
				}
				field.setAccessible(true);
				Object obj = field.get(parameter);
				result = validate(obj, fv.groups());
			}
		} catch (Exception e) {
			log.error("参数级联校验出现异常：", e);
			throw new RuntimeValidateException("参数级联校验出现异常");
		}
		return result;
	}

	/**
	 * @methodName: validateList
	 * @author: wx
	 * @description: 自定义验证器验证处理 暂时不使用，不提供入口
	 * @param object 验证对象
	 * @param validatorHandler 自定义验证器
	 * @date: 2018/8/16
	 * @return: com.baidu.unbiz.fluentvalidator.Result
	 */
	private static Result validate(Object object, ValidatorHandler validatorHandler) {
		Result result = FluentValidator.checkAll().on(object, validatorHandler).doValidate().result(toSimple());
		return result;
	}

	/**
	 * @methodName: validateList
	 * @author: wx
	 * @description: 重载自定义验证器验证处理（验证器集合）暂时不使用，不提供入口
	 * @param object 验证对象
	 * @param validatorHandlers 自定义验证器集合
	 * @date: 2018/8/16
	 * @return: com.baidu.unbiz.fluentvalidator.Result
	 */
	private static Result validate(Object object, ValidatorHandler... validatorHandlers) {
		ValidatorChain chain = new ValidatorChain();
		chain.setValidators(Arrays.asList(validatorHandlers));
		Result result = FluentValidator.checkAll().on(object, chain).doValidate().result(toSimple());
		return result;
	}

	/**
	 * @methodName: validateObjectParam
	 * @author: wx
	 * @description: 对象参数验证入口
	 * @param object 验证对象
	 * @param groups 验证组
	 * @date: 2018/8/15
	 * @return: void
	 */
	public static void validateObjectParam(Object object, Class... groups) {
		Result result = validate(object, groups);
		if (result != null && result.isSuccess()) {
			result = doCascadeValidate(object);
		}
		if (result != null && !result.isSuccess()) {
			throw new ParamException(result.getErrors().toString());
		}
	}
}
