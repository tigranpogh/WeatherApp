package com.example.weatherapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.CurrentWeatherResponse
import com.example.weatherapp.data.ResponseState
import com.example.weatherapp.database.MainModel
import com.example.weatherapp.databinding.FragmentWeatherInfoBinding
import com.example.weatherapp.ui.adapters.WeatherAdapter
import com.example.weatherapp.ui.viewmodel.WeatherInfoViewModel
import com.example.weatherapp.util.Utils
import com.example.weatherapp.util.WeatherInfoConstants.WEATHER_IMAGE_URL
import kotlinx.android.synthetic.main.fragment_weather_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherInfoFragment : Fragment() {

    private var _binding: FragmentWeatherInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherInfoViewModel by viewModel()

    private lateinit var cityName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        if (Utils.checkForInternet(requireContext())) {
            arguments?.let {
                cityName = WeatherInfoFragmentArgs.fromBundle(it).cityName
            }
            initCurrentWeatherObservers()
            viewModel.getCurrentWeather(cityName)
            viewModel.getForecastWeather(cityName)
        } else {
            initSavedNewsObservers()
        }
    }

    private fun showLoading() {
        progressBar.visibility = VISIBLE
    }

    private fun showError() {
        binding.progressBar.visibility = GONE
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.dialogTitle)
        builder.setMessage(R.string.dialogMessage)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes"){ dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun showData(response: CurrentWeatherResponse) {
        binding.apply {
            progressBar.visibility = GONE
            cityNameTxt.text = cityName
            temperatureTxt.text = getString(R.string.temperature, response.main?.temp)
            humidityTxt.text = getString(R.string.humidity, response.main?.humidity.toString(), "%")
            descriptionTxt.text =
                "${response.weather?.get(0)?.description}"

            imageId.load(
                String.format(
                    WEATHER_IMAGE_URL,
                    response.weather?.get(0)?.icon.toString()
                ),
            )
        }
    }

    private fun initWeatherAdapter(daysList: List<String>, tempList: List<String>) {
        val weatherAdapter = WeatherAdapter()
        binding.rvForecast.adapter = weatherAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvForecast.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )
        weatherAdapter.setItems(daysList, tempList)
    }

    private fun initCurrentWeatherObservers() {
        viewModel.getCurrentWeatherMutableLiveData().observe(viewLifecycleOwner) {
            when(it) {
                is ResponseState.Loading -> showLoading()
                is ResponseState.Success -> {
                    showData(it.data!!)
                    initForecastWeatherObservers()
                }
                is ResponseState.Error -> showError()
            }
        }
    }

    private fun initForecastWeatherObservers() {
        viewModel.getForecastWeatherMutableLiveData().observe(viewLifecycleOwner) {
            initWeatherAdapter(it.days!!, it.temp!!)
            viewModel.clearData()
            for (i in 0..3) {
                val weather = MainModel(it.days[i], it.temp[i], cityName)
                viewModel.saveWeather(weather)
            }
        }
    }

    private fun initSavedNewsObservers() {
        viewModel.getSavedWeather().observe(viewLifecycleOwner) {
            val daysList = emptyList<String>().toMutableList()
            val tempList = emptyList<String>().toMutableList()
            for (i in it.indices) {
                daysList.add(it[i].day)
                tempList.add(it[i].temp)
            }
            cityName = it[0].city
            binding.cityNameTxt.text = cityName
            initWeatherAdapter(daysList, tempList)
        }
    }
}
