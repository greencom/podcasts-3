package buildtype

val appBuildTypes = listOf(Debug, Release)

object Debug : BuildType("debug") {
    override val appName = "@string/app_name_debug"
    override val isDebuggable = true
    override val isMinifyEnabled = false
}

object Release : BuildType("release") {
    override val appName = "@string/app_name_release"
    override val isDebuggable = false
    override val isMinifyEnabled = true
}
