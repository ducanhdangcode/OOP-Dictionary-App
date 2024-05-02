package com.example.appdictionary.commandline;

import javafx.scene.Parent;

import java.lang.reflect.Array;
import java.util.*;
class TreeNode {
    static final int ALL_CHAR = 95;
    private TreeNode[] children;
    private int idArray;

    /**
     * default constructor.
     */
    public TreeNode() {
        children = new TreeNode[ALL_CHAR];
        idArray = -1;
        for (int i = 0; i < ALL_CHAR; ++i) {
            children[i] = null;
        }
    }

    public TreeNode[] getChildren() {
        return this.children;
    }

    public int getIdArray() {
        return this.idArray;
    }

    public void setIdArray(int idx) {
        this.idArray = idx;
    }
}

public class Tree {
    private TreeNode root;

    /**
     * default constructor.
     */
    public Tree() {
        this.root = new TreeNode();
    }

    /**
     * constructor with 1 parameter.
     * @param root - root of the tree.
     */
    public Tree(TreeNode root) {
        this.root = root;
    }

    /**
     * reset ID function.
     * @param key - key of the word.
     * @param id - id of the word.
     */
    public void resetID(String key, int id) {
        TreeNode ptr = root;
        for (int i = 0; i < key.length(); ++i) {
            int idx = key.charAt(i) - ' ';
            if (idx > 94) continue;
            ptr = ptr.getChildren()[idx];
        }
        ptr.setIdArray(id);
    }

    public void insert(String key, int idx) {
        TreeNode ptr = root;
        for (int i = 0; i < key.length(); ++i) {
            int index = key.charAt(i) - ' ';
            if (index > TreeNode.ALL_CHAR) continue;
            if (ptr.getChildren()[index] == null) {
                ptr.getChildren()[index] = new TreeNode();
            }
            ptr = ptr.getChildren()[index];
        }
        ptr.setIdArray(idx);
    }

    public int search(String key) {
        TreeNode ptr = root;
        for (int i = 0; i < key.length(); ++i) {
            int index = key.charAt(i) - ' ';
            if (index >= TreeNode.ALL_CHAR) continue;
            if (ptr.getChildren()[index] != null) {
                ptr = ptr.getChildren()[index];
            } else {
                return -1;
            }
        }
        if (ptr != null && ptr.getIdArray() >= 0) {
            return ptr.getIdArray();
        } else {
            return -1;
        }
    }

    public int remove(String key) {
        TreeNode ptr = root;
        for (int i = 0; i < key.length(); ++i) {
            int index = key.charAt(i) - ' ';
            if (index >= TreeNode.ALL_CHAR) continue;
            if (ptr.getChildren()[index] != null) {
                ptr = ptr.getChildren()[index];
            } else {
                return -1;
            }
        }

        if (ptr != null && ptr.getIdArray() >= 0) {
            int res = ptr.getIdArray();
            ptr.setIdArray(-1);
            return res;
        } else {
            return -1;
        }
    }

    public ArrayList<String> suggestion(String s, boolean isPermitted) {
        ArrayList<String> possibleResult = new ArrayList<>();
        if (s.isEmpty() && !isPermitted) {
            return possibleResult;
        }
        String key = s.toLowerCase();
        TreeNode ptr = root;
        for (int i = 0; i < key.length(); ++i) {
            int index = s.charAt(i) - ' ';
            if (index >= TreeNode.ALL_CHAR) continue;
            if (ptr.getChildren()[index] != null) {
                ptr = ptr.getChildren()[index];
            } else {
                return possibleResult;
            }
        }
        for (int i = 0; i < TreeNode.ALL_CHAR; ++i) {
            if (ptr.getChildren()[i] != null) {
                char suggest_char = (char) ((int) ' ' + i);
                if (ptr.getChildren()[i].getIdArray() != -1) {
                    possibleResult.add(s + suggest_char);
                ArrayList<String> suggestionList = suggestion(s + suggest_char, isPermitted);
                possibleResult.addAll(suggestionList);
                }
            }
        }
        return possibleResult;
    }
}
