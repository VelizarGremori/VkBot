import com.vk.api.sdk.objects.messages.Keyboard
import com.vk.api.sdk.objects.messages.KeyboardButton
import com.vk.api.sdk.objects.messages.KeyboardButtonAction
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames

object EmptyKeyboard : Keyboard(){
    init {
        buttons = listOf()
    }
}