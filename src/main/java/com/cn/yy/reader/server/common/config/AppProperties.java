package com.cn.yy.reader.server.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目自定义信息.
 *
 * @author Yuqisen
 */
@Data
@Component
@ConfigurationProperties(prefix = AppProperties.PROPERTY_PREFIX)
public class AppProperties {

  public static final String PROPERTY_PREFIX = "app.server";

  /** 文件上传路径 */
  String uploadPath;
}
