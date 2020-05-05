package com.cn.yy.reader.server.repository;

import com.cn.yy.reader.server.domain.ClientNoteEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * XXXXX 接口.
 *
 * @author Yuqisen
 * @since 2020/05/04
 */
@Repository
public interface ClientNoteRepository extends PagingAndSortingRepository<ClientNoteEntity, Long> {

  /**
   * 根据IMEI查找客户端记录
   * @param imei
   * @return
   */
  List<ClientNoteEntity> findAllByImeiEqualsOrderByInsertTimeDesc(String imei);
}
