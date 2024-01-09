package codeflies.com.meunavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {
    private var bottomNavigation: MeowBottomNavigation? = null
    private val fragment1 = Fragment1()
    private val fragment2 = Fragment2()
    private val fragment3 = Fragment3()
    private val fragment4 = Fragment4()

    private val fragmentManager: FragmentManager = supportFragmentManager
    private var activeFragment: Fragment = fragment1 // Set Fragment1 as the default active fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)

        // Initialize fragments
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.fragmentContainer, fragment4, "fragment4").hide(fragment4)
            fragmentTransaction.add(R.id.fragmentContainer, fragment3, "fragment3").hide(fragment3)
            fragmentTransaction.add(R.id.fragmentContainer, fragment2, "fragment2").hide(fragment2)
            fragmentTransaction.add(R.id.fragmentContainer, fragment1, "fragment1").commit()
        } else {
            activeFragment = fragmentManager.findFragmentByTag(savedInstanceState.getString("activeFragmentTag"))!!
        }

        // Set up bottom navigation
        bottomNavigation?.show(0, true) // Show the first item by default
        bottomNavigation?.add(MeowBottomNavigation.Model(0, R.drawable.baseline_add_home_24))
        bottomNavigation?.add(MeowBottomNavigation.Model(1, R.drawable.baseline_build_circle_24))
        bottomNavigation?.add(MeowBottomNavigation.Model(2, R.drawable.outline_app_registration_24))
        bottomNavigation?.add(MeowBottomNavigation.Model(3, R.drawable.baseline_build_circle_24))

        bottomNavigation?.setOnClickMenuListener {
            when (it.id) {
                0 -> replaceFragment(fragment1, "fragment1")
                1 -> replaceFragment(fragment2, "fragment2")
                2 -> replaceFragment(fragment3, "fragment3")
                3 -> replaceFragment(fragment4, "fragment4")
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        if (fragment != activeFragment) {
            supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
            activeFragment = fragment
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("activeFragmentTag", activeFragment.tag)
    }
}
