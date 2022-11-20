package com.miodemi.squirrelsbox


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miodemi.squirrelsbox.databinding.ActivityHomeBinding
import com.miodemi.squirrelsbox.inventory.application.HomeSearchViewModel
import com.miodemi.squirrelsbox.inventory.presentation.SearchFragment
import com.miodemi.squirrelsbox.profile.presentation.AddDialogViewFab
import com.miodemi.squirrelsbox.inventory.presentation.box.AddBoxDialogFragment
import com.miodemi.squirrelsbox.inventory.presentation.item.AddItemDialogFragment
import com.miodemi.squirrelsbox.inventory.presentation.section.AddSectionDialogFragment
import com.miodemi.squirrelsbox.profile.presentation.download.DownloadedBoxFragment
import com.miodemi.squirrelsbox.profile.presentation.home.*
import com.miodemi.squirrelsbox.profile.presentation.profile.MenuProfileFragment
import com.miodemi.squirrelsbox.profile.presentation.settings.MenuSettingsFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*


class HomeActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityHomeBinding
    private var actBox = 5
    private var maxBox = 10

    //Navigation Fragments
    private val homeBoxFragment = HomeBoxFragment()
    private val homeSectionFragment = HomeSectionFragment()
    private val homeItemFragment = HomeItemFragment()
    private val homeSearchFragment = HomeSearchFragment()
    private val downloadedBoxFragment = DownloadedBoxFragment()
    private val homeSearchFragment = SearchFragment()

    //UI Fragments
    private val profileFragment = MenuProfileFragment()
    private val settingsFragment = MenuSettingsFragment()

    // floating action buttons and a boolean variable.
    lateinit var menuFAB: FloatingActionButton
    lateinit var addBoxFAB: FloatingActionButton
    lateinit var favouriteFAB: FloatingActionButton
    lateinit var homeFAB: FloatingActionButton
    private var fabVisible = false

    private val viewModel: HomeViewModel by viewModels()
    private val viewModelFAB: AddDialogViewFab by viewModels()
    private val viewModelHomeSearch: HomeSearchViewModel by viewModels()

    private var our_request_code : Int = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //assign current and max boxes amount in text view
        binding.actBoxTV.text = actBox.toString()
        binding.maxBoxTV.text = maxBox.toString()

        //assign  current and max boxes amount in progress bar
        binding.actBoxesPb.max = maxBox
        binding.actBoxesPb.progress = actBox

        //show fragment
        replaceFragment(homeBoxFragment)

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
                    replaceFragment(profileFragment)
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
                    drawerLY.close()
                    replaceFragment(downloadedBoxFragment)
                    titleLy.isGone = true
                    false
                }
                R.id.nav_settings -> {
                    Toast.makeText(applicationContext, "Settings clicked..", Toast.LENGTH_SHORT).show()
                    drawerLY.close()
                    replaceFragment(settingsFragment)
                    titleLy.isGone = true
                    false
                }
                else -> false
            }
        }

        //searchbar
        searchBarBtn.setOnClickListener {
            titleLy.isGone = true
            replaceFragment(homeSearchFragment)
        }

        // initializing fab
        menuFAB = menuFab
        addBoxFAB = addBoxFab
        favouriteFAB = favouriteFab
        homeFAB = homeFab

        // on below line we are initializing our
        // fab visibility boolean variable
        fabVisible = false

        // on below line we are adding on click listener
        // for our add floating action button
        menuFAB.setOnClickListener {
            // on below line we are checking
            // fab visible variable.
            if (!fabVisible) {
                openMenuFAB()
            } else {
                closeMenuFAB()
            }
        }

        //Add fab on click actions

        //home fab
        homeFAB.setOnClickListener {
            // on below line we are displaying a toast message.
            Toast.makeText(this , "Home clicked..", Toast.LENGTH_SHORT).show()
            titleLy.isGone = false
            replaceFragment(homeBoxFragment)
            closeMenuFAB()
        }

        //add box fab
        addBoxFAB.setOnClickListener{
            // initializing fab dialogs

  //          when {
   //             homeBoxFragment-> AddBoxDialogFragment().show(supportFragmentManager, "addBoxDialog")
 //               homeSectionFragment.allowEnterTransitionOverlap -> AddSectionDialogFragment().show(supportFragmentManager, "addSectionDialog")
//                sectionFragment.isVisible -> AddItemDialogFragment().show(supportFragmentManager, "addItemDialog")

            when (viewModelFAB.data.value) {
                "box" -> AddBoxDialogFragment().show(supportFragmentManager, "addBoxDialog")
                "section" -> AddSectionDialogFragment().show(supportFragmentManager, "addSectionDialog")
                "item" -> AddItemDialogFragment().show(supportFragmentManager, "addItemDialog")
            }


            closeMenuFAB()
        }

        //favourite fab
        favouriteFAB.setOnClickListener {
            // on below line we are displaying a toast message
            Toast.makeText(this, "Favourites clicked..", Toast.LENGTH_SHORT).show()

            closeMenuFAB()
        }
    }

    private fun openMenuFAB() {
        // if its false we are displaying home fab
        // and settings fab by changing their
        // visibility to visible.
        addBoxFAB.show()
        favouriteFAB.show()
        homeFAB.show()
        // make the additional fab visible
        addBoxFAB.visibility = View.VISIBLE
        favouriteFAB.visibility = View.VISIBLE
        homeFAB.visibility = View.VISIBLE

        // change the acorn icon to cross icon
        menuFAB.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_close_24))

        // on below line we are changing
        // fab visible to true
        fabVisible = true
    }

    private fun closeMenuFAB() {
        // if the condition is true then we hide home, add and favourite fab
        addBoxFAB.hide()
        favouriteFAB.hide()
        homeFAB.hide()

        // change visibility of home, add and favourite fab
        addBoxFAB.visibility = View.GONE
        favouriteFAB.visibility = View.GONE
        homeFAB.visibility = View.GONE

        // changing back the cross icon to acorn icon
        menuFAB.setImageDrawable(resources.getDrawable(R.drawable.iv_acorn_1))
        //after click you make it disappear
        fabVisible = false
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

    fun takePhoto(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager) != null){
            startActivityForResult(intent,our_request_code)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==our_request_code && resultCode == RESULT_OK){
            titleLy.isGone = true
            replaceFragment(homeSearchFragment)
            val bitmap = data?.extras?.get("data") as Bitmap
            viewModelHomeSearch.setData(bitmap)
        }
    }
}