package hr.tvz.android.fragmentivukovic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    private var listener: OnItemSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.my_recycler_view)
        val carDao = (activity as ListActivity).provideCarDao()
        val cars = carDao.getAll()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AdapterRecyclerView(cars).apply {
            setOnItemClickListener { car ->
                listener?.onItemSelected(car)
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnItemSelectedListener {
        fun onItemSelected(car: CarObject)
    }
}