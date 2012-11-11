package org.hashtree.stringmetric.cli.similarity

import org.hashtree.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class weightedLevenshteinMetricSpec extends ScalaTest {
	"weightedLevenshteinMetric" should provide {
		"main method" when passed {
			"valid dashless arguments and valid weight arguments" should executes {
				"print if they are a match" in {
					val out = new java.io.ByteArrayOutputStream()

					Console.withOut(out)(
						weightedLevenshteinMetric.main(
							Array(
								"--unitTest",
								"--debug",
								"--deleteWeight=1",
								"--insertWeight=1",
								"--substituteWeight=1",
								"abc",
								"abc"
							)
						)
					)

					out.toString should equal ("0.0\n")
					out.reset()

					Console.withOut(out)(
						weightedLevenshteinMetric.main(
							Array(
								"--unitTest",
								"--debug",
								"--deleteWeight=2",
								"--insertWeight=2",
								"--substituteWeight=1",
								"abc",
								"xyz"
							)
						)
					)

					out.toString should equal ("3.0\n")
					out.reset()

					Console.withOut(out)(
						weightedLevenshteinMetric.main(
							Array(
								"--unitTest",
								"--debug",
								"--deleteWeight=2",
								"--insertWeight=1",
								"--substituteWeight=2",
								"xyz",
								"xyzxyz"
							)
						)
					)

					out.toString should equal ("3.0\n")
					out.reset()

					Console.withOut(out)(
						weightedLevenshteinMetric.main(
							Array(
								"--unitTest",
								"--debug",
								"--deleteWeight=1",
								"--insertWeight=2",
								"--substituteWeight=2",
								"xyzxyz",
								"xyz"
							)
						)
					)

					out.toString should equal ("3.0\n")
					out.reset()
				}
			}
			"valid dashless arguments and invalid weight arguments" should throws {
				"IllegalArgumentException" in {
					evaluating {
						weightedLevenshteinMetric.main(
							Array(
								"--unitTest",
								"--debug",
								"--deleteWeight=1",
								"--substituteWeight=1",
								"abc",
								"abc"
							)
						)
					} should produce [IllegalArgumentException]

					evaluating {
						weightedLevenshteinMetric.main(
							Array(
								"--unitTest",
								"--debug",
								"--deleteWeight=1",
								"--insertWeight=q",
								"--substituteWeight=1",
								"abc",
								"abc"
							)
						)
					} should produce [IllegalArgumentException]
				}
			}
			"no dashless arguments" should throws {
				"IllegalArgumentException" in {
					evaluating {
						weightedLevenshteinMetric.main(Array("--unitTest", "--debug"))
					} should produce [IllegalArgumentException]
				}
			}
		}
	}
}