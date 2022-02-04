package fr.alkanife.jsontotxt;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;

public class JSONtoTXT extends ListenerAdapter {

    public static void main(String[] args) {
        Characters.load();

        for (Character character : Characters.getCharacters()) {

            try {
                FileWriter fileWriter = new FileWriter("done/" + character.getFull_name().toLowerCase(Locale.ROOT)
                        .replaceAll(" ", "-").replaceAll("/", "-") + ".txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println(character.getFull_name() + ", *" + character.getTitle() + "*");
                printWriter.println();
                printWriter.println("**RACE :** " + character.getRace());
                printWriter.println("**ÂGE :** " + character.getAge());
                printWriter.println("**SEXE :** " + character.getSex());
                printWriter.println("**ÉTAGE :** " + character.getFloor());
                printWriter.println("**STATUS :** " + character.getState());
                printWriter.println("**SPÉCIALISATION(S) :** " + character.getSpecializations());
                printWriter.println("**ARME(S) :** " + character.getWeapons());
                printWriter.println("**TRAITS DE CARACTÈRE :** " + character.getCharacter_traits());
                printWriter.println("**ASPECT PHYSIQUE :** " + character.getAspect());
                printWriter.println("**POUVOIR(S) :** " + character.getOwn_power());
                printWriter.println("**PROPRIÉTAIRE ORIGINEL :** " + character.getOriginal_owner());
                printWriter.println("**INSPIRATIONS :** " + character.getOwner_inspirations());
                printWriter.println("**NOTES :** " + character.getOwner_notes());

                printWriter.close();
            } catch (Exception exception) {
                exception.printStackTrace();
                break;
            }

        }
    }

}