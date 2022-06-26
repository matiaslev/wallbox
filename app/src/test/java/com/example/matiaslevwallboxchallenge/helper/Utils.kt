package com.example.matiaslevwallboxchallenge.helper

import org.junit.Assert.assertEquals

infix fun <T> T.shouldBeEqualTo(expected: T?): T = this.apply { assertEquals(expected, this) }