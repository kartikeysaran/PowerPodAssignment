package ks.assignment.powerpod.api

import ks.assignment.powerpod.model.QRCode
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getQRCodeList(): ArrayList<QRCode>? {
        return apiService.getQRCodeData()
    }

    suspend fun postQRData(qrCode: QRCode): QRCode? {
        return apiService.postQRCodeData(qrCode)
    }

}