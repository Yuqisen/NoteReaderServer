package com.cn.yy.reader.server.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 章节查询条件.
 *
 * @author Yuqisen
 */
@Data
public class SectionPageable implements Serializable {

  /** 查询页码 */
  int page;

  /** 数据ID */
  Long id;
}
