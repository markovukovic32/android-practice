package hr.tvz.android.fragmentivukovic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRecyclerView(private val data: List<CarObject>) : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>() {
    private var onItemClickListener: ((CarObject) -> Unit)? = null

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val carName: TextView = view.findViewById(R.id.carName)
        val carYear: TextView = view.findViewById(R.id.carYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carObject = data[position]
        holder.carName.text = carObject.model
        holder.carYear.text = carObject.year
        holder.view.setOnClickListener {
            onItemClickListener?.invoke(carObject)
        }
    }

    override fun getItemCount() = data.size

    fun setOnItemClickListener(listener: (CarObject) -> Unit) {
        onItemClickListener = listener
    }
}