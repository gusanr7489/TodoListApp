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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<항목 추가>\n"
				+ "제목 -> ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("항목 제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		System.out.print("내용 -> ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		
		System.out.print("\n"
				+ "<항목 삭제>\n"
				+ "삭제할 제목을 입력하세요 -> ");
		
		String title = sc.next();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다.");
				flag = true;
				break;
			}
		}
		if(!flag) System.out.println("항목을 찾을 수가 없습니다.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<항목 수정>\n"
				+ "수정할 항목의 제목을 입력하세요 -> ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("항목을 찾을 수가 없습니다.");
			return;
		}

		System.out.print("제목을 어떻게 변경하시겠습니까? -> ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("항목 제목이 중복됩니다!");
			return;
		}
		sc.nextLine();
		
		System.out.print("내용을 어떻게 변경하시겠습니까? -> ");
		String new_description = sc.nextLine();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 수정되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<전체 목록>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
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
				l.addItem(new TodoItem(st.nextToken(),st.nextToken(),st.nextToken()));
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
