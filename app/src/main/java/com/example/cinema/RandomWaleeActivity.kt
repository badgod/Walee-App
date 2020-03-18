package com.example.cinema

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.random_view_walee.*
import java.util.*


class RandomWaleeActivity : AppCompatActivity(){
    private lateinit var realm: Realm
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.random_view_walee)

        realm = Realm.getDefaultInstance()

        val results: RealmResults<Walee> = realm.where<Walee>(Walee::class.java).findAll()
        var prevRandom: Walee? = null

        btnRandom.performClick()
        btnRandom.setOnClickListener(){

            if(results.size < 3){
                Toast.makeText(applicationContext,"Please Add Quote Before!!", Toast.LENGTH_SHORT).show()
            }else{
                val r = Random(System.nanoTime())
                val firstRandomNumber: Int = r.nextInt(results.size)
                val firstRandomObject: Walee? = results[firstRandomNumber]
                if (firstRandomObject?.equals(prevRandom)!!){
                    btnRandom.performClick()
                }

                val article = "👦: "+ firstRandomObject!!.article
                val source = "🌐: "+ firstRandomObject!!.source

                randomView.text = firstRandomObject!!.quote
                randomArticleView.text = article
                randomSourceView.text = source

                if (prevRandom !== null){
                    Toast.makeText(applicationContext,"Random Already.", Toast.LENGTH_SHORT).show()
                }
                prevRandom = results[firstRandomNumber]
            }

            closeButton.callOnClick()
        }

        addButton.setOnClickListener(){
            //Toast.makeText(applicationContext,"Open Menu",Toast.LENGTH_SHORT).show()
            addButton.visibility = View.INVISIBLE
            imgView.visibility = View.VISIBLE
            addWalee.visibility = View.VISIBLE
            allWalee.visibility = View.VISIBLE
            closeButton.visibility = View.VISIBLE
        }

        closeButton.setOnClickListener(){
            //Toast.makeText(applicationContext,"Close Menu",Toast.LENGTH_SHORT).show()
            addButton.visibility = View.VISIBLE
            imgView.visibility = View.INVISIBLE
            addWalee.visibility = View.INVISIBLE
            allWalee.visibility = View.INVISIBLE
            closeButton.visibility = View.INVISIBLE
        }

        addWalee.setOnClickListener {
            closeButton.callOnClick()
            val intent = Intent(addWalee.context, AddWaleeActivity::class.java)
            addWalee.context.startActivity(intent)
        }

        allWalee.setOnClickListener {
            closeButton.callOnClick()
            val intent = Intent(addWalee.context, ShowAllListActivity::class.java)
            addWalee.context.startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}