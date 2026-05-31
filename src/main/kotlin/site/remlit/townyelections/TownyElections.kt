package site.remlit.townyelections

import com.palmergames.bukkit.towny.TownyAPI
import org.bukkit.plugin.java.JavaPlugin

class TownyElections : JavaPlugin() {

    override fun onEnable() {
        instance = this
        towny = TownyAPI.getInstance()
    }

    override fun onDisable() {
    }


    companion object {
        lateinit var instance: TownyElections
        lateinit var towny: TownyAPI
    }
}
