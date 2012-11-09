package org.hashtree.stringmetric.cli.similarity

import org.hashtree.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class nGramMetricSpec extends ScalaTest {
	"nGramMetric" should provide {
		"main method" when passed {
			"valid dashless arguments and valid n argument" should executes {
				"print if they are a match" in {
					val out = new java.io.ByteArrayOutputStream()

					Console.withOut(out)(
						nGramMetric.main(
							Array(
								"--unitTest",
								"--debug",
								"--n=1",
								"aBc",
								"abc"
							)
						)
					)

					out.toString should equal ("1.0\n")
					out.reset()

					Console.withOut(out)(
						nGramMetric.main(
							Array(
								"--unitTest",
								"--debug",
								"--n=1",
								"aBc",
								"xyz"
							)
						)
					)

					out.toString should equal ("0.0\n")
					out.reset()
				}
			}
			"valid dashless arguments and invalid n argument" should throws {
				"IllegalArgumentException" in {
					evaluating {
						nGramMetric.main(
							Array(
								"--unitTest",
								"aBc",
								"abc"
							)
						)
					} should produce [IllegalArgumentException]
				}
			}
			"no dashless arguments" should throws {
				"IllegalArgumentException" in {
					evaluating {
						nGramMetric.main(Array("--unitTest", "--debug"))
					} should produce [IllegalArgumentException]
				}
			}
		}
	}
}