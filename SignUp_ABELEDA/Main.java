package SignUp_ABELEDA;

public class Main {
    public static void main(String[] args) {
        Loading loading = new Loading();
        FirstPage firstPage = new FirstPage();
        loading.setVisible(true);
        loading.iterate();
        loading.dispose();
        firstPage.setVisible(true);
    }
}
