package com.baiyanbing.cme.parser;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.baiyanbing.cme.domain.Subtitle;
import com.baiyanbing.cme.service.SubtitleService;
import com.baiyanbing.cme.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyanbing
 * @createAt 2019/12/17 11:09
 */
@Component
public class Friends04Parser {


    public void parse(String fileDir){
        String startWith = "Dialogue";
        String replaceStr[] =  {"{\\fe134}","{\\fs20}","{\\fn方正黑体简体}", "{\\fad(500", "{\\fad(500,500)}", "{\\c&HFFFFFF&}", "{\\pos(190,286)}", "{\\fs16}", "{\\fn方正准圆简体}", "{\\bord0}", "{\\fs24}", "{\\fn方正综艺简体}", "{\\fe0}", "{\\c&H26F4FF&}", "{\\fe134}", "{\\fn方正宋黑简体}", "{\\c&HFFAE1A&}", "{\\fscy100}", "{\\fe134}", "{\\fn方正准圆简体}", "{\\c&HFFFFFF&}", "{\\fs16}", "{\\fn方正准圆简体}", "{\\fs14}", "{\\b0}", "{\\bord0}", "{\\shad0}", "{\\c&HFFFFFF&}", "{\\3c&H2F2F2F&}", "{\\4c&H000000&}", "{\\fsp0}", "{\\fs16}", "{\\c&HFFFFFF&}", "{\\c&H26F4FF&}", "{\\fn方正综艺简体}", "{\\fs24}", "{\\fn方正综艺简体}", "{\\fs14}", "{\\b0}", "{\\bord0}", "{\\shad0}", "{\\c&HFFFFFF&}", "{\\3c&H2F2F2F&}", "{\\4c&H000000&}"};;


        File[] files = FileUtil.ls(fileDir);
        for(File file : files){
            if(file.isDirectory()){
                continue;
            }

//            System.out.println(file.getName());

            String fileName = file.getName();
            int season = 4;
            int episode = Integer.parseInt(fileName.substring(13,15));

            int row = 0;

            List<Subtitle> subtitleList = new ArrayList<>();

            List<String>  list =  FileUtil.readUtf8Lines(file);

            for(int i = 0; i < list.size(); i++){

                String line = list.get(i);

                if(StrUtil.startWith(line, startWith)){
                    String content = StrUtil.split(line, ",")[9];
                    content = HtmlUtil.cleanHtmlTag(content);
                    content = StringTools.remove(content, replaceStr);

                    if(StrUtil.isBlank(content)){
                        continue;
                    }

                    Subtitle subtitle = new Subtitle();
                    subtitle.setContent(content);
                    subtitle.setTvName("绝望的主妇");
                    subtitle.setEpisode(episode);
                    subtitle.setSeason(season);
                    subtitle.setRow(++row);
                    subtitleList.add(subtitle);


                }
            }
        }
    }


    public static void main(String[] args) {
        Friends04Parser parser = new Friends04Parser();
        parser.parse("/users/Jobin/老友记");
    }



}
