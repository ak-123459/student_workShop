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
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.FragmentManager
import com.example.student_workshop.R
import com.example.student_workshop.Sqlite_database.Sql_Db_class





class WorkShopDashboard_Fragmet : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        removeFragment()
        remove_login_frag_Fragment()

        val view = inflater.inflate(R.layout.fragment_work_shop_dashboard__fragmet, container, false)


        var text = view.findViewById<AppCompatTextView>(R.id.Show_All_workshop_text)
        val layout1 = view.findViewById<LinearLayoutCompat>(R.id.workShop_layout)

        val list = view.findViewById<ListView>(R.id.Your_workShop_list)


       var textViewNo_workShop = view.findViewById<TextView>(R.id.no_workShop_added)

        var data1 = mutableListOf<String>()

       var data =  Sql_Db_class(requireContext()).displayData(requireContext())

        for (j in data){

            if (j.Is_selected==1){
                data1.add(j.Course_type)
            }
        }

        if (data1.size==0){

            textViewNo_workShop.visibility = View.VISIBLE

        }


        val adapter = ArrayAdapter(requireContext(),R.layout.layout_for_items_your_workshop,R.id.Show_course_name,data1)





        list.adapter=adapter

        text.setOnClickListener {

             loadNewFragment()

        }



        return view
    }




    private fun loadNewFragment() {
        val newFragment = All_WorkShop_Avil()
        requireFragmentManager().beginTransaction()
            .replace(R.id.Main_fragmet_container, newFragment,"All_Available_workshop")
            .addToBackStack("All_Available_workshop")
            .commit()
    }



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






    fun tag_to_Remove(tag:String){


    val fragmentManager = requireActivity().supportFragmentManager

// Replace "YourFragmentTag" with the tag of the fragment you want to remove
    fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)

}




}






