package commands

import CommandName
import StandartKeyboard
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.*
import database.User


@CommandName(name = "{\"command\":\"wait\"}")
public class WaitCommand : CommandBase() {
    override fun invokeInternal(user: User, message: Message): String {
        return "Для того, чтобы что-то сделать, нажми на кнопки"
    }

    override fun updateUser(client: VkApiClient, actor: GroupActor, user: User, message: Message) {
    }

    public override fun getKeyboard(client: VkApiClient, actor: GroupActor, user: User, message: Message): Keyboard {
        return StandartKeyboard;
    }
}
