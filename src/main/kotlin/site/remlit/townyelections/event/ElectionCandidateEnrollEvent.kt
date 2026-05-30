package site.remlit.townyelections.event

import site.remlit.townyelections.model.Candidate
import site.remlit.townyelections.model.Election

class ElectionCandidateEnrollEvent(val election: Election, val candidate: Candidate) : ElectionEvent()