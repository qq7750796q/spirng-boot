package com.zyl.ExceptionHander;

import com.zyl.common.customException;
import com.zyl.common.CustomResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/** 全局定义异常类  抛出异常时候 全局异常类会自动拦截
 * Created by z1761 on 2018/10/10.
 * 注意 ：：：：此处一加 控制台也不会打印错误信息，只有日志会输出一行异常
 */
@ControllerAdvice
public class ResultExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CustomResult handleException(HttpServletRequest request, Exception e) {
        if (e instanceof customException) {
            customException personException = (customException) e;
            return new CustomResult(personException.getCode(), personException.getMsg());
        }
        return new CustomResult(-1, "未知错误");
    }

}
