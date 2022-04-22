package com.example.project.parameterized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleParamDemo {

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
    void palindromes(String candidate) {
        assertTrue(candidate.contentEquals(new StringBuilder(candidate).reverse()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void nullEmptyAndBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }


    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void nullEmptyAndBlankStringsWithOneAnnotation(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    @DisplayName("Testing exceptions and error messages for external name")
    @ParameterizedTest(
            name = "{index} => Validating external name ''{0}'', expected to fail with code ''{1}''")
    @CsvSource(useHeadersInDisplayName = true, delimiter = '|', textBlock = """
            #--------------------------------------------------------------------------------------------------
            ExternalName     |     Error Code     |     Expected Error Message
            #-------------------------------------------------------------------------------------------------------------------------------
            NULL     |      error.string.null     |     The field cannot have value equal to NULL
            #-----------------------------
            t2h3i4s is89 v5555alid | error.externalName.maxDigits | The profile name should not have more than six digits. Please remove the extra digits from the profile name
            #-----------------------------
            thi!...s .is .v?alid | error.externalName.maxPunctuation | The profile name should not have more than six punctuation marks. Please remove the extra characters from the profile name     
            #-----------------------------
            this is\" val”id$A”B | error.externalName.invalidCharacters | The external name \"this is\" val”id$A”B\" contains the unsupported characters (\"”$”). Please remove the unsupported characters from the external name     
            #-----------------------------
            this is val�id | error.externalName.invalidCharacters | The external name \"this is val�id\" contains the unsupported characters (�). Please remove the unsupported characters from the external name     
            #-----------------------------
            this is val”id | error.externalName.invalidCharacters | The external name \"this is val”id\" contains the unsupported characters (”). Please remove the unsupported characters from the external name     
            #-----------------------------
            this is val“id | error.externalName.invalidCharacters | The external name \"this is val“id\" contains the unsupported characters (“). Please remove the unsupported characters from the external name     
            #-----------------------------
            this is val\"id |  error.externalName.invalidCharacters | The external name \"this is val\"id\" contains the unsupported characters (\"). Please remove the unsupported characters from the external name      
            #-----------------------------
            this i$ valid | error.externalName.invalidCharacters | The external name \"this i$ valid\" contains the unsupported characters ($). Please remove the unsupported characters from the external name     
            #-----------------------------
            """
//            {"NULL, error.string.null,The field cannot have value equal to NULL",
//            "t2h3i4s is89 v5555alid, error.externalName.maxDigits,The profile name should not have more than six digits. Please remove the extra digits from the profile name",
//            "thi!...s .is .v?alid, error.externalName.maxPunctuation,The profile name should not have more than six punctuation marks. Please remove the extra characters from the profile name",
//            "this is\" val”id$A”B, error.externalName.invalidCharacters, The external name \"this is\" val”id$A”B\" contains the unsupported characters (\"”$”). Please remove the unsupported characters from the external name",
//            "this is val�id, error.externalName.invalidCharacters, The external name \"this is val�id\" contains the unsupported characters (�). Please remove the unsupported characters from the external name",
//            "this is val”id, error.externalName.invalidCharacters, The external name \"this is val”id\" contains the unsupported characters (”). Please remove the unsupported characters from the external name",
//            "this is val“id, error.externalName.invalidCharacters, The external name \"this is val“id\" contains the unsupported characters (“). Please remove the unsupported characters from the external name",
//            "this is val\"id,  error.externalName.invalidCharacters, The external name \"this is val\"id\" contains the unsupported characters (\"). Please remove the unsupported characters from the external name ",
//            "this i$ valid, error.externalName.invalidCharacters, The external name \"this i$ valid\" contains the unsupported characters ($). Please remove the unsupported characters from the external name"}
    )
    void testNameWithUnsupportedCharactersWithLocaleTranslation(String name,
                                                                String expectedErrorMessageKey,
                                                                String expectedErrorMessageValue) {
        assertAll(() -> assertNotNull(name),
                () -> assertNotNull(expectedErrorMessageKey),
                () -> assertNotNull(expectedErrorMessageValue));
    }


}
