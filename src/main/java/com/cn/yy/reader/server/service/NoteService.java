package com.cn.yy.reader.server.service;

import com.cn.yy.reader.server.client.BiqugeClient;
import com.cn.yy.reader.server.common.config.AppProperties;
import com.cn.yy.reader.server.config.RuntimeInfo;
import com.cn.yy.reader.server.domain.ClientRecordEntity;
import com.cn.yy.reader.server.domain.NoteEntity;
import com.cn.yy.reader.server.domain.SectionEntity;
import com.cn.yy.reader.server.mapper.NoteMapper;
import com.cn.yy.reader.server.model.LocalNoteInfo;
import com.cn.yy.reader.server.model.NoteInfo;
import com.cn.yy.reader.server.model.SectionDisplay;
import com.cn.yy.reader.server.model.SectionInfo;
import com.cn.yy.reader.server.repository.ClientRecordRepository;
import com.cn.yy.reader.server.repository.NoteRepository;
import com.cn.yy.reader.server.repository.SectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 小说相关服务程序.
 *
 * @author Yuqisen
 */
@Service
@Slf4j
public class NoteService {

  @Autowired
  NoteRepository noteRepository;

  @Autowired
  SectionRepository sectionRepository;

  @Autowired
  ClientRecordRepository clientRecordRepository;

  @Autowired
  NoteMapper noteMapper;

  @Autowired
  AppProperties appProperties;

  /**
   * 查询小说列表.
   * @param keyword 关键字
   * @return 小说列表
   */
  @Transactional(rollbackOn = Exception.class)
  public List<NoteEntity> findNoteList(String keyword) {
    List<NoteEntity> retList = new ArrayList<>();
    List<NoteInfo> webList = BiqugeClient.findNoteList(keyword);
    for (NoteInfo noteInfo : webList) {
      if (noteInfo.getName().contains(keyword) || noteInfo.getAuthor().contains(keyword)) {
        NoteEntity entity = new NoteEntity();
        entity.setName(noteInfo.getName());
        entity.setUrl(noteInfo.getUrl());
        entity.setAuthor(noteInfo.getAuthor());
        entity.setNoteType(noteInfo.getNoteType());
        entity.setLastUpdateTime(noteInfo.getLastSection());
        entity.setLastSection(noteInfo.getLastSection());
        retList.add(entity);
      }
    }
    return retList;
  }

  /**
   * 查询本地小说列表
   * @return 本地小说列表
   */
  public List<LocalNoteInfo> findLocalNoteList() {
//    System.err.println(RuntimeInfo.getImei());
//    List<ClientNoteEntity> clientNotes = clientNoteRepository.findAllByImeiEqualsOrderByInsertTimeDesc(RuntimeInfo.getImei());
//    return noteRepository.findAllByNameIsNotNullOrderByInsertTimeDesc();
    return noteMapper.findAll(RuntimeInfo.getImei());
  }

  /**
   * 删除小说.
   * @param id 数据ID
   */
  @Transactional(rollbackOn = Exception.class)
  public void deleteNote(Long id) {
    if (noteRepository.existsById(id)) {
      noteRepository.deleteById(id);
    }
  }

  /**
   * 下载小说章节信息.
   * @param entity 小说记录
   * @return 下载结果
   */
  @Transactional(rollbackOn = Exception.class)
  public boolean downloadSection(NoteEntity entity) {
    NoteEntity noteEntity = noteRepository.findFirstByNameEqualsAndUrlEquals(entity.getName(), entity.getUrl());
    if (noteEntity == null) {
      noteEntity = noteRepository.save(entity);
    }
    List<SectionEntity> list = sectionRepository.findAllByNoteIdEqualsOrderBySectionIndexAsc(noteEntity.getId());
    Map<String, Integer> nameMap = list.stream().collect(Collectors.toMap(SectionEntity::getName, SectionEntity::getSectionIndex));
    List<SectionInfo> webList = BiqugeClient.findSectionList(noteEntity.getUrl());
    int sectionIndex = 0;
    if (!list.isEmpty()) {
      sectionIndex = list.get(list.size()-1).getSectionIndex();
    }
    List<SectionEntity> insertData = new ArrayList<>();
    for (SectionInfo sectionInfo : webList) {
      if (!nameMap.containsKey(sectionInfo.getName())) {
        sectionIndex++;
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setNoteId(noteEntity.getId());
        sectionEntity.setSectionIndex(sectionInfo.getIndex());
        sectionEntity.setName(sectionInfo.getName());
        sectionEntity.setUrl(sectionInfo.getUrl());
        sectionEntity.setSectionIndex(sectionIndex);
        insertData.add(sectionEntity);
      }
    }
    if (insertData.size() > 0) {
      sectionRepository.saveAll(insertData);
    }
    return true;
  }

  /**
   * 查询小说章节列表（本地缓存）.
   * @param noteId 小说ID
   * @param searchPage 查询页码
   * @return 小说章节列表
   */
  public List<SectionEntity> findLocalSectionList(Long noteId, int searchPage) {
    Sort sort = new Sort(Sort.Direction.ASC, "sectionIndex");
    Pageable pageable = PageRequest.of(searchPage,20, sort);
    Page<SectionEntity> page = sectionRepository.findAllByNoteIdEqualsOrderBySectionIndexAsc(noteId, pageable);
    return page.get().collect(Collectors.toList());
  }

  /**
   * 获取小说详细信息.
   * @param id 详细ID
   * @return 小说内容
   */
  public SectionDisplay getSectionPath(Long id) {
    SectionDisplay info = new SectionDisplay();
    SectionEntity entity = sectionRepository.findById(id).get();
    String url = entity.getUrl();
    SectionEntity beforeEntity = sectionRepository.findFirstByNoteIdEqualsAndSectionIndexEquals(entity.getNoteId(), entity.getSectionIndex() - 1);
    SectionEntity afterEntity = sectionRepository.findFirstByNoteIdEqualsAndSectionIndexEquals(entity.getNoteId(), entity.getSectionIndex() + 1);
    info.setSectionDetail(BiqugeClient.findSectionDetail(url));
    info.setBeforeId(beforeEntity == null ? -1 : beforeEntity.getId());
    info.setBeforeName(beforeEntity == null ? org.apache.commons.lang3.StringUtils.EMPTY : beforeEntity.getName());
    info.setAfterId(afterEntity == null ? -1 : afterEntity.getId());
    info.setAfterName(afterEntity == null ? org.apache.commons.lang3.StringUtils.EMPTY : afterEntity.getName());
    return info;
  }

  /**
   * 记录用户阅读信息。
   *
   * @param entity 阅读记录
   */
  public void updateClientRecord(ClientRecordEntity entity) {
    ClientRecordEntity dbEntity = clientRecordRepository.findFirstByNoteIdEqualsAndImeiEquals(entity.getNoteId(), entity.getImei());
    if (dbEntity == null) {
      dbEntity = new ClientRecordEntity();
      dbEntity.setNoteId(entity.getNoteId());
      dbEntity.setImei(entity.getImei());
    }
    dbEntity.setSectionId(entity.getSectionId());
    clientRecordRepository.save(dbEntity);
  }
}
