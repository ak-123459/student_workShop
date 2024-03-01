package com.example.student_workshop.Main_Activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.student_workshop.R
import com.example.student_workshop.Fragments_class.Student_DashBoard
import com.example.student_workshop.Sqlite_database.Sql_Db_class
import com.example.student_workshop.Sqlite_database.WorkShop_deatail
import com.example.student_workshop.Shared_Prefrences.WorkShopShared_Prefrence

class MainActivity : AppCompatActivity(){

//    we will get the sqlDatabase instance in this Sql_db variable
    private lateinit var Sql_db: Sql_Db_class

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


// load the by default fragment ---------------------------------------------------
        loadDefaultFragment(this)
// get the instance of our Sql database class -------------------------------
        Sql_db = Sql_Db_class(this)

// here we have create the object to get the shared_prefrences class instance
        var shared_prefrences = WorkShopShared_Prefrence(this)
// call the function  and check all the data inserted is inserted or not -----------------------
        var is_first_insertion = shared_prefrences.Get_Insertion(this)

// if false ----------
        if (!is_first_insertion) {

//            add the data of workshop  in sql database in one time ----------------------------------
            Add_Firts_time_data(Sql_db, this)

        }


    }



// here we are override the OnBackpressed to manage backstack fragments ----
// beacause when user click back button we want to load previous screen and clear the all backstack fragment screen---------------------
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


// load fragment--------------------------------
    private fun loadDefaultFragment(activity: MainActivity) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.Main_fragmet_container, Student_DashBoard(),"Dashboard_frag")
            .addToBackStack("Dashboard_frag")
            .commit()
    }






}

// here define the function Add_Firts_time_data to add all the workshop data in sqlLite db table first time ---------------
@SuppressLint("SuspiciousIndentation")
fun Add_Firts_time_data(sqlDbClass: Sql_Db_class, context: Context){


//     here the list type WorkShop_deatail to insert different type data you can use hashmap ---------------
    var list_of = listOf<WorkShop_deatail>(
        WorkShop_deatail(Course_type = "Learn Python Beginer To Advance", Is_selected = 0)
        , WorkShop_deatail(Course_type = "Full stack web development", Is_selected = 0)
        , WorkShop_deatail(Course_type = "Android Development", Is_selected = 0),
        WorkShop_deatail(Course_type = "MySql Tutorial", Is_selected = 0),
        WorkShop_deatail(Course_type = "Data Science Full Course", Is_selected = 0),
        WorkShop_deatail(Course_type = "Java Intermediate level", Is_selected = 0),
        WorkShop_deatail(Course_type = "Ethical Hacking", Is_selected = 0)
    )


//     here intilise list -------------- to check all data inserted or not ---------------------

    var list = mutableListOf<Int>()

//    iterate the list_of  ----------------------
    for (j in list_of){


//         call the SqlDbClass to insert data in sqlDB ---------------------------------
      var data =   sqlDbClass.insertData(j.Course_type,j.Is_selected, context = context)

//        here this data variable return affected row -----------------------------
        if (data!=0) {

            list.add(data)

        }

    }

//     check if list size == list_of size all data inserted successfully ----------------------------------

    if (list.size==list_of.size){

//        make toast ----------------

//         now when all the data inserted successfully we will save the valu true in shared prefrence beacuse we dont want to add workshop
//        data again and again ------------------
        WorkShopShared_Prefrence(context).Add_Insertion(context)
    }





}





