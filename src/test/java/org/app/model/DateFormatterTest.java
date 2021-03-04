package org.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateFormatterTest {

    @Test
    void dateParsingFromUTC() {

        Assertions.assertEquals(DateFormatter.getDateFromUTC(1614772800), "03/03/2021 śr.");
        Assertions.assertEquals(DateFormatter.getDateFromUTC(1609534479), "01/01/2021 pt.");

    }

}
