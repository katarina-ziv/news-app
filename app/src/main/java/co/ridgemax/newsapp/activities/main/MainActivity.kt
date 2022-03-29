package co.ridgemax.newsapp.activities.main

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.databinding.ActivityMainBinding
import co.ridgemax.newsapp.modules.debug.components.info.InfoDialogFragment
import co.ridgemax.newsapp.utils.listeners.OnUserInteractionListener


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnUserInteractionListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        observeViewModel()

    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.user.collectLatest {
                if (it == null) {
                    navController.navigate(R.id.loginFragment)
                }
            }
        }
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.shared_nav_graph)
        navGraph.setStartDestination(if (viewModel.isUserLoggedIn()) R.id.homeFragment else R.id.loginFragment)

        navController.graph = navGraph
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

    override fun logout() {
        super.logout()
    }


}