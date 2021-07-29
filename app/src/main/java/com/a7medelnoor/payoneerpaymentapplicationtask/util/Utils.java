package com.a7medelnoor.payoneerpaymentapplicationtask.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.a7medelnoor.payoneerpaymentapplicationtask.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.signature.ObjectKey;

public class Utils {
    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return false;
            }
        }
        return true;
    }
    public static void loadBitmap(String url, ImageView imageView, Context context
            ) {
        if (isValidContextForGlide(context)) {
            Glide.with(context).load(new GlideUrl(url))
                    .thumbnail(0.1f).placeholder(R.drawable.image_logao_placeholder)
                    .error(android.R.drawable.ic_dialog_alert).into(imageView);
        }
    }
}
