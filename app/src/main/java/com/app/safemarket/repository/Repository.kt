package com.app.safemarket.repository

import android.util.Log
import com.app.safemarket.api.RetrofitInstance
import com.app.safemarket.model.Marker
import com.app.safemarket.util.Constants
import retrofit2.Response

class Repository {

    suspend fun  getMarker():Marker{
        Log.d("response",Constants.URL)
        return RetrofitInstance.api.getMarker()
    }

    suspend fun updateMarker(marker: Marker):Response<Marker>{
        return RetrofitInstance.api.updateMarker(marker)
    }
}