package com.example.uccmobileapp

//Listener interface for controlling the visibility of the Floating Action Button (FAB)
//from fragments or other components.
interface FabVisibilityListener {

    //Requests to show the FAB.
    fun showFab()

    //Requests to hide the FAB.
    fun hideFab()
}
