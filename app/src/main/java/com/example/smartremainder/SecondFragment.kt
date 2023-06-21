package com.example.smartremainder

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartremainder.databinding.FragmentSecondBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

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
/*
        val dateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date: String = "${day}/${month}/${year}"
                binding.date.setText(date)
                calendar.set(year, month, day)
                Log.i("Formatting", "${calendar.timeInMillis}")

            }
*/
/*
        val OnTimeSetListener =
            TimePickerDialog.OnTimeSetListener { timePicker, year, month, day ->
                var month = month
                month = month + 1
                val date: String = "${day}/${month}/${year}"
                binding.date.setText(date)
                calendar.set(year, month, day)
                Log.i("Formatting", "${calendar.timeInMillis}")

            }
*/

        binding.date.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                container!!.context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        })

        binding.time.setOnClickListener(View.OnClickListener {
            TimePickerDialog(
                container!!.context,
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            ).show()
        })


        return binding.root

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val month = month + 1
        val date: String = "${day}/${month}/${year}"
        binding.date.setText(date)
        calendar.set(year, month, day)
        Log.i("Formatting", "${calendar.timeInMillis}")
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
        val time: String = "${hourOfDay}:${minute}"
        binding.time.setText(time)
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