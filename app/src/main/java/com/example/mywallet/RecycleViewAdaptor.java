package com.example.mywallet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecycleViewAdaptor extends RecyclerView.Adapter<RecycleViewAdaptor.MyViewHolder> {
    Context context;
    LinkedList<FinancialOperation> list;

    int type;

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    public RecycleViewAdaptor(Context context, LinkedList<FinancialOperation> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recive_model, parent, false);


        return new MyViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdaptor.MyViewHolder holder, int position) {
        holder.DescriptionTxtVu.setText(list.get(position).comment);
        holder.MoneyTxtVu.setText(String.valueOf(list.get(position).value));
        holder.dateTxtVu.setText(String.valueOf(list.get(position).dateTime));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


     class MyViewHolder extends RecyclerView.ViewHolder {
        TextView DescriptionTxtVu, MoneyTxtVu, dateTxtVu;
        Button deleteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            DescriptionTxtVu = itemView.findViewById(R.id.descriptionTxtVu);
            MoneyTxtVu = itemView.findViewById(R.id.moneyTxtVu);
            dateTxtVu = itemView.findViewById(R.id.dateTxtVu);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(getAdapterPosition());
                }
            });

        }
    }
    public void deleteItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }



}
