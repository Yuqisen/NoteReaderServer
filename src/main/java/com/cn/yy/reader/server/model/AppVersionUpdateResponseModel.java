package com.cn.yy.reader.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * APP版本号升级结果.
 *
 * @author Yuqisen
 * @since 2020/05/05
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionUpdateResponseModel implements Serializable {

  /** 是否需要升级标志 */
  boolean update;

  /** 最新版本APP地址 */
  String apkUrl;
}
