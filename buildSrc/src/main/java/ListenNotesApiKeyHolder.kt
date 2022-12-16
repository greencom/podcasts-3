import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.*

class ListenNotesApiKeyHolder private constructor(
    val key: String,
) {

    companion object {
        private const val LOCAL_PROPERTIES_FILE = "listen_notes_api.properties"
        private const val LOCAL_PROPERTIES_KEY_KEY = "key"

        private const val CI_CD_KEY_KEY = "LISTEN_NOTES_API_KEY"

        fun init(project: Project): ListenNotesApiKeyHolder {
            // Check local properties
            val localPropertiesDir = File(project.rootDir, PROPERTIES_DIR)
            val localPropertiesFile = File(localPropertiesDir, LOCAL_PROPERTIES_FILE)
            if (localPropertiesFile.exists()) {
                println("ListenNotesApiKeyHolder is created with local $LOCAL_PROPERTIES_FILE file")
                val properties = Properties()
                properties.load(FileInputStream(localPropertiesFile))
                val key = properties.getProperty(LOCAL_PROPERTIES_KEY_KEY, "")
                return ListenNotesApiKeyHolder(key)
            }

            // If there is no local properties file, check CI/CD environment
            println("ListenNotesApiKeyHolder is created with CD/CD environment")
            val key = System.getenv(CI_CD_KEY_KEY) ?: ""
            return ListenNotesApiKeyHolder(key)
        }
    }

}
