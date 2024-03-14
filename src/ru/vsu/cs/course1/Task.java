package ru.vsu.cs.course1;


public class Task {

    public static int commonDeterminant(int[][] matrix) {
        int size = matrix.length;
        if (size ==  1) {
            return matrix[0][0];
        }

        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int det =  0;
        for (int columnIndex =  0; columnIndex < size; ++columnIndex) {
            int[][] smallerMatrix = new int[size -  1][size -  1];
            for (int row =  1, rowForSmallerMatrix =  0; row < size; ++row) {
                for (int column =  0, columnForSmallerMatrix =  0; column < size; ++column) {
                    if (column != columnIndex) {
                        smallerMatrix[rowForSmallerMatrix][columnForSmallerMatrix] = matrix[row][column];
                        ++columnForSmallerMatrix;
                    }
                }
                ++rowForSmallerMatrix;
            }
            det += Math.pow(-1, columnIndex) * matrix[0][columnIndex] * commonDeterminant(smallerMatrix);
        }
        return det;
    }

    



    public static int[] numberToArray (int a) {
        int[] arr = new int[1];
        arr[0] = a;
        return arr;
    }

    public static int[][] replaceColumnWithLast(int[][] matrix, int n) {
        if (matrix == null || matrix.length == 0 || n < 0 || n >= matrix[0].length) {
            throw new IllegalArgumentException("Неверные аргументы");
        }

        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == n) {
                    newMatrix[i][j] = matrix[i][matrix[0].length - 1];
                } else if (j == matrix[0].length - 1) {
                    newMatrix[i][j] = matrix[i][n];
                } else {
                    newMatrix[i][j] = matrix[i][j];
                }
            }
        }
        return newMatrix;
    }

    public static int[][] findMatrixRoots(int[][] matrix) {
        int n = matrix.length;
        int[][] roots = new int[n][n];
        int determinant = commonDeterminant(matrix);

        for (int i = 0; i < matrix[0].length - 1; i++) {
            int sideDeterminant = commonDeterminant(replaceColumnWithLast(matrix, i));
            roots[0][i] = sideDeterminant / determinant;
        }
        return roots;
    }

    public static int[][] findMatrixSideDeterminants(int[][] matrix) {
        int n = matrix.length;
        int[][] dets = new int[n][n];

        for (int i = 0; i < matrix[0].length - 1; i++) {
            int sideDeterminant = commonDeterminant(replaceColumnWithLast(matrix, i));
            dets[0][i] = sideDeterminant;
        }
        return dets;
    }

    public static int[][] multiplyMatrix(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int columnsA = matrixA[0].length;
        int columnsB = matrixB[0].length;

        if (columnsA != matrixB.length) {
            throw new IllegalArgumentException("Матрицы невозможно перемножить");
        }

        int[][] result = new int[rowsA][columnsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsB; j++) {
                for (int k = 0; k < columnsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }


}
