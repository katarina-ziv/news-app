package co.ridgemax.newsapp.modules.article.components.singleArticle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.activities.main.MainViewModel

class ArticleFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainViewModel)
    }
}