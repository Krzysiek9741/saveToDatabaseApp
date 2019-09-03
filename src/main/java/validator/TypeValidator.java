package validator;

public class TypeValidator {

    // review dlaczego nie zwracamy model.Type tylko Stringi? A później Stringi zamieniamy na Type?
    public static String checkType(String contact) {
        if (contact.length() == 9) {
            return "phone";
        }
        // review stała.equals.(zmienna), czyli "jbr".equals(contact.substring(0, 3))
        // review ale i to nic nie da, jeżei contact będzie null :(
        // review dodatkowo, jeżeli contact będzie miał 2 znaki to wyleci wyjątek StringIndexOutOfBoundsException
        if (contact.substring(0, 3).equals("jbr")) {
            return "jabber";
        }
        if (isContainAt(contact)) {
            return "email";
        }
        return "unknown";
    }

    // review po prostu containsAtCharacter()
    private static boolean isContainAt(String contact) {
        int atCount = 0;

        for (int i = 0; i < contact.length(); i++) {
            if (contact.charAt(i) == '@') {
                atCount++;
            }
        }

        // review czyli każdy String zawierający @ to email? Nie do końca - są lepsze metody sprawdzenia tego faktu

        if (atCount == 1) {
            return true;
        } else {
            return false;
        }
    }
}
