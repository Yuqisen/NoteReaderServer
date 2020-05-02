package com.cn.yy.reader.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 小说信息.
 *
 * @author Yuqisen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteInfo implements Serializable {

  /** 名称 */
  private String name;

  /** 地址 */
  private String url;

  /** 作者 */
  private String author;

  /** 类型 */
  private String noteType;

  /** 更新时间 */
  private String lastUpdateTime;

  /** 最新章节 */
  private String lastSection;
}
