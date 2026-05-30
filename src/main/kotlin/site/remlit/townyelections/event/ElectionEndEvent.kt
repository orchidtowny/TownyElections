package site.remlit.townyelections.event

import site.remlit.townyelections.model.Election

class ElectionEndEvent(val election: Election) : ElectionEvent()