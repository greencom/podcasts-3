import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.*

class ListenNotesApiKeyHolder private constructor(
    val key: String,
) {

    companion object {
        private const val PROPERTIES_FILE = "listen_notes_api.properties"
        private const val PROPERTIES_KEY = "key"

        private const val CI_CD_KEY = "LISTEN_NOTES_API_KEY"

        fun init(project: Project): ListenNotesApiKeyHolder {
            // Check local properties
            val localPropertiesDir = File(project.rootDir, Keys.PROPERTIES_DIR)
            val localPropertiesFile = File(localPropertiesDir, PROPERTIES_FILE)
            if (localPropertiesFile.exists()) {
                println("Local ListenNotesApi .properties file found")
                val properties = Properties()
                properties.load(FileInputStream(localPropertiesFile))
                val key = properties.getProperty(PROPERTIES_KEY) ?: let {
                    println("Property not found for given key")
                    ""
                }
                return ListenNotesApiKeyHolder(key)
            }

            // Check CI/CD env variables if local properties not found
            println("Local ListenNotesApi .properties file not found, check env variables")
            val key = System.getenv(CI_CD_KEY) ?: let {
                println("CI/CD env variable not found for given key")
                ""
            }
            return ListenNotesApiKeyHolder(key)
        }
    }

}
