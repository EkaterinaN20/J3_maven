public class MyTree {
        public static void main(String[] params) {
            // не поняла, как сделать иначе, поэтому просто преобразовала массив в дерево, добавив вершину наверх
            Tree root =
                    new Tree(0,
                            new Tree(1,
                                    new Tree(3,
                                            new Tree(4, new Tree(5), null,null), null, null), null, null),
                            new Tree(2),
                            new Tree(6));

            System.out.println("Сумма дерева: " + root.sum());
        }

        static class Tree {
            int index;
            Tree left;
            Tree middle;
            Tree right;
// конструктор для непустой ветви
            public Tree(int index, Tree left, Tree middle, Tree right) {
                this.index = index;
                this.left = left;
                this.middle = middle;
                this.right = right;
            }
            // конструктор для листа
            public Tree(int index) {
                this.index = index;
            }

            public int sum() {
                int summ = index;
                System.out.println("Посещен узел " + index);
                if (left != null) {
                    summ += left.sum();
                    System.out.println("Посещен узел " + index);
                }
                if (middle!= null) {
                    summ += middle.sum();
                    System.out.println("Посещен узел " + index);
                }
                if (right != null) {
                    summ += right.sum();
                    System.out.println("Посещен узел " + index);
                }

                return summ;
            }
        }
    }

