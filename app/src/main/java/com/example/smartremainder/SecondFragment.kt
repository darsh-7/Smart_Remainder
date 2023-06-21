package com.example.smartremainder

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartremainder.databinding.FragmentSecondBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentSecondBinding? = null
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MMMM d, yyyy hh:mm:ss a", Locale.US)
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.date.text = getTodaysDate()
        val dateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date: String = "${day}/${month}/${year}"
                binding.date.setText(date)
                calendar.set(year, month, day)
            }

        binding.date.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                container!!.context,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        })

        return binding.root

    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.save.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun getTodaysDate(): String? {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        var month: Int = cal.get(Calendar.MONTH)
        month = month + 1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        return "${day}/${month}/${year}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}