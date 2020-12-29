package database

import database.Users.index
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Subjects : IntIdTable() {
    val name = varchar("Name", 50).index()
}

class Subject(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Subject>(Subjects)
    val name by Subjects.name
}