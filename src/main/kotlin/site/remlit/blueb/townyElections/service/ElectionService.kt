package site.remlit.blueb.townyElections.service

import com.palmergames.adventure.text.Component
import com.palmergames.adventure.text.minimessage.MiniMessage
import com.palmergames.bukkit.towny.TownyMessaging
import com.palmergames.bukkit.towny.`object`.Resident
import site.remlit.blueb.townyElections.Database
import site.remlit.blueb.townyElections.TownyElections
import site.remlit.blueb.townyElections.event.ElectionCandidateEnrollEvent
import site.remlit.blueb.townyElections.event.ElectionStartEvent
import site.remlit.blueb.townyElections.model.Candidate
import site.remlit.blueb.townyElections.model.Color
import site.remlit.blueb.townyElections.model.Election
import site.remlit.blueb.townyElections.model.ElectionTargetType
import site.remlit.blueb.townyElections.model.Vote
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

		fun enrollCandidate(target: UUID, resident: Resident, color: Color) {
			val election = get(target) ?: throw Exception("No election found.")
			if (election.getCandidates().any { it.id == resident.uuid.toString() }) throw Exception("Candidate already enrolled.")

			broadcastElectionUpdate(target, "${color.toLegacyCode()}● ${resident.name}&e is now running in the election")

			ElectionCandidateEnrollEvent(
				election,
				Candidate(resident.uuid.toString(), target.toString(), color.toString())
			)

			Database.connection.prepareStatement("INSERT INTO candidate (id, election, color) VALUES (?, ?, ?)").use { ps ->
				ps.setString(1, resident.uuid.toString())
				ps.setString(2, target.toString())
				ps.setString(3, color.toString())
				ps.execute()
			}
		}

		fun getCandidates(id: UUID): List<Candidate> {
			val list = mutableListOf<Candidate>()
			Database.connection.prepareStatement("SELECT * FROM candidate WHERE election = ?").use { ps ->
				ps.setString(1, id.toString())
				ps.executeQuery().use { rs ->
					val value = Candidate.fromRs(rs)
					if (value != null) list.add(value)
				}
			}
			return list
		}

		fun getVotes(id: UUID): List<Vote> {
			val list = mutableListOf<Vote>()
			Database.connection.prepareStatement("SELECT * FROM vote WHERE election = ?").use { ps ->
				ps.setString(1, id.toString())
				ps.executeQuery().use { rs ->
					val value = Vote.fromRs(rs)
					if (value != null) list.add(value)
				}
			}
			return list
		}

		fun checkElections() {
			val now = CalendarService.getDays()
		}

		fun broadcastElectionUpdate(target: UUID, message: String) {
			val election = get(target) ?: throw Exception("No election found.")

			when (election.type) {
				ElectionTargetType.TOWN -> TownyMessaging.sendPrefixedTownMessage(towny.getTown(target)!!, message)
				ElectionTargetType.NATION -> TownyMessaging.sendPrefixedNationMessage(towny.getNation(target)!!, message)
			}
		}
	}
}