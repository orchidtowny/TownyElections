package site.remlit.blueb.townyElections

import java.nio.file.Files
import java.sql.Connection
import java.sql.DriverManager
import kotlin.io.path.Path
import kotlin.io.path.exists


class Database {
    companion object {
        lateinit var connection: Connection

        fun init() {
            Files.createDirectories(Path("${TownyElections.instance.dataFolder.path}"))
            val file = Path("${TownyElections.instance.dataFolder.path}/database.db")
            if (!file.exists()) Files.createFile(file)

            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:$file")

            connection.createStatement().use { stmt ->
                stmt.execute("CREATE TABLE IF NOT EXISTS database_meta(id varchar(1) primary key default 'r', version int default 0)")

                val version: Int = stmt.executeQuery("SELECT version FROM database_meta WHERE id = 'r'").use { rs ->
                    if (!rs.isBeforeFirst) {
                        stmt.execute("INSERT INTO database_meta (id, version) VALUES ('r', 0)")
                        0
                    } else {
                        rs.next()
                        rs.getInt("version")
                    }
                }

                if (version <= 0) {
                    stmt.execute("CREATE TABLE election(id text primary key, type text, length integer, days_remaining integer, started_at integer)")
                    stmt.execute("CREATE TABLE candidate(id text primary key, election text, color text)")
                    stmt.execute("CREATE TABLE vote(id text primary key, player text, election text, ranked text)")
                    stmt.execute("CREATE TABLE settings(id text primary key, type text, interval integer, length integer)")
                    stmt.execute("CREATE TABLE calendar(id text primary key, days integer)")

                    stmt.execute("UPDATE database_meta SET version = 1 WHERE id = 'r'")
                }

                if (version <= 1) {
                    stmt.execute("CREATE TABLE petition(id text primary key, count integer)")

                    stmt.execute("UPDATE database_meta SET version = 2 WHERE id = 'r'")
                }

                if (version <= 2) {
                    stmt.execute("ALTER TABLE election DROP COLUMN days_remaining")

                    stmt.execute("UPDATE database_meta SET version = 3 WHERE id = 'r'")
                }

                if (version <= 3) {
                    stmt.execute("UPDATE database_meta SET version = 4 WHERE id = 'r'")
                }
            }
        }
    }
}