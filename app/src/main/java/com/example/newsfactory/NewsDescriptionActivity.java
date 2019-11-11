package com.example.newsfactory;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class NewsDescriptionActivity extends AppCompatActivity {

    private static final String TAG = "NewsDescriptionActivity";
    private Toolbar toolbar;
    private TextView toolbarTitle;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_description);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        getIncomingIntent();
    }


    private void getIncomingIntent(){
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("title") && getIntent().hasExtra("content")){

            String imageURL = getIntent().getStringExtra("image_url");
            String title = getIntent().getStringExtra("title");
            String content = getIntent().getStringExtra("content");

            putData(imageURL,title,content);

        }
    }


    private void putData(String imageURL,String title,String content){
        toolbarTitle.setText(title);
        TextView tvTitle = findViewById(R.id.tvTitleNewsDescription);
        tvTitle.setText(title);

        ImageView imImage = findViewById(R.id.ivImageNewsDescription);

        
        Glide.with(this)
                .asBitmap()
                .load(imageURL)
                .into(imImage);

        TextView tvContent = findViewById(R.id.tvContentNewsDescription);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvContent.setText(Html.fromHtml(content));
        }
    }
}
