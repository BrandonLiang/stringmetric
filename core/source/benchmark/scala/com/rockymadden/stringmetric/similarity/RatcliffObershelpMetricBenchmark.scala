package com.rockymadden.stringmetric.similarity

import com.google.caliper.Param
import com.rockymadden.stringmetric.{CaliperBenchmark, CaliperRunner}
import scala.util.Random

final class RatcliffObershelpMetricBenchmark extends CaliperBenchmark {
	import RatcliffObershelpMetricBenchmark.Metric

	@Param(Array("0", "1", "2", "4", "8", "16"))
	var length: Int = _

	var string1: String = _
	var charArray1: Array[Char] = _
	var string2: String = _
	var charArray2: Array[Char] = _

	override protected def setUp() {
		@annotation.tailrec
		def random(l: Int, ps: String = null): String =
			if (l == 0) ""
			else {
				val s = Random.alphanumeric.take(l).mkString

				if (ps == null || s != ps) s
				else random(l, ps)
			}

		string1 = random(length)
		string2 = random(length, string1)
		charArray1 = string1.toCharArray
		charArray2 = string2.toCharArray
	}

	def timeCompareWithDifferentCharArrays(reps: Int) = run(reps) {
		Metric.compare(charArray1, charArray2)
	}

	def timeCompareWithDifferentStrings(reps: Int) = run(reps) {
		Metric.compare(string1, string2)
	}

	def timeCompareWithIdenticalCharArrays(reps: Int) = run(reps) {
		Metric.compare(charArray1, charArray1)
	}

	def timeCompareWithIdenticalStrings(reps: Int) = run(reps) {
		Metric.compare(string1, string1)
	}
}

object RatcliffObershelpMetricBenchmark extends CaliperRunner(classOf[RatcliffObershelpMetricBenchmark]) {
	private final val Metric = RatcliffObershelpMetric()
}
