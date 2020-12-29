package commands

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Keyboard
import com.vk.api.sdk.objects.messages.Message
import database.SQLiteDB
import database.User
import database.Users
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.*

abstract class CommandBase {
    fun invoke(client : VkApiClient, actor : GroupActor, user : User, message : Message)
    {
        SQLiteDB.db
            transaction {
            client.messages()
                .send(actor)
                .keyboard(getKeyboard(client, actor, user, message))
                .peerId(message.peerId)
                .randomId(user.random++)
                .message( invokeInternal(user, message))
                .execute()


            updateUser(client, actor, user, message);
        }

    }

    protected abstract fun invokeInternal(user: User, message : Message ) : String
    protected abstract fun updateUser(client : VkApiClient,
                                       actor : GroupActor,
                                       user : User,
                                       message : Message)
    protected abstract fun getKeyboard(client : VkApiClient,
                                       actor : GroupActor,
                                       user : User,
                                       message : Message) : Keyboard
}