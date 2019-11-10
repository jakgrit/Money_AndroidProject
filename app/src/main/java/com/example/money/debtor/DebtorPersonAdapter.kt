package com.example.money.debtor

import android.app.Application
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.money.R
import com.example.money.database.Person
import com.example.money.database.PersonDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DebtorPersonAdapter: RecyclerView.Adapter<TextItemViewHolder>() {


    var data =  listOf<Person>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {

        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
        val item = data[position]

        holder.textView.text = "\t${item.firstName}     ---     ${item.lastName}\n"

        holder.textView.setTextColor(Color.parseColor("#D4AC0D"))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)