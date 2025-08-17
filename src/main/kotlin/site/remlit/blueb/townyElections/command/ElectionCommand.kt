package site.remlit.blueb.townyElections.command

import com.palmergames.bukkit.towny.TownyCommandAddonAPI
import com.palmergames.bukkit.towny.TownyMessaging
import com.palmergames.bukkit.towny.`object`.Nation
import com.palmergames.bukkit.towny.`object`.Town
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import site.remlit.blueb.townyElections.TownyElections
import site.remlit.blueb.townyElections.model.ElectionTargetType
import site.remlit.blueb.townyElections.model.ElectionType
import site.remlit.blueb.townyElections.service.CalendarService
import site.remlit.blueb.townyElections.service.ElectionService
import site.remlit.blueb.townyElections.service.SettingsService

class ElectionCommand(
    val type: TownyCommandAddonAPI.CommandType
) : CommandExecutor {

    val towny = TownyElections.towny

    val completions = listOf(
        listOf("start")
    )

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String?>
    ): Boolean {
        val fromPlayer = sender is Player
        val player = if (fromPlayer) sender else null
        val resident = if (fromPlayer) towny.getResident(player) else null

        val forNation = type == TownyCommandAddonAPI.CommandType.NATION
        val target = if (resident != null) (if (forNation) resident.nation!! else resident.town!!) else null
        val settings = if (target != null) SettingsService.get(target.uuid) else null

        if (target == null) return false
        if (forNation) target as Nation else target as Town

        when (args.getOrNull(0)) {
            "start" -> {
                if (!fromPlayer) return false
                val leader = if (forNation) resident!!.nation.king else resident!!.town.mayor

                if (settings!!.type == ElectionType.PETITION) {
                    println("todo:petition")
                } else {
                    if (leader == resident) {
                        ElectionService.start(
                            target.uuid,
                            if (forNation) ElectionTargetType.NATION else ElectionTargetType.TOWN,
                            args.getOrNull(1)?.toInt()
                        )
                    } else {
                        TownyMessaging.sendErrorMsg(sender, "You need to be a leader to start an election here.")
                    }
                }
            }
            null -> {
                val election = ElectionService.get(target.uuid)

                if (election == null) {
                    TownyMessaging.sendErrorMsg(sender, "No election currently occurring.")
                } else {
                    val currentDay = CalendarService.getDays()
                    val timeRemaining = (election.startedAt + election.length) - currentDay
                    val candidates = election.getCandidates()

                    val votes = election.getVotes()
                    val requiredVotes = 1
                    val voteRequirementPercentage = votes.size / target.residents.size * 100

                    TownyMessaging.sendMessage(sender, "&6.o0o._______.[ &eTowny Elections &6].______.o0o.")
                    TownyMessaging.sendMessage(sender, "&eAn election is underway in ${target.name}!")
                    TownyMessaging.sendMessage(sender, "&6${timeRemaining} &edays remain. &6${votes.size} votes counted.")
                    if (votes.size < requiredVotes) TownyMessaging.sendMessage(sender, "&6$voteRequirementPercentage% &eof the required votes have been counted. ${requiredVotes - votes.size} needed.")
                    TownyMessaging.sendMessage(sender, "&eCandidates (${candidates.size}): &7${candidates.joinToString(", ")}")
                }
            }
            else -> {

            }
        }

        return true
    }

}