package com.example.interviewtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import kotlin.Float as Float

class MainActivity : AppCompatActivity() {

    lateinit var edtNum1:EditText
    lateinit var edtNum2:EditText
    lateinit var btnClick:Button
    lateinit var btnHistory:Button
    lateinit var txtResult:TextView
    lateinit var spin:Spinner
    lateinit var dbHelper:DBHelper

    var spin1 = arrayOf("Select Operation","Addition", "Substraction", "Multiplication", "Divison","Modulus")




    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNum1 = findViewById(R.id.edtNum1)
        edtNum2 = findViewById(R.id.edtNum2)
        btnClick = findViewById(R.id.btnClick)
        btnHistory = findViewById(R.id.btnHistory)
        txtResult = findViewById(R.id.txtResult)
        spin = findViewById(R.id.spin1)

        dbHelper = DBHelper(applicationContext)


        val adap =ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, spin1)
        adap.setDropDownViewResource(R.layout.design_spinner)
        spin.adapter = adap

           spin.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener
           {
               override fun onItemSelected(parent: AdapterView<*>?,view: View?, position: Int,id: Long)
               {
                   btnClick.setOnClickListener {

                       var num1 = Float(edtNum1.text.toString()).toFloat()
                       var num2 = Float(edtNum2.text.toString()).toFloat()


                       var m = Model()
                       when(position)
                       {
                           1 ->
                           {
                               var add = num1 + num2
                               txtResult.setText("$add")
                               m.answer  = "\n" + num1  + "  +  " +  num2 + "  =  " + "  " +  add.toString()
                           }

                           2 ->
                           {
                               var sub = num1 - num2
                               txtResult.setText("$sub")
                               m.answer  = "\n" + num1  + "  -  " +  num2 + "  =  " + "  " +  sub.toString()
                           }
                           3 ->
                           {
                               var mul = num1 * num2
                               txtResult.setText("$mul")
                               m.answer  = "\n" + num1  + "  *  " +  num2 + "  =  " + "  " +  mul.toString()
                           }
                           4 ->
                           {
                               var div = num1 / num2
                               txtResult.setText("$div")
                               m.answer  = "\n" + num1  + "  /  " +  num2 + "  =  " + "  " +  div.toString()
                           }
                           5 ->
                           {
                               var mod = num1 % num2
                               txtResult.setText("$mod")
                               m.answer  = "\n" + num1  + "  %  " +  num2 + "  =  " + "  " +  mod.toString()
                           }
                           else ->
                           {
                               Toast.makeText(applicationContext, "Please Select Any Opration", Toast.LENGTH_SHORT).show()
                           }

                       }
                       var data =  dbHelper.insertdata(m)

                       Toast.makeText(applicationContext,"Record Inserted " + data, Toast.LENGTH_LONG).show()

                   }

               }

               override fun onNothingSelected(parent: AdapterView<*>?) {

               }

           }


        btnHistory.setOnClickListener {


                startActivity(Intent(applicationContext,ViewActivity::class.java))

        }
        }


    }





