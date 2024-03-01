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

// this is the fragment class to show all the list  workshop of all items
class AllAvailableWorkShop : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

// here call the function to remove backstack login fragment

        removeFragment()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.available_workshop, container, false)


//        find the all require id from this fragment layout -------------

//       here find the list layout id
        val listView: ListView = view.findViewById(R.id.All_work_shop_list)

        var applyBtn = view.findViewById<AppCompatButton>(R.id.applybtn)


//        here make object for sql database to display data

        var sql = Sql_Db_class(requireContext())

//        here getting all data from sql db class

        var data = sql.displayData(requireContext())






// here make the make the instance of our custom adapter class ------------------------
        val adapter = Available_item_adapter(requireContext(),
            R.layout.available_workshop_item_layout, data,this)

// here set the adapter to our listview -------------
        listView.adapter = adapter


//       return view to create view for this  fragment
        return view
    }

// here define the function to remove backstack fragment Login-------------
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







