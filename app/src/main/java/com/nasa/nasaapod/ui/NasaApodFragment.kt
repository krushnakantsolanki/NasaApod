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
import com.google.android.material.snackbar.Snackbar
import com.nasa.nasaapod.ApodApp
import com.nasa.nasaapod.R
import com.nasa.nasaapod.databinding.FragmentApodBinding
import com.nasa.nasaapod.db.NasaApodEntity
import com.nasa.nasaapod.utils.Utils
import timber.log.Timber
import java.util.*
import com.nasa.nasaapod.data.Result

import javax.inject.Inject

class NasaApodFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = NasaApodFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ApodApp.instance.getApodAppComponent().inject(this)
        val nasaapodViewModel: NasaApodViewModel by viewModels { viewModelFactory }

        val binding = DataBindingUtil.inflate<FragmentApodBinding>(
            inflater, R.layout.fragment_apod, container, false
        )
        subscribeUi(binding,nasaapodViewModel)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        nasaapodViewModel.nasaApodPicture.observe(viewLifecycleOwner, Observer {
//            bindView(it)
//        })
//    }

    private fun subscribeUi(binding: FragmentApodBinding,nasaapodViewModel:NasaApodViewModel) {
        nasaapodViewModel.nasaApod.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { bindView(binding, it) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.titleTv, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
    private fun bindView(binding: FragmentApodBinding,nasaApodEntityList: List<NasaApodEntity>) {
        if (!nasaApodEntityList.isEmpty()) {
            Timber.e("List is %s",nasaApodEntityList)
            Timber.e("Size is %d",nasaApodEntityList.size)
            val nasaApodEntity = nasaApodEntityList.get(0);
            Timber.i("Date is %s" , Utils.convertDateFormat(nasaApodEntity.date))
            nasaApodEntity.apply {
                bindImageFromUrl(binding.apodImageview, nasaApodEntity.url)
                binding.titleTv.text = nasaApodEntity.title
                binding.explainTv.text = nasaApodEntity.explanation
            }

            Timber.i("Current Date is %s" , Utils.getCurrentDate(Calendar.getInstance()))
            Timber.e("Date compare %s",Utils.convertDateFormat(nasaApodEntity.date)==(Utils.getCurrentDate(Calendar.getInstance())))
            if (!Utils.convertStringToDate(Utils.convertDateFormat(nasaApodEntity.date)).equals(Utils.convertStringToDate(Utils.getCurrentDate(Calendar.getInstance()))) && !Utils.isNetworkAvailable(
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
