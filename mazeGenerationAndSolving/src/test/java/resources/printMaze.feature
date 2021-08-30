@run
Feature: Maze can be printed

    Scenario: Given maze is printed correctly
    Given a maze is created
    When the maze is printed
    Then system will respond with "# #####/# #   #/# # # #/#   # #/# ### #/#   # #/##### #/"


    Scenario: Created maze is correct size
    Given a depth-first search is selected
    And the height is set to "10"
    And the width is set to "20"
    When the the maze is generated and printed
    Then system's response contains "Maze generated with depth-first search:"
    Then the width of maze is "41" characters
    Then the length of maze is "21" characters
