package com.example.student_workshop.Fragments_class

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.student_workshop.R
import com.example.student_workshop.Shared_Prefrences.WorkShopShared_Prefrence


// this is login screen fragment class to login ----------------------
class Login_fragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.login_screen, container, false)


//         here find all the view id for click event ----------------------

//        this is register text id -----------
        val registertext = view.findViewById<TextView>(R.id.RegsiterText)
// this is login btn id --------
        val Login_btn =   view.findViewById<Button>(R.id.loginButton)


        // here call the function to remove backstack login fragment

        removeFragment()

//         here click listener on Login Btn click ------------------

          Login_btn.setOnClickListener {

// make toast user sign in successfully -----------------
            Toast.makeText(activity, " ✔\uFE0F User Login SuccessFully", Toast.LENGTH_SHORT).show()

//               save value true in shared preference to remember that user login

//              WorkshoopShared_Prefrence class that have Put_IS_Login function ---------------

//              by calling this function  user Login value will be save as true -------------------
            WorkShopShared_Prefrence(requireContext()).Put_Is_Login(requireContext())

// ------------ here navigating to All Work_Shop_Available fragment class --------------------
           loadNewFragment1()



        }


//         register text on click lister event call----------------
        registertext.setOnClickListener {

// here remove fragment while load another fragment  by calling remove fragment -------------
            removeFragment()

//            here load the register fragment to user register
            loadNewFragment()


        }


        return view
    }



// define the function to load a specific fragments---------------------------------

    @SuppressLint("SuspiciousIndentation")
    private fun loadNewFragment() {
//        make object of your destination fragments
        val newFragment = SignUp_Screen()
//        call all require function from fragment manager----------------------
        requireFragmentManager().beginTransaction()
            .replace(R.id.Main_fragmet_container, newFragment,"Register_Frag")
            .addToBackStack("Register_Frag")
            .commit()

//         call commit after transaction to complete the fragment transaction
    }

    // here are the function to remove the specific fragment from backstack to manage all fragment
    private fun removeFragment() {

//         here get fragment manager
        val fragmentManager = requireActivity().supportFragmentManager

//         find the fragment by tag to remove the specific fragment
        val fragmentToRemove = fragmentManager.findFragmentByTag("Register_Frag")


//        check if it is not on backstack ------------------------
        if (fragmentToRemove != null) {

//             here begin transaction ------------------
            fragmentManager.beginTransaction()
                .remove(fragmentToRemove)
                .commit()
        }
    }









// here are the same function to load the another fragment ----------------------------

    @SuppressLint("SuspiciousIndentation")
    private fun loadNewFragment1() {
        val newFragment = AllAvailableWorkShop()
        requireFragmentManager().beginTransaction()
            .replace(R.id.Main_fragmet_container, newFragment,"All_Available_workshop")
            .commit()
    }



}