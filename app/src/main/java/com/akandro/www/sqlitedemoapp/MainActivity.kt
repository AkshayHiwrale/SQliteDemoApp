package com.akandro.www.sqlitedemoapp

import android.content.ContentValues
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dBase = openOrCreateDatabase("emp_db",
                Context.MODE_PRIVATE,null)
        dBase.execSQL("create table if not exists employee(_id integer primary key autoincrement,emp_id number,name varchar(100),desig varchar(100),dept varchar(100) )")

//insert code start


        insert.setOnClickListener {

            var cv = ContentValues()

            cv. put("emp_id",et1.text.toString().toInt())
             cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())
            cv.put("dept",et4.text.toString())

            var status = dBase.insert("employee",null,cv)


            if(status!= -1.toLong()) {


                Toast.makeText(this@MainActivity, "Insertion Success...", Toast.LENGTH_LONG).show()

                et1.setText("");et2.setText("")
                et3.setText("");et4.setText("")
            }
            else{

                Toast.makeText(this@MainActivity, "Insertion Failed...", Toast.LENGTH_LONG).show()
            }

        }
        //insert

        read.setOnClickListener {
            var  c = dBase.query("employee",null,null,null,null,null,null)

            var from:Array<String> = arrayOf("emp_id",
                    "name","desig","dept")
            var to: IntArray = intArrayOf(R.id.tv1,
                    R.id.tv2,R.id.tv3,R.id.tv4)
            var adapter = SimpleCursorAdapter(this@MainActivity,
                    R.layout.indiview,c,from,to,0)
            lview.adapter = adapter
        }


        update.setOnClickListener {


            // update table_name set name=?,desig=?  where emp_id=?
/* String table, ContentValues values,
         String whereClause, String[] whereArgs   ->  Int */

            var cv = ContentValues()

            cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())

            var status = dBase.update("employee",cv,"emp_id", arrayOf(et1.text.toString()))


            if(status!=0){
                Toast.makeText(this@MainActivity,
                        "Data Updated  Successfully...",
                        Toast.LENGTH_LONG).show()
                et1.setText("");et2.setText("")
                et3.setText("");et4.setText("")
            }else{
                Toast.makeText(this@MainActivity,
                        "Data Updatation is Failed...",
                        Toast.LENGTH_LONG).show()
            }
        }




    delete.setOnClickListener({
        // delete from employee where emp_id=?
        // String table, String whereClause, String[] whereArgs -> Int
        var status =  dBase.delete("employee","emp_id=?",
                arrayOf(et1.text.toString()))
        if(status!=0){
            Toast.makeText(this@MainActivity,
                    "Data Deleted  Successfully...",
                    Toast.LENGTH_LONG).show()
            et1.setText("");et2.setText("")
            et3.setText("");et4.setText("")
        }else{
            Toast.makeText(this@MainActivity,
                    "Data Deletion is Failed...",
                    Toast.LENGTH_LONG).show()
        }
    })

}

    }








