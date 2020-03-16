package com.dongldh.retrofitexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class GitHubRepoAdapter(context: Context, val repos: List<GitHubRepo>): ArrayAdapter<GitHubRepo>(context, R.layout.list_item_pagination, repos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row: View? = convertView

        if (row == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            row = inflater.inflate(R.layout.list_item_pagination, parent, false)
        }

        val textView = row?.findViewById<TextView>(R.id.list_item_pagination_text)
        val item = repos[position]
        val message = item.name
        textView?.text = message

        return row!!
    }
}
