package com.cn.yy.reader.server.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 章节表示对象。
 *
 * @author Yuqisen
 */
@Data
public class SectionDisplay implements Serializable {

  /** 前章ID */
  Long beforeId;

  /** 前章名称 */
  String beforeName;

  /** 后章ID */
  Long afterId;

  /** 后章名称 */
  String afterName;

  /** 本章数据 */
  SectionDetail sectionDetail;
}
