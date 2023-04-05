package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentCityListBinding
import com.example.weatherapp.ui.adapters.CitiesAdapter
import com.example.weatherapp.util.Utils

class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!
    private lateinit var citiesAdapter: CitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!Utils.checkForInternet(requireContext())) {
            requireView().findNavController().navigate(
                CityListFragmentDirections.actionCityListFragmentToWeatherInfoFragment()
            )
        }
        initCitiesAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCitiesAdapter() {
        citiesAdapter = CitiesAdapter { city ->
            requireView().findNavController().navigate(
                CityListFragmentDirections.actionCityListFragmentToWeatherInfoFragment(city)
            )
        }
        binding.rvCities.adapter = citiesAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvCities.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )
        binding.rvCities.layoutManager = layoutManager
        citiesAdapter.setItems(Utils.getCitiesList(requireContext()))
    }

}