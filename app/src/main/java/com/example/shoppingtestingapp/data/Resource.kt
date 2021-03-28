
import com.example.shoppingtestingapp.data.ResponseResult
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

object Resource {


    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResponseResult<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                ResponseResult.Success(myResp.body()!!)
            } else {


                ResponseResult.Error(
                    myResp.code().toString() ?: "Something goes wrong"
                )
            }

        } catch (e: Throwable) {
            when(e){

                is HttpException-> ResponseResult.NetworkError(e.code().toString())

                is SocketTimeoutException-> ResponseResult.NetworkError(e.message ?: "Internet error runs")
                else -> {
                    ResponseResult.Error(e.message!!)

                }
            }

        }
    }
}