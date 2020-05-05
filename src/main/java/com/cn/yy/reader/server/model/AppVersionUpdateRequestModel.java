package com.cn.yy.reader.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 版本号升级请求数据.
 *
 * @author Yuqisen
 * @since 2020/05/05
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionUpdateRequestModel implements Serializable {

  /** 应用编号 */
  String appId;

  /** 当前版本号 */
  int version;
}
