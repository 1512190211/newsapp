package com.example.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private TextView text_send;
    private ImageView comment;
    private ImageView message;
    private TextView hide_down;
    private EditText comment_content;
    private LinearLayout rl_enroll;
    private LinearLayout rl_comment;
    private ListView comment_list;
    private CommentAdapetr adapterComment;
    private List<Comment> data;
    private WebView webView;
    private boolean isshow=true;
    private MyDatabaseHelper dbHelper;
    int datasize=1;

    public static String LINK_NAME="CONTENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        dbHelper=new MyDatabaseHelper(this,"MyComment.db",null,1);
        dbHelper.getWritableDatabase();

        initView();
        webView=(WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Intent intent=getIntent();
        String LinkName=intent.getStringExtra(LINK_NAME).toString();
        webView.loadUrl(LinkName);
    }

    public void initView(){
        // 初始化评论列表
        comment_list = (ListView) findViewById(R.id.comment_list);
        data = new ArrayList<>();
        adapterComment = new CommentAdapetr(getApplicationContext(), data);
        comment_list.setAdapter(adapterComment);
        comment = (ImageView) findViewById(R.id.comment);
        message=(ImageView)findViewById(R.id.message);
        hide_down = (TextView) findViewById(R.id.hide_down);
        comment_content = (EditText) findViewById(R.id.edit_text);
        text_send=(TextView)findViewById(R.id.text_send);
        rl_enroll = (LinearLayout) findViewById(R.id.rl_enroll);
        rl_comment = (LinearLayout) findViewById(R.id.rl_comment);
        setListener();
    }

    public void setListener(){
        comment.setOnClickListener(this);
        hide_down.setOnClickListener(this);
        text_send.setOnClickListener(this);
        message.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment:
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // 显示评论框
                rl_enroll.setVisibility(View.GONE);
                rl_comment.setVisibility(View.VISIBLE);
                break;
            case R.id.hide_down:
                // 隐藏评论框
                rl_enroll.setVisibility(View.VISIBLE);
                rl_comment.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                break;
            case R.id.text_send:
                sendComment();
                break;
            case R.id.message:
                if(isshow==true) {
                    //隐藏网页
                    webView.setVisibility(View.GONE);
                    comment_list.setVisibility(View.VISIBLE);
                    isshow=false;
                    break;
                }
                if(isshow==false){
                    //显示网页
                    webView.setVisibility(View.VISIBLE);
                    comment_list.setVisibility(View.GONE);
                    isshow=true;
                    break;
                }
            default:
                break;
        }
    }

        public void sendComment(){
            if(comment_content.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
            }else{
                // 生成评论数据
                Comment comment = new Comment();
                comment.setName("reviewer"+(data.size()+1)+"：");
                comment.setContent(comment_content.getText().toString());
                adapterComment.addComment(comment);
                // 发送完，清空输入框
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name","reviewer"+datasize+":");
                values.put("comment",comment_content.getText().toString());
                db.insert("Comment",null,values);
                values.clear();
                datasize++;
                comment_content.setText("");
                Toast.makeText(getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT).show();
            }
        }

}
