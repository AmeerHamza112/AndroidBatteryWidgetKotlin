package com.brown.widgets.HomescreenWidgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import android.widget.RemoteViews
import com.brown.widgets.R
import com.brown.widgets.helpers.BatteryInfo

/**
 * Implementation of Battery Widget functionality.
 */
class BatteryWidget : AppWidgetProvider() {
	private val TAG = BatteryWidget::class.simpleName

	override fun onUpdate(
		context: Context,
		appWidgetManager: AppWidgetManager,
		appWidgetIds: IntArray
	) {
		// get battery info
		val battery = BatteryInfo(context.registerReceiver(null,
			IntentFilter(Intent.ACTION_BATTERY_CHANGED)
		))

		// update all widgets with battery info
		for (widgetId in appWidgetIds) {
			updateAppWidget(context, appWidgetManager, widgetId, battery)
		}
	}

	companion object {
		fun updateAppWidget(
			context: Context,
			appWidgetManager: AppWidgetManager,
			appWidgetId: Int,
			battery: BatteryInfo
		) {
			// Construct the RemoteViews object
			val views = RemoteViews(context.packageName, R.layout.battery_widget)

			var level=battery.level
			var isCharging=battery.isCharging


			val remoteViews = RemoteViews(context.packageName, R.layout.battery_widget)
			remoteViews.setImageViewResource(R.id.battery_view, R.drawable.battery)
			remoteViews.setViewVisibility(
				R.id.percent100,
				if (level <= 100 && level > 90) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent90,
				if (level <= 90 && level > 80) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent80,
				if (level <= 80 && level > 70) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent70,
				if (level <= 70 && level > 60) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent60,
				if (level <= 60 && level > 50) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent50,
				if (level <= 50 && level > 40) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent40,
				if (level <= 40 && level > 30) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent30,
				if (level <= 30 && level > 20) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent20,
				if (level <= 20 && level > 10) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.percent10,
				if (level <= 10 && level > 0) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setViewVisibility(
				R.id.charge_view,
				if (isCharging) View.VISIBLE else View.INVISIBLE
			)
			remoteViews.setTextViewText(R.id.batterytext, "$level%")
//			val activityIntent = Intent(context, BatteryWidget::class.java)
//			val pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0)
//			remoteViews.setOnClickPendingIntent(R.id.widget_view, pendingIntent)


			val componentName =  ComponentName(context, BatteryWidget::class.java)
			val appWidgetManager = AppWidgetManager.getInstance(context)
			appWidgetManager.updateAppWidget(componentName, remoteViews)



//			// Launch battery settings on touch
//			val intentToLaunchBatterySettings = Intent(Intent.ACTION_MAIN).apply {
//				component = ComponentName("com.android.settings", "com.android.settings.Settings\$PowerUsageSummaryActivity")
//			}
//			val pendingIntent = PendingIntent.getActivity(context, appWidgetId, intentToLaunchBatterySettings, 0)
//			views.setOnClickPendingIntent(R.id.Root, pendingIntent)
//
//			// Instruct the widget manager to update the widget
//			appWidgetManager.updateAppWidget(appWidgetId, views)


		}
	}


}
