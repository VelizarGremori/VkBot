package commands

import CommandName
import StandartKeyboard
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Keyboard
import com.vk.api.sdk.objects.messages.Message
import database.SQLiteDB
import database.User
import database.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update


@CommandName(name = "{\"command\":\"confirmName\"}")
class ConfirmCommand : CommandBase() {

    override fun invokeInternal(user: User, message: Message): String {
        return "Привет, " + message.text +". Захочешь поменять имя - с тебя шоколадку \uD83D\uDE1C"
    }

    override fun updateUser(client: VkApiClient, actor: GroupActor, user: User, message: Message) {
        user.nextAction = Action.WAIT.actionName
        user.nextActionParameter = ""
        user.fullName = message.text
    }

    override fun getKeyboard(client: VkApiClient, actor: GroupActor, user: User, message: Message): Keyboard {
        return StandartKeyboard
    }
}