#stringmetric [![Build Status](https://travis-ci.org/rockymadden/stringmetric.png?branch=master)](http://travis-ci.org/rockymadden/stringmetric)
String metrics and phonetic algorithms for Scala. The library provides facilities to perform approximate string matching, measurement of string similarity/distance, indexing by word pronunciation, and sounds-like comparisons. In addition to the core library, each metric and algorithm has a command line interface.

* __Requirements:__ Scala 2.10.x and, if building yourself, Gradle 1.8+
* __Documentation:__ [Scaladoc](http://rockymadden.com/stringmetric/scaladoc/)
* __Issues:__ [Enhancements](https://github.com/rockymadden/stringmetric/issues?labels=accepted%2Cenhancement&page=1&state=open), [Questions](https://github.com/rockymadden/stringmetric/issues?labels=accepted%2Cquestion&page=1&state=open), [Bugs](https://github.com/rockymadden/stringmetric/issues?labels=accepted%2Cbug&page=1&state=open)
* __Versioning:__ [Semantic Versioning v2.0](http://semver.org/)

## Metrics and algorithms
* __[Dice / Sorensen](http://en.wikipedia.org/wiki/Dice%27s_coefficient)__ (Similarity metric)
* __[Double Metaphone](http://en.wikipedia.org/wiki/Metaphone)__ ([Queued](https://github.com/rockymadden/stringmetric/issues/6) phonetic metric and algorithm)
* __[Hamming](http://en.wikipedia.org/wiki/Hamming_distance)__ (Similarity metric)
* __[Jaccard](http://en.wikipedia.org/wiki/Jaccard_index)__ (Similarity metric)
* __[Jaro](http://en.wikipedia.org/wiki/Jaro-Winkler_distance)__ (Similarity metric)
* __[Jaro-Winkler](http://en.wikipedia.org/wiki/Jaro-Winkler_distance)__ (Similarity metric)
* __[Levenshtein](http://en.wikipedia.org/wiki/Levenshtein_distance)__ (Similarity metric)
* __[Metaphone](http://en.wikipedia.org/wiki/Metaphone)__ (Phonetic metric and algorithm)
* __[Monge-Elkan](http://www.cs.cmu.edu/~pradeepr/papers/ijcai03.pdf)__ ([Queued](https://github.com/rockymadden/stringmetric/issues/7) similarity metric)
* __[Match Rating Approach](http://en.wikipedia.org/wiki/Match_rating_approach)__ ([Queued](https://github.com/rockymadden/stringmetric/issues/8) phonetic metric and algorithm)
* __[Needleman-Wunch](http://en.wikipedia.org/wiki/Needleman%E2%80%93Wunsch_algorithm)__ ([Queued](https://github.com/rockymadden/stringmetric/issues/9) similarity metric)
* __[N-Gram](http://en.wikipedia.org/wiki/N-gram)__ (Similarity metric)
* __[NYSIIS](http://en.wikipedia.org/wiki/New_York_State_Identification_and_Intelligence_System)__ (Phonetic metric and algorithm)
* __[Overlap](http://en.wikipedia.org/wiki/Overlap_coefficient)__ (Similarity metric)
* __[Ratcliff-Obershelp](http://xlinux.nist.gov/dads/HTML/ratcliffObershelp.html)__ (Similarity metric)
* __[Refined NYSIIS](http://www.markcrocker.com/rexxtipsntricks/rxtt28.2.0482.html)__ (Phonetic metric and algorithm)
* __[Refined Soundex](http://ntz-develop.blogspot.com/2011/03/phonetic-algorithms.html)__ (Phonetic metric and algorithm)
* __[Tanimoto](http://en.wikipedia.org/wiki/Tanimoto_coefficient)__ ([Queued](https://github.com/rockymadden/stringmetric/issues/10) similarity metric)
* __[Tversky](http://en.wikipedia.org/wiki/Tversky_index)__ ([Queued](https://github.com/rockymadden/stringmetric/issues/16) similarity metric)
* __[Smith-Waterman](http://en.wikipedia.org/wiki/Smith%E2%80%93Waterman_algorithm)__ ([Queued](https://github.com/rockymadden/stringmetric/issues/11) similarity metric)
* __[Soundex](http://en.wikipedia.org/wiki/Soundex)__ (Phonetic metric and algorithm)
* __Weighted Levenshtein__ (Similarity metric)


## Depending upon
The project is available on the [Maven Central Repository](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.rockymadden.stringmetric%22). Adding a dependency to the core sub-project in various build systems (add other sub-projects as needed):


__Simple Build Tool:__
```scala
libraryDependencies += "com.rockymadden.stringmetric" % "stringmetric-core" % "0.25.3"
```

---

__Gradle:__
```groovy
compile 'com.rockymadden.stringmetric:stringmetric-core:0.25.3'
```

---

__Maven:__
```xml
<dependency>
	<groupId>com.rockymadden.stringmetric</groupId>
	<artifactId>stringmetric-core</artifactId>
	<version>0.25.3</version>
</dependency>
```

## Similarity package
Useful for approximate string matching and measurement of string distance. Most metrics calculate the similarity of two strings as a double with a value between 0 and 1. A value of 0 being completely different and a value of 1 being completely similar.


__Dice / Sorensen Metric:__ _(Note you must specify the size of the n-gram you wish to use.)_
```scala
DiceSorensenMetric(1).compare("night", "nacht")
DiceSorensenMetric(1).compare("context", "contact")
```

Output:
```
0.6
0.7142857142857143
```

---

__Hamming Metric:__
```scala
HammingMetric.compare("toned", "roses")
HammingMetric.compare("1011101", "1001001")
```

Output: _(Note the exception of integers, rather than doubles, being returned.)_
```
3
2
```

---

__Jaccard Metric:__ _(Note you must specify the size of the n-gram you wish to use.)_
```scala
JaccardMetric(1).compare("night", "nacht")
JaccardMetric(1).compare("context", "contact")
```

Output:
```
0.3
0.35714285714285715
```

---

__Jaro Metric:__
```scala
JaroMetric.compare("dwayne", "duane")
JaroMetric.compare("jones", "johnson")
JaroMetric.compare("fvie", "ten")
```

Output:
```
0.8222222222222223
0.7904761904761904
0
```

---

__Jaro-Winkler Metric:__
```scala
JaroWinklerMetric.compare("dwayne", "duane")
JaroWinklerMetric.compare("jones", "johnson")
JaroWinklerMetric.compare("fvie", "ten")
```

Output:
```
0.8400000000000001
0.8323809523809523
0
```

---

__Levenshtein Metric:__
```scala
LevenshteinMetric.compare("sitting", "kitten")
LevenshteinMetric.compare("cake", "drake")
```

Output: _(Note the exception of integers, rather than doubles, being returned.)_
```
3
2
```

---


__N-Gram Metric:__ _(Note you must specify the size of the n-gram you wish to use.)_
```scala
NGramMetric(1).compare("night", "nacht")
NGramMetric(2).compare("night", "nacht")
NGramMetric(2).compare("context", "contact")
```

Output:
```
0.6
0.25
0.5
```

---

__Overlap Metric:__ _(Note you must specify the size of the n-gram you wish to use.)_
```scala
OverlapMetric(1).compare("night", "nacht")
OverlapMetric(1).compare("context", "contact")
```

Output:
```
0.6
0.7142857142857143
```

---

__Ratcliff/Obershelp Metric:__
```scala
RatcliffObershelpMetric.compare("aleksander", "alexandre")
RatcliffObershelpMetric.compare("pennsylvania", "pencilvaneya")
```

Output:
```
0.7368421052631579
0.6666666666666666
```

---

__Weighted Levenshtein Metric:__ _(Note you must specify the weight of each operation. Delete, insert, and then substitute.)_
```scala
WeightedLevenshteinMetric(10, 0.1, 1).compare("book", "back")
WeightedLevenshteinMetric(10, 0.1, 1).compare("hosp", "hospital")
WeightedLevenshteinMetric(10, 0.1, 1).compare("hospital", "hosp")
```

Output: _(Note that while a double is returned, it can be outside the range of 0 to 1, based upon the weights used.)_
```
2
0.4
40
```

## Phonetic package
Useful for indexing by word pronunciation and performing sounds-like comparisons. All metrics return a boolean value indicating if the two strings sound the same, per the algorithm used. All metrics have an algorithm counterpart which provide the means to perform indexing by word pronunciation.

__Metaphone Metric:__
```scala
MetaphoneMetric.compare("merci", "mercy")
MetaphoneMetric.compare("dumb", "gum")
```

Output:
```
true
false
```

---

__Metaphone Algorithm:__
```scala
MetaphoneAlgorithm.compute("dumb")
MetaphoneAlgorithm.compute("knuth")
```

Output:
```
tm
n0
```

---

__NYSIIS Metric:__
```scala
NysiisMetric.compare("ham", "hum")
NysiisMetric.compare("dumb", "gum")
```

Output:
```
true
false
```

---

__NYSIIS Algorithm:__
```scala
NysiisAlgorithm.compute("macintosh")
NysiisAlgorithm.compute("knuth")
```

Output:
```
mcant
nnat
```

---

__Refined NYSIIS Metric:__
```scala
RefinedNysiisMetric.compare("ham", "hum")
RefinedNysiisMetric.compare("dumb", "gum")
```

Output:
```
true
false
```

---

__Refined NYSIIS Algorithm:__
```scala
RefinedNysiisAlgorithm.compute("macintosh")
RefinedNysiisAlgorithm.compute("westerlund")
```

Output:
```
mcantas
wastarlad
```

---

__Refined Soundex Metric:__
```scala
RefinedSoundexMetric.compare("robert", "rupert")
RefinedSoundexMetric.compare("robert", "rubin")
```

Output:
```
true
false
```

---

__Refined Soundex Algorithm:__
```scala
RefinedSoundexAlgorithm.compute("hairs")
RefinedSoundexAlgorithm.compute("lambert")
```

Output:
```
h093
l7081096
```

---

__Soundex Metric:__
```scala
SoundexMetric.compare("robert", "rupert")
SoundexMetric.compare("robert", "rubin")
```

Output:
```
true
false
```

---

__Soundex Algorithm:__
```scala
SoundexAlgorithm.compute("rupert")
SoundexAlgorithm.compute("lukasiewicz")
```

Output:
```
r163
l222
```

## Decorating
It is possible to decorate algorithms and metrics with additional functionality. The most common decorations are filters, which are useful for filtering strings prior to evaluation (e.g. ignore case, ignore non-alpha, ignore spaces). __NOTE:__ [Memoization decorator queued](https://github.com/rockymadden/stringmetric/issues/5)

Basic examples with no filtering:
```scala
JaroWinklerMetric.compare("string1", "string2")
JaroWinklerMetric().compare("string1", "string2")
(new JaroWinklerMetric).compare("string1", "string2")
```

---

Basic example with single filter:
```scala
(new JaroWinklerMetric with IgnoreAsciiLetterCaseFilter).compare("string1", "string2")
```

---

Basic example with stacked filters. Filters are applied in reverse order:
```scala
(new JaroWinklerMetric with IgnoreAsciiLetterCaseFilter with AsciiLetterOnlyFilter).compare("string1", "string2")
```

## Convenience objects
Convenience objects are available to make interactions with the library easier.

__StringAlgorithm:__
```scala
// Easy access to compute methods.
StringAlgorithm.computeWithMetaphone("string")
```

---

__StringMetric:__
```scala
// Easy access to compare methods.
StringMetric.compareWithJaroWinkler("string1", "string2")
```

---

## Building the CLIs
When built, the CLI sub-project creates an individual shell command for each algorithm and metric. The sub-project is built via a Gradle task:

```shell
git clone https://github.com/rockymadden/stringmetric.git
cd stringmetric
gradle :stringmetric-cli:tar
```

Running the ```tar``` task will create a compressed archive __and__ an unarchived copy of the built files. The files can be found under the ```build``` directory that Gradle creates. The archive is named ```stringmetric-cli.tar.gz``` and the unarchived files can be found in the directory named ```stringmetric-cli```.

To run a command from the current directory that you would be in from doing the above:

```shell
./cli/build/stringmetric-cli/jarometric abc xyz
```

Lastly, you may need to chmod the files because of the inability for Gradle to do so reliably.

## Using the CLIs
The help option prints command syntax and usage:
```shell
$ metaphonemetric --help
Compares two strings to determine if they are phonetically similarly, per the Metaphone algorithm.

Syntax:
  metaphonemetric [Options] string1 string2...

Options:
  -h, --help
    Outputs description, syntax, and options.
```

```shell
$ jarowinklermetric --help
Compares two strings to calculate the Jaro-Winkler distance.

Syntax:
  jarowinklermetric [Options] string1 string2...

Options:
  -h, --help
    Outputs description, syntax, and options.
```

---

Compare "dog" to "dawg":
```shell
$ metaphonemetric dog dawg
true
```

```shell
$ jarowinklermetric dog dawg
0.75
```

---

Get the phonetic representation of "dog" using the Metaphone phonetic algorithm:
```shell
$ metaphonealgorithm dog
tk
```

## License
```
The MIT License (MIT)

Copyright (c) 2013 Rocky Madden (http://rockymadden.com/)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
