package com.rockymadden.stringmetric.cli.similarity

import com.rockymadden.stringmetric.cli._
import com.rockymadden.stringmetric.similarity.OverlapMetric

/**
 * The overlapMetric [[com.rockymadden.stringmetric.cli.Command]]. Compares the similarity of two strings using the
 * overlap coefficient.
 */
object overlapMetric extends Command {
	override def main(args: Array[String]): Unit = {
		val opts: OptionMap = args

		try
			if (opts.contains('h) || opts.contains('help)) {
				help()
				exit(opts)
			} else if (opts.contains('dashless) && (opts('dashless): Array[String]).length == 2
				&& opts.contains('n) && (opts('n): Int) >= 1) {

				execute(opts)
				exit(opts)
			} else throw new IllegalArgumentException("Expected valid syntax. See --help.")
		catch { case e: Throwable => error(e, opts) }
	}

	override def help(): Unit = {
		val ls = sys.props("line.separator")
		val tab = "  "

		println(
			"Compares the similarity of two strings using the overlap coefficient." + ls + ls +
			"Syntax:" + ls +
			tab + "overlapMetric [Options] string1 string2..." + ls + ls +
			"Options:" + ls +
			tab + "-h, --help" + ls +
			tab + tab + "Outputs description, syntax, and opts." +
			tab + "--n" + ls +
			tab + tab + "The n-gram size."
		)
	}

	override def execute(opts: OptionMap): Unit = {
		val strings: Array[String] = opts('dashless)
		val n: Int = opts('n)

		println(OverlapMetric.compare(strings(0), strings(1))(n).getOrElse("not comparable"))
	}
}
