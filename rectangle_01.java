import java.lang.Math;

// Circle //////////////////////

class Circle
{

  // properties of the circle
  int center_x;
  int center_y;
  int radius;

  // constructor
  public Circle (int center_x, int center_y, int radius)
  {
    this.center_x = center_x;
    this.center_y = center_y;
    this.radius = radius;
    System.out.println("Creating circle at " + center_x + "," + center_y + " with radius " + radius);
  }

  // copy constructor (simple class, just a shallow copy)
  public Circle (Circle circle)
  {
    this.center_x = circle.center_x;
    this.center_y = circle.center_y;
    this.radius = circle.radius;
  }

  // Just a way to show what object we made, vain attempt to limit printlns in the main
  public int Properties()
  {
    System.out.println("Circle properties: ");
    System.out.println(center_x + "," + center_y + " R = " + radius);
    return 0;
  }

  // Do the bounding test
  public boolean IsBounding(int x, int y)
  {
    double pythag_distance = Math.pow(x,2) + Math.pow(y,2);
    if (pythag_distance < Math.pow(this.radius,2)) {
      return true;
    }
    return false;
  }

  public boolean SelfTestBounding()
  {
    Circle circle_1 = new Circle(0,0,100);
    boolean testResult = false; // default to false

    // Interior point - this should generate a TRUE response
    if (circle_1.IsBounding(8,9)) {
      System.out.println("Passed bounding test for interior point");
      testResult = true;
    } else {
      System.out.println("Failed bounding test for interior point");
    }

    // exterior point - this should generate a TRUE response
    if (circle_1.IsBounding(108,109)) {
      System.out.println("Failed bounding test for exterior point");
    } else {
      System.out.println("Passed bounding test for exterior point");
      // only pass this if the previous test(s) have passed
      if (testResult) {
        testResult = true;
      }
    }
    return testResult;
  }

  // this test allows arbitrary input for SelfTestBounding
  // overload the previous method
  public boolean SelfTestBounding(Circle this_circle, int x, int y, boolean expected_result)
  {
    boolean test_result = this_circle.IsBounding(x,y);
    if (test_result == expected_result) {
      System.out.println("Passed bounding test for arbitrary circle/point");
      return true;
    } else {
      return false;
    }
  }


}


// Rectangle //////////////////

class Rectangle
{
  // properties of the rectangle
  int min_x = 0;
  int max_x = 0;
  int min_y = 0;
  int max_y = 0;

  // constructor
  public Rectangle (int min_x, int min_y, int max_x, int max_y)
  {
    this.min_x = min_x;
    this.min_y = min_y;
    this.max_x = max_x;
    this.max_y = max_y;
    System.out.println("Creating rectangle at " + this.min_x + "," + this.min_y + "," + this.max_x + "," + this.max_y);
  }

  // copy constructor
  public Rectangle(Rectangle rectangle)
  {
    this.min_x = rectangle.min_x;
    this.min_y = rectangle.min_y;
    this.max_x = rectangle.max_x;
    this.max_y = rectangle.max_y;
    System.out.println("Creating rectangle at " + this.min_x + "," + this.min_y + "," + this.max_x + "," + this.max_y);

  }

  // Just a way to show what object we made, futile attempt to limit printlns in the main
  public int Properties()
  {
    System.out.println("Rectangle properties: ");
    System.out.println(min_x + "," + min_y + "," + max_x + "," + max_y);
    return 0;
  }

  public boolean IsBounding(int x, int y)
  {
    if ((x > this.min_x) & (x < this.max_x) & (y > this.min_y) & (y < this.max_y)) {
      return true;
    }
    return false;
  }

  // this is a super naive "same shape" test - has to be same dimensions in same orientation
  public boolean SameAs(Rectangle other_rect)
  {
    if (  (this.min_x == other_rect.min_x) &&
          (this.min_y == other_rect.min_y) &&
          (this.max_x == other_rect.max_x) &&
          (this.max_y == other_rect.max_y))
      {
          return true;
      } else return false;
  }

  // this is less naive "same shape" test - has to be same dimensions in different orientions
  // only really works for multiples of 90 degrees
  public boolean SimilarTo(Rectangle other_rect)
  {
    int self_distance_x = this.max_x - this.min_x;
    int self_distance_y = this.max_y - this.min_y;
    int compare_distance_x = other_rect.max_x - other_rect.min_x;
    int compare_distance_y = other_rect.max_y - other_rect.min_y;
    if ((self_distance_x == compare_distance_x) && (self_distance_y == compare_distance_y)) {
        return true;
      } else if ((self_distance_x == compare_distance_y) && (self_distance_y == compare_distance_x)) {
        return true;
      } else return false;
  }

  // change the shape of the rectangle
  public int ChangeBounds(int min_x, int min_y, int max_x, int max_y)
  {
    this.min_x = min_x;
    this.min_y = min_y;
    this.max_x = max_x;
    this.max_y = max_y;

    return 0;
  }

  // let's wrap the testing into the shape class itself, to simplify 'main
  // this is a simple test that builds it's own rectangle and chooses it's own points

  public boolean SelfTestBounding()
  {
    System.out.println("TESTING: bounding test for rectang;e interior point");

    Rectangle rect_1 = new Rectangle(0,0,100,100);
    boolean testResult = false; // default to false

    // Interior point - this should generate a TRUE response
    if (rect_1.IsBounding(8,9)) {
      System.out.println("Passed bounding test for interior point");
      testResult = true;
    } else {
      System.out.println("Failed bounding test for interior point");
    }

    // exterior point - this should generate a TRUE response
    if (rect_1.IsBounding(108,109)) {
      System.out.println("Failed bounding test for exterior point");
    } else {
      System.out.println("Passed bounding test for exterior point");
      // only pass this if the previous test(s) have passed
      if (testResult) {
        testResult = true;
      }
    }
    return testResult;
  }

