package database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

object SQLiteDB {
    val db by lazy {
        Database.connect("jdbc:sqlite:data.db3", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel =
            Connection.TRANSACTION_SERIALIZABLE
    }
}