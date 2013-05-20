package com.rockymadden.stringmetric.similarity

import com.rockymadden.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class JaroMetricSpec extends ScalaTest {
	import JaroMetricSpec.Metric

	"JaroMetric" should provide {
		"compare method" when passed {
			"empty arguments" should returns {
				"None" in {
					Metric.compare("", "").isDefined should be (false)
					Metric.compare("abc", "").isDefined should be (false)
					Metric.compare("", "xyz").isDefined should be (false)
				}
			}
			"equal arguments" should returns {
				"1" in {
					Metric.compare("a", "a").get should be (1)
					Metric.compare("abc", "abc").get should be (1)
					Metric.compare("123", "123").get should be (1)
				}
			}
			"unequal arguments" should returns {
				"0" in {
					Metric.compare("abc", "xyz").get should be (0)
					Metric.compare("123", "456").get should be (0)
				}
			}
			"valid arguments" should returns {
				"Double indicating distance" in {
					Metric.compare("aa", "a").get should be (0.8333333333333334)
					Metric.compare("a", "aa").get should be (0.8333333333333334)
					Metric.compare("veryveryverylong", "v").get should be (0.6875)
					Metric.compare("v", "veryveryverylong").get should be (0.6875)
					Metric.compare("martha", "marhta").get should be (0.9444444444444445)
					Metric.compare("dwayne", "duane").get should be (0.8222222222222223)
					Metric.compare("dixon", "dicksonx").get should be (0.7666666666666666)
					Metric.compare("abcvwxyz", "cabvwxyz").get should be (0.9583333333333334)
					Metric.compare("jones", "johnson").get should be (0.7904761904761904)
					Metric.compare("henka", "henkan").get should be (0.9444444444444445)
					Metric.compare("fvie", "ten").get should be (0)

					Metric.compare("zac ephron", "zac efron").get should be >
						Metric.compare("zac ephron", "kai ephron").get
					Metric.compare("brittney spears", "britney spears").get should be >
						Metric.compare("brittney spears", "brittney startzman").get
				}
			}
		}
	}
	"JaroMetric companion object" should provide {
		"pass-through compare method" should returns {
			"same value as class" in {
				JaroMetric.compare("fvie", "ten").get should be (0)
			}
		}
	}
}

object JaroMetricSpec {
	private final val Metric = JaroMetric()
}
