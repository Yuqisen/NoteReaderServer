package com.cn.yy.reader.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 小说信息。
 *
 * @author Yuqisen
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="note")
public class NoteEntity extends AbstractEntity {

  /** 标题 */
  @Column
  String name;

  /** 地址 */
  @Column
  String url;

  /** 作者 */
  @Column
  String author;

  /** 类型 */
  @Column
  String noteType;

  /** 更新时间 */
  @Column
  String lastUpdateTime;

  /** 最新章节 */
  @Column
  String lastSection;

  /** 本地保存地址 */
  @Column
  String path;
}
