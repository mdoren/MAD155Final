package com.example.mad155final

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyCustomAdapter(var ctx: Context, var ourResource: Int,
                      var list: ArrayList<Model>): ArrayAdapter<Model>(ctx, ourResource, list){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{

        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(ourResource, null)

        val tag = view.findViewById<TextView>(R.id.tag)
        val name = view.findViewById<TextView>(R.id.name)
        val img = view.findViewById<ImageView>(R.id.img1)

        tag.text = list[position].tag
        name.text = list[position].name
        img.setImageDrawable(ctx.resources.getDrawable(list[position].img))

        return view
    }
}
