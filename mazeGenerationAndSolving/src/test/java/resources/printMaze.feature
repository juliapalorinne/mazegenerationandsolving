@run
Feature: Maze can be printed

    Scenario: User can print a maze
    Given a maze is created with a depth-first search
    When command printMaze is selected
    Then system will respond with ""

