package site.remlit.blueb.townyElections.model

import java.sql.ResultSet

data class Candidate(
    val id: String,
    val election: String,
    val color: String
) {
    companion object {
        fun fromRs(rs: ResultSet): Candidate? {
            while (rs.next()) {
                return Candidate(
                    rs.getString("id"),
                    rs.getString("election"),
                    rs.getString("color")
                )
            }
            return null
        }
    }
}