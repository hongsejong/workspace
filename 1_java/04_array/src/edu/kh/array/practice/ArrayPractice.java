package edu.kh.array.practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayPractice {

	public void practice1() {

		/*
		 * 메소드명: public void practice1(){}
   길이가 9인 배열을 선언 및 할당하고,1부터 9까지의 값을 반복문을 이용하여
   순서대로 배열의 각 인덱스 요소에대입하고출력한후
   짝수번째 인덱스 값의 합을 출력하세요. (0 번째인덱스는짝수로취급)
 [실행화면]
 1 2 3 4 5 6 7 8 9
짝수번째인덱스합: 25
		 * */
		//인덱스 0 1 2 3 4 5 6 7 8 9
		//  값  1 2 3 4 5 6 7 8 9

		int[] arr = new int[9];
		int sum=0;
		for(int i=0; i<arr.length; i++) {
			arr[i]=i+1;

			System.out.print(arr[i]+" ");
			if(i%2==0) {
				sum+=arr[i];
			}

		}

		System.out.println("\n짝수번째 인덱스 합 : "+sum);
	}

	public void practice2() {
		/*길이가 9인 배열을 선언 및 할당하고, 9부터 1까지의 값을 반복문을 이용하여 
		 * 순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후
		 * "홀수 번쨰" 인덱스 값의 합을 출력하세요(0번쨰 인덱스는 짝수로 취급)
		 * 
		 * */
		int sum=0;
		int[] arr = new int[9];
		for(int i=0; i<arr.length; i++) {
			arr[i]=arr.length-i;

			System.out.print(arr[i]+" ");
			if(i%2==1) {
				sum+=arr[i];
			}
		}


		System.out.println("\n홀수 번째 인덱스 합 :"+sum);
	}

	public void practice3() {
		/*사용자에게 입력받은 양의 정수만큼 배열 크기를 할당하고
		 * 1부터 입력 받은 값까지 배열에 초기화 한 후 출력하세요
		 * */

		Scanner sc = new Scanner(System.in);
		System.out.print("양의 정수 :");
		int input = sc.nextInt();

		int[] arr = new int[input];

		for(int i=0; i<arr.length; i++) {


			arr[i]=i+1;

			System.out.print(arr[i]+" ");
		}



	}

	public void practice4() {

		Scanner sc = new Scanner(System.in);
		int[] arr= new int[5];
		int search;
		int count=0;

		for(int i=0; i<arr.length;i++) {
			System.out.print("입력"+i+":");
			arr[i]=sc.nextInt();
		}
		System.out.print("검색할 값 :");
		search=sc.nextInt();

		for(int i=0; i<arr.length;i++) {

			if(arr[i]==search) {
				System.out.print("인덱스 :"+i);
				count++;
			}

		}
		if(count==0) {
			System.out.println("일치하는 값이 존재하지 않습니다");

		}

	}

	/*문자열을 입력 받아 문자 하나하나를 배열에 넣고 검색할 문자가 문자열에 몇개 들어가 있는지
	 * 개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요
	 * */

	//3.String.charAt(index) : 문자열에서 특정 index에 위치한 문자 하나를 얻어옴
	//  ex) "Hello".charAt(1) -> 'e'

	public void practice5() {


		Scanner sc = new Scanner(System.in);
		String input;   
		int count = 0;


		System.out.print("문자열 :");
		input=sc.nextLine();
		System.out.println("문자");
		char m=sc.nextLine().charAt(0);
		char[] arr = new char[input.length()];
		System.out.print(input+"에 " + m +" 가 존재하는 위치(인덱스) : ");
		for(int i=0; i<arr.length; i++) {

			arr[i]=input.charAt(i);
			//   System.out.println(arr[i]);
			if(arr[i]==m) {
				System.out.print(i+ " ");
				count++;
			}else {
				continue;
			}

		}   
		System.out.print("\n"+m+"개수 :"+count);
	}

	public void practice6() {
		/*사용자가 배열의 길이를 직접 입력하여 그 값만큼 정수형 배열을 선언 및 할당하고
      배열의 크기만큼 사용자가 직접 값을 입력하여 각각의 인덱스에 값을 초기화하세요
      그리고 배열 전체 값을 나열하고 각 인덱스에 저장된 값들의 합을 출력*/
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 :");
		int input = sc.nextInt();
		int sum=0;
		int[] arr = new int[input]; 
		for(int i=0; i<input; i++) {
			System.out.print("배열"+i+"번째 인덱스에 넣을 값 :");
			int input1=sc.nextInt();
			arr[i]=input1;
			sum+=input1;
		}
		for(int i=0; i<input; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println("\n총 합 :"+sum);
	}

	public void practice7() {
		//주민등록번호 번호를 입력받아 char배열에 저장한 후 출력하세요
		//단,char 배열 저장 시 성별을 나타내는 숫자 이후부터 *로 저장하세요
		Scanner sc = new Scanner(System.in);
		System.out.print("주민등록번호(-포함) :");
		String input=sc.nextLine();
		char[] arr = new char[input.length()];

		for(int i=0; i<arr.length;i++) {
			if(i<=7) {
				arr[i]=input.charAt(i);
			}else {
				arr[i]='*';
			}
			System.out.print(arr[i]);
		}



	}

	public void practice8() {
		/*3이상인 홀수를 입력 받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고,
		 * 중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하세요
		 * 단,입력한 정수가 홀수가 아니거나 3 미만일경우 " 다시입력하세요"를 출력하고
		 * 다시 정수를 받도록 하세요
		 * [실행화면]
		 * 정수:4
		 * 다시 입력하세요
		 * 정수:-6						9
		 * 다시 입력하세요		1 2 3 4 5 4 3 2 1
		 * 정수:5               //7
		 * 1,2,3,2,1		  1,2,3,4,3,2,1   5일떄 3   7일떄 4   9일떄 5
		 * */


		Scanner sc = new Scanner(System.in);
		while(true) { //3이상의 홀수가 입력 될때까지 무한 반복
			System.out.print("정수 : ");
			int input= sc.nextInt();

			if(input % 2 == 0 || input<3) { // 3미만 이거나 짝수인경우
				System.out.print("다시 입력하세요.");
			}else {

				//입력 받은 정수 만큼의 크기를 가지는 배열 생성
				int[] arr = new int[input];

				int num=1; // arr 배열에 대입될 값

				for(int i = 0; i<arr.length; i++) {
					if(i<arr.length/2) {// 중간 이전까지 -> 증가
						arr[i] = num++;

					}else { // 중간 이후 ->감소
						arr[i]=num--;
					}
					//출력 시 , 추가 (단, 마지막 제외)
					if(i==arr.length-1) { //마지막 바퀴
						System.out.print(arr[i]);
					}else {
						System.out.print(arr[i]+",");

					}
				}
				break;// while문 반복 종료
			}




		}
	}

	public void practice9() {
		//10개의 값을 저장할수있는 정수형 배열을 선언 및 할당하고,
		//1~10사이의 난수를 발생시켜 배열에 초기화 한후 출력하세요

		int[] arr = new int[10];
		System.out.print("발생한 난수 :");
		for(int i=0;i<arr.length;i++) {
			arr[i]=(int)(Math.random()*10+1);
			System.out.print(arr[i]+" ");	
		}
	}
	public void practice10() {
		/*10개의 값을 저장할 수 있는 정수열 배열을 선언 및 할당하고
		 * 1~10 사이의 난수를 발생시켜 배열에 초기화 후
		 * 배열 전체 값과 그 값 중에서 최대값과 최소값을 출력하세요
		 * 
		 * [실행 화면]
		 * 발생한 난수 : ~~
		 * 최대값 : 
		 * 최소값 :
		 * */

		int[] arr= new int[10];
		System.out.print("발생한 난수 :");
		for(int i=0; i<arr.length;i++) {
			arr[i]=(int)(Math.random()*10+1);
			System.out.print(arr[i]+" ");
		}
		//최고 / 최저점 구하기
		int min=arr[0];
		int max=arr[0];
		// for 문을 이용해서 arr배열에 있는 모든 값과 
		//max,min값 비교
		// arr[i]값이 max보다 크면 max에 대입
		// arr[i]값이 min보다 작으면 min에 대입
		for(int i=0; i<arr.length;i++) {
			if(arr[i]>max) {// 최고점 비교
				max=arr[i]; 
			}else if(arr[i]<min){
				min=arr[i];
			}
		}

		System.out.println("\n최대값 : "+max);
		System.out.println("최소값 : "+min);

	}

	public void practice11() {
		//10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고
		//1~10 사이의 난수를 발생시켜 중복된 값이 없게 배열에 초기화 한후 출력하세요
		int random;
		int[] arr = new int[10];

		for(int i=0; i<arr.length; i++) {

			// 난수 생성 -> 대입(단,중복X)
			arr[i]=(int)(Math.random()*10+1);
			//중복 확인시 i값 감소 시켜서
			//다음 반복에서 현재 인덱스에 난수 덮어쓰기
			for(int x=0; x<i; x++) {
				//x의 최대값은 i보다 1작은 수

				//현재 생성된 난수가 앞서 대입된 난수와 같은 경우 == 중복
				if(arr[x]==arr[i]) {
					i--; //i를 1감소
					//바깥쪽 for문 반복 시 다시 i가 1 증가
					//-1+1 == 0 (제자리)
					break;		//중복되는 값을 찾으면 더이상 검사할 필요 X				
				}
			}
		}//출력용 for문
		for(int i=0; i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	/*		문자열을 입력 받아 문자열에 어떤 문자가 들어갔는지 배열에 저장하고 문자의 개수와 함께 출력하세요

		[실행화면]
		문자열:application
		문자열에 있는 문자: a,p,l,i,c,t,o,n
		문자 개수:8
	 */


	public void practice13_1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 :");
		String input=sc.nextLine();
		int count=0;
		System.out.print("문자열에 있는 문자 :");
		char [] arr = new char[input.length()];
		String arr1="";


		for(int i=0; i<input.length(); i++) {
			arr[i]=input.charAt(i);
			boolean m=true;
			for(int x=0; x<arr1.length();x++) {
				if(arr[i]==input.charAt(x)) {
					m=false;
				}
			}
			if(m) {
				arr1+=input.charAt(i);
				System.out.print(arr[i]+" ");
				count++;
			}


		}


		System.out.print("\n문자 개수 :"+ count);



	}
	public void practice13() {
		/*		문자열을 입력 받아 문자열에 어떤 문자가 들어갔는지 배열에 저장하고 문자의 개수와 함께 출력하세요

			[실행화면]
			문자열:application
			문자열에 있는 문자: a,p,l,i,c,t,o,n
			문자 개수:8
		 */
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 : ");
		String input = sc.nextLine();
		System.out.print("문자열에 있는 문자 :");
		char[] arr= new char[input.length()];
		int count=0; // 카운트용 변수

		for(int i=0; i<arr.length;i++) {
			arr[i]=input.charAt(i); // 문자열 -> char배열에 옮겨 담기
			boolean flag = true; //신호용 변수
			// flag==true : 중복 없음
			// flag==false : 중복 있음


			for(int x=0; x<i; x++) { //중복 검사용 for문
				if(arr[i]==arr[x]) { //현재 대입된 문자가 앞서 대입된 문자와 같다면 중복
					flag = false;
					break;
				}
			}

			if(flag) { //중복이 없을 경우 flag == true
				count++; // 카운트 1씩 증가
				if(i==0) {
					System.out.print(arr[i]);

				}else {
					System.out.print(", "+arr[i]);
				}
			}
		}//바깥쪽 for문 끝

		System.out.print("\n문자 개수 : "+ count);
	}

	public void practice14() {
		Scanner sc = new Scanner(System.in);
		System.out.print("배열의 크기를 입력하세요 :");
		int input=sc.nextInt();
		sc.nextLine();

	

			String[] arr= new String[input];
			for(int i=0; i<arr.length;i++) {
				System.out.print((i+1)+"번째 문자열 :");
				arr[i]=sc.nextLine();
				if(i==arr.length-1) {
					System.out.print("더 입력하시겠습니까?");
					char yn=sc.next().charAt(0);
					if(yn=='y') {
						System.out.print("더 입력하고 싶은 개수 :");
						int more =sc.nextInt();
						int moreArr=more+arr.length;
						String[] arr2=new String[moreArr];
						//						System.out.print(arr2.length);
						for(int j=0; j<arr2.length;j++) {
							arr2[j]=arr[j];
							for(int k=arr.length; k<arr2.length;k++) {
								System.out.print((k+1)+"번째 문자열 :");
								arr2[k]=sc.nextLine();
							}

						}
					}

					if(yn=='n') {
						System.out.println(Arrays.toString(arr));
						break;
					}
				}
			}

		
	}

	public void practice14_1() {
		Scanner sc = new Scanner(System.in);

		System.out.print("배열의 크기를 입력하세요 :");
		int size=sc.nextInt();
		sc.nextLine(); //입력 버퍼에 남은 개행문자 제거

		String[] arr= new String[size]; //배열 선언 및 할당


		int start =0; // while문 내 for문의 초기식에 사용할 변수
		
		
		while(true) {
			for(int i=start; i<arr.length;i++) {
				System.out.print((i+1)+"번째 문자열 :");
				arr[i] = sc.nextLine();
			}
			System.out.print("더 값을 입력하시겠습니까?(Y/N)");
			char input=sc.nextLine().charAt(0);
			// 입력 받은 문자열 중 제일 앞 문자 하나만 얻어옴

			if(input=='Y' || input=='y') {
				
				start=arr.length;
				//추가 입력받기위한 추가 배열 부분의 시작 위치
				
				
				System.out.print("더 입력하고 싶은 개수 : ");
				int addSize=sc.nextInt();
				sc.nextLine();
				
				//증가된 크기의 배열을 생성하여 arr 배열
				String[] copyArr =new String[arr.length+addSize];
				for(int i=0; i<arr.length;i++) { //기존 배열 크기 만큼만 반복
					copyArr[i] = arr[i]; //복사본 배열에 기존 배열 값을 같은 인덱스에 대입
				}
				
			
				
				//배열 얕은 복사
				arr=copyArr; // arr이 참조하는 주소 값을
							// copyArr의 주소 값으로 바꿔서
							//arr이 참조하는 배열의 크기가 증가한 것 처럼 보이게 함
			}else {
				System.out.print(Arrays.toString(arr));
				break; //while 반복 종료
			}
		}

	}

}




