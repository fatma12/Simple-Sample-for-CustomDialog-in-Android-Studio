package com.ucinbir.xplugin.customdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by xplugin on 27.03.2017.
 */
public class CustomDialogFragment extends DialogFragment {
    private static final String SELECTED_POSITION_ARG="SELECTED_POSITION";
    private static final String SELECTED_NAME_ARG="SELECTED_NAME";
    private NameUpdateListener nameUpdateListener;
    public static CustomDialogFragment newInstance(int position, String name){
        Bundle bundle=new Bundle();
        bundle.putInt(SELECTED_POSITION_ARG, position);
        bundle.putString(SELECTED_NAME_ARG, name);
        CustomDialogFragment dialogFragment= new CustomDialogFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final int selectedItemPosition=getArguments().getInt(SELECTED_POSITION_ARG);
        String selectedName =getArguments().getString(SELECTED_NAME_ARG);
        LayoutInflater inflater=LayoutInflater.from(getActivity());
        View layout=inflater.inflate(R.layout.activity_main, null);
        Button guncelleButton=(Button) layout.findViewById(R.id.guncelleButton);
        Button iptalButton=(Button) layout.findViewById(R.id.iptalButton);
        final EditText guncelleEditText = (EditText) layout.findViewById(R.id.guncelleEditText);
        guncelleEditText.setText(selectedName);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        final AlertDialog dialog=builder.create();
        guncelleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameUpdateListener.onUpdate(selectedItemPosition,guncelleEditText.getText().toString());
                dialog.dismiss();
            }
        });
        iptalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        return dialog;

    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        nameUpdateListener=(NameUpdateListener) activity;
    }
}
