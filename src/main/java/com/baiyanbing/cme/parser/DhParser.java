package com.baiyanbing.cme.parser;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.tokenizer.Word;
import cn.hutool.http.HtmlUtil;
import com.baiyanbing.cme.domain.Subtitle;
import com.baiyanbing.cme.service.SubtitleService;
import com.baiyanbing.cme.utils.FileSourceParser;
import com.baiyanbing.cme.utils.StringTools;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyanbing
 * @createAt 2019/12/17 11:09
 */
@Component
public class DhParser {

    @Autowired
    SubtitleService subtitleService;



    public void parse(String fileDir) throws Exception {
        File[] files = FileUtil.ls(fileDir);

        List<Subtitle> subtitleList = new ArrayList<>();

        for(File file : files){

            String fileName = file.getName();
            int season = Integer.parseInt(fileName.substring(1,3));
            int episode = Integer.parseInt(fileName.substring(4,6));


            int row = 0;

            InputStream in = FileUtil.getInputStream(file);
            List<String> lines = FileSourceParser.parseRtf(in);
            for(int i = 0; i < lines.size(); i++){
                String line = lines.get(i);
                Subtitle subtitle = new Subtitle();
                subtitle.setContent(line);
                subtitle.setTvName("绝望的主妇");
                subtitle.setEpisode(episode);
                subtitle.setSeason(season);
                subtitle.setRow(++row);
                subtitleList.add(subtitle);

                if(subtitleList.size() == 100){
                    subtitleService.saveBatch(subtitleList);
                    subtitleList = new ArrayList<>();
                    System.out.println("---" + row);
                    continue;
                }

                if(i + 1 == lines.size()){
                    subtitleService.saveBatch(subtitleList);
                    subtitleList = new ArrayList<>();
                    System.out.println("compeleted !!!");
                }
            }
        }
    }






}
