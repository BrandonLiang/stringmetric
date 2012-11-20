package org.hashtree.stringmetric.phonetic

import org.hashtree.stringmetric.{ FilterableStringAlgorithm, StringAlgorithm, StringFilter }
import org.hashtree.stringmetric.filter.StringFilterDelegate
import scala.annotation.tailrec

/** An implementation of the NYSIIS [[org.hashtree.stringmetric.StringAlgorithm]]. */
object NysiisAlgorithm extends StringAlgorithm with FilterableStringAlgorithm {
	type ComputeReturn = String

	override def compute(charArray: Array[Char])(implicit stringFilter: StringFilter): Option[Array[Char]] = {
		val ca = stringFilter.filter(charArray)

		if (ca.length == 0 || !Alphabet.is(ca.head)) None
		else {
			val tr = transcodeRight(ca.map(_.toLower))
			val tl = transcodeLeft(tr._1)
			val t =
				if (tl._2.length == 0) tl._1 ++ tr._2
				else
					tl._1 ++ transcodeCenter(Array.empty[Char], tl._2.head, if (tl._2.length > 1) tl._2.tail else Array.empty[Char], Array.empty[Char]) ++ tr._2

			if (t.length == 1) Some(t)
			else Some(t.head +: deduplicate(cleanTerminal(cleanLast(t.tail))))
		}
	}

	override def compute(string: String)(implicit stringFilter: StringFilter): Option[ComputeReturn] =
		compute(stringFilter.filter(string.toCharArray))(new StringFilterDelegate).map(_.mkString)

	private[this] def cleanLast(ca: Array[Char]) =
		if (ca.length == 0) ca
		else if(ca.last == 'a' || ca.last == 's') ca.dropRight(ca.reverseIterator.takeWhile(c => c == 'a' || c == 's').length)
		else ca

	private[this] def cleanTerminal(ca: Array[Char]) =
		if (ca.length >= 2 && ca.last == 'y' && ca(ca.length - 2) == 'a') ca.dropRight(2) :+ 'y'
		else ca

	private[this] def deduplicate(ca: Array[Char]) =
		if (ca.length <= 1) ca
		else ca.sliding(2).withFilter(a => a(0) != a(1)).map(a => a(0)).toArray[Char] :+ ca.last

	@tailrec
	private[this] def transcodeCenter(l: Array[Char], c: Char, r: Array[Char], o: Array[Char]): Array[Char] = {
		if (c == '\0' && r.length == 0) o
		else {
			val shift = (d: Int, ca: Array[Char]) => {
				val sa = r.splitAt(d - 1)

				(
					if (sa._1.length > 0) (l :+ c) ++ sa._1 else l :+ c,
					if (sa._2.length > 0) sa._2.head else '\0',
					if (sa._2.length > 1) sa._2.tail else Array.empty[Char],
					ca
				)
			}
			val t = {
				c match {
					case 'a' | 'i' | 'o' | 'u' => shift(1, o :+ 'a')
					case 'b' | 'c' | 'd' | 'f' | 'g' | 'j' | 'l' | 'n' | 'r' | 't' | 'v' | 'x' | 'y' => shift(1, o :+ c)
					case 'e' =>
						if (r.length >= 1 && r.head == 'v') shift(2, o ++ Array('a', 'f'))
						else shift(1, o :+ 'a')
					case 'h' =>
						if (l.length >= 1 && (!Alphabet.isVowel(l.last) || (r.length >= 1 && !Alphabet.isVowel(r.head)))) shift(1, o)
						else shift(1, o :+ c)
					case 'k' => if (r.length >= 1 && r.head == 'n') shift(2, o :+ 'n') else shift(1, o :+ 'c')
					case 'm' => shift(1, o :+ 'n')
					case 'p' => if (r.length >= 1 && r.head == 'h') shift(2, o :+ 'f') else shift(1, o :+ c)
					case 'q' => shift(1, o :+ 'g')
					case 's' =>
						if (r.length >= 2 && r.head == 'c' && r(1) == 'h') shift(3, o :+ c)
						else shift(1, o :+ c)
					case 'w' =>
						if (l.length >= 1 && Alphabet.isVowel(l.last)) shift(1, o)
						else shift(1, o :+ c)
					case 'z' => shift(1, o :+ 's')
					case _ => shift(1, o)
				}
			}

			transcodeCenter(t._1, t._2, t._3, t._4)
		}
	}

	private[this] def transcodeLeft(ca: Array[Char]) = {
		if (ca.length == 0) (Array.empty[Char], ca)
		else {
			lazy val tr2 = ca.takeRight(ca.length - 2)
			lazy val tr3 = ca.takeRight(ca.length - 3)

			ca.head match {
				case 'k' if (ca.length >= 2 && ca(1) == 'n') => (Array('n', 'n'), tr2)
				case 'k' => (Array('c'), ca.tail)
				case 'm' if (ca.length >= 3 && (ca(1) == 'a' && ca(2) == 'c')) => (Array('m', 'c'), tr3)
				case 'p' if (ca.length >= 2 && (ca(1) == 'h' || ca(1) == 'f')) => (Array('f', 'f'), tr2)
				case 's' if (ca.length >= 3 && (ca(1) == 'c' && ca(2) == 'h')) => (Array('s', 's'), tr3)
				case _ => (Array(ca.head), ca.tail)
			}
		}
	}

	private[this] def transcodeRight(ca: Array[Char]) = {
		if (ca.length >= 2) {
			val l = ca(ca.length - 1)
			val lm1 = ca(ca.length - 2)
			lazy val t2 = ca.take(ca.length - 2)

			l match {
				case 'd' if (lm1 == 'n' || lm1 == 'r') => (t2, Array('d'))
				case 'e' if (lm1 == 'e' || lm1 == 'i') => (t2, Array('y'))
				case 't' if (lm1 == 'd' || lm1 == 'n' || lm1 == 'r') => (t2, Array('d'))
				case _ => (ca, Array.empty[Char])
			}
		} else (ca, Array.empty[Char])
	}
}