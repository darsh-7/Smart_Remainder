package com.example.smartremainder.addRemainder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.smartremainder.MainActivity
import com.example.smartremainder.R
import com.example.smartremainder.databinding.FragmentSecondBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var viewModel : addViewModel

    private val calendar = Calendar.getInstance()

    private val formatter = SimpleDateFormat("MM d, yyyy hh:mm:ss a", Locale.US)
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(addViewModel::class.java)
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.togBar()
        binding.date.text = viewModel.getTodaysDate()
        binding.save.setOnClickListener(View.OnClickListener {
            viewModel.calendar = calendar
            viewModel.name= binding.name.text.toString()
            viewModel.discription = binding.Discription.text.toString()

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        })
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
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity?)?.togBar()
        _binding = null
    }
}