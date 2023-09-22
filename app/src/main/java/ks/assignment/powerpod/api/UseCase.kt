package ks.assignment.powerpod.api


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ks.assignment.powerpod.model.QRCode
import javax.inject.Inject

class UseCase @Inject constructor(private val apiRepository: ApiRepository) {

    suspend fun getQRDataList(): Flow<ResultData<ArrayList<QRCode>>> {
        return flow {
            emit(ResultData.Loading)

            val qrCodeList = apiRepository.getQRCodeList()

            val resultData = if (qrCodeList == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(qrCodeList)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }

    suspend fun postQRCodeData(qrCode: QRCode): Flow<ResultData<QRCode>> {
        return flow {
            emit(ResultData.Loading)

            val qrcode = apiRepository.postQRData(qrCode)

            val resultData = if (qrcode == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(qrcode)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }

}