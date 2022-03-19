package com.example.walliproject;
import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;

public class DataBaseClass extends SQLiteOpenHelper {
    private static String DataBaseName = "AppDataBase";
    SQLiteDatabase AppDataBase;
    public DataBaseClass(Context context1){
        super(context1 ,DataBaseName,null ,1 );
    }


    private static Users loggedInUser;

    public Users getLoggedInUser() {
        return loggedInUser;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users ( Name text not null,Email text primary key, Password text not null, MembershipType integer )");
        db.execSQL("create table doctors (Location text,Specialty text,workingHoursStart integer,workingHoursEnd integer, UserEmail text,Fees integer not null,Rating text, foreign key (UserEmail) REFERENCES users(email))");
        db.execSQL("CREATE TABLE appointments (appointmentStart integer,appointmentEnd integer,userEmail text, doctorEmail text,confirm integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists appointments");
        db.execSQL("drop table if exists doctors");
        onCreate(db);
    }

    public void AddNewUser (String name , String email , String password , int typ){
        ContentValues row = new ContentValues();
        row.put("Name",name);
        row.put("Email",email);
        row.put("Password",password);
        row.put("MembershipType",typ);
        AppDataBase= getWritableDatabase();
        AppDataBase.insert("users",null,row);
        AppDataBase.close();
    }
    public void addNewAppointment(int appointmentStart,int appointmentEnd,String userEmail,String doctorEmail,int confirm){
        ContentValues row = new ContentValues();
        row.put("appointmentStart",appointmentStart);
        row.put("appointmentEnd",appointmentEnd);
        row.put("userEmail",userEmail);
        row.put("doctorEmail",doctorEmail);
        row.put("confirm",confirm);
        AppDataBase= getWritableDatabase();
        AppDataBase.insert("appointments",null,row);
        AppDataBase.close();
    }
    public void AddNewDoctor(String specialty, String location, String workingHoursStart,String workingHoursEnd,String UserEmail,int fees,String rating){
        ContentValues row = new ContentValues();
        row.put("Specialty",specialty);
        row.put("Location",location);
        row.put("workingHoursStart",workingHoursStart);
        row.put("workingHoursEnd",workingHoursEnd);
        row.put("UserEmail",UserEmail);
        row.put("Fees",fees);
        row.put("Rating",rating);
        AppDataBase= getWritableDatabase();
        AppDataBase.insert("doctors",null,row);
        AppDataBase.close();
    }
    public Cursor getAllDoctorsInfo(String search,String specialty){
        AppDataBase=getReadableDatabase();
        String s = '%' + search + '%';
        String searchspecialty;
        if (specialty.equals("All Specialties")){
            searchspecialty="%%";
        }
        else{
            searchspecialty='%'+specialty+'%';
        }
        //getting Doctor data
        Cursor data=AppDataBase.rawQuery("select users.Name,doctors.Specialty ,doctors.workingHoursStart,doctors.workingHoursEnd,doctors.UserEmail,doctors.location,doctors.rating from doctors,users where users.Email = doctors.UserEmail AND users.Name LIKE ? And doctors.Specialty LIKE ? AND users.MembershipType = 1", new String[] {s,searchspecialty});
        if (data!=null){
            data.moveToFirst();
        }

        AppDataBase.close();

        // 0 name, 1 Specialty , 2 workStartTime, 3 workendTime, 4 email
        return data;
    }

    public Cursor getAllPatientAppointments(String email){
        AppDataBase=getReadableDatabase();
        //getting Doctor data
        Cursor data=AppDataBase.rawQuery("select appointmentStart,appointmentEnd,userEmail,doctorEmail,confirm from appointments where userEmail = ? AND confirm = 1", new String[] {email});
        if (data!=null){
            data.moveToFirst();
        }

        AppDataBase.close();

        return data;
    }
    public Cursor getAllDoctorAppointments(String email){
        AppDataBase=getReadableDatabase();
        //getting Doctor data
        Cursor data=AppDataBase.rawQuery("select appointmentStart,appointmentEnd,userEmail,doctorEmail,confirm from appointments where doctorEmail = ? AND confirm = 0", new String[] {email});
        if (data!=null){
            data.moveToFirst();
        }

        AppDataBase.close();

        return data;
    }
    public int loginCheck (String email , String password){
        AppDataBase=getReadableDatabase();
        Cursor cursor =AppDataBase.rawQuery("select * from users where Email =? and Password =?",new String[] {email , password});
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            loggedInUser = new Users(cursor.getString(0),cursor.getString(1),cursor.getString(2),parseInt(cursor.getString(3)));
            if (cursor.getString(3).equals("0")){
                return 1;
            }
            else if(cursor.getString(3).equals("1")){
            return 2;
            }
            else if(cursor.getString(3).equals("2")){
                return 3;
            }

            else{
                return 4;
            }
        }

