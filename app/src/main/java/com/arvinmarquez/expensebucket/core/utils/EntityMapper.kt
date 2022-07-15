package com.arvinmarquez.expensebucket.core.utils

interface EntityMapper<Entity, Domain> {
    fun mapFromEntity(entity: Entity): Domain
    fun mapToEntity(domain: Domain): Entity
    fun mapFromEntityList(initial: List<Entity>): List<Domain>
    fun mapToEntityList(initial: List<Domain>): List<Entity>
}