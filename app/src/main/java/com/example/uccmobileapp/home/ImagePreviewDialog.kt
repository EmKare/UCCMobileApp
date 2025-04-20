package com.example.uccmobileapp.home

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.uccmobileapp.R

// DialogFragment to show an image preview in fullscreen with a ViewPager2
class ImagePreviewDialog(private val images: List<Int>, private val startPosition: Int) : DialogFragment() {

    // Creates the dialog with a full-screen layout and initializes the ViewPager
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Creates a dialog with a fullscreen theme
        val dialog = Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.dialog_image_preview) // Set the layout for the dialog

        // Finds the ViewPager2 from the dialog's layout and set its adapter
        val viewPager = dialog.findViewById<ViewPager2>(R.id.fullscreenViewPager)
        viewPager.adapter = FullscreenImageAdapter(images) {
            dismiss()  // Dismisses the dialog when an image is tapped
        }

        // Sets the initial position of the ViewPager to the start position
        viewPager.setCurrentItem(startPosition, false)

        // Sets window animations for the dialog
        dialog.window?.setWindowAnimations(R.style.DialogFade)

        return dialog
    }

    // Override onStart to adjust window animations for the dialog
    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setWindowAnimations(android.R.style.Animation_Dialog)  // Set default dialog animation
        }
    }
}
