package com.example.character

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.add_character.*

class AddWaleeActivity: AppCompatActivity() {
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_character)
        realm = Realm.getDefaultInstance()

        btnSaveCharacter.setOnClickListener(){
            val quote = editQuote.text.toString()
            val article = editArticle.text.toString()
            val source = editSource.text.toString()

            realm.executeTransaction { realm ->
                val maxValue = realm.where<Walee>().max("id")
                var pk:Int = 1
                if (maxValue != null) {
                    pk = maxValue.toInt() + 1
                }
                var waleeAdd = Walee(pk,quote,article,source)
                realm.insertOrUpdate(waleeAdd)
                Toast.makeText(applicationContext,"Add Already", Toast.LENGTH_SHORT).show()
                finish()
            }
        }//btnAddwalee

    }//OnCreate
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}