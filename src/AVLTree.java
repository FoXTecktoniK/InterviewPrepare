import org.jetbrains.annotations.NotNull;

public class AVLTree{
    public AVLTree left, right;
    public int value;
    private int height;

    public AVLTree(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 0;
    }


    public static AVLTree insert(int value, AVLTree t) {
        if (t == null) {
            t = new AVLTree(value);
        } else {
            if (value < t.value) {
                t.left = insert(value, t.left);
                if (getHeight(t.left) - getHeight(t.right) == 2) {
                    if (getHeight(t.left) - getHeight(t.right) == 2) {
                        if (value < t.left.value)
                            t = simpleRightTurn(t);
                        else
                            t = bigRightTurn(t);
                    }
                }
            } else {
                t.right = insert(value, t.right);
                if(getHeight(t.right) - getHeight(t.left) == 2) {
                    if (value > t.right.value)
                        t = simpleLeftTurn(t);
                    else
                        t = bigLeftTurn(t);
                }
            }
        }
        t.height = Math.max(getHeight(t.left), getHeight(t.right)) + 1;
        return t;
    }

    public static int getHeight(AVLTree t) {
        if (t == null) return 0;
        return t.height;
        //return Math.max(getHeight(t.left), getHeight(t.right)) + 1;
    }

    private static AVLTree bigLeftTurn(@NotNull AVLTree t) {
        t.right = simpleRightTurn(t.right);
        t = simpleLeftTurn(t);
        return t;
    }

    private static AVLTree bigRightTurn(@NotNull AVLTree t) {
        t.left = simpleLeftTurn(t.left);
        t = simpleRightTurn(t);
        return t;
    }

    private static AVLTree simpleLeftTurn(@NotNull AVLTree t) {
        AVLTree par = t.right;
        t.right = par.left;
        par.left = t;
        t.height = Math.max(getHeight(t.left), getHeight(t.right)) + 1;
        par.height = Math.max(getHeight(t), getHeight(par.right)) + 1;
        return par;
    }

    private static AVLTree simpleRightTurn(@NotNull AVLTree t) {
        AVLTree par = t.left;
        t.left = par.right;
        par.right = t;
        t.height = Math.max(getHeight(t.left), getHeight(t.right)) + 1;
        par.height = Math.max(getHeight(par.left), getHeight(par.right));
        return par;
    }

    public static void inOrder(AVLTree t) {
        if (t == null) return;
        inOrder(t.left);
        System.out.println(t.value);
        inOrder(t.right);
    }

    public static void preOrder(AVLTree t) {
        if (t == null) {
            return;
        }
        System.out.println(t.value);
        preOrder(t.left);
        preOrder(t.right);
    }

    public static void postOrder(AVLTree t) {
        if (t == null) {
            return;
        }
        postOrder(t.left);
        postOrder(t.right);
        System.out.println(t.value);
    }

    private static AVLTree findAndDeleteNextMin(AVLTree t) {
        if (t == null) return null;
        if (t.right != null) {
            t = t.right;
            while (t.left != null && t.left.left != null)
                t = t.left;
        }
        if (t.left != null) {
            AVLTree tmp = t.left;
            t.left = tmp.right;
            t = tmp;
        }
        return t;
    }


    public static boolean delete(int value, AVLTree t) {
        if (t == null) return false;
        if (value < t.value) {
            if (t.left == null) return false;
            if (t.left.value == value) {
                AVLTree newNode = findAndDeleteNextMin(t.left);
                if (newNode != null) {
                    newNode.left.left = t.left.left;
                    newNode.left.right = t.left.right;
                    delete(newNode.value, newNode);
                }
                t.left = newNode;
            } else
                return delete(value, t.left);
        } else if (value > t.value){
            if (t.right == null) return false;
            if (t.right.value == value) {
                AVLTree newNode = findNextMin(t.right);
                if (newNode != null) {
                    newNode.left.left = t.right.left;
                    newNode.left.right = t.right.right;
                    delete(newNode.value, newNode);
                }
                t.right = newNode;
            } else
                return delete(value, t.right);
        }
        return true;
    }
}
