package buildtypes

val appBuildTypes = listOf(Debug, Qa, Release)

object Debug : BuildType("debug") {
    override val appName = "@string/app_name_debug"
    override val isDebuggable = true
    override val isMinifyEnabled = false
}

object Qa : BuildType("qa") {
    override val appName = "@string/app_name_qa"
    override val isDebuggable = false
    override val isMinifyEnabled = true
    override val isLoggingEnabled = true
}

object Release : BuildType("release") {
    override val appName = "@string/app_name_release"
    override val isDebuggable = false
    override val isMinifyEnabled = true
    override val applicationIdSuffix = ""
    override val versionNameSuffix = ""
}
