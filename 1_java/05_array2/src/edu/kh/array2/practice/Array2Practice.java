package edu.kh.array2.practice;

import java.util.Arrays;
import java.util.Scanner;

public class Array2Practice {


	public void practice1() {

		//3행 3열짜리 문자열 배열을 선언 및 할당하고
		//인덱스 0행 0열부터 2행 2열까지 차례대로 접근하여 "(0,0)"과 같은 형식으로 저장 후 출력
		//(0,0)(0,1)(0,2)
		//(1,0)(1,1)(1,2)
		//(2,0)(2,1)(2,2)

		String[][] arr = new String[3][3];
		for(int row=0; row<arr.length; row++) {

			for(int col=0; col<arr[row].length; col++) {

				arr[row][col] = "("+row+","+col+")";
				System.out.print(arr[row][col]);
			}
			System.out.println();
		}
	}


	public void practice2() {
		//4행 4열짜리 정수형 배열을 선언 및 할당하고
		//1~16까지 값을 차례대로 저장
		//저장된 값들을 차례대로 출력
		//1 2 3 4 
		//5 6 7 8 
		//9 10 11 12 
		//13 14 15 16 
		int i=1;
		int[][] arr= new int[4][4];
		for(int row=0; row<arr.length;row++) { //행 반복(0~3)
			for(int col=0; col<arr[row].length;col++) { //열 반복(0~3)
				arr[row][col]=i++;

				System.out.printf("%3d ",arr[row][col]);
			}
			System.out.println();
		}

	}

	public void practice3() {
		//4행 4열 정수 배열 선언 및 할당
		// 16~1과 같이 값을 거꾸로 저장
		// 저장된 값 차례로 출력
		//16 15 14 13
		//12 11 10 9
		//8 7 6 5
		//4 3 2 1


		int[][] arr= new int[4][4];
		int i= arr.length*arr[0].length;
		for(int row=0; row<arr.length;row++) {
			for(int col=0; col<arr[row].length;col++) {
				arr[row][col]=i;
				i--;

				System.out.printf("%3d",arr[row][col]);
			}
			System.out.println();
		}
	}

