package com.cn.yy.reader.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 章节详细.
 *
 * @author Yuqisen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDetail implements Serializable {

  /** 标题 */
  String title;

  /** 文字 */
  List<String> content;
}
