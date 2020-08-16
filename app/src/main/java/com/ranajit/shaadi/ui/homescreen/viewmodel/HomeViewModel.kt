package com.ranajit.shaadi.ui.homescreen.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ranajit.shaadi.base.BaseViewModel
import com.ranajit.shaadi.data.MainRepository
import com.ranajit.shaadi.model.Matches
import com.ranajit.shaadi.model.MatchesModel
import com.ranajit.shaadi.utility.Resource
import com.ranajit.shaadi.utility.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, private val mainRepository: MainRepository) :
    BaseViewModel(application) {
    private val TAG = HomeViewModel::class.java.canonicalName

    fun getMatches() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val matchesModel = mainRepository.getMatches(10)
            if (!matchesModel.results.isNullOrEmpty()) {
                prepareDataForDB(matchesModel.results!!).also { arylstMatchesModel ->
                    mainRepository.storeMatches(arylstMatchesModel)
                    emit(Resource.success(true))
                }
            } else {
                emit(Resource.error(null, "Empty list fetched"))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(null, e.message ?: "Error Occured"))
        }
    }

    var matchesList = ArrayList<MatchesModel>()

    private fun prepareDataForDB(arylstMatches: ArrayList<Matches>): ArrayList<MatchesModel> {
        val arylstMatchesModel = ArrayList<MatchesModel>()
        for (match in arylstMatches) {
            var matchesModel = MatchesModel(email = match.email ?: "")
            matchesModel.gender = match.gender ?: ""
            matchesModel.title = match.name?.title ?: ""
            matchesModel.firstName = match.name?.first ?: ""
            matchesModel.lastName = match.name?.last ?: ""
            matchesModel.streetNumber = (match.location?.street?.number ?: 0).toString()
            matchesModel.streetName = match.location?.street?.name ?: ""
            matchesModel.city = match.location?.city ?: ""
            matchesModel.state = match.location?.state ?: ""
            matchesModel.country = match.location?.country ?: ""
            matchesModel.postCode = match.location?.postcode ?: ""
            matchesModel.latitude = match.location?.coordinates?.latitude ?: 0.0
            matchesModel.longitude = match.location?.coordinates?.longitude ?: 0.0
            matchesModel.timeZone = match.location?.timezone?.offset ?: ""
            matchesModel.timeZoneDesc = match.location?.timezone?.description ?: ""
            matchesModel.userName = match.login?.userName ?: ""
            matchesModel.dobDate = match.dob?.date ?: ""
            matchesModel.dobAge = match.dob?.age ?: 0
            matchesModel.regDate = match.registered?.date ?: ""
            matchesModel.regAge = match.registered?.age ?: 0
            matchesModel.phone = match.phone ?: ""
            matchesModel.cell = match.cell ?: ""
            matchesModel.idName = match.id?.name ?: ""
            matchesModel.idValue = match.id?.value ?: ""
            matchesModel.pictureLarge = match.picture?.large ?: ""
            matchesModel.pictureMedium = match.picture?.medium ?: ""
            matchesModel.picturethumbnail = match.picture?.thumbnail ?: ""
            matchesModel.nat = match.nat ?: ""
            matchesModel.dateAdded = Utility.getCurrentTimeStamp()
            arylstMatchesModel.add(matchesModel)
        }
        return arylstMatchesModel
    }

    fun getMatchesFromDB() =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            try {
                matchesList.addAll(
                    ArrayList(
                        mainRepository.geALLtMatches() ?: emptyList()
                    )
                )
                Log.d(TAG, "setupObserver:: $matchesList")
                emit(Resource.success(matchesList))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.error(null, e.message ?: "Error Occured"))
            }

        }

    fun updateMatch(matchModel: MatchesModel) {
        viewModelScope.launch {
            mainRepository.updateMatch(matchModel)
        }
    }

}