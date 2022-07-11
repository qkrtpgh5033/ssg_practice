package com.ll.exam.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @DisplayName("VO 테스트")
    @Test
    void compare(){
        final int MONEY_VALUE = 10000;
        Money money1 = new Money(MONEY_VALUE);
        Money money2 = new Money(MONEY_VALUE);

        assertThat(money1).isEqualTo(money2);
        assertThat(money1).hasSameHashCodeAs(money2);
    }



}