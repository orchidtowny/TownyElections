package site.remlit.blueb.townyElections.model

import java.sql.ResultSet
import java.time.LocalDateTime

data class Election(
    val id: String,
    val type: ElectionTargetType,
    val length: Int,
    val startedAt: Int,
) {
    fun getCandidates() {}

    companion object {
        fun fromRs(rs: ResultSet): Election? {
            while (rs.next()) {
                return Election(
                    rs.getString("id"),
                    ElectionTargetType.valueOf(rs.getString("type")),
                    rs.getInt("length"),
                    rs.getInt("started_at")
                )
            }
            return null
        }
    }
}