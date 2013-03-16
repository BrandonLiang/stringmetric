package com.rockymadden.stringmetric.cli.phonetic

import com.rockymadden.stringmetric.cli._
import com.rockymadden.stringmetric.phonetic.NysiisAlgorithm

/**
 * The nysiisAlgorithm [[com.rockymadden.stringmetric.cli.Command]]. Returns the phonetic representation of the passed
 * string, per the NYSIIS algorithm.
 */
object nysiisAlgorithm extends Command {
	override def main(args: Array[String]): Unit = {
		val options: OptionMap = args

		try
			if (options.contains('h) || options.contains('help)) {
				help()
				exit(options)
			} else if (options.contains('dashless) && (options('dashless): Array[String]).length == 1) {
				execute(options)
				exit(options)
			} else throw new IllegalArgumentException("Expected valid syntax. See --help.")
		catch { case e: Throwable => error(e, options) }
	}

	override def help(): Unit = {
		val ls = sys.props("line.separator")
		val tab = "  "

		println(
			"Returns the phonetic representation of the passed string, per the NYSIIS algorithm." + ls + ls +
			"Syntax:" + ls +
			tab + "nysiisAlgorithm [Options] string..." + ls + ls +
			"Options:" + ls +
			tab + "-h, --help" + ls +
			tab + tab + "Outputs description, syntax, and options."
		)
	}

	override def execute(options: OptionMap): Unit =
		println(NysiisAlgorithm.compute(options('dashless)).getOrElse("not computable"))
}
