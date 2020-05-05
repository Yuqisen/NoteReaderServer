package com.cn.yy.reader.server.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域请求响应头过滤器 程序.
 *
 * @author Yuqisen
 * @since 2020/05/02
 */
@WebFilter(filterName = "CorsFilter ")
@Component
@Slf4j
public class CorsFilter implements Filter {
  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest reqs = (HttpServletRequest) req;
    response.setHeader("Access-Control-Allow-Origin","*");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    // 手机客户端IMEI过滤
    String imei = reqs.getHeader(IMEI);
    if (StringUtils.isEmpty(imei)) {
      imei = reqs.getHeader(IMEI.toLowerCase());
    }
    log.info("imei http: {}", imei);
    if (StringUtils.isEmpty(imei)) {
      imei = "863072039242081,863072039242099";
    }
    RuntimeInfo.setImei(imei);
    log.info("imei: {}", imei);
    chain.doFilter(reqs, response);
  }

  private static String IMEI = "IMEI";
}
