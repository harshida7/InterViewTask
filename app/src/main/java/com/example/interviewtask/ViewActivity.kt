package com.example.interviewtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class ViewActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var list:MutableList<Model>
    lateinit var dbHelper: DBHelper

    var arraylist:ArrayList<HashMap<String,String>> = ArrayList<HashMap<String,String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        listView = findViewById(R.id.list)
        list = ArrayList()
        dbHelper = DBHelper(applicationContext)

        list = dbHelper.viewdata()

        for(i in list)
        {
            var map = HashMap<String,String>()
            map.put("ANSWER",i.answer)

            arraylist.add(map)
        }

        var from = arrayOf("ANSWER")
        var to = intArrayOf(R.id.txtAnswer)

        var adapter = SimpleAdapter(applicationContext,arraylist,R.layout.design,from,to)
        listView.adapter=adapter

        registerForContextMenu(listView)
    }
    }

