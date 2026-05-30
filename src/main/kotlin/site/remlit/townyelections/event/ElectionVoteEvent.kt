package site.remlit.townyelections.event

import site.remlit.townyelections.model.Election
import site.remlit.townyelections.model.Vote

class ElectionVoteEvent(val election: Election, val vote: Vote) : ElectionEvent()