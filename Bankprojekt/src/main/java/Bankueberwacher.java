public class Bankueberwacher implements Bankbeobachter{
    @Override
    public void benachrichtigung(Konto k) {
        System.out.println("Die Bank wurde geändert");
    }
}
