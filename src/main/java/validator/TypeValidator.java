package validator;

public class TypeValidator {

    public static String checkType(String contact) {
        if (contact.length() == 9) {
            return "phone";
        }
        if (contact.substring(0, 3).equals("jbr")) {
            return "jabber";
        }
        if (isContainAt(contact)) {
            return "email";
        }
        return "unknown";
    }

    private static boolean isContainAt(String contact) {
        int atCount = 0;

        for (int i = 0; i < contact.length(); i++) {
            if (contact.charAt(i) == '@') {
                atCount++;
            }
        }
        if (atCount == 1) {
            return true;
        } else {
            return false;
        }
    }
}
