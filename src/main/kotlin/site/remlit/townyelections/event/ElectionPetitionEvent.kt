package site.remlit.townyelections.event

import site.remlit.townyelections.model.Petition

open class ElectionPetitionEvent(val petition: Petition) : ElectionEvent()