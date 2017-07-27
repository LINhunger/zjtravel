package com.zjtravel.web.exception;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.zjtravel.pojo.dto.RequestResult;
import com.zjtravel.web.enums.StatEnum;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by hunger on 2017/2/17.
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 没有权限异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public RequestResult<?> processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        return new RequestResult<Object>(StatEnum.DEFAULT_WRONG,e.getMessage());
    }

    /**
     * 重复主键
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView processDuplicateKeyException(NativeWebRequest request, DuplicateKeyException e) {
        ModelAndView mv = new ModelAndView();
        e.printStackTrace();
        mv.addObject("exception", "该名称已存在");
        mv.setViewName("unauthorized");
        return mv;
    }
}
