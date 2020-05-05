package com.cn.yy.reader.server.repository;

import com.cn.yy.reader.server.domain.AppVersionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 客户端版本管理 JPA接口.
 *
 * @author Yuqisen
 * @since 2020/05/05
 */
@Repository
public interface AppVersionRepository extends PagingAndSortingRepository<AppVersionEntity, Long> {

  /**
   * 根据APP编号查询最新版本号数据信息.
   * @param appId APP编号
   * @return 最新版本信息
   */
  AppVersionEntity findDistinctFirstByAppIdEqualsOrderByAppVersionDesc(String appId);
}
