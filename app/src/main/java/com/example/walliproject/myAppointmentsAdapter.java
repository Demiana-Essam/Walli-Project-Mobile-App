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

public class myAppointmentsAdapter extends RecyclerView.Adapter<myAppointmentsAdapter.myAppointmentsHolder> {

    ArrayList<appointment> appointments;
    DataBaseClass dataBaseClass;
    public myAppointmentsAdapter(ArrayList<appointment> appointments) {
        this.appointments = appointments;
    }
    @NonNull
    @Override
    public myAppointmentsAdapter.myAppointmentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myAppointmentsAdapter.myAppointmentsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_appointments_card,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull myAppointmentsAdapter.myAppointmentsHolder holder, int position) {
        Cursor c = dataBaseClass.getDoctorInfo(appointments.get(position).getDoctorMail());

        holder.doctorName.setText("Doctor's Name:" + c.getString(0));
        holder.doctorSpecialty.setText("Doctor's Specialty:" + c.getString(1));
        holder.workingHoursStart.setText("From:" + c.getString(3));
        holder.workingHourEnd.setText("To:" + (parseInt(c.getString(4))+1));
    }
    @Override
    public int getItemCount() {
        return appointments.size();
    }

    class myAppointmentsHolder extends RecyclerView.ViewHolder{
        TextView doctorName;
        TextView doctorSpecialty;
        TextView workingHoursStart;
        TextView workingHourEnd;
        public myAppointmentsHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.docName);
            doctorSpecialty = itemView.findViewById(R.id.docSpeciality);
            workingHourEnd = itemView.findViewById(R.id.workingHoursEnd);
            workingHoursStart = itemView.findViewById(R.id.workingHoursStart);
            dataBaseClass = new DataBaseClass(itemView.getContext());
        }
    }
}
