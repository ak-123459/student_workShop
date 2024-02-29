package com.example.student_workshop.Recycler_View.All_Workshop_list_Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.student_workshop.R
import com.example.student_workshop.Fragments_class.Login_fragment
import com.example.student_workshop.Sqlite_database.Sql_Db_class
import com.example.student_workshop.Sqlite_database.WorkShop_deatail
import com.example.student_workshop.Shared_Prefrences.WorkShopShared_Prefrence
import kotlin.properties.Delegates


class Available_item_adapter(context: Context, resource: Int, objects: List<WorkShop_deatail>, fragment: Fragment) : ArrayAdapter<WorkShop_deatail>(context, resource, objects) {

    var is_login by Delegates.notNull<Boolean>()

     var Fragments: Fragment  = fragment

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.all_availble_workshop_layout, parent, false)

            viewHolder = ViewHolder(
                view.findViewById(R.id.Course_details),
                view.findViewById(R.id.applybtn)
            )


            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        if (item != null) {
            viewHolder.textView.text = item.Course_type
        }

        if (item != null) {
            if (item.Is_selected==1){

                viewHolder.button.setBackgroundResource(R.drawable.btn_click_bg)
                viewHolder.button.text = "Applied"

            }
        }


         is_login = WorkShopShared_Prefrence(context).Is_Login(context)

        viewHolder.button.setOnClickListener {

            if (item != null) {

                if (is_login) {

                    if (item.Is_selected == 0) {
                        Sql_Db_class(context).update_data(position + 1, 1, context)

                        viewHolder.button.setBackgroundResource(R.drawable.btn_click_bg)

                    } else {


                        Toast.makeText(
                            context,
                            "❌ Sorry You Have Already Applied",
                            Toast.LENGTH_SHORT
                        ).show()


                    }

                }else{

                    Toast.makeText(context, " \uD83D\uDC64 Please Login First", Toast.LENGTH_SHORT).show()

                    loadNewFragment(fragment =Fragments)



                }
            }
        }

        return view
    }

    private data class ViewHolder(
        val textView: TextView,
        val button: Button
    )



    interface OnButtonClickListener {
        fun onButtonClick(item: String?, position: Int)
    }


    private var onButtonClickListener: OnButtonClickListener? = null


    fun setOnButtonClickListener(listener: OnButtonClickListener) {

        this.onButtonClickListener = listener
    }
}



@SuppressLint("SuspiciousIndentation")
public fun loadNewFragment(fragment: Fragment) {
    val newFragment = Login_fragment()
               fragment.requireFragmentManager().beginTransaction()
              .replace(R.id.Main_fragmet_container, newFragment,"Login_frag")
              .commit()
}

