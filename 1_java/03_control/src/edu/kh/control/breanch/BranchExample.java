package edu.kh.control.breanch;

import java.util.Scanner;

public class BranchExample {

	// 분기문
	//break : (가장 가까운) 반복문을 멈춤
	//continue : (가장 가까운)반복문의 시작 부분으로 이동 (다음 반복 진행)


	//홀수인 경우 출력 X
	//i가 20인 경우 반복문을 멈춤
	public void ex1() {

		for(int i=1; i<=10000;i++) {
			if(i%2==1) {
				continue;
			}
			System.out.print(i+" ");
			if(i==20)
				break;
		}
	}
	// 1~100 까지 1씩 증가하며 출력하는 반복문
	//단, 5의 배수는 건너뛰고
	//증가하는 값이 40이 되었을때 반복을 멈춤
	public void ex2() {

		for(int i=1; i<=100; i++) {
			if(i==40) {
				break;
			}
			if(i%5==0) {
				continue;
			}

			System.out.print(" "+i);
		}

	}

	//중첩 반복문 내부에서 break 사용하기

	//구구단 2단~9단까지 모두 출력
	//단 , 2단은 x2까지, 3단은 x3까지...9단은 x9까지만 출력
	// 2x1=2 2x2=4
	// 3x1=3 3x2=6 3x3=9
	public void ex3() {

		for(int dan=2; dan<=9; dan++) {

			for(int i=1; i<=9; i++) {
				System.out.printf("%d X %d = %d ", dan,i,dan*i);
				if(dan==i) { //단과 곱해지는 수가 같을 경우
					break;
					//분기문이 중첩 반복문 내에서 사용되면
					//가장 가까운 반복문에 적용!
				}
			}
			System.out.println();
		} 
	}

	//col이 3의 배수인 경우 출력X
	//row가 3일때 반복 종료

	public void ex4() {
		for(int row = 1; row <=6; row++) {

			for(int col=1; col<=10; col++) {//col이 3의 배수인 경우 출력X
				if(col%3==0) {
					continue; //다음 반복 수행
				}
				System.out.printf("(%d,%d)",row,col);
			}
			System.out.println();

			if(row==3) {//row가 3일때 반복 종료
				break;
			}
		}
	}
	//0이 입력될때까지 모든 정수의 합 구하기
	public void ex5() {

		Scanner sc = new Scanner(System.in);

		int sum=0;
		int input=0;

		//방법1. while : input에 초기값을 0이 아닌 다른 값

		//방법2. do-while

		//방법3. while(무한루프)+break

		while(true) { //무한 반복(무한 루프)
			System.out.print("정수 입력 : ");
			input = sc.nextInt();
			if(input==0) {
				break;
			}
			sum += input;
		}

		System.out.println("합계 : "+ sum);
		//break;없으면 뜨는 에러 ->Unreachable code : 도달할 수 없는 코드
	}
	/*1번째 입력 : 1000
	 ****1~100사이 수를 입력해주세요 *** 
	 * 
	 * 1번째 입력: 51
	 * DOWN
	 * 2번째 입력 :27
	 * UP
	 * 3번째 입력 :35
	 * ...
	 * 6번째 입력:
	 */
	public void upDownGame() {

		Scanner sc = new Scanner(System.in);
		int count=1;

		// Math.random() : 0.0이상 1.0미만의 난수를 반환

		int answer = (int)(Math.random()*100+1);

		//	System.out.println(answer); //답안 임시 확인


		while(true) { //언제 끝날지 모르니 무한 반복
			System.out.printf("%d 번째 입력 :",count);
			int input=sc.nextInt();

			//잘못 입력한 경우
			if(input<1 || input>100) {
				System.out.println("1~100사이의 수를 입력하세요");
				continue;
			}

			//재대로 입력한 경우
			if(input<answer) { //입력한 값이 정답보다 작은 경우
				System.out.println("UP");	
			}else if(input>answer) { //입력한 값이 정답보다 큰 경우
				System.out.println("DOWN");

			}else{ //같은 경우
				System.out.println("[정답!!!]");
				System.out.printf("총 입력 횟수 : %d회", count);
				break; //while문 반복 종료
			}
			count++;
		}
	}

	//입력 받은 모든 문자열을 누적
	//단, "end!" 입력시 문자열 누적을 종료하고 결과 출력

	public void ex6() {

		Scanner sc= new Scanner(System.in);
		String str =""; //빈 문자열

		while(true) {


			System.out.print("문자열 입력(end! 입력시 종료) : ");
			String input = sc.nextLine();
			// next() : 다음 한 단어(띄어쓰기 포함 X / 띄어쓰기, 엔터를 만나면 입력종료)
			// nextLine() : 다음 한 줄 (띄어쓰기 포함 O / 엔터를 만나면 입력 종료)

			//**next()로 문장을 작성 시 결과가 이상한 이유 **
			//1)next()는 한 단어만 읽어옴
			//2) 입력 -> 입력 버퍼에 저장 -> nextXXX() 통해 버퍼 내용을 읽어옴
			// *next(), nextInt(), nextDouble() 등
			// 모두 입력 버퍼에서 (엔터)를 제외하고 내용만 읽어옴
			//->이후 nextLine() 사용 시 입력받지 못하고 종료됨
			//->why? 입력 버퍼에 남아있는 (엔터)를 읽었기 때문

			//해결 방법
			//입력을 위한 nextLine() 수행 전 
			//입력 버퍼에서 (엔터)를 제거
			// -> 빈 공간에 sc.nextLine() 구문을 한번 작성하면 (엔터)가 제거됨.


			//입력 받는 문자열이 "end!면 반복 종료
			//if(input=="end!") X
			if(input.equals("end!")) {


				//String 자료형은 비교 연산자(==)로 같은 문자열인지 판별 X

				//비교 연산자는 보통 기본 자료형끼리 연산에만 사용 가능
				//-> String은 기본 자료형이 아닌 참조형

				//[해결방법] : 문자열1.equlas(문자열2)
				break;
			}
			str +=input + "\n";
		}		
		System.out.println(str);
	}
	
