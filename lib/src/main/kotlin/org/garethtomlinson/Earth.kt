package org.garethtomlinson

class Earth {
    companion object {
        fun mars(missionDetails: String): Mars {
            val marsConfiguration = missionDetails.trim().split("\n")[0]
            return Mars.from(marsConfiguration)
        }
    }
}
