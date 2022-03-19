package com.example.walliproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class PopUpMessage extends AppCompatDialogFragment {
    String message;
    String messageHead;
    Context navigate;
    String BtnName;
    PopUpMessage(String messageHead, String message,String BtnName, Context navigate){
        this.message=message;
        this.messageHead=messageHead;
        this.navigate=navigate;
        this.BtnName=BtnName;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(messageHead)
                .setMessage(message)
                .setPositiveButton(BtnName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(navigate,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
        return builder.create();

    }
}
