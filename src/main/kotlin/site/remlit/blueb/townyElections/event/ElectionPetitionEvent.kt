package site.remlit.blueb.townyElections.event

import site.remlit.blueb.townyElections.model.Petition

open class ElectionPetitionEvent(val petition: Petition) : ElectionEvent()