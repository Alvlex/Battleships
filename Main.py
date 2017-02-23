class Main:
    def menu(self):
        menu_choice = input("Menu\n(1)Play a normal game\n(2)Play a test game")
        return menu_choice

    def set_up_game(self):
        board = [10][10]
        two_ships = Ships[4]
        for i in range(0, 4):
            two_ships[i].how_big = 2
            two_ships[i].coordinates = Main.random_generation(self, 2)
        three_ships = Ships[3]
        for i in range(0, 3):
            three_ships[i].how_big = 3
            three_ships[i].coordinates = Main.random_generation(self, 3)
        four_ships = Ships[2]
        for i in range(0, 2):
            four_ships[i].how_big = 4
            four_ships[i].coordinates = Main.random_generation(self, 4)
        five_ship = Ships
        five_ship.how_big = 5
        five_ship.coordinates = Main.random_generation(self, 5)
        return two_ships, three_ships, four_ships, five_ship, board


    def random_generation(self, big):
        return Coordinates[big][1]

    def set_up_board(self, two_ships, three_ships, four_ships, five_ship, board):
        for i in range(0, two_ships.length):
            for j in range(0, 2):
                board[two_ships[i].coordinates[j][0]][two_ships[i].coordinates[j][1]] = '2'
        return board

    def get_coordinates(self, board):
        coordinates = input("Input a coordinate (A1 - J10)")
        y = coordinates.index("", 0, 1)
        x = coordinates.index("", 1, coordinates.__sizeof__())
        if y == 'A':
            board[x]

    def check_if_hit(self):
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
main = Main()
choice = main.menu()
if choice == 1:
    two_ships, three_ships, four_ships, five_ship, board = main.setUpGame()
    main.set_up_board(two_ships, three_ships, four_ships, five_ship, board)