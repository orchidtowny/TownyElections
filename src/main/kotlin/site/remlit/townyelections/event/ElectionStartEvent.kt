package site.remlit.townyelections.event

import site.remlit.townyelections.model.Election

class ElectionStartEvent(val election: Election) : ElectionEvent()