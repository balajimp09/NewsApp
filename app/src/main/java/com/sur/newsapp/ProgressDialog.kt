package com.sur.newsapp

import android.app.Dialog
import android.content.Context
import android.view.Window

class ProgressDialog(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.progress_dialog)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun showProgressDialog() {
        if (!isShowing) {
            show()
        }
    }

    fun dismissProgressDialog() {
        if (isShowing) {
            dismiss()
        }
    }
}
