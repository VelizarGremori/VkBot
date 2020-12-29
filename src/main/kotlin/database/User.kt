package database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
    val peerId = integer("PeerId").index()
    val nextAction = varchar("NextAction", 50)
    val nextActionParameter = varchar("NextActionParameter", 50)
    val fullName = varchar("FullName", 50)
    val random = integer("Random")
}

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)
    var peerId by Users.peerId
    var nextAction by Users.nextAction
    var nextActionParameter by Users.nextActionParameter
    var fullName by Users.fullName
    var random by Users.random

}