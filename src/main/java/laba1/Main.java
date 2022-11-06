package laba1;

public class Main {

    public static void main(String[] args) {
        Container<Integer> container = new Container<>();
        for(int i = 0; i <= 10; i++)
            container.add(i);
        container.add(0,5) ;
        container.remove(5);
        System.out.println(container);
    }

}
