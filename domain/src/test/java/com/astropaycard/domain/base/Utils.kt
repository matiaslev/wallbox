package com.astropaycard.domain.base

import org.junit.Assert.assertEquals

infix fun <T> T.shouldBeEqualTo(expected: T?): T = this.apply { assertEquals(expected, this) }