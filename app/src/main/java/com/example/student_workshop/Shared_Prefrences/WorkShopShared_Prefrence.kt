package com.example.student_workshop.Shared_Prefrences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class WorkShopShared_Prefrence(context: Context) {


    lateinit var SharedPreferences:SharedPreferences


    @SuppressLint("CommitPrefEdits")
    fun Add_Insertion(context: Context){

        SharedPreferences=context.getSharedPreferences("Add_data_first_time",0)

       var sp = SharedPreferences.edit().putBoolean("is_First",true)

        sp.apply()

    }


    fun Get_Insertion(context: Context):Boolean{

        SharedPreferences=context.getSharedPreferences("Add_data_first_time",0)

       return SharedPreferences.getBoolean("is_First",false)


    }

    fun Clear_Insertion(context: Context){

        SharedPreferences= context.getSharedPreferences("Add_data_first_time",0)

        SharedPreferences.edit().clear().apply()
    }

      fun Is_Login(context: Context):Boolean {

          SharedPreferences = context.getSharedPreferences("IS_LOGIN", 0)

          return SharedPreferences.getBoolean("IS_LOGIN", false)

      }




      fun Put_Is_Login(context: Context) {

          SharedPreferences = context.getSharedPreferences("IS_LOGIN", 0)

           SharedPreferences.edit().putBoolean("IS_LOGIN",true).apply()

      }


    fun Clear_IsLogin(context: Context){

        SharedPreferences= context.getSharedPreferences("IS_LOGIN",0)

        SharedPreferences.edit().clear().apply()
    }





      }

