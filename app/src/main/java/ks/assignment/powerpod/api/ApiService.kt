package ks.assignment.powerpod.api

import ks.assignment.powerpod.model.QRCode
import ks.assignment.powerpod.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST(Constants.QR_CODE)
    suspend fun postQRCodeData(@Body qrCode: QRCode): QRCode?

//    @GET(Constants.QR_CODE)
//    suspend fun getQRCodeData(@Header("Authorization") token: String): ?

    @GET(Constants.QR_CODE)
    suspend fun getQRCodeData(): ArrayList<QRCode>?
}