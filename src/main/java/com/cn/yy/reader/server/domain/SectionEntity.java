package com.cn.yy.reader.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 章节信息。
 *
 * @author Yuqisen
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="section")
public class SectionEntity extends AbstractEntity {

  /** 标题 */
  @Column
  String name;

  /** 地址 */
  @Column
  String url;

  /** 本地保存地址 */
  @Column
  String path;

  /** 序号 */
  @Column
  int sectionIndex;

  /** 小说物理ID */
  @Column
  Long noteId;
}
