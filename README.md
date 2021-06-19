# project1

This was a school project for my Computational Theory class. It is split into two problems, both using Deterministic Finite Automata for high number counting and recursive algorithm checking. I was the main contributor to problem 1 of this project and a co-contributor to problem 2. The problems are as follows:

PROBLEM 1:
Write a function count that computes the number of strings w of length n over {a, b, c, d} with the following
property: In any substring of length 6 of w, all three letters a, b, c and d occur at least once. For example, strings
like abbccdaabca satisfy the property, but a string like badaabcbcdcabad does not satisfy the property since the
substring aabcbc does not have a d. The idea is to create a DFA M for the language:
L = {w | in any substring of length 6 of w, each letter a, b, c and d occurs at least once }.
By definition, all strings of length less than 6 are in L. Let M = hQ, Σ, δ, 0, Fi be a DFA. Assume that Q =
{0, 1, · · · , m–1} and that 0 is the start state. The first step is to construct this DFA. This DFA has hundreds of
states so you have to write a program to build it. We have looked at similar DFAs in class. The states will encode
a buffer that tracks the last 5 symbols seen. When the next input is read, the DFA checks if the suffix of last 6
symbols (the five from the buffer and the next input) meets the requirement that all letters occur at least once. If
this condition is not met, the DFA enters a fail state and thereafter it will remain there. If the condition is met,
then the buffer is updated. Initially the buffer is empty and so the first five transitions will just fill the buffer.
Formally, if the current state q encodes a string b1b2 · · · bm where m < 5, then δ(q, a) encodes the state b1b2 · · · bma.
If the state p encodes a buffer of length 5, say b1b2b3b4b5, then δ(q, a) encodes the state b2b3b4b5a if b1b2b3b4b5a
contains at least once occurrence of each a, b, c and d, else δ(q, a) is the fail state. It is enconvenient to map each
buffer string by a positive integer so that the states can be labeled 0, 1, 2, etc. One such encoding is to use base-4.
For example, babca represents the integer 2 × 4
4 + 43 + 2 × 4
2 + 3 × 4
1 + 40
.
The next step is to implement an algorithm that takes as input a DFA M and an integer n and computes the
number of strings of length n accepted by M. This algorithm was presented and a number of examples were given,
including some in quizzes 3 and 4. Specifically, this algorithm computes Nj (n) = the number of strings of length n
from any state j to an accepting state:
The number of strings of length n accepted by a DFA M is given by N0(n). The recurrence formula for Nj (n) is
given as follows: Nj (n) = Σx∈{a,b,c,d}Nδ(j,x)(n − 1). Initial values Nj (0) are given by: Nj (0) = 1 if j ∈ F, Nj (0)
= 0 if j 6∈ F. Using this recurrence formula, you can compute Nj (k) for all j for k = 0, 1, · · · , n. As we noted in
class, you only need to keep two vectors prev and next of length m where m is the number of states. Using the
values of Nj (k) stored in prev, you can compute Nj (k + 1) for all 0 ≤ j ≤ m − 1 in next. Then copy next to prev
and repeat.
When your main function is run, it will ask for an integer input n, and will output the number of strings of length
n with the specified property. The range of n will be between 1 and 300. The answer should be exact, not a
floating-point approximation so you should use a language that supports unlimited precision arithmetic like Java
or Python or a library like GMP (in case of C++).
Some test cases are included below:
n = 6 Answer: 1560
n = 56 Answer: 1144518781838828768850216

PROBLEM 2:
Write a function MinString that takes as input a DFA M and outputs a string w of shortest length (lexicographically
first in case of more than one string) accepted by the DFA. (If L(M) is empty, your program should print No
solution. Breadth-First Search (BFS) will be used to solve this problem. Use this function to write another
function smallestMultiple that takes as input a positive integer k, and a subset S of {0, 1, 2, · · · , 9} and outputs
the smallest positive integer y > 0 that is an integer multiple of k, and has only the digits (in decimal) from the set
S. The algorithm to solve this problem is as follows: Create a DFA M = hQ, S, δ, k, Fi where Q = {0, 1, · · · , k},
F = {0}, and δ(j, a) = (10 ∗ j + a)%k. Here is a brief summary of BFS (which was presented in more detail in
class. See the pseudo-code below for more details.) Initially, a Queue contains n, the start state. Also VISITED
is set to True for n and False for all other states. Then, the search is performed until the Queue is empty or state
0 is reached: Delete j from the Queue and let NEXT be the set of states reachable from j: NEXT ={ δ(j, a) |
for all a ∈ S}, and insert for each x in NEXT such that VISITED[x] = false into the queue (and set VISITED[x]
to True.) Also PARENT[x] is set to j. When the loop ends, if the QUEUE is empty, the DFA does not generate
any string. Otherwise, your algorithm has found the shortest path from n to 0. By tracing the path (using the
PARENT pointers) you can find the shortest string that accepted by the DFA. (Make sure to skip the null string
as the shortest string.)
For this problem, you can assume that k is in the range 1 to 99999.
Some test cases:
Test case 1:
Inputs: k = 26147, Digits permitted: 1, 3
Output: 1113313113
Test case 2:
Inputs: k = 198217, Digits permitted: 1
Output: integer containing 10962 ones (Your output will be a string of this many 1’s.)
Test case 3:
Inputs: k = 135, Digits permitted: 1 3 7
Output: No solution.
BFS customized for this project
As we discussed in class, the generic BFS has to be modified for this project in the following ways. In usual
applications of BFS, the underlying graph has no labels on the edges. But a DFA is a graph in which edges are
labeled. So to keep track of the shortest path found from the current vertex to the starting vertex, parent pointer
stores the state, and label stores the label of the last edge on the path from the starting vertex to the current vertex.
