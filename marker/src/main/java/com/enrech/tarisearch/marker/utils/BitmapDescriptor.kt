package com.enrech.tarisearch.marker.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun bitmapDescriptorFromVector(
    context: Context,
    density: Density,
    size: DpSize,
    vectorResId: Int
): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        val (height, width) = with(density) {
            Pair(
                size.height.toPx().toInt(),
                size.width.toPx().toInt()
            )
        }
        setBounds(0, 0, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}