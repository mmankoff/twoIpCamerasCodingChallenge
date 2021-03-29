
------------------------------------------------------------------------------------------------------------------
     Two Ip Cameras Challenge
-------------------------------------------------------------------------------------------------------------------
Goal: Complete the task list:
1) 3 seconds per test
2) Standard input
3) Standard Output
4) Successfully ran using only 256 megabytes.
---------------------------------------------------------------------------------------------------------------------
Context:
1) There are 2 cameras both of the same model and both can take photos beginning at some fixed point in time.
   ( Me thinking out loud -> Inlining a rate limiter might work or a window slide recursive check.)
2) Start time of those processes is up for me to choose but must be from a value set by the cameras
   manufacturer which are set as one of (k) values == to (p1, p2,....,pk).
3) You have (n) points that you are interested in == to (x1,x2,....,xn).
4) Configure both cameras in a way that at least one of them will take a photo in each of these moments.
   p1-> p2...pk where (k) is equal to the manufactures set start value.
   x1 -> x2 -> x3 -> x4
5) To configure each camera means: -> ( Setting the points where each camera takes the first photo along with
   the gap between it's next two consecutive points where one of those points should be equal to one of the values
   between.) (p1,p2,....,pk).

6) The ability for both cameras being able to take pictures at different moments doesn't effect my points of
   interest
7) The application should return " This is impossible " if there if it can't find a way to cover all required points.
   -> Printed to output.
   .
   Key -> The main goal is to find each point sufficient enough that both cameras start points are set so that the entire
   distance from each of their start points to the end covers every initial point within the range it needs to
   search.
---------------------------------------------------------------------------------------------------------------------

