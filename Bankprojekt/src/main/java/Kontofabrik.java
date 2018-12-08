
interface AbstractFactory{

    public Sparbuch createSparbuch();
    public Girokonto createGirokonto();

}

class Kontofabrik implements AbstractFactory{

    @Override
    public Sparbuch createSparbuch() {
        return new Sparbuch();
    }

    @Override
    public Girokonto createGirokonto(){
        return new Girokonto();
    }
}
