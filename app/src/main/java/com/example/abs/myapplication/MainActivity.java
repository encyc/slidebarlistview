package com.example.abs.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.erning.slidebarlistview.SimpleSlideBarListAdapter;
import com.erning.slidebarlistview.SlideBarListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    private SlideBarListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        final ArrayList<String> datalist = new ArrayList<>();
        datalist.add("abd");
        datalist.add("abc");
        datalist.add("我不好");
        datalist.add("555");
        datalist.add("4444");
        datalist.add("lllll");
        datalist.add("你好吧(）");
        datalist.add("你好吧");
        datalist.add("zzzz");
        datalist.add("zzzzc");
        datalist.add("你好吧）");
        datalist.add("你好啊");
        datalist.add("abd");
        datalist.add("abc");
        datalist.add("我不好");
        datalist.add("555");
        datalist.add("4444");
        datalist.add("lllll");
        datalist.add("你好吧(）");
        datalist.add("你好吧");
        datalist.add("zzzz");
        datalist.add("zzzzc");
        datalist.add("你好吧）");
        datalist.add("你好啊");

        final SimpleSlideBarListAdapter adapter = new SimpleSlideBarListAdapter(this, datalist);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, datalist.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        list.setOnSlideBarChangeLintener(new SlideBarListView.OnSlideBarChangeLintener() {
            @Override
            public void OnSlideBarChange(int index, char latter) {
                adapter.scroll(list,latter);
            }
        });
    }
}
