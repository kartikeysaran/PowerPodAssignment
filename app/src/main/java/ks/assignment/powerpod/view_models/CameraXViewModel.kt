package ks.assignment.powerpod.view_models

import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.barcode.BarcodeScanner
import javax.inject.Inject

class CameraXViewModel @Inject constructor(private val barcodeScanner: BarcodeScanner) : ViewModel() {

}