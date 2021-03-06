package net.jeremycasey.hamiltonheatalert.app.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import net.jeremycasey.hamiltonheatalert.R;
import net.jeremycasey.hamiltonheatalert.heatstatus.HeatStatusFetcher;

public class DisclaimerDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(
                getString(R.string.disclaimer, HeatStatusFetcher.RSS_URL)
        ).setPositiveButton(R.string.ok, null);
        return builder.create();
    }
}
