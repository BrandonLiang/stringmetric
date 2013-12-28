package com.rockymadden.stringmetric.cli.phonetic

import com.rockymadden.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class refinedsoundexalgorithmSpec extends ScalaTest { "refinedsoundexalgorithm" should provide {
	"main method" when passed {
		"valid dashless argument" should executes {
			"print phonetic representation" in {
				val out = new java.io.ByteArrayOutputStream()

				Console.withOut(out)(
					refinedsoundexalgorithm.main(Array("--unitTest", "--debug", "abc"))
				)

				out.toString should equal ("a013\n")
				out.reset()

				Console.withOut(out)(
					refinedsoundexalgorithm.main(Array("--unitTest", "--debug", "1"))
				)

				out.toString should equal ("not computable\n")
				out.reset()
			}
		}
		"no dashless argument" should throws {
			"IllegalArgumentException" in {
				evaluating {
					refinedsoundexalgorithm.main(Array("--unitTest", "--debug"))
				} should produce [IllegalArgumentException]
			}
		}
	}
}}
