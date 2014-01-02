package com.rockymadden.stringmetric.cli.phonetic

import com.rockymadden.stringmetric.cli.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class metaphonemetricSpec extends ScalaTest { "metaphonemetric" should provide {
	"main method" when passed {
		"valid dashless arguments" should executes {
			"print if they are a match" in {
				val out = new java.io.ByteArrayOutputStream()

				Console.withOut(out)(
					metaphonemetric.main(Array("--unitTest", "--debug", "abc", "abc"))
				)

				out.toString should equal ("true\n")
				out.reset()

				Console.withOut(out)(
					metaphonemetric.main(Array("--unitTest", "--debug", "abc", "xyz"))
				)

				out.toString should equal ("false\n")
				out.reset()

				Console.withOut(out)(
					metaphonemetric.main(Array("--unitTest", "--debug", "1", "1"))
				)

				out.toString should equal ("not comparable\n")
				out.reset()
			}
		}
		"no dashless arguments" should throws {
			"IllegalArgumentException" in {
				evaluating {
					metaphonemetric.main(Array("--unitTest", "--debug"))
				} should produce [IllegalArgumentException]
			}
		}
	}
}}