        else
            return 0;
    }
    public Cursor getUser(String email){
        AppDataBase=getReadableDatabase();
        //getting Doctor data
        Cursor data=AppDataBase.rawQuery("select name,email from users where Email = ?", new String[] {email});
        if (data!=null){
            data.moveToFirst();
        }
        AppDataBase.close();

        // 0 name, 1 Specialty , 2 workStartTime, 3 workendTime, 4 email
        return data;
    }
    public Cursor getDoctorInfo(String email){
        AppDataBase=getReadableDatabase();
        Cursor data=AppDataBase.rawQuery("select users.Name,doctors.Specialty ,doctors.Location ,doctors.workingHoursStart,doctors.workingHoursEnd ,doctors.Fees ,doctors.Rating from doctors,users where users.Email = doctors.UserEmail AND doctors.UserEmail = ?",new String[] {email});
        if (data!=null){
            data.moveToFirst();
        }
        return data;
    }

    public boolean exist (String EMail){
        AppDataBase=getReadableDatabase();
        Cursor cursor =AppDataBase.rawQuery("select email from users where Email =? ",new String[] {EMail});
        if (cursor.getCount()>0){
            return true;
        }

        else
            return false;
    }

    public int[] getAdminStatictics(){
        AppDataBase=getReadableDatabase();
        Cursor usersCur=AppDataBase.rawQuery("select Email from users",null);
        Cursor doctorsCur=AppDataBase.rawQuery("select UserEmail from doctors",null);

        int[] activeNumber=new int[2];
        activeNumber[0]=usersCur.getCount();
        activeNumber[1]=doctorsCur.getCount();
        return activeNumber;
    }

    public void UpdatePersonalData(String newName,String NewPassword){
        AppDataBase= getWritableDatabase();
        AppDataBase.execSQL("update users set Name = ? where email =?",new String[] {newName,loggedInUser.getEmail()});
        AppDataBase.execSQL("update users set Password = ? where email =?",new String[] {NewPassword,loggedInUser.getEmail()});
        AppDataBase.close();
    }

    public ArrayList<doctors> fetchDoctorsData(Cursor cursor){
        ArrayList<doctors> doctorsList = new ArrayList<>();
        while(!cursor.isAfterLast()){
            doctors tmp = new doctors();
            tmp.setName(cursor.getString(0));
            tmp.setSpecialty(cursor.getString(1));
            tmp.setWorkingHoursStart(parseInt(cursor.getString(2)));
            tmp.setWorkingHoursEnd(parseInt(cursor.getString(3)));
            tmp.setUserEmail(cursor.getString(4));
            tmp.setLocation(cursor.getString(5));
            tmp.setRating(parseInt(cursor.getString(6)));
            doctorsList.add(tmp);
            cursor.moveToNext();
        }
        return doctorsList;
    }

    public Cursor getAllPendingDoctors(){
        AppDataBase=getReadableDatabase();
        //Cursor[] data=new Cursor[2];
        //getting Doctor data
        Cursor data=AppDataBase.rawQuery("select users.Name,doctors.Specialty ,doctors.workingHoursStart,doctors.workingHoursEnd,doctors.UserEmail,doctors.location,doctors.rating from doctors,users where users.MembershipType = 2 AND users.Email = doctors.UserEmail ", null);
        if (data!=null){
            data.moveToFirst();
        }

        AppDataBase.close();

        // 0 name, 1 Specialty , 2 workStartTime, 3 workendTime, 4 email
        return data;
    }
    @SuppressLint("Recycle")
    public void refuseDoctor(String email){
        AppDataBase= getWritableDatabase();
        AppDataBase.delete("doctors","UserEmail = ?",new String[] {email});
        AppDataBase.delete("users","email = ?",new String[] {email});
        AppDataBase.close();
    }
    @SuppressLint("Recycle")
    public void acceptDoctor(String email){
        AppDataBase= getWritableDatabase();
        AppDataBase.execSQL("update users set MembershipType = 1 where email =?",new String[] {email});
        AppDataBase.close();
    }
    @SuppressLint("Recycle")
    public void refusePatient(String email){
        AppDataBase= getWritableDatabase();
        AppDataBase.delete("appointments","UserEmail = ?",new String[] {email});
        AppDataBase.close();
    }
    @SuppressLint("Recycle")
    public void acceptPatient(String email){
        AppDataBase= getWritableDatabase();
        AppDataBase.execSQL("update appointments set confirm = 1 where userEmail =?",new String[] {email});
        AppDataBase.close();
    }

}
