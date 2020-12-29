package commands

import EmptyKeyboard
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.*
import database.User

class UnknownCommand : CommandBase() {

    override fun invokeInternal(user: User, message: Message): String {
        return "UnknownCommand. Где ты взял эту кнопку?!"
    }

    override fun updateUser(client: VkApiClient, actor: GroupActor, user: User, message: Message) {
    }

    override fun getKeyboard(client: VkApiClient, actor: GroupActor, user: User, message: Message): Keyboard {
       return EmptyKeyboard
    }
}