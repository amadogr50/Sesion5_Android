package com.marito.sesion5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class ClearDialogFragment extends DialogFragment {
  @NonNull
  @Override
  public Dialog onCreateDialog (@Nullable Bundle savedInstanceState) {
    // Use the Builder class for convenient dialog construction
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle("Clear");
    builder.setMessage(R.string.dialog_clear)
            .setPositiveButton(R.string.clear, new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                clear();
              }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
              }
            });
    // Create the AlertDialog object and return it
    return builder.create();
  }
  
  public void clear () {
  
  }
}
