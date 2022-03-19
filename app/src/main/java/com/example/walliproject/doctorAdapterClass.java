package com.example.walliproject;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class doctorAdapterClass extends RecyclerView.Adapter<doctorAdapterClass.doctorAdapterHolder> {

    ArrayList<appointment> appointments;
    DataBaseClass dataBaseClass;
    public doctorAdapterClass(ArrayList<appointment> appointments) {
        this.appointments = appointments;
    }
    @NonNull
    @Override
    public doctorAdapterClass.doctorAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new doctorAdapterClass.doctorAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_confirmation_card,parent,false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull doctorAdapterClass.doctorAdapterHolder holder, int position) {
        Cursor c = dataBaseClass.getUser(appointments.get(position).getUserMail());

        holder.patientName.setText("Patient's Name:" + c.getString(0));
        holder.patientEmail.setText("Patient's Email:" + c.getString(1));
        holder.appointmentDate.setText("Appointment Date:" + appointments.get(position).getStart());
    }
    @Override
    public int getItemCount() {
        return appointments.size();
    }

    class doctorAdapterHolder extends RecyclerView.ViewHolder{
        TextView patientName;
        TextView patientEmail;
        TextView appointmentDate;
        Button acceptButton,refuseButton;
        public doctorAdapterHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patientName);
            patientEmail = itemView.findViewById(R.id.patientEmail);
            appointmentDate = itemView.findViewById(R.id.appointmentDate);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            refuseButton = itemView.findViewById(R.id.refuseButton);
            dataBaseClass = new DataBaseClass(itemView.getContext());
            acceptButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    dataBaseClass.acceptPatient(appointments.get(getAdapterPosition()).getUserMail());
                    appointments.remove(appointments.get(getAdapterPosition()));
                    doctorPage.getAppointmentAdapter().notifyDataSetChanged();
                }
            });
            refuseButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    dataBaseClass.refusePatient(appointments.get(getAdapterPosition()).getUserMail());
                    appointments.remove(appointments.get(getAdapterPosition()));
                    doctorPage.getAppointmentAdapter().notifyDataSetChanged();
                }
            });
        }
    }
}
