package co.ridgemax.newsapp.activities.main

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.databinding.ActivityMainBinding
import co.ridgemax.newsapp.modules.debug.components.info.InfoDialogFragment
import co.ridgemax.newsapp.modules.repository.NewsRepository
import co.ridgemax.newsapp.services.persistence.room.ArticleDatabase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))

        setupNavigation()
        observeViewModel()

    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {

            navController.navigate(R.id.breakingNewsFragment)
        }
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.shared_nav_graph)
//        navGraph.setStartDestination(R.id.breakingNewsFragment)
        navController.graph = navGraph

        val bottomNavigationView = binding.bottomNavigationView
       // val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_POINTER_DOWN -> {
                val count = event.pointerCount
                if (count == 3) {
                    val dialog = InfoDialogFragment()
                    dialog.show(supportFragmentManager, null)
                }
                return true
            }
        }
        return false
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (onTouchEvent(ev)) {
            true
        } else super.dispatchTouchEvent(ev)
    }

//    override fun logout() {
//        super.logout()
//    }


}