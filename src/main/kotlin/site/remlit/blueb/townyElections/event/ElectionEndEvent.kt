package site.remlit.blueb.townyElections.event

import site.remlit.blueb.townyElections.model.Election

class ElectionEndEvent(val election: Election) : ElectionEvent()