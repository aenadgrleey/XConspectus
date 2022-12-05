package com.example.xconspectus

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xconspectus.data.repositories.SubjectsRepository
import com.example.xconspectus.databinding.ActivityMainBinding
import com.example.xconspectus.ui.navdrawer.MyNavigationDrawerSubjectRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggleActionBar: ActionBarDrawerToggle
    private lateinit var repository: SubjectsRepository
    private var myAdapter = MyNavigationDrawerSubjectRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        repository = SubjectsRepository(applicationContext)

        //Menu drawer define
        drawerFilling(this, binding.navigationIncluded.navigationMenuRecyclerView)
        toggleActionBar =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggleActionBar)
        toggleActionBar.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        setObservers()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_support, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggleActionBar.onOptionsItemSelected(item)) return true
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun drawerFilling(context: Context, view: View) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }


    }

    private fun setObservers() {
        repository.subjects.observe(this, androidx.lifecycle.Observer {
            myAdapter.onItemLoad(repository.subjects.value)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.navHostFragmentContainer.findNavController().navigateUp()
    }


}


class XConspectus : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}