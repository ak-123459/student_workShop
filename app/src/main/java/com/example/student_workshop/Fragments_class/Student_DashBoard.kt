package com.example.student_workshop.Fragments_class

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.example.student_workshop.R
import com.example.student_workshop.Sqlite_database.Sql_Db_class


// This is the Home DashBoard Class to show  all applied workshop by user and some details about the user--------------------------


class Student_DashBoard : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // here call the function to remove backstack login fragment

        removeFragment()

//        here removing Login Fragment from backstack ------------------------------
        remove_login_frag_Fragment()

//         inflating the view --------------------------
        val view = inflater.inflate(R.layout.student_dashboard, container, false)



//        here find the all usable view id
        var text = view.findViewById<AppCompatTextView>(R.id.Show_All_workshop_text)


//        this is the list view to show student selected/added workshop --------------------
        val list = view.findViewById<ListView>(R.id.Your_workShop_list)

// here are the textview if the student not add  select any Workshop this textview will bw display
       var textViewNo_workShop = view.findViewById<TextView>(R.id.no_workShop_added)


//         here are the variable to add user selected data in this---------------
        var data1 = mutableListOf<String>()


//         getting the instance of class and call the function displayData to select all items from table
       var data =  Sql_Db_class(requireContext()).displayData(requireContext())

//         iterate all items one by one ---------------------
        for (j in data){

//            add item only that user selected true/1 --to show the student selected workshop course
            if (j.Is_selected==1){
//                add the data in data1 list--------
                data1.add(j.Course_type)
            }
        }

//        check if data size 0 -------------

        if (data1.size==0){

//            ------- here will be show the textview if data isEmpty/0 -----------------------

            textViewNo_workShop.visibility = View.VISIBLE

        }

//         here sat call prebuilt adapter class ------------------------------------------

        val adapter = ArrayAdapter(requireContext(),R.layout.applied_workshop_item_layout,R.id.Show_course_name,data1)




// sat the adapter to your list to show all items user selected item  -------------------------------
        list.adapter=adapter


//        here the textview in when user click load the fragment All_Workshop_Available to display workshop for students ----
        text.setOnClickListener {

             loadNewFragment()

        }



        return view
    }



    // you can see the Login_fragment class in Fragment_class Module where i describe about this function working

    private fun loadNewFragment() {
        val newFragment = AllAvailableWorkShop()
        requireFragmentManager().beginTransaction()
            .replace(R.id.Main_fragmet_container, newFragment,"All_Available_workshop")
            .addToBackStack("All_Available_workshop")
            .commit()
    }


    // you can see the Login_fragment class in Fragment_class Module where i describe about this function working

    private fun removeFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentToRemove = fragmentManager.findFragmentByTag("All_Available_workshop")

        fragmentManager.backStackEntryCount

        if (fragmentToRemove != null) {

            fragmentManager.beginTransaction()
                .remove(fragmentToRemove)
                .commit()
        }
    }


    // you can see the Login_fragment class in Fragment_class Module where i describe about this function working


    private fun remove_login_frag_Fragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentToRemove = fragmentManager.findFragmentByTag("Login_frag")

        fragmentManager.backStackEntryCount

        if (fragmentToRemove != null) {

            fragmentManager.beginTransaction()
                .remove(fragmentToRemove)
                .commit()
        }
    }










}






