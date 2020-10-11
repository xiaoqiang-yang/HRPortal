package com.example.hrapi.model;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrix<E> {
    int maxRow;
    int maxCol;

    public List<SparseItem<E>> getSparseItems() {
        return sparseItems;
    }

    List<SparseItem<E>> sparseItems = new ArrayList<>();

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }

    public static class SparseItem<T> {
        int row, col;
        private T value;
        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public SparseItem(int row, int col, T value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public String toString() {
            return "row: " + row + " col: " + col + " value: " + (value != null ? value.toString() : "");
        }
    }

    public void add(int row, int col, E element) {
        maxRow = row > maxRow ? row : maxRow;
        maxCol = col > maxCol ? col : maxCol;
        sparseItems.add(new SparseItem<E>(row, col, element));
    }

    public int getElementNumber() {
        return sparseItems.size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("maxRow = " + maxRow + " , maxCol = " + maxCol + "\n");
        for (int i = 0; i < sparseItems.size(); i++) {
            s.append(i + ": " + sparseItems.get(i) + "\n");
        }
        return s.toString();
    }
}
