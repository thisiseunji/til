package array;

public class Array {

	public static void main(String[] args) {
        int[] myArr = new int[10];
        myInsert(myArr, 3, 10);
        System.out.println();
        myErase(myArr, 3);
    }

    private static void myInsert(int[] myArr, int index, int value) {
        int[] newArr = new int[myArr.length+1];
        for(int i = 0; i<index; i++) {
        	
            newArr[i] = myArr[i];
        }
        newArr[index] = value;

        for(int i = myArr.length-1; i > index; i--) {
            newArr[i+1] = myArr[i];           
        } 
//        for(int i = index; i < myArr.length; i++) {
//            answer[i+1] = myArr[i];           
//        } //강의에서는 끝에서부터 구현
        for(int n :  newArr) {
        	System.out.print(n+" ");
        }
    }
    private static void myErase(int[] myArr, int index) {
        int[] newArr = new int[myArr.length-1];

        for(int i = 0; i < index; i++) {
        	newArr[i] = myArr[i];
        }
        for(int i = myArr.length-1; i > index; i--) {
            newArr[i-1] = myArr[i];
        }

        for(int n :  newArr) {
        	System.out.print(n+" ");
        }

    }
}

