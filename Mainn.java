public class Mainn {
        public int[] method1 ( int[] arr){
            int x = 0;
            int [] result = null;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 4) {
                    x = arr[i];
                    result = new int[arr.length - i - 1];
                    for (int j = i + 1, k = 0; j < arr.length; j++, k++) {
                        if (arr[j] == 4) continue;
                        result[k] = arr[j];
                    }
                }
            }
            if (result==null) throw new RuntimeException();
            return result;
        }

        public boolean method2 ( int[] arr){
            boolean check = false;
            for (int i = 0; i < arr.length; i++) {
                if ((arr[i] == 1) || (arr[i] == 4)) check = true;
            }
            return check;
        }
    }

