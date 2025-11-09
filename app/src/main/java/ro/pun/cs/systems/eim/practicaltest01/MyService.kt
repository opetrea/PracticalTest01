package ro.pun.cs.systems.eim.practicaltest01

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread
import kotlin.math.sqrt

class MyService : Service() {
    private var running = true

    companion object {
        const val ACTION_1 = "ro.pun.cs.systems.eim.practicaltest01.action1"
        const val ACTION_2 = "ro.pun.cs.systems.eim.practicaltest01.action2"
        const val ACTION_3 = "ro.pun.cs.systems.eim.practicaltest01.action3"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val number1 = intent?.getIntExtra("number1", 0) ?: 0
        val number2 = intent?.getIntExtra("number2", 0) ?: 0

        thread {
            while (running) {
                val meanAritm = (number1 + number2) / 2.0
                val meanGeom = sqrt(number1 * number2.toDouble())
                val now = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

                val actions = listOf(ACTION_1, ACTION_2, ACTION_3)
                val randomAction = actions.random()

                val broadcastIntent = Intent()
                broadcastIntent.action = randomAction
                broadcastIntent.putExtra("now", now)
                broadcastIntent.putExtra("meanAritm", meanAritm)
                broadcastIntent.putExtra("meanGeom", meanGeom)

                sendBroadcast(broadcastIntent)

                Log.d("SERVICE", "Broadcast $randomAction trimis la $now")

                Thread.sleep(10000)
            }
        }

        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        running = false
    }

    override fun onBind(intent: Intent?): IBinder? = null
}