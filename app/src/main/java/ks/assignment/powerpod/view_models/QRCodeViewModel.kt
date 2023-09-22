package ks.assignment.powerpod.view_models

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ks.assignment.powerpod.api.UseCase
import ks.assignment.powerpod.model.QRCode
import javax.inject.Inject

@ExperimentalGetImage
@HiltViewModel
class QRCodeViewModel @Inject constructor(private val scanner: BarcodeScanner, private val useCase: UseCase):ViewModel() {

    private val _qrCodeData: MutableLiveData<String> = MutableLiveData()
    val qrData: LiveData<String>
        get() = _qrCodeData

    fun postDataToAPI() {
        viewModelScope.launch {
            var qrCode = QRCode(true, 1, qrData.value!!)
            useCase.postQRCodeData(qrCode).onEach {
                // We get the data here and we can use it
            }.collect()
        }
    }


    fun processImageProxy(imageProxy: ImageProxy) {
        imageProxy.image?.let { image ->
            val inputImage =
                InputImage.fromMediaImage(
                    image,
                    imageProxy.imageInfo.rotationDegrees
                )

            scanner.process(inputImage)
                .addOnSuccessListener { barcodeList ->
                    val barcode = barcodeList.getOrNull(0)

                    // `rawValue` is the decoded value of the barcode
                    barcode?.rawValue?.let { value ->
                        _qrCodeData.value = value
                    }
                }
                .addOnFailureListener {

                }.addOnCompleteListener {
                    imageProxy.image?.close()
                    imageProxy.close()
                }
        }
    }
}