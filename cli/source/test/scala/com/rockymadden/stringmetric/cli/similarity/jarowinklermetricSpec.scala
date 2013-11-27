package com.rockymadden.stringmetric.cli.similarity

import com.rockymadden.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class jarowinklermetricSpec extends ScalaTest {
	"jarowinklermetric" should provide {
		"main method" when passed {
			"valid dashless arguments" should executes {
				"print the distance" in {
					val out = new java.io.ByteArrayOutputStream()

					Console.withOut(out)(
						jarowinklermetric.main(Array("--unitTest", "--debug", "abc", "abc"))
					)

					out.toString should equal ("1.0\n")
					out.reset()

					Console.withOut(out)(
						jarowinklermetric.main(Array("--unitTest", "--debug", "abc", "xyz"))
					)

					out.toString should equal ("0.0\n")
					out.reset()
				}
			}
			"no dashless arguments" should throws {
				"IllegalArgumentException" in {
					evaluating {
						jarowinklermetric.main(Array("--unitTest", "--debug"))
					} should produce [IllegalArgumentException]
				}
			}
		}
	}
}
