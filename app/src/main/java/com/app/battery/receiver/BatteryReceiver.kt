package com.app.battery.receiver

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.app.battery.widget.BatteryWidget
import com.app.battery.helpers.BatteryInfo
import com.app.battery.helpers.DataReceiverHelper

class BatteryReceiver : BroadcastReceiver() {
	private val TAG = BatteryReceiver::class.simpleName

	override fun onReceive(context: Context, intent: Intent) {
		val battery = BatteryInfo(intent)

		DataReceiverHelper.updateWidget<BatteryWidget>(context) {
		manager: AppWidgetManager, id: Int ->
			BatteryWidget.updateAppWidget(context, manager, id, battery)
		}

		BatteryWidget.UpdateBackground(context)
	}
}