package com.example.proyecto_nuevo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*

enum class ProviderType{

    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var homeFragment: HomeFragment
    lateinit var tareasPendientes: TareasPendientes
    lateinit var linksReuniones: Links_Reuniones
    lateinit var apoyos: Apoyos
    lateinit var asesorias: Asesorias
    lateinit var logoutFragment: LogoutFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        val bundle :Bundle?=intent.extras
        val email :String?=bundle?.getString("email")
        val provider :String?=bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()



        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle (
            this,
            drawerLayout,
            toolBar,
            (R.string.open),
            (R.string.close)

        ){


        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }



    private fun setup(email:String, provider:String){

        title="Inicio"
       // emailTextView.text=email
        //providerTextView.text=provider

     /*   logOutButton.setOnClickListener {


            val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
*/
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when(menuItem.itemId){

            R.id.tablero ->{
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }

            R.id.tapen ->{
                tareasPendientes = TareasPendientes()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, tareasPendientes)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.links ->{
                linksReuniones = Links_Reuniones()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, linksReuniones)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.apoyos ->{
                apoyos = Apoyos()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, apoyos)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.asesorias ->{
                asesorias = Asesorias()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, asesorias)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.chat ->{
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.ajustes ->{
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.logout ->{
                logoutFragment = LogoutFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, logoutFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                prefs.clear()
                prefs.apply()
                FirebaseAuth.getInstance().signOut()
                val authIntent = Intent(this,AuthActivity::class.java)
                startActivity(authIntent)
                Toast.makeText(applicationContext, "Cerraste sesión correctamente", Toast.LENGTH_LONG).show()

            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }
    override fun onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }


}