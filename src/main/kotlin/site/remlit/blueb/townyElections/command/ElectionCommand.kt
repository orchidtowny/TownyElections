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

        when (args.getOrNull(0)) {
            "start" -> {
                if (!fromPlayer) return false
                if (forNation) target!! as Nation else target!! as Town
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
            else -> {

            }
        }

        return true
    }

}