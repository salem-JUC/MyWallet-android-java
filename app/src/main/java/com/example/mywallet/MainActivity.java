package com.example.mywallet;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {


    LinkedList<FinancialOperation> list;
    Double budget;
    RecyclerView recyclerVu;
    EditText amountEdtTxt;
    TextView totalTxtVu, commentTxtVu;
    Button btnPls, btnMins, saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loadData();

        recyclerVu = findViewById(R.id.recycleVu);

        if (list != null) {
            System.out.println(list.toString());
        }
        RecycleViewAdaptor adaptor = new RecycleViewAdaptor(this, list);
        recyclerVu.setAdapter(adaptor);
        recyclerVu.setLayoutManager(new LinearLayoutManager(this));
        amountEdtTxt = findViewById(R.id.amount);
        totalTxtVu = findViewById(R.id.totalBudget);
        totalTxtVu.setText(String.valueOf(budget));
        saveBtn = findViewById(R.id.SaveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });
        saveBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                reset();
                return false;
            }
        });
        btnPls = findViewById(R.id.buttonPls);
        btnMins = findViewById(R.id.buttonMins);
        commentTxtVu = findViewById(R.id.comment);
        btnPls.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (amountEdtTxt.getText().toString().length() != 0) {
                    double amnt = Double.valueOf(String.valueOf(amountEdtTxt.getText()));
                    budget = budget + amnt;
                    totalTxtVu.setText(String.valueOf(budget));
                    list.add(new Receive(amnt ,  commentTxtVu.getText().toString(), LocalDateTime.now()));
                    adaptor.notifyItemInserted(0);

                    System.out.println(list.toString());
                    System.out.println(budget);
                    setTheEditTextEmpty();
                } else {

                }

            }
        });
        btnMins.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (amountEdtTxt.getText().toString().length() != 0) {
                    double amnt = Double.valueOf(String.valueOf(amountEdtTxt.getText()));
                    budget = budget - amnt;
                    totalTxtVu.setText(String.valueOf(budget));
                    list.addFirst(new Pay(-amnt, String.valueOf(commentTxtVu.getText()), LocalDateTime.now()));
                    adaptor.notifyItemInserted(0);
                    System.out.println(list.toString());
                    System.out.println(budget);
                    setTheEditTextEmpty();
                    ;
                } else
                    System.out.println("no number");
            }
        });


    }

    private void reset() {
        list.clear();
        budget = 0.0;
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("task list", json);
        editor.apply();

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<LinkedList<FinancialOperation>>() {
        }.getType();
        list = gson.fromJson(json, type);

        if (list == null) {
            list = new LinkedList<FinancialOperation>();
        }

    }


    private void setTheEditTextEmpty() {
        commentTxtVu.setText("");
        amountEdtTxt.setText("");
    }


    private void caluclateBudgetFromList() {
        if (!list.isEmpty() && list != null) {
            for (int j = 0; j < list.size(); j++) {
                budget = budget + list.get(j).value;
            }

        }else {
            budget = 0.0;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }


}