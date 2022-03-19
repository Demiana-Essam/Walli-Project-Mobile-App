package com.example.walliproject;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pendingListAdapter extends RecyclerView.Adapter<pendingListAdapter.pendingViewHolder> {

    ArrayList<doctors> drList;
    DataBaseClass dataBaseClass;
    public pendingListAdapter(ArrayList<doctors> drList) {
        this.drList = drList;
    }
    @NonNull
    @Override
    public pendingListAdapter.pendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new pendingListAdapter.pendingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_doctor_form_card,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull pendingListAdapter.pendingViewHolder holder, int position) {

        holder.doctorName.setText("Doctor's Name:" + drList.get(position).getName());
        holder.doctorEmail.setText("Doctor's Email:" + drList.get(position).getUserEmail());
        holder.doctorSpecialty.setText("Doctor's Specialty:" + drList.get(position).getSpecialty());
        holder.workingHoursStart.setText("From:" + drList.get(position).getWorkingHoursStart());
        holder.workingHourEnd.setText("To:" + drList.get(position).getWorkingHoursEnd());
        holder.docLocation.setText("Location:" + drList.get(position).getLocation());
    }
    @Override
    public int getItemCount() {
        return drList.size();
    }

    class pendingViewHolder extends RecyclerView.ViewHolder{
        TextView doctorName;
        TextView doctorSpecialty;
        TextView docLocation;
        TextView workingHoursStart;
        TextView workingHourEnd;
        TextView doctorEmail;
        Button acceptButton,refuseButton;
        public pendingViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.docName);
            doctorEmail = itemView.findViewById(R.id.docEmail);
            doctorSpecialty = itemView.findViewById(R.id.docSpeciality);
            docLocation = itemView.findViewById(R.id.docLocation);
            workingHourEnd = itemView.findViewById(R.id.workingHoursEnd);
            workingHoursStart = itemView.findViewById(R.id.workingHoursStart);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            refuseButton = itemView.findViewById(R.id.refuseButton);
            dataBaseClass = new DataBaseClass(itemView.getContext());
            acceptButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    dataBaseClass.acceptDoctor(drList.get(getAdapterPosition()).getUserEmail());
                    drList.remove(drList.get(getAdapterPosition()));
                    pendingDoctors.getPendingListAdapter().notifyDataSetChanged();
                }
            });
            refuseButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    dataBaseClass.refuseDoctor(drList.get(getAdapterPosition()).getUserEmail());
                    drList.remove(drList.get(getAdapterPosition()));
                    pendingDoctors.getPendingListAdapter().notifyDataSetChanged();
                }
            });
        }

    }
}
