package recamp.authenticationproject.global.utility;

import recamp.authenticationproject.global.exception.IllegalPasswordException;

public class Validator {
    private static final String MESSAGE = "비밀번호가 일치하지 않습니다. 다시 시도 부탁드립니다";
    private Validator() {
        throw new IllegalStateException();
    }

    public static void comparePasswords(String password, String validationPassword) {
        if (!password.equals(validationPassword)) {
            throw new IllegalPasswordException(MESSAGE);
        }
    }
}
