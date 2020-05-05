package com.cn.yy.reader.server.web;

import com.cn.yy.reader.server.domain.AppVersionEntity;
import com.cn.yy.reader.server.model.AppVersionUpdateRequestModel;
import com.cn.yy.reader.server.model.AppVersionUpdateResponseModel;
import com.cn.yy.reader.server.service.AppVersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版本号升级控制器.
 *
 * @author Yuqisen
 * @since 2020/05/05
 */
@RestController
@Slf4j
@RequestMapping("/sys/app")
public class AppManagerController {
  @Autowired
  AppVersionService service;

  /**
   * 验证是否有最新版本信息.
   * @return 验证结果
   */
  @GetMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public AppVersionUpdateResponseModel update(AppVersionUpdateRequestModel request) {
    log.debug("app update app id: {}", request.getAppId());
    log.debug("app update app version: {}", request.getVersion());
    AppVersionEntity entity = service.findByAppId(request.getAppId());
    AppVersionUpdateResponseModel model = new AppVersionUpdateResponseModel();
    model.setApkUrl(entity.getApkPath());
    model.setUpdate(entity.getAppVersion() > request.getVersion());
    return model;
  }
}
