package com.w2w.What2Watch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class What2WatchApplicationTest {

    private  What2WatchApplication w2wDummy = new What2WatchApplication();

    @Test
    void hello() {
        assertEquals("Hello World !", w2wDummy.hello("abc"));
    }
}