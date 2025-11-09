package ro.pun.cs.systems.eim.practicaltest01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01 : AppCompatActivity() {

    private lateinit var navigate: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var text1: TextView
    private lateinit var text2: TextView

    companion object {
        private const val SECUNDARY_ACTIVITY_REQUEST_CODE = 1
        private const val PRAG = 5
    }

    fun startServiceIfNeedeed() {
        val val1 = text1.text.toString().toInt()
        val val2 = text2.text.toString().toInt()
        val suma = val1 + val2

        if (suma > PRAG) {
            val intent = Intent(this, MyService::class.java)
            intent.putExtra("number1", val1)
            intent.putExtra("number2", val2)
            startService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }

    private var funcListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.button2 -> {
                // incrementez val din text1
                var val1 = text1.text.toString().toInt()
                val1++
                text1.text = val1.toString()
                startServiceIfNeedeed()
            }

            R.id.button3 -> {
                // incrementez val din text2
                var val2 = text2.text.toString().toInt()
                val2++
                text2.text = val2.toString()
                startServiceIfNeedeed()
            }

            R.id.button1 -> {
                val total = text1.text.toString().toInt() + text2.text.toString().toInt()
                val intent = Intent(this, PracticalTest01SecondaryActivity::class.java)
                intent.putExtra("totalClicks", total)
                startActivityForResult(intent, SECUNDARY_ACTIVITY_REQUEST_CODE)
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("text1_value", text1.text.toString())
        outState.putString("text2_value", text2.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        text1.text = savedInstanceState.getString("text1_value", "0")
        text2.text = savedInstanceState.getString("text2_value", "0")
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01)

        navigate = findViewById(R.id.button1)
        button1 = findViewById(R.id.button2)
        button2 = findViewById(R.id.button3)
        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)

        button1.setOnClickListener(funcListener)
        button2.setOnClickListener(funcListener)
        navigate.setOnClickListener(funcListener)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SECUNDARY_ACTIVITY_REQUEST_CODE) {
            val message = when (resultCode) {
                Activity.RESULT_OK -> "S-a apăsat OK"
                Activity.RESULT_CANCELED -> "S-a apăsat Cancel"
                else -> "Rezultat necunoscut"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}