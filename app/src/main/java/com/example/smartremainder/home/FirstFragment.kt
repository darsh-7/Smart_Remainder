package com.example.smartremainder.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.smartremainder.R
import com.example.smartremainder.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val filters = arrayOf<String>("name", "date", "time")
    private var _binding: FragmentFirstBinding? = null

/*
    private lateinit var autoCompleteView : AutoCompleteTextView
    private lateinit var arrayAdapter : ArrayAdapter<String>
*/
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
/*
        autoCompleteView = binding.autoCompleteText
        arrayAdapter = ArrayAdapter<String>(container!!.context,R.layout.list_filter,filters)
        autoCompleteView.setOnItemClickListener(AdapterView.OnItemClickListener(){

        })
*/




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}