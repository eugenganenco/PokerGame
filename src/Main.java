public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
        Model model = new Model(view);
        controller.addView(view);
        controller.addModel(model);
    }
}