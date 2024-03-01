package com.example.student_workshop.Fragments_class

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment

import com.example.student_workshop.R
import com.example.student_workshop.Shared_Prefrences.WorkShopShared_Prefrence



// Note --> This RegsiterFragment class working like Login_fragment class so you can understand its function definition on it

class SignUp_Screen : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.signup_screen, container, false)


//         here find all the view id for click event ----------------------
        val regsiter_btn = view.findViewById<AppCompatButton>(R.id.regsiterBtn)

        //         here click listener on Register Btn click ------------------

        regsiter_btn.setOnClickListener {

            Toast.makeText(activity, " ✔\uFE0F User Register SuccessFully", Toast.LENGTH_SHORT).show()
            WorkShopShared_Prefrence(requireContext()).Put_Is_Login(requireContext())

            loadNewFragment1()

        }

        return view
    }



    // you can see the Login_fragment class in Fragment_class Module where i describe about this function working

    @SuppressLint("SuspiciousIndentation")
    private fun loadNewFragment() {
        val newFragment = Login_fragment()
        requireFragmentManager().beginTransaction()
            .replace(R.id.Main_fragmet_container, newFragment,"Login_frag")
            .addToBackStack("Login_frag")
            .commit()
    }



    // you can see the Login_fragment class in Fragment_class Module where i describe about this function working

    private fun removeFragment() {

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentToRemove = fragmentManager.findFragmentByTag("Register_Frag")

        if (fragmentToRemove != null) {

            fragmentManager.beginTransaction()
                .remove(fragmentToRemove)
                .commit()
        }
    }




    // you can see the Login_fragment class in Fragment_class Module where i describe about this function working

    @SuppressLint("SuspiciousIndentation")
    private fun loadNewFragment1() {
        val newFragment = AllAvailableWorkShop()
        requireFragmentManager().beginTransaction()
            .replace(R.id.Main_fragmet_container, newFragment,"All_Available_workshop")
            .commit()
    }

}