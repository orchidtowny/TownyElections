package site.remlit.blueb.townyElections

import com.palmergames.bukkit.towny.TownyCommandAddonAPI
import site.remlit.blueb.townyElections.command.*

class Commands {
    companion object {
        fun register() {
            /*
            * /t election
            * /n election
            *
            * election start
            * election cancel
            * election enroll
            * election vote
            *
            * --
            *
            * set election
            *   interval
            *   type
            *       leader
            *       resident
            *       interval
            *   length
            * */
            TownyCommandAddonAPI.addSubCommand(TownyCommandAddonAPI.CommandType.TOWN, "election", ElectionCommand(TownyCommandAddonAPI.CommandType.TOWN))
            TownyCommandAddonAPI.addSubCommand(TownyCommandAddonAPI.CommandType.NATION, "election", ElectionCommand(TownyCommandAddonAPI.CommandType.NATION))
        }
    }
}