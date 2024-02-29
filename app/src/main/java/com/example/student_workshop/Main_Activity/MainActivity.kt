package com.example.student_workshop.Main_Activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.student_workshop.R
import com.example.student_workshop.Fragments_class.WorkShopDashboard_Fragmet
import com.example.student_workshop.Sqlite_database.Sql_Db_class
import com.example.student_workshop.Sqlite_database.WorkShop_deatail
import com.example.student_workshop.Shared_Prefrences.WorkShopShared_Prefrence

class MainActivity : AppCompatActivity(){

    private lateinit var Sql_db: Sql_Db_class

    lateinit var layout: LinearLayoutCompat

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        loadDefaultFragment(this)

        Sql_db = Sql_Db_class(this)


        var shared_prefrences = WorkShopShared_Prefrence(this)

        var is_first_insertion = shared_prefrences.Get_Insertion(this)


        if (!is_first_insertion) {

            Add_Firts_time_data(Sql_db, this)

        }


    }




    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager

        // Check if there are fragments in the back stack

        if (fragmentManager.backStackEntryCount !=1) {
            // Pop the top fragment from the back stack
            fragmentManager.popBackStack()

        } else {

            this.finish()
            super.onBackPressed()

        }
    }



    private fun loadDefaultFragment(activity: MainActivity) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.Main_fragmet_container, WorkShopDashboard_Fragmet(),"Dashboard_frag")
            .addToBackStack("Dashboard_frag")
            .commit()
    }






}


@SuppressLint("SuspiciousIndentation")
fun Add_Firts_time_data(sqlDbClass: Sql_Db_class, context: Context){

    var i = 0

    var list_of = listOf<WorkShop_deatail>(
        WorkShop_deatail(Course_type = "Learn Python Beginer To Advance", Is_selected = 0)
        , WorkShop_deatail(Course_type = "Full stack web development", Is_selected = 0)
        , WorkShop_deatail(Course_type = "Android Development", Is_selected = 0),
        WorkShop_deatail(Course_type = "MySql Tutorial", Is_selected = 0),
        WorkShop_deatail(Course_type = "Data Science Full Course", Is_selected = 0),
        WorkShop_deatail(Course_type = "Java Intermediate level", Is_selected = 0),
        WorkShop_deatail(Course_type = "Ethical Hacking", Is_selected = 0)
    )

    var list = mutableListOf<Int>()

    for (j in list_of){

      var data =   sqlDbClass.insertData(j.Course_type,j.Is_selected, context = context)

        if (data!=0) {

            list.add(data)

        }

    }

    if (list.size==list_of.size){

        Toast.makeText(context, "All Data Inserted Successfully", Toast.LENGTH_SHORT).show()
        WorkShopShared_Prefrence(context).Add_Insertion(context)
    }





}





