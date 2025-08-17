package site.remlit.blueb.townyElections.model

import java.sql.ResultSet

data class Vote(
    val id: String,
    val player: String,
    val election: String,
    val ranked: List<String>,
) {
    companion object {
        fun fromRs(rs: ResultSet): Vote? {
            while (rs.next()) {
                return Vote(
                    rs.getString("id"),
                    rs.getString("player"),
                    rs.getString("election"),
                    rs.getString("ranked").split(","),
                )
            }
            return null
        }
    }
}
