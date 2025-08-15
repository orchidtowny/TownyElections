package site.remlit.blueb.townyElections

import org.bukkit.Bukkit
import site.remlit.blueb.townyElections.listener.NewDayListener

class EventHandler {
    companion object {
        fun register() {
            Bukkit.getPluginManager().registerEvents(NewDayListener(), TownyElections.instance)
        }
    }
}