package com.cn.yy.reader.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 章节目录.
 *
 * @author Yuqisen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionInfo implements Serializable {

  /** 序号 */
  int index;

  /** 名称 */
  String name;

  /** 地址 */
  String url;
}