  // this test allows arbitrary input for SelfTestBounding
  // overload the previous method
  public boolean SelfTestBounding(Rectangle this_rect, int x, int y, boolean expected_result)
  {
    System.out.println("TESTING: bounding test for rectang;e interior point");
    boolean test_result = this_rect.IsBounding(x,y);
    if (test_result == expected_result) {
      System.out.println("Passed bounding test for arbitrary rectangle/point");
      return true;
    } else {
      return false;
    }
  }

  public boolean SelfTestSameness(Rectangle this_rect, Rectangle that_rect, boolean expected_result)
  {
    System.out.println("TESTING: sameness test for rectangles");
    boolean test_result = false;
    // tests for rectangle with identical dimensions
    if (  (this_rect.min_x == that_rect.min_x) &
          (this_rect.min_y == that_rect.min_y) &
          (this_rect.max_x == that_rect.max_x) &
          (this_rect.max_y == that_rect.max_y)) {
      System.out.println("Passed sameness test");
      test_result = true;
    } else {
      System.out.println("Failed sameness test");
      test_result = false;
    }
    if (expected_result == test_result) {
      return true;
    } else {
      return false;
    }
  }

  public boolean SelfTestSimilarity(Rectangle this_rect, Rectangle that_rect, boolean expected_result)
  {
    System.out.println("TESTING: similarity test for rectangles");
    boolean test_result = false;
    int this_distance_x = this_rect.max_x - this_rect.min_x;
    int this_distance_y = this_rect.max_y - this_rect.min_y;
    int that_distance_x = that_rect.max_x - that_rect.min_x;
    int that_distance_y = that_rect.max_y - that_rect.min_y;

    if (((this_distance_x == that_distance_x) & (this_distance_y == that_distance_y)) ||
        ((this_distance_x == that_distance_y) & (this_distance_y == that_distance_x))) {
      System.out.println("Passed similarity test");
      test_result = true;
    } else {
      System.out.println("Failed similarity test");
      test_result = false;
    }
    if (expected_result == test_result) {
      return true;
    } else {
      return false;
    }
  }

}

///////////////////////////////////////////////////////////////////////////////

public class rectangle_01 {
  public static void main(String args[]) {
    System.out.println("Here we go...");

    boolean test_result = false; // set to fail until a pass is earned

    // Rectangle bounding test
    // first method
    Rectangle rect_10 = new Rectangle(0,0,100,100);
    Rectangle rect_11 = new Rectangle(20, 20, 50, 50);
    test_result = rect_10.SelfTestBounding();
    // second method - precompute the expected result, look for the match
    test_result = test_result & rect_10.SelfTestBounding(rect_11, 60, 60, false);
    test_result = test_result & rect_10.SelfTestBounding(rect_11, 23, 44, true);

    if (test_result) {
      System.out.println("PASSED: Rectangle bounding tests");
    } else {
      System.out.println("FAILED: Rectangle bounding tests");
    }

    // Circt bounding test
    // first method
    Circle circle_10 = new Circle(0,0,100);
    Circle circle_11 = new Circle(20, 20, 10);
    // simple test
    test_result = circle_10.SelfTestBounding();
    // second method - precompute the expected result, look for the match
    test_result = test_result & circle_10.SelfTestBounding(circle_11, 60, 60, false);
    test_result = test_result & circle_10.SelfTestBounding(circle_11, 23, 24, true);

    if (test_result) {
      System.out.println("PASSED: Circle bounding tests");
    } else {
      System.out.println("FAILED: Circle bounding tests");
    }

    // test out the copy constructor
    Circle circle_12 = new Circle(circle_11);
    System.out.println("Copy construct a circle...");
    circle_12.Properties();
    if (circle_12 == circle_11) {
      System.out.println("PASSED: Circle copy constructor test");
    } else {
      System.out.println("FAILED: Circle copy constructor test");
    }
    Rectangle rect_12 = new Rectangle(rect_11);
    System.out.println("Copy construct a rectangle...");
    rect_12.Properties();
    if (rect_12 == rect_11) {
      System.out.println("PASSED: Circle copy constructor test");
    } else {
      System.out.println("FAILED: Circle copy constructor test");
    }

    // sameness test
    Rectangle rect_1 = new Rectangle(0, 0, 100, 100);
    Rectangle rect_2 = new Rectangle(0, 0, 50, 100);
    Rectangle rect_3 = new Rectangle(0, 0, 100, 50);
    Rectangle rect_4 = new Rectangle(0, 0, 100, 100);
    // rect_1 and rect_4 should pass the same test
    // rect_2 and rect_3 should fail the same test
    // rect_2 and rect_3 should pass the similar test
    // reset expectations
    test_result = false;
    test_result = rect_1.SelfTestSameness(rect_1, rect_4, true);
    test_result = test_result & rect_1.SelfTestSameness(rect_2, rect_3, false);
    test_result = test_result & rect_1.SelfTestSimilarity(rect_2, rect_3, true);

    if (test_result) {
      System.out.println("PASSED: Rectangle sameness tests");
    } else {
      System.out.println("FAILED: Rectangle sameness tests");
    }


  }
}
