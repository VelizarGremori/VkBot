package commands

import EmptyKeyboard
import CommandName
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.*
import database.SQLiteDB
import org.jetbrains.exposed.sql.transactions.transaction
import database.User
import database.Users
import org.jetbrains.exposed.sql.update

@CommandName(name = "{\"command\":\"selectHomework\"}")
class SelectHomeworkCommand : CommandBase() {


    override fun invokeInternal(user: User, message: Message): String {
        return "Отправьте файл в формате .doc, .docx, .pdf"
    }

    override fun updateUser(client: VkApiClient, actor: GroupActor, user: User, message: Message) {
        user.nextAction = Action.PUSH_HW.actionName
        user.nextActionParameter = message.text
    }

    override fun getKeyboard(client: VkApiClient, actor: GroupActor, user: User, message: Message): Keyboard {
        return EmptyKeyboard
    }
}