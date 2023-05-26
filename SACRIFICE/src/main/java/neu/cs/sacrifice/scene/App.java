package neu.cs.sacrifice.scene;

import java.util.*;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = "";
        do {
            line = scanner.nextLine();
            System.out.println("Line: " + line);
            solve(line);
        } while(!(line.isEmpty() || line.equals(".")));

    }

    public static void solve(String line) {
        String[] elements = line.split(", ");
        String[] types = new String[elements.length];

        LinkedList<String> stringList = new LinkedList<>();
        LinkedList<Integer> integerList = new LinkedList<>();

        int index = 0;
        for(String e : elements){
            e = e.replace(".", "");
            try {
                int n = Integer.parseInt(e);
                integerList.add(n);
                types[index] = "int";
            }catch (NumberFormatException ex){
                stringList.add(e);
                types[index] = "string";
            }
            index++;
        }

        Collections.sort(stringList);
        Collections.sort(integerList);

        for(int i = 0; i < types.length; i++){
            String type = types[i];
            if(type.equals("string")){
                System.out.print(stringList.poll());
            }else{
                System.out.print(integerList.poll());
            }

            if(i != types.length - 1)
                System.out.print(", ");
            else
                System.out.print(".\n");
        }
    }
}
