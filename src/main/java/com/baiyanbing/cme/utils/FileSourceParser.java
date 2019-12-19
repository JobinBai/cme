package com.baiyanbing.cme.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyanbing
 * @createAt 2019-11-18 15:02
 */
public class FileSourceParser {


    public static List<String> parse(InputStream in, String fileType) throws Exception {
        switch (fileType){
            case "doc":
                return parseDoc(in);
            case "docx":
                return parseDocx(in);
            case "txt":
                return parseTxt(in);
            default:
                    break;
        }
        return null;
    }


    public static List<String> parseTxt(InputStream in) throws IOException {
        List<String> lines = new ArrayList<>();
        IoUtil.readLines(in, StandardCharsets.UTF_8, lines);
        return lines;
    }


    public static List<String> parseDocx(InputStream in) throws IOException {
        XWPFWordExtractor extractor = null;
        try{
            XWPFDocument xdoc = new XWPFDocument(in);
            extractor = new XWPFWordExtractor(xdoc);
            String text = extractor.getText();
            return StrUtil.split(text, '\n', true, true);
        } finally {
            if(extractor != null){
                extractor.close();
            }

        }
    }

    public static List<String> parseRtf(InputStream in) throws Exception {
        DefaultStyledDocument styledDoc = new DefaultStyledDocument();
        // 创建文件输入流
        new RTFEditorKit().read(in, styledDoc, 0);
        String  result = new String(styledDoc.getText(0, styledDoc.getLength()));
        List<String> list = CollectionUtil.newArrayList(result.split("\n"));
        return list;
    }


    public static List<String> parseDoc(InputStream in) throws IOException {
        HWPFDocument doc = null;
        try{
            doc = new HWPFDocument(in);
            String text = doc.getDocumentText();
            return StrUtil.split(text, '\n', true, true);
        } finally {
            if(doc != null){
                doc.close();
            }
            if(in != null){
                in.close();
            }
        }
    }
}
