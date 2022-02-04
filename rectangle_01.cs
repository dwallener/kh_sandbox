namespace Namespace {

    using copy;

    using System;

    public static class Module {

        public class Circle {

            public Circle(object center, object radius) {
                this.center = (0, 0);
                this.radius = 10;
            }

            public virtual object properties() {
                Console.WriteLine("Center: {self.center} Radius: {self.radius}");
            }

            public virtual object is_bounding(object x, object y) {
                // this is not the most computationally efficient way, but it works
                var pythag = Math.Pow(x - this.center[0], 2) + Math.Pow(y - this.center[1], 2);
                if (pythag < Math.Pow(this.radius, 2)) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        public class Rectangle {

            public Rectangle(object min_x, object min_y, object max_x, object max_y) {
                // define the shape properties
                // this currently only works for rectangles
                this.min_x = min_x;
                this.min_y = min_y;
                this.max_x = max_x;
                this.max_y = max_y;
            }

            // let's give us a way of sharing shape properties to make testing easier
            public virtual object properties() {
                Console.WriteLine("Min x: {self.min_x}  Min y: {self.min_y} Max x: {self.max_x} Max y: {self.max_y}");
            }

            // the simplest copy constructor...for this assignment deep (recursive)
            // copying may not be necessary, but why take chances. :)
            public virtual object make_a_copy() {
                return copy.deepcopy(this);
            }

            // change the coordinates of the shape's vertices
            // the old coordinates are gone, forever, after this
            public virtual object change_bounds_to(object new_min_x, object new_min_y, object new_max_x, object new_max_y) {
                this.min_x = new_min_x;
                this.min_y = new_min_y;
                this.max_x = new_max_x;
                this.max_y = new_max_y;
            }

            // tons of assumptions about what "bounded" actually means
            // let's go with bounded means inside all the edges
            // so if a point is on an edge, it's outside, or at least not inside
            // at this point we are also assuming the rectangle is "flat" - all edges
            // are either vertical or horizontal
            // This has NOT been tested with a coordinate system allowing negative numbers
            public virtual object is_bounding(object x, object y) {
                if (x > this.min_x && x < this.max_x && y > this.min_y && y < this.max_y) {
                    return true;
                } else {
                    return false;
                }
            }

            // this assumes "equal" means identical shape in identical orientation
            public virtual object is_equal_to(object shape_2) {
                if (this.min_x == shape_2.min_x && this.min_y == shape_2.min_y && this.max_x == shape_2.max_x && this.max_y == shape_2.max_y) {
                    return true;
                } else {
                    return false;
                }
            }

            // this assume "equal" means geometrically similar shapes
            // this is only slightly less naive than the above, only tested for integer multiples
            // of 90 degree rotations
            public virtual object is_similar_to(object shape_2) {
                // check the length of the x axis
                var self_distance_x = this.max_x - this.min_x;
                var compare_distance_x = shape_2.max_x - shape_2.min_x;
                // check the length of the y axis
                var self_distance_y = this.max_y - this.min_y;
                var compare_distance_y = shape_2.max_y - shape_2.min_y;
                // do the comparison
                if (self_distance_x == compare_distance_x && self_distance_y == compare_distance_y) {
                    return true;
                } else if (self_distance_x == compare_distance_y && self_distance_y == compare_distance_x) {
                    return true;
                } else {
                    return false;
                }
            }

            // now let's see if we can do this by overloading the "==" or __EQ__ operator...
            public virtual object @__eq__(object other) {
                if (this.is_equal_to(other)) {
                    return true;
                } else {
                    return false;
                }
            }

            // is it even possible to overload "=" (__setitem__) in python?
            public virtual object @__setitem__(object other) {
            }
        }

        public static object shape_1 = Rectangle(0, 0, 100, 100);

        static Module() {
            shape_1.properties();
            shape_2.properties();
            shape_1.change_bounds_to(10, 10, 20, 20);
            shape_1.properties();
            shape_2.properties();
            shape_9.properties();
            shape_9.properties();
            shape_1.properties();
            shape_2.properties();
            shape_3.properties();
            shape_4.properties();
            shape_5.properties();
            circle_1.properties();
        }

        public static object shape_2 = shape_1.make_a_copy();

        public static object shape_9 = shape_1;

        public static object shape_9 = shape_2;

        public static object coords = (5, 5);

        public static object shape_3 = shape_1.make_a_copy();

        public static object shape_4 = Rectangle(0, 0, 50, 100);

        public static object shape_5 = Rectangle(0, 0, 100, 50);

        public static object circle_1 = Circle((0, 0), 10);

        public static object coords = (5, 5);

        public static object coords = (20, 20);
    }
}
