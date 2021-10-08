package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			
			if(choice.contains("find_cate")) {
				TodoUtil.findCategory(choice.replace("find_cate ", ""), l);
				continue;
			}
			
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "find":
				String target = sc.nextLine().trim();
				TodoUtil.find(target, l);
				break;
				
			case "ls_name_asc":
				l.sortByName();
				isList = true;
				System.out.println("제목순으로 정렬했습니다.");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				System.out.println("제목순으로 역정렬했습니다.");
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				System.out.println("날짜순으로 정렬했습니다.");
				break;
			
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				isList = true;
				System.out.println("날짜순으로 역정렬했습니다.");
				break;	
				
			case "ls_cate":
				TodoUtil.listCategory(l);
				break;
				
			case "exit":
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			default:
				System.out.println("해당하는 명령어가 아닙니다.");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
