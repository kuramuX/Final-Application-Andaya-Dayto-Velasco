package com.example.to_dolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    public static String act;

    private Context context;
    private ArrayList id, task, subject;
    CustomAdapter(Context context, ArrayList id, ArrayList task, ArrayList subject){
        this.context = context;
        this.id = id;
        this.task = task;
        this.subject = subject;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.id_txt.setText(String.valueOf(id.get(i)));
        holder.task_txt.setText(String.valueOf(task.get(i)));
        holder.subject_txt.setText(String.valueOf(subject.get(i)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (act=="Todo") {
                    Intent intent = new Intent(context, TodoActivity.class);
                    intent.putExtra("id", String.valueOf(id.get(i)));
                    intent.putExtra("task", String.valueOf(task.get(i)));
                    intent.putExtra("subject", String.valueOf(subject.get(i)));
                    context.startActivity(intent);
                } else if (act=="Doing") {
                    Intent intent = new Intent(context, Doing.class);
                    intent.putExtra("id", String.valueOf(id.get(i)));
                    intent.putExtra("task", String.valueOf(task.get(i)));
                    intent.putExtra("subject", String.valueOf(subject.get(i)));
                    context.startActivity(intent);
                } else if (act=="Done") {
                    Intent intent = new Intent(context, Done.class);
                    intent.putExtra("id", String.valueOf(id.get(i)));
                    intent.putExtra("task", String.valueOf(task.get(i)));
                    intent.putExtra("subject", String.valueOf(subject.get(i)));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, task_txt, subject_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            task_txt = itemView.findViewById(R.id.task_txt);
            subject_txt = itemView.findViewById(R.id.subject_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
