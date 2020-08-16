package com.ranajit.shaadi.data

import android.util.Log
import com.ranajit.shaadi.base.ShaadiDb
import com.ranajit.shaadi.data.dao.MatchesDao
import com.ranajit.shaadi.ShaadiApplication
import com.ranajit.shaadi.model.MatchesModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainRepository(private val mainNetworkDataProvider: NetworkDataProvider) :
    CoroutineScope {

    private val TAG = MainRepository::class.java.canonicalName

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var matchesDao: MatchesDao?

    init {
        val db = ShaadiDb.getDatabase(ShaadiApplication.instance)
        matchesDao = db?.matchesDao()
    }

    suspend fun getMatches(noOfResults: Int) = mainNetworkDataProvider.getMatches(noOfResults)

    suspend fun geALLtMatches(
    ): List<MatchesModel> {
        return matchesDao?.getMatchesByAll() ?: emptyList()
    }

    suspend fun storeMatches(arylstMatchesModel: ArrayList<MatchesModel>) {
        for (matchesModel in arylstMatchesModel) {
            setMatchBG(matchesModel)
        }
    }

    private suspend fun setMatchBG(matchModel: MatchesModel) {
        withContext(Dispatchers.IO) {
            val addedId = matchesDao?.setMatch(matchModel)
            Log.d(TAG, "udpateMatchBG::addedId: $addedId")
        }
    }

    suspend fun updateMatch(matchModel: MatchesModel) {
        udpateMatchBG(matchModel)
    }

    private suspend fun udpateMatchBG(matchModel: MatchesModel) {
        withContext(Dispatchers.IO) {
            val updated = matchesDao?.updateMatch(matchModel)
            Log.d(TAG, "udpateMatchBG::$updated")
        }
    }

}