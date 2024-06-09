package hr.tvz.android.fragmentivukovic

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.firebase.messaging.FirebaseMessaging

class ListActivity : AppCompatActivity(), ListFragment.OnItemSelectedListener {
    private var twoPane = false
    lateinit var carDao: CarDao

    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name2"
        )
            .allowMainThreadQueries()
            .build()
        carDao = db.carDao()

        setContentView(R.layout.activity_rec_view)


        if (findViewById<FrameLayout>(R.id.detailFragment) != null) {
            twoPane = true
        }
        else {
            twoPane = false
            if (supportFragmentManager.findFragmentById(R.id.listFragment) == null) {
                var itemListFragment = ListFragment()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.listFragment, itemListFragment)
                    .commit()
            }
        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }
            val token = task.result
            Log.w("Main activity token :", token)
            println("Token: $token")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("channel_id", "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT)
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)
        }

    }

    override fun onItemSelected(car: CarObject) {
        val fragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("carObject", car)
            }
        }
        if (twoPane) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailFragment, fragment)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.listFragment, fragment)
                .commit()
        }
    }
    fun provideCarDao(): CarDao {
        return carDao
    }
}