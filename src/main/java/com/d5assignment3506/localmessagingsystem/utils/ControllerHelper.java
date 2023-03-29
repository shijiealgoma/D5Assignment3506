package com.d5assignment3506.localmessagingsystem.utils;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 
 * 
 * @author felix
 * @date下午7:52:05
 */
public class ControllerHelper {

    public static final Logger logger = LoggerFactory.getLogger(ControllerHelper.class);

    /**
     * 发送json格式数据
     * 
     * @throws IOException
     */
    public static void sendJson(HttpServletResponse response, String data){
        //response.setCharacterEncoding("UTF-8");
        response.setContentType("application/Json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        }catch (IOException e){
            logger.error(e.getMessage());
        }
        writer.write(data);
        writer.flush();
        writer.close();
    }

    public static void sendSuccessJson(HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        result.put("success", true);
        sendJson(response, result.toString()); 
    }


}
