package com.example.smart.activity.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.smart.R
import com.example.smart.activity.data.UserVO
import com.example.smart.activity.persistance.Dao.db.UserDB
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mTheDB:UserDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTheDB = UserDB.getDBInstance(this)
        var name = nameTxt.text.toString()
        var phone = phoneTxT.text.toString()
        var email = emailTxt.text.toString()
        var address = addressTxt.text.toString()

        val positiveButtonClick = {
            dialog:DialogInterface,which:Int ->
            Toast.makeText(applicationContext,
            android.R.string.ok,Toast.LENGTH_SHORT).show()
        }

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { radioGroup, i ->
               val radio:RadioButton = findViewById(i)
                val toast =  Toast.makeText(applicationContext,"On checked change:${radio.text}",Toast.LENGTH_SHORT)
                toast.show()
            })

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip:Chip ?= findViewById(checkedId)
            chip.let {
                val toast =  Toast.makeText(applicationContext,"On checked change:${chip?.text}",Toast.LENGTH_SHORT)
                toast.show()
            }

        }
        doneBtn.setOnClickListener {
            if (name != null || email != null || address != null || radioGroup != null || chipGroup != null) {
                if (name?.isEmpty() == true || email?.isEmpty() == true || address?.isEmpty() == true){
                    val toastInvalid = Toast.makeText(applicationContext,"Please fill require field ",Toast.LENGTH_SHORT)
                    toastInvalid.show()
                    val user = ArrayList<UserVO>()
                    user.add(UserVO(name = name,phone = phone, email = email,address = address))
                    mTheDB.userDao().insetUserList(user)

                    val items = arrayOf(name, phone ,email , address)
                    val builder = AlertDialog.Builder(this)
                    with(builder)
                    {
                        setTitle("List of Items")
                        setItems(items) { dialog, which ->
                            Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
                        }

                        setPositiveButton("OK", positiveButtonClick)
                        show()
                    }


                }else{
                    val toastCompleted = Toast.makeText(applicationContext,"Your form is successfully submitted",Toast.LENGTH_SHORT)
                    toastCompleted.show()
                }
            }else{
                val toastInvalid = Toast.makeText(applicationContext,"Please fill require field ",Toast.LENGTH_SHORT)
                toastInvalid.show()
            }
        }
    }


    }