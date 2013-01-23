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

## Installation
Available on the [Maven Central Repository](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.rockymadden.stringmetric%22):

* __groupId__: com.rockymadden.stringmetric
* __artifactId__: stringmetric-core
* __artifactId__: stringmetric-cli

## Similarity package
Useful for approximate string matching and measurement of string distance. Most metrics calculate the similarity of two strings as a double with a value between 0 and 1. A value of 0 being completely different and a value of 1 being completely similar.

__Dice / Sorensen Metric:__
```scala
println(DiceSorensenMetric.compare("night", "nacht"))
println(DiceSorensenMetric.compare("context", "contact"))
```

Output:
```
0.6
0.7142857142857143
```

__Hamming Metric:__
```scala
println(HammingMetric.compare("toned", "roses"))
println(HammingMetric.compare("1011101", "1001001"))
```

Output: _(Note the exception of integers, rather than doubles, being returned.)_
```
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
```
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
```
0.8400000000000001
0.8323809523809523
0
```

__Levenshtein Metric:__
```scala
println(LevenshteinMetric.compare("sitting", "kitten"))
println(LevenshteinMetric.compare("cake", "drake"))
```

Output: _(Note the exception of integers, rather than doubles, being returned.)_
```
3
2
```

__N-Gram Metric:__ _(Note you must specify the size of the n-gram you wish to use. This can be done implicitly.)_
```scala
println(NGramMetric.compare("night", "nacht")(1))
println(NGramMetric.compare("night", "nacht")(2))
println(NGramMetric.compare("context", "contact")(2))
```

Output:
```
0.6
0.25
0.5
```

__N-Gram Algorithm:__ _(Note you must specify the size of the n-gram you wish to use. This can be done implicitly.)_
```scala
println(NGramAlgorithm.compute("abcdefghijklmnopqrstuvwxyz")(1))
println(NGramAlgorithm.compute("abcdefghijklmnopqrstuvwxyz")(2))
println(NGramAlgorithm.compute("abcdefghijklmnopqrstuvwxyz")(3))
```

Output:
```
Array("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
Array("ab", "bc", "cd", "de", "ef", "fg", "gh", "hi", "ij", "jk", "kl", "lm", "mn", "no", "op", "pq", "qr", "rs", "st", "tu", "uv", "vw", "wx", "xy", "yz")
Array("abc", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij", "ijk", "jkl", "klm", "lmn", "mno", "nop", "opq", "pqr", "qrs", "rst", "stu", "tuv", "uvw", "vwx", "wxy", "xyz")
```

__Ratcliff/Obershelp Metric:__
```scala
println(RatcliffObershelpMetric.compare("aleksander", "alexandre"))
println(RatcliffObershelpMetric.compare("pennsylvania", "pencilvaneya"))
```

Output:
```
0.7368421052631579
0.6666666666666666
```

__Weighted Levenshtein Metric:__ _(Note you must specify the weight of each operation. Delete, insert, and then substitute. This can be done implicitly.)_
```scala
println(WeightedLevenshteinMetric.compare("book", "back")(10, 0.1, 1))
println(WeightedLevenshteinMetric.compare("hosp", "hospital")(10, 0.1, 1))
println(WeightedLevenshteinMetric.compare("hospital", "hosp")(10, 0.1, 1))
```

Output: _(Note that while a double is returned, it can be outside the range of 0 to 1, based upon the weights used.)_
```
2
0.4
40
```

## Phonetic package
Useful for indexing by word pronunciation and performing sounds-like comparisions. All metrics return a boolean value indicating if the two strings sound the same, per the algorithm used. All metrics have a algorithm counterpart which provide the means to perform indexing by word pronunciation.

__Metaphone Metric:__
```scala
println(MetaphoneMetric.compare("merci", "mercy"))
println(MetaphoneMetric.compare("dumb", "gum"))
```

Output:
```
true
false
```

__Metaphone Algorithm:__
```scala
println(MetaphoneAlgorithm.compute("dumb"))
println(MetaphoneAlgorithm.compute("knuth"))
```

Output:
```
tm
n0
```

