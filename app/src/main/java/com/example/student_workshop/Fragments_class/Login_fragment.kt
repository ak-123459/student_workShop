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
        val view =  inflater.inflate(R.layout.fragment_login_fragment, container, false)

        val registertext = view.findViewById<TextView>(R.id.RegsiterText)

        removeFragment()


        view.findViewById<Button>(R.id.loginButton).setOnClickListener {


            Toast.makeText(activity, " ✔\uFE0F User Login SuccessFully", Toast.LENGTH_SHORT).show()

            WorkShopShared_Prefrence(requireContext()).Put_Is_Login(requireContext())

           loadNewFragment1()



        }
        registertext.setOnClickListener {


            removeFragment()
            loadNewFragment()


        }


        return view
    }





    @SuppressLint("SuspiciousIndentation")
    private fun loadNewFragment() {
        val newFragment = RegsiterFragments()
        requireFragmentManager().beginTransaction()
            .replace(R.id.Main_fragmet_container, newFragment,"Register_Frag")
            .addToBackStack("Register_Frag")
            .commit()
    }


    private fun removeFragment() {

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentToRemove = fragmentManager.findFragmentByTag("Register_Frag")

        if (fragmentToRemove != null) {

            fragmentManager.beginTransaction()
                .remove(fragmentToRemove)
                .commit()
        }
    }






    fun Index(){


        val fragmentManager = requireActivity().supportFragmentManager

        // Get the current fragment's tag
        val currentFragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name

        val ALl_index = fragmentManager.backStackEntryCount

        // Find the current fragment using its tag
        val currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag)

        // Get the index of the current fragment in the back stack
        val currentIndex = fragmentManager.fragments.indexOf(currentFragment)

        Toast.makeText(activity, "$ALl_index,$currentIndex", Toast.LENGTH_SHORT).show()

    }




    @SuppressLint("SuspiciousIndentation")
    private fun loadNewFragment1() {
        val newFragment = All_WorkShop_Avil()
        requireFragmentManager().beginTransaction()
            .replace(R.id.Main_fragmet_container, newFragment,"All_Available_workshop")
            .commit()
    }



}