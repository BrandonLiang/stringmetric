package com.rockymadden.stringmetric.filter

import com.rockymadden.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class AsciiLetterNumberFilterSpec extends ScalaTest {
	import AsciiLetterNumberFilterSpec.Filter

	"AsciiLetterNumberFilter" should provide {
		"overloaded filter method" when passed {
			"String with letters and numbers" should returns {
				"String with letters and numbers removed" in {
					Filter.filter("	Hello123World!") should equal ("	!")
					Filter.filter("Hello123	!World") should equal ("	!")
					Filter.filter("!Hello123World	") should equal ("!	")
				}
			}
			"character array with letters and numbers" should returns {
				"character array with letters and numbers removed" in {
					Filter.filter("	Hello123World!".toCharArray) should equal ("	!".toCharArray)
					Filter.filter("Hello123	!World".toCharArray) should equal ("	!".toCharArray)
					Filter.filter("!Hello123World	".toCharArray) should equal ("!	".toCharArray)
				}
			}
		}
	}
}

object AsciiLetterNumberFilterSpec {
	private final val Filter = new StringFilterDelegate with AsciiLetterNumberFilter
}
