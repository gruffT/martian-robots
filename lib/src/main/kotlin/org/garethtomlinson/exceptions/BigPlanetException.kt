package org.garethtomlinson.exceptions

class BigPlanetException(configuration: String) : Exception("""Planet coordinate greater than 50 specified: `$configuration`""")
