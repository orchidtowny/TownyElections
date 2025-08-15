package site.remlit.blueb.townyElections.event

import site.remlit.blueb.townyElections.model.Election

class ElectionStartEvent(val election: Election) : ElectionEvent()