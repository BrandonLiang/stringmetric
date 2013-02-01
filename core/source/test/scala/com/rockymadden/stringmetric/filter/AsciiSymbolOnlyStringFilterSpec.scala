package com.rockymadden.stringmetric.filter

import com.rockymadden.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class AsciiSymbolOnlyStringFilterSpec extends ScalaTest {
	import AsciiSymbolOnlyStringFilterSpec.Filter

	"AsciiSymbolOnlyStringFilter" should provide {
		"overloaded filter method" when passed {
			"String with mixed characters" should returns {
				"String with non-symbols removed" in {
					Filter.filter("!@#$%^&*()abc123") should equal ("!@#$%^&*()")
					Filter.filter("abc123!@#$%^&*()") should equal ("!@#$%^&*()")
					Filter.filter("!@#$%abc123^&*()") should equal ("!@#$%^&*()")
				}
			}
			"character array with mixed characters" should returns {
				"character array with non-symbols removed" in {
					Filter.filter("!@#$%^&*()abc123".toCharArray) should equal ("!@#$%^&*()".toCharArray)
					Filter.filter("abc123!@#$%^&*()".toCharArray) should equal ("!@#$%^&*()".toCharArray)
					Filter.filter("!@#$%abc123^&*()".toCharArray) should equal ("!@#$%^&*()".toCharArray)
				}
			}
		}
	}
}

object AsciiSymbolOnlyStringFilterSpec {
	private final val Filter = new StringFilterDelegate with AsciiSymbolOnlyStringFilter
}
