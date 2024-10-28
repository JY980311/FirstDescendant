package com.jjy9811.thefirstdescendantlink.data.user.external

data class ExternalComponent(
    val external_component_additional_stat: List<ExternalComponentAdditionalStat>,
    val external_component_id: Int,
    val external_component_level: Int,
    val external_component_slot_id: Int
)