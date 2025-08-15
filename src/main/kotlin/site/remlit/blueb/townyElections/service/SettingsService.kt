package site.remlit.blueb.townyElections.service

import site.remlit.blueb.townyElections.Database
import site.remlit.blueb.townyElections.model.Settings
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