	public void practice4() {
		//4행 4열 2차원 배열 생성 / 0행0열부터 2행2열까지 1~10까지 임의의 정수 값 저장후 아래의 내용처럼 정리
		// 9 3 7 합 (0.3)
		// 3 3 7 합 (1.3)
		// 6 3 7 합 (2.3)
		//합 합 합총합 (3.3)
		//(3.3)(3.2)(3.1)	


		int[][] arr = new int[4][4];

		final int ROW_LAST_INDEX = arr.length-1; //행 마지막 인덱스
		final int COL_LAST_INDEX = arr[0].length-1; //열 마지막 인덱스
		for(int row=0; row<arr.length; row++) { //행 반복

			for(int col=0; col<arr[row].length; col++) { //열 반복

				//마지막 행, 마지막 열이 아닌 경우
				if(row!=ROW_LAST_INDEX && col !=COL_LAST_INDEX) {
					int random=(int)(Math.random()*10+1); // 1~10 난수

					arr[row][col]=random;

					//각 행의 마지막 열에 난수를 누적

					arr[row][COL_LAST_INDEX]+=arr[row][col];
					//각 열의 마지막 행에 난수를 누적
					arr[ROW_LAST_INDEX][col]+=arr[row][col];

					//생성된 모든 난수를 마지막 행, 마지막 열에 누적

					arr[ROW_LAST_INDEX][COL_LAST_INDEX]+=arr[row][col];
				}
				System.out.printf("%4d ",arr[row][col]);
			}//열 반복 끝
			System.out.println();
		}//행 반복 끝




		/*
		int[][] arr= new int[4][4];
			for(int row =0; row<arr.length;row++) {
				System.out.println();
				for(int col=0; col<arr[row].length;col++) {

					arr[row][col]=(int)(Math.random()*10+1);
					System.out.printf("%d ",arr[row][col]);
					int colSum=0;
					int rowSum=0;
					if(col==2) {
						colSum=arr[row][0]+arr[row][1]+arr[row][2];

						System.out.print(colSum+" ");
						break;
					}else if(row==2){
						rowSum=arr[0][col]+arr[1][col]+arr[2][col];
						System.out.print(rowSum+" ");
						break;
					}
				}
			}
		 */

		/*
		int[][] arr= new int[4][4];
		for(int row =0; row<arr.length-1;row++) {
			System.out.println();
			for(int col=0; col<arr[row].length-1;col++) {
				int rowSum=0;
				int colSum=0;

				arr[row][col]=(int)(Math.random()*10+1);
				System.out.printf("%d ",arr[row][col]);
				if(col==2) {
				colSum=	arr[row][0]+arr[row][1]+arr[row][2];
					System.out.print(colSum);
				}
				if(row==3) {
					for(int i=0;i<3;i++) {
						for(int j=0;j<3;j++) {
							rowSum=arr[i][j];
						}
						System.out.print(rowSum);
					}
				}



			}

		}
		 */
	}
	public void practice5() {

		//2차원 배열의 행과 열의 크기를 사용자에게 직접 입력받되, 1~10사이 숫자가 아니면
		//"반드시 1~10 사이의 정수를 입력해야합니다." 출력후 다시 정수를 받게 하세요
		//크기가 정해진 이차원 배열 안에는 영어 대문자가 랜덤으로 들어가게 한 뒤 출력하세요
		//char형은 숫자를 더해서 문자를 표현할수있고 65는 A를 나타냄,알파벳은 총 26글자

		Scanner sc= new Scanner(System.in);
		while(true) {
			System.out.print("행 크기 :");
			int row = sc.nextInt();

			System.out.print("열 크기 :");
			int col = sc.nextInt();

			if(row<1 || row>10 || col<1 || col>10) {

			}else {
				char arr[][] = new char[row][col];
				for(int x=0; x<row;x++) {
					for(int y=0; y<col;y++) {
						arr[x][y]=(char)(Math.random()*26+65);
						//0.0<= z < 1.0
						//0.0<=z*26<26.0
						//65.0<=(int)(z*26)+65<91.0
						System.out.print(arr[x][y]+" ");
					}
					System.out.println();
				}
				break;
			}
		}

		/*




		Scanner sc = new Scanner(System.in);
		boolean flag=false;
		while(!flag) {

			System.out.print("행 크기 :");
			int row=sc.nextInt();
			System.out.print("열 크기 :");
			int col=sc.nextInt();
			int [][] arr= new int[row][col];
			if(row<0 || row>10 || col<0 || col>10) {
				System.out.print("반드시 1~10 사이의 정수를 입력해야 합니다\n");
				flag=false;
			}else {
				flag=true;
				for(row=0; row<arr.length-1; row++) {
					System.out.println();
					for(col=0; col<arr[row].length-1;col++) {
						arr[row][col]=(char)(Math.random()*10+65);
						System.out.printf("%3d",arr[row][col]);
					}
				}
			}
		}*/
	}
	public void practice6() {
		//사용자에게 행의 크기를 입력받고 그 수만큼의 반복을 통해 열의 크기도 받아
		//문자형 가변 배열을 선언 및 할당하세요
		//그리고 각 인덱스에 'a'부터 총 인덱스의 개수만큼 하나씩 늘려 저장하고 출력하세요
		//실행화면
		//행의크기 :4
		//0열의크기 :2
		//1열의크기 :6
		//2열의크기 :3
		//3열의크기 :5
		//a b
		//c d e f h
		//i j k
		//l m n o p
		Scanner sc = new Scanner(System.in);
		System.out.print("행의 크기 :");
		int input=sc.nextInt();
		char ch ='a';
		char[][] arr = new char[input][];
		//열의 크기를 정하는 for문
		for(int i=0; i<input;i++) {

			System.out.print(i+"열의 크기 :");
			int col=sc.nextInt();
			arr[i]=new char[col];
		}

		//출력용 for문
		for(int row =0; row<arr.length; row++) { //행 반복

			for(int col=0; col<arr[row].length;col++) { //열 반복

				arr[row][col]=ch++;
				System.out.print(arr[row][col]+" ");
			}
			System.out.println();
		}
	}
	public void practice7() {
		/*1차원 문자열 배열에 학생 이름 초기화되어있다
		 * String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배", 
				   "송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하"};
		 * 3행 2열짜리 2차원 문자열 배열 2개를 새로 선언 및 할당하여
		 * 학생이름을 2차원 배열에 순서대로 저장하고 아래와 같이 출력하시오
		 * (첫번째 2차원 배열이 모두 저장된 경우 2번째 2차원 배열에 저장 진행)
		 * 
		 * [실행화면]
		 * ==1분단==
		 * 강건강 남나나
		 * ......
		 * ==2분단==
		 * 송성실 윤예의			(0,0)=강건강 (0,1)남나나 (1,0)도대담 (1,1)류라라
		 * .....
		 * */
		int count=0;
		String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배", 
				"송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하"};
		String[][] one =new String[3][2];
		String[][] two =new String[3][2];
		System.out.print("==1분단==\n");
		for(int row=0; row<one.length;row++) {
			for(int col=0; col<one[row].length;col++) {
				one[row][col]=students[count];
				count++;
				System.out.print(one[row][col]+" ");
			}
			System.out.println();
		}
		System.out.print("==2분단==\n");
		for(int row=0; row<two.length;row++) {
			for(int col=0; col<two[row].length;col++) {
				two[row][col]=students[count];
				count++;
				System.out.print(two[row][col]+" ");
			}
			System.out.println();
		}
	}

	public void practice7_1() {
		String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배", 
				"송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하"};

		String[][] arr1=new String[3][2];
		String[][] arr2=new String[3][2];

		int index=0; // students 배열에서 0부터 1씩 증가하며 학생들을 선택하는 용도의 변수

		System.out.println("==1분단 ==");
		for(int row=0; row<arr1.length; row++) {
			for(int col=0; col<arr1[row].length; col++) {
				arr1[row][col]=students[index];
				//students 배열에서 index번째 학생을 arr1[row][col]에 대입
				index++;
				System.out.print(arr1[row][col]+" ");
			}
			System.out.println();//줄바꿈
		}
		System.out.println("==2분단 ==");
		for(int row=0; row<arr2.length; row++) {
			for(int col=0; col<arr2[row].length; col++) {
				arr2[row][col]=students[index];
				//students 배열에서 index번째 학생을 arr1[row][col]에 대입
				index++;
				System.out.print(arr2[row][col]+" ");
			}
			System.out.println();//줄바꿈
		}
	}

	public void practice8() {

		//위 문제에서 자리 배치한것을 가지고 학생 이름을 검색하여
		//해당 학생이 어느자리에 앉았는지 출력하세요.
		int count=0;
		String a="";
		Scanner sc = new Scanner(System.in);
		String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배", 
				"송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하"};
		String[][] one =new String[3][2];
		String[][] two =new String[3][2];
		System.out.print("==1분단==\n");
		for(int row=0; row<one.length;row++) {
			for(int col=0; col<one[row].length;col++) {
				one[row][col]=students[count];
				count++;
				System.out.print(one[row][col]+" ");
			}
			System.out.println();
		}
		System.out.print("==2분단==\n");
		for(int row=0; row<two.length;row++) {
			for(int col=0; col<two[row].length;col++) {
				two[row][col]=students[count];
				count++;
				System.out.print(two[row][col]+" ");
			}
			System.out.println();
		}
		System.out.print("검색할 학생 이름을 입력하세요 :");
		String name= sc.nextLine();

		for(int row=0; row<one.length; row++) {
			for(int col=0; col<one[row].length; col++) {
				if(name.equals(one[row][col])) {
					if(col==0) {
						a = "왼쪽";
					}if(col==1) {
						a = "오른쪽";
					}

					System.out.print("검색하신 "+name+" 학생은 "+"1분단 "+(row+1)+"번째 줄 "+a+"에 있습니다.");

					//0 < 왼쪽
					//1 < 오른쪽
				}
			}
		}
		for(int row=0; row<two.length; row++) {
			for(int col=0; col<two[row].length; col++) {
				if(name.equals(two[row][col])) {
					if(name.equals(two[row][col])) {
						if(col==0) {
							a = "왼쪽";
						}if(col==1) {
							a = "오른쪽";
						}
					}
					System.out.print("검색하신 "+name+" 학생은 "+"2분단 "+(row+1)+"번째 줄 "+a+"에 있습니다.");
				}
			}
		}


	}

	public void practice9() {
		//String 2차열 배열 6행6열을 만들고 행의 맨 위와 제일 앞 열은 각 인덱스를 저장하세요
		//그리고 사용자에게 행과 열을 입력받아 해당 좌표의 값을"X"로 변환해 2차원 배열을 출력하세요

		//[실행화면]
		//행 인덱스 입력: 4
		//열 인덱스 입력 :2
		// 0 1 2 3 4
		//0
		//1
		//2
		//3
		//4	   x
		Scanner sc = new Scanner(System.in);
		int num1=0;
		int num2=0;
		String[][] arr= new String[6][6];
		System.out.print("행 인덱스 입력 :");
		int rowIndex=sc.nextInt();
		System.out.print("열 인덱스 입력 :");
		int colIndex=sc.nextInt();

		for(int row=0;row<arr.length;row++) { //row = 0,1,2,3,4,5

			for(int col =0; col<arr[row].length;col++) { // col=0,1,2,3,4,5

				if(row==0 && col!=0) {
					arr[row][col]=num1++ +" ";
					//숫자+ 문자열 = 문자열(이어쓰기됨)
				}else if(col==0 && row!=0) {
					arr[row][col]=num2++ +" ";
				}else {
					arr[row][col]="  ";
				}

				arr[rowIndex+1][colIndex+1] = "X";
				System.out.print(arr[row][col]);
			}
			System.out.println();
		}



	}

	public void practice10() { //위에꺼 똑같은데 99입력시까지 계속반복

		Scanner sc = new Scanner(System.in);
		int num1=0;
		int num2=0;
		String[][] arr= new String[6][6];
		//기본 실행화면 출력용 for문
		for(int row=0;row<arr.length;row++) { 

			for(int col =0; col<arr[row].length;col++) { 

				if(row==0 && col!=0) {
					arr[row][col]=num1++ +" ";
				}else if(col==0 && row!=0) {
					arr[row][col]=num2++ +" ";
				}else {
					arr[row][col]="  ";
				}

				//				System.out.print(arr[row][col]);
			}
			//			System.out.println();
		}
		while(true) {

			System.out.print("행 인덱스 입력 :");
			int rowIndex=sc.nextInt();
			if(rowIndex==99) {
				System.out.print("\n프로그램 종료");
				break;
			}
			System.out.print("열 인덱스 입력 :");
			int colIndex=sc.nextInt();
			arr[rowIndex+1][colIndex+1] = "X";
			//화면에 보이는 행,열에 X값 저장
			for(int row=0; row<arr.length; row++) {
				for(int col=0;col<arr[row].length; col++){
					System.out.print(arr[row][col]);
				}
				System.out.println();
			}
		}
	}

	public void practice11() {
		Scanner sc = new Scanner(System.in);
		System.out.print("빙고판 크기 지정 :");
		int size=sc.nextInt();
		int bingoCount=0;
		String[][] arr= new String[size][size];
		for(int row=0; row<arr.length;row++) {
			for(int col=0; col<arr[row].length;col++) {
				String random=""+(int)(Math.random()*size*size+1);
				arr[row][col]=random;
				System.out.print(arr[row][col]+" ");
			}
			System.out.println();
		}

		System.out.print("=====빙고게임 시작=====");
		while(true) {

			System.out.print("\n정수를 입력하시오 :");
			String input = sc.nextLine();
			for(int row=0; row<arr.length;row++) {
				for(int col=0; row<arr[row].length;col++) {


					if(arr[row][col]==input) {
						arr[row][col]="*";
					}else {
						System.out.print("다시 입력하세요");
						break;
					}
				}
			}
			for(int row=0; row<arr.length;row++) {
				for(int col=0; row<arr[row].length;col++) {
					System.out.print(arr[row][col]+" ");
				}
			}
			System.out.println();
		}


	}

	public void bingo() {

		Scanner sc = new Scanner(System.in);

		System.out.print("빙고판 크기 지정 : ");
		int size = sc.nextInt();

		// 1차원 배열로 빙고판에 입력될 값 생성 + 중복 제거
		int[] randomArr = new int[size*size];
		// -> 빙고판의 크기는 가로, 세로의 곱 만큼의 공간이 필요

		// 중복 제거하면서 랜덤값 집어넣기
		for(int i=0; i<randomArr.length; i++) {
			randomArr[i] = (int)(Math.random() * (size * size) + 1);

			// 중복 제거
			for(int j=0; j<i; j++) {
				if(randomArr[i] == randomArr[j]) {
					i--;
					break;
				}
			}
		}

		// 위에서 만들어진 중복제거한 1차원 배열(randomArr)을 2차원 배열에 넣기
		// String 배열로 변경해서 진행 
		// 왜? 빙고가 된 부분을 "★"로 변경하기 위해서
		String[][] bingoBoard = new String[size][size];

		int index = 0; // 1차원 배열(randomArr)의 인덱스 지정을 위한 변수

		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				bingoBoard[i][j] = randomArr[index++] + "";

				// 랜덤 배치된 빙고판 출력
				System.out.printf("%4s", bingoBoard[i][j]);
			}
			System.out.println();
		}

		System.out.println("==========빙고게임시작=========");
		while(true) {
			System.out.print("정수를 입력하시오 :");
			String input = sc.next();
			boolean flag=true; //검색된 값이 빙고판에 있는지 확인용 변수
			// flag == true : 값 존재 X
			// flag == false : 값 존재 O
			for(int i=0; i<bingoBoard.length; i++) {
				for(int j=0; j<bingoBoard.length;j++) {

					//입력된 값과 일치하는 곳을 ★로 변환
					if(input.equals(bingoBoard[i][j])) {
						bingoBoard[i][j]="★";
						flag=false;
					}

					System.out.printf("%4s", bingoBoard[i][j]);
				}
				System.out.println(); //줄바꿈
			}
			if(flag) { //값이 존재하지 않을경우
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.\n");
				continue;
			}

			//빙고 검사

			//빙고 기준이 되는 문자열 생성
			//ex)4*4크기 빙고 => 한 줄이 "★★★★"면 빙고

			String bingoLine="";
			for(int i=0; i<size; i++) {
				bingoLine +="★";
			}
			int bingoCount =0; //빙고 수를 저장할 변수

			//가로(행) 또는 세로(열)의 문자열을 더하여 하나의 문자열로 저장
			//-> 저장된 문자열의 모양이 "★★★★"인 경우 ==빙고
			//-> bingoCount 증가


			for(int i=0; i<bingoBoard.length; i++) {
				//매 반복시 마다 rowLine,colLine을 빈 문자열로 초기화
				// 왜? 바깥쪽 for문이 반복될 때마다 검사하는 행과 열이 이동하므로
				//		빙고 여부를 검사하기 위한 rowLine,colLine을 빈문자열로 초기화 해야함
				String rowLine="";
				String colLine="";

				for(int j=0; j<bingoBoard[i].length; j++) {
					rowLine+=bingoBoard[i][j]; // 현재 행의 문자를 모두 더함
					colLine +=bingoBoard[j][i]; // i,j(행렬)를 반대로 하여 열의 모든 문자를 더함
				}
				if(rowLine.equals(bingoLine)) { //가로 빙고가 존재할 경우
					bingoCount++;
				}

				if(colLine.equals(bingoLine)) {
					bingoCount++;
				}
			}

			// 대각선 빙고 여부
			// 대각선 : diagonal

			//대각선은 빙고판에서 두개만 존재
			//-> 대각선 문자를 더해 저장할 변수 두개 선언 및 빈문자열로 초기화

			String dia1Line ="";
			String dia2Line="";
			for(int i=0; i<bingoBoard.length; i++) {
				dia1Line +=bingoBoard[i][i]; // 좌상 우하
				dia2Line +=bingoBoard[i][bingoBoard.length-i-1]; //우상 좌하
			}
			if(dia1Line.equals(bingoLine)) {
				bingoCount++;
			}
			if(dia2Line.equals(bingoLine)) {
				bingoCount++;
			}
			//빙고 카운트 출력
			System.out.print("현재 "+bingoCount+"빙고");
			if(bingoCount>=3) { //빙고 개수가 3개 이상인 경우
				System.out.print("\n***Bingo!***");
				break;
			}
		}
	}
}








