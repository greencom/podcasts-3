package buildtypes

abstract class BuildType(val name: String) {
    abstract val appName: String

    open val isDebuggable = false
    open val isMinifyEnabled = true

    val applicationIdSuffix = ".$name"
    val versionNameSuffix = "--$name"
}
