package com.rockymadden.stringmetric.phonetic

import com.rockymadden.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class NysiisAlgorithmSpec extends ScalaTest {
	import NysiisAlgorithmSpec.Algorithm

	"NysiisAlgorithm" should provide {
		"compute method" when passed {
			"empty argument" should returns {
				"None" in {
					Algorithm.compute("").isDefined should be (false)
				}
			}
			"non-phonetic argument" should returns {
				"None" in {
					Algorithm.compute("123").isDefined should be (false)
				}
			}
			"phonetic argument" should returns {
				"Some" in {
					// a
					Algorithm.compute("a").get should equal ("a")
					Algorithm.compute("aa").get should equal ("a")

					// b
					Algorithm.compute("b").get should equal ("b")
					Algorithm.compute("bb").get should equal ("bb")

					// c
					Algorithm.compute("c").get should equal ("c")
					Algorithm.compute("cc").get should equal ("cc")

					// d
					Algorithm.compute("d").get should equal ("d")
					Algorithm.compute("dd").get should equal ("dd")

					// e
					Algorithm.compute("e").get should equal ("e")
					Algorithm.compute("ee").get should equal ("y")

					// f
					Algorithm.compute("f").get should equal ("f")
					Algorithm.compute("ff").get should equal ("ff")

					// g
					Algorithm.compute("g").get should equal ("g")
					Algorithm.compute("gg").get should equal ("gg")

					// h
					Algorithm.compute("h").get should equal ("h")
					Algorithm.compute("hh").get should equal ("hh")

					// i
					Algorithm.compute("i").get should equal ("i")
					Algorithm.compute("ii").get should equal ("i")

					// j
					Algorithm.compute("j").get should equal ("j")
					Algorithm.compute("jj").get should equal ("jj")

					// k
					Algorithm.compute("k").get should equal ("c")
					Algorithm.compute("kk").get should equal ("cc")

					// l
					Algorithm.compute("l").get should equal ("l")
					Algorithm.compute("ll").get should equal ("ll")

					// m
					Algorithm.compute("m").get should equal ("m")
					Algorithm.compute("mm").get should equal ("mn")

					// n
					Algorithm.compute("n").get should equal ("n")
					Algorithm.compute("nn").get should equal ("nn")

					// o
					Algorithm.compute("o").get should equal ("o")
					Algorithm.compute("oo").get should equal ("o")

					// p
					Algorithm.compute("p").get should equal ("p")
					Algorithm.compute("pp").get should equal ("pp")

					// q
					Algorithm.compute("q").get should equal ("q")
					Algorithm.compute("qq").get should equal ("qg")

					// r
					Algorithm.compute("r").get should equal ("r")
					Algorithm.compute("rr").get should equal ("rr")

					// s
					Algorithm.compute("s").get should equal ("s")
					Algorithm.compute("ss").get should equal ("s")

					// t
					Algorithm.compute("t").get should equal ("t")
					Algorithm.compute("tt").get should equal ("tt")

					// u
					Algorithm.compute("u").get should equal ("u")
					Algorithm.compute("uu").get should equal ("u")

					// v
					Algorithm.compute("v").get should equal ("v")
					Algorithm.compute("vv").get should equal ("vv")

					// w
					Algorithm.compute("w").get should equal ("w")
					Algorithm.compute("ww").get should equal ("ww")

					// x
					Algorithm.compute("x").get should equal ("x")
					Algorithm.compute("xx").get should equal ("xx")

					// y
					Algorithm.compute("y").get should equal ("y")
					Algorithm.compute("yy").get should equal ("yy")

					// z
					Algorithm.compute("z").get should equal ("z")
					Algorithm.compute("zz").get should equal ("z")

					// Head cases.
					Algorithm.compute("mac").get should equal ("mc")
					Algorithm.compute("kn").get should equal ("nn")
					Algorithm.compute("k").get should equal ("c")
					Algorithm.compute("ph").get should equal ("ff")
					Algorithm.compute("pf").get should equal ("ff")
					Algorithm.compute("sch").get should equal ("s") // dropby wrongly says ss

					// Last cases.
					Algorithm.compute("ee").get should equal ("y")
					Algorithm.compute("ie").get should equal ("y")
					Algorithm.compute("dt").get should equal ("d")
					Algorithm.compute("rt").get should equal ("d")
					Algorithm.compute("rd").get should equal ("d")
					Algorithm.compute("nt").get should equal ("d")
					Algorithm.compute("nd").get should equal ("d")

					// Core cases.
					Algorithm.compute("eev").get should equal ("eaf")
					Algorithm.compute("zev").get should equal ("zaf")
					Algorithm.compute("kkn").get should equal ("cn")
					Algorithm.compute("sschn").get should equal ("ssn")
					Algorithm.compute("pph").get should equal ("pf")

					// Miscellaneous.
					Algorithm.compute("macdonald").get should equal ("mcdanald")
					Algorithm.compute("phone").get should equal ("ffan")
					Algorithm.compute("aggregate").get should equal ("agragat")
					Algorithm.compute("accuracy").get should equal ("acaracy")
					Algorithm.compute("encyclopedia").get should equal ("encyclapad")
					Algorithm.compute("honorificabilitudinitatibus").get should equal ("hanarafacabalatadanatatab")
					Algorithm.compute("antidisestablishmentarianism").get should equal ("antadasastablasnantaranasn")

					// Dropby.
					Algorithm.compute("macintosh").get should equal ("mcant")
					Algorithm.compute("knuth").get should equal ("nnat")
					Algorithm.compute("koehn").get should equal ("can") // dropby wrongly says c
					Algorithm.compute("phillipson").get should equal ("ffalapsan")
					Algorithm.compute("pfeister").get should equal ("ffastar")
					Algorithm.compute("schoenhoeft").get should equal ("ssanaft")
					Algorithm.compute("mckee").get should equal ("mcy")
					Algorithm.compute("heitschmedt").get should equal ("hatsnad")
					Algorithm.compute("bart").get should equal ("bad")
					Algorithm.compute("hurd").get should equal ("had")
					Algorithm.compute("hunt").get should equal ("had")
					Algorithm.compute("westerlund").get should equal ("wastarlad")
					Algorithm.compute("casstevens").get should equal ("castafan")
					Algorithm.compute("vasquez").get should equal ("vasg")
					Algorithm.compute("frazier").get should equal ("frasar")
					Algorithm.compute("bowman").get should equal ("banan")
					Algorithm.compute("mcknight").get should equal ("mcnagt")
					Algorithm.compute("rickert").get should equal ("racad")
					Algorithm.compute("deutsch").get should equal ("dat") // dropby wrongly says dats
					Algorithm.compute("westphal").get should equal ("wastfal")
					Algorithm.compute("shriver").get should equal ("shravar")
					Algorithm.compute("kuhl").get should equal ("cal") // dropby wrongly says c
					Algorithm.compute("rawson").get should equal ("rasan")
					Algorithm.compute("jiles").get should equal ("jal")
					Algorithm.compute("carraway").get should equal ("caray")
					Algorithm.compute("yamada").get should equal ("yanad")
				}
			}
		}
	}
	"NysiisAlgorithm companion object" should provide {
		"pass-through compute method" should returns {
			"same value as class" in {
				NysiisAlgorithm.compute("macdonald").get should equal ("mcdanald")
			}
		}
	}
}

object NysiisAlgorithmSpec {
	final private val Algorithm = NysiisAlgorithm()
}
