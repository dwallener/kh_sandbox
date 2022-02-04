# kh_sandbox
Miscellany

I started writing this in Python, as that's where I'm most comfortable. With the basics done, I rewrote and extended in Java. I work on a Macbook, but I don't anticipate any issues running on Linux. Running on Windows is completely untested.

The (incomplete) python version is executed with:

`> python3 rectangle_01.py`

There are some features used that do require Python 3. The output should look like this:

![Screen Shot 2022-02-03 at 9 53 12 PM](https://user-images.githubusercontent.com/5768048/152479601-4c6c2270-609a-45b7-91e8-b0be8ba3b5e4.png)

The Java version is built and executed with:

`> javac rectangle_01.java`
`> java rectangle_01`

![Screen Shot 2022-02-04 at 12 13 05 PM](https://user-images.githubusercontent.com/5768048/152596954-9e5942e0-8e61-438f-8b48-50ddcd310af7.png)

Some of the assumptions:

- "Bounding" isn't defined. I did two versions - one which looks for twins, and one which looks for rectangles of the same dimensions but in different orientations. There are many ways to extend this - same area, all angles congruent (for non-rectangles), etc.
- Coordinate system is in integers, and untested with negative coords. It would be easy to move it all to floating point.
- The unit tests dump to stdout, for easy of examination. for production code I would do it differently.
- Since I had to create a "universe" to add shapes to, I added very very basic (and computationally awful!) collision detection for rectangles. I didn't have time to create a unit test for this, but it is tested in "main".
- The requirement was for everything in one file, and I completed the requirements incrementally. This resulted in code that, really, needs to be refactored. 
- From the requested features the following have been implemented for both Circle and Rectangle shapes:
- - "object oriented"
- - normal constructor and a copy constructor
- - assignement operator, as an ".Assign" method because Java doesn't easily allow operator overloading
- - equality operator, as an ".Equals" method because Java doesn't easily allow operator overloading
- - checking if a point is inside or outside a shape
- - methods for unit testing the class


