package com.cn.yy.reader.server.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 阅读信息。
 *
 * @author Yuqisen
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="client_record")
public class ClientRecordEntity extends AbstractEntity {

  /** 小说ID */
  Long noteId;

  /** 章节ID */
  Long sectionId;

  /** 客户端识别符 */
  String imei;
}
