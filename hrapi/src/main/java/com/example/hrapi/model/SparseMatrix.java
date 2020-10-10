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

    static class SparseItem<T> {
        int row, col;

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

        private T value;

        public SparseItem(int row, int col, T value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    public void add(int row, int col, E element) {
        maxRow = row > maxRow ? row : maxRow;
        maxCol = col > maxCol ? col : maxCol;
        sparseItems.add(new SparseItem<E>(row, col, element));
    }
    public int getElementNumber(){
        return sparseItems.size();
    }
}
