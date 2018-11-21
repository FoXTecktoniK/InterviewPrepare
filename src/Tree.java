public class Tree {
    public int value;
    public Tree left, right;

    public Tree() {

    }

    public Tree(int value) {
         this.value = value;
    }

    public void insert(int value) {
         if (value <= this.value) {
            if (left == null) {
                this.left = new Tree(value);
            } else {
                left.insert(value);
            }
         } else {
             if (right == null) {
                 right = new Tree(value);
             } else {
                 right.insert(value);
             }
         }
    }

    public void remove(int value) {

    }

    public int getHeight() {
        int leftHeight = this.left == null ? 0 : this.left.getHeight();
        int rightHeight = this.right == null ? 0 : this.right.getHeight();
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
