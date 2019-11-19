package com.example.money.list

import android.graphics.Color
import android.text.Html
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.money.R
import com.example.money.database.Person

class ListPersonAdapter: RecyclerView.Adapter<TextItemViewHolder>() {


    var data =  listOf<Person>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {

        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
        val item = data[position]

        var text = "<font color=#D4AC0D> ${item.firstName}</font> "+ "<font color=#00000>--</font> "+
                "<font color=#D4AC0D> ${item.lastName}</font> "+ "<font color=#000000>--</font> "+
                "<font color=#000000> ${item.amount}</font> "

        holder.textView.text = Html.fromHtml(text)

//        holder.textView.text = "\tName ${item.firstName} -- ${item.lastName} Amount ${item.amount.toString()}\n"
//        holder.textView.setTextColor(Color.parseColor("#D4AC0D"))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }

}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)