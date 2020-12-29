
import commands.CommandBase;
import org.reflections.Reflections;

import java.util.Set;

public class CommandFactory {
    public static CommandBase getCommand(String command) throws Exception {
        Reflections reflections = new Reflections("commands");

        Set<Class<? extends CommandBase>> allClasses =
                reflections.getSubTypesOf(CommandBase.class);

        for (Class<? extends CommandBase> commandClass:
                allClasses ) {
            String name;
            try{
                name = commandClass.getAnnotation(CommandName.class).name();
            }catch (Exception e)
            {
                name = "";
            }
            if (command.equals(name)) {
                return commandClass.getConstructor().newInstance();
            }
        }

        throw new Exception();
    }

}
