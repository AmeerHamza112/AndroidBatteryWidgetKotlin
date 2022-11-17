package com.brown.widgets

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.brown.widgets.databinding.HomeBinding
import com.brown.widgets.helpers.NotifyHelper


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment(), View.OnClickListener {
	private var binding: HomeBinding? = null
	private val TAG = HomeFragment::class.simpleName
	private lateinit var makeToast: (String, Boolean) -> Unit
	private lateinit var manager: IManager

	override fun onCreateView(
			inflater: LayoutInflater, container: ViewGroup?,
			savedInstanceState: Bundle?
	): View? {


		// Inflate the layout for this fragment

		binding = HomeBinding.inflate(inflater, container, false)

		binding!!.ivShare.setOnClickListener(this)



		makeToast = NotifyHelper.toastGenerator(requireContext())
		manager = context as IManager

		// Get initial status
		updateView(manager.sendMonitorAction(ManagerAction.MONITORING_CHANGE))

		binding!!.ivTransparent.setOnClickListener(this)
		binding!!.ivWhite.setOnClickListener(this)
		binding!!.ivBlack.setOnClickListener(this)
		binding!!.ivGrey.setOnClickListener(this)
		binding!!.ivYellow.setOnClickListener(this)
		binding!!.ivRed.setOnClickListener(this)
		binding!!.ivOrange.setOnClickListener(this)

		return  binding!!.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val toggleButton = view.findViewById<Button>(R.id.ToggleServiceButton)
		toggleButton.setOnClickListener {
			makeToast("Toggling monitoring", true)
			updateView(manager.sendMonitorAction(ManagerAction.TOGGLE_MONITORING))
		}
	}

	private fun updateView(status: ManagerStatus = ManagerStatus.UNKNOWN) {
		if (view == null) return
		makeToast("Updating HomeFragment view.", true)

		val toggleButton = requireView().findViewById<Button>(R.id.ToggleServiceButton)
		when (status) {
			ManagerStatus.MONITORING -> {
				toggleButton.text = resources.getText(R.string.stop_widget_service_text)
			}
			ManagerStatus.STOPPED -> {
				toggleButton.text = resources.getText(R.string.start_widget_service_text)
			}
			ManagerStatus.UNKNOWN -> {
				toggleButton.text = getString(R.string.toggle_widget_service)
			}
		}
	}

	override fun onClick(v: View?) {
		var id=v!!.id
		if(id==R.id.iv_share){

			val intent = Intent(Intent.ACTION_SEND)
			intent.putExtra(Intent.EXTRA_TEXT, "Hi i am using Battery Widget App . It is a cool battery widget app . Download App from play store "+
				"https://play.google.com/store/apps/details?id=" + requireContext().packageName)
			intent.type = "text/plain"
			startActivity(Intent.createChooser(intent, "Share Via"))

		} else if(id==R.id.iv_transparent){
			binding!!.ivBackground.background =resources.getDrawable(R.drawable.bg_rounded_transparent)

		} else if(id==R.id.iv_white){
			binding!!.ivBackground.background =resources.getDrawable(R.drawable.bg_rounded_white)
		} else if(id==R.id.iv_black){
			binding!!.ivBackground.background =resources.getDrawable(R.drawable.bg_rounded_black)

		} else if(id==R.id.iv_red){
			binding!!.ivBackground.background =resources.getDrawable(R.drawable.bg_rounded_red)

		} else if(id==R.id.iv_yellow){
			binding!!.ivBackground.background =resources.getDrawable(R.drawable.bg_rounded_yellow)

		} else if(id==R.id.iv_orange){

			binding!!.ivBackground.background =resources.getDrawable(R.drawable.bg_rounded_orange)
		} else if(id==R.id.iv_grey){
			binding!!.ivBackground.background =resources.getDrawable(R.drawable.bg_rounded_grey)

		}
	}
}