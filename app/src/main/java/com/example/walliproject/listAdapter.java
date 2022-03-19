package com.example.walliproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder>{

    ArrayList<doctors> drList;
    onItemClick listener;
    public listAdapter(ArrayList<doctors> drList,onItemClick listener) {
        this.drList = drList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_shape,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.doctorName.setText(drList.get(position).getName());
        holder.doctorSpecialty.setText(drList.get(position).getSpecialty());
        holder.workingHourStart.setText(String.valueOf(drList.get(position).getWorkingHoursStart()));
       // holder.workingHourEnd.setText(String.valueOf(drList.get(position).getWorkingHoursEnd()));
    }

    @Override
    public int getItemCount() {
        return drList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView doctorName;
        TextView doctorSpecialty;
        TextView workingHourStart;
        TextView workingHourEnd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.docName);
            doctorSpecialty = itemView.findViewById(R.id.docSpeciality);
            workingHourStart = itemView.findViewById(R.id.docLocation);
            //workingHourEnd = itemView.findViewById(R.id.workingHourEnd);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
    public interface onItemClick{
        void onClick(View v,int position);
    }
}
