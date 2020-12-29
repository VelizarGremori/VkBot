import com.vk.api.sdk.objects.messages.Keyboard
import com.vk.api.sdk.objects.messages.KeyboardButton
import com.vk.api.sdk.objects.messages.KeyboardButtonAction
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames

object StandartKeyboard : Keyboard(){
    init {
        var action = KeyboardButtonAction()
            .setPayload(Action.START_SELECT.actionName)
            .setLabel("Сдать ДЗ")//.setLabel("Отправить ДЗ")
            .setType(TemplateActionTypeNames.TEXT)
        var button = KeyboardButton().setAction(action)
        buttons = listOf(listOf(button))
    }
}