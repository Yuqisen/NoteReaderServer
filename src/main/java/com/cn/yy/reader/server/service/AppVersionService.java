package com.cn.yy.reader.server.service;

import com.cn.yy.reader.server.domain.AppVersionEntity;
import com.cn.yy.reader.server.repository.AppVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户端版本管理 业务逻辑.
 *
 * @author Yuqisen
 * @since 2020/05/05
 */
@Service
public class AppVersionService {

  @Autowired
  AppVersionRepository repository;

  public AppVersionEntity findByAppId(String appId) {
    return repository.findDistinctFirstByAppIdEqualsOrderByAppVersionDesc(appId);
  }
}
