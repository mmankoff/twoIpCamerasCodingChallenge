package codingChallenges
/** ------------------------------------------------------------------------------------------------------------------
   First step -> narrow your search down to the inputs provided that will cover your base case and match
   all points as being covered.
       -> Start by looking at the (0,1,2) position and consider all divisor for the moments in time that
         cover the entire time period where at least the differences of each cover the full area
         where one camera (0) -> (1,4,5) excluding (7,12) and replacing
  it with (0,1,2).
----------------------------------------------------------------------------------------------------------------------
  This will give us (0,1,2,3,4,5) where (0) is covering the start index and allowing for (1,4) to match the desired
      output of(0) covering (1) -> (4) ||   (0) covering (2) -> (3) will provide (0,1,2,3,4,5).

  The period of the camera should be a divisor of the difference of both moments in time
     Example inputs:
       Camera 1
        (3,5)
       (3,5,7)
    (1,4,5,7,12) -> / (0,1,2)
----------------------------------------------------------------------------------------------------------------------
    Consider these first as this provides a base case for all COVERED moments in time
    Camera 2
     (3,2)
     (1,2,3)
     (1,10)
--------------------------------------------------------------------------------------------------------------------
    Outputs:
     YES
     (1,3)
     (5,7)
--------------------------------------------------------------------------------------------------------------------
 */
// First I want to iterate over (k)
// To ensure edge cases where a larger amount of inputs needs to be searched through, I need to add a limiter
// here that will reduce the problem and allow me to not worry about receiving too large of an input to search. I
// want to take the smallest possible number to search through for the input to start.
fun solve(periods: List<Int>, moments: List<Int>): List<Int>? {

    val goodStartingIndex = (0..moments.size - 3).minByOrNull{ moments[it + 2] - moments[it] }
                    ?: return listOf( moments[0], periods[0],moments[1] ,periods[0])
    val triple = goodStartingIndex..goodStartingIndex + 2
    for (k in triple) {
        val (i, j) = triple.minus(k)
        for (period1 in periods) {
            val remainderI = moments[i] % period1
            val remainderJ = moments[j] % period1
            if (remainderI != remainderJ) continue
            val uncoveredMoments = moments.filter { it % period1 != remainderI }

            //defining the gcd set to 0 is ok to do and will be the start here which gets updated with all the new
            // GCD for each difference. Using a fold function would work here.
            val gcdOfDifferences = uncoveredMoments.zipWithNext(Int::minus).fold(0, ::gcd)

            // linear time still and this will provide the
            val period2 = periods.firstOrNull{ gcdOfDifferences % it == 0} ?: continue
            val phase1 = phase(remainderI, period1)
            val phase2 = phase(uncoveredMoments.firstOrNull() ?: 0 , period2)
            return listOf(phase1,period1,phase2,period2)
            // Setting both phases so as they will cover both moments where my index points match moments i and j.
        }
    }
    return null
}

/**------------------------------------------------------------------------------------------------------------------
 I'm going to be using a tailrec recursive tail function.
 This will be the fastest solution but I've demonstrated 2 other examples of how it can be done as well with slower
 results. -> 1) return a.toBigInteger().gcd(b.toBigInteger().toInt()) or
             2)return a.toBigInteger().gcd(b.toBigInteger().toInt())
 --------------------------------------------------------------------------------------------------------------------
*/
private fun phase(remainder: Int, period: Int) = (remainder + period - 1) % period + 1
tailrec fun gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)
fun main(){
    readLine()
    val periods = readInts()
    val moments = readInts()
    //This will create the function that will read the entire line that aren't matched already by the other periods.
    //To solve this I will need to add a nullable type.
    val (phase1, period1, phase2, period2) = solve(periods,moments)
        ?: return println("NO")
    println("YES\n$phase1 $period1\n$phase2 $period2")
}


private fun readInts() = readLine()!!.split(" ").map { it.toInt() }