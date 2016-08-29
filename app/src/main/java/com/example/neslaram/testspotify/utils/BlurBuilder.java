package com.example.neslaram.testspotify.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

/**
 * Created by desarrollo on 7/26/16.
 */
public class BlurBuilder {
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;

    public static Bitmap blur(Context context, Bitmap bitmap) {
//        int width = Math.round(image.getWidth() * BITMAP_SCALE);
//        int height = Math.round(image.getHeight() * BITMAP_SCALE);
//
//        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
//        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
//
//        RenderScript rs = RenderScript.create(context);
//        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
//        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
//        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
//        theIntrinsic.setRadius(BLUR_RADIUS);
//        theIntrinsic.setInput(tmpIn);
//        theIntrinsic.forEach(tmpOut);
//        tmpOut.copyTo(outputBitmap);
//
//        return outputBitmap;
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(25.f);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;

    }
}
