package com.ylz.login.config.handler;

import com.alibaba.fastjson.JSON;
import com.ylz.common.entity.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author ylz
 * @Description
 * @date 2021-05-27-17:52
 */
@Slf4j
@Component
public class Handel403 {
    /**
     * 使用response输出JSON
     *
     * @param response
     * @param result
     */
    public static void out(ServletResponse response, JsonResult  result) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("响应报错", e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
