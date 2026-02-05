import java.util.ArrayList;

public class LigneTab implements Ligne{

    private ArrayList<Character> caracteres;
    private int curseur;

    public LigneTab() {
        this.caracteres = new ArrayList<>();
        this.curseur = 0;
    }

    @Override
    public int getLongueur() {
        return caracteres.size();
    }

    @Override
    public int getCurseur() {
        return curseur;
    }

    @Override
    public void avancer() {
        if (curseur < caracteres.size())
            curseur++;
        else 
            throw new IllegalStateException("Le curseur est déjà à la fin de la ligne.");
    }

    @Override
    public void raz() {
        if (!caracteres.isEmpty()) {
            curseur = 1;
        }
    }
}
