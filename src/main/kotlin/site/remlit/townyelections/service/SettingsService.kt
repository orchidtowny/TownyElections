package site.remlit.townyelections.service

import site.remlit.townyelections.Database
import site.remlit.townyelections.model.Settings
import java.util.UUID

class SettingsService {
    companion object {
        fun get(id: UUID): Settings {
            Database.connection.prepareStatement("SELECT * FROM settings WHERE id = ?").use { ps ->
                ps.setString(1, id.toString())
                ps.executeQuery().use { rs ->
                    return Settings.fromRs(rs) ?: Settings(id.toString())
                }
            }
        }
    }
}