package com.miodemi.squirrelsbox

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.miodemi.squirrelsbox.databinding.ActivityHomeBinding
import com.miodemi.squirrelsbox.fragments.ManageMainFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityHomeBinding
    private var actBox = 5
    private var maxBox = 10

    //Navigation Fragments
    private val mainFragment = ManageMainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //assign current and max boxes amount in text view
        binding.actBoxTV.text = actBox.toString()
        binding.maxBoxTV.text = maxBox.toString()

        //show fragment
        replaceFragment(mainFragment)

        menuNvIb.setOnClickListener{
            //hide active qwerty keyboard
            hideKeyboard()
            //open drawer function
            drawerLY.openDrawer(GravityCompat.START)
        }

        //assign button functions to the drawer menu
        binding.menuNV.setNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_profile -> {
                    Toast.makeText(applicationContext, "Profile clicked..", Toast.LENGTH_SHORT).show()
                    drawerLY.close()
                    // - - Here insert fragment - -
                    titleLy.isGone = true
                    false
                }
                R.id.nav_shared -> {
                    Toast.makeText(applicationContext, "Shared clicked..", Toast.LENGTH_SHORT).show()
                    drawerLY.close()
                    // - - Here insert fragment - -
                    false
                }
                R.id.nav_downloads -> {
                    Toast.makeText(applicationContext, "Downloads clicked..", Toast.LENGTH_SHORT).show()
                    // - - Here insert fragment - -
                    false
                }
                R.id.nav_settings -> {
                    Toast.makeText(applicationContext, "Settings clicked..", Toast.LENGTH_SHORT).show()
                    // - - Here insert fragment - -
                    drawerLY.close()
                    titleLy.isGone = true
                    false
                }
                else -> false
            }
        }
    }

    //basic function for fragment replacement
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLy, fragment)
        transaction.addToBackStack(null).commit()
    }

    //hide keyboard function
    private fun hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    //when on back/return click it will go back to the last fragment
    override fun onBackPressed() {
        val count = fragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            fragmentManager.popBackStack()
        }
    }
}