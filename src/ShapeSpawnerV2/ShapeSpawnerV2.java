package ShapeSpawnerV2;

import java.util.Scanner;

//메인 부모 클레스 (부모 A)
class ConsoleMenu {
	Scanner sc = new Scanner(System.in);
	String SH; // 높이 입력값을 저장 받을 변수

	public void ConsoleMenu() { // 메인 메뉴 메서드
		System.out.println("-----------------------------------");
		System.out.println("- 1 - 직삼각형v1 출력");
		System.out.println("- 2 - 직삼각형v2 출력");
		System.out.println("- 3 - 정삼각형 출력");
		System.out.println("- 4 - 평행사변형 출력");
		System.out.println("- 5 - 마름모 출력");
		System.out.println("- 6 - 종료");
		System.out.println("-----------------------------------");
	}

	public void lineSpawner() { // 줄 생성기 메서드
		System.out.println("-----------------------------------");
	}

	public void clearConsole() { // 콘솔창을 정리하는 메서드
		for (int i = 0; i < 50; i++) { // 자바 콘솔창 정리법을 못찾겠어서 50개의 라인을
			System.out.println(); // 밀어버리는걸로 대체함
		}
	}

}

class Shapes extends ConsoleMenu { // 도형 생성기를 모아둔 클레스 (자식 B)
	// 직삼각형1
	static void RectangleV1(int h) {
		for (int i = 0; i <= h; i++) {

			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	// 직삼각형2
	static void RectangleV2(int h) {
		for (int i = 0; i <= h; i++) {

			for (int k = 0; k <= h - i; k++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}

			System.out.println();
		}
	}

	// 정삼각형
	static void Triangle(int h) {
		for (int i = 0; i <= h; i++) {

			for (int k = 0; k <= h - i; k++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= i * 2; j++) {
				System.out.print("*");
			}

			System.out.println();
		}
	}

	// 직사각형
	static void Quadrilateral(int h) {
		for (int i = 0; i <= h; i++) {

			for (int k = 0; k <= h - i; k++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= h; j++) {
				System.out.print("*");
			}

			System.out.println();
		}
	}

	// 마름모
	static void Rhombus(int h) {
		for (int i = 0; i <= h; i++) {
			if (i <= h / 2) {
				for (int k = 0; k <= (h - 2) - i; k++) {
					System.out.print(" ");
				}
				for (int j = 0; j <= i * 2; j++) {
					System.out.print("*");
				}

			} else {
				for (int k = 0; k <= i - 1; k++) {
					System.out.print(" ");
				}
				for (int j = 0; j <= (h - 1) * 2 - (i * 2); j++) {
					System.out.print("*");
				}
			}

			System.out.println();
		}
	}

}

class ScannerSearcher extends Shapes { // 입력판독기 클레스(자식 C)
	boolean outStringSearcher; // 정수,문자 구분 탈출용 변수
	boolean outintSearcher; // 홀수,짝수 구분 탈출용 변수
	int intSH; // 정수로 판명된걸 저장하는 변수
	int OKSH; // 홀수로 판명된걸 저장하는 변수

	public int scannerSearcher() { // 문자인지 정수인지 홀수인지 짝수인지 확인후 최종값 반환 메서드
		this.intSH = 0; // 변수 초기화
		this.OKSH = 0;

		System.out.println("높이를 입력");
		System.out.print("(주의! 홀수만!) : ");
		this.outStringSearcher = true;
		while (outStringSearcher == true) { // 문자 인지 정수인지 확인 루프 시작
			this.SH = sc.next();

			if (isint(SH) == true) {
				OKSH = searcherheight(Integer.parseInt(this.SH)); // 작수 홀수 확인 메서드 실행 후 반환값 저장
			} else {
				System.err.println("정수만 입력하세요!");
			}

		}
		return OKSH;
	}

	public boolean isint(String str) { 			// 문자인지 정수인지 확인하는 메서드
												// Integer.parseInt 는 문자를 정수로 변환한다.
		try { 									// 하지만 변환하려는 값이 정수문자가 아니라 문자이면 
			Integer.parseInt(str);				// NumberFormatException 오류가 발생한다.
			return true;
		} catch (NumberFormatException ex) {	// 만약 NumberFormatException 오류가 발생하면 예외처리하고
			return false;						// false를 반환한다.
		}
	}

	public int searcherheight(int sh) { 		// 홀수인지 짝수인지 확인하는 메서드
		this.outintSearcher = true;
		while (outintSearcher == true) { 		// 홀수인지 짝수인지 확인하는 루프 시작
			if (sh % 2 == 1) {
				this.intSH = sh;
				this.outintSearcher = false; 	// 홀짝 확인 루프 종료
				this.outStringSearcher = false; // 문자 정수 확인 루프 종료
			} else {
				System.err.println("홀수만 입력하세요!");
				this.outintSearcher = false; 	// 홀짝 확인 루프만 종료시키고 다시 문자 정수 확인 루프로 돌아간다.
			} 									// 문자 정수 확인 루프로 돌아가면 문자 정수 확인 루프가 최종값을 리턴하지만
												// 루프가 정지되지는 않아 다시 처음루프로 돌아간다.
		}
		return this.intSH;

	}

}

interface makeShape {// 각 도형생성기를 가동시킬 인터페이스
	void makeShape();
}

class makeShapeRectangleV1 extends ScannerSearcher implements makeShape { // 동작 클레스 (자식 D)

	@Override
	public void makeShape() {
		clearConsole(); // 콘솔창 청소기
		lineSpawner(); // 줄 생성기
		RectangleV1(scannerSearcher()); // 도형생성기와 도형생성기에 들어갈 값을 받는 메서드
		lineSpawner(); // 줄 생성기
		ConsoleMenu(); // 메인메뉴
	}

}

class makeShapeRectangleV2 extends ScannerSearcher implements makeShape {

	@Override
	public void makeShape() {
		clearConsole();
		lineSpawner();
		RectangleV2(scannerSearcher());
		lineSpawner();
		ConsoleMenu();
	}

}

class makeShapeTriangle extends ScannerSearcher implements makeShape {

	@Override
	public void makeShape() {
		clearConsole();
		lineSpawner();
		Triangle(scannerSearcher());
		lineSpawner();
		ConsoleMenu();
	}

}

class makeShapeQuadrilateral extends ScannerSearcher implements makeShape {

	@Override
	public void makeShape() {
		clearConsole();
		lineSpawner();
		Quadrilateral(scannerSearcher());
		lineSpawner();
		ConsoleMenu();
	}

}

class makeShapeRhombus extends ScannerSearcher implements makeShape {

	@Override
	public void makeShape() {
		clearConsole();
		lineSpawner();
		Rhombus(scannerSearcher());
		lineSpawner();
		ConsoleMenu();
	}

}

class MainRun extends Shapes { // 메뉴 동작 클레스 (자식 C)
	// 인터페이스 연결
	// -------------------------------------------------------
	makeShape ShapeRectangleV1 = new makeShapeRectangleV1();
	makeShape ShapeRectangleV2 = new makeShapeRectangleV2();
	makeShape ShapeTriangle = new makeShapeTriangle();
	makeShape ShapeQuadrilateral = new makeShapeQuadrilateral();
	makeShape ShapeRhombus = new makeShapeRhombus();
	// -------------------------------------------------------

	String mselecter; // 루프탈출용 변수
	boolean inmenu; // 스위치 선택용 변수

	public void MainRun() {
		ConsoleMenu();
		this.inmenu = true;
		while (inmenu == true) {
			System.out.print("명령어 입력 : ");
			this.mselecter = sc.next();

			switch (mselecter) {
			// 직삼각형v1 형성
			case "1": {
				ShapeRectangleV1.makeShape();
				break;
			}
			// 직삼각형v2 형성
			case "2": {
				ShapeRectangleV2.makeShape();
				break;
			}
			// 정삼각형 형성
			case "3": {
				ShapeTriangle.makeShape();
				break;
			}
			// 평행사변형 형성
			case "4": {
				ShapeQuadrilateral.makeShape();
				break;
			}
			// 마름모 형성
			case "5": {
				ShapeRhombus.makeShape();
				break;
			}
			// 종료
			case "6": {
				inmenu = false;
				System.out.println("종료합니다.");
				break;
			}
			// 그외의 명령어 오류처리
			default:
				System.err.println("잘못된 명령어 입니다.");
			}
		}
	}

}

public class ShapeSpawnerV2 {
	// ShapeSpawner의 스타틱으로 지정한거 전부 클레스로 변경할 예정
	// main에 호출을 제외한 다른 기능은 사용하지 않을 예정
	// 높이를 입력받아 적용되게 할 예정
	// 홀수값만 넣을수있게하고 다른 값이 들어오면 만들어둔 오류메시지 출력 (문자도 포함)

	public static void main(String[] args) {
		MainRun startshape = new MainRun();
		startshape.MainRun();
	}
}
