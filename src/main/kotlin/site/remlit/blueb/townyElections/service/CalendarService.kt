package site.remlit.blueb.townyElections.service

import site.remlit.blueb.townyElections.Database

class CalendarService {
    companion object {
        fun init() {
            Database.connection.createStatement().use { statement ->
                val exists = statement.executeQuery("SELECT COUNT(*) FROM calendar").use { rs ->
                    rs.next(); rs.getInt(1)
                }
                if (exists < 1) statement.execute("INSERT INTO calendar (id, days) VALUES ('r', 0)")
            }
        }

        fun getDays(): Int {
            try {
                Database.connection.createStatement().use { statement ->
                    statement.executeQuery("SELECT days FROM calendar").use { rs ->
                        while (rs.next()) {
                            return rs.getInt("days")
                        }
                    }
                }
            } catch (e: Exception) {}
            return 0
        }

        fun tick() {
            val old = getDays()
            Database.connection.prepareStatement("UPDATE calendar SET days = ? WHERE id = ?").use { statement ->
                statement.setInt(1, old + 1)
                statement.setString(2, "r")
                statement.executeUpdate()
            }
        }
    }
}