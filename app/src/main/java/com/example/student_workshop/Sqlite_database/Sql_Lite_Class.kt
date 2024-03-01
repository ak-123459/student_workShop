package com.example.student_workshop.Sqlite_database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class Sql_Db_class(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Database information
        private const val DATABASE_NAME = "My_WorkShop"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create the table if not exists
        val createTableQuery = "CREATE TABLE IF NOT EXISTS AllWorkShop " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,courseType TEXT,is_selected Int)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop and recreate the table if there is an upgrade
        db.execSQL("DROP TABLE IF EXISTS AllWorkShop")
        onCreate(db)
    }

    fun insertData(courseType: String, is_selected: Int, context: Context): Int {
        // Insert data into the table
        val db = writableDatabase
        var data = 0
        val values = ContentValues()

        values.put("courseType", courseType)
        values.put("is_selected", is_selected)

        val rowId = db.insert("AllWorkShop", null, values)

        if (rowId != -1L) {
            // Data inserted successfully
            data = 1
        } else {
            // Error inserting data
        }

        db.close()

        return data
    }

    fun update_data(id: Int, is_selected: Int, context: Context) {
        // Update data in the table based on ID
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("is_selected", is_selected)

        // Update the row with the specified ID
        val update = db.update("AllWorkShop", values, "id" + " = ?", arrayOf<String>(java.lang.String.valueOf(id)))

        if (update != 0) {
            // Update successful
            Toast.makeText(context, " ✔\uFE0F Course Applied SuccessFully", Toast.LENGTH_SHORT).show()
        }

        db.close()
    }

    @SuppressLint("Range")
    fun displayData(context: Context): MutableList<WorkShop_deatail> {
        // Retrieve data from the table
        val db = readableDatabase
        var list = mutableListOf<WorkShop_deatail>()
        val projection = arrayOf("id", "courseType", "is_selected")

        val cursor: Cursor = db.query("AllWorkShop", projection, null, null, null, null, null)

        while (cursor.moveToNext()) {
            // Iterate through the result set
            val id: Int = cursor.getInt(cursor.getColumnIndex("id"))
            val course_type: String = cursor.getString(cursor.getColumnIndex("courseType"))
            val is_selected: Int = cursor.getInt(cursor.getColumnIndex("is_selected"))

            list.add(WorkShop_deatail(Course_type = course_type, Is_selected = is_selected))
        }

        cursor.close()
        db.close()

        return list
    }
}

data class WorkShop_deatail(var Course_type: String, val Is_selected: Int)
