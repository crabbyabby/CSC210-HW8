# A8 DIY SpellChecker

Each student should complete this README individually, even when pair programming.

## Basic Information

Your name: Abigail Lei

Programming partner name, if any:

Other collaborators, including TAs:

If anyone was particularly helpful, please give them a shout-out here:

## References

Any references or resources used besides JavaDoc and course materials:
https://www.geeksforgeeks.org/java/java-string-manipulation-best-practices-for-clean-code/
https://www.geeksforgeeks.org/java/stringbuilder-class-in-java-with-examples/ 

If you used generative AI, how did you use it? What role did it play in your learning?

## Assignment Reflection

Please reflect on your experience with this assignment. Include:

- the benchmark output `Timer.java` reported when benchmarking the `ArrayList`-backed dictionary against the `HashSet`-backed dictionary

contains() benchmark using 99168 words
ListDictionary: 13260527750 ns
HashSetDictionary: 8632959 ns

- How does `ArrayList.contains(...)` work? How does this affect runtime?
The ArrayList `.contains()` works by iterating through every item in the list and checking if it is there, then returning true when it hits it and false when it doesn't. Since it checks every item in the list, this results in an O(n) runtime.

- How does `HashSet.contains(...)` work? How does this affect runtime?
The HashSet `.contains()` works by checking for a result from a hash code and immediately jumping to that "bucket" without having to iterating through everything. This results in O(1) runtime because it can immediately jump to the item.

- Why is the runtime of these `contains` tests important for the overall efficiency of a spellchecker?
The runtime is important because O(n) scales up dramatically with the number of objects in the list, whereas it will stay more stable with a hash set. This is important because this means that the HashSet will make the spell checker much more efficient.

- Which dictionary implementation will you choose to implement Phase 2?
I will use HashSet implementation

- what you observed in that benchmark
- which data structures you chose for the dictionary and near-miss suggestions, and why
- any other important design choices you made
- what was most challenging or most interesting about the assignment?
