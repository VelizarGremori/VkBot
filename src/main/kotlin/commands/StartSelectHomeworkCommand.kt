package commands

import CommandName
import EmptyKeyboard
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.*
import database.SQLiteDB
import database.Subjects
import org.jetbrains.exposed.sql.transactions.transaction
import commands.CommandBase
import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll
import com.vk.api.sdk.objects.messages.*
import commands.UnknownCommand
import database.Subject
import database.User
import database.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import kotlin.math.sqrt

@CommandName(name = "{\"command\":\"startSelectHomework\"}")
class StartSelectHomeworkCommand : CommandBase() {


    override fun invokeInternal(user: User, message: Message): String {
        return "Выберите передмет"
    }

    override fun updateUser(client: VkApiClient, actor: GroupActor, user: User, message: Message) {

    }

    override fun getKeyboard(client: VkApiClient, actor: GroupActor, user: User, message: Message): Keyboard {
        SQLiteDB.db

        var subjects : List<Subject> = listOf()
        transaction {
            subjects = Subject.all().toList()
        }
        if(subjects.count() >0) {
            var keyboard = Keyboard()
            val columnCount = sqrt(subjects.count().toDouble()).toInt()

            keyboard.buttons = mutableListOf()

            val iter = subjects.iterator()
            while (iter.hasNext()) {
                val list = mutableListOf<KeyboardButton>()
                for (i in 1..columnCount) {
                    if (iter.hasNext()) {
                        var subject = iter.next()
                        var action = KeyboardButtonAction()
                            .setPayload(Action.SELECT_HW.actionName)
                            .setLabel(subject.name)
                            .setType(TemplateActionTypeNames.TEXT)
                        var button = KeyboardButton().setAction(action)

                        list.add(button)
                    }
                }
                keyboard.buttons.add(list)
            }
            return keyboard
        }
        return EmptyKeyboard
    }
}