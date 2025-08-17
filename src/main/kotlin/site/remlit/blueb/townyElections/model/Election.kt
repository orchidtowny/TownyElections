package site.remlit.blueb.townyElections.model

import site.remlit.blueb.townyElections.service.ElectionService
import java.sql.ResultSet
import java.util.UUID

data class Election(
    val id: String,
    val type: ElectionTargetType,
    val length: Int,
    val startedAt: Int,
) {
    fun getCandidates() = ElectionService.getCandidates(UUID.fromString(id))
    fun getVotes() = ElectionService.getVotes(UUID.fromString(id))

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