__NYSIIS Metric:__
```scala
println(NysiisMetric.compare("ham", "hum"))
println(NysiisMetric.compare("dumb", "gum"))
```

Output:
```
true
false
```

__NYSIIS Algorithm:__
```scala
println(NysiisAlgorithm.compute("macintosh"))
println(NysiisAlgorithm.compute("knuth"))
```

Output:
```
mcant
nnat
```

__Refined NYSIIS Metric:__
```scala
println(RefinedNysiisMetric.compare("ham", "hum"))
println(RefinedNysiisMetric.compare("dumb", "gum"))
```

Output:
```
true
false
```

__Refined NYSIIS Algorithm:__
```scala
println(RefinedNysiisAlgorithm.compute("macintosh"))
println(RefinedNysiisAlgorithm.compute("westerlund"))
```

Output:
```
mcantas
wastarlad
```

__Refined Soundex Metric:__
```scala
println(RefinedSoundexMetric.compare("robert", "rupert"))
println(RefinedSoundexMetric.compare("robert", "rubin"))
```

Output:
```
true
false
```

__Refined Soundex Algorithm:__
```scala
println(RefinedSoundexAlgorithm.compute("hairs"))
println(RefinedSoundexAlgorithm.compute("lambert"))
```

Output:
```
h093
l7081096
```

__Soundex Metric:__
```scala
println(SoundexMetric.compare("robert", "rupert"))
println(SoundexMetric.compare("robert", "rubin"))
```

Output:
```
true
false
```

__Soundex Algorithm:__
```scala
println(SoundexAlgorithm.compute("rupert"))
println(SoundexAlgorithm.compute("lukasiewicz"))
```

Output:
```
r163
l222
```

## Filter package
Useful for filtering strings prior to evaluation (e.g. ignore case, ignore non-alpha, ignore spaces). Filters can be used implicitly.

Basic example with no filtering:
```scala
JaroWinklerMetric.compare("string1", "string2")
```

Basic example with single filter:
```scala
JaroWinklerMetric.compare("string1", "string2")(new StringFilterDelegate with AsciiLetterCaseStringFilter)
```

Basic example with stacked filter. Filters are applied in reverse order:
```scala
JaroWinklerMetric.compare("string1", "string2")(new StringFilterDelegate with AsciiLetterCaseStringFilter with AsciiLetterOnlyStringFilter)
```

## Convenience objects
The StringMetric, StringAlgorithm, and StringFilter convenience objects are available to make interactions with the library easier:
```scala
StringMetric.compareWithJaroWinkler("string1", "string2")
StringMetric.compareWithJaroWinkler("string1", "string2")(StringFilter.asciiLetterCase)
StringAlgorithm.computeWithMetaphone("string1", "string2")
```

## Command line interfaces
Every metric and algorithm has a command line interface.

The help option prints command syntax and usage:
```shell
$ metaphoneMetric --help
Compares two strings to determine if they are phonetically similarly, per the Metaphone algorithm.

Syntax:
  metaphoneMetric [Options] string1 string2...

Options:
  -h, --help
    Outputs description, syntax, and options.
```

```shell
$ jaroWinklerMetric --help
Compares two strings to calculate the Jaro-Winkler distance.

Syntax:
  jaroWinklerMetric [Options] string1 string2...

Options:
  -h, --help
    Outputs description, syntax, and options.
```

Compare "dog" to "dawg":
```shell
$ metaphoneMetric dog dawg
true
```

```shell
$ jaroWinklerMetric dog dawg
0.75
```

Get the phonetic representation of "dog" using the Metaphone phonetic algorithm:
```shell
$ metaphoneAlgorithm dog
tk
```

## Todo
* SmithWaterman
* MongeElkan
* NeedlemanWunch
* Jaccard
* Double Metaphone
* Memoization decorator

## Requirements
* Scala 2.10.x
* Gradle 1.x

## Versioning
[Semantic Versioning v2.0](http://semver.org/)

## License
[Apache License v2.0](http://www.apache.org/licenses/LICENSE-2.0)

## Questions, Comments, and Requests
Find all my contact information on my [personal website](http://rockymadden.com/).
