package com.example.pmd_ud6cp1_frutas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pmd_ud6cp1_frutas.R
import com.example.pmd_ud6cp1_frutas.model.Fruit


class Adapter (
    private val context: Context,
    private val layout: Int,
    private val fruits: ArrayList<Fruit>
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return fruits.size
    }

    override fun getItem(position: Int): Any {
        return fruits[position]
    }

    override fun getItemId(id: Int): Long {
        return id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        val layoutInflater = LayoutInflater.from(context)
        view = layoutInflater.inflate(layout, null)
        val fruit = fruits[position]

        val imgIcon = view.findViewById<ImageView>(R.id.icon)
        val txtName = view.findViewById<TextView>(R.id.name)
        val txtDescription = view.findViewById<TextView>(R.id.from)

        imgIcon.setImageResource(fruit.icon)
        txtName.text = fruit.name
        txtDescription.text = fruit.from

        return view
    }
}
