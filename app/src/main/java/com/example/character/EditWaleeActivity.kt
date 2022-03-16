package com.example.character

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.edit_character.*

class EditWaleeActivity : AppCompatActivity(){
    private lateinit var realm: Realm
    private lateinit var walee: Walee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_character)

        realm = Realm.getDefaultInstance()
        val id = intent.getStringExtra(EXTRA_STUDENT_ID).toInt()
        walee = realm.where<Walee>().equalTo("id", id)
            .findFirst() ?: return
            idEditText.setText(walee.id.toString())
            editQuote.setText(walee.quote)
            editArticle.setText(walee.article)
            editSource.setText(walee.source)

        btnSaveCharacter.setOnClickListener {
            realm.executeTransaction {
                walee.quote = editQuote.text.toString()
                walee.article = editArticle.text.toString()
                walee.source = editSource.text.toString()
            }
            Toast.makeText(applicationContext,"Save Already",Toast.LENGTH_SHORT).show()
            finish()
        }

        deleteButton.setOnClickListener {
            realm.executeTransaction {
                walee.deleteFromRealm()
            }
            Toast.makeText(applicationContext,"Delete Already",Toast.LENGTH_SHORT).show()
            finish()
        }
    }//onCreate

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    companion object {
        const val EXTRA_STUDENT_ID = "EXTRA_STUDENT_ID"
    }
}