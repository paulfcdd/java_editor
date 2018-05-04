import EditorPackage.Editor;

public class App {

    private Editor editor;

    private App() {
        editor = new Editor();
    }

    public static void main(String[] args) {
        App app = new App();
        app.editor.run();
    }
}
