package com.cn.yy.reader.server.client;

import com.cn.yy.reader.server.model.NoteInfo;
import com.cn.yy.reader.server.model.SectionDetail;
import com.cn.yy.reader.server.model.SectionInfo;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 笔趣阁查询客户端.
 *
 * @author Yuqisen
 */
public class BiqugeClient {

  public static void main(String...pStr) {
    BiqugeClient client = new BiqugeClient();
//    List<NoteInfo> list = client.findNoteList("花豹");
//    System.err.println(list);
//    String url = "https://www.xbiquge6.com/4_4298/";
//    List<SectionInfo> list = client.findSectionList(url);
//    System.err.println(list);
    SectionDetail detail = BiqugeClient.findSectionDetail("https://www.xbiquge6.com/4_4298/5039423.html");
    System.err.println(detail);
  }

  /**
   * 查询小说列表.
   *
   * @param keyword 查询关键字.
   *
   * @return 小说列表
   */
  public static List<NoteInfo> findNoteList(String keyword) {
    List<NoteInfo> list = new ArrayList<>();
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // 创建POST请求
    HttpGet httpGet = new HttpGet(ROOT_PATH + "/search.php?keyword=" + keyword);
    // 响应模型
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(httpGet);
      String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
      Document doc = Jsoup.parse(responseStr);
      Elements elements = doc.select(".result-item");
      for (int i=0; i<elements.size(); i++) {
        Element element = elements.get(i);
        String url = element.select(".result-item-title a").first().attr("href");
        String name = element.select(".result-item-title a").first().attr("title");
        Elements elementTags = element.select(".result-game-item-info-tag");
        String author = null, noteType = null, lastUpdateTime = null, lastSection = null;
        for (int m=0; m<elementTags.size(); m++) {
          String val = elementTags.get(m).text();
          if (StringUtils.startsWith(val, "作者")) {
            author = val.replaceAll("作者： ", StringUtils.EMPTY);
          } else if (StringUtils.startsWith(val, "类型")) {
            noteType = val.replaceAll("类型： ", StringUtils.EMPTY);
          } else if (StringUtils.startsWith(val, "更新时间")) {
            lastUpdateTime = val.replaceAll("更新时间： ", StringUtils.EMPTY);
          } else if (StringUtils.startsWith(val, "最新章节")) {
            lastSection = val.replaceAll("最新章节： ", StringUtils.EMPTY);
          }
        }
        list.add(new NoteInfo(name, url, author, noteType, lastUpdateTime, lastSection));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询章节目录.
   *
   * @param url 地址
   * @return 章节目录
   */
  public static List<SectionInfo> findSectionList(String url) {
    List<SectionInfo> list = new ArrayList<>();
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // 创建POST请求
    HttpGet httpGet = new HttpGet(url);
    // 响应模型
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(httpGet);
      String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
      Document doc = Jsoup.parse(responseStr);
      Elements elements = doc.select("dl dd a");
      for (int i=0; i<elements.size(); i++) {
        Element element = elements.get(i);
        String detUrl = element.attr("href");
        String name = element.text();
        list.add(new SectionInfo(i, name, ROOT_PATH + detUrl));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 查询章节文字内容.
   *
   * @param url 地址
   */
  public static SectionDetail findSectionDetail(String url) {
    SectionDetail detail = new SectionDetail();
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // 创建POST请求
    HttpGet httpGet = new HttpGet(url);
    // 响应模型
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(httpGet);
      String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
      Document doc = Jsoup.parse(responseStr);
      String title = doc.select(".content_read .bookname h1").first().text();
      String content = doc.select(".content_read #content").first().html();
      content = content.replaceAll("&nbsp;", "").replaceAll("<br>", "").replaceAll("<br/>", "").replaceAll("<br />", "");
      String[] contentArray = content.split("\n");
      List<String> contentList = new ArrayList<>();
      for (String s : contentArray) {
        if (!StringUtils.isEmpty(s)) {
          contentList.add(s);
        }
      }
      detail.setTitle(title);
      detail.setContent(contentList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return detail;
  }

  /** 基本路径 */
  private static final String ROOT_PATH = "https://www.xbiquge6.com";
}
