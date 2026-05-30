package site.remlit.townyelections.event

import site.remlit.townyelections.model.Petition

class ElectionPetitionStartEvent(petition: Petition) : ElectionPetitionEvent(petition)