	//가위 바위 보 게임
	
	// 몇판? : 3
	// 1번째 게임
	   // 가위/바위/보 중 하나를 입력 해주세요 :  가위
	   // 컴퓨터는 [보]를 선택했습니다.
	   // 플레이어 승!
	   // 현재 기록 : 1승 0무 0패

	   // 2번째 게임
	   // 가위/바위/보 중 하나를 입력 해주세요 :  보
	   // 컴퓨터는 [보]를 선택했습니다.
	   // 비겼습니다.
	   // 현재 기록 : 1승 1무 0패

	   // 3번째 게임
	   // 가위/바위/보 중 하나를 입력 해주세요 :  가위
	   // 컴퓨터는 [바위]를 선택했습니다.
	   // 졌습니다ㅠㅠ
	   // 현재 기록 : 1승 1무 1패
	
	public void RPSGame() {
		
		
		int w=0;
		int d=0;
		int l=0;
		int count=1;
		Scanner sc = new Scanner(System.in);
	
		System.out.print("몇판 ?");
		int loopcount=sc.nextInt();
					sc.nextLine();
		while(count!=loopcount+1) {
		
			
			int computer = (int)(Math.random()*3+1);
			String computer1 ="" ;
			switch(computer) {
			case 1: computer1="가위"; break;
			case 2: computer1="바위"; break;
			case 3: computer1="보"; break;
			
			}
			
			System.out.printf("%d번째 게임\n",count);
			
			System.out.print("가위/바위/보 중 하나를 입력 해주세요 :");
			String me = sc.nextLine();
			System.out.print("컴퓨터는"+"["+computer1+"]"+"를 선택했습니다.");
			
			if(me.equals(computer1)) {
				System.out.println("\n비겼습니다"); 
				d++;
			}else if((me.equals("바위") && computer1.equals("가위")) || ((me.equals("가위") && computer1.equals("보")) || (me.equals("보") && computer1.equals("바위")))) {
				System.out.println("\n플레이어 승!");
				w++;
			}else {
				System.out.println("\n졌습니다ㅠㅠ");
				l++;
			}
			count++;
			System.out.printf("현재 기록 : %d승 %d무 %d패\n",w,d,l);

		}
	}
	
	public void RPSGame_1() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[가위 바위 보 게임]");
		System.out.print("[몇 판? :]");
		int round = sc.nextInt();
		
		// 승패 기록할 용도의 변수
		int win =0;
		int draw = 0;
		int lose=0;
		
		for(int i=1; i<=round; i++) { //입력한 판수 만큼 반복
			System.out.println( i + "번째 게임");
			System.out.println("가위 바위 보 중 하나를 입력해주세요 :");
			
			String input = sc.next(); // 플레이어가 가위/바위/보 중 하나 입력
			
			// 컴퓨터 가위/바위/보 정하기(랜덤)
			// 1)1~3 사이 난수 생성
			// 2)1이면 가위, 2면 바위, 3이면 보 지정
			
			// Math.random() : 0.0이상 1.0 미만의 난수 생성
			
			int random = (int)(Math.random()*3 + 1);
			
			String com = null ; // 컴퓨터가 선택한 가위/바위/보 저장하는 변수
			// null : 아무것도 참조하고 있지 않음
			
			switch(random) {
			
			case 1 : com = "가위"; break;
			case 2 : com = "바위"; break;
			case 3 : com = "보"; break;
			
			}
			
			System.out.printf("컴퓨터는 [%s]를 선택했습니다.\n",com);
			
			//컴퓨터와 플레이어(기준) 가위 바위 보 판별
			// win, draw , lose
			
			// String 비교시 equlas() 사용!!
			if(input.equals(com)) { //비긴 경우
				System.out.println("비겼습니다.");
				draw++;
				
			}else {
				//boolean 으로도 가능
					//boolean win1 = input.equals("가위") && com.equals("보");
				//boolean win2 = input.equals("바위") && com.equals("가위");
				//boolean win3 = input.equals("보") && com.equals("바위");
				
				if(input.equals("가위") && com.equals("보") || 
						input.equals("바위") && com.equals("가위")||
						input.equals("보") && com.equals("바위")) { 
					System.out.println("플레이어 승!");
					win++;
					
				}else{
					System.out.println("졌습니다ㅠㅠ");
					lose++;
				} // else 끝
				
				System.out.printf("현재 기록 : %d승 %d무 %d패\n",win,draw,lose);
				
			}
			
		}
	}
}


