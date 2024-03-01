package com.example.student_workshop.Shared_Prefrences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


// here i make the WorkShopShared_Preference class to use the different function of its in various use cases
class WorkShopShared_Prefrence(context: Context) {

// declare late init var SharedPreferences
    lateinit var SharedPreferences:SharedPreferences

// here are the function we make to svae the value that can useful to check my sql lite database added in first time
    // because i dont need to add all Workshop data every time when user open the app ---------

//    here add the value true if this function call
    @SuppressLint("CommitPrefEdits")
    fun Add_Insertion(context: Context){

        SharedPreferences=context.getSharedPreferences("Add_data_first_time",0)

       var sp = SharedPreferences.edit().putBoolean("is_First",true)

        sp.apply()

    }


    //    here get the value  if this function call
    fun Get_Insertion(context: Context):Boolean{

        SharedPreferences=context.getSharedPreferences("Add_data_first_time",0)

       return SharedPreferences.getBoolean("is_First",false)


    }


    //    here clear the SharedPreferences if needed call this function--------------------------

    fun Clear_Insertion(context: Context){

        SharedPreferences= context.getSharedPreferences("Add_data_first_time",0)

        SharedPreferences.edit().clear().apply()
    }




//     here i make the function to save the login value of user ----------------------------------




    //     here i make the function to get the login value of user ----------------------------------
      fun Is_Login(context: Context):Boolean {

          SharedPreferences = context.getSharedPreferences("IS_LOGIN", 0)

          return SharedPreferences.getBoolean("IS_LOGIN", false)

      }

//     here i make the function to add the login value of user as true when this function wii be called ----------------------------------


      fun Put_Is_Login(context: Context) {

          SharedPreferences = context.getSharedPreferences("IS_LOGIN", 0)

           SharedPreferences.edit().putBoolean("IS_LOGIN",true).apply()

      }

//    clear the login value when user logout -------------------------------------------------------------

    fun Clear_IsLogin(context: Context){

        SharedPreferences= context.getSharedPreferences("IS_LOGIN",0)

        SharedPreferences.edit().clear().apply()
    }





      }

