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

}


// Rectangle //////////////////

class Rectangle
{
  // properties of the rectangle
  int min_x;
  int max_x;
  int min_y;
  int max_y;

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

}

///////////////////////////////////////////////////////////////////////////////

public class rectangle_01 {
  public static void main(String args[]) {
    System.out.println("Here we go...");

    // let's start with circles...
    System.out.println("Create a circle object at 5,5 with radius 10");
    Circle circle_1 = new Circle(50,50,10);
    circle_1.Properties();

    // test out the copy constructor
    Circle circle_2 = new Circle(circle_1);
    System.out.println("Copy construct another one...");
    circle_2.Properties();

    // test the bounding test...
    System.out.println("Test if point 8,9 is inside the circle");
    if (circle_1.IsBounding(58,59)) {
      System.out.println("Yep!");
    } else {
      System.out.println("Nope!");
    }
    System.out.println("Test if point 118,129 is inside the circle");
    if (circle_1.IsBounding(68,69)) {
      System.out.println("Yep!");
    } else {
      System.out.println("Nope!");
    }

    // Move on to rectangles...
    System.out.println("Creating a rectangle object at (0,0) to (100,100)");
    Rectangle rect_1 = new Rectangle(0,0,100,100);
    rect_1.Properties();

    // test the bounding test...
    System.out.println("Test if point 8,9 is inside the rectangle");
    if (rect_1.IsBounding(8,9)) {
      System.out.println("Yep!");
    } else {
      System.out.println("Nope!");
    }
    System.out.println("Test if point 118,129 is inside the rectangle");
    if (rect_1.IsBounding(118,129)) {
      System.out.println("Yep!");
    } else {
      System.out.println("Nope!");
    }

    // test for sameness
    // create 2nd rectangle - same as first using the normal constructor
    Rectangle rect_2 = new Rectangle(0,0,100,100);
    // create 3rd rectangle using the copy constructor
    Rectangle rect_3 = new Rectangle(rect_2);
    rect_2.Properties();
    rect_3.Properties();
    // change rectangle 3
    rect_3.ChangeBounds(0, 0, 50, 100);
    rect_3.Properties();
    // change rectangle 2
    rect_2.ChangeBounds(0, 0, 100, 50);
    rect_2.Properties();
    // change rectangle 1
    rect_1.ChangeBounds(0, 0, 50, 100);
    rect_1.Properties();

    // now rectangles 2 and 3 should not be the same but they should be similar
    System.out.println("Test if Rectangles 2 & 3 are the same");
    if (rect_2.SameAs(rect_3)) {
      System.out.println("Yep!");
    } else {
      System.out.println("Nope!");
    }
    System.out.println("Test if Rectangles 2 & 3 are similar");
    if (rect_2.SimilarTo(rect_3)) {
      System.out.println("Yep!");
    } else {
      System.out.println("Nope!");
    }
    System.out.println("Test if Rectangles 1 & 3 are the same");
    if (rect_1.SameAs(rect_3)) {
      System.out.println("Yep!");
    } else {
      System.out.println("Nope!");
    }


  }
}
