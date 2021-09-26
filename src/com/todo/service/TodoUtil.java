package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<항목 추가>\n"
				+ "카테고리 -> ");
		
		category = sc.next();
		
		sc.nextLine();
		
		System.out.print("제목 -> ");
		title = sc.next().trim();
		
		if (list.isDuplicate(title)) {
			System.out.println("항목 제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		
		System.out.print("내용 -> ");
		//trim = 양 끝 공백 제거 메소드
		desc = sc.nextLine().trim();
		
		System.out.print("마감일 -> ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<항목 삭제>\n"
				+ "삭제할 항목의 번호를 입력하세요 -> ");
		
		TodoItem target = l.getItem(sc.nextInt()-1);
		
		if(target==null) {
			System.out.println("항목을 찾을 수가 없습니다.");
			return;
		}
		
		System.out.println(target.toString());
		System.out.print("정말로 삭제하시겠습니까? (y/n) -> ");
		String do_delete = sc.next();
		
		if(do_delete.equals("y")) {
			l.deleteItem(target);
			System.out.println("삭제되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<항목 수정>\n"
				+ "수정할 항목의 번호를 입력하세요 -> ");
		
		TodoItem target = l.getItem(sc.nextInt()-1);
		
		System.out.print("새 카테고리? -> ");
		String new_category = sc.next().trim();
		
		System.out.print("새 제목? -> ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title) && !target.getTitle().contains(new_title)) {
			System.out.println("항목 제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		
		System.out.print("새 내용? -> ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일? -> ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		l.editItem(target, t);
		System.out.println("항목이 수정되었습니다.");
		
	}
	
	public static void find(String target, TodoList l) {
		int count=0;
		for(TodoItem myitem : l.getList()) {
			if(myitem.getTitle().contains(target) || myitem.getDesc().contains(target)) {
				System.out.println( (l.indexOf(myitem) + 1) + ". " + myitem.toString());
				count++;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void findCategory(String target, TodoList l) {
		int count=0;
		for(TodoItem myitem : l.getList()) {
			if(myitem.getCategory().contains(target)) {
				System.out.println( (l.indexOf(myitem)+1) + ". " + myitem.toString());
				count++;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void listCategory(TodoList l) {
		String st="";
		int count=0;
		for(TodoItem myitem: l.getList()) {
			if(!st.contains(myitem.getCategory())) {
				if(count==0)
					st = myitem.getCategory();
				else 
					st = st + " / " +myitem.getCategory();
				count++;
			}
		}
		System.out.println(st + "\n" 
				+ "총 " + count + "개의 카테고리가 등록되어 있습니다.");
	}

	public static void listAll(TodoList l) {
		System.out.println("<전체 목록, 총 " + l.getList().size() + "개>");
		int count=0;
		for (TodoItem item : l.getList()) {
			count++;
			System.out.println(count+ ". " +item.toString());
		}
	}
	
	public static void saveList(TodoList l, String fileName) {
		//try-catch for file open to write
		try {
			Writer fw = new FileWriter(fileName);
			for(TodoItem item: l.getList()) {
				fw.write(item.toSaveString());
			}
			fw.close();
			System.out.println("파일에 성공적으로 저장되었습니다.");
		}	catch (FileNotFoundException e){
			e.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String item="";
			while((item=br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(item,"##");
				l.addItem(new TodoItem(st.nextToken(),st.nextToken(),st.nextToken(), st.nextToken(), st.nextToken()));
			}
			br.close();
			System.out.println("파일을 성공적으로 열었습니다.");
		} catch (FileNotFoundException e) {
			System.out.println("저장된 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
