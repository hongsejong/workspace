package edu.kh.array.ex;

import java.util.Arrays;

public class ArrayExample2 {

	// 얕은 복사 (shallow : 얕은)
	//->주소를 복사하여 서로 다른 두 변수가
	//  하나의 배열(또는 객체)을 참조하는 상태를 만드는 복사 방법이다


	public void shallowCopy() {
		int[] arr= {1,2,3,4,5};

		// 얕은 복사 진행
		int[] copyArr = arr;

		System.out.println("주소 확인");
		System.out.println("arr : " + arr);
		System.out.println("copyArr : " + copyArr);

		System.out.println("변경 전");
		System.out.println("arr :"+Arrays.toString(arr));
		System.out.println("copyArr :"+Arrays.toString(copyArr));

		// 얕은 복사한 배열의 값 변경
		copyArr[2]=555;

		System.out.println("변경 후");
		System.out.println("arr :"+Arrays.toString(arr));
		System.out.println("copyArr :"+Arrays.toString(copyArr));
	}

	//깊은 복사
	// ->같은 자료형의 새로운 배열을 만들어서
	//	기존 배열의 데이터를 모두 복사하는 방법

	public void deepCopy() {

		int[] arr = {1,2,3,4,5}; //원본 배열

		// 1.for문을 이용한 깊은 복사
		int[] copyArr1= new int[arr.length]; // 5칸짜리 배열 생성

		for(int i=0; i<arr.length; i++) {
			copyArr1[i]=arr[i];
		}

		//2. System.arraycopy(원본배열, 원본 복사 시작 인덱스,
		//					  복사배열, 복사배열의 삽입 시작 인덱스,복사길이);

		int[] copyArr2 = new int[arr.length];
		System.arraycopy(arr, 0, copyArr2, 0, arr.length);

		//3.복사할 배열 참조 변수 = Arrays.copyOf(원본 배열, 복사할 길이);
		int[] copyArr3= Arrays.copyOf(arr, arr.length);

		//값 변경
		copyArr1[4]=7;
		copyArr2[4]=1000;
		copyArr3[4]=9999;

		// Arrays.toString(배열명) : 참조하는 배열에 있는 모든 값을 하나의 문자열로 얻어옴
		System.out.println("arr : "+ Arrays.toString(arr));
		System.out.println("copyArr1 : "+ Arrays.toString(copyArr1));
		System.out.println("copyArr2 : "+ Arrays.toString(copyArr2));
		System.out.println("copyArr3 : "+ Arrays.toString(copyArr3));
	}

	//배열을 이용한 중복 데이터 제거 +정렬
	public void createLotto() {
		//로또 번호 생성

		//1. 1~45 사이 중복되지 않는 난수 6개 생성
		//2. 생성된 난수를 오름차순 정렬

		// 1) 정수 6개를 저장할 배열 선언 및 할당
		int[] lotto= new int[6];

		// 2) 생성된 배열을 처음부터 끝까지 순차 접근하는 for문 작성
		for(int i=0; i<lotto.length; i++) {
			// 3) 1~45 사이의 난수 생성
			int random=(int)(Math.random()*45+1);
			// 4) 생성된 난수를 순서대로 배열 요소에 대입
			lotto[i]=random;

			// 5)중복 검사를 위한 for문 작성
			for(int x=0; x<i; x++) {

				//6)현재 생성된 난수와 같은 수가
				//	앞 쪽 요소에 있는지 검사
				if(random==lotto[x]) {
					i--;
					// i가 1씩 증가할 떄 마다 난수가 하나 생성됨
					//-> 중복 값이 있으므로 난수를 하나 더 생성해야 된다
					//--> i는 기본적으로 0~5까지 6회반복 되지만
					//	  i값을 인위적으로 1씩 감소시켜 7회 반복하는 모양을 만듦
					break;
					//앞쪽에서 중복 데이터를 발견한다면
					//남은 값을 비교할 필요가 없음
					//->효율 향상을 위해서 중복 검사하는 for문 종료
				}
			}
		}//for 문 끝


		// 7) 오름차순 정렬
		// -> 선택,삽입,버블,퀵 등등
		//--> 자바가 정렬 방법을 미리 만들어서 제공하고 있음

		//Arrays.sort(배열명) : 배열 내 값들이 오름차순 정렬됨
		Arrays.sort(lotto);
		System.out.println(Arrays.toString(lotto));
	}
	
	


}
