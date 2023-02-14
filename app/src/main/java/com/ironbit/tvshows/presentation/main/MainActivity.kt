package com.ironbit.tvshows.presentation.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.ironbit.tvshows.R
import com.ironbit.tvshows.databinding.ActivityMainBinding

// Activity that shows the main screen
class MainActivity : AppCompatActivity() {

    // ViewBinding associated to the current screen
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if(resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        setContentView(binding.root)
        initNavigation()
    }

    // Inits the current screen navigation components
    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container_view) as NavHostFragment
        navHostFragment.navController
    }

}