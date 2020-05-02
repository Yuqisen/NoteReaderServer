package com.cn.yy.reader.server.repository;

import com.cn.yy.reader.server.domain.SectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 章节信息数据库操作类.
 *
 * @author Yuqisen
 */
@Repository
public interface SectionRepository extends PagingAndSortingRepository<SectionEntity, Long> {

  /**
   * 按照小说查询章节目录.
   *
   * @param noteId 小说ID
   * @return 章节目录信息
   */
  List<SectionEntity> findAllByNoteIdEqualsOrderBySectionIndexAsc(Long noteId);

  /**
   * 按照小说查询章节分页目录.
   * @param noteId 小说ID
   * @param pageable 分页条件
   * @return 章节列表
   */
  Page<SectionEntity> findAllByNoteIdEqualsOrderBySectionIndexAsc(Long noteId, Pageable pageable);

  /**
   * 按照小说ID和序号查询章节信息.
   * @param noteId 小说ID
   * @param index 序号
   * @return 章节信息
   */
  SectionEntity findFirstByNoteIdEqualsAndSectionIndexEquals(Long noteId, int index);
}
