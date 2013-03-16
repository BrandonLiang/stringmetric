package com.rockymadden.stringmetric.cli.phonetic

import com.rockymadden.stringmetric.cli._
import com.rockymadden.stringmetric.phonetic.RefinedSoundexAlgorithm

/**
 * The refinedSoundexAlgorithm [[com.rockymadden.stringmetric.cli.Command]]. Returns the phonetic representation of the
 * passed string, per the refined Soundex algorithm.
 */
object refinedSoundexAlgorithm extends Command {
	override def main(args: Array[String]): Unit = {
		val opts: OptionMap = args

		try
			if (opts.contains('h) || opts.contains('help)) {
				help()
				exit(opts)
			} else if (opts.contains('dashless) && (opts('dashless): Array[String]).length == 1) {
				execute(opts)
				exit(opts)
			} else throw new IllegalArgumentException("Expected valid syntax. See --help.")
		catch { case e: Throwable => error(e, opts) }
	}

	override def help(): Unit = {
		val ls = sys.props("line.separator")
		val tab = "  "

		println(
			"Returns the phonetic representation of the passed string, per the refined Soundex algorithm." + ls + ls +
			"Syntax:" + ls +
			tab + "refinedSoundexAlgorithm [Options] string..." + ls + ls +
			"Options:" + ls +
			tab + "-h, --help" + ls +
			tab + tab + "Outputs description, syntax, and opts."
		)
	}

	override def execute(opts: OptionMap): Unit =
		println(RefinedSoundexAlgorithm.compute(opts('dashless)).getOrElse("not computable"))
}
