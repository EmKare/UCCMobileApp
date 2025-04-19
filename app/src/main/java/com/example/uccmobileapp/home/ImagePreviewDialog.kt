package com.example.uccmobileapp.home

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.uccmobileapp.R

class ImagePreviewDialog(private val images: List<Int>, private val startPosition: Int) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.dialog_image_preview)

        val viewPager = dialog.findViewById<ViewPager2>(R.id.fullscreenViewPager)
        viewPager.adapter = FullscreenImageAdapter(images) {
            dismiss()
        }

        viewPager.setCurrentItem(startPosition, false)

        dialog.window?.setWindowAnimations(R.style.DialogFade)

        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setWindowAnimations(android.R.style.Animation_Dialog)
        }
    }

}

