package com.example.inprint.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.inprint.R;

public class DocFragmentBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        throw new UnsupportedOperationException(context.getResources()
                .getString(R.string.docList_update_error));
    }
}
