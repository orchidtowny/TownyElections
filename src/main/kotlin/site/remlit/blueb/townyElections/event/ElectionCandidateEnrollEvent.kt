package site.remlit.blueb.townyElections.event

import site.remlit.blueb.townyElections.model.Candidate
import site.remlit.blueb.townyElections.model.Election

class ElectionCandidateEnrollEvent(val election: Election, val candidate: Candidate) : ElectionEvent()