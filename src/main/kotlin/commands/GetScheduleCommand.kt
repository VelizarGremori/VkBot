package commands

import CommandName
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Keyboard
import com.vk.api.sdk.objects.messages.Message
import database.User
import java.util.*

@CommandName(name = "{\"command\":\"getSchedule\"}")
class GetScheduleCommand : CommandBase() {
    override fun invokeInternal(user: User, message: Message): String {
        TODO("Not yet implemented")
    }

    override fun updateUser(client: VkApiClient, actor: GroupActor, user: User, message: Message) {
        TODO("Not yet implemented")
    }

    override fun getKeyboard(client: VkApiClient, actor: GroupActor, user: User, message: Message): Keyboard {
        TODO("Not yet implemented")
    }

}