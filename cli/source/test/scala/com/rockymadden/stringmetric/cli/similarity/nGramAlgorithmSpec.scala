package com.rockymadden.stringmetric.cli.similarity

import com.rockymadden.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class nGramAlgorithmSpec extends ScalaTest {
	"nGramAlgorithm" should provide {
		"main method" when passed {
			"valid dashless argument and valid n argument" should executes {
				"print N-Gram representation" in {
					val out = new java.io.ByteArrayOutputStream()

					Console.withOut(out)(
						nGramAlgorithm.main(
							Array(
								"--unitTest",
								"--debug",
								"--n=1",
								"abc"
							)
						)
					)

					out.toString should equal ("a|b|c\n")
					out.reset()

					Console.withOut(out)(
						nGramAlgorithm.main(
							Array(
								"--unitTest",
								"--debug",
								"--n=2",
								"abc"
							)
						)
					)

					out.toString should equal ("ab|bc\n")
					out.reset()
				}
			}
			"valid dashless argument and invalid n argument" should throws {
				"IllegalArgumentException" in {
					evaluating {
						nGramAlgorithm.main(
							Array(
								"--unitTest",
								"abc",
								"abc"
							)
						)
					} should produce [IllegalArgumentException]
				}
			}
			"no dashless argument" should throws {
				"IllegalArgumentException" in {
					evaluating {
						nGramAlgorithm.main(Array("--unitTest", "--debug"))
					} should produce [IllegalArgumentException]
				}
			}
		}
	}
}