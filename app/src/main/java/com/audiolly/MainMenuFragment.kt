package com.audiolly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.main_menu_nav_host.*

class MainMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_nav_host, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHost =
            childFragmentManager.findFragmentById(R.id.main_menu_nav_host) as NavHostFragment
        NavigationUI.setupWithNavController(main_menu_bottom_nav, navHost.navController)
    }
}