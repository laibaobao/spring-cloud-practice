package com.example.interceptor;

import com.example.controller.CustomerContronller;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Enumeration;

public class FeginIterceptor implements RequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(FeginIterceptor.class);

    /**
     * 原生fegin客户端发出请求时，只会发声明的基本信息。
     * 如果想要填充请求，可以通过拦截器的方式将信息回填至请求之中
     * 底层依旧是使用了resttemplate类来实现HTTP请求
     * 说明：
     * 该服务发出请求时，会被当前拦截器拦截下来
     * 该拦截器会将本次请求的header信息重新填入请求之中
     * 同时，需要注入实现RequestInterceptor类保证拦截
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("Request has been in RequestInterceptor");

        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest httpRequest = servletRequestAttributes.getRequest();
        Enumeration<String> enumeration = httpRequest.getHeaderNames();

        if (httpRequest!=null){
            while (enumeration.hasMoreElements()){
                String name = enumeration.nextElement();
                String value = httpRequest.getHeader(name);
//
//                Enumeration<String> values = httpRequest.getHeaders(name);
//                while (values.hasMoreElements()) {
//                    String value = values.nextElement();
//                    requestTemplate.header(name, value);
//                }


               if(name.contains("cookie")) {
                   String session = "SESSION";
                   String[] cookie = value.split("[=]");
                   requestTemplate.header(session,cookie[cookie.length-1]);
                //   logger.info("encode cookie is "+ session+"="+base64Decode(cookie[cookie.length-1]));
               }
                requestTemplate.header(name,value);

            }
        }

    }

    /**
     * Decode the value using Base64.
     * @param base64Value the Base64 String to decode
     * @return the Base64 decoded value
     * @since 1.2.2
     */
    private String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
            return new String(decodedCookieBytes);
        }
        catch (Exception e) {
            return null;
        }
    }


}
