package com.cn.yy.reader.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据模型基本类.
 *
 * @author Yuqisen
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
  /** 物理ID */
  @javax.persistence.Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  Long id;

  /** 版本号 */
  @Version
  @JsonIgnore
  int version;

  /** 登录时间 */
  @CreatedDate
  @JsonIgnore
  Date insertTime;

  /** 登录人 */
  @CreatedBy
  @JsonIgnore
  Long insertUser;

  /** 更新时间 */
  @LastModifiedDate
  @JsonIgnore
  Date updateTime;

  /** 更新人 */
  @LastModifiedBy
  @JsonIgnore
  Long updateUser;
}
