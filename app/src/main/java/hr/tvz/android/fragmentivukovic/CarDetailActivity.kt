package hr.tvz.android.fragmentivukovic

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        val message = intent.getStringExtra("message")
        val messageTextView = findViewById<TextView>(R.id.message_text_view)
        messageTextView.text = message
    }
}