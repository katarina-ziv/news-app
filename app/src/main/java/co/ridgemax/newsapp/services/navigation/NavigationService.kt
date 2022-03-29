package co.ridgemax.newsapp.services.navigation

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.*
import co.ridgemax.newsapp.R

interface NavigationService {

    fun navigate(navDir: NavDirections, navOps: NavOptions? = null) {
        val view = (this as Fragment).requireView()
        val navController = Navigation.findNavController(view)
        navController.navigate(navDir, navOps)
    }

    fun navigateToNestedGraph(navDir: NavDirections, destination: Int, navOps: NavOptions? = null){
        val view = (this as Fragment).requireView()
        val navController = Navigation.findNavController(view)
        val graph = navController.graph.findNode(R.id.shared_nav_graph)
        if (graph is NavGraph){
            graph.setStartDestination(destination)
            navController.navigate(navDir, navOps)
        }
    }

    fun navigateUsingDeepLink(uri: String){
        val view = (this as Fragment).requireView()
        val request = NavDeepLinkRequest.Builder.fromUri(uri.toUri()).build()
        val navController = Navigation.findNavController(view)
        navController.navigate(request)
    }

    fun back() {}

    fun switchBetweenActivities() {}

}