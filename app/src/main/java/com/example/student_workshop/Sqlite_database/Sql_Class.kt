package com.example.student_workshop.Sqlite_database

import android.R.id
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class Sql_Db_class(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "My_WorkShop"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTableQuery = "CREATE TABLE IF NOT EXISTS AllWorkShop " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,courseType TEXT,is_selected Int)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS AllWorkShop")
        onCreate(db)
    }

    fun insertData(courseType: String,is_selected:Int,context:Context):Int {
        val db = writableDatabase
        var data = 0

        val values = ContentValues()

        values.put("courseType", courseType)

        values.put("is_selected",is_selected)

        val rowId = db.insert("AllWorkShop", null, values)

        if (rowId != -1L) {

            data = 1

            Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error inserting data", Toast.LENGTH_SHORT).show()
        }

        db.close()

        return data
    }




    fun update_data(id:Int,is_selected:Int,context:Context) {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put("is_selected", is_selected)

        // Update the row with the specified ID

        // Update the row with the specified ID
       var upadte =  db.update(
            "AllWorkShop",
            values,
            "id" + " = ?",
            arrayOf<String>(java.lang.String.valueOf(id))
        )



        if (upadte!=0){



            Toast.makeText(context, " ✔\uFE0F Course Applied SuccessFully", Toast.LENGTH_SHORT).show()

        }

        db.close()


    }




    @SuppressLint("Range")
    fun displayData(context: Context) :MutableList<WorkShop_deatail>{
        val db = readableDatabase
        var list = mutableListOf<WorkShop_deatail>()
        val projection = arrayOf("id", "courseType","is_selected")

        val cursor: Cursor = db.query("AllWorkShop", projection, null, null, null, null, null)


        while (cursor.moveToNext()) {

            val id: Int = cursor.getInt(cursor.getColumnIndex("id"))

            val course_type: String = cursor.getString(cursor.getColumnIndex("courseType"))


            val is_selected:Int= cursor.getInt(cursor.getColumnIndex("is_selected"))


            list.add(WorkShop_deatail(Course_type = course_type,is_selected))

        }

        cursor.close()
        db.close()

        return  list
    }
}

data class WorkShop_deatail(var Course_type:String, val Is_selected:Int)
