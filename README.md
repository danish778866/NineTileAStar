# Nine Tile Problem
The problem consists of a permutation of tiles numbered 1-8 arranged in a 3x3 matrix along with a blank tile.

# Goal
The aim is find the steps in order to arrange the 3x3 matrix of tiles in the following order:

<table>
    <tr>
        <td>1</td>
        <td>2</td>
        <td>3</td>
    </tr>
    <tr>
        <td>4</td>
        <td>5</td>
        <td>6</td>
    </tr>
    <tr>
        <td>7</td>
        <td>8</td>
        <td> </td>
    </tr>
</table>

# Rules
1. The blank tile can be swapped with any non-diagonal adjacent tile (Left, Right, Up, Down)
2. No other movement/swap is allowed.

# Example Initial Configuration

<table>
    <tr>
        <td>1</td>
        <td>8</td>
        <td>4</td>
    </tr>
    <tr>
        <td>7</td>
        <td>2</td>
        <td>6</td>
    </tr>
    <tr>
        <td>3</td>
        <td> </td>
        <td>5</td>
    </tr>
</table>

# Note
An initial configuration might be unsolvable.

# AStar search
AStar search is the most widely known form of best-first search that aims at minimizing the total estimated solution cost. This is achieved by the search strategy by using heuristic functions in order to optimize decision making.
