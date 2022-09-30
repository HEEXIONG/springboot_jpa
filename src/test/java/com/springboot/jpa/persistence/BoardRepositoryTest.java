package com.springboot.jpa.persistence;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.jpa.entity.Board;

@RunWith(SpringRunner.class)
@SpringBootTest  // 테스트 코드에는 BoardRepositoryTest 클래스 선언 밑에
public class BoardRepositoryTest {
    // BoardRepository를 주입하고
	@Autowired
	private BoardRepository boardRepo;

	// @Test 어노테이션이 붙은 테스트 코드를 작성해 줍니다.
	@Test
	public void inspect() {
		// 실제 객체의 클래스를 생성합니다.
		Class<?> clz = boardRepo.getClass();
		// 실행 결과를 보면 실제 클래스가 com.sun.proxy...로 시작하는 것을 볼 수 있습니다.
		// 클래스의 이름의 com.sun.proxy로 시작하는 경우에는 Java 언어의 Dynamic Proxy(동적 프록시) 기능에 의해
		// 동적으로 생성된 클래스를 의미한다고 보시면 됩니다.
		// 클래스 이름의 중간에 있는 "$"는 보통 내부 클래스임을 나타냅니다.
		System.out.println(clz.getName()); // 실제 객체의 클래스 이름을 Console 창에 출력해 줍니다.

		// 클래스가 구현하고 있는 인터페이스 목록을 생성합니다.
		Class<?>[] interfaces = clz.getInterfaces();
		// 클래스가 구현하고 있는 인터페이스 목록을 Console 창에 출력해 줍니다.
		Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));  

		// 클래스의 Super(부모) 클래스를 생성합니다.
		Class<?> superClasses = clz.getSuperclass();
		// 클래스의 Super(부모) 클래스의 이름을 Console 창에 출력해 줍니다.
		System.out.println(superClasses.getName());
	}
	
	@Test
	public void testInsert() {
		Board board = new Board();
		board.setTitle("게시물의 제목");
		board.setContent("게시물 내용 넣기");
		board.setWriter("장나라");
		
		boardRepo.save(board);
	}
	
	@Test 
	public void testRead() {
		//보드 타입은 식별 데이터를 long 타입으로 사용했으므로
		//'1L'과 같이 Long 타입으로 파라미터를 지정합니다.
		boardRepo.findById(1L).ifPresent((board)->{
			System.out.println(board);
		});
		
	}
	@Test
	public void testUpdate() {
		System.out.println("Read First");
		//1번 게시물의 title 속성 값을  "안녕 김희선 "문자열러 수정 쿼리문을 처리합니다.
		Board board = boardRepo.findById(3L).get();
		
		System.out.println("Update Title");
		board.setTitle("안녕 김희선");
		
		System.out.println("Call save()");
		boardRepo.save(board);
	}
	
	@Test
	public void testDelete() {
		//객체 삭제 할 때, select 쿼리문으로 데이터 확인 후 delete 실행 처리함
		System.out.println("객체 삭제");
		//Long타입으로 첫번째 파라미터 값 (1번 게시물)삭제 처리함
		boardRepo.deleteById(2L);
	}
	
	
	
	
	
}
