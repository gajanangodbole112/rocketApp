package com.gajanan.rocketapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gajanan.rocketapp.repository.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RocketViewModel
@Inject constructor(
    private val repo : RocketRepository
) : ViewModel() {


    val getAllRockets = repo.getAllRockets().asLiveData()
    fun getRocketDetail(id : String) = repo.getRocketDetail(id).asLiveData()


}