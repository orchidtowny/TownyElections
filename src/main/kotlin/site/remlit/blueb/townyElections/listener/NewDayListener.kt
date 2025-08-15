package site.remlit.blueb.townyElections.listener

import com.palmergames.bukkit.towny.event.NewDayEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import site.remlit.blueb.townyElections.service.CalendarService

class NewDayListener : Listener {

    @EventHandler
    fun onNewDay(event: NewDayEvent) {
        CalendarService.tick()
    }

}