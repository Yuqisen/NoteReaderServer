package com.cn.yy.reader.server.repository;

import com.cn.yy.reader.server.domain.NoteEntity;
import com.cn.yy.reader.server.domain.SectionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 小说数据库操作类.
 *
 * @author Yuqisen
 */
@Repository
public interface NoteRepository extends PagingAndSortingRepository<NoteEntity, Long> {

  /**
   * 按照名称查询已有小说列表.
   *
   * @param name 小说名称
   * @return 小说列表
   */
  List<NoteEntity> findAllByNameLikeOrderByInsertTimeDesc(String name);

  /**
   * 查询所有小说记录.
   *
   * @return 所有小说记录
   */
  List<NoteEntity> findAllByNameIsNotNullOrderByInsertTimeDesc();

  /**
   * 查询小说信息。
   *
   * @param name 小说名称
   * @param url 小说访问地址
   * @return 小说信息.
   */
  NoteEntity findFirstByNameEqualsAndUrlEquals(String name, String url);
}
