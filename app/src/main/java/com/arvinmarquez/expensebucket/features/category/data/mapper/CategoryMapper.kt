package com.arvinmarquez.expensebucket.features.category.data.mapper

import com.arvinmarquez.expensebucket.features.category.data.entities.CategoryEntity
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.core.utils.EntityMapper

class CategoryMapper : EntityMapper<CategoryEntity, Category> {

    override fun mapFromEntity(entity: CategoryEntity): Category {
        return Category(
            id = entity.id,
            description = entity.description,
            isExpense = entity.isExpense,
            isActive = entity.isActive
        )
    }

    override fun mapToEntity(domain: Category): CategoryEntity {
        return CategoryEntity(
            id = domain.id,
            description = domain.description,
            isExpense = domain.isExpense,
            isActive = domain.isActive
        )
    }

    override fun mapFromEntityList(initial: List<CategoryEntity>): List<Category> {
       return initial.map { mapFromEntity(it) }
    }

    override fun mapToEntityList(initial: List<Category>): List<CategoryEntity> {
        return initial.map { mapToEntity(it) }
    }

}