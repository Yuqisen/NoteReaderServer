package com.cn.yy.reader.server.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 实行时情报.
 *
 * @author Yuqisen
 * @since 2020/05/04
 */
public class RuntimeInfo {

  /**
   * 本地线程安全变量.
   */
  private static final ThreadLocal<RuntimeInfo> INSTANCE = new ThreadLocal<>();

  /**
   * the thread safe runtime info map.
   */
  private Map<String, String> infos = new HashMap<>();

  /**
   * 自定义缓存对象
   * @return 自定义缓存对象
   */
  private static Map<String, String> getContext() {
    if (INSTANCE.get() == null) {
      INSTANCE.set(new RuntimeInfo());
    }
    return INSTANCE.get().infos;
  }

  /**
   * 设定客户端IMEI
   * @param imei 客户端IMEI
   */
  public static void setImei(String imei) {
    getContext().put("RUNTIMEI_INFO_IMEI", imei);
  }

  /**
   * 获取客户端IMEI
   * @return 客户端IMEI
   */
  public static String getImei() {
    return getContext().get("RUNTIMEI_INFO_IMEI");
  }
}
