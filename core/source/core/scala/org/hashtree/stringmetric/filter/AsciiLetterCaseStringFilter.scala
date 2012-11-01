package org.hashtree.stringmetric.filter

import org.hashtree.stringmetric.StringFilter

/** A decorator [[org.hashtree.stringmetric.StringFilter]]. Ensures ASCII letter case-sensitivity does not matter. */
trait AsciiLetterCaseStringFilter extends StringFilter {
	abstract override def filter(charArray: Array[Char]): Array[Char] =
		super.filter(
			charArray.map { c =>
				if (c >= 65 && c <= 90) (c + 32).toChar else c
			}
		)

	abstract override def filter(string: String): String = filter(string.toCharArray).mkString
}