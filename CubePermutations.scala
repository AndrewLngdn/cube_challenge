// Coding Challenge
// Andrew Langdon
object CubePermutations {

	var runTests = false;

	var countMap = Map[String, Long]();
	var cubesMap = Map[String, List[(Long)]]()

	def main(args: Array[String]){

		if (runTests){
			testFunctions();
			return;
		} 

		var base = 0;
		var base_cubed = 0L;
		var foundFive = false;
		
		// The loops exits when we've found 5 cube permutations.
		while (!foundFive) {
			base_cubed = Math.pow(base, 3).toLong;

			// addToMaps returns a boolean when it's found five 
			// cube permutations. 
			foundFive = addToMaps(makeFrequencyArr(base_cubed), base_cubed);
			base += 1;
		}
	}

	def makeFrequencyArr(cube:Long):Array[Long] = {
		//split our long in to a bunch of chars
		var arr = cube.toString().toCharArray;

		// init digit array w/ 0s
		var bins = new Array[Long](10);
		bins = Array.fill(10){0};

		// nested function to increment bin count
		val incrementCount = (char:Char) =>
			bins(char.asDigit) +=1;

		// for each char in the array, increment the proper bin
		arr.foreach(char => 
			incrementCount(char) 
		);

		return bins;
	}

	def addToMaps(frequencyArr: Array[Long], input:Long):Boolean = {
		// we're using the frequency string as the key to our hash maps
		var hashKey = frequencyArr.mkString("");

		// init with zero value if it's the first time we've seen this key
		if (!countMap.contains(hashKey)){
			countMap += (hashKey -> 0.toLong);
		}
		if (!cubesMap.contains(hashKey)){
			cubesMap += (hashKey -> List());
		}

		// count!
		val oldCount = countMap(hashKey);
		val newCount = oldCount + 1;
		countMap += (hashKey -> newCount);

		// prepend cubes!
		val oldList = cubesMap(hashKey);
		var newList = input :: oldList;
		cubesMap += (hashKey -> newList);  


		// this is the win condition! It prints some details 
		// and breaks out of the while loop in 'main'
		if (newCount == 5 ) {
			println("Found " + newCount + " permutations.");
			println("Frequency array: " + hashKey)
			println("Cubes = " + cubesMap(hashKey).sorted);
			return true;
		}
		return false;
	}


	// a few basic tests
	def testFunctions() = {
		var testPassed = true;

		var f1 = makeFrequencyArr(12345);
		var f2 = makeFrequencyArr(54321);
		val s1 = f1.mkString("");
		val s2 = f2.mkString("");		
		// s1 and s2 should be the same string if 'makeFrequencyArr' works
		if (s1 != s2) testPassed = false;

		// We should only ever have 1 key in our map if 
		// addToMaps works correctly.
		var b1 = addToMaps(f1, 1);
		println(countMap.size);
		if (countMap.size != 1) testPassed = false;
		b1 = addToMaps(f1, 1);
		if (countMap.size != 1) testPassed = false;

		// This is testing our win condition above. Note that 
		// changing the win condition will break the test,
		// as of now they are not tied together. 
		b1 = addToMaps(f1, 1);
		b1 = addToMaps(f1, 1);
		b1 = addToMaps(f1, 1);
		if (!b1) testPassed = false;

		if (testPassed) {
			println("tests passed!");
		} else {
			println("tests failed.");
		}
	}
}

