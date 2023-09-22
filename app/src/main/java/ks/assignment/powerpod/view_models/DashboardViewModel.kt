package ks.assignment.powerpod.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ks.assignment.powerpod.api.ResultData
import ks.assignment.powerpod.api.UseCase
import ks.assignment.powerpod.model.QRCode
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    private val _qrCodeListData: MutableLiveData<ResultData<ArrayList<QRCode>>> = MutableLiveData()
    val qrData: LiveData<ResultData<ArrayList<QRCode>>>
        get() = _qrCodeListData

    fun getQRCodeList() {
        viewModelScope.launch {
            useCase.getQRDataList().onEach {
                _qrCodeListData.postValue(it)
            }.collect()
        }
    }
}