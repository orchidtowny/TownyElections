package site.remlit.blueb.townyElections.model

data class Vote(
    val id: String,
    val player: String,
    val election: String,
    val ranked: List<String>,
) {

}
