package com.ingrammicro.imdelivery.utils

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ingrammicro.imdelivery.R

enum class SnackbarType {
    BLUE,
    YELLOW
}

fun shortSnackbar(view: View, resId: Int, type: SnackbarType): Snackbar {
    return when (type) {
        SnackbarType.BLUE -> blueSnackbar(view, resId, Snackbar.LENGTH_SHORT)
        SnackbarType.YELLOW -> yellowSnackbar(view, resId, Snackbar.LENGTH_SHORT)
    }
}

fun shortSnackbar(view: View, message: String, type: SnackbarType): Snackbar {
    return when (type) {
        SnackbarType.BLUE -> blueSnackbar(view, message, Snackbar.LENGTH_SHORT).setDuration(7000)
        SnackbarType.YELLOW -> yellowSnackbar(
            view,
            message,
            Snackbar.LENGTH_SHORT
        ).setDuration(7000)
    }
}

fun longSnackbar(view: View, resId: Int, type: SnackbarType): Snackbar {
    return when (type) {
        SnackbarType.BLUE -> blueSnackbar(view, resId, Snackbar.LENGTH_LONG).setDuration(7000)
        SnackbarType.YELLOW -> yellowSnackbar(view, resId, Snackbar.LENGTH_LONG).setDuration(7000)
    }
}

fun indefiniteSnackbar(view: View, resId: Int, type: SnackbarType): Snackbar {
    return when (type) {
        SnackbarType.BLUE -> blueSnackbar(view, resId, Snackbar.LENGTH_INDEFINITE)
        SnackbarType.YELLOW -> yellowSnackbar(view, resId, Snackbar.LENGTH_INDEFINITE)
    }
}

fun indefiniteSnackbar(view: View, msg: String, type: SnackbarType): Snackbar {
    return when (type) {
        SnackbarType.BLUE -> blueSnackbar(view, msg, Snackbar.LENGTH_INDEFINITE)
        SnackbarType.YELLOW -> yellowSnackbar(view, msg, Snackbar.LENGTH_INDEFINITE)
    }
}

fun longSnackbar(view: View, message: String, type: SnackbarType): Snackbar {
    return when (type) {
        SnackbarType.BLUE -> blueSnackbar(view, message, Snackbar.LENGTH_LONG).setDuration(7000)
        SnackbarType.YELLOW -> yellowSnackbar(view, message, Snackbar.LENGTH_LONG).setDuration(7000)
    }
}

fun blueSnackbar(view: View, resId: Int, duration: Int): Snackbar {
    return createSnackbar(
        Snackbar.make(view, resId, duration),
        R.color.colorWhite,
        R.color.colorWhite,
        R.color.colorPrimary
    )
}

fun blueSnackbar(view: View, message: String, duration: Int): Snackbar {
    return createSnackbar(
        Snackbar.make(view, message, duration),
        R.color.colorWhite,
        R.color.colorWhite,
        R.color.colorPrimary
    )
}

fun yellowSnackbar(view: View, resId: Int, duration: Int): Snackbar {
    return createSnackbar(
        Snackbar.make(view, resId, duration),
        R.color.colorBlack,
        R.color.colorPrimary,
        R.color.color_snackbar_bg
    )
}

fun yellowSnackbar(view: View, message: String, duration: Int): Snackbar {
    return createSnackbar(
        Snackbar.make(view, message, duration),
        R.color.colorBlack,
        R.color.colorPrimary,
        R.color.color_snackbar_bg
    )
}

private fun createSnackbar(
    snackBar: Snackbar,
    textColor: Int = R.color.colorWhite,
    actionColor: Int = R.color.colorWhite,
    background: Int = R.color.colorPrimary
): Snackbar {
    val sbView = snackBar.view
    sbView.setBackgroundResource(background)
    val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(ContextCompat.getColor(snackBar.context, textColor))
    textView.maxLines = 4

    snackBar.setActionTextColor(ContextCompat.getColor(snackBar.context, actionColor))
    return snackBar
}