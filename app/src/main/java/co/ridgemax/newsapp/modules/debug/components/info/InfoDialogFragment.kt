package co.ridgemax.newsapp.modules.debug.components.info

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import co.ridgemax.newsapp.BuildConfig
import co.ridgemax.newsapp.R
import co.ridgemax.newsapp.databinding.FragmentInfoBinding
import co.ridgemax.newsapp.utils.listeners.OnUserInteractionListener

class InfoDialogFragment : DialogFragment() {


    private lateinit var listener: OnUserInteractionListener
    private var binding: FragmentInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window!!.setGravity(Gravity.CENTER_HORIZONTAL)
        val p = dialog!!.window!!.attributes
        p.width = ViewGroup.LayoutParams.MATCH_PARENT
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        dialog!!.window?.attributes = p
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnUserInteractionListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        instantiateUi()
    }

    private fun instantiateUi() {

        val production = BuildConfig.FLAVOR == "production"
        val versionName = "${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})"

        binding?.apply {
            versionCircleView.background.colorFilter = PorterDuffColorFilter(
                if (production) Color.GREEN else Color.YELLOW,
                PorterDuff.Mode.SRC_ATOP
            )
            appNameTextView.text = getString(R.string.app_name)
            versionNameTextView.text = versionName
            environmentTextView.text =
                getString(if (production) R.string.t1_loc_general_ctx_production_environment else R.string.t1_loc_general_ctx_staging_environment)
            logoutButton.setOnClickListener {
                dismiss()
                listener.logout()
            }
        }
    }


    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}