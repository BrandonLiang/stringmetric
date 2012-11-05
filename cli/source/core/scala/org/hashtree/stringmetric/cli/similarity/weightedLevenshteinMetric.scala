package org.hashtree.stringmetric.cli.similarity

import org.hashtree.stringmetric.StringFilterDelegate
import org.hashtree.stringmetric.cli._
import org.hashtree.stringmetric.filter.AsciiLetterCaseStringFilter
import org.hashtree.stringmetric.similarity.WeightedLevenshteinMetric
import scala.math.BigDecimal

/**
 * The weightedLevenshteinMetric [[org.hashtree.stringmetric.cli.Command]]. Compares the number of characters that two
 * strings are different from one another via insertion, deletion, and substitution. Allows the invoker to indicate
 * the weight each operation takes.
 */
object weightedLevenshteinMetric extends Command {
	override def main(args: Array[String]): Unit = {
		val options = OptionMapUtility.toOptionMap(args)

		try {
			// Help.
			if (options.contains('h) || options.contains('help)) {
				help()
				exit(options)
			// Execute.
			} else if (options.contains('dashless) && options('dashless).count(_ == ' ') == 1 &&
				options.contains('deleteWeight) && ParseUtility.parseDouble(options('deleteWeight)).isDefined &&
				options.contains('insertWeight) && ParseUtility.parseDouble(options('insertWeight)).isDefined &&
				options.contains('substituteWeight) && ParseUtility.parseDouble(options('substituteWeight)).isDefined
			) {
				execute(options)
				exit(options)
			// Invalid syntax.
			} else throw new IllegalArgumentException("Expected valid syntax. See --help.")
		} catch {
			case e => error(e, options)
		}
	}

	override def help(): Unit = {
		val ls = sys.props("line.separator")
		val tab = "  "

		println(
			"Compares the number of characters that two strings are different from one another via insertion, deletion, " +
			"and substitution. Allows the invoker to indicate the weight each operation takes." + ls + ls +
			"Syntax:" + ls +
			tab + "weightedLevenshteinMetric [Options] --deleteWeight=[double] --insertWeight=[double] --substituteWeight=[double] string1 string2..." + ls + ls +
			"Options:" + ls +
			tab + "--deleteWeight" + ls +
			tab + tab + "The weight given to delete operations." +
			tab + "-h, --help" + ls +
			tab + tab + "Outputs description, syntax, and options." +
			tab + "--insertWeight" + ls +
			tab + tab + "The weight given to insert operations." +
			tab + "--substituteWeight" + ls +
			tab + tab + "The weight given to substitute operations."
		)
	}

	override def execute(options: OptionMap): Unit = {
		val strings = options('dashless).split(" ")
		val weights = Tuple3[BigDecimal, BigDecimal, BigDecimal](
			ParseUtility.parseBigDecimal(options('deleteWeight)).get,
			ParseUtility.parseBigDecimal(options('insertWeight)).get,
			ParseUtility.parseBigDecimal(options('substituteWeight)).get
		)

		println(
			WeightedLevenshteinMetric.compare(
				strings(0),
				strings(1)
			)(weights)(new StringFilterDelegate with AsciiLetterCaseStringFilter).getOrElse("not comparable").toString
		)
	}
}