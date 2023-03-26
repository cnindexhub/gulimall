package com.atguigu.gulimall.product.advice;

import com.atguigu.common.utils.BizCodeEnum;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DengPengFei
 * @Description
 * @email DengTPengTFei@qq.com
 * @date 2023-03-24 23:30
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.atguigu.gulimall.product.controller"})
public class ExceptionAdvice {

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("异常信息：{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        List<String> errorMessages = bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(), BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("errorData", errorMessages);
    }

    @ExceptionHandler({ Throwable.class })
    public R handleUnKnowException(Throwable throwable) {
        log.error("异常信息：{}", throwable.getMessage());
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(), BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }


}
