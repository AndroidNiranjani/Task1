package com.example.task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity.LEFT
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.solver.widgets.Barrier.LEFT
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class NavigationDrawerEx : AppCompatActivity() {

    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer_ex)

        val drawer_layouty:DrawerLayout = findViewById(R.id.drawer_layouty);
        val nav_view:NavigationView=findViewById(R.id.nav_view)

        toggle= ActionBarDrawerToggle(this,drawer_layouty,R.string.open,R.string.close)
        drawer_layouty.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled( true)

        nav_view.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.home -> {startActivity(Intent(this,MainActivity::class.java) )
                                drawer_layouty.closeDrawer(GravityCompat.START)}

                R.id.shoppinglist ->{
                    startActivity(Intent(this,ShoppingList::class.java) )
                    drawer_layouty.closeDrawer(GravityCompat.START)
                }

                R.id.share -> startActivity(Intent(this,CalendarViewDemo::class.java) )
                R.id.link -> startActivity(Intent(this,MVVMDemo::class.java) )
                R.id.enumID -> startActivity(Intent(this,GraphDemo::class.java))
                R.id.languageID -> startActivity(Intent(this,ExpandableListViewDemo::class.java))
            }
            true
            }
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
    }

