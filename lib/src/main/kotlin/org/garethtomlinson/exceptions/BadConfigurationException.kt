package org.garethtomlinson.exceptions

class BadConfigurationException(objectName: String, configuration: String) : Exception(
    """A bad configuration has been provided for $objectName: `$configuration`""",
)
