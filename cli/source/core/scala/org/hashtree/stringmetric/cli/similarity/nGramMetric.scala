package org.hashtree.stringmetric.cli.similarity

import org.hashtree.stringmetric.cli._
import org.hashtree.stringmetric.similarity.NGramMetric

/**
 * The nGramMetric [[org.hashtree.stringmetric.cli.Command]]. Compares the similarity of two strings using an N-Gram
 * similarity index.
 */
object nGramMetric extends Command {
	override def main(args: Array[String]): Unit = {
		val options = OptionMap(args)

		try {
			// Help.
			if (options.contains('h) || options.contains('help)) {
				help()
				exit(options)
			// Execute.
			} else if (options.contains('dashless) && (OptionMapArray(options('dashless)): Array[String]).length == 2
				&& options.contains('n) && OptionMapInt(options('n)).isDefined
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
			"Compares the similarity of two strings using an N-Gram similarity index." + ls + ls +
			"Syntax:" + ls +
			tab + "nGramMetric [Options] string1 string2..." + ls + ls +
			"Options:" + ls +
			tab + "-h, --help" + ls +
			tab + tab + "Outputs description, syntax, and options." +
			tab + "--n" + ls +
			tab + tab + "The n."
		)
	}

	override def execute(options: OptionMap): Unit = {
		val strings = OptionMapArray(options('dashless))
		val n = OptionMapInt(options('n))

		println(
			NGramMetric.compare(
				strings(0),
				strings(1)
			)(n).getOrElse("not comparable")
		)
	}
}