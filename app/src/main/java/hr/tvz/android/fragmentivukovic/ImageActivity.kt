package hr.tvz.android.fragmentivukovic

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.fragmentivukovic.databinding.ImageActivityBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ImageActivityBinding

    private var carObject: CarObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ImageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        carObject = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.extras!!.getParcelable("carObject", CarObject::class.java)
        }
        else{
            intent.extras!!.getParcelable("carObject")
        }

        if (carObject != null) {
            carObject!!.image?.let { binding.carImage.setImageResource(it) }
        }
    }
}