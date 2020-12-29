import commands.CommandBase
import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.*
import commands.UnknownCommand
import database.SQLiteDB
import database.User
import database.Users
import org.jetbrains.exposed.sql.transactions.transaction


class CallbackHandler(_client: VkApiClient, _actor: GroupActor) : CallbackApiLongPoll(_client, _actor) {
    private val actor : GroupActor = _actor

    private val commands: MutableMap<String?, CommandBase> = mutableMapOf(null to UnknownCommand() )

    private var counter = 0;

    override fun messageNew(groupId: Int?, message: Message?) {
        super.messageNew(groupId, message)


        /*client.messages()
            .send(actor)
            .keyboard(StandartKeyboard)
            .peerId(message?.peerId)
            .randomId(1002)
            .message("reset")
            .execute()*/
        SQLiteDB.db
        if (message != null) {
            var user : User? = null
            transaction {
                user = User.find { Users.peerId eq message.peerId }.firstOrNull() ?: User.new {
                    peerId = message.peerId
                    nextAction = Action.START.actionName
                    nextActionParameter = ""
                    fullName = message.peerId.toString()
                    random = 0
                }
            }
                var commandName = user!!.nextAction

                if (!message.payload.isNullOrBlank())
                    commandName = message.payload

                getCommand(commandName).invoke(client, actor, user!!, message)

        }
    }

    private fun getCommand(command: String?) : CommandBase
    {
        if(!commands.containsKey(command)){
            try {
                commands[command] = CommandFactory.getCommand(command);
            }catch (e : Exception)
            {
                return UnknownCommand();
            }
        }
        return commands.getValue(command);
    }
}