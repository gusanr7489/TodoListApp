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
		
		System.out.print("<�׸� �߰�>\n"
				+ "���� -> ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("�׸� ������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		System.out.print("���� -> ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		
		System.out.print("\n"
				+ "<�׸� ����>\n"
				+ "������ ������ �Է��ϼ��� -> ");
		
		String title = sc.next();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("�����Ǿ����ϴ�.");
				flag = true;
				break;
			}
		}
		if(!flag) System.out.println("�׸��� ã�� ���� �����ϴ�.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<�׸� ����>\n"
				+ "������ �׸��� ������ �Է��ϼ��� -> ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�׸��� ã�� ���� �����ϴ�.");
			return;
		}

		System.out.print("������ ��� �����Ͻðڽ��ϱ�? -> ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�׸� ������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		
		System.out.print("������ ��� �����Ͻðڽ��ϱ�? -> ");
		String new_description = sc.nextLine();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�׸��� �����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<��ü ���>");
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
			System.out.println("���Ͽ� ���������� ����Ǿ����ϴ�.");
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
			System.out.println("������ ���������� �������ϴ�.");
		} catch (FileNotFoundException e) {
			System.out.println("����� ������ �����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
