package com.nasa.nasaapod.ui

import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elifox.legocatalog.binding.bindImageFromUrl
import com.nasa.nasaapod.ApodApp
import com.nasa.nasaapod.R
import com.nasa.nasaapod.databinding.FragmentApodBinding
import com.nasa.nasaapod.db.NasaApodEntity
import com.nasa.nasaapod.utils.Utils
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class NasaApodFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentApodBinding

    companion object {
        fun newInstance() = NasaApodFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_apod, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ApodApp.instance.getApodAppComponent().inject(this)
        val nasaapodViewModel: NasaApodViewModel by viewModels { viewModelFactory }
        nasaapodViewModel.nasaApodPicture.observe(viewLifecycleOwner, Observer {
            bindView(it)
        })
    }

    private fun bindView(nasaApodEntityList: List<NasaApodEntity>) {
        if (!nasaApodEntityList.isEmpty()) {
            val nasaApodEntity = nasaApodEntityList.get(0);
            Timber.e("Date is " + nasaApodEntity.date)
            nasaApodEntity.apply {
                bindImageFromUrl(binding.apodImageview, nasaApodEntity.url)
                binding.titleTv.text = nasaApodEntity.title
                binding.explainTv.text = nasaApodEntity.explanation
            }

            if (nasaApodEntity.date?.equals(Utils.getCurrentDate(Calendar.getInstance())) != true && !Utils.isNetworkAvailable(
                    binding.titleTv.context
                )
            ) {
                Toast.makeText(
                    binding.titleTv.context,
                    getString(R.string.last_cache_image),
                    Toast.LENGTH_LONG
                ).show()
            }
        } else if (!Utils.isNetworkAvailable(binding.titleTv.context)) {
            Toast.makeText(
                binding.titleTv.context,
                getString(R.string.no_internet),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
