package com.cn.yy.reader.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 客户端版本管理Entity.
 *
 * @author Yuqisen
 * @since 2020/05/05
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="app_version")
public class AppVersionEntity extends AbstractEntity {

  /** 应用ID */
  @Column
  String appId;

  /** app名称 */
  @Column
  String appName;

  /** app版本号 */
  @Column
  int appVersion;

  /** 版本发布时间 */
  @Column
  Date releaseTime;

  /** apk路径 */
  @Column
  String apkPath;
}
