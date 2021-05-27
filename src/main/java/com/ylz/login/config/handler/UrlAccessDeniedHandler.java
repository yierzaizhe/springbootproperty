package com.ylz.login.config.handler;

import com.ylz.common.enums.ResultCode;
import com.ylz.common.utils.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ylz
 * @Description
 * @date 2021-05-27-17:12
 */
@Component
public class UrlAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private Handel403 handel403;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        handel403.out(response, ResultTool.fail(ResultCode.USER_NOT_LOGIN));
        //ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        //handel403.result(ResultTool.fail(ResultCode.USER_NOT_LOGIN));
    }
}
