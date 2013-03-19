package com.rockymadden.stringmetric.tokenization

import com.google.caliper.Param
import com.rockymadden.stringmetric.{ CaliperBenchmark, CaliperRunner }
import scala.util.Random

final class NGramTokenizerBenchmark extends CaliperBenchmark {
	import NGramTokenizerBenchmark.Tokenizer

	@Param(Array("0", "1", "2", "4", "8", "16"))
	var length: Int = _

	@Param(Array("2", "3"))
	var n: Int = _

	var string: String = _
	var charArray: Array[Char] = _

	override protected def setUp() {
		string = Random.alphanumeric.take(length).mkString
		charArray = string.toCharArray
	}

	def timeComputeWithCharArray(reps: Int) = run(reps) {
		Tokenizer.tokenize(charArray)(n)
	}

	def timeComputeWithString(reps: Int) = run(reps) {
		Tokenizer.tokenize(string)(n)
	}
}

object NGramTokenizerBenchmark extends CaliperRunner(classOf[NGramTokenizerBenchmark]) {
	private final val Tokenizer = NGramTokenizer()
}
