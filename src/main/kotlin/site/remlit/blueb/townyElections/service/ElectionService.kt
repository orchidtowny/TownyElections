package site.remlit.blueb.townyElections.service

import com.palmergames.adventure.translation.Translatable
import com.palmergames.bukkit.towny.TownyMessaging
import site.remlit.blueb.townyElections.Database
import site.remlit.blueb.townyElections.TownyElections
import site.remlit.blueb.townyElections.event.ElectionStartEvent
import site.remlit.blueb.townyElections.model.Election
import site.remlit.blueb.townyElections.model.ElectionTargetType
import site.remlit.blueb.townyElections.model.Settings
import java.util.UUID
import kotlin.use

class ElectionService {
    companion object {

        val towny = TownyElections.towny

        fun start(target: UUID, type: ElectionTargetType, length: Int?) {
            val settings = SettingsService.get(target)
            val length = length ?: settings.interval

            val existing = get(target)
            if (existing != null) throw Exception("An election is already taking place.")

            Database.connection.prepareStatement("INSERT INTO election (id, type, length, started_at) VALUES (?, ?, ?, ?)").use { ps ->
                ps.setString(1, target.toString())
                ps.setString(2, type.name)
                ps.setInt(3, length)
                ps.setInt(4, CalendarService.getDays())
                ps.execute()
            }

            ElectionStartEvent(get(target)!!)

            when (type) {
                ElectionTargetType.TOWN -> TownyMessaging.sendPrefixedTownMessage(towny.getTown(target)!!, "An election has begun! It will end in $length days.")
                ElectionTargetType.NATION -> TownyMessaging.sendPrefixedNationMessage(towny.getNation(target)!!, "An election has begun! It will end in $length days.")
            }
        }

        fun get(id: UUID): Election? {
            Database.connection.prepareStatement("SELECT * FROM election WHERE id = ?").use { ps ->
                ps.setString(1, id.toString())
                ps.executeQuery().use { rs ->
                    return Election.fromRs(rs)
                }
            }
        }

        fun checkElections() {
            val now = CalendarService.getDays()
        }
    }
}