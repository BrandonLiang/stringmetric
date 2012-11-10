package org.hashtree.stringmetric.similarity

import org.hashtree.stringmetric.{ FilterableStringMetric, StringFilter, StringMetric, StringFilterDelegate }

/**
 * An implementation of the Jaro-Winkler [[org.hashtree.stringmetric.StringMetric]]. One differing detail in this
 * implementation is that if a character is matched in string2, it cannot be matched upon again. This results in a more
 * penalized distance in these scenarios (e.g. comparing henka and henkan distance is 0.9666 versus the typical 0.9722).
 */
object JaroWinklerMetric extends StringMetric with FilterableStringMetric {
	type CompareReturn = Double

	override def compare(charArray1: Array[Char], charArray2: Array[Char])(implicit stringFilter: StringFilter): Option[CompareReturn] = {
		val ca1 = stringFilter.filter(charArray1)
		val ca2 = stringFilter.filter(charArray2)

		JaroMetric.compare(ca1, ca2)(new StringFilterDelegate) match {
			case Some(0d) => Some(0d)
			case Some(1d) => Some(1d)
			case Some(jaro) => {
				val prefix = ca1.zip(ca2).takeWhile(t => t._1 == t._2).map(_._1)

				Some(jaro + ((if (prefix.length <= 4) prefix.length else 4) * 0.1d * (1 - jaro)))
			}
			case None => None
		}
	}

	override def compare(string1: String, string2: String)(implicit stringFilter: StringFilter): Option[CompareReturn] =
		compare(
			stringFilter.filter(string1.toCharArray),
			stringFilter.filter(string2.toCharArray)
		)(new StringFilterDelegate)
}