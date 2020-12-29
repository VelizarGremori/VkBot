package commands

import CommandName
import EmptyKeyboard
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Keyboard
import com.vk.api.sdk.objects.messages.Message
import database.SQLiteDB
import database.User
import database.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update


@CommandName(name = "{\"command\":\"start\"}")
class StartCommand : CommandBase() {

    override fun invokeInternal( user: User, message: Message): String {

        return "Привет, теперь я буду принимать твои домашки и, возможно, многое другое." +
                "Для начала, как тебя зовут? Напиши так, как хочешь, чтобы я подписывал твои домашки"
    }

    override fun updateUser(client: VkApiClient, actor: GroupActor, user: User, message: Message) {
        user.nextAction = Action.CONFIRM_NAME.actionName
    }

    override fun getKeyboard(client: VkApiClient, actor: GroupActor, user: User, message: Message): Keyboard {
        return EmptyKeyboard
    }
}