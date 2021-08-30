@run
Feature: Maze can be printed

    Scenario: Given maze is printed
        Given a maze is created
        When the maze is printed
        Then system will respond with "# #####/# #   #/# # # #/#   # #/# ### #/#   # #/##### #/"


    Scenario: Maze is created with depth-first search
        Given the option "1" is selected
        And the height is set to "10"
        And the width is set to "20"
        When the the maze is generated and printed
        Then system's response contains "Maze generated with depth-first search:"
        Then the length of maze is "21" characters
        Then the width of maze is "41" characters


    Scenario: Maze is created with Kruskal's algorithm
        Given the option "2" is selected
        And the height is set to "20"
        And the width is set to "40"
        When the the maze is generated and printed
        Then system's response contains "Maze generated with Kruskal's algorithm:"
        Then the length of maze is "41" characters
        Then the width of maze is "81" characters
    
    Scenario: Maze is solved with breadth-first search
        Given the option "1" is selected
        And the height is set to "10"
        And the width is set to "20"
        And the option "1" is selected
        When the the maze is generated and printed
        Then system's response contains "Solved with wall follower:"
    
    Scenario: Maze is solved with breadth-first search
        Given the option "3" is selected
        And the height is set to "10"
        And the width is set to "20"
        And the option "2" is selected
        When the the maze is generated and printed
        Then system's response contains "Solved with breadth-first search:"
    