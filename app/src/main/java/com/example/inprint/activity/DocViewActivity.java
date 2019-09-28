package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.inprint.R;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.StatusBarUtil;
import com.example.inprint.util.WordUtil;
import com.google.android.material.appbar.AppBarLayout;

import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * 查看文档
 */
public class DocViewActivity extends AppCompatActivity {
    //创建生成的文件地址
    private static String docName = "123.docx";
    private static String docPath="/storage/emulated/0/";
    private int rate;  //0表示docx，1表示doc

    private static final String savePath="/data/user/0/com.example.inprint/files/";
    private static final String tempPath="/data/user/0/com.example.inprint/files/";

    private WordUtil wu;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_view);
        intentData();
        displayFile();
    }
    //获取启用此活动的数据
    private void intentData(){
        StatusBarUtil.hideStatusBar(this);
        Intent intent=getIntent();
        AppBarLayout appBarLayout = findViewById(R.id.al_actionbar);
        docPath=intent.getStringExtra("docUrl");
        docName=intent.getStringExtra("docName");

        //WebView加载显示本地html文件
        webView = (WebView) this.findViewById(R.id.office);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);// 设置WebView可触摸放大缩小
        settings.setUseWideViewPort(true);

        String rates = docName.substring(docName.lastIndexOf('.')+1);
        if(rates.equals("docx")){
            rate = 0;
        }else{
            rate = 1;
        }
    }
    private void displayFile()
    {
        String name = docName.substring(0, docName.indexOf("."));
        if(rate == 0) {
            try {
                if (!(new File(savePath + name).exists()))
                    new File(savePath + name).mkdirs();
                LogUtil.d("性能分析", "开始解析文档");
                docx2Html(docPath + docName, savePath + name + ".html");
                LogUtil.d("性能分析", "文档解析完毕");
            } catch (Exception e) {
                LogUtil.d("fileView", "没有找到文档");
                e.printStackTrace();
            }
            webView.loadUrl("file://" + savePath + name + ".html");
        }else{
            String path = docPath + docName;
            wu = new WordUtil(path);
            webView.loadUrl("file://" + wu.htmlPath);
        }
    }
    /**
     * docx格式word转换为html
     *
     * @param fileName
     *            docx文件路径
     * @param outPutFile
     *            html输出文件路径
     * @throws TransformerException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static void docx2Html(String fileName, String outPutFile)
            throws TransformerException, IOException, ParserConfigurationException {
        String fileOutName = outPutFile;
        long startTime = System.currentTimeMillis();
        XWPFDocument document = new XWPFDocument(new FileInputStream(fileName));
        XHTMLOptions options = XHTMLOptions.create().indent(4);
        // 导出图片
        File imageFolder = new File(tempPath);
        options.setExtractor(new FileImageExtractor(imageFolder));
        // URI resolver
        options.URIResolver(new FileURIResolver(imageFolder));
        File outFile = new File(fileOutName);
        outFile.getParentFile().mkdirs();
        OutputStream out = new FileOutputStream(outFile);
        XHTMLConverter.getInstance().convert(document, out, options);
        System.out.println("Generate " + fileOutName + " with " +
                (System.currentTimeMillis() - startTime) + " ms.");
    }
}