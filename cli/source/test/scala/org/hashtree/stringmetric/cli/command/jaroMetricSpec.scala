package org.hashtree.stringmetric.cli.command

import org.hashtree.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class jaroMetricSpec extends ScalaTest {
	"jaroMetric" should provide {
		"main method" when passed {
			"valid dashless arguments" should executes {
				"print the distance" in {
					val out = new java.io.ByteArrayOutputStream()

					Console.withOut(out)(
						jaroMetric.main(Array("--unitTest", "--debug", "aBc", "abc"))
					)

					out.toString should equal ("1.0\n")
					out.reset()

					Console.withOut(out)(
						jaroMetric.main(Array("--unitTest", "--debug", "aBc", "xyz"))
					)

					out.toString should equal ("0.0\n")
					out.reset()
				}
			}
			"no dashless arguments" should throws {
				"IllegalArgumentException" in {
					evaluating {
						jaroMetric.main(Array("--unitTest", "--debug"))
					} should produce [IllegalArgumentException]
				}
			}
		}
	}
}