package site.remlit.blueb.townyElections.event

import site.remlit.blueb.townyElections.model.Election
import site.remlit.blueb.townyElections.model.Vote

class ElectionVoteEvent(val election: Election, val vote: Vote) : ElectionEvent()