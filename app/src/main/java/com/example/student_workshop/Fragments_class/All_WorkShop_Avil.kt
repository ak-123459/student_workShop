package com.example.student_workshop.Fragments_class

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.widget.AppCompatButton
import com.example.student_workshop.R
import com.example.student_workshop.Recycler_View.All_Workshop_list_Adapter.Available_item_adapter
import com.example.student_workshop.Sqlite_database.Sql_Db_class


class All_WorkShop_Avil : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        removeFragment()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all__work_shop__avil, container, false)


        val listView: ListView = view.findViewById(R.id.All_work_shop_list)

        var sql = Sql_Db_class(requireContext())

        var data = sql.displayData(requireContext())


        var applyBtn = view.findViewById<AppCompatButton>(R.id.applybtn)




        val adapter = Available_item_adapter(requireContext(),
            R.layout.all_availble_workshop_layout, data,this)


        listView.adapter = adapter


        return view
    }


    private fun removeFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentToRemove = fragmentManager.findFragmentByTag("Login_frag")

        if (fragmentToRemove != null) {

            fragmentManager.beginTransaction()
                .remove(fragmentToRemove)
                .commit()
        }
    }








}







