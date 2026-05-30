package site.remlit.townyelections.event

import site.remlit.townyelections.model.Petition

class ElectionPetitionFailEvent(petition: Petition) : ElectionPetitionEvent(petition)