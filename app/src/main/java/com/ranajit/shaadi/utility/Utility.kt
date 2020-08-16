package com.ranajit.shaadi.utility

import java.text.SimpleDateFormat
import java.util.*

interface IAcceptanceGroup {
    fun getAccepted() = arrayOf(AcceptanceGroup.ACCEPTED.acceptance)
    fun getDeclined() = arrayOf(AcceptanceGroup.DECLINED.acceptance)
    fun getNew() = arrayOf(AcceptanceGroup.NEW.acceptance)
    fun getAll() = arrayOf(
        AcceptanceGroup.ACCEPTED.acceptance,
        AcceptanceGroup.DECLINED.acceptance,
        AcceptanceGroup.NEW.acceptance
    )
}

enum class AcceptanceGroup(val acceptance: Int) : IAcceptanceGroup {
    ACCEPTED(1),
    DECLINED(0),
    NEW(-1),
    ALL(2)

}

interface IGenderGroup {
    fun getMale(): Array<String>
    fun getFemale(): Array<String>
    fun getAll(): Array<String>
}

enum class GenderGroup : IGenderGroup {
    MALE {
        override fun getMale() = arrayOf("male")
        
        override fun getFemale() = emptyArray<String>()

        override fun getAll() = emptyArray<String>()
    },
    FEMALE {
        override fun getMale() = emptyArray<String>()

        override fun getFemale() = arrayOf("female")

        override fun getAll() = emptyArray<String>()
    },
    ALL {
        override fun getMale() = emptyArray<String>()

        override fun getFemale() = emptyArray<String>()

        override fun getAll() = arrayOf("male", "female")
    }
}

interface IAgeGroup {
    fun getFloorAge(): Int
    fun getCeilingAge(): Int
}

enum class AgeGroup : IAgeGroup {
    GROUP1 {
        override fun getFloorAge() = 18
        override fun getCeilingAge() = 22
    },
    GROUP2 {
        override fun getFloorAge() = 23
        override fun getCeilingAge() = 27
    },
    GROUP3 {
        override fun getFloorAge() = 28
        override fun getCeilingAge() = 32
    },
    GROUP4 {
        override fun getFloorAge() = 33
        override fun getCeilingAge() = 37
    },
    GROUP5 {
        override fun getFloorAge() = 38
        override fun getCeilingAge() = 42
    },
    GROUP6 {
        override fun getFloorAge() = 43
        override fun getCeilingAge() = 47
    },
    GROUP7 {
        override fun getFloorAge() = 48
        override fun getCeilingAge() = 52
    },
    GROUP8 {
        override fun getFloorAge() = 53
        override fun getCeilingAge() = 57
    },
    GROUP9 {
        override fun getFloorAge() = 0
        override fun getCeilingAge() = 0
    }
}

class Utility {

    companion object {
        fun getCurrentTimeStamp(): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZZZZZ")
            return sdf.format(Calendar.getInstance().time)
        }

    }

}