package buildtypes

abstract class BuildType(val name: String) {
    abstract val appName: String

    open val isDebuggable: Boolean = false
    open val isMinifyEnabled: Boolean = true

    open val applicationIdSuffix: String = ".$name"
    open val versionNameSuffix: String = "--$name"

    open val isLoggingEnabled: Boolean
        get() = isDebuggable
}
