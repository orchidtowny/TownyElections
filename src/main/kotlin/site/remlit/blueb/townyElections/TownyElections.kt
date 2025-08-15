package site.remlit.blueb.townyElections

import com.palmergames.bukkit.towny.TownyAPI
import org.bukkit.plugin.java.JavaPlugin
import site.remlit.blueb.townyElections.service.CalendarService

class TownyElections : JavaPlugin() {

    override fun onEnable() {
        instance = this
        towny = TownyAPI.getInstance()

        Database.init()
        CalendarService.init()

        EventHandler.register()
        Commands.register()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        lateinit var instance: TownyElections
        lateinit var towny: TownyAPI
    }
}
