package recamp.authenticationproject.global.utility;

import java.util.Random;

public class ValueGenerator {

    private ValueGenerator() {
        throw new IllegalStateException();
    }

    public static String messageCertification() {
        return createCode();
    }

    private static String createCode(){
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for(int i=0;i<8;i++){
            int index = random.nextInt(3);
            switch (index) {
                case 0 -> key.append((char) ((int) random.nextInt(26) + 97));
                case 1 -> key.append((char) ((int) random.nextInt(26) + 65));
                case 2 -> key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

    public static String newPassword() {
        return createCode();
    }
}