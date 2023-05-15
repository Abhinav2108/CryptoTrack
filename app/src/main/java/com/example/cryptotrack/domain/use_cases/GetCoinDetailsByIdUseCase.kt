package com.example.cryptotrack.domain.use_cases

import com.example.cryptotrack.domain.model.Coin
import com.example.cryptotrack.domain.model.CoinDetails
import com.example.cryptotrack.domain.repository.CoinRepository
import com.example.cryptotrack.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsByIdUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(id:String): Flow<ResponseState<CoinDetails>> = flow{
        try {
            emit(ResponseState.Loading())
            val coinDetails = repository.getCoinsById(id).toCoinDetails()

            emit(ResponseState.Success(coinDetails))
        }
        catch (e: HttpException){
            emit(ResponseState.Error(message = "${e.localizedMessage}"))
        }
        catch (e: IOException){
            emit(ResponseState.Error(message = "Input Output Exception Occurred"))
        }
    }
}