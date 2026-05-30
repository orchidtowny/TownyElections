package site.remlit.townyelections

import org.bukkit.Bukkit
import site.remlit.townyelections.listener.NewDayListener

class EventHandler {
    companion object {
        fun register() {
            Bukkit.getPluginManager().registerEvents(NewDayListener(), TownyElections.instance)
        }
    }
}