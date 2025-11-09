package ro.pun.cs.systems.eim.practicaltest01

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01SecondaryActivity : AppCompatActivity() {
    private lateinit var total: TextView
    private lateinit var ok: Button
    private lateinit var cancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_secondary)


        total = findViewById(R.id.total)
        ok = findViewById(R.id.ok)
        cancel = findViewById(R.id.cancel)

        val totalClicks = intent.getIntExtra("totalClicks", 0)
        total.text = totalClicks.toString()

        ok.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        cancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }


    }
}