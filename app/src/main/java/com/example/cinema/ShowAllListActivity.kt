package com.example.cinema

//import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_film_list.*

class ShowAllListActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var adapter: WaleeListAdapter
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)
        //filmsView.layoutManager = LinearLayoutManager(this)
        //filmsView.adapter = FilmListAdapter(Data.films)
        realm = Realm.getDefaultInstance()

//        realm.beginTransaction();
//        realm.deleteAll();
//        realm.commitTransaction();
        
        val walees = realm.where<Walee>().sort("id").findAll()
        walees.addChangeListener(changeListener)
        adapter = WaleeListAdapter(walees)
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this)

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
//            val intent = Intent(addWalee.context, ShowAllListActivity::class.java)
//            addWalee.context.startActivity(intent)
            Toast.makeText(applicationContext,"This Page!!",Toast.LENGTH_SHORT).show()
        }
    }//onCreate
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    private val changeListener =
        OrderedRealmCollectionChangeListener<RealmResults<Walee>> { _, changeSet ->
            adapter.notifyDataSetChanged()
        }
}
