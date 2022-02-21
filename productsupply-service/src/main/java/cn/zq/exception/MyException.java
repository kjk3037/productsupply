package cn.zq.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@ControllerAdvice
public class MyException {
    //无权限异常处理
    @ExceptionHandler(value=AuthorizationException.class)
    public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response,Exception e) throws IOException {
        response.sendRedirect("/user/permission/unautho");
    }
}
