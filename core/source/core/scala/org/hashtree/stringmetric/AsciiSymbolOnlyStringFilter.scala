package org.hashtree.stringmetric

/** A decorator [[org.hashtree.stringmetric.StringFilter]]. Ensures only ASCII symbols matter. */
trait AsciiSymbolOnlyStringFilter extends StringFilter {
	abstract override def filter(charArray: Array[Char]): Array[Char] =
		super.filter(
			charArray.filter { c =>
				((c >= 32 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126))
			}
		)

	abstract override def filter(string: String): String = filter(string.toCharArray).mkString
}