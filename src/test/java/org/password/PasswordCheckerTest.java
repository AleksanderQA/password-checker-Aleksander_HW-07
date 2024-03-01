package org.password;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PasswordCheckerTest {

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialChars() {
        assertTrue(PasswordChecker.checkPasswordComplexity("Password123!"));
    }

    @Test
    void shouldReturnTrueForComplexIfPasswordContainsUpperCaseLetters() {
        assertTrue(PasswordChecker.checkPasswordComplexity("PASSWORD22/"));
    }

    @Test
    void shouldReturnTrueForComplexIfPasswordContainsLowerAndUpperCaseLetters() {
        assertTrue(PasswordChecker.checkPasswordComplexity("PaSsWoRd22/"));
    }

    @Test
    void shouldReturnTrueForComplexIfPasswordContainsMoreThanMinimumCharacters() {
        assertTrue(PasswordChecker.checkPasswordComplexity("Passsswoooooooord1234//-*"));
    }

    @Test
    void shouldReturnFalseNotComplexIfPasswordContainsLessCharactersThanExpected() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Pass12*"));
    }

    @Test
    void shouldReturnNotComplexDueMissingNecessarySpecialSymbols() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Password1234"));
    }

    @Test
    void shouldReturnNotComplexIfPasswordContainsOnlyDigits() {
        assertFalse(PasswordChecker.checkPasswordComplexity("12345678"));
    }

    @Test
    void shouldReturnNotComplexIfPasswordContainsOnlyLetters() {
        assertFalse(PasswordChecker.checkPasswordComplexity("asdfghjk"));
    }

    @Test
    void shouldReturnNotComplexIfPasswordContainsOnlyUpperCaseLetters() {
        assertFalse(PasswordChecker.checkPasswordComplexity("EERJGNRETNBRT"));
    }

    @Test
    void shouldReturnNotComplexIfFieldIsEmpty() {
        assertFalse(PasswordChecker.checkPasswordComplexity(""));
    }

    @Test
    void shouldReturnNotComplexIfPasswordContainsCyrillicLetters() {
        assertTrue(PasswordChecker.checkPasswordComplexity("пароль123*/-"));
    }
}
