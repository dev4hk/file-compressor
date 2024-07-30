import java.util.HashSet;
import java.util.Set;

public class ArgumentChecker {

    public static void check(String[] args) {

        if(args.length != 3) {
            throw new RuntimeException(AppEnum.INVALID_NUMBER_OF_ARGUMENTS.message);
        }

        String sourcePath = args[0];
        String option = args[1];
        String destinationPath = args[2];

        String argument = sourcePath + ">>>>" + option + ">>>>" + destinationPath;

        if(!argument.matches("^.+\\.txt>>>>-[d,e]>>>>.+.txt$")) {
            throw new RuntimeException(AppEnum.INVALID_ARGUMENT.message);
        }

    }
}
