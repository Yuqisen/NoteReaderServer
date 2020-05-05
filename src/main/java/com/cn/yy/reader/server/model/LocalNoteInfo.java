package com.cn.yy.reader.server.model;

import com.cn.yy.reader.server.domain.NoteEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * XXXXX 程序.
 *
 * @author Yuqisen
 * @since 2020/05/04
 */
@Setter
@Getter
public class LocalNoteInfo extends NoteEntity {

  long lastSectionId;
}
