package org.garethtomlinson

class Mars private constructor(val width: Int, val height: Int) {
    companion object {
        fun from(configuration: String): Mars {
            val configurationElements = configurationRegex.find(configuration)
            val (widthBound, heightBound) = configurationElements!!.destructured
            val width = widthBound.toInt() + 1
            val height = heightBound.toInt() + 1
            return Mars(width, height)
        }

        private val configurationRegex = """(\d+) (\d+)""".toRegex()
    }
}
