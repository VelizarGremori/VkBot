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
import org.mortbay.log.Log
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.security.cert.Extension

@CommandName(name = "{\"command\":\"pushHomework\"}")
class PushHomeworkCommand : CommandBase() {

    override fun invokeInternal(user: User, message: Message): String {
        try {
            var attachment = message!!.attachments[0]
            val website = attachment.doc.url.toURL()
            val rbc: ReadableByteChannel = Channels.newChannel(website.openStream())
            val fileName = user.fullName.replace(' ', '_')
            val dir =  File(Application.DOWNLOAD_PATH + "\\" + user.nextActionParameter);
            dir.mkdir()
            val file = File(dir.path + "\\"+ fileName + "." + attachment.doc.ext)
            file.createNewFile()
            val fos = FileOutputStream(file)
            fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
        }catch (e : Exception)
        {
            return "Произошло что-то странное. Возможно, вы ничего не отправили \uD83D\uDE43" +
                    "Попробуйте снова"
        }

        return "Файл получен!"
    }

    override fun updateUser(client: VkApiClient, actor: GroupActor, user: User, message: Message) {
        user.nextAction = Action.WAIT.actionName
        user.nextActionParameter = ""
    }

    override fun getKeyboard(client: VkApiClient, actor: GroupActor, user: User, message: Message): Keyboard {
        return StandartKeyboard
    }
}