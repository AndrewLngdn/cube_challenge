Coding Challenge

Goal: 
Find the smallest cube for which exactly five permutations of its digits are cube. 

Example:
The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.


My idea: 
Permutations of a number are all the different concatenations of a given set of 
digits. For example, the set {1,2,3} can realize the numbers 213, 312, 123, etc. So 
I decided to represent the result of cubing a number as an array of digit 
frequencies. The index of the array represents the digit and the value at that 
index represents the frequency. For example, the number 100 becomes the array [2, 
1, 0, 0, 0, 0, 0, 0, 0, 0], meaning the number one has 2 '0's and 1 '1'. The number 
1234567890 would be [1, 1, 1, 1, 1, 1, 1, 1, 1]. If numbers generate the same 
frequency array, then they are permutations of each other. All that's left is to 
apply this to our cubes to find permutations. 

Execution: 

I hadn't used any of the required languages before so I chose to use Scala. The 
basic idea is to start with a base variable, cube it, see if it is a permutation of 
four other cubes, and if it is, return the cubes. If it isn't a permutation, 
increment the base by one and try again until the problem is solved. Program 
execution starts at main, where I initialize the 'base' variable and the 
'base_cubed' variable. 'base_cubed' has to be a Long or else it will overflow 
fairly quickly. I use a Boolean called 'foundFive' to control the execution of the 
'while' loop. 

The while loop is fairly simple. It cubes the base, then passes this number to the 
'makeFrequencyArr' function. This function stringify's the cube and then turns it 
into a character array. Then, the frequency bin array is initialized to '0's. The 
rest of the function iterates over the array and increments the proper bin. 

The resulting frequency array is passed to the 'addToMaps' function along with the 
cubed number. This function transforms the frequency array into a string, then 
uses this string as a key to two hashMaps. One map holds the count for a given 
frequency array; it's the number of times we've seen a permutation of the digits 
that created the frequency array. The other Map holds the base number that created 
those digits, and it uses the same key as the count. When the first count reaches 
5, we've gotten five permutations of cubes. This gets sorted and then printed to 
stdout. The smallest cube is the first one in the list. You can see the bases that 
produce this result by passing in 'cube' instead of 'base_cubed' to the 'addToMaps' 
function. 

Also, the tests can be run by switching the runTests flag to 'true' at the top of 
the file. 

Extensions:
If I had more time, I'd refactor the code a little bit and combine the two 
hashtables in to one so we aren't doing two lookups for each number. The value of 
the new map would be a tuple like (count, cubed_list) or something. This would 
definitely speed it up but since the program is using hashing functions it's 
already pretty quick. It takes longer to compile than to run on my machine. 


I would love to hear any comments or suggestions (andrewlngdn@gmail.com).



