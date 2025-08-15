package site.remlit.blueb.townyElections.model

import java.sql.ResultSet

data class Settings(
    val id: String,
    val type: ElectionType = ElectionType.LEADER,
    val interval: Int = 14,
    val length: Int = 7
) {
    companion object {
        fun fromRs(rs: ResultSet): Settings? {
            while (rs.next()) {
                return Settings(
                    rs.getString("id"),
                    ElectionType.valueOf(rs.getString("type")),
                    rs.getInt("interval"),
                    rs.getInt("length")
                )
            }
            return null
        }
    }
}
