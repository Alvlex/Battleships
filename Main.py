from pathlib import _Accessor


class Main:
    def menu(self):
        menu_choice = input("Menu\n(1)Play a normal game\n(2)Play a test game")
        return menu_choice

    def set_up_game(self):
        board = [10][10]
        two_ships = Ships[4]
        for i in range(0, 4):
            two_ships[i].how_big = 2
            two_ships[i] = Ships
            two_ships[i].coordinates = Main.random_generation(self, 2)
        three_ships = Ships[3]
        for i in range(0, 3):
            three_ships[i].how_big = 3
            three_ships[i] = Ships
            three_ships[i].coordinates = Main.random_generation(self, 3)
        four_ships = Ships[2]
        for i in range(0, 2):
            four_ships[i].how_big = 4
            four_ships[i] = Ships
            four_ships[i].coordinates = Main.random_generation(self, 4)
        five_ship = Ships
        five_ship.how_big = 5
        five_ship = Ships
        five_ship.coordinates = Main.random_generation(self, 5)
        return two_ships, three_ships, four_ships, five_ship, board


    def random_generation(self, big):
        return Coordinates[big][1]

    def set_up_board(self, two_ships, three_ships, four_ships, five_ship, board):
        for i in range(0, two_ships.length):
            for j in range(0, 2):
                board[two_ships[i].coordinates[j][0]][two_ships[i].coordinates[j][1]] = '2'
        for i in range(0, three_ships.length):
            for j in range(0, 3):
                board[two_ships[i].coordinates[j][0]][two_ships[i].coordinates[j][1]] = '3'
        for i in range(0, four_ships.length):
            for j in range(0, 4):
                board[two_ships[i].coordinates[j][0]][two_ships[i].coordinates[j][1]] = '4'
        for j in range(0, 5):
            board[two_ships[i].coordinates[j][0]][two_ships[i].coordinates[j][1]] = '5'
        return board

    def display_board(self):
        for i in range(0, ):
            if i%2 == 0:
                for j in range(0, 21):
                    if j%2 == 1:
                        print("|")
                    elif j%2 == 0:
                        if i == 0 & j == 0:
                            print(" ")
                        elif j == 0:
                            if i/2 == 1:
                                print('A')
                            elif i/2 == 2:
                                print('B')
                        elif i == 0:
                            print(j/2)
                        else:
                            print(board[i/2][j/2])
            else:
                Main.make_dashes()
    def make_dashes(self):
        print("--------------------------")

    def get_coordinates(self, board):
        coordinates = input("Input a coordinate (A1 - J10)")
        y = coordinates.index("", 0, 1)
        #letter
        x = coordinates.index("", 1, coordinates.length) - 1
        #number
        if y == 'A':
            y = 0
            if Main.check_if_hit(x, y, board):
                print("s")

    def check_if_hit(self, x, y, board):
        if board[x][y] != "":
            print("You hit a ship!")
            board[x][y] = 'X'
            for i in range(0, 4):
                for j in range(0, 2):
                    if two_ships[i].coordinates[j][0] == x & two_ships[i].coordinates[j][1] == y:
                        ship_hit = two_ships[i]
            for i in range(0, 3):
                for j in range(0, 3):
                    if three_ships[i].coordinates[j][0] == x & two_ships[i].coordinates[j][1] == y:
                        ship_hit = three_ships[i]
            for i in range(0, 2):
                for j in range(0, 4):
                    if four_ships[i].coordinates[j][0] == x & two_ships[i].coordinates[j][1] == y:
                        ship_hit = four_ships[i]
            for i in range(0,5):
                if five_ship.coordinates[j][0] == x & five_ship.coordinates[j][1] == y:
                    ship_hit = five_ship
            if Main.check_if_sunk(ship_hit, board):
                print("You sunk a battleship!")

    def check_if_sunk(self, ship_hit, board):
        counter = 0
        for i in range(0, ship_hit.coordinates.length):
            if board[ship_hit.coordinates[i][0]][ship_hit.coordinates[i][1]] == 'X':
                counter += 1
        if counter == ship_hit.how_big:
            ship_hit.is_sunk = True
            return True
        else:
            return False


class Ships:
    how_big = 0
    coordinates = Coordinates[how_big][1]
    for i in range(0, how_big):
        coordinates[i][0] = ' '
        coordinates[i][1] = ' '
    is_sunk = False


class Coordinates:
    x_coordinate = 0
    y_coordinate = 0
    set_visible = False
main = Main()
choice = main.menu()
if choice == 1:
    two_ships, three_ships, four_ships, five_ship, board = main.setUpGame()
    main.set_up_board(two_ships, three_ships, four_ships, five_ship, board)