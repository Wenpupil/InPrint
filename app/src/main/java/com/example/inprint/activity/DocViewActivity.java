package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.myview.MyGestureListener;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.StatusBarUtil;
import com.example.inprint.util.WordUtil;

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

/**
 * 查看文档
 */
public class DocViewActivity extends AppCompatActivity implements View.OnClickListener{
    private int rate;  //0表示docx，1表示doc
    //创建生成的文件地址
    private static String docName = "123.docx";
    private static String docPath="/storage/emulated/0/";

    private String activityName;

    private WordUtil wu;
    private WebView webView;
    private ImageView back;
    private TextView title;

    private GestureDetectorCompat mDetector;
    private MyGestureListener myGestureListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_view);
        overridePendingTransition(R.anim.doc_view_from_right,R.anim.no_slide);
        intentData();
        displayFile();
        iniAnim();
    }
    private void iniAnim(){
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.END);
        slide.setDuration(300);
        getWindow().setEnterTransition(slide);
        iniTouch();                            //初始化手势操作
    }
    private void iniTouch(){
        myGestureListener = new MyGestureListener(this);
        mDetector = new GestureDetectorCompat(this, myGestureListener);
    }
    //获取启用此活动的数据
    private void intentData(){
        StatusBarUtil.hideStatusBar(this);
        Intent intent = getIntent();
        docPath = intent.getStringExtra("docUrl");
        docName = intent.getStringExtra("docName");
        activityName = intent.getStringExtra("activityName");

        //WebView加载显示本地html文件
        webView = findViewById(R.id.office);
        back = findViewById(R.id.iv_back);
        title = findViewById(R.id.title);

        title.setText(docName.substring(0,docName.lastIndexOf('.')));
        back.setOnClickListener(this);

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
        String savePath= this.getFilesDir().getPath();
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
     * @throws IOException 输出异常
     */
    public void docx2Html(String fileName, String outPutFile)
            throws IOException {
        String tempPath=this.getFilesDir().getPath();
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
        boolean result = outFile.getParentFile().mkdirs();
        if(!result){
            LogUtil.d("文档解析","docx2Html() mkdirs()");
        }
        OutputStream out = new FileOutputStream(outFile);
        XHTMLConverter.getInstance().convert(document, out, options);
        System.out.println("Generate " + fileOutName + " with " +
                (System.currentTimeMillis() - startTime) + " ms.");
    }
    @Override
    public void onClick(View view){
        finish();
    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.doc_view_from_left,R.anim.doc_view_out_right);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}