package hr.tvz.android.fragmentivukovic

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import hr.tvz.android.fragmentivukovic.databinding.DetailActivityBinding
import android.Manifest
import android.view.animation.AnimationUtils

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: DetailActivityBinding
    private var carObject: CarObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carObject = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.extras!!.getParcelable("carObject", CarObject::class.java)
        }
        else{
            intent.extras!!.getParcelable("carObject")
        }

        if (carObject != null) {
            binding.carName.text = carObject!!.model
            binding.carDescription.text = carObject!!.description
            carObject!!.image?.let { binding.carImage.setImageResource(it) }
        }
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.carName.startAnimation(fadeInAnimation)
    }
    fun openImage(view: View) {
        val intent = Intent(view.context, ImageActivity::class.java).apply {
            putExtra("carObject", carObject)
        }
        view.context.startActivity(intent)
    }

    fun callPhoneNumber(view: android.view.View) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val intent: Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:093555555"))
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "@string/phone_activity_failed_message",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "@string/phone_permission_denied_message",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}