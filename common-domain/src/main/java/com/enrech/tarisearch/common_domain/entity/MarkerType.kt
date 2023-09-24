package com.enrech.tarisearch.common_domain.entity

enum class MarkerType(val value: String) {
    Studio("Studio"),
    Venue("Venue"),
    Stadium("Stadium"),
    IndoorArena("Indoor arena"),
    ReligiousBuilding("Religious building"),
    EducationalInstitution("Educational institution"),
    PressingPlant("Pressing plant"),
    Other("Other");

    companion object {
        fun getByName(name: String) = MarkerType.values().firstOrNull { it.value.equals(name, true) } ?: Other
    }
}