package com.greencom.android.podcasts3.util.navigation

object RouteUtils {

    fun generateRouteSchema(
        routeBase: String,
        argNames: Array<String> = emptyArray(),
        optionalArgNames: Array<String> = emptyArray(),
    ): String = buildString {
        append(routeBase)

        if (argNames.isNotEmpty()) {
            if (!this.endsWith(MandatoryArgumentsSeparator)) append(MandatoryArgumentsSeparator)
            argNames.forEachIndexed { index, arg ->
                append("{$arg}")
                if (index < argNames.lastIndex) append(MandatoryArgumentsSeparator)
            }
        }

        if (optionalArgNames.isNotEmpty()) {
            append(MandatoryAndOptionalArgumentsSeparator)
            optionalArgNames.forEachIndexed { index, arg ->
                append("$arg={$arg}")
                if (index < optionalArgNames.lastIndex) append(OptionalArgumentsSeparator)
            }
        }
    }

    fun generateRoute(
        routeBase: String,
        args: Array<Any> = emptyArray(),
        optionalArgs: Array<OptionalNavArg> = emptyArray(),
    ): String = buildString {
        append(routeBase)

        if (args.isNotEmpty()) {
            if (!this.endsWith(MandatoryArgumentsSeparator)) append(MandatoryArgumentsSeparator)
            args.forEachIndexed { index, arg ->
                append("$arg")
                if (index < args.lastIndex) append(MandatoryArgumentsSeparator)
            }
        }

        if (optionalArgs.isNotEmpty()) {
            append(MandatoryAndOptionalArgumentsSeparator)
            optionalArgs.forEachIndexed { index, arg ->
                val (name, value) = arg
                append("$name=$value")
                if (index < optionalArgs.lastIndex) append(OptionalArgumentsSeparator)
            }
        }
    }

    private const val MandatoryArgumentsSeparator = "/"
    private const val MandatoryAndOptionalArgumentsSeparator = "?"
    private const val OptionalArgumentsSeparator = "&"

}
