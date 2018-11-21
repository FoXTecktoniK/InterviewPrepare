public class Main {
    public static void main(String[] args) {
        int arr[] = {1, 5, 7, 9, -3, 6, 8};
        //Tree myTree = new Tree(4);
        AVLTree myTree = new AVLTree(3);
        for (int i : arr) {
            myTree = AVLTree.insert(i, myTree);
        }
        System.out.println("Inserted");
        AVLTree.inOrder(myTree);
        AVLTree.delete(7, myTree);
        AVLTree.inOrder(myTree);
    }
}
