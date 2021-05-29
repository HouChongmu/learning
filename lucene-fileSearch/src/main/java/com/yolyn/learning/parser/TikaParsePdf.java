package com.yolyn.learning.parser;

import org.apache.commons.exec.environment.EnvironmentUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.core.env.Environment;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2021/5/29 9:02 PM
 * @project lucene-fileSearch
 * @description
 */
public class TikaParsePdf {
    public static void main(String[] args) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        PDFParser parser = new PDFParser();
        parser.parse(new TikaParsePdf().getClass().getClassLoader().getResourceAsStream("files/中国人工智能大会CCAI 2016圆满落幕.pdf"), handler, metadata, parseContext);
        for (String name : metadata.names()) {
            System.out.println(name + ":" + metadata.get(name));
        }
        System.out.println("文档内容");
        System.out.println(handler.toString());

    }
}
