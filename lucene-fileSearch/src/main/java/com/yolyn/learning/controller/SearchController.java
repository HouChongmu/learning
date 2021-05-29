package com.yolyn.learning.controller;

import com.yolyn.learning.filesearch.IKAnalyzer6x;
import com.yolyn.learning.model.FileModel;
import com.yolyn.learning.model.SearchResultModel;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.apache.lucene.queryparser.surround.parser.QueryParserConstants.N;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2021/5/29 9:52 PM
 * @project lucene-fileSearch
 * @description
 */
@RestController("/search")
public class SearchController {
    @GetMapping("/searchFileByKey")
    public List<SearchResultModel> search(String keyWord) {
        ArrayList<SearchResultModel> hitsList = new ArrayList<SearchResultModel>();
        //检索域
        String[] fields = {"title", "content"};
        Path indexPath = Paths.get("/Users/yolyn/Desktop/work/demo_workspace/learning/lucene-fileSearch/src/main/resources/indexdir");
        Directory dir;
        try {
            dir = FSDirectory.open(indexPath);
            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new IKAnalyzer6x();
            // QueryParser parser = new QueryParser(field, analyzer);
            MultiFieldQueryParser parser2 = new MultiFieldQueryParser(fields, analyzer);
            // 查询字符串
            Query query = parser2.parse(keyWord);
            TopDocs topDocs = searcher.search(query, N);
            // 定制高亮标签
            SimpleHTMLFormatter fors = new SimpleHTMLFormatter("<span style=\"color:red;font-weight:bold;\">", "</span>");
            QueryScorer scoreTitle = new QueryScorer(query, fields[0]);
            Highlighter hlqTitle = new Highlighter(fors, scoreTitle);// 高亮分析器
            QueryScorer scoreContent = new QueryScorer(query, fields[0]);
            Highlighter hlqContent = new Highlighter(fors, scoreTitle);// 高亮分析器
            TopDocs hits = searcher.search(query, 100);
            for (ScoreDoc sd : topDocs.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                String title = doc.get("title");
                String content = doc.get("content");
                TokenStream tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), sd.doc, fields[0],
                        new IKAnalyzer6x());// 获取tokenstream
                Fragmenter fragment = new SimpleSpanFragmenter(scoreTitle);
                hlqTitle.setTextFragmenter(fragment);
                String hl_title = hlqTitle.getBestFragment(tokenStream, title);// 获取高亮的片段，可以对其数量进行限制
                tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), sd.doc, fields[1],
                        new IKAnalyzer6x());
                fragment = new SimpleSpanFragmenter(scoreContent);
                hlqContent.setTextFragmenter(fragment);
                String hl_content = hlqTitle.getBestFragment(tokenStream, content);// 获取高亮的片段，可以对其数量进行限制
                SearchResultModel resultModel = new SearchResultModel(hl_title != null ? hl_title : title,
                        hl_content != null ? hl_content : content);
                resultModel.setDownloadUrl("/" + title);
                hitsList.add(resultModel);
            }
            dir.close();
            reader.close();
            return hitsList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
        return hitsList;
    }

    public static void main(String[] args) {
        List<SearchResultModel> resultModels = new SearchController().search("人工智能");
        resultModels.forEach(System.out::println);
    }
}
