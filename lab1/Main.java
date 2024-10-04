public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Library library = parser.parse("C:\\Users\\m4882\\IdeaProjects\\TechProg\\lab1\\random_structure_20.xml");
        String result = library.toString();
        System.out.println(result);
    }
}
