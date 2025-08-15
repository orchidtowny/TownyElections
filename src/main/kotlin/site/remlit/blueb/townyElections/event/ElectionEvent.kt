package site.remlit.blueb.townyElections.event

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

open class ElectionEvent() : Event(), Cancellable {
    private var cancelled: Boolean = false
    override fun isCancelled(): Boolean = cancelled
    override fun setCancelled(cancel: Boolean) {
        cancelled = true
    }

    override fun getHandlers(): HandlerList {
        return HANDLER_LIST
    }

    companion object {
        @JvmStatic
        @Suppress("Unused")
        fun getHandlerList(): HandlerList = HANDLER_LIST
        val HANDLER_LIST: HandlerList = HandlerList()
    }
}