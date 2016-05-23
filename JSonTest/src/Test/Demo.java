package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Demo {
	
	private static String fileName = "test.json";
	private static String jsonContent;
	private static ArrayList<Test> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		
		createJSON();
		readFromJSON();
		parseJSON();
		printResult();

	}
	
	private static void printResult() {		
		for(Test test : list){
			System.out.println(test);
		}		
	}

	private static void parseJSON() throws JsonSyntaxException, IOException {
		
		BufferedReader  reader = new BufferedReader(new FileReader(fileName));		
		String content;
		Gson gson = new Gson();
		while((content = reader.readLine()) != null){			
			Test test = gson.fromJson(content, Test.class);			
			list.add(test);
		}		
		reader.close();		
	}

	private static void readFromJSON() throws IOException {
		
		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		StringBuilder str = new StringBuilder();
		String read;
			while((read = br.readLine()) != null){
				str.append(read + "\n");
			}
		br.close();
		
		jsonContent = str.toString();		
		
	}

	private static void createJSON() throws IOException{

		File file = new File(fileName);
		FileWriter wr = new FileWriter(file);		
		
		int age = 1;
		int name = 55;
		int count = 0;
		
		do{
			JSONObject obj = new JSONObject();
			obj.put("age", age);
			obj.put("name", name);

			JSONObject obj2 = new JSONObject();
			obj2.put("age", age + age);
			obj2.put("name", name + name);
			
			obj.put("innerTest", obj2);
			
			wr.write(obj.toString() + "\n");
			count++;
			age++;
			name++;
		}
		while(count != 5);
		
		wr.close();
	}
}
		
		
	
	class Test{
		
		private String name;
		private int age;
		private Test innerTest;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Test getInnerTest() {
			return innerTest;
		}
		public void setInnerTest(Test innerTest) {
			this.innerTest = innerTest;
		}
		
		@Override
		public String toString() {
			return (age + " " + name + " " + innerTest.age + " " + " " + innerTest.name);
		}
		
	}
