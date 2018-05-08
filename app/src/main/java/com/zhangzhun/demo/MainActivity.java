package com.zhangzhun.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhangzhun.demo.library.AutoClickListener;
import com.zhangzhun.demo.library.AutoClickableTextView;

public class MainActivity extends AppCompatActivity {

    AutoClickableTextView textView;
    String testText = "啊哈哈哈哈哈哈\u200b这是第一篇文章\u200b啊哈哈哈哈哈,,啊哈哈哈哈哈哈\u200b这是第二篇爆炸文章\u200b啊哈哈哈哈哈";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.span_text);
        textView.setText(testText);
        textView.setAutoClickListener(new AutoClickListener() {
            @Override
            public void onClick(String article) {
                Toast.makeText(MainActivity.this, article, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
