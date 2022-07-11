import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        // создаем двумерный массив 5 на 5
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите количество строк:");
        int rows = scan.nextInt();
        System.out.println("Введите количество столбцов:");
        int cols = scan.nextInt();

        int[][] array = reset(rows, cols);
        System.out.println("Исходный массив:");
        print(array);

        // Выбор заполнения матрицы
        int choice = 0;
        while(choice != 5) {
            System.out.println("Выберите вариант заполнения матрицы (цифру) : (1) крест по диагонали, (2) крест по вертикали, (3) квадрат, (4) ромб, (5) выход из программы.");
            System.out.print("Выбранный вариант: ");
            array = reset(rows, cols);
            choice = scan.nextInt();
            switch (choice) {
                case (1): {
                    diagonal(array);
                    print(array);
                    break;
                }


                case (2): {
                    verticalAndHorizontal(array);
                    print(array);
                    break;
                }

                case (3): {
                    square(array);
                    print(array);
                    break;
                }

                case (4): {
                    rhombus(array);
                    print(array);
                    break;
                }

                case (5):
                {
                    System.out.println("Выход из программы");
                    System.exit (0);
                }
            }
        }
    }

    static int[][] reset(int rows, int cols){
        int[][] array = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = 0;
            }
        }
        return array;
    }

    static void verticalAndHorizontal(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length / 2) {
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = 1;
                }
            } else {
                for (int j = 0; j < arr.length; j++) {
                    if (j == arr.length / 2) {
                        arr[i][j] = 1;
                    }
                }
            }
        }
    }

    static void diagonal(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j || j == arr.length - 1 - i) {
                    arr[i][j] = 1;
                }
            }
        }
    }

    static void square(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || i == arr.length - 1) {
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j] = 1;
                }
            } else {
                for (int j = 0; j < arr.length; j++) {
                    if (j == 0 || j == arr.length - 1) {
                        arr[i][j] = 1;
                    }
                }
            }
        }
    }

    public static void rhombus(int[][] arr) {
        int k = 1;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || i == arr.length - 1) {
                for (int j = 0; j < arr.length; j++) {
                    if (j == arr.length / 2) {
                        arr[i][j] = 1;
                    }
                }
            } else if (i == arr.length / 2) {
                for (int j = 0; j < arr.length; j++) {
                    if (j == 0 || j == arr.length - 1) {
                        arr[i][j] = 1;
                    }
                }
            } else if (i > arr.length / 2) {
                for (int j = 0; j < arr.length; j++) {
                    if (j == k || j == arr.length - 1 - k) {
                        arr[i][j] = 1;
                    }
                }
                k++;
            } else {
                for (int j = 0; j < arr.length; j++) {
                    if (j == arr.length / 2 + i || j == arr.length / 2 - i) {
                        arr[i][j] = 1;
                    }
                }
            }

        }
    }

    static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}