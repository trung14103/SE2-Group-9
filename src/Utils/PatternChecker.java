package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternChecker {
    public boolean checkEmail(String email) {
        Pattern patternEmail = Pattern.compile("/^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$/");
        Matcher matcher = patternEmail.matcher(email);

        return matcher.matches();
    }

}
