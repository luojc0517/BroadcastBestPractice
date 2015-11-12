package com.jackie.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

/**
 * 用于强制下线的广播接收器
 */
public class OfflineReceiver extends BroadcastReceiver {
    public OfflineReceiver() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("注意");
        dialogBuilder.setMessage("您已被强制下线，请重新登陆");
        dialogBuilder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();
                Intent intent = new Intent(context, LoginActivity.class);
                //在Activity外部启动activity需要设置下面这个Flag
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        dialogBuilder.setCancelable(false);
        AlertDialog alertDialog = dialogBuilder.create();
        //全局的对话框
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }

}
