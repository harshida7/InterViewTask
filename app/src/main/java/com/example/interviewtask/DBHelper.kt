package com.example.interviewtask

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object
    {
        var DB_NAME = "calc.db"
        var TABLE_NAME = "datastore"

        var ID = "id"
        var ANSWER = "answer"

        var DB_VERSION = 3
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val query =
            "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY," +  ANSWER + " TEXT" + ")"

        db!!.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var upquery = "DROP TABLE if exists " + TABLE_NAME
        db!!.execSQL(upquery)
        onCreate(db)
    }

    fun insertdata(m: Model): Long {
        var db = writableDatabase // you have to write something for data insertion

        var values = ContentValues()//add all data inside values

//
        values.put(ANSWER, m.answer)
        var id = db.insert(TABLE_NAME, ID, values)

        return id
    }

    fun viewdata(): MutableList<Model> {

        var db = readableDatabase
        var list: ArrayList<Model> = ArrayList<Model>()
        var col = arrayOf(ID, /*NUMBER1, NUMBER2,*/ ANSWER)
        var cursor: Cursor = db.query(TABLE_NAME, col, null, null, null, null, null, null)
        //select * from tablename

        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var answer = cursor.getString(1)


            var m = Model()
            m.id = id
            m.answer = answer.toString()


            list.add(m)
        }
        return list
    }
}
