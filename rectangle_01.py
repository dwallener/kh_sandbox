#!/Users/damir00/miniconda3/bin/python3

# github personal access token
# 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

import copy

class Rectangle:

    def __init__(self, min_x, min_y, max_x, max_y):
        # define the shape properties
        # this currently only works for rectangles
        self.min_x = min_x
        self.min_y = min_y
        self.max_x = max_x
        self.max_y = max_y

    # let's give us a way of sharing shape properties to make testing easier
    def properties(self):
        print (f"Min x: {self.min_x}  Min y: {self.min_y} Max x: {self.max_x} Max y: {self.max_y}")

    def make_a_copy(self):
        return copy.deepcopy(self)
        pass

    # change the coordinates of the shape's vertices
    # the old coordinates are gone, forever, after this
    def change_bounds_to(self, new_min_x, new_min_y, new_max_x, new_max_y):
        self.min_x = new_min_x
        self.min_y = new_min_y
        self.max_x = new_max_x
        self.max_y = new_max_y

    # tons of assumptions about what "bounded" actually means
    # let's go with bounded means inside all the edges
    # so if a point is on an edge, it's outside, or at least not inside
    # at this point we are also assuming the rectangle is "flat" - all edges
    # are either vertical or horizontal
    def is_bounding(self, x, y):
        if x > self.min_x and x < self.max_x and y > self.min_y and y < self.max_y:
            return True
        else:
            return False

    # this assumes "equal" means identical shapes - ie, same coordinates
    def is_equal_to(self, shape_2):
        if self.min_x == shape_2.min_x and self.min_y == shape_2.min_y and self.max_x == shape_2.max_x and self.max_y == shape_2.max_y:
            return True
        else:
            return False

    # this assume "equal" means geometrically similar shapes
    # this is only slightly less naive than the above, only tested for integer multiples
    # of 90 degree rotations
    def is_similar_to(self, shape_2):
        # check the length of the x axis
        self_distance_x = self.max_x - self.min_x
        compare_distance_x = shape_2.max_x - shape_2.min_x
        # check the length of the y axis
        self_distance_y = self.max_y - self.min_y
        compare_distance_y = shape_2.max_y - shape_2.min_y
        # do the comparison
        if self_distance_x == compare_distance_x and self_distance_y == compare_distance_y:
            return True
        elif self_distance_x == compare_distance_y and self_distance_y == compare_distance_x:
            return True
        else:
            return False


################################################################################
# Start testing...


# now we construct a new rectangle
shape_1 = Rectangle(0, 0, 100, 100)
# shape properties to sdout
shape_1.properties()
# copy the shape
shape_2 = shape_1.make_a_copy()
# test by sending properties to sdout
shape_2.properties()
# now change one of these instances to different shape
shape_1.change_bounds_to(10, 10, 20, 20)
# confirm we now have two different shapes
shape_1.properties()
shape_2.properties()

# test the bounding check
# (5,5) will be inside one rectangle, outside the other
coords = (5,5)
print (f"Bounding test for coords {coords}")
print ("Shape 1")
if shape_1.is_bounding(coords[0], coords[1]):
    print ("Bounding!")
else:
    print ("Not bounding!")
print ("Shape 2")
if shape_2.is_bounding(coords[0], coords[1]):
    print ("Bounding!")
else:
    print ("Not bounding!")

# test the Naive Equality
# make a third shape the same as one of the first two shapes
shape_3 = shape_1.make_a_copy()
shape_1.properties()
shape_2.properties()
shape_3.properties()

# make two tests...
if shape_1.is_equal_to(shape_2):
    print ("Two shapes are equal!")
else:
    print ("Two shapes are not equal!")
if shape_1.is_equal_to(shape_3):
    print ("Two shapes are equal!")
else:
    print ("Two shapes are not equal!")

# now test for similarity
# first create a new shape that is 90 degrees rotated from one of the other shapes
shape_4 = Rectangle(0, 0, 50, 100)
shape_5 = Rectangle(0, 0, 100, 50)
shape_4.properties()
shape_5.properties()
# do the test
# this should fail...
if shape_5.is_equal_to(shape_4):
    print ("Two shapes are equal!")
else:
    print ("Two shapes are not equal!")
# this should pass
if shape_5.is_similar_to(shape_4):
    print ("Two shapes are similar!")
else:
    print ("Two shapes are not similar!")
