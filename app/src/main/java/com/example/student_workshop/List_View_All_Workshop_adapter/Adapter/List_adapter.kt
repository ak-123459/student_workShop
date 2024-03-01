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


    // here are Creating the adapter class to show All WorkShop list  item with ListView -------------------------------------------
class Available_item_adapter(context: Context, resource: Int, objects: List<WorkShop_deatail>, fragment: Fragment) :
    ArrayAdapter<WorkShop_deatail>(context, resource, objects) {

     // Flag to check if user is logged in
    var is_login by Delegates.notNull<Boolean>()

     // Reference to the fragment
    var Fragments: Fragment = fragment

     // Inflating layout for each list item
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

     // ViewHolder pattern for efficient view recycling
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder


        // Check if the view is being recycled, otherwise inflate the view
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.available_workshop_item_layout, parent, false)

            viewHolder = ViewHolder(
                view.findViewById(R.id.Course_details),
                view.findViewById(R.id.applybtn)
            )

            // Set the ViewHolder as a tag for the view to optimize recycling
            view.tag = viewHolder
        } else {
            // Reuse the recycled view and retrieve the ViewHolder
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // Get the current item at the position
        val item = getItem(position)

        // Display course type in the TextView
        if (item != null) {
            viewHolder.textView.text = item.Course_type
        }

        // Check if the workshop is already applied, update button text and background accordingly
        if (item != null) {
            if (item.Is_selected == 1) {
                viewHolder.button.setBackgroundResource(R.drawable.btn_click_bg)
                viewHolder.button.text = "Applied"
            }
        }

        // Check if user is logged in
        is_login = WorkShopShared_Prefrence(context).Is_Login(context)

        // Set click listener for the apply button
        viewHolder.button.setOnClickListener {

            if (item != null) {

//                 executed when user is_login true ------------------------
                if (is_login) {

                    if (item.Is_selected == 0) {
                        // Update the database on button click
                        Sql_Db_class(context).update_data(position + 1, 1, context)
                        viewHolder.button.setBackgroundResource(R.drawable.btn_click_bg)

                    } else {

                        // Display a message if the workshop is already applied
                        Toast.makeText(
                            context,
                            "❌ Sorry You Have Already Applied",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {

                    // Display a message if the user is not logged in and navigate to login fragment
                    Toast.makeText(context, " \uD83D\uDC64 Please Login First", Toast.LENGTH_SHORT).show()
                    loadNewFragment(fragment = Fragments)
                }
            }
        }

        return view
    }

    // ViewHolder class to hold the views for each item
    private data class ViewHolder(
        val textView: TextView,
        val button: Button
    )

    // Interface to handle button click events
    interface OnButtonClickListener {
        fun onButtonClick(item: String?, position: Int)
    }

    // Listener for button clicks
    private var onButtonClickListener: OnButtonClickListener? = null

    // Setter for button click listener
    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        this.onButtonClickListener = listener
    }
}

// Function to load a new fragment
@SuppressLint("SuspiciousIndentation")
public fun loadNewFragment(fragment: Fragment) {
    val newFragment = Login_fragment()
    fragment.requireFragmentManager().beginTransaction()
        .replace(R.id.Main_fragmet_container, newFragment, "Login_frag")
        .commit()
}
