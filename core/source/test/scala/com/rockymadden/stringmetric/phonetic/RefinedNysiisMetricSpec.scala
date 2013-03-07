package com.rockymadden.stringmetric.phonetic

import com.rockymadden.stringmetric.ScalaTest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
final class RefinedNysiisMetricSpec extends ScalaTest {
	import RefinedNysiisMetricSpec.Metric

	"RefinedNysiisMetric" should provide {
		"compare method" when passed {
			"empty arguments" should returns {
				"None" in {
					Metric.compare("", "").isDefined should be (false)
					Metric.compare("abc", "").isDefined should be (false)
					Metric.compare("", "xyz").isDefined should be (false)
				}
			}
			"non-phonetic arguments" should returns {
				"None" in {
					Metric.compare("123", "123").isDefined should be (false)
					Metric.compare("123", "").isDefined should be (false)
					Metric.compare("", "123").isDefined should be (false)
				}
			}
			"phonetically similar arguments" should returns {
				"Boolean indicating true" in {
					Metric.compare("ham", "hum").get should be (true)
				}
			}
			"phonetically dissimilar arguments" should returns {
				"Boolean indicating false" in {
					Metric.compare("dumb", "gum").get should be (false)
				}
			}
		}
	}
}

object RefinedNysiisMetricSpec {
	final private val Metric = RefinedNysiisMetric()
}
