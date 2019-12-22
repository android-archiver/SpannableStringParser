package com.hitanshudhawan.spannablestringparser.spanner

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.*
import com.hitanshudhawan.spannablestringparser.spannify
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SpannerTest {

    @Test
    fun testColor001() {
        val string = "{ `Hitanshu` < color : #0000FF /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertEquals(Color.parseColor("#0000FF"), (spans1[0] as ForegroundColorSpan).foregroundColor)
        }
    }

    @Test
    fun testColor002() {
        val string = "{ `Hitanshu` < color : #AA0000FF /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertEquals(Color.parseColor("#AA0000FF"), (spans1[0] as ForegroundColorSpan).foregroundColor)
        }
    }

    @Test
    fun testColor003() {
        val string = "Hello { `Hitanshu` < color : #0000FF /> } Dhawan"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans(queryStart = 0, queryEnd = 6)
            assertTrue(spans1.isEmpty())
            val spans2 = spannable.getSpans(queryStart = 6, queryEnd = 14)
            assertTrue(spans2.size == 1)
            assertEquals(Color.parseColor("#0000FF"), (spans2[0] as ForegroundColorSpan).foregroundColor)
            val spans3 = spannable.getSpans(queryStart = 14, queryEnd = length)
            assertTrue(spans3.isEmpty())
        }
    }

    @Test
    fun testTextDecorationLine001() {
        val string = "{ `Hitanshu` < text-decoration-line : underline /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertTrue(spans1[0] is UnderlineSpan)
        }
    }

    @Test
    fun testTextDecorationLine002() {
        val string = "{ `Hitanshu` < text-decoration-line : line-through /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertTrue(spans1[0] is StrikethroughSpan)
        }
    }

    @Test
    fun testTextDecorationLine003() {
        val string = "Hello { `Hitanshu` < text-decoration-line : underline /> } Dhawan"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans(queryStart = 0, queryEnd = 6)
            assertTrue(spans1.isEmpty())
            val spans2 = spannable.getSpans(queryStart = 6, queryEnd = 14)
            assertTrue(spans2.size == 1)
            assertTrue(spans2[0] is UnderlineSpan)
            val spans3 = spannable.getSpans(queryStart = 14, queryEnd = length)
            assertTrue(spans3.isEmpty())
        }
    }

    @Test
    fun testTextDecorationLine004() {
        val string = "Hello { `Hitanshu` < text-decoration-line : line-through /> } Dhawan"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans(queryStart = 0, queryEnd = 6)
            assertTrue(spans1.isEmpty())
            val spans2 = spannable.getSpans(queryStart = 6, queryEnd = 14)
            assertTrue(spans2.size == 1)
            assertTrue(spans2[0] is StrikethroughSpan)
            val spans3 = spannable.getSpans(queryStart = 14, queryEnd = length)
            assertTrue(spans3.isEmpty())
        }
    }

    @Test
    fun testFontSize001() {
        val string = "{ `Hitanshu` < font-size : 16dp /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertEquals(16, (spans1[0] as AbsoluteSizeSpan).size)
            assertTrue((spans1[0] as AbsoluteSizeSpan).dip)
        }
    }

    @Test
    fun testFontSize002() {
        val string = "{ `Hitanshu` < font-size : 1.5em /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertEquals(1.5F, (spans1[0] as RelativeSizeSpan).sizeChange)
        }
    }

    @Test
    fun testFontSize003() {
        val string = "{ `Hitanshu` < font-size : 16px /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertEquals(16, (spans1[0] as AbsoluteSizeSpan).size)
            assertFalse((spans1[0] as AbsoluteSizeSpan).dip)
        }
    }

    @Test
    fun testFontStyle001() {
        val string = "{ `Hitanshu` < font-style : italic /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertEquals(Typeface.ITALIC, (spans1[0] as StyleSpan).style)
        }
    }

    @Test
    fun testFontWeight001() {
        val string = "{ `Hitanshu` < font-weight : bold /> }"
        with(string.spannify()) {
            val spannable = this as SpannableStringBuilder

            val spans1 = spannable.getSpans()
            assertTrue(spans1.size == 1)
            assertEquals(Typeface.BOLD, (spans1[0] as StyleSpan).style)
        }
    }

    private fun SpannableStringBuilder.getSpans(queryStart: Int = 0, queryEnd: Int = length): Array<Any> {
        return getSpans(queryStart, queryEnd, Any::class.java)
    }

}
