package com.cn.yy.reader.server.repository;

import com.cn.yy.reader.server.domain.ClientRecordEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 阅读记录数据库操作类.
 *
 * @author Yuqisen
 */
@Repository
public interface ClientRecordRepository extends PagingAndSortingRepository<ClientRecordEntity, Long> {

  /**
   * 根据小说ID查询最后阅读记录.
   * @param noteId 小说ID
   * @param imei 客户端标识符
   * @return 最后阅读记录
   */
  ClientRecordEntity findFirstByNoteIdEqualsAndImeiEquals(Long noteId, String imei);
}
