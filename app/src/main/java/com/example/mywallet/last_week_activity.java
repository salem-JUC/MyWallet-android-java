package com.example.mywallet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class last_week_activity extends AppCompatActivity {
    RecyclerView recyclerVu ;
    LinkedList<FinancialOperation> weekList;
    LocalDateTime now = LocalDateTime.now();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_week_activity);
        getWeekList();
        recyclerVu = findViewById(R.id.recycleVu);
        recyclerVu.setAdapter(new RecycleViewAdaptor(this,weekList ));
        recyclerVu.setLayoutManager(new LinearLayoutManager(this));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    void getWeekList(){
        Iterator it = weekList.iterator();
        while (it.hasNext()){
            FinancialOperation x = (FinancialOperation) it.next();
            if(now.minusWeeks(1).isBefore(x.dateTime)){
                weekList.addFirst(x);
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    Date getNowTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        now.minusWeeks(1);
        return new Date(dtf.format(now)) ;
    }

}