package org.hashtree.stringmetric.cli.similarity

import org.hashtree.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class diceSorensenMetricSpec extends ScalaTest {
	"diceSorensenMetric" should provide {
		"main method" when passed {
			"valid dashless arguments" should executes {
				"print if they are a match" in {
					val out = new java.io.ByteArrayOutputStream()

					Console.withOut(out)(
						diceSorensenMetric.main(Array("--unitTest", "--debug", "--n=2", "abc", "abc"))
					)

					out.toString should equal ("1.0\n")
					out.reset()

					Console.withOut(out)(
						diceSorensenMetric.main(Array("--unitTest", "--debug", "--n=2", "abc", "xyz"))
					)

					out.toString should equal ("0.0\n")
					out.reset()
				}
			}
			"no dashless arguments" should throws {
				"IllegalArgumentException" in {
					evaluating {
						diceSorensenMetric.main(Array("--unitTest", "--debug"))
					} should produce [IllegalArgumentException]
				}
			}
		}
	}
}