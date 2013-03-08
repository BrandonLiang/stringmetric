package com.rockymadden.stringmetric

trait StringMetric[R] extends Metric[String, R] {
	def compare(charArray1: Array[Char], charArray2: Array[Char]): Option[R]
}

object StringMetric {
	type HammingMetric = com.rockymadden.stringmetric.similarity.HammingMetric
	val HammingMetric = com.rockymadden.stringmetric.similarity.HammingMetric
	lazy val hamming = HammingMetric()

	type JaroMetric = com.rockymadden.stringmetric.similarity.JaroMetric
	val JaroMetric = com.rockymadden.stringmetric.similarity.JaroMetric
	lazy val jaro = JaroMetric()

	type JaroWinklerMetric = com.rockymadden.stringmetric.similarity.JaroWinklerMetric
	val JaroWinklerMetric = com.rockymadden.stringmetric.similarity.JaroWinklerMetric
	lazy val jaroWinkler = JaroWinklerMetric()

	type LevenshteinMetric = com.rockymadden.stringmetric.similarity.LevenshteinMetric
	val LevenshteinMetric = com.rockymadden.stringmetric.similarity.LevenshteinMetric
	lazy val levenshtein = LevenshteinMetric()

	type MetaphoneMetric = com.rockymadden.stringmetric.phonetic.MetaphoneMetric
	val MetaphoneMetric = com.rockymadden.stringmetric.phonetic.MetaphoneMetric
	lazy val metaphone = MetaphoneMetric()

	type NysiisMetric = com.rockymadden.stringmetric.phonetic.NysiisMetric
	val NysiisMetric = com.rockymadden.stringmetric.phonetic.NysiisMetric
	lazy val nysiis = NysiisMetric()

	type RefinedNysiisMetric = com.rockymadden.stringmetric.phonetic.RefinedNysiisMetric
	val RefinedNysiisMetric = com.rockymadden.stringmetric.phonetic.RefinedNysiisMetric
	lazy val refinedNysiis = RefinedNysiisMetric()

	type RefinedSoundexMetric = com.rockymadden.stringmetric.phonetic.RefinedSoundexMetric
	val RefinedSoundexMetric = com.rockymadden.stringmetric.phonetic.RefinedSoundexMetric
	lazy val refinedSoundex = RefinedSoundexMetric()

	type SoundexMetric = com.rockymadden.stringmetric.phonetic.SoundexMetric
	val SoundexMetric = com.rockymadden.stringmetric.phonetic.SoundexMetric
	lazy val soundex = SoundexMetric()

	def compareWithHamming(charArray1: Array[Char], charArray2: Array[Char]): Option[Int] =
		hamming.compare(charArray1, charArray2)

	def compareWithHamming(string1: String, string2: String): Option[Int] = hamming.compare(string1, string2)

	def compareWithJaro(charArray1: Array[Char], charArray2: Array[Char]): Option[Double] =
		jaro.compare(charArray1, charArray2)

	def compareWithJaro(string1: String, string2: String): Option[Double] = jaro.compare(string1, string2)

	def compareWithJaroWinkler(charArray1: Array[Char], charArray2: Array[Char]): Option[Double] =
		jaroWinkler.compare(charArray1, charArray2)

	def compareWithJaroWinkler(string1: String, string2: String): Option[Double] = jaroWinkler.compare(string1, string2)

	def compareWithLevenshtein(charArray1: Array[Char], charArray2: Array[Char]): Option[Int] =
		levenshtein.compare(charArray1, charArray2)

	def compareWithLevenshtein(string1: String, string2: String): Option[Int] = levenshtein.compare(string1, string2)

	def compareWithMetaphone(charArray1: Array[Char], charArray2: Array[Char]): Option[Boolean] =
		metaphone.compare(charArray1, charArray2)

	def compareWithMetaphone(string1: String, string2: String): Option[Boolean] = metaphone.compare(string1, string2)

	def compareWithNysiis(charArray1: Array[Char], charArray2: Array[Char]): Option[Boolean] =
		nysiis.compare(charArray1, charArray2)

	def compareWithNysiis(string1: String, string2: String): Option[Boolean] = nysiis.compare(string1, string2)

	def compareWithRefinedNysiis(charArray1: Array[Char], charArray2: Array[Char]): Option[Boolean] =
		refinedNysiis.compare(charArray1, charArray2)

	def compareWithRefinedNysiis(string1: String, string2: String): Option[Boolean] =
		refinedNysiis.compare(string1, string2)

	def compareWithRefinedSoundex(charArray1: Array[Char], charArray2: Array[Char]): Option[Boolean] =
		refinedSoundex.compare(charArray1, charArray2)

	def compareWithRefinedSoundex(string1: String, string2: String): Option[Boolean] =
		refinedSoundex.compare(string1, string2)

	def compareWithSoundex(charArray1: Array[Char], charArray2: Array[Char]): Option[Boolean] =
		soundex.compare(charArray1, charArray2)

	def compareWithSoundex(string1: String, string2: String): Option[Boolean] = soundex.compare(string1, string2)
}
