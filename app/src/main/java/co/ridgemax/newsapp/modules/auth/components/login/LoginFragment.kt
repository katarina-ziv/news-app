package co.ridgemax.newsapp.modules.auth.components.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.databinding.FragmentLoginBinding
import co.ridgemax.newsapp.services.navigation.NavigationService
import co.ridgemax.newsapp.utils.enums.UiStates.*
import co.ridgemax.newsapp.utils.extensions.getStringOrNull

@AndroidEntryPoint
class LoginFragment : Fragment(), NavigationService {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instantiateUi()
        observeViewModel()
    }

    private fun instantiateUi() {
        with(binding) {
            loginButton.setOnClickListener {
                viewModel.login(
                    loginEmail.text.toString().trim(),
                    loginPassword.text.toString().trim()
                )
//                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.eventFlow.collectLatest {
                when (it) {
                    NO_INTERNET_CONNECTION -> {
                        showErrorDialog(getString(R.string.alert_loc_login_ctx_empty_password))
                    }
                    USER_NOT_FOUND -> {
                        showErrorDialog(getString(R.string.alert_loc_login_ctx_user_not_found))
                    }
                    LOGIN -> {
                        navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    }
                    else -> return@collectLatest
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.loginErrorFlow.collectLatest {
                binding.loginEmail.error = getStringOrNull(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.passwordErrorFlow.collectLatest {
                binding.loginPassword.error = getStringOrNull(it)
            }
        }
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext()).setTitle(null)
            .setMessage(message)
            .setPositiveButton(
                R.string.alert_btn_positive_loc_login
            ) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}