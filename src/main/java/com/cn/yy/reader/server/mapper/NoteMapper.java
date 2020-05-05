package com.cn.yy.reader.server.mapper;

import com.cn.yy.reader.server.model.LocalNoteInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小说查询接口.
 *
 * @author Yuqisen
 * @since 2020/05/04
 */
@Mapper
public interface NoteMapper {

  List<LocalNoteInfo> findAll(@Param("imei") String imei);
}
