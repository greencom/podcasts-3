import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.*

class SigningConfig private constructor(
    val name: String,
    val keystorePath: String,
    val keystorePassword: String,
    val keyAlias: String,
    val keyPassword: String,
) {

    companion object {
        private const val DEFAULT_SIGNING_CONFIG_NAME = "defaultSigningConfig"

        private const val LOCAL_PROPERTIES_FILE = "keystore.properties"
        private const val LOCAL_PROPERTIES_KEYSTORE_PATH_KEY = "keystorePath"
        private const val LOCAL_PROPERTIES_KEYSTORE_PASSWORD_KEY = "keystorePassword"
        private const val LOCAL_PROPERTIES_KEY_ALIAS_KEY = "keyAlias"
        private const val LOCAL_PROPERTIES_KEY_PASSWORD_KEY = "keyPassword"

        private const val CI_CD_KEYSTORE_PATH_KEY = "CI_CD_KEYSTORE_PATH"
        private const val CI_CD_KEYSTORE_PASSWORD_KEY = "CI_CD_KEYSTORE_PASSWORD"
        private const val CI_CD_KEY_ALIAS_KEY = "CI_CD_KEY_ALIAS"
        private const val CI_CD_KEY_PASSWORD_KEY = "CI_CD_KEY_PASSWORD"

        fun getDefault(project: Project): SigningConfig {
            // Check local properties
            val localPropertiesDir = File(project.rootDir, PROPERTIES_DIR)
            val localPropertiesFile = File(localPropertiesDir, LOCAL_PROPERTIES_FILE)
            if (localPropertiesFile.exists()) {
                println("SigningConfig is created with local keystore.properties file")
                val properties = Properties()
                properties.load(FileInputStream(localPropertiesFile))
                return createDefaultFromLocalProperties(properties)
            }

            // If there is no local properties file, check CI/CD environment
            println("SigningConfig is created with CI/CD environment")
            return createDefaultFromSystemEnvironment()
        }

        fun createDefaultFromLocalProperties(properties: Properties): SigningConfig {
            val keystorePath =
                properties.getProperty(LOCAL_PROPERTIES_KEYSTORE_PATH_KEY, "")
            val keystorePassword =
                properties.getProperty(LOCAL_PROPERTIES_KEYSTORE_PASSWORD_KEY, "")
            val keyAlias =
                properties.getProperty(LOCAL_PROPERTIES_KEY_ALIAS_KEY, "")
            val keyPassword =
                properties.getProperty(LOCAL_PROPERTIES_KEY_PASSWORD_KEY, "")
            return SigningConfig(
                name = DEFAULT_SIGNING_CONFIG_NAME,
                keystorePath = keystorePath,
                keystorePassword = keystorePassword,
                keyAlias = keyAlias,
                keyPassword = keyPassword,
            )
        }

        fun createDefaultFromSystemEnvironment(): SigningConfig {
            val keystorePath = System.getenv(CI_CD_KEYSTORE_PATH_KEY) ?: ""
            val keystorePassword = System.getenv(CI_CD_KEYSTORE_PASSWORD_KEY) ?: ""
            val keyAlias = System.getenv(CI_CD_KEY_ALIAS_KEY) ?: ""
            val keyPassword = System.getenv(CI_CD_KEY_PASSWORD_KEY) ?: ""
            return SigningConfig(
                name = DEFAULT_SIGNING_CONFIG_NAME,
                keystorePath = keystorePath,
                keystorePassword = keystorePassword,
                keyAlias = keyAlias,
                keyPassword = keyPassword,
            )
        }
    }

}
