//로또번호생성기
// package com.kej.array;

import java.util.Arrays;

public class Lotto {
    public void method11() {
		/*
		 * 로또 번호 자동생성 프로그램
		 * 로또 룰:
		 * 1. 1-45까지 랜덤한 숫자 중 6개를 뽑는다. 숫자 중복 안된다. => 중복검사
		 * 2. 뽑힌 숫자들을 작은 숫자에서부터, 큰 숫자 순서대로 나열까지 해야함(오름차순)
		 * 3. Arrays.toString();  형식으로 출력
		 */
		
		int[] arr = new int[6];
		int num;
		int cnt = 0;
		
		while(cnt < arr.length) {
			num = (int)(Math.random()*45)+1;
			arr[cnt] = num;
			for (int i =0; i < cnt; i++){
				if (arr[cnt] == arr[i]) {
					cnt--;
					break;
				}
			}
			cnt++;
		}
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}	
}