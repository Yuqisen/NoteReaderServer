package com.cn.yy.reader.server.web;

import com.cn.yy.reader.server.domain.ClientRecordEntity;
import com.cn.yy.reader.server.domain.NoteEntity;
import com.cn.yy.reader.server.domain.SectionEntity;
import com.cn.yy.reader.server.model.SectionDisplay;
import com.cn.yy.reader.server.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小说控制器.
 *
 * @author Yuqisen
 */
@RequestMapping("/note")
@RestController
public class NoteController {

  @Autowired
  NoteService noteService;

  @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<NoteEntity> findNoteList(@RequestBody NoteEntity entity) {
    return noteService.findNoteList(entity.getName());
  }

  @GetMapping(value = "/localList", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<NoteEntity> findLocalNoteList() {
    return noteService.findLocalNoteList();
  }

  @GetMapping(value = "/localSection/{noteId}/{page}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<SectionEntity> findLocalSectionList(@PathVariable Long noteId, @PathVariable int page) {
    return noteService.findLocalSectionList(noteId, page);
  }

  @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void deleteNote(@RequestBody SectionEntity info) {
    noteService.deleteNote(info.getNoteId());
  }

  @GetMapping(value = "/getSection/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public SectionDisplay getSectionFile(@PathVariable Long id) {
    return noteService.getSectionPath(id);
  }

  @PostMapping(value = "/downloadSection", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void downloadSection(@RequestBody NoteEntity entity) {
    this.noteService.downloadSection(entity);
  }

  @PostMapping(value = "/updateRecord", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void updateClientRecord(@RequestBody ClientRecordEntity entity) {
    this.noteService.updateClientRecord(entity);
  }
}
