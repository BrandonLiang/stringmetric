#stringmetric [![Build Status](https://travis-ci.org/rockymadden/stringmetric.png?branch=master)](http://travis-ci.org/rockymadden/stringmetric)
String metrics and phonetic algorithms implemented in Scala. The library provides facilities to perform approximate string matching, measurement of string similarity/distance, indexing by word pronunciation, and sounds-like comparisions. In addition to the core library, each metric and algorithm has a command line interface. Heavy emphasis is placed on unit testing and performance (verified via microbenchmark suites).

## Metrics and Algorithms
* __[Dice / Sorensen](http://en.wikipedia.org/wiki/Dice%27s_coefficient)__ (Similarity metric)
* __[Hamming](http://en.wikipedia.org/wiki/Hamming_distance)__ (Similarity metric)
* __[Jaro](http://en.wikipedia.org/wiki/Jaro-Winkler_distance)__ (Similarity metric)
* __[Jaro-Winkler](http://en.wikipedia.org/wiki/Jaro-Winkler_distance)__ (Similarity metric)
* __[Levenshtein](http://en.wikipedia.org/wiki/Levenshtein_distance)__ (Similarity metric)
* __[Metaphone](http://en.wikipedia.org/wiki/Metaphone)__ (Phonetic metric and algorithm)
* __[N-Gram](http://en.wikipedia.org/wiki/N-gram)__ (Similarity metric and algorithm)
* __[NYSIIS](http://en.wikipedia.org/wiki/New_York_State_Identification_and_Intelligence_System)__ (Phonetic metric and algorithm)
* __[Ratcliff / Obershelp](http://xlinux.nist.gov/dads/HTML/ratcliffObershelp.html)__ (Similarity metric)
* __[Refined NYSIIS](http://www.markcrocker.com/rexxtipsntricks/rxtt28.2.0482.html)__ (Phonetic metric and algorithm)
* __[Refined Soundex](http://ntz-develop.blogspot.com/2011/03/phonetic-algorithms.html)__ (Phonetic metric and algorithm)
* __[Soundex](http://en.wikipedia.org/wiki/Soundex)__ (Phonetic metric and algorithm)
* __Weighted Levenshtein__ (Similarity metric)

## Similarity package
Useful for approximate string matching and measurement of string distance. Most metrics calculate the similarity of two strings as a double with a value between 0 and 1. A value of 0 being completely different and a value of 1 being completely similar.

__Dice / Sorensen Metric:__
```scala
println(DiceSorensenMetric.compare("night", "nacht"))
println(DiceSorensenMetric.compare("context", "contact"))
```

Output:
```shell
0.6
0.7142857142857143
```

__Hamming Metric:__
```scala
println(HammingMetric.compare("toned", "roses"))
println(HammingMetric.compare("1011101", "1001001"))
```

Output: _(Note the exception of integers, rather than doubles, being returned)_
```shell
3
2
```

__Jaro Metric:__
```scala
println(JaroMetric.compare("dwayne", "duane"))
println(JaroMetric.compare("jones", "johnson"))
println(JaroMetric.compare("fvie", "ten"))
```

Output:
```shell
0.8222222222222223
0.7904761904761904
0
```

__Jaro-Winkler Metric:__
```scala
println(JaroWinklerMetric.compare("dwayne", "duane"))
println(JaroWinklerMetric.compare("jones", "johnson"))
println(JaroWinklerMetric.compare("fvie", "ten"))
```

Output:
```shell
0.8400000000000001
0.8323809523809523
0
```

__Levenshtein Metric:__
```scala
println(LevenshteinMetric.compare("sitting", "kitten"))
println(LevenshteinMetric.compare("cake", "drake"))
```

Output: _(Note the exception of integers, rather than doubles, being returned)_
```shell
3
2
```

__N-Gram Metric:__ _(Note you must specify the size of the n-gram you wish to use)_
```scala
println(NGramMetric.compare("night", "nacht")(1))
println(NGramMetric.compare("night", "nacht")(2))
println(NGramMetric.compare("context", "contact")(2))
```

Output:
```shell
0.6
0.25
0.5
```

__N-Gram Algorithm:__ _(Note you must specify the size of the n-gram you wish to use)_
```scala
println(NGramAlgorithm.compute("abcdefghijklmnopqrstuvwxyz")(1))
println(NGramAlgorithm.compute("abcdefghijklmnopqrstuvwxyz")(2))
println(NGramAlgorithm.compute("abcdefghijklmnopqrstuvwxyz")(3))
```

Output:
```shell
Array("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
Array("ab", "bc", "cd", "de", "ef", "fg", "gh", "hi", "ij", "jk", "kl", "lm", "mn", "no", "op", "pq", "qr", "rs", "st", "tu", "uv", "vw", "wx", "xy", "yz")
Array("abc", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij", "ijk", "jkl", "klm", "lmn", "mno", "nop", "opq", "pqr", "qrs", "rst", "stu", "tuv", "uvw", "vwx", "wxy", "xyz")
```

## Testing
```shell
$ gradle :stringmetric-core:test
```

```shell
$ gradle :stringmetric-cli:test
```

## Building
```shell
$ gradle :stringmetric-core:jar
```

```shell
$ gradle :stringmetric-cli:tar
```

## Depending Upon
Available on the [Maven Central Repository](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.rockymadden.stringmetric%22):

* __groupId__: com.rockymadden.stringmetric
* __artifactId__: stringmetric-core
* __artifactId__: stringmetric-cli

## Requirements
* Scala 2.10.x
* Gradle 1.x

## Versioning
[Semantic Versioning v2.0](http://semver.org/)

## License
[Apache License v2.0](http://www.apache.org/licenses/LICENSE-2.0)

## Todo
* SmithWaterman
* MongeElkan
* NeedlemanWunch
* Jaccard
* Double Metaphone
* Memoization decorator

## Questions and Comments
Reach me at <stringmetric@rockymadden.com>.
